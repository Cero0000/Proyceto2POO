/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;


public class PanelTablero extends JPanel implements Vista {

  
   private TableroOtelo tablero;
   public final static int DIMENSION_CASILLA = 50;
   private ArrayList<Line2D.Float> lineasDelimitadoras;
   public PanelTablero(TableroOtelo tablero) {
      this.setTablero(tablero);
      lineasDelimitadoras = new ArrayList<Line2D.Float>();
 Dimension dimension = new Dimension(tablero.getColumnas() * DIMENSION_CASILLA, tablero.getFilas() * DIMENSION_CASILLA);
      setPreferredSize(dimension);
      crearLineasDelimitadoras();
   }

   private void crearLineasDelimitadoras() {
      Line2D.Float linea;
      int xp, yp, xl, yl;

 
      for (int columna = 0; columna < tablero.getColumnas() + 1; columna++) {
         xp = columna * DIMENSION_CASILLA;
         yp = 0;
         xl = xp;
         yl = tablero.getFilas() * DIMENSION_CASILLA;
         linea = new Line2D.Float(xp, yp, xl, yl);

 
         lineasDelimitadoras.add(linea);
      }

    
      for (int fila = 0; fila < tablero.getFilas() + 1; fila++) {
       
         xp = 0;
         yp = fila * DIMENSION_CASILLA;
         xl = tablero.getFilas() * DIMENSION_CASILLA;
         yl = yp;
         linea = new Line2D.Float(xp, yp, xl, yl);

    
         lineasDelimitadoras.add(linea);
      }
   }


   @Override
   public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;

      g2.setColor(Color.WHITE);
      for (Line2D.Float linea : lineasDelimitadoras) {
         g2.draw(linea);
      }

      
      Ellipse2D.Float circulo;
      int xSupIzq;
      int ySupIzq;

     

      for (int fila = 0; fila < tablero.getFilas(); fila++) {
         for (int columna = 0; columna < tablero.getColumnas(); columna++) {
            xSupIzq = columna * DIMENSION_CASILLA + 5;
            ySupIzq = fila * DIMENSION_CASILLA + 5;
            if (tablero.getJugador(fila, columna) == 'b') {
               circulo = new Ellipse2D.Float(xSupIzq, ySupIzq, DIMENSION_CASILLA - 10, DIMENSION_CASILLA - 10);
               g2.setColor(Color.WHITE);
               g2.fill(circulo);
            }
            if (tablero.getJugador(fila, columna) == 'n') {
               circulo = new Ellipse2D.Float(xSupIzq, ySupIzq, DIMENSION_CASILLA - 10, DIMENSION_CASILLA - 10);
               g2.setColor(Color.BLACK);
               g2.fill(circulo);
            }
         }
      }
   }

   
   public void modelChanged() {

      this.repaint();
   }

  
   private void setTablero(TableroOtelo tablero) {
      if (tablero != null) {
         if (this.tablero != null) {
            this.tablero.removeVista(this);
         }
         this.tablero = tablero;
         this.tablero.addVista(this);
         this.repaint();
      }
   }

   
   public void restart(TableroOtelo tablero) {
      setTablero(tablero);
   }
}
