package othello;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;


public class Footer extends JTextArea implements Vista{

    TableroOtelo tablero;

   
    public Footer(TableroOtelo tablero){

        setFont(getFont().deriveFont(Font.BOLD));
        setBackground(new Color(238,238,238));
        this.setTablero(tablero);

    }

    
    public void refrescar(){
        String turno;
        if(tablero.getTurno() == 'b')
            turno = "blancas";
        else
            turno = "negras";
        setText("Juegan las "+turno+"Negras: "+tablero.getNumNegras()+"  Blancas: "+tablero.getNumBlancas());
    }

    
    private  void setTablero(TableroOtelo tablero) {
        if (tablero != null) {
            if (this.tablero != null) {
                this.tablero.removeVista(this);
            }
            this.tablero = tablero;
            this.tablero.addVista(this);
            this.refrescar();
        }
    }

   
    public void modelChanged() {
        this.refrescar();
    }

   
    public void restart(TableroOtelo tablero){
        setTablero(tablero);
    }
}
