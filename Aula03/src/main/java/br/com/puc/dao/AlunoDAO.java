package br.com.puc.dao;

import br.com.puc.config.ConnectionFactory;
import br.com.puc.model.Aluno;
import br.com.puc.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO implements IAlunoDAO {

    @Override
    public Aluno create(Aluno aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO Alunos" +
                    "(nome, maioridade, curso, sexo)" +
                    "VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,aluno.getNome());
            statement.setBoolean(2,aluno.isMaioridade());
            statement.setString(3,aluno.getCurso());
            statement.setString(4,aluno.getSexo());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            Long matriculaGerada = resultSet.getLong(1);
            aluno.setMatricula(matriculaGerada);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aluno;
    }

    @Override
    public Aluno update(Aluno aluno) {
        try (Connection connection = ConnectionFactory.getConnection()){
            String query = "UPDATE alunos SET " +
                    "nome = ?, maioridade = ?, curso = ?, sexo = ? " +
                    "WHERE matricula = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, aluno.getNome());
            statement.setBoolean(2, aluno.isMaioridade());
            statement.setString(3, aluno.getCurso());
            statement.setString(4, aluno.getSexo());
            statement.setLong(5, aluno.getMatricula());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return aluno;
    }

    @Override
    public void delete(Long matricula) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String query = "DELETE FROM Alunos WHERE matricula = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, matricula);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aluno> findAll() {
        String query = "SELECT * FROM alunos";
        List<Aluno> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getLong("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMaioridade(rs.getBoolean("maioridade"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setSexo(rs.getString("sexo"));
                lista.add(aluno);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Aluno findById(long matricula) {
        String query = "SELECT * FROM alunos WHERE matricula = ?";
        Aluno aluno;

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,matricula);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();

            aluno = new Aluno(
                    rs.getLong("matricula"),
                    rs.getString("nome"),
                    rs.getBoolean("maioridade"),
                    rs.getString("curso"),
                    rs.getString("sexo")
            );
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return aluno;
    }

    @Override
    public List<Aluno> findByCurso(String curso) {
        return null;
    }

    @Override
    public boolean searchMatricula(long matricula) {
        String query = "SELECT * FROM alunos WHERE matricula = ?";
        boolean resposta = false;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, matricula);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            resposta = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resposta;
    }

}
