
package othello;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Estadisticas extends JDialog implements ActionListener{


   private Jugador jugador;

   private JLabel partidasJugadas;
   private JLabel partidasGanadas;
   private JLabel partidasPerdidas;
   private JLabel maxPuntuacion;
   
   private JButton cerrar;
   private JButton reiniciarEstadisticas;
   


   
   public Estadisticas(Jugador jugador){
      this.jugador = jugador;

     
      partidasJugadas = new JLabel("  Nº de partidas jugadas: "+jugador.getPartidasJugadas()+"  ");
      partidasGanadas = new JLabel("  Nº de partidas ganadas: "+jugador.getPartidasGanadas()+"  ");
      partidasPerdidas = new JLabel("  Nº de partidas perdidas: "+jugador.getPartidasPerdidas()+"  ");
      maxPuntuacion = new JLabel("  Maxima puntuacion: "+jugador.getMaxPuntuacion());

      cerrar = new JButton("Cerrar");
      reiniciarEstadisticas = new JButton("Reiniciar Estadisticas");
      cerrar.addActionListener(this);
      
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      panel.add(cerrar);
      panel.add(reiniciarEstadisticas);

      this.add(partidasJugadas);
      this.add(partidasGanadas);
      this.add(partidasPerdidas);
      this.add(maxPuntuacion);
      this.add(panel);

      this.setLayout(new GridLayout(0,1));
      this.setLocation(100, 100);
      this.setTitle("Estadisticas de "+jugador.getNombre());
      this.setResizable(true);
      this.setModal(true);

      this.pack();
      this.setVisible(false);

   }

   
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == cerrar){
         this.setVisible(false);
      }
      if(e.getSource() == reiniciarEstadisticas){
         System.out.println("Ba");
         jugador.reiniciarDatos();
         System.out.println("Bu");
         this.setVisible(false);
      }
   }



}
