package org.example.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {

        Client client =new Client();
        client.setClient_id(resultSet.getLong("client_id"));
        client.setCurrent_balance(resultSet.getBigDecimal("current_balance"));

        return client;
    }

}
