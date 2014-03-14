/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora_es;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculadora_es.ColaVacia;
import calculadora_es.ErrorParentesis;
import calculadora_es.ErrorParentesis;

/**
 *
 * @author Victor
 */
public class Calcu extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Cola infija;
    Pila posfija;
    String cadena = "";
    String mostrar = "";
    String ans = "";

    private JTextField t;

    public Calcu() throws ColaVacia, PilaVacia, ErrorParentesis {
        super("Calculadora");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(50, 50, 500, 500);
        JPanel panel = new JPanel();
        String labelButtons[] = {"Sen", "Cos", "Tan", "DEL", "AC",
            "7", "8", "9", "(", ")",
            "4", "5", "6", "+", "-",
            "1", "2", "3", "*", "/",
            "0", "√", "^", ".", "=",
            "-/+", "ans"};

        panel.setLayout(new GridLayout(0, 5));
        for (int i = 0; i < labelButtons.length; ++i) {
            JButton button = new JButton(labelButtons[i]);
            button.addActionListener(this);
            panel.add(button);
        }

        JPanel ambos = (JPanel) this.getContentPane();
        ambos.setLayout(new BorderLayout());
        t = new JTextField();
        t.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 50));
        t.setHorizontalAlignment(JTextField.RIGHT);
        t.setText("0.");
        ambos.add(t, BorderLayout.NORTH);
        ambos.add(panel, BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(true);
    }

    public String calcula(String s) throws ColaVacia, PilaVacia, ErrorParentesis {
        infija = new Cola();
        posfija = new Pila();

        if (balanceParentesis(s) != true) {
            throw new ErrorParentesis();
        } else {
            String sub = new String();;

            for (int i = 0; i < s.length(); i++) {
                if (((i == 0) && (s.charAt(0) == '-')) || ((s.charAt(i) == '(') && (s.charAt(i + 1) == '-'))) {
                    infija.push("0");
                }
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '.') {
                    sub = sub + s.charAt(i);
                } else {
                    if (sub.length() != 0) {
                        infija.push(sub);
                    }
                    sub = new String();

                    switch (s.charAt(i)) {
                        case '+':
                            infija.push("+");
                            break;
                        case '(':
                            infija.push("(");
                            break;
                        case ')':
                            infija.push(")");
                            break;
                        case '-':
                            infija.push("-");
                            break;
                        case '*':
                            infija.push("*");
                            break;
                        case '/':
                            infija.push("/");
                            break;
                        case 's':
                            infija.push("s");
                            break;
                        case 'c':
                            infija.push("c");
                            break;
                        case 't':
                            infija.push("t");
                            break;
                        case 'r':
                            infija.push("r");
                            break;
                        case '^':
                            infija.push("^");
                            break;
                        case '@':
                            infija.push("@");
                            break;
                        case 'x':
                            infija.push("*");
                            infija.push(String.valueOf(1));
                            break;
                        case 'X':
                            infija.push(String.valueOf(1));

                    }
                }
            }

            if (sub.length() != 0) {
                infija.push(sub);
            }
            infija.mostrarCola();
            this.posFija();
            return this.resuelve();
        }
    }

    private String resuelve() throws ColaVacia, PilaVacia {
        Pila pos = new Pila();
        Pila resul = new Pila();
        Double numeroA;
        Double numeroB;
        Double exp;

        while (!posfija.isVacia()) {
            pos.push(posfija.pop());
        }

        String dato;
        while (!pos.isVacia()) {
            dato = pos.pop();
            System.out.println(dato);
            if ((dato.compareTo("+") != 0) && (dato.compareTo("-") != 0) && (dato.compareTo("*") != 0) && (dato.compareTo("/") != 0) && (dato.compareTo("s") != 0) && (dato.compareTo("c") != 0) && (dato.compareTo("t") != 0) && (dato.compareTo("r") != 0) && (dato.compareTo("^") != 0) && (dato.compareTo("(") != 0) && (dato.compareTo(")") != 0) && (dato.compareTo("@") != 0)) {
                resul.push(dato);
            } else if ((dato.compareTo("s") == 0) || (dato.compareTo("c") == 0) || (dato.compareTo("t") == 0) || (dato.compareTo("r") == 0) || (dato.compareTo("@") == 0)) {
                numeroA = Double.parseDouble(resul.pop());
                switch (dato) {
                    case "s":
                        exp = (numeroA * Math.PI) / 180;
                        resul.push(String.valueOf(Math.sin(exp)));
                        break;
                    case "c":
                        exp = (numeroA * Math.PI) / 180;
                        resul.push(String.valueOf(Math.cos(exp)));
                        break;
                    case "t":
                        exp = (numeroA * Math.PI) / 180;
                        resul.push(String.valueOf(Math.tan(exp)));
                        break;
                    case "r":
                        resul.push(String.valueOf(Math.sqrt(numeroA)));
                        break;
                    case "@":
                        resul.push(String.valueOf(0 - numeroA));
                        break;
                }
            } else {
                numeroB = Double.parseDouble(resul.pop());
                numeroA = Double.parseDouble(resul.pop());

                switch (dato) {
                    case "+":
                        resul.push(String.valueOf(numeroA + numeroB));
                        break;
                    case "-":
                        resul.push(String.valueOf(numeroA - numeroB));
                        break;
                    case "*":
                        resul.push(String.valueOf(numeroA * numeroB));
                        break;
                    case "/":
                        resul.push(String.valueOf(numeroA / numeroB));
                        break;
                    case "^":
                        resul.push(String.valueOf(Math.pow(numeroA, numeroB)));
                        break;
                }
            }
        }
        return resul.pop();
    }

    private void posFija() throws ColaVacia, PilaVacia {
        Pila aux = new Pila();
        String dato = new String();
        String dato1 = new String();

        while (!infija.isVacia()) {
            dato = infija.pop();
            if ((dato.compareTo("+") != 0) && (dato.compareTo("-") != 0) && (dato.compareTo("*") != 0) && (dato.compareTo("/") != 0) && (dato.compareTo("s") != 0) && (dato.compareTo("c") != 0) && (dato.compareTo("t") != 0) && (dato.compareTo("r") != 0) && (dato.compareTo("^") != 0) && (dato.compareTo("(") != 0) && (dato.compareTo(")") != 0)) {
                posfija.push(dato);
            } else if (dato.compareTo("(") == 0) {
                aux.push(dato);
            } else if (dato.compareTo(")") == 0) {
                do {
                    dato1 = aux.pop();
                    if (dato1.compareTo("(") != 0) {
                        posfija.push(dato1);
                    }
                } while (dato1.compareTo("(") != 0);
            } else if (aux.isVacia() || (this.prioridad(dato) > this.prioridad(aux.getInicio().getDato()))) {
                aux.push(dato);
            } else {
                while (!aux.isVacia() && (this.prioridad(dato) <= this.prioridad(aux.getInicio().getDato()))) {
                    posfija.push(aux.pop());
                }
                aux.push(dato);
            }
        }

        while (!aux.isVacia()) {
            posfija.push(aux.pop());
        }
    }

    private int prioridad(String op) {
        if (op.compareTo("(") == 0) {
            return 0;
        } else if (op.compareTo("+") == 0 || op.compareTo("-") == 0 || op.compareTo("@") == 0) {
            return 1;
        } else if (op.compareTo("*") == 0 || op.compareTo("/") == 0) {
            return 2;
        } else if (op.compareTo("^") == 0 || op.compareTo("r") == 0) {
            return 3;
        } else if (op.compareTo("s") == 0 || op.compareTo("c") == 0 || op.compareTo("t") == 0) {
            return 4;
        } else if (op.compareTo(")") == 0) {
            return 5;
        } else {
            return -1;
        }
    }

    public Boolean balanceParentesis(String cadena) throws PilaVacia {
        Pila p = new Pila();
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == '(') {
                p.push(")");
            } else if (!p.isVacia() && cadena.charAt(i) == ')') {
                p.pop();
            } else if (cadena.charAt(i) == ')' && p.isVacia()) {
                return false;
            }
        }
        return p.isVacia();
    }

    public void actionPerformed(ActionEvent e) {
        String digito = ((JButton) e.getSource()).getText();
        System.out.println(digito);

        if (digito.compareTo("0") >= 0 && digito.compareTo("9") <= 0) {
            cadena = cadena + digito;
            mostrar = mostrar + digito;
            t.setText(mostrar);
        } else if (digito.compareTo("=") == 0) {
            try {
                if (this.cadena != null) {
                    ans = calcula(this.cadena);
                }
                t.setText(ans);
                this.cadena = "";
                this.mostrar = "";
            } catch (ColaVacia | PilaVacia e1) {
                t.setText("Error");
                this.cadena = "";
                this.mostrar = "";
            } catch (ErrorParentesis e1) {
                t.setText("Error");
                this.cadena = "";
                this.mostrar = "";
            }
        } else {
            switch (digito) {
                case "+":
                    mostrar = mostrar + "+";
                    cadena = cadena + "+";
                    t.setText(mostrar);
                    break;

                case "-":
                    mostrar = mostrar + "-";
                    cadena = cadena + "-";
                    t.setText(mostrar);
                    break;

                case "*":
                    mostrar = mostrar + "*";
                    cadena = cadena + "*";
                    t.setText(mostrar);
                    break;

                case "/":
                    mostrar = mostrar + "/";
                    cadena = cadena + "/";
                    t.setText(mostrar);
                    break;

                case ".":
                    mostrar = mostrar + ".";
                    cadena = cadena + ".";
                    t.setText(mostrar);
                    break;

                case "(":
                    mostrar = mostrar + "(";
                    cadena = cadena + "(";
                    t.setText(mostrar);
                    break;

                case ")":
                    mostrar = mostrar + ")";
                    cadena = cadena + ")";
                    t.setText(mostrar);
                    break;

                case "^":
                    mostrar = mostrar + "^";
                    cadena = cadena + "^";
                    t.setText(mostrar);
                    break;

                case "AC":
                    this.mostrar = "";
                    this.cadena = "";
                    t.setText("0.");
                    break;

                case "ans":
                    this.mostrar = ans;
                    this.cadena = this.cadena + ans;
                    if (mostrar.length() != 0) {
                        t.setText(mostrar);
                    } else {
                        t.setText("0.");
                    }
                    break;

                case "DEL":
                    if (mostrar.length() != 0) {
                        this.mostrar = this.mostrar.substring(0, mostrar.length() - 1);
                        this.cadena = this.cadena.substring(0, cadena.length() - 1);
                        t.setText(mostrar);
                    } else {
                        t.setText("0.");
                    }
                    break;

                case "-/+":
                    this.mostrar = this.mostrar + "-";
                    this.cadena = this.cadena + "@";
                    t.setText(mostrar);
                    break;

                case "Sen":
                    this.mostrar = this.mostrar + " Sen ";
                    this.cadena = this.cadena + "s";
                    t.setText(mostrar);
                    break;

                case "Cos":
                    this.mostrar = this.mostrar + " Cos ";
                    this.cadena = this.cadena + "c";
                    t.setText(mostrar);
                    break;

                case "Tan":
                    this.mostrar = this.mostrar + " Tan ";
                    this.cadena = this.cadena + "t";
                    t.setText(mostrar);
                    break;

                case "√":
                    this.mostrar = this.mostrar + "√";
                    this.cadena = this.cadena + "r";
                    t.setText(mostrar);
                    break;
            }

        }
    }

}
