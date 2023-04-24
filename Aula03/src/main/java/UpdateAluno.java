import br.com.puc.dao.AlunoDAO;
import br.com.puc.dao.CursoDAO;
import br.com.puc.model.Aluno;
import br.com.puc.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UpdateAluno {
    private JTextField txtNome;
    private JCheckBox maiorDeIdadeCheckBox;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JButton adicionarButton;
    private JComboBox comboBox1;
    private JTextField txtMatricula;
    private JButton buscarButton;
    public JPanel painelPrincipal;
    private JButton limpar;
    public JPanel painelUpdateAluno;

    public UpdateAluno(){

        Curso curso = new Curso();
        CursoDAO cursoDAO = new CursoDAO();
        List<Curso> listaAllCursos = cursoDAO.findAll();

        ArrayList<String> listCursos = new ArrayList<String>();
        for (Curso c : listaAllCursos) {
            listCursos.add(c.getNomecurso());
        }

        listCursos.add(0, "<Selecione>");
        comboBox1.setSelectedIndex(0);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        model.addAll(listCursos);
        comboBox1.setModel(model);
        
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aluno aluno = new Aluno();
                AlunoDAO alunoDAO = new AlunoDAO();
                if(txtMatricula.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Digite a Matricula", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else{
                    boolean resposta = alunoDAO.searchMatricula(Long.parseLong(txtMatricula.getText().toString()));
                    if(resposta){
                        aluno = alunoDAO.findById(Long.parseLong(txtMatricula.getText()));
                        txtNome.setText(aluno.getNome());
                        if(aluno.isMaioridade()){
                            maiorDeIdadeCheckBox.setSelected(aluno.isMaioridade());
                        }
                        if(aluno.getSexo().equals("Masculino")){
                            masculinoRadioButton.setSelected(true);
                        } else{
                            femininoRadioButton.setSelected(true);
                        }
                        comboBox1.setEnabled(true);
                        txtNome.setEnabled(true);
                        txtNome.setEditable(true);
                        txtMatricula.setEditable(false);
                        masculinoRadioButton.setEnabled(true);
                        femininoRadioButton.setEnabled(true);
                        maiorDeIdadeCheckBox.setEnabled(true);
                        adicionarButton.setEnabled(true);
                        comboBox1.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "MATRICULA NÃO EXISTE", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNome.setEditable(false);
                txtNome.setEnabled(false);
                masculinoRadioButton.setSelected(false);
                masculinoRadioButton.setEnabled(false);
                femininoRadioButton.setEnabled(false);
                femininoRadioButton.setSelected(false);
                comboBox1.setSelectedIndex(0);
                comboBox1.setEditable(false);
                comboBox1.setEnabled(false);
                maiorDeIdadeCheckBox.setSelected(false);
                maiorDeIdadeCheckBox.setEnabled(false);
                adicionarButton.setEnabled(false);
                txtMatricula.setEditable(true);
                txtMatricula.setEnabled(true);
                txtNome.setText("");
                txtMatricula.setText("");
            }
        });
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtMatricula.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Digite a Matricula", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else if(txtNome.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Digite o Nome", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else if(comboBox1.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null, "SELECIONE UM CURSO", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else if(!masculinoRadioButton.isSelected() && !femininoRadioButton.isSelected()){
                    JOptionPane.showMessageDialog(null, "SELECIONE UM SEXO", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else{
                    Aluno aluno = new Aluno();
                    AlunoDAO alunoDAO = new AlunoDAO();
                    aluno = alunoDAO.findById(Long.parseLong(txtMatricula.getText()));
                    if(masculinoRadioButton.isSelected()){
                        aluno.setSexo("Masculino");
                    } else{
                        aluno.setSexo("Feminino");
                    }
                    if(maiorDeIdadeCheckBox.isSelected()){
                        aluno.setMaioridade(true);
                    } else {
                        aluno.setMaioridade(false);
                    }
                    String nomeCurso = cursoDAO.findBySiglaCurso(comboBox1.getSelectedItem().toString());
                    aluno.setCurso(nomeCurso);
                    aluno.setNome(txtNome.getText());
                    aluno.setMatricula(Long.parseLong(txtMatricula.getText().toString()));
                    aluno = alunoDAO.update(aluno);
                    txtNome.setEditable(false);
                    maiorDeIdadeCheckBox.setEnabled(false);
                    comboBox1.setEditable(false);
                    comboBox1.setEnabled(false);
                    masculinoRadioButton.setEnabled(false);
                    femininoRadioButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Aluno Atualizado", "OK", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
