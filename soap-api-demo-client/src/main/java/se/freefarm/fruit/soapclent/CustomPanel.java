package se.freefarm.fruit.soapclent;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;

public class CustomPanel extends JPanel {
    public int ypos=0;
	int FONTSIZE=32;
    GridBagLayout g = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();


    public CustomPanel() {
        setLayout(g);
    }
    public void place(Component component) {
        component.setFont(new Font("Courier", Font.PLAIN, FONTSIZE));
        g.setConstraints(component, c);
        add(component);
    }
    public void place(Component component, Font f) {
        component.setFont(f);
        g.setConstraints(component, c);
        add(component);
    }
    public void placeln(Component component) {
        component.setFont(new Font("Courier", Font.PLAIN, FONTSIZE));
        g.setConstraints(component, c);
        add(component);
        newLine();
    }
    public void placeln(Component component, Font f) {
        component.setFont(f);
        g.setConstraints(component, c);
        add(component);
        newLine();
    }

    public void newLine() {
        ++ypos;
        c.gridy=ypos;
    }


}
