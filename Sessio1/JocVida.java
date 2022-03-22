package Sessio1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JocVida extends JFrame implements ActionListener {
	
	private Casella[][] taulell;
	private int generacio=0;
	private int generacionsTotals,files,columnes,organismes,organismesTotals;
	
	private boolean game_over=false;
	
	JPanel panel_entrades,panel_taulell;
	JLabel filesLabel,columnesLabel,generacionsLabel,organismesViusLabel,dataValidityLabel;
	JTextField numFiles,numColumnes,numGeneracions,numEssersVius;
	JButton submitDades,nextGeneracio;
	
	public JocVida() {
		setLayout(new BorderLayout());
		
		panel_entrades=new JPanel();
		panel_entrades.setBounds(100,100,100,500);
		panel_entrades.setLayout(new GridLayout(20,1));
		
		panel_taulell=new JPanel();
		panel_taulell.setLayout(new GridLayout(12,12));
		
		setBounds(0,0,750,500);
		setTitle("JOC de la VIDA!!!");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		filesLabel=new JLabel("# de files:");
		numFiles=new JTextField(null, 15);
		
		columnesLabel=new JLabel("# de columnes:");
		numColumnes=new JTextField(null, 15);

		generacionsLabel=new JLabel("# de generacions:");
		numGeneracions=new JTextField(null, 15);
		
		organismesViusLabel=new JLabel("# de vius:");
		numEssersVius=new JTextField(null, 15);
		
		dataValidityLabel=new JLabel("DADES INCORRECTES");
		dataValidityLabel.setVisible(false);
		
		submitDades=new JButton("Entrar dades");
		submitDades.addActionListener(this);
		
		nextGeneracio=new JButton("Generacio");
		nextGeneracio.addActionListener(this);
		nextGeneracio.setEnabled(false);
		
		
		panel_entrades.add(filesLabel);
		panel_entrades.add(numFiles);
		panel_entrades.add(new JPanel());
		
		panel_entrades.add(columnesLabel);
		panel_entrades.add(numColumnes);
		panel_entrades.add(new JPanel());
		
		panel_entrades.add(generacionsLabel);
		panel_entrades.add(numGeneracions);
		panel_entrades.add(new JPanel());
		
		panel_entrades.add(organismesViusLabel);
		panel_entrades.add(numEssersVius);
		panel_entrades.add(new JPanel());
		
		panel_entrades.add(dataValidityLabel);
		
		panel_entrades.add(submitDades);
		panel_entrades.add(nextGeneracio);
		
		panel_entrades.add(new JPanel());
		panel_entrades.add(new JPanel());
		
		add(panel_entrades, BorderLayout.WEST);
		add(panel_taulell, BorderLayout.CENTER);
	    setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submitDades) {
			boolean dades=comprobacioDades();
			nextGeneracio.setEnabled(dades);
			
			if(dades){
				dataValidityLabel.setText("Dades correctes");
				setEnabledDades(false);
				panel_taulell.setLayout(new GridLayout(files,columnes));
				taulell=crearTaulell();
				
				posarVida(organismesTotals);
				mostraTaulell();
				
				
			} else setEnabledDades(true);
			dataValidityLabel.setVisible(true);
		} else if(e.getSource()==nextGeneracio) {
			
			game_over=!(ferGeneracio() && organismes>0);
			
			mostraTaulell();
			
			generacio++; nextGeneracio.setText("Generacio "+generacio);
	    	
			if(game_over || generacio==generacionsTotals) {
				nextGeneracio.setEnabled(false);
				nextGeneracio.setText("GGs");
			}
	    }
	}
	private void setEnabledDades(boolean b) {
		// fer un for q iteri per cada element
		numFiles.setEnabled(b);
		numColumnes.setEnabled(b);
		numGeneracions.setEnabled(b);
		numEssersVius.setEnabled(b);
		submitDades.setEnabled(b);
	}
	private boolean comprobacioDades() {
		try{
			files=Integer.parseInt(numFiles.getText());
			columnes=Integer.parseInt(numColumnes.getText());
			generacionsTotals=Integer.parseInt(numGeneracions.getText());
			organismesTotals=Integer.parseInt(numEssersVius.getText());
		
			if(organismesTotals>files*columnes || organismesTotals<1 || generacionsTotals<1 || files<1 || columnes<1)
				throw new Exception();
		} catch(Exception exc) {
			return false;
		} 
		return true;
	}
	private Casella[][] crearTaulell(){ 
		Casella[][] t= new Casella[files][columnes];
		for(int y=0;y<files; y++){
			for(int x=0;x<columnes; x++) 
				t[y][x]=new Casella();
		}
		return t;
	}

	
	public static void main(String[] args) {
		new JocVida();
	}
	
	///////////////////////////////////////////////////   JocVida   /////////////////////////////////////////////////////////
	
	private void posarVida(int N) {
		for(int i=0; i<N; i++) {
			int x,y;
			do {
				Random rand=new Random();
				y=rand.nextInt(files); x=rand.nextInt(columnes);
			} while (taulell[y][x].getEstat());
			taulell[y][x].setEstat(true);
		}
	}
	private void mostraTaulell() {
		for (int y = 0; y<files; y++) {
			for (int x = 0; x<columnes; x++) {
				panel_taulell.add(taulell[y][x], y,x);
			}
		}
	}
	private boolean ferGeneracio() {
		Casella[][] aux=crearTaulell();
		int igualtats=0;
		
		organismes=0;
		
		for (int y=0; y<taulell.length; y++) {
			for (int x=0; x<taulell[0].length; x++) {
				int num_veines=quantesVeines(y,x);			
				if (num_veines==3) aux[y][x].setEstat(true);
				else if (num_veines==2) aux[y][x].setEstat(taulell[y][x].getEstat());
				// per defecte ja estan mortes.

				if(taulell[y][x].getEstat()==aux[y][x].getEstat()) igualtats++;
				if(aux[y][x].getEstat()) organismes++;
				
			}
		}
		copiar(aux);
		
		return( igualtats!=files*columnes );
	}
	private int quantesVeines(int fil, int col) {
		int veines=0;
		
		for(int y=fil-1; y<(fil+2); y++) {
			if(y>=0 && y<taulell.length) {
				for(int x=col-1; x<(col+2); x++) {
					if(x>=0 && x<taulell[0].length) {
						if (taulell[y][x].getEstat() && !(x==col && y==fil)) veines++;
					}
				}
			}
		}

		return veines;
	}
	private void copiar(Casella[][] origen) {
		for (int y = 0; y<origen.length; y++) {
			for (int x = 0; x<origen[y].length; x++) {
				taulell[y][x].setEstat(origen[y][x].getEstat());
			}
		}
	}
}