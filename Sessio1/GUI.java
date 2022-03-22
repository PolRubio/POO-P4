package Sessio1;

import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

	JButton boton1;

	public GUI() {
	
	   //Layout absoluto
		setLayout(null);
		
		//Tama�o de la ventana
		setBounds(0,0,450,350);
		
		//T�tulo
		setTitle("Ejemplo 1: Bot�n");
		
		//No redimensionable
		setResizable(false);
		
		//Cerrar proceso al cerrar la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Bot�n
		boton1=new JButton("Finalizar");
		boton1.setBounds(300,250,100,30);
		add(boton1);
		boton1.addActionListener(this);
		
		//Muestro JFrame (lo �ltimo para que lo pinte todo correctamanete)
	    setVisible(true);
	
	
	  }
	
	  public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == boton1) {
	    	System.out.print("adeu");
	    	System.exit(0);
	    }
	  }
	
	  public static void main(String[] args) {
	    new GUI();
	  }
}