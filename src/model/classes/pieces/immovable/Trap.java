package model.classes.pieces.immovable;

import model.classes.pieces.ImmovablePiece;

/**
 * Bomb class that extends ImmovablePiece
 * The bomb cannot be moved on the board. Whoever steps on it
 * is captured except the Dwarf(3), who has the ability to
 * difuse the bomb and destroy it.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Trap extends ImmovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the bomb
     */
    public Trap(String team){
        super("Trap", team, getPathOfImage(team, "trap"));
    }
}