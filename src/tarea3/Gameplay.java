package tarea3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

public class Gameplay extends JFrame {

    // Coleccion global
    ArrayList<Videojuego> catalogo = new ArrayList<>();

    JTextField txtTitulo = new JTextField(12);
    JTextField txtConsola = new JTextField(12);
    JTextField txtAnio = new JTextField(12);
    JButton btnAgregar = new JButton("Agregar Juego");
    JButton btnMostrar = new JButton("Mostrar Catálogo");
    JButton btnEliminar = new JButton("Eliminar Último");
    JTextArea txtResultados = new JTextArea(10, 40);

    public Gameplay() {
        setTitle("Catálogo de Videojuegos");
        setLayout(new BorderLayout(5, 5));

        JPanel campos = new JPanel(new GridLayout(0, 2, 5, 5));
        campos.add(new JLabel("Título:"));
        campos.add(txtTitulo);
        campos.add(new JLabel("Consola:"));
        campos.add(txtConsola);
        campos.add(new JLabel("Año de lanzamiento:"));
        campos.add(txtAnio);

        JPanel botones = new JPanel(new FlowLayout());
        botones.add(btnAgregar);
        botones.add(btnMostrar);
        botones.add(btnEliminar);

        JPanel arriba = new JPanel(new BorderLayout());
        arriba.add(campos, BorderLayout.CENTER);
        arriba.add(botones, BorderLayout.SOUTH);
        add(arriba, BorderLayout.NORTH);

        txtResultados.setEditable(false);
        add(new JScrollPane(txtResultados), BorderLayout.CENTER);

        setSize(520, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnAgregar.addActionListener(e -> agregar());
        btnMostrar.addActionListener(e -> mostrar());
        btnEliminar.addActionListener(e -> eliminar());
    }

    private void agregar() {
        try {
            String titulo = txtTitulo.getText().trim();
            String consola = txtConsola.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());

            Videojuego v = new Videojuego(titulo, consola, anio);
            catalogo.add(v);

            txtTitulo.setText("");
            txtConsola.setText("");
            txtAnio.setText("");
            txtTitulo.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero.");
        }
    }

    private void mostrar() {
        txtResultados.setText("");
        for (int i = 0; i < catalogo.size(); i++) {
            Videojuego v = catalogo.get(i);
            txtResultados.append((i + 1) + ". " + v.titulo + " | " + v.consola + " | " + v.anioLanzamiento + "\n");
        }
        txtResultados.append("----------------------------\n");
        txtResultados.append("Total de juegos registrados: " + catalogo.size() + "\n");
    }

    private void eliminar() {
        if (catalogo.size() > 0) {
            catalogo.remove(catalogo.size() - 1);
            JOptionPane.showMessageDialog(this, "El último juego fue eliminado.");
        } else {
            JOptionPane.showMessageDialog(this, "El catálogo está vacío.");
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Gameplay().setVisible(true));
    }
}
