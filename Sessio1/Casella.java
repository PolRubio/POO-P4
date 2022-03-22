package Sessio1;

import javax.swing.*;
import java.awt.*;


public class Casella extends JPanel {
    public Casella(){
        setBackground(Color.black);
        // border necesari?
    }

    public boolean getEstat(){ return !(getBackground().equals(Color.black)); } // 1=viu;0=mort;
    public void alternarEstat(){
        if(getEstat()) setBackground(Color.black);
        else setBackground(Color.white);
    }
    // public void setEstat() {} ???
}