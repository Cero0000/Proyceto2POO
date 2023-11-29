/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;


public class Movimiento {

   private int fila; 
   private int columna;

  
   public Movimiento(int fila, int columna) {
      this.fila = fila;
      this.columna = columna;
   }

 
   public int getColumna() {
      return columna;
   }

   
   public int getFila() {
      return fila;
   }
}
