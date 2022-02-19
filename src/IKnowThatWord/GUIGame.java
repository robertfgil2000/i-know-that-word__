package IKnowThatWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//2022985 - Robert Fernando Gil Trujillo
//2025176 - Esperanza Olivo Calderón
public class GUIGame extends JFrame {

    private JLabel title;
    private ControlGame controlGame;
    private JButton save, help;
    private  JTextField wordText;
    private FileManager fileManager;
    private PanelWord panelWord;
    private JLabel lblBackgroundImage = new JLabel();
    private Escucha escucha;
    private  JButton yes, no;
    private  JPanel guiPanel;
    private Point initialClick;
    public static final String MESSAGE = "Bienvenido a I Know That Word\n" +
            "\n I Know That Word es un juego de memoria que tiene 10 niveles. " +
            "\n Una vez que comiences la partida mostrará una serie de palabras "+
            "\n que aumentarán según el nivel en que se encuentre el jugador." +
            "\n Tendrás 5 segundos para memorizar cada palabra mostrada "+
            "\n en la fase inicial y 7 segundos para decidir si la palabra está o no. " +
            "\n Pasarás de nivel si aciertas el mayor número de palabras " +
            "\n y ganarás si logras completar todos los niveles.";


    public GUIGame(ControlGame w){
        this.controlGame = w;
        this.fileManager = new FileManager();
        this.controlGame.changeWords(this.controlGame.getLevels());
        setUndecorated(true);
        initGUI(controlGame);
        //setLayout(new FlowLayout(FlowLayout.CENTER));
       // setLayout(null);
        setTitle("I Know That Word");
        setSize(300,300);
        setPreferredSize(new Dimension(300,300));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setContentPane(new JLabel(new ImageIcon(getClass().getResource("/resources/back.png"))));
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });
    }

    private void initGUI(ControlGame controlGame){
     //  this.getContentPane().setLayout(new GridBagLayout());
      // GridBagConstraints constraints = new GridBagConstraints();




       // lblBackgroundImage.setIcon(new ImageIcon(getClass().getResource("/resources/back.png")));
        //lblBackgroundImage.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        //setContentPane(lblBackgroundImage);

      //  this.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/resources/back.png"))));

        escucha = new Escucha();
        save = new JButton("X");
        save.addActionListener(escucha);
        save.setOpaque(true);
        save.setFocusPainted(false);
        save.setBackground(new Color(246, 51, 71));
        save.setForeground(Color.BLACK);
        save.setPreferredSize(new Dimension(30, 30));
        save.setBounds(255, 0, 45, 25);
        add(save);

        help = new JButton("?");
        help.addActionListener(escucha);
        help.setOpaque(true);
        help.setFocusPainted(false);
        help.setBounds(0, 0, 45, 25);
        help.setBackground(new Color(51, 153, 255));
        //help.setBackground(new Color(255, 0, 127));
        help.setForeground(Color.BLACK);
        add(help);




        Font font = new Font("Agency FB",Font.BOLD,20);
        title = new JLabel ("I Know That Word");
        title.setFont(font);
        title.setSize(300,50);
        title.setForeground(Color.BLACK);
        title.setPreferredSize(new Dimension(300,50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);


        add(title, BorderLayout.PAGE_START);

        panelWord = new PanelWord(this.controlGame);
        panelWord.setOpaque(false);






        add(panelWord, BorderLayout.CENTER);






        yes = new JButton();
        yes.setIcon(new ImageIcon(getClass().getResource("/resources/yes.png")));
        yes.setPreferredSize(new Dimension(30, 30));
        yes.setOpaque(true);
        yes.setBorder(null);
        yes.setFocusPainted(false);
        yes.setContentAreaFilled(false);
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

    }
    public JButton getYes(){
        initGUI(controlGame);
        return yes;
    }
    public JButton getNo(){
        initGUI(controlGame);
        return no;
    }
    private class  Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == save) {
                System.exit(0);
               /** if(controlGame.getLives() == 0) {

                    JOptionPane.showMessageDialog(null, "Has perdido todas las vidas, gracias por jugar :)");
                    GUIStart game = new GUIStart();
                    dispose();

                }
                if(controlGame.getLives() != 0 && controlGame.getLevels() !=10) {

                    if(fileManager.getGameFile().length() == 0) {
                       fileManager.addScore(controlGame);

                        fileManager.writeGame();
                    }else {
                        fileManager.writeGame(controlGame);

                    }

                }

                */

            }

            if(e.getSource() == help){
                JOptionPane.showMessageDialog(null, MESSAGE);
            }

        }


    }
}
