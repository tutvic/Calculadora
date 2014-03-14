/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora_es;

/**
 *
 * @author Victor
 */
public class Nodoc {

    private String dato;
    private Nodoc siguiente;
    private Nodoc anterior;

    public Nodoc() {

    }

    public Nodoc(String d) {
        this.dato = d;
        this.siguiente = null;
        this.anterior = null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodoc getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodoc siguiente) {
        this.siguiente = siguiente;
    }

    public Nodoc getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodoc anterior) {
        this.anterior = anterior;
    }

}
