package model.classes.pieces;

/**
 * EmptyPiece class that extends Piece.
 * This class is only used when the reduced army special rule is activated.
 * It is a temporary piece to the list of pieces of a player, and is later
 * deleted from that list.
 *
 * @author Ioannis Petsis csd4993
 */
public class EmptyPiece extends Piece{

    /**
     * Constructor.
     */
    public EmptyPiece(){
        super("EmptyPiece", null, null);
    }
}