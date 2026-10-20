package tarea3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

public class Tareas extends JFrame {

    // Variables del arreglo e indice
    String[] tareas = new String[5];
    int indice = 0;

    JTextField txtTarea = new JTextField(20);
    JTextArea txtAreaTareas = new JTextArea(8, 30);
    JButton btnAgregar = new JButton("Agregar Tarea");
    JButton btnExportar = new JButton("Exportar Agenda");

    public Tareas() {
        setTitle("Gestor de Tareas Pendientes");
        setLayout(new BorderLayout(5, 5));
        JPanel arriba = new JPanel(new FlowLayout());
        arriba.add(new JLabel("Tarea:"));
        arriba.add(txtTarea);
        arriba.add(btnAgregar);
        add(arriba, BorderLayout.NORTH);
        txtAreaTareas.setEditable(false);
        add(new JScrollPane(txtAreaTareas), BorderLayout.CENTER);
        JPanel abajo = new JPanel();
        abajo.add(btnExportar);
        add(abajo, BorderLayout.SOUTH);
        setSize(520, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnAgregar.addActionListener(e -> agregarTarea());
        btnExportar.addActionListener(e -> exportarAgenda());
    }

    private void agregarTarea() {
        if (indice >= 5) {
            JOptionPane.showMessageDialog(this, "Ya ingresó las 5 tareas.");
            return;
        }
        String texto = txtTarea.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba la descripción de la tarea.");
            return;
        }
        tareas[indice] = texto;
        txtAreaTareas.append((indice + 1) + ". " + texto + "\n");
        indice++;
        txtTarea.setText("");
        txtTarea.requestFocus();
    }

    private void exportarAgenda() {
        if (indice < 5) {
            JOptionPane.showMessageDialog(this, "Faltan tareas por ingresar (" + indice + "/5).");
            return;
        }
        try {
            new File("temp").mkdirs();
            PrintWriter pw = new PrintWriter(new FileWriter("temp/efrain_agenda.txt", StandardCharsets.UTF_8));
            pw.println("Agenda de Tareas");
            for (int i = 0; i < tareas.length; i++) {
                pw.println((i + 1) + ". " + tareas[i]);
            }
            pw.close();
            JOptionPane.showMessageDialog(this, "Agenda exportada en temp/efrain_agenda.txt");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al escribir el archivo: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Tareas().setVisible(true));
    }
}
