package othello;

import java.util.*;


public class Minimax extends Thread {

  
    private TableroOtelo tableroOtelo;
    private Controlador controlador;
    public int profundidadMaxima;
    public Minimax(int profundidadMaxima, TableroOtelo tablero, Controlador controlador) {
        this.profundidadMaxima = profundidadMaxima;
        tableroOtelo=tablero;
        this.controlador=controlador;
    }

    
    public Movimiento getBestMove() {
        Movimiento mejorMovimiento = null;
        int mejorScore = 0;
        List<Movimiento> movimientos;
        movimientos = tableroOtelo.calcularMovimientosPosibles();
        TableroOtelo nuevoTablero;
        int profundidad = 0;

        for (Movimiento movimiento : movimientos) {
            nuevoTablero = tableroOtelo.clone();
            nuevoTablero.situarFicha(movimiento);
            int score = getBestMoveRec(nuevoTablero, profundidad + 1);
            if (score >= mejorScore) {
                mejorMovimiento = movimiento;
                mejorScore = score;
            }
        }
        if(mejorMovimiento == null){
           System.out.println("Minimax movimiento null");
           System.out.println(movimientos.size());
        }

        return mejorMovimiento;
    }

    
    private int getBestMoveRec(TableroOtelo tableroOtelo, int profundidad) {
        if (profundidad == profundidadMaxima) {
            return tableroOtelo.score();
        } else {
            List<Movimiento> movimientos;
            movimientos = tableroOtelo.calcularMovimientosPosibles();
            TableroOtelo nuevoTablero;
            int scoreMin = Integer.MAX_VALUE;
            int scoreMax = 0;

            if (profundidad % 2 == 0) {

                for (Movimiento movimiento : movimientos) {
                    nuevoTablero = tableroOtelo.clone();
                    nuevoTablero.situarFicha(movimiento);
                    int score = getBestMoveRec(nuevoTablero, profundidad + 1);
                    if (score < scoreMin) {
                        scoreMin = score;
                    }

                }
                return scoreMin;
            } else {
                for (Movimiento movimiento : movimientos) {
                    nuevoTablero = tableroOtelo.clone();
                    nuevoTablero.situarFicha(movimiento);
                    int score = getBestMoveRec(nuevoTablero, profundidad + 1);
                    if (score > scoreMax) {
                        scoreMax = score;
                    }
                }
                return scoreMax;
            }

        }
    }

    
    @Override
    public void run() {
        Movimiento movimiento = getBestMove();
        this.controlador.movimientoMinimax(movimiento);
    }
}
