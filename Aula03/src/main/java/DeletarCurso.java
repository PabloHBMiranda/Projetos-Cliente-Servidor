import javax.swing.*;

import br.com.puc.dao.CursoDAO;
import br.com.puc.model.Curso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;


public class DeletarCurso {
    private JTextField textField1;
    private JButton deletarButton;
    public JPanel painelPrincipal;

    public DeletarCurso() {
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Digite a Matricula!!", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else {
                    CursoDAO cursoDAO = new CursoDAO();
                    boolean procuraCurso = cursoDAO.searchByCodigo(Long.parseLong(textField1.getText().toString()));
                    if(procuraCurso){
                        cursoDAO.delete(Long.parseLong(textField1.getText().toString()));
                        JOptionPane.showMessageDialog(null, "Curso Deletado", "OK", JOptionPane.OK_OPTION);
                    } else{
                        JOptionPane.showMessageDialog(null, "Curso NÃO EXISTE", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
