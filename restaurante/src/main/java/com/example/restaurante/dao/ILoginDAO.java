package com.example.restaurante.dao;

import com.example.restaurante.model.Pessoa;

public interface ILoginDAO  {

    Pessoa create(Pessoa pessoa);

    boolean searchByEmail(String email);

    Pessoa findByEmail(String email);
}
