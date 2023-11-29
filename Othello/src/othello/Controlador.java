package othello;



import java.awt.event.*;

public class Controlador implements MouseListener {

 
   private PanelTablero panelControlado;
   private TableroOtelo tablero;
   private JuegoOtelo juegoOtelo;
   private int dificultad;
   private boolean minimaxActivo;

 
   public Controlador(PanelTablero panelControlado, TableroOtelo tablero, int dificultad, JuegoOtelo juego) {
      this.panelControlado = panelControlado;
      this.tablero = tablero;
      this.juegoOtelo = juego;
      this.minimaxActivo=juego.getMinimaxActivo();
      this.dificultad=dificultad;
   }

   
   public void mouseClicked(MouseEvent evento) {

      if (minimaxActivo && tablero.getTurno() == 'b')
      {
         return;
      }
      try {
    
         int posx = evento.getX();
         int posy = evento.getY();
         int fila = posy / PanelTablero.DIMENSION_CASILLA;
         int columna = posx / PanelTablero.DIMENSION_CASILLA;
         Movimiento movimiento = new Movimiento(fila, columna);

         tablero.situarFicha(movimiento);



         if (!tablero.hayMovimientosDisponibles()) {
            System.out.println("Blancas no pueden mover y pasan turno");
            tablero.pasarTurno();
            if (!tablero.hayMovimientosDisponibles()) {
              
               tablero.pasarTurno();
               juegoOtelo.gameFinished();
               return;
            }
         }


         if (minimaxActivo && tablero.getTurno() == 'b') {
            
            Minimax minimax = new Minimax(this.dificultad, tablero, this);
            minimax.start();
         }
      }
      catch (NullPointerException e) {
         System.err.println(e);
      }
   }

  
   public void mouseEntered(MouseEvent evento) {
   }

   public void mouseExited(MouseEvent evento) {
   }

  
   public void mousePressed(MouseEvent evento) {
   }

   public void mouseReleased(MouseEvent evento) {
   }

  
   public void movimientoMinimax(Movimiento movimiento){
      tablero.situarFicha(movimiento);
      if (!tablero.hayMovimientosDisponibles()) {
            tablero.pasarTurno();
            if (!tablero.hayMovimientosDisponibles()) {
               juegoOtelo.gameFinished();
            }
         }
   }

   
   public void restart(TableroOtelo tablero, int dificultad) {
      this.tablero = tablero;
      this.dificultad = dificultad;
   }

   
   public void setMinimaxActivo(boolean activo){
      this.minimaxActivo=activo;
   }

   
   public void setDificultad(int dificultad){
      this.dificultad=dificultad;
   }
}
