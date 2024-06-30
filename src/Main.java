import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               JFrame principalJFrame = new VentanaPrincipal();
               principalJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               principalJFrame.pack();
               principalJFrame.setVisible(true);
           }
        });
    }
}