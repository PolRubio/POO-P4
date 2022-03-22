package Sessio1;

import javax.swing.*;
import java.awt.*;

public class Casella extends JPanel{
	
	// Aquests dos atributs no calen, es per simplement per comoditat.
	// Es podrien canviar per els seus valors sense cap problema.
	Color colorMort=Color.white;
	Color colorViu=Color.black;
	
	public Casella(){
        setBackground(colorMort);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public boolean getEstat(){ return !(getBackground().equals(colorMort)); } // 1=viu;0=mort;
    public void setEstat(boolean val) { setBackground( val ? colorViu : colorMort); }
    public void alternarEstat(){
        if(getEstat()) setBackground(colorMort);
        else setBackground(colorViu);
    }
    
}