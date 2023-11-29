
package othello;

import java.io.*;


public class EntradaSalida {

  
   public void guardarPartida(File f, Partida partida) {
      ObjectOutputStream oos;
      try {
         oos = new ObjectOutputStream(new FileOutputStream(f + ".otg"));
         oos.writeObject(partida);
         oos.close();
      }
      catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }

   
   public Partida cargarPartida(File f) {
      Partida partida = null;
      if (f != null) {
         try {
            ObjectInputStream ois;

            ois = new ObjectInputStream(new FileInputStream(f));
            partida = (Partida) ois.readObject();
            ois.close();
            return partida;
         }
         catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
         }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
         }
      }
      return partida;
   }

   
   public Jugador cargarJugador(File file) {
      if (file != null) {
         ObjectInputStream ois;
         Jugador jugador = null;
         try {
            ois = new ObjectInputStream(new FileInputStream(file));
            jugador = (Jugador) ois.readObject();
            ois.close();
         }
         catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
         }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
         }
         return jugador;
      }
      return null;
   }

   
   public void guardarJugador(File file, Jugador jugador) {
      if (jugador != null) {
         File archivoSalida = new File(file + ".otp");
         ObjectOutputStream oos;
         try {
            oos = new ObjectOutputStream(new FileOutputStream(archivoSalida));
            oos.writeObject(jugador);
            oos.close();
         }
         catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }

}
