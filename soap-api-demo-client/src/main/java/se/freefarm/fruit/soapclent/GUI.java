package se.freefarm.fruit.soapclent;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class GUI extends CustomPanel implements ActionListener {
	JComboBox fruitSelector, unitsSelector;
	JButton ok;
	JLabel priceLabel;
	SoapClient soapClient;
    GUI() {
        soapClient=new SoapClient();
        c.insets = new Insets(5,5,5,5);  //padding
        c.anchor=c.WEST;
        c.fill=c.NONE;		
		place(new JLabel("Fruit: "));		
		fruitSelector=new JComboBox();
        fruitSelector.addItem("apple");
        fruitSelector.addItem("banana");
        placeln(fruitSelector);
        place(new JLabel("Units: "));		
		unitsSelector=new JComboBox();
        unitsSelector.addItem(" ");
		for (int i=1;i<10;i++) {
			unitsSelector.addItem("     "+i);
		}
        placeln(unitsSelector);
		c.gridwidth=2;
        c.anchor=c.EAST;		
		ok = new JButton(" OK ");
        ok.addActionListener(this);		
        placeln(ok);
		c.gridwidth=1;
		c.anchor=c.WEST;
		place(new JLabel("Price: "));		
		priceLabel = new JLabel("...");
		placeln(priceLabel);
        
    }


    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        if (buttonText.equals(" OK ")) {
            if ("".equals(unitsSelector.getSelectedItem().toString().trim())) {
                priceLabel.setText("");
                return;
            }
            Integer units = new Integer(unitsSelector.getSelectedItem().toString().trim());
            String name = fruitSelector.getSelectedItem().toString();
            double price=soapClient.doSOAPCall(units,name);
            priceLabel.setText(String.format("%.2f", price));
        }
    }
}
