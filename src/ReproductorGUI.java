import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */


public class ReproductorGUI extends JFrame {
    
    private ListaCancion listaCancion = new ListaCancion();
    private JList<String> listaJ;
    private DefaultListModel<String> modeloLista;
    
    private JTextField campoNombre;
    private JTextField campoArtista;
    private JTextField campoDuracion;
    private JTextField campoTipo;
    
    private JLabel etiquetaImagen;
    
    private ReproductorMusica reproductorMusica = new ReproductorMusica();
    
    private String rutaMp3Actual = null;
    private String rutaImagenActual = null;
    
    public ReproductorGUI() {
        super("Reproductor Musical");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        
        modeloLista = new DefaultListModel<>();
        listaJ = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaJ);
        
        etiquetaImagen = new JLabel();
        etiquetaImagen.setPreferredSize(new Dimension(200, 200));
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);
        etiquetaImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JPanel panelDerecha = new JPanel(new BorderLayout());
        panelDerecha.add(etiquetaImagen, BorderLayout.CENTER);
        
        add(scroll, BorderLayout.CENTER);
        add(panelDerecha, BorderLayout.EAST);
        
        JPanel panelInferior = new JPanel();
        
        JButton botonAgregar = new JButton("Agregar");
        JButton botonSeleccionar = new JButton("Seleccionar");
        JButton botonReproducir = new JButton("Reproducir");
        JButton botonPausar = new JButton("Pausar");
        JButton botonDetener = new JButton("Detener");
        
        panelInferior.add(botonAgregar);
        panelInferior.add(botonSeleccionar);
        panelInferior.add(botonReproducir);
        panelInferior.add(botonPausar);
        panelInferior.add(botonDetener);
        
        add(panelInferior, BorderLayout.SOUTH);
        
        JPanel panelSuperior = new JPanel(new GridLayout(2,5));
        campoNombre = new JTextField();
        campoArtista = new JTextField();
        campoDuracion = new JTextField();
        campoTipo = new JTextField();
        
        JButton botonCargarMp3 = new JButton("CargarMP3");
        JButton botonCargarImagen = new JButton("CargarImagen");
        
        panelSuperior.add(new JLabel("Nombre:"));
        panelSuperior.add(campoNombre);
        panelSuperior.add(new JLabel("Artista:"));
        panelSuperior.add(campoArtista);
        panelSuperior.add(botonCargarMp3);
        
        panelSuperior.add(new JLabel("Duracion:"));
        panelSuperior.add(campoDuracion);
        panelSuperior.add(new JLabel("Tipo:"));
        panelSuperior.add(campoTipo);
        panelSuperior.add(botonCargarImagen);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        botonCargarMp3.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int opt = chooser.showOpenDialog(this);
            if (opt == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                if (f.getName().toLowerCase().endsWith(".mp3")) {
                    rutaMp3Actual = f.getAbsolutePath();
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un archivo MP3 valido.");
                }
            }
        });
        
        botonCargarImagen.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int opt = chooser.showOpenDialog(this);
            if (opt == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                String fname = f.getName().toLowerCase();
                if (fname.endsWith(".jpg") || fname.endsWith(".jpeg") || fname.endsWith(".png")) {
                    rutaImagenActual = f.getAbsolutePath();
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un archivo de imagen valido (jpg, png).");
                }
            }
        });
        
        
        botonAgregar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String artista = campoArtista.getText();
            String duracion = campoDuracion.getText();
            String tipo = campoTipo.getText();
            
            if (nombre.isEmpty() || artista.isEmpty() || duracion.isEmpty() || tipo.isEmpty() || rutaMp3Actual == null || rutaImagenActual == null) {
                JOptionPane.showMessageDialog(this, "Complete todos los datos y seleccione MP3 e Imagen.");
                return;
            }
            
            Cancion c = new Cancion(nombre, artista, duracion, tipo, rutaMp3Actual, rutaImagenActual);
            listaCancion.agregar(c);
            modeloLista.addElement(c.toString());
            
            campoNombre.setText("");
            campoArtista.setText("");
            campoDuracion.setText("");
            campoTipo.setText("");
            rutaMp3Actual = null;
            rutaImagenActual = null;
        });
        
        
        
        botonSeleccionar.addActionListener(e -> {
            int indice = listaJ.getSelectedIndex();
            if (indice >= 0) {
                Cancion seleccionada = listaCancion.obtenerEnIndice(indice);
                if (seleccionada.rutaImagen != null) {
                    ImageIcon img = new ImageIcon(seleccionada.rutaImagen);
                    Image escalada = img.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH);
                    etiquetaImagen.setIcon(new ImageIcon(escalada));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una cancion de la lista.");
            }
        });
        
        botonReproducir.addActionListener(e -> {
            int indice = listaJ.getSelectedIndex();
            if (indice >= 0) {
                Cancion seleccionada = listaCancion.obtenerEnIndice(indice);
                if (seleccionada.rutaImagen != null) {
                    ImageIcon img = new ImageIcon(seleccionada.rutaImagen);
                    Image escalada = img.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH);
                    etiquetaImagen.setIcon(new ImageIcon(escalada));
                }
                reproductorMusica.reproducir(seleccionada.rutaArchivo);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una cancion de la lista para reproducir.");
            }
        });
        
        botonPausar.addActionListener(e -> {
            reproductorMusica.pausar();
        });
        
        botonDetener.addActionListener(e -> {
            reproductorMusica.detener();
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
