import javazoom.jl.player.Player;
import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */

public class ReproductorMusica {
    private Player reproductor;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private boolean pausado = false;
    
    public void reproducir(String rutaArchivo) {
        try {
            detener();
            fis = new FileInputStream(rutaArchivo);
            bis = new BufferedInputStream(fis);
            reproductor = new Player(bis);
            reproductor.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void pausar() {
        detener();
        pausado = true;
    }
    
    public void detener() {
        try {
            if (reproductor != null) {
                reproductor.close();
            }
            if (bis != null) bis.close();
            if (fis != null) fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reproductor = null;
            bis = null;
            fis = null;
            pausado = false;
        }
    }
}
