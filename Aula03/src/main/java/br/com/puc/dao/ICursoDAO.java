package br.com.puc.dao;

import br.com.puc.model.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoDAO {
    Curso create(Curso curso);
    Curso update(Curso curso);
    void delete (Long codigo);
    List<Curso> findAll();
    Curso findById(long codigo);
    List<Curso> findByArea(String area);
    Optional<Curso> findBySigla(String sigla);
    List<Curso> findAllSigla();

    List<Curso> findAllCurso();
    String findBySiglaCurso(String nomeCurso);

    boolean searchByCodigo(long codigo);

    boolean serchByArea();

    boolean searchSigla(String sigla);
}
