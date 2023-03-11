package view.classes;

import controller.classes.Controller.*;

import model.classes.Player;
import model.classes.Utilities;

import javax.swing.*;
import java.awt.*;

import static model.classes.Board.positions;
import static model.classes.pieces.Piece.getPathOfImage;
import static view.classes.StartingScreen.getReducedArmy;


/**
 * BoardView class that extends JPanel.
 * This class holds the visual aspect 
 * of the board of the game.
 * 
 * @author Ioannis Petsis csd4993
 */
public class BoardView extends JPanel{

    /**
     * The positions on the board.
     */
    public static PieceButton[][] pieceButtons = new PieceButton[8][10];

    /**
     * Constructor.
     */
    public BoardView(Player redPlayer, Player bluePlayer){
        super();

        int i, j;
        int lessPieces;

        this.setLayout(new GridLayout(8, 10));
        this.setSize(Utilities.getWIDTH(), Utilities.getHEIGHT());

        if(getReducedArmy()){
            lessPieces = 2;
        }else{
            lessPieces = 1;
        }

        for(i = 0; i < 3; i++){
            for(j = 0; j < 10; j++){
                if(bluePlayer.getPieces().get(i * 10 + j).getImage() != null){
                    pieceButtons[i][j] = new PieceButton(bluePlayer.getPieces().get(i * 10 + j).getImage());

                    PieceListener pl = new PieceListener();
                    pieceButtons[i][j].addMouseListener(pl);

                    this.add(pieceButtons[i][j]);
                }else{
                    pieceButtons[i][j] = new PieceButton(Color.WHITE);

                    PieceListener pl = new PieceListener();
                    pieceButtons[i][j].addMouseListener(pl);

                    this.add(pieceButtons[i][j]);
                }
            }
        }

        for(; i < 5; i++){
            for(j = 0; j < 10; j++){
                if(j >= 2 && j <= 3 || j >= 6 && j <= 7){
                    pieceButtons[i][j] = new PieceButton(Color.YELLOW);
                }else{
                    pieceButtons[i][j] = new PieceButton(Color.WHITE);
                }
                PieceListener pl = new PieceListener();
                pieceButtons[i][j].addMouseListener(pl);

                this.add(pieceButtons[i][j]);
            }
        }

        for(; i < 8; i++){
            for(j = 0; j < 10; j++){
                if(redPlayer.getPieces().get((i - 5) * 10 + j).getImage() != null){
                    pieceButtons[i][j] = new PieceButton(redPlayer.getPieces().get((i - 5) * 10 + j).getImage());

                    PieceListener pl = new PieceListener();
                    pieceButtons[i][j].addMouseListener(pl);

                    this.add(pieceButtons[i][j]);
                }else{
                    pieceButtons[i][j] = new PieceButton(Color.WHITE);

                    PieceListener pl = new PieceListener();
                    pieceButtons[i][j].addMouseListener(pl);

                    this.add(pieceButtons[i][j]);
                }
            }
        }

        this.setVisible(true);
    }

    public static void boardViewChangeImage(String team, String name, int i, int j){
        ImageIcon imageicon;

        switch (name){
            case "blueHidden.png": {
                imageicon = new ImageIcon(getPathOfImage(team, "blueHidden.png"));
                break;
            } case "redHidden.png": {
                imageicon = new ImageIcon(getPathOfImage(team, "redHidden.png"));
                break;
            } case "Flag": {
                imageicon = new ImageIcon(getPathOfImage(team, "flag"));
                break;
            } case "Trap": {
                imageicon = new ImageIcon(getPathOfImage(team, "trap"));
                break;
            } case "Dwarf": {
                imageicon = new ImageIcon(getPathOfImage(team, "dwarf"));
                break;
            } case "Scout": {
                imageicon = new ImageIcon(getPathOfImage(team, "scout"));
                break;
            } case "Slayer": {
                imageicon = new ImageIcon(getPathOfImage(team, "slayer"));
                break;
            } case "Beast Rider": {
                imageicon = new ImageIcon(getPathOfImage(team, "beastRider"));
                break;
            } case "Dragon": {
                imageicon = new ImageIcon(getPathOfImage(team, "dragon"));
                break;
            } case "Elf": {
                imageicon = new ImageIcon(getPathOfImage(team, "elf"));
                break;
            } case "Knight": {
                imageicon = new ImageIcon(getPathOfImage(team, "knight"));
                break;
            } case "Lava Beast": {
                imageicon = new ImageIcon(getPathOfImage(team, "lavaBeast.png"));
                break;
            } case "Mage": {
                imageicon = new ImageIcon(getPathOfImage(team, "mage"));
                break;
            } case "Sorceress": {
                imageicon = new ImageIcon(getPathOfImage(team, "sorceress"));
                break;
            } case "Yeti": {
                imageicon = new ImageIcon(getPathOfImage(team, "yeti.png"));
                break;
            } default: {
                imageicon = null;
            }
        }

        assert imageicon != null;

        Image image = imageicon.getImage().getScaledInstance(Utilities.getWIDTH() / 10,  Utilities.getHEIGHT() / 8, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(image);

        pieceButtons[i][j].setIcon(icon);
        positions[i][j].setImage(imageicon.getDescription());
    }
}