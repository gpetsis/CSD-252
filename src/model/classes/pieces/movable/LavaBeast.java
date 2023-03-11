package model.classes.pieces.movable;

import model.classes.pieces.MovablePiece;

/**
 * LavaBeast class that extends MovablePiece.
 * This piece only exists on the Red team.
 * 
 * @author Ioannis Petsis csd4993
 */
public class LavaBeast extends MovablePiece{
    
    /**
     * Constructor.
     */
    public LavaBeast(){
        super("Lava Beast", "Red", getPathOfImage("Red", "lavaBeast.png"), 5);
    }
}