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
public class ColaVacia extends Exception {

    /**
     * Creates a new instance of <code>ColaVacia</code> without detail message.
     */
    
    public ColaVacia() {
    }

    /**
     * Constructs an instance of <code>ColaVacia</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    private static final long serialVersionUID = 1L;
    public ColaVacia(String msg) {
        super(msg);
    }
}
