package com.experiment.translate.repository.local.dao;

import com.experiment.translate.repository.bean.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WordDAO {
    private Connection connection;

    public WordDAO(Connection connection) {
        this.connection = connection;
    }

    public Word getWordById(String id) {
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

    public boolean insertWord(Word word) {
        try {
            // 插入Word表
            String insertWordQuery = "INSERT INTO word (word_id, speak_url, basic_phonetic, uk_speech, us_speech) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement wordStatement = connection.prepareStatement(insertWordQuery);
            wordStatement.setString(1, word.getWord_id());
            wordStatement.setString(2, word.getSpeakUrl());
            wordStatement.setString(3, word.getBasicPhonetic());
            wordStatement.setString(4, word.getUkSpeech());
            wordStatement.setString(5, word.getUsSpeech());
            wordStatement.executeUpdate();

            // 插入Explaination表
            String insertExplainationQuery = "INSERT INTO explanation (word_id, meaning) VALUES (?, ?)";
            PreparedStatement explainationStatement = connection.prepareStatement(insertExplainationQuery);
            List<String> explanations = word.getExplanation();
            for (String explanation : explanations) {
                explainationStatement.setString(1, word.getWord_id());
                explainationStatement.setString(2, explanation);
                explainationStatement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

