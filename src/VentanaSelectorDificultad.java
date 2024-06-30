import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSelectorDificultad extends JFrame {
    private JPanel panelSelectorDificultad;
    private JLabel fondoLabel;

    public VentanaSelectorDificultad() {
        super("Sudoku por Agustín Puente");

        setContentPane(panelSelectorDificultad);
        setSize(1080, 720);
        setResizable(false);

        //Label Titulo

        JLabel tituloLabel = new JLabel("Selector de dificultad de Sudoku");
        tituloLabel.setBounds(355, 260, 375, 30);
        Font fontTitulo = new Font("Arial", Font.BOLD, 24);
        tituloLabel.setFont(fontTitulo);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        tituloLabel.setBorder(border);
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(Color.white);

        fondoLabel.add(tituloLabel);

        //JButton modo facil

        JButton buttonFacil = new JButton("Facil");
        buttonFacil.setBounds(220, 420, 120, 30);

        fondoLabel.add(buttonFacil);

        buttonFacil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Crea la ventana de juego facil
                JFrame sudokuFacilJFrame = new VentanaSudokuFacil();
                sudokuFacilJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                sudokuFacilJFrame.pack();
                sudokuFacilJFrame.setVisible(true);

                //Cierra ventana actual
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonFacil);
                frame.dispose();
            }
        });

        //JButton modo medio

        JButton buttonMedio = new JButton("Medio");
        buttonMedio.setBounds(490, 420, 120, 30);

        fondoLabel.add(buttonMedio);

        buttonMedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Crea la ventana de juego medio
                JFrame sudokuMedioJFrame = new VentanaSudokuMedio();
                sudokuMedioJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                sudokuMedioJFrame.pack();
                sudokuMedioJFrame.setVisible(true);

                //Cierra ventana actual
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonMedio);
                frame.dispose();
            }
        });

        //JButton modo dificil

        JButton buttonDificil = new JButton("Dificil");
        buttonDificil.setBounds(760, 420, 120, 30);

        fondoLabel.add(buttonDificil);

        buttonDificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Crea la ventana de juego dificil
                JFrame sudokuDificilJFrame = new VentanaSudokuDificil();
                sudokuDificilJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                sudokuDificilJFrame.pack();
                sudokuDificilJFrame.setVisible(true);

                //Cierra ventana actual
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonDificil);
                frame.dispose();
            }
        });

        //JButton volver

        JButton buttonSalir = new JButton("Salir");
        buttonSalir.setBounds(490, 600, 120, 30);

        fondoLabel.add(buttonSalir);

        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Crea la ventana de Modalidad
                JFrame modalidadJFrame = new VentanaModalidad();
                modalidadJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                modalidadJFrame.pack();
                modalidadJFrame.setVisible(true);

                //Cierra ventana principal
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonSalir);
                frame.dispose();
            }
        });
    }
}
