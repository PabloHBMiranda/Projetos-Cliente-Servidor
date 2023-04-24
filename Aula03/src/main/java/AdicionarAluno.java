

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import br.com.puc.dao.AlunoDAO;
import br.com.puc.dao.CursoDAO;
import br.com.puc.model.Aluno;
import br.com.puc.model.Curso;

public class AdicionarAluno {
    public JPanel painelAdicionarAluno;
    private JTextField txtNome;
    private JRadioButton masculinoRadioButton;
    private JCheckBox maiorDeIdadeCheckBox;
    private JRadioButton femininoRadioButton;
    private JButton adicionarButton;
    private JComboBox comboBox1;


    public AdicionarAluno() {
        CursoDAO cursoDAO = new CursoDAO();
        List<Curso> cursos = cursoDAO.findAll();
        ArrayList<String> listCursos = new ArrayList<String>();
        for (Curso c : cursos) {
            listCursos.add(c.getNomecurso());
        }
        listCursos.add(0, "< Selecione >");
        comboBox1.setSelectedIndex(0);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        model.addAll(listCursos);
        comboBox1.setModel(model);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoDAO alunoDAO = new AlunoDAO();
                String siglaCurso = "";
                Aluno aluno = new Aluno();
                CursoDAO cursoDAO = new CursoDAO();
                if (txtNome.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Digite o Nome para prosseguir!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
                else if (!masculinoRadioButton.isSelected() && !femininoRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Selecione o Sexo!!!", "ERRO", JOptionPane.ERROR_MESSAGE);

                } else if (comboBox1.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Selecione o Curso!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else {
                    aluno.setNome(txtNome.getText());
                    aluno.setMaioridade(maiorDeIdadeCheckBox.isSelected());
                    String searchCurso = cursoDAO.findBySiglaCurso(comboBox1.getSelectedItem().toString());
                    aluno.setCurso(searchCurso);
                    if (masculinoRadioButton.isSelected()) aluno.setSexo("Masculino");
                    else if (femininoRadioButton.isSelected()) {
                        aluno.setSexo("Feminino");
                    }
                    String maioridade = "";
                    if(maiorDeIdadeCheckBox.isSelected()){
                        maioridade = "SIM";
                    } else{
                        maioridade = "NÃO";
                    }
                    Aluno novoAluno = alunoDAO.create(aluno);
                    JOptionPane.showMessageDialog(null,
                            "\nNOME ALUNO: " + novoAluno.getNome()
                            + "\nMATRICULA ALUNO: " + novoAluno.getMatricula()
                            + "\nSEXO ALUNO: " + novoAluno.getSexo()
                            + "\nCURSO ALUNO: " + comboBox1.getSelectedItem().toString()
                            + "\nMAIORIDADE: " + maioridade
                            + "\nAdicionado com sucesso!!!");
                    masculinoRadioButton.setSelected(false);
                    maiorDeIdadeCheckBox.setSelected(false);
                    femininoRadioButton.setSelected(false);
                    txtNome.setText("");
                    comboBox1.setSelectedIndex(0);
                }
            }
        });
    }


}
