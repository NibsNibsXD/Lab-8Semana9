/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */


public class ListaCancion {
    Cancion cabeza;
    
    public void agregar(Cancion c) {
        if (cabeza == null) {
            cabeza = c;
        } else {
            Cancion temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = c;
        }
    }
    
    public Cancion obtenerEnIndice(int indice) {
        int contador = 0;
        Cancion temp = cabeza;
        while (temp != null) {
            if (contador == indice) {
                return temp;
            }
            contador++;
            temp = temp.siguiente;
        }
        return null;
    }
    
    public int tamano() {
        int c = 0;
        Cancion temp = cabeza;
        while (temp != null) {
            c++;
            temp = temp.siguiente;
        }
        return c;
    }
    
    public String[] aArreglo() {
        String[] arr = new String[tamano()];
        int i=0;
        Cancion temp = cabeza;
        while (temp != null) {
            arr[i++] = temp.toString();
            temp = temp.siguiente;
        }
        return arr;
    }
}
