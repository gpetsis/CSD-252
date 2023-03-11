package model.classes.pieces.movable.specialmovable;

import model.classes.pieces.SpecialMovablePiece;

/**
 * Scout class that extends SpecialMovablePiece.
 * The Scout can move more than one block when he moves.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Scout extends SpecialMovablePiece{
    
    /**
     * Constructor.
     *
     * @param team the team of the Scout
     */
    public Scout(String team){
        super("Scout", team, getPathOfImage(team, "scout"), 2);
    }
}