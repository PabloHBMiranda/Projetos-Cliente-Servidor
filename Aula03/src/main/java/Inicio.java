import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {
    private JButton alunoButton;
    private JButton cursosButton;
    public JPanel painelPrincipal;

    public Inicio() {
        alunoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame quadro = new JFrame("Alunos");
                quadro.setContentPane(new Alunos().painelAlunos);
                quadro.pack();
                quadro.setLocationRelativeTo(null);
                quadro.setVisible(true);
            }
        });


        cursosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame quadro = new JFrame("Cursos");
                quadro.setContentPane(new Cursos().painelCuros);
                quadro.pack();
                quadro.setLocationRelativeTo(null);
                quadro.setVisible(true);
            }
        });

    }
}
