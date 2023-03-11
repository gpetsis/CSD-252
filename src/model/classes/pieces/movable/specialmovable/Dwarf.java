package model.classes.pieces.movable.specialmovable;

import model.classes.pieces.SpecialMovablePiece;

/**
 * Dwarf class that extends SpecialMovablePiece.
 * The Dwarf has the ability to defuse bombs.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Dwarf extends SpecialMovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the Dwarf
     */
    public Dwarf(String team){
        super("Dwarf", team, getPathOfImage(team, "dwarf"), 3);
    }
}