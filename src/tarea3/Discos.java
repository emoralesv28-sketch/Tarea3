package tarea3;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;

public class Discos extends JFrame {

    // Arreglos paralelos globales
    String[] titulos = new String[5];
    int[] anios = new int[5];
    String ruta = "temp/discos.csv";

    JTextField txtBuscar = new JTextField(15);
    JButton btnBuscar = new JButton("Buscar");
    JLabel lblAnio = new JLabel("Año actual: -");
    JTextField txtNuevoAnio = new JTextField(8);
    JButton btnActualizar = new JButton("Actualizar Registro");

    public Discos() {
        initUI();
        cargarDatos(); // se ejecuta automáticamente al abrir la ventana
    }

    private void initUI() {
        setTitle("Biblioteca de Discos Musicales");
        setLayout(new GridLayout(0, 2, 8, 8));
        add(new JLabel("Buscar Álbum:"));
        add(txtBuscar);
        add(btnBuscar);
        add(lblAnio);
        add(new JLabel("Nuevo Año:"));
        add(txtNuevoAnio);
        add(btnActualizar);
        add(new JLabel(""));
        setSize(430, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnBuscar.addActionListener(e -> buscar());
        btnActualizar.addActionListener(e -> actualizar());
    }

    private void cargarDatos() {
        try {
            Scanner sc = new Scanner(new File(ruta));
            int i = 0;
            while (sc.hasNextLine() && i < titulos.length) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(",");
                titulos[i] = partes[0].trim();
                anios[i] = Integer.parseInt(partes[1].trim());
                i++;
            }
            sc.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar " + ruta + ": " + ex.getMessage());
        }
    }

    // Busqueda secuencial: devuelve el indice o -1
    private int buscarIndice(String nombre) {
        int pos = -1;
        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i] != null && titulos[i].equalsIgnoreCase(nombre)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private void buscar() {
        int pos = buscarIndice(txtBuscar.getText().trim());
        if (pos == -1) {
            lblAnio.setText("Año actual: no encontrado");
        } else {
            lblAnio.setText("Año actual: " + anios[pos]);
        }
    }

    private void actualizar() {
        int pos = buscarIndice(txtBuscar.getText().trim());
        if (pos == -1) {
            JOptionPane.showMessageDialog(this, "Álbum no encontrado.");
            return;
        }
        try {
            anios[pos] = Integer.parseInt(txtNuevoAnio.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un año válido.");
            return;
        }
        try {
            FileWriter fw = new FileWriter(ruta, false); // sobrescribe
            for (int i = 0; i < titulos.length; i++) {
                fw.write(titulos[i] + "," + anios[i] + "\n");
            }
            fw.close();
            lblAnio.setText("Año actual: " + anios[pos]);
            txtNuevoAnio.setText("");
            JOptionPane.showMessageDialog(this, "Registro actualizado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al escribir: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Discos().setVisible(true));
    }
}
