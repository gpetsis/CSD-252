package model.classes.pieces;

/**
 * SpecialMovablePiece class that extends MovablePiece
 * There are 3 special pieces in the game:
 * the dwarf(3), that can diffuse bombs,
 * the scout(2), which can move more than one block at once, and
 * the slayer(1), which is the only piece that can capture the dragon.
 * 
 * @author Ioannis Petsis csd4993
 */
public class SpecialMovablePiece extends MovablePiece{
    
    /**
     * Constructor.
     *
     * @param name the name of the piece
     * @param team the team of the piece
     * @param image the path to the image of the piece
     * @param rank the rank of the piece
     */
    public SpecialMovablePiece(String name, String team, String image, int rank){
        super(name, team, image, rank);
    }
}