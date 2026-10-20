package tarea3;

import java.awt.GridLayout;
import javax.swing.*;

public class Principal extends JFrame {

    public Principal() {
        setTitle("Tarea 3 - Menú");
        setLayout(new GridLayout(6, 1, 5, 5));
        String[] nombres = {
            "1. Kilometraje Semanal", "2. Tareas Pendientes", "3. Temperaturas",
            "4. Discos Musicales", "5. Artistas y Canciones", "6. Videojuegos"};
        for (int i = 0; i < nombres.length; i++) {
            JButton b = new JButton(nombres[i]);
            final int n = i + 1;
            b.addActionListener(e -> abrir(n));
            add(b);
        }
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void abrir(int n) {
        switch (n) {
            case 1: new Kilometraje().setVisible(true); break;
            case 2: new Tareas().setVisible(true); break;
            case 3: new Temperaturas().setVisible(true); break;
            case 4: new Discos().setVisible(true); break;
            case 5: new ArtistasyCanciones().setVisible(true); break;
            default: new Gameplay().setVisible(true);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Principal().setVisible(true));
    }
}
