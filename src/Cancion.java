/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */
public class Cancion {
    String nombre;
    String artista;
    String duracion;
    String tipoMusica;
    String rutaArchivo;
    String rutaImagen;
    Cancion siguiente;
    
    public Cancion(String nombre, String artista, String duracion, String tipoMusica, String rutaArchivo, String rutaImagen) {
        this.nombre = nombre;
        this.artista = artista;
        this.duracion = duracion;
        this.tipoMusica = tipoMusica;
        this.rutaArchivo = rutaArchivo;
        this.rutaImagen = rutaImagen;
        this.siguiente = null;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + artista;
    }
}
