import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaModalidad extends JFrame {
    private JPanel panelModalidad;
    private JLabel fondoLabel;

    public VentanaModalidad() {
        super("Sudoku por Agustín Puente");

        setContentPane(panelModalidad);
        setSize(1080, 720);
        setResizable(false);

        //Label Titulo

        JLabel tituloLabel = new JLabel("Selector de modalidad de Sudoku");
        tituloLabel.setBounds(340, 260, 390, 30);
        Font fontTitulo = new Font("Arial", Font.BOLD, 24);
        tituloLabel.setFont(fontTitulo);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        tituloLabel.setBorder(border);
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(Color.white);

        fondoLabel.add(tituloLabel);

        //Label modo juego

        JLabel modo1Label = new JLabel("Jugar");
        modo1Label.setBounds(300, 370, 70, 30);
        modo1Label.setFont(fontTitulo);
        modo1Label.setBorder(border);
        modo1Label.setOpaque(true);
        modo1Label.setBackground(Color.white);

        fondoLabel.add(modo1Label);

        //Label modo resolver

        JLabel modo2Label = new JLabel("Resolvedor de Sudokus");
        modo2Label.setBounds(600, 370, 280, 30);
        modo2Label.setFont(fontTitulo);
        modo2Label.setBorder(border);
        modo2Label.setOpaque(true);
        modo2Label.setBackground(Color.white);

        fondoLabel.add(modo2Label);

        //JButton modo jugar

        JButton buttonSiguiente1 = new JButton("Siguiente");
        buttonSiguiente1.setBounds(275, 420, 120, 30); // Posicionar el botón

        fondoLabel.add(buttonSiguiente1);

        buttonSiguiente1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //Crea la ventana de Jugar mode
               JFrame selectdifVentana = new VentanaSelectorDificultad();
               selectdifVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               selectdifVentana.pack();
               selectdifVentana.setVisible(true);

               //Cierra ventana actual
               JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonSiguiente1);
               frame.dispose();
           }
        });

        //JButton modo resolver

        JButton buttonSiguiente2 = new JButton("Siguiente");
        buttonSiguiente2.setBounds(680, 420, 120, 30);

        fondoLabel.add(buttonSiguiente2);

        buttonSiguiente2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //Crea la ventana de Resolver mode
               JFrame resolverJFrame = new VentanaResolver();
               resolverJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               resolverJFrame.pack();
               resolverJFrame.setVisible(true);

               //Cierra ventana actual
               JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonSiguiente1);
               frame.dispose();
           }
        });
    }
}
