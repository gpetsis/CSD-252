package view.classes;

import model.classes.Utilities;

import javax.swing.*;
import java.awt.*;

/**
 * PieceButton class that extends JButton.
 * This is the buttons that are placed on the board.
 *
 * @author Ioannis Petsis csd4993
 */
public class PieceButton extends JButton{

    /**
     * Constructor.
     *
     * @param path the path to the image of the piece
     */
    public PieceButton(String path){
        super();

        ImageIcon imageicon = new ImageIcon(path);
        Image image = imageicon.getImage().getScaledInstance(Utilities.getWIDTH() / 10, Utilities.getHEIGHT() / 8, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(image);

        this.setIcon(icon);
        this.setPreferredSize(new Dimension(Utilities.getWIDTH() / 10, Utilities.getHEIGHT() / 8));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setBackground(null);
    }

    /**
     * Constructor.
     *
     * @param color the color of the piece
     */
    public PieceButton(Color color){
        super();

        this.setBackground(color);
        this.setIcon(null);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setPreferredSize(new Dimension(Utilities.getWIDTH() / 10, Utilities.getHEIGHT() / 8));
    }
}