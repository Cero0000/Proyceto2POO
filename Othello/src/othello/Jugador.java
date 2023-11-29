/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.io.Serializable;


public class Jugador implements Serializable {

  
   private String nombre;
 
   private String password;
   private int maxPuntuacion;
   private int partidasJugadas;
   private int partidasGanadas;
   private int partidasPerdidas;
   public Jugador(String nombre, String pwd) {
      this.nombre = nombre;
      password = pwd;
      maxPuntuacion = 0;
      partidasJugadas = 0;
      partidasGanadas = 0;
      partidasPerdidas = 0;
   }

   public String getNombre() {
      return this.nombre;
   }


   public void setNombre(String nombre) {
      this.nombre = nombre;
   }


   public boolean setPassword(String pwdActual, String pwdNuevo) {
      if (pwdActual.equals(password)) {
         this.password = pwdNuevo;
         return true;
      }
      else {
         return false;
      }
   }

   
   public int getMaxPuntuacion() {
      return this.maxPuntuacion;
   }

   
   public void setMaxPuntuacion(int puntuacion) {
      if (maxPuntuacion < puntuacion) {
         maxPuntuacion = puntuacion;
      }
   }

  
   public int getPartidasJugadas() {
      return this.partidasJugadas;
   }

 
   public int getPartidasGanadas() {
      return this.partidasGanadas;
   }

   
   public int getPartidasPerdidas() {
      return this.partidasPerdidas;
   }

  
   public void aumentarPartidasJugadas() {
      this.partidasJugadas++;
   }

   
   public void aumentarPartidasGanadas() {
      this.partidasGanadas++;
   }

   
   public void aumentarPartidasPerdidas() {
      this.partidasPerdidas++;
   }

  
   public void reiniciarDatos() {
      this.maxPuntuacion = 0;
      this.partidasGanadas = 0;
      this.partidasJugadas = 0;
      this.partidasPerdidas = 0;
   }
}
