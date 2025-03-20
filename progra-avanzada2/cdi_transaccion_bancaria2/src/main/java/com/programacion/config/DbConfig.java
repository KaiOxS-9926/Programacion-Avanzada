package com.programacion.config;


import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
public class DbConfig {

    public Connection getConnection() {

        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://ep-sparkling-unit-a5qm8msy.us-east-2.aws.neon.tech/transferencias",
                    "transferencias_owner",
                    "vzDIVXoSx01s"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
