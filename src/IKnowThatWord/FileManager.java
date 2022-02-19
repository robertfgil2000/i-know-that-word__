package IKnowThatWord;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
//2022985 - Robert Fernando Gil Trujillo
//2025176 - Esperanza Olivo Calderón

public class FileManager {
    private Score score;
    private File scoreFile;
    private File gameFile;

    public FileManager() {

        this.score = new Score();
        this.scoreFile = new File("src/data/clasification.txt");
        this.gameFile = new File("src/data/games.txt");
    }

    public FileManager(Score newScore) {

        this.score = newScore;
        this.scoreFile = new File("src/data/clasification.txt");
        this.gameFile = new File("src/data/games.txt");
    }

    // OJO, ESTE METODO SOLO ES PARA AGREGAR EN EL ARRAYLIST DE CONTROL PALABRAS
    public void addScore(ControlGame cont) {
        this.score.addScore(cont);
    }
    public Score getScore() {
        return score;
    }

    public void setScoreFile(File scoreFile) {
        this.scoreFile = scoreFile;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void printScore () {
        try{

            ObjectOutputStream read = new ObjectOutputStream(new FileOutputStream(scoreFile,false));
            read.writeObject(this.score);
            read.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printScore (ControlGame newControl) {
        try{

            if(this.getScoreFile().length()!=0) {
                this.score = this.getScoreObject();
            }else {
                this.score = new Score();
            }
            this.score.addScore(newControl);
            ObjectOutputStream read = new ObjectOutputStream(new FileOutputStream(scoreFile,false));
            read.writeObject(this.score);
            read.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    // funcion para obtener los puntajes de los jugadores
    public String getScoreString(){
        String info = "";
        try{
            ObjectInputStream read = new ObjectInputStream(new FileInputStream(scoreFile));
            this.score = (Score) read.readObject();
            info = this.score.printScore();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return info;
    }

    // funcion para obtener los puntajes de los jugadores
    public Score getScoreObject(){
        Score newScore = null;
        try{
            ObjectInputStream read = new ObjectInputStream(new FileInputStream(scoreFile));

            if(this.getScoreFile().length()!=0) {
                newScore = (Score) read.readObject();
            }else {
                newScore = new Score();
            }

        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return newScore;
    }

    public File getScoreFile() {
        return scoreFile;
    }

    public File getGameFile() {
        return gameFile;
    }

    public void setGameFile(File archivoPartidas) {
        this.gameFile = gameFile;
    }

    public void writeGame() {
        try{
            ObjectOutputStream read = new ObjectOutputStream(new FileOutputStream(gameFile,false));
            read.writeObject(this.score);
            read.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeGame(Score sco){
        try{
            ObjectOutputStream read = new ObjectOutputStream(new FileOutputStream(gameFile,false));
            read.writeObject(sco);
            read.close();


        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeGame (ControlGame newControl) {
        try{

            this.score = this.getGameObject();

            this.score.addScore(newControl);

            ObjectOutputStream read = new ObjectOutputStream(new FileOutputStream(gameFile,false));

            read.writeObject(this.score);

            read.close();


        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // funcion para obtener los puntajes de los jugadores
    public Score getGameObject(){
        Score newScore = null;
        try{

            ObjectInputStream read = new ObjectInputStream(new FileInputStream(this.gameFile));

            newScore = (Score)read.readObject();

        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return newScore;
    }


}
