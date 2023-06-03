package com.experiment.translate.repository.local.dao;

import com.experiment.translate.repository.bean.Explanation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExplanationDAO {
    private Connection connection;

    public ExplanationDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertExplanation(Explanation explanation) {
        String sql = "INSERT INTO explanation (id, meaning) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, explanation.getId());
            statement.setString(2, explanation.getMeaning());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    public Explanation getExplanationById(long id) {
        String sql = "SELECT * FROM explanation WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Explanation explanation = new Explanation();
                explanation.setId(resultSet.getLong("id"));
                explanation.setMeaning(resultSet.getString("meaning"));
                return explanation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return null;
    }

    public List<Explanation> getExplanationsByWordId(String wordId) {
        String sql = "SELECT * FROM explanation WHERE word_id = ?";
        List<Explanation> explanations = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, wordId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Explanation explanation = new Explanation();
                explanation.setId(resultSet.getLong("id"));
                explanation.setMeaning(resultSet.getString("meaning"));
                explanations.add(explanation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return explanations;
    }
}

