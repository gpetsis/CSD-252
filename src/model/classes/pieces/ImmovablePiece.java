package model.classes.pieces;

/**
 * ImmovablePiece class that extends Piece.
 * There are two types of immovable pieces, the Bomb and the Flag.
 * They cannot move on the board.
 * 
 * @author Ioannis Petsis csd4993
 */
public class ImmovablePiece extends Piece{

    /**
     * Constructor.
     *
     * @param name the name of the piece
     * @param team the team of the piece
     * @param image the path to the image of the piece
     */
    public ImmovablePiece(String name, String team, String image){
        super(name, team, image);
    }
}