package model.classes.pieces.movable;

import model.classes.pieces.MovablePiece;

/**
 * Knight class that extends MovablePiece.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Knight extends MovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the Knight
     */
    public Knight(String team){
        super("Knight", team, getPathOfImage(team, "knight"), 8);
    }
}