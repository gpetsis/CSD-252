package model.classes.pieces.immovable;

import model.classes.pieces.ImmovablePiece;

/**
 * Flag class that extends ImmovablePiece
 * Each player has one flag. If a player attacks
 * the opponents flag, he/she wins the game.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Flag extends ImmovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the flag
     */
    public Flag(String team){
        super("Flag", team, getPathOfImage(team, "flag"));
    }
}