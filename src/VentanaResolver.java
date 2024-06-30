import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaResolver extends JFrame {
    private JPanel panelResolver;
    private JLabel fondoLabel;
    private JLabel[][] labels;
    private JLabel respuestaLabel;

    public VentanaResolver() {
        super("Sudoku por Agustín Puente");

        setContentPane(panelResolver);
        setSize(1080, 720);
        setResizable(false);

        //Medidas 100,58
        //Grafica el tablero
        int [][] tablero;

        tablero = TablerosGenerator.sudokuGenerator();
        SudokuGraph sudokuGraph = new SudokuGraph(tablero);
        sudokuGraph.removeNumbers(49);

        labels = new JLabel[9][9];

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (sudokuGraph.getValor(i, j) == 0){
                    labels[i][j] = new JLabel("-");
                    labels[i][j].setBounds(100*(i+1)-5, 58*(j+1), 30, 30);
                    Font font = new Font("Arial", Font.BOLD, 24);
                    labels[i][j].setFont(font);
                    labels[i][j].setForeground(Color.white);
                    fondoLabel.add(labels[i][j]);
                } else {
                    labels[i][j] = new JLabel(String.valueOf(sudokuGraph.getValor(i,j)));
                    labels[i][j].setBounds(100*(i+1)-5, 58*(j+1), 30, 30);
                    Font font = new Font("Arial", Font.BOLD, 24);
                    labels[i][j].setFont(font);
                    labels[i][j].setForeground(Color.white);
                    fondoLabel.add(labels[i][j]);
                }
            }
        }

        //boton de resolver

        JButton buttonResolver = new JButton("Resolver");
        buttonResolver.setBounds(500, 650, 100, 30);

        fondoLabel.add(buttonResolver);

        buttonResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sudokuGraph.resolver()){
                    respuestaLabel = new JLabel("Sudoku resuelto");
                } else {
                    respuestaLabel = new JLabel("No se puede resolver");
                }
                respuestaLabel.setBounds(650, 650, 200, 30);
                Font font = new Font("Arial", Font.BOLD, 24);
                respuestaLabel.setFont(font);
                respuestaLabel.setForeground(Color.GREEN);
                fondoLabel.add(respuestaLabel);
                panelResolver.revalidate();
                panelResolver.repaint();
                buttonResolver.setEnabled(false);

                //mete los numeros en el sudoku
                for (int i = 0; i < 9; i++){
                    for (int j = 0; j < 9; j++){
                        if (labels[i][j].getText().equals("-")){
                            labels[i][j].setText(String.valueOf(sudokuGraph.getValor(i,j)));
                        }
                    }
                }
            }
        });

        //JButton volver

        JButton buttonSalir = new JButton("Salir");
        buttonSalir.setBounds(350, 650, 100, 30);

        fondoLabel.add(buttonSalir);

        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Crea la ventana de Modalidad
                JFrame modalidadJFrame = new VentanaModalidad();
                modalidadJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                modalidadJFrame.pack();
                modalidadJFrame.setVisible(true);

                //Cierra ventana actual
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonSalir);
                frame.dispose();
            }
        });
    }
}
