package tarea3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;

public class Temperaturas extends JFrame {

    // Variables globales
    double[] temperaturas = new double[6];
    int indice = 0;
    String ruta = "temp/temperaturas.txt";
    boolean cargado = false;

    JButton btnCargar = new JButton("Cargar Datos");
    JButton btnCalcular = new JButton("Calcular Estadísticas");
    JTextArea txtResultado = new JTextArea(8, 30);

    public Temperaturas() {
        setTitle("Lector y Analizador de Temperaturas");
        setLayout(new BorderLayout(5, 5));
        JPanel arriba = new JPanel(new FlowLayout());
        arriba.add(btnCargar);
        arriba.add(btnCalcular);
        add(arriba, BorderLayout.NORTH);
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCargar.addActionListener(e -> cargarDatos());
        btnCalcular.addActionListener(e -> calcularEstadisticas());
    }

    private void cargarDatos() {
        try {
            Scanner sc = new Scanner(new File(ruta));
            indice = 0;
            while (sc.hasNextLine() && indice < temperaturas.length) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }
                temperaturas[indice] = Double.parseDouble(linea);
                indice++;
            }
            sc.close();
            cargado = true;
            JOptionPane.showMessageDialog(this, "Los datos fueron cargados en memoria.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al leer " + ruta + ": " + ex.getMessage());
        }
    }

    private void calcularEstadisticas() {
        if (!cargado) {
            JOptionPane.showMessageDialog(this, "Primero presione \"Cargar Datos\".");
            return;
        }
        double suma = 0;
        int calidos = 0, frescos = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            suma += temperaturas[i];
            if (temperaturas[i] >= 25.0) {
                calidos++;
            } else {
                frescos++;
            }
        }
        double promedio = Math.round(suma / temperaturas.length * 100) / 100.0;

        txtResultado.setText("");
        txtResultado.append("Análisis Climático\n");
        txtResultado.append("------------------\n");
        txtResultado.append("Promedio de Temperatura: " + promedio + "\n");
        txtResultado.append("Días Cálidos (>= 25°): " + calidos + "\n");
        txtResultado.append("Días Frescos (< 25°): " + frescos + "\n");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Temperaturas().setVisible(true));
    }
}
