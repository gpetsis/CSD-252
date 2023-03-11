package model.classes.pieces.movable;

import model.classes.pieces.MovablePiece;

/**
 * Sorceress class that extends MovablePiece.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Sorceress extends MovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the Sorceress
     */
    public Sorceress(String team){
        super("Sorceress", team, getPathOfImage(team, "sorceress"), 6);
    }
}