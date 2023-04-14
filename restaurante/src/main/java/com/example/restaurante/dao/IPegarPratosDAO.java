package com.example.restaurante.dao;

import com.example.restaurante.model.PegarPratos;

import java.util.List;

public interface IPegarPratosDAO {
    List<PegarPratos> findByAll();
}
