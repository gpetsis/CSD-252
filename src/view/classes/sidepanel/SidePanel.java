package view.classes.sidepanel;

import model.classes.Utilities;

import javax.swing.*;
import java.awt.*;

import static model.classes.Model.turn;
import static view.classes.StartingScreen.getNoFallBack;
import static view.classes.StartingScreen.getReducedArmy;

/**
 * SidePanel class that extends JPanel.
 * This class shows all the data for the side panel of the game.
 * 
 * @author Ioannis Petsis csd4993
 */
public class SidePanel extends JPanel{

    /**
     * ActiveRules panel.
     */
    static ActiveRules activeRules;

    /**
     * Stats panel.
     */
    Stats stats;

    /**
     * The red player's capture panel.
     */
    Captures redCaptures;

    /**
     * The blue player's capture panel.
     */
    Captures blueCaptures;

    /**
     * Constructor.
     */
    public SidePanel(){
        super();

        this.setSize(new Dimension(Utilities.getWIDTH() / 3, Utilities.getHEIGHT()));

        activeRules = new ActiveRules(getReducedArmy(), getNoFallBack());
        stats = new Stats();
        redCaptures = new Captures();
        blueCaptures = new Captures();

        this.setLayout(new GridLayout(3, 0));

        this.add(activeRules);
        this.add(stats);
        this.add(redCaptures);

        this.setVisible(true);
    }

    /**
     * Adding a captured piece to the Captures panel.
     */
    public void addCapture(String image, String team){
        if(team.equals("Red")){
            addCaptureWithLabel(image, redCaptures);
        }else{
            addCaptureWithLabel(image, blueCaptures);
        }
    }

    /**
     * Function that adds a new capture on the panel.
     *
     * @param image the path to the image of the piece.
     * @param captures the captures panel
     */
    private void addCaptureWithLabel(String image, Captures captures) {
        for(int i = 0; i < captures.getCaptures().size(); i++){
            if(captures.getImages().get(i).equals(image)){
                int number = Integer.parseInt(captures.getCaptures().get(i).getLabel().getText());

                captures.getCaptures().get(i).getLabel().setText(String.valueOf(number + 1));

                return;
            }
        }

        captures.addCapture(image);
    }

    /**
     * Function that returns the stats panel.
     *
     * @return the stats panel
     */
    public Stats getStatsPanel(){ return this.stats; }

    /**
     * Function that updates the captures panel.
     */
    public void setCapturePanel(){
        if(turn.equals("Red")){
            this.remove(blueCaptures);

            redCaptures.removeAll();

            for(Capture capture : redCaptures.getCaptures()){
                redCaptures.add(capture);
            }

            this.add(redCaptures);
            this.validate();
        }else{
            this.remove(redCaptures);

            blueCaptures.removeAll();

            for(Capture capture : blueCaptures.getCaptures()){
                blueCaptures.add(capture);
            }

            this.add(blueCaptures);
            this.validate();
        }
    }

    /**
     * Function that removes a capture from the panel.
     *
     * @param pieceImage the path to the image of the capture
     * @param pieceTeam the team of the captured piece
     */
    public void removeCapture(String pieceImage, String pieceTeam){
        if(pieceTeam.equals("Red")){
            removeCaptureImage(pieceImage, blueCaptures);
        }else{
            removeCaptureImage(pieceImage, redCaptures);
        }
    }

    /**
     * Function that removes a capture from the panel.
     *
     * @param pieceImage the path to the image of the capture
     * @param captures the captures panel
     */
    private void removeCaptureImage(String pieceImage, Captures captures) {
        for(Capture capture : captures.getCaptures()){
            if(capture.getPath().equals(pieceImage)){
                if((capture.getLabel().getText()).equals("1")){
                    captures.removeCapture(capture);
                }else{
                    int number = Integer.parseInt(capture.getLabel().getText());

                    capture.getLabel().setText(String.valueOf(number - 1));
                }

                break;
            }
        }
    }
}