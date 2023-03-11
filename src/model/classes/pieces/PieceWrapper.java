package model.classes.pieces;

/**
 * Class that implements the swap of two pieces.
 *
 * @author Ioannis Petsis csd4993
 */
public class PieceWrapper {

    /**
     * The swapping piece.
     */
    public Piece piece;

    /**
     * Constructor.
     *
     * @param piece the swapping piece
     */
    public PieceWrapper(Piece piece){
        this.piece = piece;
    }
}