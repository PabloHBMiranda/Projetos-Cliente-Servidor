package com.example.pratos.dao;

import com.example.pratos.model.Pratos;

import java.sql.*;

public class PratosDAO implements IPratosDAO{
    @Override
    public Pratos create(Pratos pratos) {
        try(Connection connection = com.example.restaurante.config.ConnectionFactory.getConnection()){
            String query = "INSERT INTO pratos"+
                    "(nomeprato, valor, descricao, categoria)" +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,pratos.getNomeprato());
            statement.setFloat(2,pratos.getValor());
            statement.setString(3,pratos.getDescricao());
            statement.setString(4, pratos.getCategoria());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            Long codigoGerado = resultSet.getLong(1);
            pratos.setId(codigoGerado);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return pratos;
    }

}
