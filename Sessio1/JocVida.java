package Sessio1;

import javax.swing.*;
import java.awt.event.*;

public class JocVida extends JFrame implements ActionListener {

	JButton boton1;

	public JocVida() {
	
	   //Layout absoluto
		setLayout(null);
		
		//Tamaño de la ventana
		setBounds(0,0,450,350);
		
		//Título
		setTitle("JOC de la VIDA!!!");
		
		//No redimensionable
		setResizable(false);
		
		//Cerrar proceso al cerrar la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Botón
		boton1=new JButton("Finalizar");
		boton1.setBounds(300,250,100,30);
		add(boton1);
		boton1.addActionListener(this);
		
		//Muestro JFrame (lo último para que lo pinte todo correctamanete)
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
