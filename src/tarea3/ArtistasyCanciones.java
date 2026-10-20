package tarea3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class ArtistasyCanciones extends JFrame {

    JTextField txtNombreArtista = new JTextField(12);
    JTextField txtPais = new JTextField(12);
    JTextField txtOyentes = new JTextField(12);
    JButton btnCrearArtista = new JButton("Crear Artista");

    JTextField txtTitulo = new JTextField(12);
    JTextField txtDuracion = new JTextField(12);
    JTextField txtGenero = new JTextField(12);
    JTextField txtArtistaCancion = new JTextField(12);
    JButton btnCrearCancion = new JButton("Crear Canción");

    JTextArea txtResultados = new JTextArea(10, 40);

    public ArtistasyCanciones() {
        setTitle("Registro de Streaming");
        setLayout(new BorderLayout(5, 5));

        JPanel pArtista = new JPanel(new GridLayout(0, 2, 5, 5));
        pArtista.setBorder(BorderFactory.createTitledBorder("Artista"));
        pArtista.add(new JLabel("Nombre:"));
        pArtista.add(txtNombreArtista);
        pArtista.add(new JLabel("País de origen:"));
        pArtista.add(txtPais);
        pArtista.add(new JLabel("Oyentes mensuales:"));
        pArtista.add(txtOyentes);
        pArtista.add(new JLabel(""));
        pArtista.add(btnCrearArtista);

        JPanel pCancion = new JPanel(new GridLayout(0, 2, 5, 5));
        pCancion.setBorder(BorderFactory.createTitledBorder("Canción"));
        pCancion.add(new JLabel("Título:"));
        pCancion.add(txtTitulo);
        pCancion.add(new JLabel("Duración (seg):"));
        pCancion.add(txtDuracion);
        pCancion.add(new JLabel("Género:"));
        pCancion.add(txtGenero);
        pCancion.add(new JLabel("Nombre del artista:"));
        pCancion.add(txtArtistaCancion);
        pCancion.add(new JLabel(""));
        pCancion.add(btnCrearCancion);

        JPanel arriba = new JPanel(new GridLayout(1, 2, 10, 10));
        arriba.add(pArtista);
        arriba.add(pCancion);
        add(arriba, BorderLayout.NORTH);

        txtResultados.setEditable(false);
        add(new JScrollPane(txtResultados), BorderLayout.CENTER);

        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCrearArtista.addActionListener(e -> crearArtista());
        btnCrearCancion.addActionListener(e -> crearCancion());
    }

    private void crearArtista() {
        try {
            // Variables locales solo de esta categoría
            String nombre = txtNombreArtista.getText().trim();
            String pais = txtPais.getText().trim();
            int oyentes = Integer.parseInt(txtOyentes.getText().trim());

            Artista a = new Artista();
            a.nombre = nombre;
            a.paisOrigen = pais;
            a.oyentesMensuales = oyentes;

            txtResultados.append("[ARTISTA]\n");
            txtResultados.append("Nombre: " + a.nombre + "\n");
            txtResultados.append("País de origen: " + a.paisOrigen + "\n");
            txtResultados.append("Oyentes mensuales: " + a.oyentesMensuales + "\n");
            txtResultados.append("----------------------------\n");

            txtNombreArtista.setText("");
            txtPais.setText("");
            txtOyentes.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los oyentes mensuales deben ser un número entero.");
        }
    }

    private void crearCancion() {
        try {
            String titulo = txtTitulo.getText().trim();
            int duracion = Integer.parseInt(txtDuracion.getText().trim());
            String genero = txtGenero.getText().trim();
            String artista = txtArtistaCancion.getText().trim();

            Cancion c = new Cancion();
            c.titulo = titulo;
            c.duracionSegundos = duracion;
            c.genero = genero;
            c.nombreArtista = artista;

            txtResultados.append("[CANCIÓN]\n");
            txtResultados.append("Título: " + c.titulo + "\n");
            txtResultados.append("Duración: " + c.duracionSegundos + " segundos\n");
            txtResultados.append("Género: " + c.genero + "\n");
            txtResultados.append("Artista: " + c.nombreArtista + "\n");
            txtResultados.append("----------------------------\n");

            txtTitulo.setText("");
            txtDuracion.setText("");
            txtGenero.setText("");
            txtArtistaCancion.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número entero (segundos).");
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ArtistasyCanciones().setVisible(true));
    }
}
