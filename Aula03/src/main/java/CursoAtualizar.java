import br.com.puc.dao.CursoDAO;
import br.com.puc.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.puc.model.Areas;
public class CursoAtualizar {
    private JPanel painelAdicionarCurso;
    private JTextField txtNomeCurso;
    private JButton adicionarButton;
    private JTextField textCodigo;
    private JButton buscarButton;

    public JPanel painelPrincipal;
    private JTextField textSigla;
    private JComboBox comboBox1;
    private JButton btnLimpar;

    public CursoAtualizar() {

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso curso = new Curso();
                CursoDAO cursoDAO = new CursoDAO();
                if (textCodigo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite o código do Curso", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean resposta = cursoDAO.searchByCodigo(Long.parseLong(textCodigo.getText().toString()));
                    if (resposta) {
                        curso = cursoDAO.findById(Long.parseLong(textCodigo.getText().toString()));
                        textSigla.setText(curso.getSigla());
                        txtNomeCurso.setText(curso.getNomecurso());
                        textSigla.setEditable(true);
                        textSigla.setEnabled(true);
                        txtNomeCurso.setEnabled(true);
                        txtNomeCurso.setEditable(true);
                        textCodigo.setEditable(false);
                        comboBox1.setEnabled(true);
                        adicionarButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "CODIGO NÃO EXISTE", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso curso = new Curso();
                CursoDAO cursoDAO = new CursoDAO();
                if (textCodigo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite o código do Curso", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else if (txtNomeCurso.getText().isEmpty()) {
                } else if(textSigla.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite a Sigla", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else if(comboBox1.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione uma Area", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else{
                    boolean resposta = cursoDAO.searchByCodigo(Long.parseLong(textCodigo.getText().toString()));
                    if (resposta) {
                        curso = cursoDAO.findById(Long.parseLong(textCodigo.getText().toString()));
                        if(curso.getSigla().toUpperCase().equals(textSigla.getText().toString().toUpperCase()))
                        {
                            curso.setSigla(textSigla.getText().toString());
                        } else{
                            boolean sigla = cursoDAO.searchSigla(textSigla.getText());
                            if(sigla){
                                JOptionPane.showMessageDialog(null, "SIGLA JÁ UTILIZADA EM OUTRO CURSO \nA SIGLA NÃO SERÁ ATUALIZADA", "ERRO", JOptionPane.ERROR_MESSAGE);
                            } else{
                                curso.setSigla(textSigla.getText().toUpperCase());
                            }
                        }
                        curso.setNomecurso(txtNomeCurso.getText().toString());
                        if(comboBox1.getSelectedItem().equals("EXATAS")){
                            curso.setArea(Areas.EX);
                        } else if(comboBox1.getSelectedItem().equals("HUMANAS")){
                            curso.setArea(Areas.HM);
                        } else if(comboBox1.getSelectedItem().equals("BIOLOGIA")){
                            curso.setArea(Areas.BL);
                        } else if(comboBox1.getSelectedItem().equals("ARTES")){
                            curso.setArea(Areas.ART);
                        } else if(comboBox1.getSelectedItem().equals("OUTROS")){
                            curso.setArea(Areas.OUTROS);
                        }
                        curso = cursoDAO.update(curso);
                        textCodigo.setText(curso.getCodigo().toString());
                        textSigla.setText(curso.getSigla());
                        txtNomeCurso.setText(curso.getNomecurso());
                        textSigla.setEditable(false);
                        txtNomeCurso.setEditable(false);
                        comboBox1.setEnabled(false);
                        adicionarButton.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Curso Atualizado", "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "CODIGO NÃO EXISTE", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNomeCurso.setText("");
                textCodigo.setText("");
                textSigla.setText("");
                comboBox1.setSelectedIndex(0);
                comboBox1.setEditable(false);
                comboBox1.setEnabled(false);
                txtNomeCurso.setEditable(false);
                txtNomeCurso.setEnabled(false);
                textSigla.setEditable(false);
                textSigla.setEnabled(false);
                textCodigo.setEditable(true);
                textCodigo.setEnabled(true);
                adicionarButton.setEnabled(false);
            }
        });
    }
}
