package IKnowThatWord;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Serializable;
import javax.swing.*;
import java.util.*;
//2022985 - Robert Fernando Gil Trujillo
//2025176 - Esperanza Olivo Calderón
public class ControlGame implements Serializable {
    private int levels, totalPoint;
    private String nick;
    private int lives;
    private ArrayList<String> words;
    private ArrayList<Integer> repeated;
    private ArrayList<String> list; // Aquí se guardan la lista de palabras.
    transient private Dictionary dictionary;
    private double nivelDeAciertos;

    public double getNivelDeAciertos() {return nivelDeAciertos;}

    public ControlGame(){
        nivelDeAciertos = 7;
        list = new ArrayList<>();
        words = new ArrayList<>();
        dictionary = new Dictionary();
        lives = 300;
        levels = 1;
        totalPoint = 0;
        repeated = new ArrayList<Integer>();
        ArrayList<String> list = new ArrayList<String>();

        this.changeWords(levels);
    }

    public ArrayList<String> getList(){
        return list;
    }

    public ArrayList<String> getListWords(){
        return words;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public ControlGame(String name, int level,int totalP, int live) {
        list = new ArrayList<>();
        dictionary = new Dictionary();
        words = new ArrayList<>();
        this.nick = name;
        this.lives = live;
        this.levels = level;
        this.totalPoint = totalP;
        this.changeWords(levels);
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLevels() {
        return levels;
    }

    public void subirNivel () {
        this.levels = this.levels + 1;

        switch (levels) {
            case 1:
                lives = 3;
                nivelDeAciertos = 0.30/2;
                break;
            case 2:
                lives = 12;
                nivelDeAciertos = 0.30/2;
                break;
            case 3:
                lives = 19; // Fórmula: Cantidad de palabras a acertar * % permitido para cometer error * nivel (Se pone el nivel
                // porque a medida que aumenta de nivel, se quitan las vidas según el nivel, debido a que la clase del evento se
                // llama a sí misma, por lo tanto se acumulan los comandos lógicos de los botones
                nivelDeAciertos = 0.25/2;
                break;
            case 4:
                lives = 24;
                nivelDeAciertos = 0.2/2;
                break;
            case 5:
                lives = 35;
                nivelDeAciertos = 0.2/2;
                break;
            case 6:
                lives = 48;
                nivelDeAciertos = 0.15/2;
                break;
            case 7:
                lives = 35;
                nivelDeAciertos = 0.1/2;
                break;
            case 8:
                lives = 48;
                nivelDeAciertos = 0.1/2;
                break;
            case 9:
                lives = 32;
                nivelDeAciertos = 0.05/2;
                break;
            case 10:
                lives = 0;
                nivelDeAciertos = 0/2;
                break;
        }

    }  // AQUÍ DECIDEN VIDAS A RESTAURAR Y NIVEL


    public int getTotalPoint() {
        return totalPoint;
    }

    public String getNick() {
        return nick;
    }

    public int getLives() {
        return lives;
    }



    // Este método consiste en crear las palabras según el nivel.
    public ArrayList<String> getWords(int level) {

        switch (level){
            case 1:
                 list = new ArrayList<String>(words.subList(0, 10));
                 System.out.println(list);
                break;
            case 2:
                list = new ArrayList<String>(words.subList(0, 21));
                break;
            case 3:
                 list = new ArrayList<String>(words.subList(0, 26));
                break;
            case 4:
               list = new ArrayList<String>(words.subList(0, 31));
                break;
            case 5:
                list = new ArrayList<String>(words.subList(0, 36));
                break;
            case 6:
               list = new ArrayList<String>(words.subList(0, 41));
                break;
            case 7:
                 list = new ArrayList<String>(words.subList(0, 51));
                break;
            case 8:
                list = new ArrayList<String>(words.subList(0, 61));
                break;
            case 9:
                list = new ArrayList<String>(words.subList(0, 71));
                break;
            case 10:
                list = new ArrayList<String>(words.subList(0, 100));
                break;

        }

        return list;
    }

    public void GuessWord(String answer){
        if(list.contains(answer)){
            totalPoint++;
            resetComponent(answer);
            /*
            if(this.list.size() == 0){
                this.levels = levels + 1;
                getWords(levels);
            }*/ // Puse esta parte de código en PanelWord, en el método verificarNivelCompletado
        }else {
            System.out.println(levels);
            lives = lives - 1;
            // Se hizo este bloque case, porque, como el panel se llama a sí mismo, los valores de resta se van acumulando

            }


            if(lives == 0){
                JOptionPane.showMessageDialog(null, "Perdiste");

                this.changeWords(levels);
            }

    }

    // Se agregó este método para el caso en que se acerte aquellas palabras que no pertenecen al arreglo
    // (Es decir, cuando se da acertadamente No a una palabra que no pertenece al arreglo)
    public void GuessWordNO(String palabraMala){
        words.remove(palabraMala);
        totalPoint++;
        if(this.words.size() == 0){
            this.levels = levels + 1;
        }
    }
    public ArrayList<String> resetComponent (String index){

        words.remove(index); // Se elimina la palabra verdadera del grupo de palabras
        list.remove(index); // Se elimina la palabra verdadera
        return  words;
    }


    // Para eliminar.
    public void Wordwait(String palabra) {
        for (int i = 0; i < list.size(); i++) {
           // x = new JTextField(getWords(getLevels()).get(i));
            palabra = getWords(getLevels()).get(i);
            GuessWord(palabra);
        }
    }

    public void changeWords(int level){
        dictionary.FillArray(level);
        String [] str = dictionary.getWords();
        this.setWords(str);
    }


    public void setWords(String[] words_array) {
        this.words.clear();
        this.list.clear();
        for (int i = 0; i<words_array.length; i++){
            this.words.add(i, words_array[i]);
        }
    }
}


