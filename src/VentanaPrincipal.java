import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JPanel panelPrincipal;
    private JLabel fondoLabel;

    public VentanaPrincipal() {
        super("Sudoku por Agustín Puente");

        setContentPane(panelPrincipal);
        setSize(1080, 720);
        setResizable(false);

        // Añadir un botón sobre la imagen de fondo
        JButton buttonSiguiente = new JButton("Empezar");
        buttonSiguiente.setBounds(480, 345, 120, 30); // Posicionar el botón

        fondoLabel.add(buttonSiguiente);

        buttonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Crea la ventana de Modalidad
                JFrame modalidadJFrame = new VentanaModalidad();
                modalidadJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                modalidadJFrame.pack();
                modalidadJFrame.setVisible(true);

                //Cierra ventana principal
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonSiguiente);
                frame.dispose();
            }
        });

        //Label titulo

        JLabel tituloLabel = new JLabel("SUDOKU");
        tituloLabel.setBounds(485, 300, 110, 30);
        Font fontTitulo = new Font("Arial", Font.BOLD, 24);
        tituloLabel.setFont(fontTitulo);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        tituloLabel.setBorder(border);
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(Color.white);

        fondoLabel.add(tituloLabel);

        //Label creditos

        JLabel creditosLabel = new JLabel("Juego creado por Agustín Puente");
        Font fontCreditos = new Font("Arial", Font.BOLD, 14);
        creditosLabel.setFont(fontCreditos);
        creditosLabel.setBounds(420,600,240,30);
        creditosLabel.setOpaque(true);
        creditosLabel.setBackground(Color.white);
        creditosLabel.setBorder(border);

        fondoLabel.add(creditosLabel);

    }
}
