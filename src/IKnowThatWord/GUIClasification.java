package IKnowThatWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIClasification extends JFrame {
    private Escucha escucha;
    private JTextArea txtTabla;
    private JButton salir;
    private JPanel panelTexto;
    private JLabel titulo;

    public GUIClasification(String puntuacion) {
        initGUI(puntuacion);
        setTitle("I know That Word");
        setSize(490,500);
        setPreferredSize(new Dimension(490,500));
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private void initGUI(String puntuacion) {
        escucha = new Escucha();


        Font font = new Font(Font.DIALOG,Font.BOLD,20);

        titulo = new JLabel ("Tabla de Clasificaciones");
        titulo.setFont(font);
        titulo.setSize(470,50);
        titulo.setForeground(Color.BLACK);
        titulo.setPreferredSize(new Dimension(470,50));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setVerticalAlignment(JLabel.CENTER);

        txtTabla = new JTextArea();
        txtTabla.setEditable(false);
        txtTabla.setText(puntuacion);
        panelTexto = new JPanel();
        JScrollPane scroll = new JScrollPane(txtTabla);
        txtTabla.setSize(490,400);
        txtTabla.setPreferredSize(new Dimension(490,400));


        salir = new JButton("Volver");
        salir.addActionListener(escucha);


        panelTexto.add(scroll);
        add(titulo,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
        add(salir,BorderLayout.SOUTH);

    }
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if(e.getActionCommand()=="Volver") {
                GUIStart inicio = new GUIStart();
            }
            dispose();
        }

    }
}

