package IKnowThatWord;

import java.io.Serializable;
import  java.util.ArrayList;
//2022985 - Robert Fernando Gil Trujillo
//2025176 - Esperanza Olivo Calderón
public class Score implements Serializable {

    private ArrayList<ControlGame> score;

    public Score(){
        this.score = new ArrayList<ControlGame>();
    }

    public Score(ArrayList<ControlGame> scr){
        this.score = scr;
    }

    public void addScore(ControlGame control) {this.score.add(control);}
    public String printScore(){
        String info = "";
        for(int i=0; i<score.size(); i++){
            info = "assd" + score.get(i).getNick() + "Puntaje: " + Integer.toString(score.get(i).getTotalPoint()) + "Nivel: " + Integer.toString((score.get(i).getLevels())) + info;
        }
        return  info;
    }

    public ArrayList<ControlGame> getScore() {
        return score;
    }

    public void setScore(ArrayList<ControlGame> score) {
        this.score = score;
    }

    public  boolean checkExistence(String name){
        boolean answer = false;
        Score scr;
        FileManager fileManager = new FileManager();
        String scr2;
        if(fileManager.getGameFile().length() !=0){
            scr = fileManager.getGameObject();
        }else{
            scr = new Score();
        }
        for(int i=0; i<scr.getScore().size(); i++) {
            scr2 = scr.getScore().get(i).getNick();
            if(name.equalsIgnoreCase(scr2)){
                answer = true;
            }
        }
        return answer;
    }
    public ControlGame getPlayer(String name){
        ControlGame control = null;
        Score scr;
        FileManager fileManager = new FileManager();
        int index = 0;

        if(fileManager.getGameFile().length() !=0){
            scr = fileManager.getGameObject();
        }else{
            scr = new Score();
        }
        for(int i=0; i<scr.getScore().size(); i++){
            if(name.equalsIgnoreCase(scr.getScore().get(i).getNick())){
                control = scr.getScore().get(i);
                index = 1;
                scr.getScore().remove(index);
                fileManager.writeGame(scr);
                i = scr.getScore().size();
            }

        }
        return  control;
    }
}
