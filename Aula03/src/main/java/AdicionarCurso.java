import br.com.puc.dao.AlunoDAO;
import br.com.puc.dao.CursoDAO;
import br.com.puc.model.Aluno;
import br.com.puc.model.Areas;
import br.com.puc.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdicionarCurso {


    private JButton adicionarButton;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    public JPanel painelAdicionarCurso;


    public AdicionarCurso() {
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso curso = new Curso();
                CursoDAO cursoDAO = new CursoDAO();
                if (textField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite o Nome para prosseguir!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else if (comboBox1.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione o Curso!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else if (textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite a Sigla", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean resposta = cursoDAO.searchSigla(textField2.getText().toUpperCase());
                    if (!resposta) {
                        curso.setNomecurso(textField1.getText());
                        curso.setSigla(textField2.getText().toUpperCase());
                        if(comboBox1.getSelectedItem().toString().equals("EXATAS")){
                            curso.setArea(Areas.EX);
                        } else if(comboBox1.getSelectedItem().toString().equals("HUMANAS")){
                            curso.setArea(Areas.HM);
                        } else if(comboBox1.getSelectedItem().toString().equals("BIOLOGIA")){
                            curso.setArea(Areas.BL);
                        } else if(comboBox1.getSelectedItem().toString().equals("ARTES")){
                            curso.setArea(Areas.BL);
                        } else {
                            curso.setArea(Areas.OUTROS);
                        }
                        Curso novoCurso = cursoDAO.create(curso);
                        comboBox1.setSelectedIndex(0);
                        textField1.setText("");
                        textField2.setText("");
                        JOptionPane.showMessageDialog(null, "CÓDIGO: " + novoCurso.getCodigo()
                                + "\nNOME DO CURSO: " + novoCurso.getNomecurso()
                                + "\nSIGLA CURSO: " + novoCurso.getSigla()
                                + "\nAREA CURSO: " + novoCurso.getArea().nomeArea()
                                + "\nAdicionado com sucesso!!!");
                    } else{
                        JOptionPane.showMessageDialog(null, "SIGLA JÁ CADASTRADA ESCOLHA OUTRA", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
