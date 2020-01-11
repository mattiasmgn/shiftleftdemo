package se.freefarm.fruit.soapclent;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Main extends JFrame  {

    public static void main(String[] args) {
        Main j = new Main();
        j.pack();
        j.show();
    }

    public Main() {
        super("Fruit Swing");
        getContentPane().add(new GUI());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
       
    }
}

