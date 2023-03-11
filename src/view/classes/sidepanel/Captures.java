package view.classes.sidepanel;

import model.classes.Utilities;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import static controller.classes.Controller.changeTurnsThread;

/**
 * Captures class that extends JPanel.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Captures extends JPanel{

    /**
     * The capture panel.
     */
    JPanel capturePanel = new JPanel();

    /**
     * Arraylist of strings that hold the images of the pieces inside the panel.
     */
    ArrayList<String> images = new ArrayList<>();

    /**
     * Arraylist of captures that hold all the captures inside the panel.
     */
    ArrayList<Capture> captures = new ArrayList<>();

    /**
     * Constructor.
     */
    public Captures(){
        super();

        capturePanel.setLayout(new GridLayout(3, 3));

        this.setSize(Utilities.getHEIGHT() / 3, 440);

        this.add(capturePanel);
    }

    /**
     * Function that adds a capture to the panel.
     * @param image the path to the image of the piece
     */
    public void addCapture(String image){
        captures.add(new Capture(image));
        images.add(image);
    }

    /**
     * Function that removes a capture from the panel.
     *
     * @param removedCapture the capture to be removed
     */
    public void removeCapture(Capture removedCapture){
        synchronized (changeTurnsThread) {
            try {
                for (Capture capture : captures) {
                    if (capture == removedCapture) {
                        captures.remove(capture);

                        changeTurnsThread.notify();
                    }
                }
            }catch(Exception e){
                for (Capture capture : captures) {
                    if (capture == removedCapture) {
                        captures.remove(capture);

                        changeTurnsThread.notify();
                    }
                }
            }
        }
    }

    /**
     * Function that returns an arraylist of all captures inside the panel.
     *
     * @return an arraylist of all captures inside the panel.
     */
    public ArrayList<Capture> getCaptures(){
        return this.captures;
    }

    /**
     * Function that returns an arraylist of the paths of all the images of
     * all the captures inside the panel.
     *
     * @return the arraylist
     */
    public ArrayList<String> getImages(){
        return this.images;
    }
}