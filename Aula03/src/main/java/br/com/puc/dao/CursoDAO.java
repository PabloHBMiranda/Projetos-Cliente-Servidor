package br.com.puc.dao;

import br.com.puc.config.ConnectionFactory;
import br.com.puc.model.Areas;
import br.com.puc.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO implements ICursoDAO {

    @Override
    public Curso create(Curso curso) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO Curso" +
                    "(nomecurso, sigla, area)" +
                    "VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, curso.getNomecurso());
            statement.setString(2, curso.getSigla());
            statement.setString(3, curso.getArea().toString());
            statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                Long codigoGerado = resultSet.getLong(1);
                curso.setCodigo(codigoGerado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return curso;
    }

    @Override
    public Curso update(Curso curso) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE Curso SET " +
                    "nomecurso = ?, sigla = ?, area = ? " +
                    "WHERE codigo = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, curso.getNomecurso());
            statement.setString(2, curso.getSigla());
            statement.setString(3, curso.getArea().toString());
            statement.setLong(4, curso.getCodigo());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return curso;
    }

    @Override
    public void delete(Long codigo) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM Curso WHERE codigo = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> findAll() {
        String query = "SELECT * FROM curso";
        List<Curso> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCodigo(rs.getLong("codigo"));
                curso.setNomecurso(rs.getString("nomecurso"));
                curso.setSigla(rs.getString("sigla"));
                curso.setArea(Areas.valueOf(rs.getString("area")));
                lista.add(curso);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Curso findById(long codigo) {
        String query = "SELECT * FROM curso WHERE codigo = ?";
        Curso curso;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, codigo);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();

            curso = new Curso(
                    rs.getLong("codigo"),
                    rs.getString("nomecurso"),
                    rs.getString("sigla"),
                    Areas.valueOf(rs.getString("area"))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return curso;

    }

    @Override
    public List<Curso> findByArea(String area) {
        String query = "SELECT * FROM curso WHERE area = ?";
        List<Curso> listaArea = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, area);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCodigo(rs.getLong("codigo"));
                curso.setNomecurso(rs.getString("nomecurso"));
                curso.setSigla(rs.getString("sigla"));
                curso.setArea(Areas.valueOf(rs.getString("area")));
                listaArea.add(curso);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaArea;
    }

    @Override
    public Optional<Curso> findBySigla(String sigla) {
        String query = "SELECT * FROM curso WHERE sigla = ?";
        Curso curso;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sigla);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            curso = new Curso(
                    rs.getLong("codigo"),
                    rs.getString("nomecurso"),
                    rs.getString("sigla"),
                    Areas.valueOf(rs.getString("area"))
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(curso);
    }

    @Override
    public List<Curso> findAllSigla() {
        String query = "SELECT sigla FROM curso";
        List<Curso> lista = new ArrayList<>();
        String siglaCurso;
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Curso curso = new Curso();
                curso.setSigla(rs.getString("sigla"));
                lista.add(curso);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public List<Curso> findAllCurso() {
        String query = "SELECT nomecurso FROM curso";
        List<Curso> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Curso curso = new Curso();
                curso.setNomecurso(rs.getString("nomecurso"));
                lista.add(curso);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public String findBySiglaCurso(String nomeCurso) {
        String query = "SELECT * FROM curso WHERE nomecurso = ?";
        String searchString;
        Curso curso;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomeCurso);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();
            curso = new Curso(
                    rs.getLong("codigo"),
                    rs.getString("nomecurso"),
                    rs.getString("sigla"),
                    Areas.valueOf(rs.getString("area"))
            );


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return curso.getSigla();
    }

    @Override
    public boolean searchByCodigo(long codigo) {
        String query = "SELECT * FROM curso WHERE codigo = ?";
        boolean resposta = false;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, codigo);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            resposta = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resposta;
    }

    @Override
    public boolean serchByArea() {
        return false;
    }

    @Override
    public boolean searchSigla(String sigla) {
        String query = "SELECT * FROM curso WHERE sigla = ?";
        boolean resposta = false;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sigla);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            resposta = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resposta;
    }

}
