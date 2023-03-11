package model.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.classes.pieces.EmptyPiece;
import model.classes.pieces.Piece;
import model.classes.pieces.immovable.Flag;
import model.classes.pieces.immovable.Trap;
import model.classes.pieces.movable.*;
import model.classes.pieces.movable.specialmovable.Dwarf;
import model.classes.pieces.movable.specialmovable.Scout;
import model.classes.pieces.movable.specialmovable.Slayer;

/**
 * Player class.
 * Every Player has some pieces, the # of available pieces, 
 * the pieces he/she has captured, and the percentage of 
 * successful attacks. It also has a name(Red or Blue).
 * 
 * @author Ioannis Petsis csd4993
 */
public class Player {
    
    /**
     * The name of the player. Either Red or Blue.
     */
    private String name;

    /**
     * The number of available pieces the player has at
     * a given time.
     */
    private int noAvailablePieces;

    /**
     * The available pieces the player has.
     */
    private List<Piece> pieces;

    /**
     * The number of pieces the player has captured
     * from the enemy.
     */
    private int noCaptures;

    /**
     * The pieces the player has captured from
     * the enemy.
     */
    private List<Piece> piecesCaptured;

    /**
     * The number of times the player has revived a piece.
     */
    private int noRevives;

    /**
     * The amount of times the player has attacked.
     */
    private int noAttacks;

    /**
     * The amount of times the player has attacked successfully.
     */
    private int noSuccAttacks;

    /**
     * The percentage the player has attacked
     * the enemy successfully.
     */
    private float percentageOfSuccAttacks;

    /**
     * Is true if it is the players turn to attack,
     * false otherwise.
     */
    private boolean isTurn;

    /**
     * Returns the name of the player.
     * @return Either Red, or Blue
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the number of available pieces.
     * @return the number of available pieces
     */
    public int getNoPieces(){
        return this.noAvailablePieces;
    }

    /**
     * Returns a list with all the alive pieces of the player.
     * @return a list with all the alive pieces of the player
     */
    public List<Piece> getPieces(){
        return this.pieces;
    }

    /**
     * Returns the number of pieces the player has captured
     * from the enemy.
     * @return the amount of pieces captured from the enemy
     */
    public int getNoCaptures(){
        return this.noCaptures;
    }

    /**
     * Returns the pieces the player has captured.
     * @return the pieces the player has captured
     */
    public List<Piece> getCapturedPieces(){
        return this.piecesCaptured;
    }

    /**
     * Returns the amount of pieces the player has revived.
     * @return the amount of pieces the player has revived
     */
    public int getNoRevives(){
        return this.noRevives;
    }

    /**
     * Returns the amount of times the player has attacked.
     * @return the amount of times the player has attacked
     */
    public int getNoAttacks(){
        return this.noAttacks;
    }
    
    /**
     * Returns the amount of times the player has attacked successfully.
     * @return the amount of times the player has attacked successfully
     */
    public int getNoSuccAttacks(){
        return this.noSuccAttacks;
    }

    /**
     * Returns the percentage of successful attacks of the player.
     * @return the percentage of successful attacks of the player
     */
    public float getPercentageOfSuccAttacks(){
        return this.percentageOfSuccAttacks;
    }

    /**
     * Sets the name of the player.
     *
     * @pre name != NULL
     *
     * @param name the name of the player
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the number of available pieces.
     * @param number the number of available pieces
     */
    public void setNoAvailablePieces(int number){
        this.noAvailablePieces = number;
    }

    /**
     * Sets the number of captures.
     *
     * @param number the number of captures
     */
    public void setNoCaptures(int number){
        this.noCaptures = number;
    }

    /**
     * Adds a captured piece to the list.
     *
     * @param piece the captured piece
     */
    public void addPieceCaptured(Piece piece){
        piecesCaptured.add(piece);

        for(Piece capturedPiece : pieces){
            if(capturedPiece == piece){
                pieces.remove(capturedPiece);

                break;
            }
        }
    }

    public void removeCapturedPiece(String pieceName){
        for(Piece capturedPiece : piecesCaptured){
            if(pieceName.equals(capturedPiece.getName())){
                piecesCaptured.remove(capturedPiece);

                break;
            }
        }
    }

    /**
     * Sets the number of revives the player has done.
     *
     * @param number the number of revives
     */
    public void setNoRevives(int number){
        this.noRevives = number;
    }

    /**
     * Adds 1 to the number of attacks the player has done.
     * when the player attacks the enemy.
     */
    public void incrementAttacks(){
        this.noAttacks++;
    }

    /**
     * Adds 1 to the number of successful attacks the player
     * has done when the player attacks the enemy successfully.
     */
    public void incrementSuccAttacks(){
        this.noSuccAttacks++;
    }

    /**
     * Sets the successful attack percentage.
     *
     * @param number the percentage of successful attacks
     */
    public void setSuccAttackPercentage(float number){
        this.percentageOfSuccAttacks = number;
    }

    /**
     * Updates the successful attack percentage.
     *
     * @pre getNoAttacks() != 0
     */
    public void updateSuccAttackPercentage(){
        if(getNoAttacks() == 0){
            this.percentageOfSuccAttacks = 0;
        }else{
            this.percentageOfSuccAttacks = (float) getNoSuccAttacks() / getNoAttacks() * 100;
        }
    }

    /**
     * Constructor that sets the number of available pieces to 30 and
     * the number of captures, number of revives and the percentage
     * of successful attacks to 0.
     *
     * @param name the name of the player(Either Red, Blue)
     */
    public Player(String name, boolean reducedArmy){
        int start = 0, end = 30;
        int lessPieces;

        setName(name);
        setNoAvailablePieces(30);
        setNoCaptures(0);
        setNoRevives(0);
        setSuccAttackPercentage(0.0F);

        pieces = new ArrayList<>();

        if(name.equals("Red")){
            start += 50;
            end += 50;
        }

        if(reducedArmy){
            lessPieces = 2;
        }else{
            lessPieces = 1;
        }

        for(int i = start; i < end; i++){
            if(i == start){
                pieces.add(new Dragon(name));
            }else if(i == 1 + start){
                pieces.add(new Mage(name));
            }else if(i <= Math.ceil(3f / lessPieces) + start){
                pieces.add(new Knight(name));
            }else if(i <= Math.ceil(6f / lessPieces) + start){
                pieces.add(new BeastRider(name));
            }else if(i <= Math.ceil(8f / lessPieces) + start){
                pieces.add(new Sorceress(name));
            }else if(i <= Math.ceil(10f / lessPieces) + start){
                if(name.equals("Red")){
                    pieces.add(new LavaBeast());
                }else{
                    pieces.add(new Yeti());
                }
            }else if(i <= Math.ceil(12f / lessPieces) + start){
                pieces.add(new Elf(name));
            }else if(i <= Math.floor(17f / lessPieces) + start){
                pieces.add(new Dwarf(name));
            }else if(i <= Math.floor(21f / lessPieces) + start){
                pieces.add(new Scout(name));
            }else if(i == 22 / lessPieces + start){
                pieces.add(new Slayer(name));
            }else if(i <= Math.ceil(28f / lessPieces) + start){
                pieces.add(new Trap(name));
            }else if(i == Math.ceil(29f / lessPieces) + start){
                pieces.add(new Flag(name));
            }else{
                pieces.add(new EmptyPiece());
            }
        }

        Collections.shuffle(pieces);

        if(name.equals("Blue")) {
            for (Piece piece : pieces) {
                if(piece.getImage() != null){
                    piece.setImage(Piece.getPathOfImage("Blue", "blueHidden.png"));
                }
            }
        }

        for(int i = 0; i < pieces.size(); i++){
            if(name.equals("Blue")){
                pieces.get(i).setPosition(new int[][] {{i / 10}, {i % 10}});
            }else{
                pieces.get(i).setPosition(new int[][] {{i / 10 + start / 10}, {i % 10}});
            }
        }

        piecesCaptured = new ArrayList<>();
    }
}