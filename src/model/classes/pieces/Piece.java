package model.classes.pieces;

import java.io.File;

/**
 * Piece abstract class.
 * Every piece has a name, a team(Red or Blue), the availability(number of copies) and an image.
 *
 * @author Ioannis Petsis csd4993
 */
public abstract class Piece {

    /**
     * The name of the piece.
     */
    private String name;

    /**
     * The team of the piece, either Red or Blue.
     */
    private String team;

    /**
     * The image of the piece.
     */
    private String image;

    /**
     * The position of the piece on the board.
     */
    private int[][] position;

    /**
     * Boolean variable the tells if the piece has triggered a revive.
     */
    private boolean hasRevived = false;

    /**
     * Returns the name of the piece.
     *
     * @return the name of the piece
     */
    public String getName(){ return this.name; }

    /**
     * Returns the team of the piece.
     *
     * @return the team of the piece
     */
    public String getTeam(){ return this.team; }

    /**
     * Returns the path to the image of the piece.
     *
     * @return the path to the image of the piece
     */
    public String getImage(){ return this.image; }

    /**
     * Returns the position of the piece on the board.
     * On index 0 is the x coordinate and on inder 1 is the y coordinate.
     *
     * @return the position of the piece on the board
     */
    public int[][] getPosition(){ return this.position; }

    /**
     * Sets the name of the piece.
     *
     * @param name the name of the piece
     */
    public void setName(String name){ this.name = name; }

    /**
     * Sets the team of the piece.
     *
     * @param team the team of the piece
     */
    public void setTeam(String team){ this.team = team; }

    /**
     * Sets the path for the image of the piece.
     *
     * @param image the path to the image of the piece
     */
    public void setImage(String image){ this.image = image; }

    /**
     * Sets the position of the piece on the board.
     *
     * @param position the position on the board
     */
    public void setPosition(int[][] position){ this.position = position; }

    /**
     * Function to update the state of hasRevived.
     * It is called when the piece triggers a revive.
     */
    public void setHasRevived(){ this.hasRevived = true; }

    /**
     * Function that returns if the piece has triggered a revive.
     *
     * @return if the piece has triggered a revive
     */
    public boolean isHasRevived(){ return this.hasRevived; }

    /**
     * Constructor.
     *
     * @param name the name of the piece
     * @param team the team of the piece
     * @param image the path to the image of the piece
     */
    public Piece(String name, String team, String image){
        setName(name);
        setTeam(team);
        setImage(image);
    }

    /**
     * Method to get the path to an image.
     *
     * @param team the team of the piece
     * @param piece the name of the piece
     * @return the path to the image
     */
    public static String getPathOfImage(String team, String piece){
        String path = new File(System.getProperty("user.dir")).toString();
        path += "\\resources\\images\\";
        if(team.equals("Red")) path += "RedPieces\\";
        else path += "bluePieces\\";
        path += piece;
        if(!piece.equals("yeti.png") && !piece.equals("lavaBeast.png") && !piece.equals("blueHidden.png") && !piece.equals("redHidden.png")){
            path += team.charAt(0) + ".png";
        }
        return path;
    }
}