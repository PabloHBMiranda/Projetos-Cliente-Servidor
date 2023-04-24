package br.com.puc.dao;

import br.com.puc.model.Aluno;
import br.com.puc.model.Curso;

import java.util.List;
import java.util.Optional;

public interface IAlunoDAO {
    Aluno create(Aluno aluno);
    Aluno update(Aluno aluno);
    void delete (Long matricula);
    List<Aluno> findAll();
    Aluno findById(long matricula);

    List<Aluno> findByCurso(String curso);

    boolean searchMatricula(long matricula);
}
