package br.com.puc;

import br.com.puc.dao.AlunoDAO;

import java.util.Scanner;

public class AlunoDelete {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long leitor;
        AlunoDAO alunoDAO = new AlunoDAO();
        System.out.println("DIGITE A MATRICULA DO ALUNO A SER DELETADO");
        leitor = sc.nextInt();
        alunoDAO.delete(leitor);
        System.out.println("Aluno Deletado!!");
    }
}
