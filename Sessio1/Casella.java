package Sessio1;

import javax.swing.*;

public class Casella extends JPanel{
	public Casella(){
        setbackground(Color.black);
        // border necesari?
    }

    public boolean getEstat()}{ return !(getBackground().equals(Color.black)) } // 1=viu;0=mort;
    public void alternarEstat(){
        if(getEstat()) setbackground(Color.black);
        else setbackground(Color.white);
    }
    // public void setEstat() {} ???
}
