package br.com.puc;

import br.com.puc.dao.AlunoDAO;
import br.com.puc.dao.CursoDAO;
import br.com.puc.model.Aluno;
import br.com.puc.model.Curso;

import java.util.List;

public class AlunoListagens {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno();
        CursoDAO cursoDAO = new CursoDAO();
        List<Aluno> listaAlunos = alunoDAO.findAll();
        List<Curso> listaAllCursos = cursoDAO.findAll();
        System.out.println("TODOS OS ALUNOS");
        for (Aluno a : listaAlunos) {
            System.out.println("Matricula: " + a.getMatricula());
            System.out.print("Nome: " + a.getNome());
            System.out.println(a.isMaioridade() ? " - Adulto" : " - Adolescente");
            System.out.println("Curso: " + a.getCurso());
            System.out.println("Sexo: " + a.getSexo());
            System.out.println("====================================");
        }

        System.out.println("\n\nTODOS OS CURSOS");
        for (Curso a : listaAllCursos) {
            System.out.println("Codígo: " + a.getCodigo());
            System.out.println("Nome do Curso: " + a.getNomecurso());
            System.out.println("Sigla: " + a.getSigla());
            System.out.println("Área: " + a.getArea().nomeArea());
            System.out.println("====================================");
        }

    }
}
