package IKnowThatWord;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.TimerTask;
import java.util.Timer;
//2022985 - Robert Fernando Gil Trujillo
//2025176 - Esperanza Olivo Calderón
public class PanelWord extends JPanel{
    public static final int WIDTH=200;
    public static final int HEIGTH=200;
    private Border border;
    private JTextField words;
    private JButton yes,no;
    private ControlGame controlGame;
    private GUIGame guiGame;
    private GridBagConstraints constraints;
    private int randomNumber;
    private Font font;
    private Timer timer;
    MyTimerTask myTimerTask;
    private int counter;
    private  int reply;
    private JLabel score, level;
    private int scoreValue, levelValue;

    public ControlGame getControlGame(){return  controlGame;}

    public GUIGame getGuiGame() {
        return guiGame;
    }

    public void setControlGame(ControlGame controlGame) {this.controlGame = controlGame;}

    public PanelWord(ControlGame control) {
        this.controlGame = control;
       // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension size = new Dimension(150,150);
        setPreferredSize(size);
        setSize(size);
        setOpaque(false);

       // border = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        setBorder(null);
        printWords();

        scoreValue = controlGame.getTotalPoint();
        score = new JLabel("Puntaje: " +  scoreValue);
        score.setBounds(0, 50, 80, 30);
        score.setVisible(true);
        add(score);

        levelValue = controlGame.getLevels();
        level = new JLabel("Nivel: " + levelValue);
        level.setBounds(230, 50, 80, 30);
        level.setVisible(true);
        add(level);


    }

    public PanelWord(PanelWord newControl) {
        this.controlGame = newControl.controlGame;
        Dimension size = new Dimension(150,150);
        setPreferredSize(size);
        setSize(size);
        border = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        setBorder(null);
        setOpaque(false);
        printWords();

        scoreValue = controlGame.getTotalPoint();
        score = new JLabel("Puntaje: " +  scoreValue);
        score.setBounds(0, 50, 80, 30);
        score.setVisible(true);
        add(score);

        levelValue = controlGame.getLevels();
        level = new JLabel("Nivel: " + levelValue);
        level.setBounds(230, 50, 80, 30);
        level.setVisible(true);
        add(level);


    }



    private void printWords(){

        if(controlGame.getLevels() == 1){

            font = new Font("Agency FB", Font.BOLD, 25);

            words = new JTextField(controlGame.getWords(1).get(0).toUpperCase());
            words.setEditable(false);
            words.setBorder(null);
            words.setFont(font);
            words.setOpaque(false);
            words.setSize(200, 150);
            words.setHorizontalAlignment(JLabel.CENTER);
            words.setPreferredSize(new Dimension(200, 150));
            words.setVisible(true);
            add(words, BorderLayout.CENTER);


            yes = new JButton();
            yes.setIcon(new ImageIcon(getClass().getResource("/resources/yes.png")));
            yes.setPreferredSize(new Dimension(30, 30));
            yes.setOpaque(true);
            yes.setBorder(null);
            //yes.setFocusPainted(false);
            //yes.setContentAreaFilled(false);
            yes.setFocusable(true);
            yes.setVisible(false);
            add(yes, BorderLayout.EAST);

            no = new JButton();
            no.setIcon(new ImageIcon(getClass().getResource("/resources/no.png")));
            no.setPreferredSize(new Dimension(30, 30));
            no.setOpaque(true);
            no.setBorder(null);
            no.setFocusPainted(false);
            no.setContentAreaFilled(false);
            no.setFocusable(true);
            no.setVisible(false);
            add(no, BorderLayout.WEST);


            timer = new Timer();

            // Se debe de tener un parámetro de entrada en el timertask
            myTimerTask = new MyTimerTask();
            timer.schedule(myTimerTask, 100, 100);

        }

    }
int cantPalabras= 0;
    class MyTimerTask extends TimerTask {
        private int nivelDeAciertos = (int) (controlGame.getListWords().size()*0.5); // El 70% de las palabras completadas;

        public MyTimerTask(){


            System.out.println(nivelDeAciertos);
        }

        public void verificarNivelCompletado(){
            System.out.println(controlGame.getListWords().size());
            System.out.println(nivelDeAciertos);
            if(controlGame.getListWords().size()<=nivelDeAciertos){

                if(controlGame.getLevels() == 10){ // Es decir, cuando finalice el nivel 10 se termina el juego, y todo se cierra
                    JOptionPane.showMessageDialog(null,"Felicidades, completaste todos los niveles <3");
                    System.exit(0);
                }else{


                    no.setFocusable(false);
                    no.setVisible(false);
                    yes.setFocusable(false);
                    yes.setVisible(false);
                   JOptionPane.showMessageDialog(null,"Felicidades, has completado el nivel " +
                           Integer.toString(controlGame.getLevels()));
                    controlGame.subirNivel();
                    controlGame.changeWords(controlGame.getLevels()); // Para agregar otras palabras al arreglo general según el nivel
                    controlGame.getWords(controlGame.getLevels()); // Para agregar otras palabras al arreglo de palabras correctas según el nivel
                    nivelDeAciertos = (int) (controlGame.getListWords().size()*0.5); // El 70% de las palabras completadas
                    level.setText("Nivel: " + Integer.toString(controlGame.getLevels()));
                   // Hasta aquí no sé si funcionará xd
                    timer = new Timer();
                    myTimerTask = new MyTimerTask();
                    timer.schedule(myTimerTask, 100, 100);
                    System.out.println(controlGame.getList()); // borrar después
                    System.out.println(controlGame.getListWords());
                }
            }
        }
        @Override
        public void run() {


            words.setText(controlGame.getWords(controlGame.getLevels()).get(counter).toUpperCase().toString());
            counter++;

            if(counter >= controlGame.getWords(controlGame.getLevels()).size()){
                if(timer !=null){
                    timer.cancel();
                    timer = null;
                    reply = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if(reply == JOptionPane.YES_OPTION){



                        yes.setVisible(true);
                        no.setVisible(true);

                        // Esta sección es para hacer que las palabras a elegir se cambien según el tiempo
                        /*
                        counter = 0;
                        timer = new Timer();
                        myTimerTask = new MyTimerTask();
                        timer.schedule(myTimerTask, 1000, 3000);

                         */



                        MouseListener oyenteDeRatonYES = new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                ///System.out.println(e.getButton() == MouseEvent.MOUSE_CLICKED);
                                //System.out.println("Esto dice sí");
                                //controlGame.Wordwait(words.getText());
                                int numeroRandom = (int)(Math.random()*getControlGame().getListWords().size()+0);

                                if(controlGame.getList().contains(words.getText().toLowerCase(Locale.ROOT))){
                                    controlGame.GuessWord(words.getText().toLowerCase(Locale.ROOT));

                                    words.setText(getControlGame().getListWords().get(numeroRandom).toUpperCase());
                                    score.setText("Puntaje: "+ Integer.toString(controlGame.getTotalPoint())); ///
                                    System.out.println(controlGame.getList()); // borrar después
                                    System.out.println(controlGame.getListWords());
                                    verificarNivelCompletado();

                                }else{
                                    JOptionPane.showMessageDialog(null,"Respuesta equivocada");
                                    words.setText(getControlGame().getListWords().get(numeroRandom).toUpperCase());
                                    controlGame.GuessWord(words.getText().toLowerCase(Locale.ROOT));
                                    // Aquí se da cuando se dice que está en el arreglo, pero en realidad no es cierto
                                    // (Pierde vidas)
                                    System.out.println(controlGame.getList()); // borrar después
                                    System.out.println(controlGame.getListWords());
                                }
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {


                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {

                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {

                            }
                        };
                        yes.addMouseListener(oyenteDeRatonYES);


                        MouseListener oyenteDeRatonNO = new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                int numeroRandom = (int)(Math.random()*getControlGame().getListWords().size()+0);
                                if(!(controlGame.getList().contains(words.getText().toLowerCase(Locale.ROOT)))){
                                    controlGame.GuessWordNO(words.getText().toLowerCase(Locale.ROOT));
                                    System.out.println("xDDDDDDddddD");
                                    words.setText(getControlGame().getListWords().get(numeroRandom).toUpperCase());
                                    score.setText("Puntaje: "+ Integer.toString(controlGame.getTotalPoint())); ///
                                    System.out.println(controlGame.getList()); // borrar después
                                    System.out.println(controlGame.getListWords());
                                    verificarNivelCompletado();
                                }else{
                                    JOptionPane.showMessageDialog(null,"Respuesta equivocada");
                                    words.setText(getControlGame().getListWords().get(numeroRandom).toUpperCase());
                                    controlGame.GuessWord("Palabra equivocada...");
                                    System.out.println(controlGame.getList()); // borrar después
                                    System.out.println(controlGame.getListWords());
                                }
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {

                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {

                            }
                        };
                        no.addMouseListener(oyenteDeRatonNO);


                    }else{
                        System.out.print(":(");

                    }
                }
            }

        }

    }
}
