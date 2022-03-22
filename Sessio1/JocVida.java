package Sessio1;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class JocVida extends JFrame implements ActionListener {

	JPanel p1;
	JButton boton1;
	

	public JocVida() {
		p1 = new JPanel();
		p1.setLayout(new GridLayout(15,1));
		setBounds(0,0,450,350);
		setTitle("JOC de la VIDA!!!");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Botón
		boton1=new JButton("Finalizar");
		//boton1.setBounds(0,0,100,30);
		add(boton1);
		boton1.addActionListener((ActionListener) this);
		
		
	    setVisible(true);
	
	
	  }
	
	  public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == boton1) {
	    	System.out.print("adeu");
	    	System.exit(0);
	    }
	  }
	
	  public static void main(String[] args) {
	    new JocVida();
	  }
}
