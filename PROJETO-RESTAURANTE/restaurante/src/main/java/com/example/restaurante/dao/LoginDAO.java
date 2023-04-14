package com.example.restaurante.dao;

import com.example.restaurante.config.ConnectionFactory;
import com.example.restaurante.model.Pessoa;

import java.sql.*;

public class LoginDAO implements ILoginDAO {
    @Override
    public Pessoa create(Pessoa pessoa) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String query = "INSERT INTO pessoa"+
                    "(nome, email, senha)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,pessoa.getNome());
            statement.setString(2,pessoa.getEmail());
            statement.setString(3,pessoa.getSenha());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return pessoa;
    }

    @Override
    public boolean searchByEmail(String email) {
        String query = "SELECT * FROM pessoa WHERE email = ?";
        boolean resposta = false;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            resposta = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resposta;
    }

    @Override
    public Pessoa findByEmail(String email) {
        String query = "SELECT * FROM pessoa WHERE email = ?";
        Pessoa pessoa;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();

            pessoa = new Pessoa(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pessoa;
    }
}
