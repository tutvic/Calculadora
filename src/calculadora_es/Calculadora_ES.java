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
public class Calculadora_ES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Calcu cal = new Calcu();
        } catch (ColaVacia e) {
            e.printStackTrace();
        } catch (PilaVacia e) {
            e.printStackTrace();
        } catch (ErrorParentesis e) {
            e.printStackTrace();
        }

    }
}
