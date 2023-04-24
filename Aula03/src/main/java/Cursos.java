
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cursos {
    private JButton deletarCursoButton;
    private JButton adicionarCursoButton;
    private JButton atualziarCursoButton;
    public JPanel painelCuros;

    public Cursos() {
    adicionarCursoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame quadro = new JFrame("AdicionarCurso");
            quadro.setContentPane(new AdicionarCurso().painelAdicionarCurso);
            quadro.pack();
            quadro.setLocationRelativeTo(null);
            quadro.setVisible(true);

        }
    });
    atualziarCursoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame quadro = new JFrame("CursoAtualizar");
            quadro.setContentPane(new CursoAtualizar().painelPrincipal);
            quadro.pack();
            quadro.setLocationRelativeTo(null);
            quadro.setVisible(true);

        }
    });
    deletarCursoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame quadro = new JFrame("DeletarCurso");
            quadro.setContentPane(new DeletarCurso().painelPrincipal);
            quadro.pack();
            quadro.setLocationRelativeTo(null);
            quadro.setVisible(true);

        }
    });
}

}
