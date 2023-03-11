package model.classes.pieces.movable;

import model.classes.pieces.MovablePiece;

/**
 * Yeti class that extends MovablePiece.
 * This piece only exists on the Blue team.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Yeti extends MovablePiece{
    
    /**
     * Constructor.
     */
    public Yeti(){
        super("Yeti", "Blue", getPathOfImage("Blue", "yeti.png"), 5);
    }
}