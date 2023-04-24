import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Alunos {
    private JButton adicionarAlunoButton;
    private JButton deletarAlunoButton;
    private JButton atualizarAlunoButton;
    public JPanel painelAlunos;

    public Alunos() {
    adicionarAlunoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame quadro = new JFrame("AdicionarAlunos");
            quadro.setContentPane(new AdicionarAluno().painelAdicionarAluno);
            quadro.pack();
            quadro.setLocationRelativeTo(null);
            quadro.setVisible(true);


        }
    });
    deletarAlunoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame quadro = new JFrame("DeletarMatricula");
            quadro.setContentPane(new DeletarMatricula().painelDeletar);
            quadro.pack();
            quadro.setLocationRelativeTo(null);
            quadro.setVisible(true);
        }


    });
    atualizarAlunoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame quadro = new JFrame("UpdateAluno");
            quadro.setContentPane(new UpdateAluno().painelPrincipal);
            quadro.pack();
            quadro.setLocationRelativeTo(null);
            quadro.setVisible(true);

        }
    });
}
}
