package othello;

import java.io.Serializable;


public abstract class Tablero implements Cloneable, Serializable {

   
   protected char tablero[][];
   protected int filas;
   protected int columnas;
   
   
   public Tablero(int filas, int columnas) {
      this.filas = filas;
      this.columnas = columnas;
      tablero = new char[filas][columnas];
      for (int i = 0; i < filas; i++) {
         for (int j = 0; j < columnas; j++) {
            tablero[i][j] = 0;
         }
      }
   }

 
   public int getFilas() {
      return filas;
   }

   public int getColumnas() {
      return columnas;
   }

   public char getJugador(int fila, int columna) {
      return tablero[fila][columna];
   }

  
   boolean casillaOcupada(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();

      if (fila >= filas || columna >= columnas) {
         return false;
      }
      boolean ocupada;
      if (tablero[fila][columna] == 0) {
         ocupada = false;
      }
      else {
         ocupada = true;
      }
      return ocupada;
   }

   
   abstract void situarFicha(Movimiento movimiento);

   void vaciarCasilla(Movimiento movimiento) {
      boolean ocupada = casillaOcupada(movimiento);
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (ocupada == true) {
         tablero[fila][columna] = 0;
      }
   }

  
   void vaciarTablero() {
      for (int i = 0; i < tablero.length; i++) {
         for (int j = 0; j < tablero.length; j++) {
            tablero[i][j] = 0;
         }
      }
   }
}
