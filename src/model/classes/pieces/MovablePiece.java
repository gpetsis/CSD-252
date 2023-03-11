package model.classes.pieces;

/**
 * Movable piece class that extends Piece.
 * Every piece that can move on the board has a rank, and all the attributes of a Piece.
 * 
 * @author Ioannis Petsis csd4993
 */
public class MovablePiece extends Piece{

    /**
     * The rank of the piece.
     */
    private int rank;

    /**
     * Returns the rank of the piece.
     *
     * @return the rank of the piece
     */
    public int getRank(){ return this.rank; }

    /**
     * Sets the rank of the piece.
     *
     * @param rank the rank of the piece
     */
    public void setRank(int rank){ this.rank = rank; }

    /**
     * Constructor of the MovablePiece that inherits the constructor of Piece, and adds a rank.
     *
     * @param name the name of the piece
     * @param team the team of the piece
     * @param image the path to the image of the piece
     * @param rank the rank of the piece
     */
    public MovablePiece(String name, String team, String image, int rank){
        super(name, team, image);
        setRank(rank);
    }
}