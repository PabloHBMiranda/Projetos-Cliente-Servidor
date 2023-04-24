import javax.swing.*;

public class TESTE {
    public static void main(String[] args) {

        JFrame quadro = new JFrame("Inicio");
        quadro.setContentPane(new Inicio().painelPrincipal);
        quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quadro.pack();
        quadro.setLocationRelativeTo(null);
        quadro.setVisible(true);
    }
}
