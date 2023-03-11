package controller.classes;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.classes.*;
import model.classes.pieces.ImmovablePiece;
import model.classes.pieces.MovablePiece;
import model.classes.pieces.Piece;
import model.classes.Board;
import model.classes.pieces.PieceWrapper;
import model.classes.pieces.immovable.Barrier;
import model.classes.pieces.immovable.Trap;
import model.classes.pieces.movable.*;
import model.classes.pieces.movable.specialmovable.Dwarf;
import model.classes.pieces.movable.specialmovable.Scout;
import model.classes.pieces.movable.specialmovable.Slayer;

import view.classes.EndScreen;
import view.classes.PieceButton;
import view.classes.StartingScreen;
import view.classes.View;

import javax.swing.*;

import static model.classes.Board.*;
import static model.classes.Model.*;

import static view.classes.BoardView.boardViewChangeImage;
import static view.classes.BoardView.pieceButtons;

/**
 * Controller Class.
 * The controller is handles the communication
 * between the view and the model.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Controller {

    /**
     * The red player.
     */
    static Player redPlayer;

    /**
     * The blue player.
     */
    static Player bluePlayer;

    /**
     * The View class.
     */
    static View view;

    /**
     * The starting screen.
     */
    static StartingScreen startingScreen;

    /**
     * The Board class.
     */
    static Board board;

    /**
     * Boolean variable to tell if the user has pressed the button to move.
     */
    static boolean iconSelected;

    /**
     * Boolean variable to tell if the user is in revive state.
     */
    static boolean revive = false;

    /**
     * Temporary variable for the PieceButton.
     */
    static PieceButton selectedButton;

    /**
     * List of available positions for the piece to move.
     */
    static ArrayList<int[][]> moves;

    /**
     * List of available positions to revive a piece.
     */
    static ArrayList<int[][]> reviveMoves;

    /**
     * Temporary variable that holds the revived piece name.
     */
    static String revivedPieceName;

    /**
     * Temporary variable that holds the revived piece team.
     */
    static String revivedPieceTeam;

    /**
     * Temporary variable that holds the revived piece image.
     */
    static String revivedPieceImage;

    /**
     * Variables that hold the x and y coordinates of the previous pressed button.
     */
    static int selectedX, selectedY;

    /**
     * Thread for the attacking event.
     */
    static Thread attackThread;

    /**
     * Global Thread for changing the turns of the game.
     */
    public static Thread changeTurnsThread;

    /**
     * Constructor.
     */
    public Controller(){
        startingScreen = new StartingScreen();
    }

    /**
     * Initializing the controller.
     */
    public static void controllerInitialize(){
        redPlayer = new Player("Red", StartingScreen.getReducedArmy());
        bluePlayer = new Player("Blue", StartingScreen.getReducedArmy());

        view = new View(redPlayer, bluePlayer);

        board = new Board(redPlayer.getPieces(), bluePlayer.getPieces());
    }

    /**
     * Main function.
     */
    public static void main(String[] args){
        Controller controller = new Controller();
    }

    /**
     * Function to swap 2 pieces on the board.
     *
     * @param aWrapper the first piece
     * @param bWrapper the second piece
     */
    public static void swap(PieceWrapper aWrapper, PieceWrapper bWrapper){
        Piece temp = aWrapper.piece;

        aWrapper.piece = bWrapper.piece;
        bWrapper.piece = temp;
    }

    /**
     * Function that handles the change of turns.
     * Updates the view and the model.
     */
    public static void changeTurns(){
        int i, j;

        for(i = 0; i < 8; i++){
            for(j = 0; j < 10; j++){
                if(turn.equals("Red")){
                    try{
                        if(positions[i][j] == null){
                            pieceButtons[i][j].setIcon(null);
                            pieceButtons[i][j].setBackground(Color.WHITE);

                            continue;
                        }
                        if(positions[i][j].getTeam().equals("Blue")){
                            int finalI = i;
                            int finalJ = j;

                            Thread thread = new Thread(() -> boardViewChangeImage("Blue", "blueHidden.png", finalI, finalJ));
                            thread.start();
//                            boardViewChangeImage("Blue", "blueHidden.png", finalI, finalJ);
                        }else if(positions[i][j].getTeam().equals("Red")){
                            int finalI = i;
                            int finalJ = j;

                            Thread thread = new Thread(() -> boardViewChangeImage("Red", positions[finalI][finalJ].getName(), finalI, finalJ));
                            thread.start();
//                            boardViewChangeImage("Red", positions[finalI][finalJ].getName(), finalI, finalJ);
                        }
                    } catch (Exception ignored){
                    }
                }else{
                    try{
                        if(positions[i][j] == null){
                            pieceButtons[i][j].setIcon(null);
                            pieceButtons[i][j].setBackground(Color.WHITE);

                            continue;
                        }
                        if(positions[i][j].getTeam().equals("Red")){
                            int finalI = i;
                            int finalJ = j;

                            Thread thread = new Thread(() -> boardViewChangeImage("Red", "redHidden.png", finalI, finalJ));
                            thread.start();
//                            boardViewChangeImage("Red", "redHidden.png", finalI, finalJ);
                        }else if(positions[i][j].getTeam().equals("Blue")){
                            int finalI = i;
                            int finalJ = j;

                            Thread thread = new Thread(() -> boardViewChangeImage("Blue", positions[finalI][finalJ].getName(), finalI, finalJ));
                            thread.start();
//                            boardViewChangeImage("Blue", positions[finalI][finalJ].getName(), finalI, finalJ);
                        }
                    }catch (Exception ignored){
                    }
                }
            }
        }

        redPlayer.updateSuccAttackPercentage();
        bluePlayer.updateSuccAttackPercentage();

        view.updateChanges(redPlayer, bluePlayer);
    }

    /**
     * The MouseListener for the buttons on the board.
     */
    public static class PieceListener implements MouseListener {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            PieceButton button = (PieceButton) e.getSource();

            if(revive){
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 10; j++){
                        if(pieceButtons[i][j] == button){
                            for(int[][] move : reviveMoves){
                                if(move[0][0] == i && move[1][0] == j){
                                    switch(revivedPieceName){
                                        case "Dwarf": {
                                            positions[i][j] = new Dwarf(revivedPieceTeam);
                                            break;
                                        } case "Scout": {
                                            positions[i][j] = new Scout(revivedPieceTeam);
                                            break;
                                        } case "Slayer": {
                                            positions[i][j] = new Slayer(revivedPieceTeam);
                                            break;
                                        } case "Beast Rider": {
                                            positions[i][j] = new BeastRider(revivedPieceTeam);
                                            break;
                                        } case "Dragon": {
                                            positions[i][j] = new Dragon(revivedPieceTeam);
                                            break;
                                        } case "Elf": {
                                            positions[i][j] = new Elf(revivedPieceTeam);
                                            break;
                                        } case "Knight": {
                                            positions[i][j] = new Knight(revivedPieceTeam);
                                            break;
                                        } case "Lava Beast": {
                                            positions[i][j] = new LavaBeast();
                                            break;
                                        } case "Mage": {
                                            positions[i][j] = new Mage(revivedPieceTeam);
                                            break;
                                        } case "Sorceress": {
                                            positions[i][j] = new Sorceress(revivedPieceTeam);
                                            break;
                                        } case "Yeti": {
                                            positions[i][j] = new Yeti();
                                            break;
                                        } default: { /* Do nothing */ }
                                    }

                                    revivedPieceImage = positions[i][j].getImage();
                                    view.removeCapture(revivedPieceImage, revivedPieceTeam);

                                    if(revivedPieceTeam.equals("Red")){
                                        redPlayer.removeCapturedPiece(revivedPieceName);
                                    }else{
                                        bluePlayer.removeCapturedPiece(revivedPieceName);
                                    }
                                }
                            }
                        }
                    }
                }
                revive = false;

                synchronized (changeTurnsThread){
                    changeTurnsThread.notify();

                    for(int i = 0; i < 8; i++){
                        for(int j = 0; j < 10; j++){
                            pieceButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                        }
                    }
                }
            } else if (iconSelected && button != selectedButton) {
                Icon selectedIcon = selectedButton.getIcon();

                for(int i = 0; i < 8; i++) {
                    for(int j = 0; j < 10; j++) {
                        try {
                            if(pieceButtons[i][j] == button) {
                                for(int[][] move : moves){
                                    if(move[0][0] == i && move[1][0] == j){
                                        if(positions[move[0][0]][move[1][0]] != null){
                                            if(positions[move[0][0]][move[1][0]] instanceof ImmovablePiece){
                                                if(positions[move[0][0]][move[1][0]] instanceof Trap){
                                                    int finalI = i;
                                                    int finalJ = j;

                                                    if(positions[selectedX][selectedY] instanceof Dwarf){
                                                        Thread attackThread = new Thread() {
                                                            public void run() {
                                                                try {
                                                                    attackerWins(finalI, finalJ);

                                                                    view.repaint();
                                                                    sleep(990);

                                                                    movePiece(button, selectedIcon, finalI, finalJ);
                                                                } catch (InterruptedException ex) {
                                                                    throw new RuntimeException(ex);
                                                                }
                                                            }
                                                        };
                                                        attackThread.start();
                                                    }else {
                                                        Thread defendThread = new Thread() {
                                                            public void run() {
                                                                try {
                                                                    String buttonTeam = positions[finalI][finalJ].getTeam();
                                                                    String buttonName = positions[finalI][finalJ].getName();

                                                                    String selectedButtonImage = positions[selectedX][selectedY].getImage();
                                                                    String selectedButtonName = positions[selectedX][selectedY].getName();

                                                                    defenderWins(finalI, finalJ);
                                                                    boardViewChangeImage(buttonTeam, buttonName, finalI, finalJ);

                                                                    if(!selectedButtonName.equals("Slayer")){
                                                                        view.addCapture(selectedButtonImage, buttonTeam);
                                                                    }
                                                                    sleep(990);

                                                                    pieceButtons[selectedX][selectedY].setBackground(Color.WHITE);
                                                                    pieceButtons[selectedX][selectedY].setIcon(null);
                                                                } catch (InterruptedException ex) {
                                                                    throw new RuntimeException(ex);
                                                                }

                                                            }
                                                        };
                                                        defendThread.start();
                                                    }
                                                }else{ // Flag, game ends
                                                    int finalI = i;
                                                    int finalJ = j;

                                                    Thread endThread = new Thread(){
                                                        public void run(){
                                                            try{
                                                                attackerWins(finalI, finalJ);

                                                                view.repaint();
                                                                sleep(990);
                                                                view.setVisible(false);

                                                                EndScreen endScreen = new EndScreen(positions[selectedX][selectedY].getTeam());
                                                            }catch(Exception ignored){
                                                            }
                                                        }
                                                    };
                                                    endThread.start();
                                                }
                                            }else if(positions[move[0][0]][move[1][0]] instanceof MovablePiece){
                                                int defRank = ((MovablePiece) positions[move[0][0]][move[1][0]]).getRank();
                                                int attRank = ((MovablePiece) positions[selectedX][selectedY]).getRank();

                                                if(defRank > attRank) {
                                                    int finalI;
                                                    int finalJ;

                                                    if (defRank == 10 && attRank == 1) {
                                                        finalJ = j;
                                                        finalI = i;

                                                        attackThread = new Thread() {
                                                            public void run() {
                                                                try {
                                                                    attackerWins(finalI, finalJ);

                                                                    view.repaint();
                                                                    sleep(990);

                                                                    movePiece(button, selectedIcon, finalI, finalJ);
                                                                } catch (InterruptedException ex) {
                                                                    throw new RuntimeException(ex);
                                                                }
                                                            }
                                                        };
                                                        attackThread.start();
                                                    } else {
                                                        finalI = i;
                                                        finalJ = j;

                                                        Thread defendThread = new Thread() {
                                                            public void run() {
                                                                try {
                                                                    String buttonTeam = positions[finalI][finalJ].getTeam();
                                                                    String buttonName = positions[finalI][finalJ].getName();

                                                                    String selectedButtonImage = positions[selectedX][selectedY].getImage();
                                                                    String selectedButtonName = positions[selectedX][selectedY].getName();

                                                                    defenderWins(finalI, finalJ);
                                                                    boardViewChangeImage(buttonTeam, buttonName, finalI, finalJ);

                                                                    if(!selectedButtonName.equals("Slayer")){
                                                                        view.addCapture(selectedButtonImage, buttonTeam);
                                                                    }

                                                                    sleep(990);

                                                                    pieceButtons[selectedX][selectedY].setBackground(Color.WHITE);
                                                                    pieceButtons[selectedX][selectedY].setIcon(null);
                                                                } catch (InterruptedException ex) {
                                                                    throw new RuntimeException(ex);
                                                                }
                                                            }
                                                        };
                                                        defendThread.start();
                                                    }
                                                }else if(defRank < attRank){
                                                    int finalJ = j;
                                                    int finalI = i;

                                                    attackThread = new Thread(){
                                                        public void run(){
                                                            try {
                                                                attackerWins(finalI, finalJ);
                                                                view.repaint();
                                                                sleep(990);

                                                                movePiece(button, selectedIcon, finalI, finalJ);
                                                            } catch (InterruptedException ex) {
                                                                throw new RuntimeException(ex);
                                                            }
                                                        }
                                                    };
                                                    attackThread.start();
                                                }else{
                                                    int finalI = i;
                                                    int finalJ = j;

                                                    Thread drawThread = new Thread(){
                                                        public void run(){
                                                            try {
                                                                noWinnerAttack(finalI, finalJ, false);
                                                                sleep(990);
                                                                pieceButtons[finalI][finalJ].setBackground(Color.WHITE);
                                                                pieceButtons[finalI][finalJ].setIcon(null);
                                                                pieceButtons[selectedX][selectedY].setBackground(Color.WHITE);
                                                                pieceButtons[selectedX][selectedY].setIcon(null);
                                                            } catch (InterruptedException ex) {
                                                                throw new RuntimeException(ex);
                                                            }
                                                        }
                                                    };
                                                    drawThread.start();
                                                }
                                            }
                                        }else{
                                            int finalI = i;
                                            int finalJ = j;
//                                            Thread moveThread = new Thread(){
//                                                public void run(){
                                            movePiece(button, selectedIcon, finalI, finalJ);
//                                                }
//                                            };
//                                            moveThread.start();
                                        }
                                        if(turn.equals("Red")){
                                            setTurn("Blue");
                                        }else{
                                            setTurn("Red");
                                            nextTurn();
                                        }
                                        changeTurnsThread = new Thread(){
                                            public void run(){
                                                try {
                                                    sleep(2000);
                                                    synchronized (this){
                                                        if(revive){
                                                            wait();
                                                        }
                                                    }
                                                    changeTurns();
                                                    String winningTeam = checkForEndGame();
                                                    if(!winningTeam.equals("None")){
                                                        EndScreen endScreen = new EndScreen(winningTeam);
                                                    }
                                                } catch (InterruptedException ignored) {
                                                }
                                            }
                                        };
                                        changeTurnsThread.start();
                                    }
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }

                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 10; j++){
                        pieceButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    }
                }
                view.repaint();

                selectedButton = button;
                iconSelected = false;
            } else if (!iconSelected) {
                boolean found = false;
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 10; j++){
                        try{
                            if(pieceButtons[i][j] == button){
                                if(positions[i][j] instanceof MovablePiece && positions[i][j].getTeam().equals(turn)){
                                    selectedX = i;
                                    selectedY = j;

                                    found = true;

                                    moves = availableMoves(new int[][] {{i}, {j}});
                                    for (int[][] move : moves) {
                                        if(positions[move[0][0]][move[1][0]] != null && !(positions[move[0][0]][move[1][0]] instanceof Barrier)){
                                            pieceButtons[move[0][0]][move[1][0]].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
                                        }else{
                                            pieceButtons[move[0][0]][move[1][0]].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                                        }
                                    }
                                }
                            }
                        }catch (Exception ignored){
                        }
                    }
                }
                if(found){
                    iconSelected = true;
                    selectedButton = button;
                    button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                }
            } else {
                iconSelected = false;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        pieceButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    }
                }
            }
        }

        /**
         * Function to handle the successful attack of a piece.
         *
         * @param i the x coordinate of the non-attacking piece
         * @param j the y coordinate of the non-attacking piece
         */
        private static void attackerWins(int i, int j){
            if(positions[i][j].getTeam().equals("Red")) {
                redPlayer.addPieceCaptured(positions[i][j]);

                bluePlayer.incrementAttacks();
                bluePlayer.incrementSuccAttacks();
            }else{
                bluePlayer.addPieceCaptured(positions[i][j]);

                redPlayer.incrementAttacks();
                redPlayer.incrementSuccAttacks();
            }

            boardViewChangeImage(positions[i][j].getTeam(), positions[i][j].getName(), i, j);

            if(!positions[i][j].getName().equals("Slayer") && !positions[i][j].getName().equals("Trap")){
                view.addCapture(positions[i][j].getImage(), positions[selectedX][selectedY].getTeam());
            }

            positions[i][j] = null;
        }

        /**
         * Function to handle the failed attack of a piece.
         *
         * @param i the x coordinate of the defending piece
         * @param j the y coordinate of the defending piece
         */
        private static void defenderWins(int i, int j){
            if(positions[i][j].getTeam().equals("Red")) {
                bluePlayer.addPieceCaptured(positions[selectedX][selectedY]);
                bluePlayer.incrementAttacks();
            }else{
                redPlayer.addPieceCaptured(positions[selectedX][selectedY]);
                redPlayer.incrementAttacks();
            }
            positions[selectedX][selectedY] = null;
        }

        /**
         * Function to handle the draw when a piece attacked.
         *
         * @param i the x coordinate of the defending piece
         * @param j the y coordinate of the defending piece
         * @param isTrap boolean value to tell if the defending piece is a Trap
         */
        private void noWinnerAttack(int i, int j, boolean isTrap) {
            if(positions[i][j].getTeam().equals("Red")){
                redPlayer.addPieceCaptured(positions[i][j]);

                bluePlayer.addPieceCaptured(positions[selectedX][selectedY]);
                bluePlayer.incrementAttacks();
            }else{
                bluePlayer.addPieceCaptured(positions[i][j]);

                redPlayer.addPieceCaptured(positions[selectedX][selectedY]);
                redPlayer.incrementAttacks();
            }

            boardViewChangeImage(positions[i][j].getTeam(), positions[i][j].getName(), i, j);
            if(!isTrap){
                if(!positions[i][j].getName().equals("Slayer")){
                    view.addCapture(positions[i][j].getImage(), positions[selectedX][selectedY].getTeam());
                    view.addCapture(positions[selectedX][selectedY].getImage(), positions[i][j].getTeam());
                }
            }else{
                view.addCapture(positions[selectedX][selectedY].getImage(), positions[i][j].getTeam());
            }

            positions[i][j] = null;
            positions[selectedX][selectedY] = null;
        }

        /**
         * Function to move a piece on the board, and update
         * the view as well as the model.
         *
         * @param button the moving piece
         * @param selectedIcon the icon of the button it was before
         * @param i the x coordinate of the new position
         * @param j the y coordinate of the new position
         */
        private void movePiece(PieceButton button, Icon selectedIcon, int i, int j) {
            button.setIcon(selectedIcon);
            button.setBackground(null);

            selectedButton.setBackground(Color.WHITE);
            selectedButton.setIcon(null);

            PieceWrapper aWrapper = new PieceWrapper(positions[i][j]);
            PieceWrapper bWrapper = new PieceWrapper(positions[selectedX][selectedY]);

            swap(aWrapper, bWrapper);

            positions[i][j] = aWrapper.piece;
            positions[selectedX][selectedY] = bWrapper.piece;

            if(positions[i][j].getTeam().equals("Red")){
                if(i == 0 && redPlayer.getNoRevives() != 2 && !positions[i][j].isHasRevived() && !(positions[i][j] instanceof Scout)){
                    revive = true;
                    revivedPieceTeam = "Red";
                    revivePanelAndBorders(i, j, redPlayer);
                }
            }else{
                if(i == 7 && bluePlayer.getNoRevives() != 2 && !positions[i][j].isHasRevived() && !(positions[i][j] instanceof Scout)){
                    revive = true;
                    revivedPieceTeam = "Blue";
                    revivePanelAndBorders(i, j, bluePlayer);
                }
            }
        }

        /**
         * Function to revive a piece back on the board.
         *
         * @param i the x coordinate of the piece that triggered the revive
         * @param j the y coordinate of the piece that triggered the revive
         * @param player the player that has the reviving piece
         */
        private void revivePanelAndBorders(int i, int j, Player player) {
            boolean ableToRevive = false;
            String[] choices = new String[player.getCapturedPieces().size()];
            if(player.getCapturedPieces().size() != 0) {
                for (int l = 0; l < player.getCapturedPieces().size(); l++) {
                    choices[l] = player.getCapturedPieces().get(l).getName();
                }
                revivedPieceName = (String) JOptionPane.showInputDialog(null, "Piece:", "Choose piece to revive", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
                reviveMoves = availableMovesRevive(positions[i][j].getTeam());

                for (int[][] move : reviveMoves) {
                    ableToRevive = true;
                    pieceButtons[move[0][0]][move[1][0]].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                }

                if(!ableToRevive){
                    revive = false;
                }else{
                    player.setNoRevives(player.getNoRevives() + 1);
                    positions[i][j].setHasRevived();
                }
            }else{
                revive = false;
            }
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
        }
    }

    /**
     * Function that checks if the game has ended,
     * meaning if there are any possible moves to make.
     *
     * @return Red, if red team won, Blue if blue team won, None otherwise.
     */
    static String checkForEndGame(){
        boolean redExists = false;
        boolean blueExists = false;

        ArrayList<int[][]> possibleMoves;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 10; j++){
                if(positions[i][j] instanceof MovablePiece){
                    possibleMoves = availableMoves(new int[][]{{i}, {j}});

                    if(possibleMoves.size() != 0){
                        if(positions[i][j].getTeam().equals("Red")){
                            redExists = true;
                        }else{
                            blueExists = true;
                        }
                    }
                }
            }
        }

        if(redExists && !blueExists){
            return "Red";
        }else if(!redExists && blueExists){
            return "Blue";
        }else{
            return "None";
        }
    }
}