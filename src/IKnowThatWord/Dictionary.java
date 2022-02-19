package IKnowThatWord;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
//2022985 - Robert Fernando Gil Trujillo
//2025176 - Esperanza Olivo Calderón
public class Dictionary {
    //Attributes
    private static final String routeWords= "src/data/words.txt";
    private LineNumberReader br = null;
    private ArrayList<Integer> repeated;
   // private BufferedReader br;
    private InputStreamReader isr;
    private String[] words;
    private int cant;
    int levels = 1;

    public Dictionary() {
        repeated = new ArrayList<Integer>();
    }

    //Métodos
    public void FillArray(int level) {
        this.words = null;

        switch (level) {
            case 1:
                this.cant = 20;
                break;
            case 2:
                this.cant = 40;
                break;
            case 3:
                this.cant = 50;
                break;
            case 4:
                this.cant = 60;
                break;
            case 5:
                this.cant = 70;
                break;
            case 6:
                this.cant = 80;
                break;
            case 7:
                this.cant = 100;
                break;
            case 8:
                this.cant = 120;
                break;
            case 9:
                this.cant = 140;
                break;
            case 10:
                this.cant = 200;
                break;
        }
        this.words = new String[cant];
        try {
            int numWords = 0;
            String control = null;
            Random random = new Random();
            isr = new InputStreamReader(new FileInputStream(routeWords), "ISO-8859-1");
            br = new LineNumberReader(isr);
            for(int i = 0; i < cant; i++) {
                numWords = random.nextInt(300)+1;
                //numWords = (int)(Math. random()*4000+0);

                if(repeated.contains(numWords)) {
                    i--;
                }
                else {
                    control = br.readLine();
                    for(int j = 0; j < numWords; j++) {
                        control = br.readLine();
                    }
                    this.repeated.add(br.getLineNumber());
                    this.words[i] = control;
                }
            }
            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo historial_jugadores.txt");
        }
    }
    public void resetAll() {
        this.words = null;
        repeated.clear();
    }
    public String[] getWords() {
        return this.words;
    }
    public ArrayList<Integer> getRepeated() {
        return repeated;
    }

    }



