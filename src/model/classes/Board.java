package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.classes.pieces.*;
import model.classes.pieces.immovable.Barrier;
import model.classes.pieces.movable.specialmovable.Scout;

import static view.classes.StartingScreen.getNoFallBack;

/**
 * Class Board.
 * This class represents the board of the game.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Board {

    /**
     * The dimensions of the board.
     */
    private int boardH, boardW;

    /**
     * The positions of the pieces on the board.
     */
    public static Piece[][] positions;

    /**
     * Sets the height of the board
     *
     * @param height the height of the board
     */
    public void setBoardH(int height){ this.boardH = height; }

    /**
     * Sets the width of the board
     *
     * @param width the width of the board
     */
    public void setBoardW(int width){ this.boardW = width; }

    /**
     * Constructor.
     *
     * @param redPieces the pieces of the red player
     * @param bluePieces the pieces of the blue player
     */
    public Board(List<Piece> redPieces, List<Piece> bluePieces){
        setBoardW(10);
        setBoardH(8);

        positions = new Piece[boardH][boardW];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 10; j++){
                positions[i][j] = bluePieces.get((i * 10) + j);
            }
        }

        for (Piece bluePiece : bluePieces) {
            if (bluePiece instanceof EmptyPiece) {
                int[][] position = bluePiece.getPosition();
                positions[position[0][0]][position[1][0]] = null;
            }
        }

        for(int i = 3; i < 5; i++){
            for(int j = 0; j < 10; j++){
                if(j == 2 || j == 3 || j == 6 || j == 7){
                    positions[i][j] = new Barrier();
                }else{
                    positions[i][j] = null;
                }
            }
        }

        for(int i = 5 ; i < 8; i++){
            for(int j = 0; j < 10; j++){
                positions[i][j] =  redPieces.get((i - 5) * 10 + j);
            }
        }

        for (Piece redPiece : redPieces) {
            if (redPiece instanceof EmptyPiece) {
                int[][] position = redPiece.getPosition();
                positions[position[0][0]][position[1][0]] = null;
            }
        }
    }

    /**
     * Function that returns the available positions a player can place a revived piece.
     *
     * @param team the team of the reviving piece
     *
     * @return an arraylist of all possible positions to place the reviving piece
     */
    public static ArrayList<int[][]> availableMovesRevive(String team){
        ArrayList<int[][]> moves = new ArrayList<>();

        if(team.equals("Red")){
            for(int i = 5; i < 8; i++){
                for(int j = 0; j < 10; j++){
                    if(positions[i][j] == null){
                        moves.add(new int[][]{{i}, {j}});
                    }
                }
            }
        }else{
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 10; j++){
                    if(positions[i][j] == null){
                        moves.add(new int[][]{{i}, {j}});
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Function that returns all the available positions a piece
     * can move on the board.
     *
     * @param pos the position of the piece
     *
     * @return an arraylist of all possible positions the piece can move
     */
    public static ArrayList<int[][]> availableMoves(int[][] pos){
        ArrayList<int[][]> moves = new ArrayList<>();
        String team = positions[pos[0][0]][pos[1][0]].getTeam();
        boolean noFallBack = getNoFallBack();
        int behind;

        if(!(positions[pos[0][0]][pos[1][0]] instanceof Scout)) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (Math.abs(i + j) == 2 || Math.abs(i + j) == 0) continue;
                    try {
                        if(!noFallBack) {
                            if (positions[pos[0][0] + i][pos[1][0] + j] == null ||
                                    (!(team.equals(positions[pos[0][0] + i][pos[1][0] + j].getTeam())) &&
                                            !(positions[pos[0][0] + i][pos[1][0] + j] instanceof Barrier))) {
                                moves.add(new int[][]{{pos[0][0] + i}, {pos[1][0] + j}});
                            }
                        }else{
                            if(positions[pos[0][0]][pos[1][0]].getTeam().equals("Red")){
                                behind = 1;
                            }else{
                                behind = -1;
                            }
                            if(i == behind){
                                if(positions[pos[0][0] + i][pos[1][0] + j] != null){
                                    if(!(team.equals(positions[pos[0][0] + i][pos[1][0] + j].getTeam()))){
                                        if(!(positions[pos[0][0] + i][pos[1][0] + j] instanceof Barrier)){
                                            moves.add(new int[][]{{pos[0][0] + i}, {pos[1][0] + j}});
                                        }
                                    }
                                }
                            }else{
                                if (positions[pos[0][0] + i][pos[1][0] + j] == null ||
                                        (!(team.equals(positions[pos[0][0] + i][pos[1][0] + j].getTeam())) &&
                                                !(positions[pos[0][0] + i][pos[1][0] + j] instanceof Barrier))) {
                                    moves.add(new int[][]{{pos[0][0] + i}, {pos[1][0] + j}});
                                }
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }else{
            try{
                int i = 1, j = 1;

                while(true){
                    try{
                        if (checkMoveY(pos, moves, team, i)) break;
                        i++;
                    }catch(Exception exception){
                        break;
                    }
                }
                i = -1;

                while(true){
                    try{
                        if (checkMoveY(pos, moves, team, i)) break;
                        i--;
                    }catch(Exception exception){
                        break;
                    }
                }
                while(true){
                    try{
                        if (checkMoveX(pos, moves, team, j)) break;
                        j++;
                    }catch(Exception exception){
                        break;
                    }
                }
                j = -1;

                while(true){
                    try{
                        if (checkMoveX(pos, moves, team, j)) break;
                        j--;
                    }catch(Exception exception){
                        break;
                    }
                }
            } catch (Exception ignored){
            }
        }

        return moves;
    }

    /**
     * Function that checks if a position is valid for the piece to move on the x-axis,
     * and if it is, it adds it to an arraylist.
     *
     * @param pos the position of the piece
     * @param moves the arraylist of possible moves
     * @param team the team of the piece
     * @param j the offset from the piece's position to the new position
     *
     * @return true if it cannot move any further from the offset j, false otherwise
     */
    private static boolean checkMoveX(int[][] pos, ArrayList<int[][]> moves, String team, int j) {
        if(positions[pos[0][0]][pos[1][0] + j] == null){
            moves.add(new int[][]{{pos[0][0]}, {pos[1][0] + j}});
        } else if(positions[pos[0][0]][pos[1][0] + j] instanceof Barrier || team.equals(positions[pos[0][0]][pos[1][0] + j].getTeam())){
            return true;
        }else{
            moves.add(new int[][]{{pos[0][0]}, {pos[1][0] + j}});

            return true;
        }
        return false;
    }

    /**
     * Function that checks if a position is valid for the piece to move on the y-axis,
     * and if it is, it adds it to an arraylist.
     *
     * @param pos the position of the piece
     * @param moves the arraylist of possible moves
     * @param team the team of the piece
     * @param i the offset from the piece's position to the new position
     *
     * @return true if it cannot move any further from the offset i, false otherwise
     */
    private static boolean checkMoveY(int[][] pos, ArrayList<int[][]> moves, String team, int i) {
        boolean noFallBack = getNoFallBack();

        if(!noFallBack) {
            if (positions[pos[0][0] + i][pos[1][0]] == null) {
                moves.add(new int[][]{{pos[0][0] + i}, {pos[1][0]}});
            } else if (positions[pos[0][0] + i][pos[1][0]] instanceof Barrier || team.equals(positions[pos[0][0] + i][pos[1][0]].getTeam())) {
                return true;
            } else {
                moves.add(new int[][]{{pos[0][0] + i}, {pos[1][0]}});

                return true;
            }
            return false;
        }else{
            String pieceTeam = positions[pos[0][0]][pos[1][0]].getTeam();
            if (positions[pos[0][0] + i][pos[1][0]] == null) {
                if(pieceTeam.equals("Red")){
                    if(i < 0){
                        moves.add(new int[][]{{pos[0][0] + i}, {pos[1][0]}});
                    }
                }else{
                    if(i > 0){
                        moves.add(new int[][]{{pos[0][0] + i}, {pos[1][0]}});
                    }
                }
                return false;
            } else if (positions[pos[0][0] + i][pos[1][0]] instanceof Barrier || team.equals(positions[pos[0][0] + i][pos[1][0]].getTeam())) {
                return true;
            } else {
                moves.add(new int[][]{{pos[0][0] + i}, {pos[1][0]}});

                return true;
            }
        }
    }
}