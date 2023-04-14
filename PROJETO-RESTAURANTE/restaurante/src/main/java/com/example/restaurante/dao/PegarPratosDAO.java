package com.example.restaurante.dao;

import com.example.restaurante.config.ConnectionFactory;
import com.example.restaurante.model.PegarPratos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PegarPratosDAO implements IPegarPratosDAO{
    @Override
    public List<PegarPratos> findByAll() {
        String query = "SELECT * FROM pratos";
        List<PegarPratos> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PegarPratos pegarPratos = new PegarPratos();
                pegarPratos.setId(rs.getInt("id"));
                pegarPratos.setValor(rs.getFloat("valor"));
                pegarPratos.setCategoria(rs.getString("categoria"));
                pegarPratos.setDescricao(rs.getString("descricao"));
                pegarPratos.setNomeprato(rs.getString("nomeprato"));
                lista.add(pegarPratos);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
