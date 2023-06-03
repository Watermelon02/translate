package com.experiment.translate.repository.local.dao;

import com.experiment.translate.repository.bean.User;
import com.experiment.translate.repository.bean.Word;
import com.experiment.translate.repository.bean.WordSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    private Connection connection;

    // 构造函数和数据库连接的初始化

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    // 插入用户信息
    public void insertUser(User user) {
        String sql = "INSERT INTO user (user_id, account, password, level, learning_days, vocabulary) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, user.getUserId());
            statement.setString(2, user.getAccount());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getLevel());
            statement.setInt(5, user.getLearningDays());
            statement.setInt(6, user.getVocabulary());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    // 根据用户ID查询用户信息
    public User getUserById(long userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                user.setLevel(resultSet.getInt("level"));
                user.setLearningDays(resultSet.getInt("learning_days"));
                user.setVocabulary(resultSet.getInt("vocabulary"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return null;
    }

    // 根据需要添加其他操作方法


    public List<WordSet> getWordSetByUserId(long user_id) {
        String sql = "SELECT word_set_id FROM user_word_set WHERE user_id = ?";
        ArrayList<WordSet> wordSetArrayList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Long> wordSetIdList = new ArrayList<>();
            while (resultSet.next()) {
                wordSetIdList.add(resultSet.getLong(1));
            }
            for (Long wordSetId : wordSetIdList) {
                wordSetArrayList.add(getWordSetById(wordSetId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return wordSetArrayList;
    }

    private WordSet getWordSetById(long wordSetId) {
        String sql = "SELECT * FROM word_set WHERE word_set_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, wordSetId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                WordSet wordSet = new WordSet();
                wordSet.setWordSetId(resultSet.getLong("word_set_id"));
                wordSet.setSetName(resultSet.getString("set_name"));
                wordSet.setSetIntroduction(resultSet.getString("set_introduction"));
                //再向ws_w表中依次查询该单词集包含的单词关系，得到对应单词并插入WordSet中的list中
                String ws_w_sql = "SELECT * FROM ws_w WHERE word_set_id = ?";
                PreparedStatement statement2 = connection.prepareStatement(ws_w_sql);
                statement2.setLong(1, wordSetId);
                ResultSet words = statement2.executeQuery();
                while (words.next()) {
                    wordSet.getWordList().add(getWordById(words.getString("word_id")));
                }
                return wordSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return null;
    }

    private Word getWordById(String id) {
        String sql = "SELECT * FROM word WHERE word_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Word word = new Word();
                word.setWord_id(resultSet.getString("word_id"));
                word.setSpeakUrl(resultSet.getString("speak_url"));
                word.setBasicPhonetic(resultSet.getString("basic_phonetic"));
                word.setUkSpeech(resultSet.getString("uk_speech"));
                word.setUsSpeech(resultSet.getString("us_speech"));
                String explanationSql = "SELECT * FROM explanation WHERE explanation.word_id = ?";
                try (PreparedStatement explanationStatement = connection.prepareStatement(explanationSql)) {
                    explanationStatement.setString(1,id);
                    ResultSet resultSet2 = explanationStatement.executeQuery();
                    while (resultSet2.next()) {
                        String meaning = resultSet2.getString("meaning");
                        word.getExplanation().add(meaning);
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                    // 处理异常
                }
                return word;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return null;
    }
}
