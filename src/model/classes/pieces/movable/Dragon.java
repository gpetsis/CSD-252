package model.classes.pieces.movable;

import model.classes.pieces.MovablePiece;

/**
 * Dragon class that extends MovablePiece.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Dragon extends MovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the Dragon
     */
    public Dragon(String team){
        super("Dragon", team, getPathOfImage(team, "dragon"), 10);
    }
}