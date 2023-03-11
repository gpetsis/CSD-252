package model.classes.pieces.movable;

import model.classes.pieces.MovablePiece;

/**
 * BeastRider class that extends MovablePiece.
 * 
 * @author Ioannis Petsis csd4993
 */
public class BeastRider extends MovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the Beast Rider
     */
    public BeastRider(String team){
        super("Beast Rider", team, getPathOfImage(team, "beastRider"), 7);
    }
}