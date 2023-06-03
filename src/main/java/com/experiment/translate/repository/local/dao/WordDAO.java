package com.experiment.translate.repository.local.dao;

import com.experiment.translate.repository.bean.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordDAO {
    private Connection connection;

    public WordDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertWord(Word word) {
        String sql = "INSERT INTO word (word_id, speak_url, basic_phonetic, uk_speech, us_speech) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, word.getWord_id());
            statement.setString(2, word.getSpeakUrl());
            statement.setString(3, word.getBasicPhonetic());
            statement.setString(4, word.getUkSpeech());
            statement.setString(5, word.getUsSpeech());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
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

