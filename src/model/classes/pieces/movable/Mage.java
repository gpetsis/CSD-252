package model.classes.pieces.movable;

import model.classes.pieces.MovablePiece;

/**
 * Mage class that extends MovablePiece.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Mage extends MovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the Mage
     */
    public Mage(String team){
        super("Mage", team, getPathOfImage(team, "mage"), 9);
    }
}