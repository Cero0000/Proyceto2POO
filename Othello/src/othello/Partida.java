/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

import java.io.Serializable;


public class Partida implements Serializable{

  
   private TableroOtelo tablero;

  
   private int dificultad;

   
   public Partida(TableroOtelo tablero, int dificultad){
      this.tablero = tablero;
      this.dificultad = dificultad;
   }

  
   public TableroOtelo getTablero(){
      return this.tablero;
   }

   
   public int getDificultad(){
      return this.dificultad;
   }
}
