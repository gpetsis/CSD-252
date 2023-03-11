package model.classes.pieces.movable;

import model.classes.pieces.MovablePiece;

/**
 * Elf class that extends MovablePiece.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Elf extends MovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the Elf
     */
    public Elf(String team){
        super("Elf", team, getPathOfImage(team, "elf"), 4);
    }
}