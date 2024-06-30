import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSudokuFacil extends JFrame {
    private JTextField[][] textFields;
    private JLabel[][] labels;
    private JPanel panelSudokuFacil;
    private JLabel fondoLabel;
    private JLabel respuestaLabel;
    private JLabel tiempoLabel;
    private Timer timer;
    private int segundos;

    public VentanaSudokuFacil() {
        super("Sudoku por Agustín Puente");

        setContentPane(panelSudokuFacil);
        setSize(1080, 720);
        setResizable(false);

        //JLabel del tiempo y inicia el contados
        tiempoLabel = new JLabel("Tiempo: 0:00");
        tiempoLabel.setBounds(50, 650, 200, 30);
        Font tiempoFont = new Font("Arial", Font.BOLD, 24);
        tiempoLabel.setFont(tiempoFont);
        tiempoLabel.setForeground(Color.white);
        fondoLabel.add(tiempoLabel);

        segundos = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                int minutos = segundos / 60;
                int seg = segundos % 60;
                tiempoLabel.setText(String.format("Tiempo: %d:%02d", minutos, seg));
            }
        });
        timer.start(); // Iniciar el timer al abrir la ventana

        //Medidas 100,58
        //Grafica el tablero
        int[][] tablero;

        tablero = TablerosGenerator.sudokuGenerator();
        SudokuGraph sudokuGraph = new SudokuGraph(tablero);
        sudokuGraph.removeNumbers(10);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuGraph.getValor(i,j) == 0){
                    tablero[i][j] = 0;
                }
            }
        }

        textFields = new JTextField[9][9];
        labels = new JLabel[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuGraph.getValor(i, j) == 0) {
                    textFields[i][j] = new JTextField();
                    textFields[i][j].setBounds(100 * (i + 1) - 5, 58 * (j + 1), 30, 30);
                    fondoLabel.add(textFields[i][j]);
                } else {
                    labels[i][j] = new JLabel(String.valueOf(sudokuGraph.getValor(i, j)));
                    labels[i][j].setBounds(100 * (i + 1) - 5, 58 * (j + 1), 30, 30);
                    Font font = new Font("Arial", Font.BOLD, 24);
                    labels[i][j].setFont(font);
                    labels[i][j].setForeground(Color.white);
                    fondoLabel.add(labels[i][j]);
                }
            }
        }

        //JButton verificar

        JButton buttonVerificar = new JButton("Verificar");
        buttonVerificar.setBounds(500, 650, 100, 30);

        fondoLabel.add(buttonVerificar);

        buttonVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                boolean romper = false;
                int valor;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if(sudokuGraph.getValor(i,j) == 0){
                            try{
                                valor = Integer.parseInt(textFields[i][j].getText());
                            } catch (NumberFormatException exception){
                                romper = true;
                                break;
                            }
                            if (romper) break;
                            if (!sudokuGraph.setValor(i,j,valor)){
                                romper = true;
                            }
                        }
                    }
                    if(romper) break;
                }

                if (romper){
                    respuestaLabel = new JLabel("El sudoku esta MAL");
                    respuestaLabel.setForeground(Color.red);
                    tiempoLabel.setForeground(Color.red);
                } else {
                    respuestaLabel = new JLabel("El sudoku esta BIEN");
                    respuestaLabel.setForeground(Color.green);
                    tiempoLabel.setForeground(Color.green);
                }
                respuestaLabel.setBounds(650, 650, 240, 30);
                Font font = new Font("Arial", Font.BOLD, 24);
                respuestaLabel.setFont(font);
                fondoLabel.add(respuestaLabel);
                panelSudokuFacil.revalidate();
                panelSudokuFacil.repaint();
                buttonVerificar.setEnabled(false);

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (textFields[i][j] != null) {
                            String text = textFields[i][j].getText();
                            labels[i][j] = new JLabel(text);
                            labels[i][j].setBounds(textFields[i][j].getBounds());
                            Font labelFont = new Font("Arial", Font.BOLD, 24);
                            labels[i][j].setFont(labelFont);
                            if(romper) {
                                labels[i][j].setForeground(Color.red);
                            } else {
                                labels[i][j].setForeground(Color.green);
                            }
                            fondoLabel.remove(textFields[i][j]);
                            fondoLabel.add(labels[i][j]);
                        }
                    }
                }
                panelSudokuFacil.revalidate();
                panelSudokuFacil.repaint();
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

                //Cierra ventana principal
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonSalir);
                frame.dispose();
            }
        });
    }
}
