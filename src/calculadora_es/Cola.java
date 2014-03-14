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
public class Cola {

    Nodoc inicio;
    Nodoc fin;

    public Cola() {
        inicio = null;
        fin = null;
    }

    public Boolean isVacia() {
        return inicio == null;
    }

    public void push(String dat) {
        Nodoc nuevo = new Nodoc(dat);

        if (isVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setAnterior(fin);
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    public String pop() throws ColaVacia {
        Nodoc aux = this.inicio;
        if (!isVacia()) {
            String resul = new String(aux.getDato());
            inicio = inicio.getSiguiente();
            if (inicio != null) {
                inicio.setAnterior(null);
            }
            aux.setSiguiente(null);
            aux = null;
            return resul;
        } else {
            throw new ColaVacia();
        }
    }

    public void mostrarCola() {
        Nodoc aux = this.inicio;
        System.out.print("null");
        while (aux != null) {
            System.out.print("<" + aux.getDato());
            aux = aux.getSiguiente();
        }
        System.out.println();
    }

}
