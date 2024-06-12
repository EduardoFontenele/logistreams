package br.com.logistreams.application.infrastructure.persistence.jdbc;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {
    private final String url = "";
    private final String user = "";
    private final String password = "";

    @Getter
    private final Connection connection = DriverManager.getConnection(url, user, password);

    public ConnectionFactory() throws SQLException {
    }

}
