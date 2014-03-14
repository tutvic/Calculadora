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
public class Nodo {

    private String dato;
    private Nodo siguiente;

    public Nodo() {

    }

    public Nodo(String d) {
        this.dato = d;
        this.siguiente = null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}
