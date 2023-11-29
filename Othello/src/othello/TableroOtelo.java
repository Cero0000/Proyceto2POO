/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class TableroOtelo extends Tablero implements Serializable {

 
   private static final long serialVersionUID = 2;
   private static final char BLANCA = 'b';
   private static final char NEGRA = 'n';
   private char turno;
   private transient ArrayList<Vista> vistas;

  
   public TableroOtelo() {
     
      super(8, 8);
      tablero[3][3] = BLANCA;
      tablero[4][4] = BLANCA;
      tablero[3][4] = NEGRA;
      tablero[4][3] = NEGRA;
      turno = NEGRA;
      this.vistas = new ArrayList<Vista>();
   }

   
   private char getJugadorContrario() {
      if (turno == BLANCA) {
         return NEGRA;
      }
      else {
         return BLANCA;
      }
   }

   
   public char getTurno() {
      if (turno == BLANCA) {
         return BLANCA;
      }
      else {
         return NEGRA;
      }
   }

  
   public void pasarTurno() {
      if (turno == NEGRA) {
         turno = BLANCA;
      }
      else {
         turno = NEGRA;
      }
      this.notificarVistas();
   }

 
   public boolean comprobarMovimientoArriba(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (super.casillaOcupada(movimiento) || (fila <= 1)) {
         return false;
      }
   
      if (tablero[fila - 1][columna] != jugadorContrario) {
         return false;
      }
     
      int i = fila - 1;
      while ((i > 0) && (tablero[i][columna] == jugadorContrario)) {
         i--;
      }
      if (tablero[i][columna] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

   
   public boolean comprobarMovimientoAbajo(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (super.casillaOcupada(movimiento) || (fila >= 6)) {
         return false;
      }
      if (tablero[fila + 1][columna] != jugadorContrario) {
         return false;
      }

      int i = fila + 1;
      while ((i < filas - 1) && (tablero[i][columna] == jugadorContrario)) {
         i++;
      }
      if (tablero[i][columna] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

  
   public boolean comprobarMovimientoIzquierda(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (super.casillaOcupada(movimiento) || (columna <= 1)) {
         return false;
      }

      if (tablero[fila][columna - 1] != jugadorContrario) {
         return false;
      }

      int i = columna - 1;
      while ((i > 0) && (tablero[fila][i] == jugadorContrario)) {
         i--;
      }

      if (tablero[fila][i] == turno) {
         return true;
      }
      else {
         return false;
      }
   }


   public boolean comprobarMovimientoDerecha(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (super.casillaOcupada(movimiento) || (columna >= 6)) {
         return false;
      }

      if (tablero[fila][columna + 1] != jugadorContrario) {
         return false;
      }
      int i = columna + 1;
      while ((i < columnas - 1) && (tablero[fila][i] == jugadorContrario)) {
         i++;
      }
      if (tablero[fila][i] == turno) {
         return true;
      }
      else {
         return false;
      }
   }


   public boolean comprobarMovimientoArribaDerecha(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();

      if (super.casillaOcupada(movimiento) || (columna >= 6) || (fila <= 1)) {
         return false;
      }

      if (tablero[fila - 1][columna + 1] != jugadorContrario) {
         return false;
      }

      int i = fila - 1;
      int j = columna + 1;
      while ((i > 0) && (j < columnas - 1) && (tablero[i][j] == jugadorContrario)) {
         i--;
         j++;
      }

      if (tablero[i][j] == turno) {
         return true;
      }
      else {
         return false;
      }
   }


   public boolean comprobarMovimientoArribaIzquierda(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (super.casillaOcupada(movimiento) || (columna <= 1) || (fila <= 1)) {
         return false;
      }
      if (tablero[fila - 1][columna - 1] != jugadorContrario) {
         return false;
      }
      int i = fila - 1;
      int j = columna - 1;
      while ((i > 0) && (j > 0) && (tablero[i][j] == jugadorContrario)) {
         i--;
         j--;
      }
      if (tablero[i][j] == turno) {
         return true;
      }
      else {
         return false;
      }
   }


   public boolean comprobarMovimientoAbajoDerecha(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (super.casillaOcupada(movimiento) || (columna >= 6) || (fila >= 6)) {
         return false;
      }
      if (tablero[fila + 1][columna + 1] != jugadorContrario) {
         return false;
      }
      int i = fila + 1;
      int j = columna + 1;
      while ((i < filas - 1) && (j < columnas - 1) && (tablero[i][j] == jugadorContrario)) {
         i++;
         j++;
      }
      if (tablero[i][j] == turno) {
         return true;
      }
      return false;
   }

   public boolean comprobarMovimientoAbajoIzquierda(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (super.casillaOcupada(movimiento) || (columna <= 1) || (fila >= 6)) {
         return false;
      }
      if (tablero[fila + 1][columna - 1] != jugadorContrario) {
         return false;
      }
      int i = fila + 1;
      int j = columna - 1;
      while ((i < filas - 1) && (j > 0) && (tablero[i][j] == jugadorContrario)) {
         i++;
         j--;
      }
      if (tablero[i][j] == turno) {
         return true;
      }
      else {
         return false;
      }
   }


   private boolean[] comprobarMovimientoCorrecto(Movimiento movimiento) {
      boolean[] movimientosValidos = new boolean[8];
      movimientosValidos[0] = comprobarMovimientoArriba(movimiento);
      movimientosValidos[1] = comprobarMovimientoAbajo(movimiento);
      movimientosValidos[2] = comprobarMovimientoDerecha(movimiento);
      movimientosValidos[3] = comprobarMovimientoIzquierda(movimiento);
      movimientosValidos[4] = comprobarMovimientoArribaDerecha(movimiento);
      movimientosValidos[5] = comprobarMovimientoArribaIzquierda(movimiento);
      movimientosValidos[6] = comprobarMovimientoAbajoDerecha(movimiento);
      movimientosValidos[7] = comprobarMovimientoAbajoIzquierda(movimiento);
      return movimientosValidos;
   }

 
   public boolean esPosibleMovimiento(Movimiento movimiento) {
      if (comprobarMovimientoArriba(movimiento)) {
         return true;
      }
      if (comprobarMovimientoAbajo(movimiento)) {
         return true;
      }
      if (comprobarMovimientoDerecha(movimiento)) {
         return true;
      }
      if (comprobarMovimientoIzquierda(movimiento)) {
         return true;
      }
      if (comprobarMovimientoArribaDerecha(movimiento)) {
         return true;
      }
      if (comprobarMovimientoArribaIzquierda(movimiento)) {
         return true;
      }
      if (comprobarMovimientoAbajoDerecha(movimiento)) {
         return true;
      }
      if (comprobarMovimientoAbajoIzquierda(movimiento)) {
         return true;
      }
      return false;
   }

   private void convertirFichasArriba(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila - 1;
      while (tablero[i][columna] != turno) {
         tablero[i][columna] = turno;
         i--;
      }
   }


   private void convertirFichasAbajo(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila + 1;
      while (tablero[i][columna] != turno) {
         tablero[i][columna] = turno;
         i++;
      }
   }

   private void convertirFichasDerecha(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int j = columna + 1;
      while (tablero[fila][j] != turno) {
         tablero[fila][j] = turno;
         j++;
      }
   }


   private void convertirFichasIzquierda(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int j = columna - 1;
      while (tablero[fila][j] != turno) {
         tablero[fila][j] = turno;
         j--;
      }
   }

   private void convertirFichasArribaDerecha(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila - 1;
      int j = columna + 1;
      while (tablero[i][j] != turno) {
         tablero[i][j] = turno;
         i--;
         j++;
      }
   }


   private void convertirFichasArribaIzquierda(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila - 1;
      int j = columna - 1;
      while (tablero[i][j] != turno) {
         tablero[i][j] = turno;
         i--;
         j--;
      }
   }


   private void convertirFichasAbajoDerecha(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila + 1;
      int j = columna + 1;
      while (tablero[i][j] != turno) {
         tablero[i][j] = turno;
         i++;
         j++;
      }
   }


   private void convertirFichasAbajoIzquierda(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila + 1;
      int j = columna - 1;
      while (tablero[i][j] != turno) {
         tablero[i][j] = turno;
         i++;
         j--;
      }
   }


   @Override
   void situarFicha(Movimiento movimiento) {
      boolean[] movimientoCorrecto = comprobarMovimientoCorrecto(movimiento);
      boolean movimientoRealizado = false;
      if (movimientoCorrecto[0]) {
         movimientoRealizado = true;
         convertirFichasArriba(movimiento);
      }
      if (movimientoCorrecto[1]) {
         movimientoRealizado = true;
         convertirFichasAbajo(movimiento);
      }
      if (movimientoCorrecto[2]) {
         movimientoRealizado = true;
         convertirFichasDerecha(movimiento);
      }
      if (movimientoCorrecto[3]) {
         movimientoRealizado = true;
         convertirFichasIzquierda(movimiento);
      }
      if (movimientoCorrecto[4]) {
         movimientoRealizado = true;
         convertirFichasArribaDerecha(movimiento);
      }
      if (movimientoCorrecto[5]) {
         movimientoRealizado = true;
         convertirFichasArribaIzquierda(movimiento);
      }
      if (movimientoCorrecto[6]) {
         movimientoRealizado = true;
         convertirFichasAbajoDerecha(movimiento);
      }
      if (movimientoCorrecto[7]) {
         movimientoRealizado = true;
         convertirFichasAbajoIzquierda(movimiento);
      }
      if (movimientoRealizado) {
         tablero[movimiento.getFila()][movimiento.getColumna()] = turno;
         pasarTurno();
         this.notificarVistas();
      }
      else {
      }
   }


   private void notificarVistas() {
      for (Vista vista : this.vistas) {
         vista.modelChanged();
      }
   }


   public void addVista(Vista vista) {
      this.vistas.add(vista);
   }


   public void removeVista(Vista vista) {
      this.vistas.remove(vista);
   }


   public void removeallVistas() {
      for (Vista vista : this.vistas) {
         this.vistas.remove(vista);
      }
   }


   @Override
   public TableroOtelo clone() {
      TableroOtelo tableroClonado = null;
      try {
         tableroClonado = (TableroOtelo) super.clone();
         tableroClonado.tablero = new char[this.filas][this.columnas];
         for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
               tableroClonado.tablero[i][j] = this.tablero[i][j];
            }
         }
      }
      catch (CloneNotSupportedException exception) {
         System.out.println(".....");
         exception.printStackTrace();
         System.exit(1);
      }
      return tableroClonado;
   }


   public boolean hayMovimientosDisponibles() {
      for (int i = 0; i < filas; i++) {
         for (int j = 0; j < columnas; j++) {
            Movimiento m = new Movimiento(i, j);
            if (esPosibleMovimiento(m)) {
               return true;
            }
         }
      }
      return false;
   }


   public ArrayList<Movimiento> calcularMovimientosPosibles() {
      ArrayList<Movimiento> movimientosPosibles = new ArrayList<Movimiento>();
      for (int i = 0; i < tablero.length; i++) {
         for (int j = 0; j < tablero.length; j++) {
            Movimiento m = new Movimiento(i, j);
            if (esPosibleMovimiento(m)) {
               movimientosPosibles.add(m);
            }
         }
      }
      return movimientosPosibles;
   }

   public int score() {
      int score = 0;
      for (int i = 0; i < tablero.length; i++) {
         for (int j = 0; j < tablero.length; j++) {
            if (tablero[i][j] == turno) {
               score++;
            }
         }
      }
      return score;
   }


   public int getNumBlancas() {
      int blancas = 0;
      for (int i = 0; i < filas; i++) {
         for (int j = 0; j < columnas; j++) {
            if (tablero[i][j] == 'b') {
               blancas++;
            }
         }
      }
      return blancas;
   }

   public int getNumNegras() {
      int negras = 0;
      for (int i = 0; i < filas; i++) {
         for (int j = 0; j < columnas; j++) {
            if (tablero[i][j] == 'n') {
               negras++;
            }
         }
      }
      return negras;
   }


   private void writeObject(ObjectOutputStream out) throws IOException {
      System.out.println("out");
      out.defaultWriteObject();
   }


   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.vistas = new ArrayList<Vista>();
   }
}
