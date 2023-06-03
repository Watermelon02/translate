package com.experiment.translate.repository.local.database;

import com.experiment.translate.repository.bean.Explanation;
import com.experiment.translate.repository.local.dao.ExplanationDAO;
import com.experiment.translate.repository.local.dao.UserDao;
import com.experiment.translate.repository.local.dao.WordDAO;
import com.experiment.translate.repository.local.dao.WordSetDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TranslateDatabase {
    private static TranslateDatabase instance;
    private Connection connection;
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/translate";
    private static final String username = "root";
    private static final String password = "ai1wei2xi3";

    private WordDAO wordDAO;
    private ExplanationDAO explanationDAO;
    private WordSetDAO wordSetDAO;
    private UserDao userDao;

    private TranslateDatabase() {
        // 私有构造函数，避免从外部实例化该类
    }

    public static TranslateDatabase getInstance() {
        if (instance == null) {
            synchronized (TranslateDatabase.class) {
                if (instance == null) {
                    instance = new TranslateDatabase();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(jdbcUrl, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public WordDAO getWordDAO() {
        if (connection == null) getConnection();
        if (wordDAO == null) {
            wordDAO = new WordDAO(connection);
        }
        return wordDAO;
    }

    public ExplanationDAO getExplanationDAO() {
        if (connection == null) getConnection();
        if (explanationDAO == null) {
            explanationDAO = new ExplanationDAO(connection);
        }
        return explanationDAO;
    }

    public WordSetDAO getWordSetDAO() {
        if (connection == null) getConnection();
        if (wordSetDAO == null) {
            wordSetDAO = new WordSetDAO(connection);
        }
        return wordSetDAO;
    }

    public UserDao getUserDAO() {
        if (connection == null) getConnection();
        if (userDao == null) {
            userDao = new UserDao(connection);
        }
        return userDao;
    }
}

