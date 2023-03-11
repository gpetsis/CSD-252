package view.classes;

import model.classes.Player;
import model.classes.Utilities;

import view.classes.sidepanel.SidePanel;

import javax.swing.*;
import java.awt.*;

import static model.classes.Model.getTurn;
import static model.classes.Model.turn;

/**
 * View class that extends JFrame.
 * This is where all the graphics will be displayed.
 * 
 * @author Ioannis Petsis csd4993
 */
public class View extends JFrame{

    private static SidePanel sidepanel;

    /**
     * Initializing the view for when the game starts
     *
     * @param redPlayer the red player
     * @param bluePlayer the blue player
     */
    public void viewInitialize(Player redPlayer, Player bluePlayer){
        BoardView boardview = new BoardView(redPlayer, bluePlayer);

        sidepanel = new SidePanel();

        this.setLayout(new BorderLayout());

        this.add(boardview, BorderLayout.WEST);
        this.add(sidepanel, BorderLayout.CENTER);
    }

    /**
     * Constructor.
     */
    public View(Player red, Player blue){
        super("Stratego By Ioannis Petsis");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(Utilities.getWIDTH() + 333, Utilities.getHEIGHT() + 28));

        this.viewInitialize(red, blue);

        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Function that updates the view.
     *
     * @param red the red player
     * @param blue the blue player
     */
    public void updateChanges(Player red, Player blue){
        sidepanel.getStatsPanel().playerTurn.setText(turn + " player's turn");

        if(turn.equals("Red")){
            sidepanel.getStatsPanel().revives.setText("Διασώσεις: " + red.getNoRevives() + "                   ");

            sidepanel.getStatsPanel().percentage.setText("Ποσοστό επιτ. επίθεσης: " + red.getPercentageOfSuccAttacks() + "%");

            sidepanel.getStatsPanel().turn.setText("Γυρος: " + getTurn());
        }else{
            sidepanel.getStatsPanel().revives.setText("Διασώσεις: " + blue.getNoRevives() + "                   ");

            sidepanel.getStatsPanel().percentage.setText("Ποσοστό επιτ. επίθεσης: " + blue.getPercentageOfSuccAttacks() + "%");

            sidepanel.getStatsPanel().turn.setText("Γυρος: " + getTurn());
        }

        sidepanel.setCapturePanel();

        this.repaint();
    }

    /**
     * Function that adds a capture to the panel.
     *
     * @param image the path to the image of the piece
     * @param team the team of the captured piece
     */
    public void addCapture(String image, String team){
        sidepanel.addCapture(image, team);

        this.repaint();
    }

    /**
     * Function that removes a capture from the panel.
     *
     * @param pieceImage the path to the image of the piece
     * @param pieceTeam the team of the reviving piece
     */
    public void removeCapture(String pieceImage, String pieceTeam){
        sidepanel.removeCapture(pieceImage, pieceTeam);
    }
}