package model.classes;

import java.io.File;

/**
 * Moder class.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Model {

    /**
     * String variable that tells whose player it is the turn to play.
     */
    public static String turn = "Red";

    /**
     * Static integer variable that tells on which round the game is on.
     */
    public static int turns = 1;

    /**
     * Function that changes the turn of the game.
     *
     * @param newTurn either Red of Blue
     */
    public static void setTurn(String newTurn){
        turn = newTurn;
    }

    /**
     * Function that returns the turn the game is on.
     *
     * @return the turn the game is on
     */
    public static int getTurn(){
        return turns;
    }

    /**
     * Function that increments the turns variable by 1.
     */
    public static void nextTurn(){
        turns++;
    }

    /**
     * Unused function that returns the path of a sound file.
     *
     * @param name the name of the sound
     *
     * @return the path to the sound
     */
    public static String getPathOfSound(String name){
        String path = new File(System.getProperty("user.dir")).toString();

        path += "\\resources\\sounds\\";
        path += name + ".wav";

        return path;
    }
}