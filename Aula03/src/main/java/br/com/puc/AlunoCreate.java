package br.com.puc;

import java.util.List;
import java.util.Scanner;

import br.com.puc.dao.AlunoDAO;
import br.com.puc.dao.CursoDAO;
import br.com.puc.model.Aluno;
import br.com.puc.model.Curso;

public class AlunoCreate {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno();
        CursoDAO cursoDAO = new CursoDAO();
        List<Curso> listaAllSiglas = cursoDAO.findAllSigla();
        String x;
        int x1, teste = 0;
        Scanner sc = new Scanner(System.in);

        //nome
        System.out.println("Digite o nome do aluno:");
        x = sc.nextLine();
        aluno.setNome(x);


        //sigla
        System.out.println("LISTA DE SIGLAS DE CURSOS");
        for (Curso item : listaAllSiglas) {
            System.out.println(item.getSigla());
        }
        System.out.println("Digite a sigla do curso:");
        x = sc.nextLine();
        while (teste != 1) {
            for (Curso item : listaAllSiglas) {
                if (item.getSigla().equals(x)) {
                    aluno.setCurso(x);
                    teste = 1;
                    break;
                }
            }
            if (teste == 1) System.out.println("SIGLA DO CURSO CADASTRADA!");
            else {
                System.out.println("SIGLA NÃO EXISTE!");
                System.out.println("Digite a sigla do curso:");
                x = sc.nextLine();
            }
        }


        //sexo
        System.out.println("Digite o sexo");
        x = sc.nextLine();
        aluno.setSexo(x);

        //maioridade
        System.out.println("Digite 1 pra MAIS DE 18 e 0 pra MENOR DE 18:");
        x1 = sc.nextInt();
        if (x1 == 1) aluno.setMaioridade(true);
        else aluno.setMaioridade(false);

        Aluno novoAluno = alunoDAO.create(aluno);
        System.out.println("Matricula do novo aluno: " + novoAluno.getMatricula());
    }
}
