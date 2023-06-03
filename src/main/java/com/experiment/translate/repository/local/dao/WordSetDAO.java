package com.experiment.translate.repository.local.dao;

import com.experiment.translate.repository.bean.Word;
import com.experiment.translate.repository.bean.WordSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordSetDAO {
    private Connection connection;

    public WordSetDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertWordSet(WordSet wordSet) {
        //先向word_set表插入数据
        String word_set_sql = "INSERT INTO word_set (word_set_id, set_name, set_introduction) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(word_set_sql)) {
            statement.setLong(1, wordSet.getWordSetId());
            statement.setString(2, wordSet.getSetName());
            statement.setString(3, wordSet.getSetIntroduction());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        //再向ws_w表中依次插入该单词集包含的单词关系
        String ws_w_sql = "INSERT INTO ws_w (word_set_id, word_id) VALUES (?, ?)";
        for (Word word : wordSet.getWordList()){
            try (PreparedStatement statement = connection.prepareStatement(ws_w_sql)) {
                statement.setLong(1, wordSet.getWordSetId());
                statement.setString(2, word.getWord_id());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                // 处理异常
            }
        }
    }

    public WordSet getWordSetById(long wordSetId) {
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
                statement2.setLong(1,wordSetId);
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
                    ResultSet resultSet2 = explanationStatement.executeQuery();
                    if (resultSet2.next()) {
                        word.getExplanation().add(resultSet2.getString("meaning"));
                    }
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

