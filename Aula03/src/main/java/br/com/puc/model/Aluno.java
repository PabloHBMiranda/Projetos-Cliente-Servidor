package br.com.puc.model;

public class Aluno {

    public Aluno(){

    }

    public Aluno(Long matricula, String nome, boolean maioridade, String curso, String sexo) {
        this.matricula = matricula;
        this.nome = nome;
        this.maioridade = maioridade;
        this.sigla = sigla;
        this.sexo = sexo;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isMaioridade() {
        return maioridade;
    }

    public void setMaioridade(boolean maioridade) {
        this.maioridade = maioridade;
    }

    public String getCurso() {
        return sigla;
    }

    public void setCurso(String sigla) {
        this.sigla = sigla;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    private Long matricula;
    private String nome;
    boolean maioridade;
    String sigla;
    String sexo;
}
