package tarea3;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

public class Kilometraje extends JFrame {

    // Variables del arreglo e indice
    double[] kilometros = new double[7];
    int indice = 0;

    JTextField txtKilometraje = new JTextField(10);
    JButton btnAgregar = new JButton("Agregar Ruta");
    JButton btnGenerar = new JButton("Generar Reporte");
    JLabel lblEstado = new JLabel("Ruta 1 de 7 (Lunes)");

    public Kilometraje() {
        setTitle("Registro de Kilometraje Semanal");
        setLayout(new FlowLayout());
        add(new JLabel("Kilometraje:"));
        add(txtKilometraje);
        add(btnAgregar);
        add(btnGenerar);
        add(lblEstado);
        setSize(480, 130);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnAgregar.addActionListener(e -> agregarRuta());
        btnGenerar.addActionListener(e -> generarReporte());
    }

    private void agregarRuta() {
        if (indice >= 7) {
            JOptionPane.showMessageDialog(this, "Ya ingresó los 7 recorridos.");
            return;
        }
        try {
            double valor = Double.parseDouble(txtKilometraje.getText().trim());
            kilometros[indice] = valor;
            indice++;
            txtKilometraje.setText("");
            txtKilometraje.requestFocus();
            if (indice < 7) {
                lblEstado.setText("Ruta " + (indice + 1) + " de 7");
            } else {
                lblEstado.setText("Listo: 7 rutas ingresadas");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
        }
    }

    private void generarReporte() {
        if (indice < 7) {
            JOptionPane.showMessageDialog(this, "Faltan rutas por ingresar (" + indice + "/7).");
            return;
        }
        double total = 0;
        for (int i = 0; i < kilometros.length; i++) {
            total += kilometros[i];
        }
        double promedio = total / 7;
        total = Math.round(total * 100) / 100.0;
        promedio = Math.round(promedio * 100) / 100.0;

        try {
            new File("temp").mkdirs();
            PrintWriter pw = new PrintWriter(new FileWriter("temp/erick_reporte_rutas.txt", StandardCharsets.UTF_8));
            pw.println("Reporte Semanal de Rutas");
            pw.println("Total de Kilómetros: " + total);
            pw.println("Promedio Diario: " + promedio);
            pw.close();
            JOptionPane.showMessageDialog(this, "Reporte generado en temp/erick_reporte_rutas.txt");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al escribir el archivo: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Kilometraje().setVisible(true));
    }
}
