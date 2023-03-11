package model.classes.pieces.immovable;

import model.classes.pieces.Piece;

/**
 * Barrier class that represents the yellow buttons on the board.
 * No piece can move to a barrier.
 *
 * @author Ioannis Petsis csd4993
 */
public class Barrier extends Piece {

    /**
     * Constructor
     */
    public Barrier(){
        super("Barrier", null, null);
    }
}