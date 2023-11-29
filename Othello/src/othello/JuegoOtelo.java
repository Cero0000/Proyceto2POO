/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

public class JuegoOtelo extends JFrame {

   
   private TableroOtelo tableroJuego;
   private PanelTablero panelTablero;
   private Opciones opcionesDeJuego;
   private Controlador controlador;
   private Footer footer;
   private EntradaSalida es;
   private Jugador jugador1,  jugador2;
   private Estadisticas estadisticas;
   private final int DIFICULTAD_BAJA = 2;
   private final int DIFICULTAD_MEDIA = 3;
   private final int DIFICULTAD_ALTA = 4;
   private boolean minimaxActivo;
   JMenuBar barraMenu;

   JMenu archivo, opciones, dificultad, menuEstadisticas, menuAyuda;
   JMenuItem nuevoJuego, guardarPartida, cargarPartida, salir, opcionesJuego, puntuacionesJugador1,
      puntuacionesJugador2, ayuda;
   JRadioButtonMenuItem baja, media, alta;
   ButtonGroup group;
   JDialog partidaTerminada, ventanaAyuda;
   JLabel partidaFinalizada;
   JTextArea instruccionesJuego;

   
   public JuegoOtelo() {

      opcionesDeJuego=new Opciones(JuegoOtelo.this,false);

      setTitle("Othello");

      tableroJuego = new TableroOtelo();
     
      this.panelTablero = new PanelTablero(this.tableroJuego);

      minimaxActivo=true;

      this.controlador = new Controlador(panelTablero, tableroJuego,DIFICULTAD_BAJA , this);
      panelTablero.addMouseListener(controlador);


      this.footer = new Footer(this.tableroJuego);

      this.es = new EntradaSalida();

      jugador1 = null;
      jugador2 = null;


      getContentPane().add(inicializarMenus(), BorderLayout.BEFORE_FIRST_LINE);
      getContentPane().add(panelTablero, BorderLayout.LINE_START);
      footer.setEditable(false);
      getContentPane().add(footer, BorderLayout.SOUTH);

      getContentPane().setBackground(Color.cyan);

      pack();
      setVisible(true);
      setResizable(false);
      setLocation(150, 50);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

  
   private JComponent inicializarMenus() {

      barraMenu = new JMenuBar();

 
      archivo = new JMenu("Archivo");
      opciones = new JMenu("Opciones");
      dificultad = new JMenu("Dificultad");
      menuEstadisticas = new JMenu("Estadisticas");
      menuEstadisticas.setEnabled(false);
      menuAyuda = new JMenu("Ayuda");

    
      nuevoJuego = new JMenuItem("Nuevo Juego");
      nuevoJuego.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            nuevoJuego();
         }
      });

      guardarPartida = new JMenuItem("Guardar Partida");
      guardarPartida.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            int dificultad = 0;
            if(baja.isSelected()){
               dificultad = DIFICULTAD_BAJA;
            }
            else{
               if(media.isSelected()){
               dificultad = DIFICULTAD_MEDIA;
               }
               else{
                  dificultad = DIFICULTAD_ALTA;
               }
            }
            Partida partidaAguardar = new Partida(JuegoOtelo.this.tableroJuego, dificultad);
            es.guardarPartida(guardarPartida(), partidaAguardar);
         
         }
      });


      cargarPartida = new JMenuItem("Cargar Partida");
      cargarPartida.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            Partida partidaGuardada = es.cargarPartida(cargarPartida());
            if (partidaGuardada != null) {
               if (partidaGuardada.getDificultad() == DIFICULTAD_BAJA) {
                  baja.setSelected(true);
               }
               else {
                  if (partidaGuardada.getDificultad() == DIFICULTAD_MEDIA) {
                     media.setSelected(true);
                  }
                  else {
                     alta.setSelected(true);
                  }
               }
               JuegoOtelo.this.tableroJuego = partidaGuardada.getTablero();
               panelTablero.restart(tableroJuego);
               controlador.restart(tableroJuego, partidaGuardada.getDificultad());
               panelTablero.addMouseListener(controlador);
               footer.restart(tableroJuego);
            }
         }
      });

      salir = new JMenuItem("Salir");
      salir.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            System.exit(1);
         }
      });

    
      archivo.add(nuevoJuego);
      archivo.add(guardarPartida);
      archivo.add(cargarPartida);
      archivo.add(salir);

      
      opcionesJuego = new JMenuItem("Opciones");
      opcionesJuego.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            opcionesDeJuego.setVisible(true);
         }
      });

      
      group = new ButtonGroup();

      baja = new JRadioButtonMenuItem("Baja", true);
      baja.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            controlador.setDificultad(DIFICULTAD_BAJA);
         }
      });

      media = new JRadioButtonMenuItem("Media");
      media.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            controlador.setDificultad(DIFICULTAD_MEDIA);
         }
      });

      alta = new JRadioButtonMenuItem("Alta");
      alta.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            controlador.setDificultad(DIFICULTAD_ALTA);
         }
      });

      //Elementos del menu estadisticas
      puntuacionesJugador1 = new JMenuItem("Jugador 1");
      puntuacionesJugador1.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            JuegoOtelo.this.estadisticas = new Estadisticas(jugador1);
            estadisticas.setVisible(true);
         }
      });
      puntuacionesJugador2 = new JMenuItem("Jugador 2");
      puntuacionesJugador2.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            JuegoOtelo.this.estadisticas = new Estadisticas(jugador2);
            estadisticas.setVisible(true);
         }
      });

      
      menuEstadisticas.add(puntuacionesJugador1);
      menuEstadisticas.add(puntuacionesJugador2);

      //Elementos del menu Ayuda
      ayuda = new JMenuItem("Ayuda");
      ayuda.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ventanaAyuda = new JDialog(JuegoOtelo.this, true);
            ventanaAyuda.setTitle("Instrucciones");
            instruccionesJuego = new JTextArea("Empezando por quien lleva las fichas " +
               "negras los jugadores deben hacer un movimiento por turnos, a menos que " +
               "no puedan hacer ninguno, pasando en ese caso el turno al jugador contrario. " +
               "El movimiento consiste en colocar una ficha de forma que flanquee una o varias " +
               "fichas del color contrario y voltear esas fichas para que pasen a mostrar el " +
               "propio color.\n\n Mas informacion: ");
            instruccionesJuego.setLineWrap(true);
            instruccionesJuego.setWrapStyleWord(true);
            instruccionesJuego.setEditable(false);
            instruccionesJuego.setFont(getFont().deriveFont(Font.BOLD));
            instruccionesJuego.setMargin(new Insets(10, 10, 10, 0));
            ventanaAyuda.setLocation(100, 100);
            ventanaAyuda.setBackground(Color.LIGHT_GRAY);
            ventanaAyuda.add(instruccionesJuego);
            System.out.println(instruccionesJuego.getTabSize());
            ventanaAyuda.setSize(400,190);
            ventanaAyuda.setResizable(false);
            ventanaAyuda.setVisible(true);
         }
      });

      
      menuAyuda.add(ayuda);

      
      dificultad.add(baja);
      group.add(baja);
      dificultad.add(media);
      group.add(media);
      dificultad.add(alta);
      group.add(alta);

      //Se anaden y se organizan los menus
      opciones.add(opcionesJuego);
      opciones.add(dificultad);
      barraMenu.add(archivo);
      barraMenu.add(opciones);
      barraMenu.add(menuEstadisticas);
      barraMenu.add(menuAyuda);

      return barraMenu;
   }

   
   private void nuevoJuego() {
      tableroJuego = new TableroOtelo();
      panelTablero.restart(tableroJuego);
      
    
      if(panelTablero.getMouseListeners().length==0){
         panelTablero.addMouseListener(controlador);
      }
      footer.restart(tableroJuego);

      int dificultadJuego = 0;
      if (group.getSelection() == baja) {
         dificultadJuego = DIFICULTAD_BAJA;
      }
      else {
         if (group.getSelection() == media) {
            dificultadJuego = DIFICULTAD_MEDIA;
         }
         else {
            dificultadJuego = DIFICULTAD_ALTA;
         }
      }
      controlador.restart(tableroJuego, dificultadJuego);
   }

   
   private File guardarPartida() {
      JFileChooser jfc = new JFileChooser();
   
      jfc.setFileFilter(new FileFilter() {

         @Override
         public boolean accept(File f) {
            if (f.getName().substring(f.getName().length() - 4, f.getName().length()).equals(".otg")) {
               return true;
            }
            if (f.isDirectory()) {
               return true;
            }
            return false;
         }

         @Override
         public String getDescription() {
            return ".otg";
         }
      });

      int returnVal = jfc.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         return jfc.getSelectedFile();
      }
      return null;
   }

   
   private File cargarPartida() {
      JFileChooser jfc = new JFileChooser();
      jfc.setFileFilter(new FileFilter() {

         @Override
         public boolean accept(File f) {
            if (f.getName().substring(f.getName().length() - 4, f.getName().length()).equals(".otg")) {
               return true;
            }
            if(f.isDirectory()){
               return true;
            }
            return false;
         }

         @Override
         public String getDescription() {
            return ".otg";
         }
      });
      int returnVal = jfc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         return jfc.getSelectedFile();
      }
      return null;
   }

   
   public void setJugador1(Jugador jugador) {
      this.jugador1 = jugador;
      if (jugador1 != null) {
         menuEstadisticas.setEnabled(true);
         puntuacionesJugador1.setText(jugador1.getNombre());
         puntuacionesJugador1.setVisible(true);

         if (jugador2 != null) {
            puntuacionesJugador2.setVisible(true);
         }

         if (jugador2 == null) {
            puntuacionesJugador2.setVisible(false);
         }
      }
   }

  
   public void setJugador2(Jugador jugador) {
      this.jugador2 = jugador;
      if (jugador2 != null) {
         menuEstadisticas.setEnabled(true);
         puntuacionesJugador2.setText(jugador2.getNombre());
         puntuacionesJugador2.setVisible(true);

         if (jugador1 != null) {
            puntuacionesJugador1.setVisible(true);
         }

         if (jugador1 == null) {
            puntuacionesJugador1.setVisible(false);
         }
      }
   }

   
   public void gameFinished() {
      //Una vez terminado el juego no queremos que se detecten mas pulsaciones
      panelTablero.removeMouseListener(controlador);

      String ganador;
      if (tableroJuego.getNumBlancas() > tableroJuego.getNumNegras()) {
         ganador = "Ganan las blancas";
      }
      if (tableroJuego.getNumBlancas() < tableroJuego.getNumNegras()) {
         ganador = "Ganan las negras";
      }
      else {
         ganador = "La partida queda empate";
      }

      partidaTerminada = new JDialog(this, true);

      partidaFinalizada = new JLabel("La partida ha finalizado. " + ganador);
      partidaTerminada.setLayout(new GridLayout(0, 1));
      partidaTerminada.add(partidaFinalizada);

      JPanel panel = new JPanel();

      JButton aceptar = new JButton("Aceptar");
      panel.add(aceptar);
      aceptar.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {

            partidaTerminada.setVisible(false);

            if (jugador1 == null || (!minimaxActivo && jugador2 == null)) {
               Opciones op = new Opciones(JuegoOtelo.this, true);
               op.setVisible(true);
            }
            if (jugador1 != null) {
               sumarPuntuacionJugador1();
               JFileChooser jfc = new JFileChooser();
               //La extension tiene que coincidir con otp (otelo player)
               jfc.setFileFilter(new FileFilter() {

                  @Override
                  public boolean accept(File f) {
                     if (f.getName().substring(f.getName().length() - 4, f.getName().length()).equals(".otp")) {
                        return true;
                     }
                     if (f.isDirectory()) {
                        return true;
                     }
                     return false;
                  }

                  @Override
                  public String getDescription() {
                     return ".otp";
                  }
               });

               jfc.setDialogTitle("Guardar Jugador 1");
               int returnVal = jfc.showSaveDialog(JuegoOtelo.this);
               if (returnVal == JFileChooser.APPROVE_OPTION) {
                  es.guardarJugador(jfc.getSelectedFile(), jugador1);
               }
            }
            if (jugador2 != null) {
               sumarPuntuacionJugador2();
               JFileChooser jfc = new JFileChooser();

              
               jfc.setFileFilter(new FileFilter() {

                  @Override
                  public boolean accept(File f) {
                     if (f.getName().substring(f.getName().length() - 4, f.getName().length()).equals(".otp")) {
                        return true;
                     }
                     if (f.isDirectory()) {
                        return true;
                     }
                     return false;
                  }

                  @Override
                  public String getDescription() {
                     return ".otp";
                  }
               });
               jfc.setDialogTitle("Guardar Jugador 2");
               int returnVal = jfc.showSaveDialog(JuegoOtelo.this);
               if (returnVal == JFileChooser.APPROVE_OPTION) {
                  es.guardarJugador(jfc.getSelectedFile(), jugador2);
               }
            }
        
         }
      });

      partidaTerminada.add(panel);
      partidaTerminada.pack();
      partidaTerminada.setVisible(true);

   }

   public void setMinimaxActivo(boolean activo) {
      this.minimaxActivo = activo;
      controlador.setMinimaxActivo(activo);
   }

  
   public boolean getMinimaxActivo() {
      return this.minimaxActivo;
   }

   
   public Jugador getJugador1(){
      return this.jugador1;
   }

   
   public Jugador getJugador2(){
      return this.jugador2;
   }

   
   public void sumarPuntuacionJugador1() {
      if (jugador1 != null) {
         jugador1.aumentarPartidasJugadas();
         jugador1.setMaxPuntuacion(tableroJuego.getNumNegras());
         if (tableroJuego.getNumNegras() > tableroJuego.getNumBlancas()) {
            jugador1.aumentarPartidasGanadas();
         }
         if(tableroJuego.getNumNegras() < tableroJuego.getNumBlancas()){
            jugador1.aumentarPartidasPerdidas();
         }
      }
   }

   
   public void sumarPuntuacionJugador2() {
      if (jugador2 != null) {
         jugador2.aumentarPartidasJugadas();
         jugador2.setMaxPuntuacion(tableroJuego.getNumBlancas());
         if (tableroJuego.getNumBlancas() > tableroJuego.getNumNegras()) {
            jugador2.aumentarPartidasGanadas();
         }
         if(tableroJuego.getNumBlancas() < tableroJuego.getNumNegras()){
            jugador2.aumentarPartidasPerdidas();
         }
      }
   }

  
   public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {

         public void run() {
            new JuegoOtelo();
         }
      });
   }


}
