import br.com.puc.dao.AlunoDAO;
import br.com.puc.model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarMatricula {
    private JTextField txtMatricula;
    private JButton deletarButton;
    public JPanel painelDeletar;

    DeletarMatricula(){
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtMatricula.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Digite uma matricula", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else{
                    AlunoDAO alunoDAO = new AlunoDAO();
                    boolean resposta = alunoDAO.searchMatricula(Long.parseLong(txtMatricula.getText().toString()));
                    if(resposta){
                        Aluno aluno = new Aluno();
                        aluno = alunoDAO.findById(Long.parseLong(txtMatricula.getText()));
                        alunoDAO.delete(Long.parseLong(txtMatricula.getText().toString()));
                        JOptionPane.showMessageDialog(null, "MATRICULA: " + aluno.getMatricula() + "\nNOME: " + aluno.getNome() + "\nAluno Deletado!", "OK", JOptionPane.INFORMATION_MESSAGE);
                        txtMatricula.setText("");
                    } else{
                        JOptionPane.showMessageDialog(null, "MATRICULA NÃO EXISTE", "OK", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
