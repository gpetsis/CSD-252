package model.classes.pieces.movable.specialmovable;

import model.classes.pieces.SpecialMovablePiece;

/**
 * Slayer class that extends SpecialMovablePiece.
 * The Slayer is the only piece that can capture the Dragon,
 * IF he attacks, and not defend.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Slayer extends SpecialMovablePiece{

    /**
     * Constructor.
     *
     * @param team the team of the Slayer
     */
    public Slayer(String team){
        super("Slayer", team, getPathOfImage(team, "slayer"), 1);
    }
}