package br.com.puc.model;

public class Curso {

    public Curso() {
    }

    public Curso(Long codigo, String nomecurso, String sigla, Areas area) {
        this.codigo = codigo;
        this.nomecurso = nomecurso;
        this.sigla = sigla;
        this.area = area;
    }

    private Long codigo;

    private String nomecurso;

    String sigla;

    Areas area;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }
}
