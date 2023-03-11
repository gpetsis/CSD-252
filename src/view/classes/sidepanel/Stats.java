package view.classes.sidepanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Stats class that extends JPanel.
 * This class shows some data for the attacking player,
 * which change when the attacking player changes.
 * 
 * @author Ioannis Petsis csd4993
 */
public class Stats extends JPanel{

    /**
     * Label that holds the title of the panel.
     */
    public JLabel stats;

    /**
     * Label that tells the player's turn.
     */
    public JLabel playerTurn;

    /**
     * Label that holds the percentage of successful attacks.
     */
    public JLabel percentage;

    /**
     * Label that holds the number of revives the player has done.
     */
    public JLabel revives;

    /**
     * Label that tells which player plays now.
     */
    public JLabel turn;

    /**
     * Label that holds the title of the captures panel.
     */
    public JLabel captures;

    /**
     * Constructor.
     */
    public Stats(){
        super();

        stats = new JLabel("  Στατιστικά:");
        playerTurn = new JLabel("Red player's turn");
        percentage = new JLabel("Ποσοστό επιτ. επίθεσης: 0.0%");
        captures = new JLabel("Αιχμαλωτίσεις:");
        revives = new JLabel();
        turn = new JLabel();

        stats.setFont(new Font("Arial", Font.BOLD, 25));
        stats.setBorder(new EmptyBorder(10, 10, 30, 10));

        playerTurn.setFont(new Font("Arial", Font.PLAIN, 22));

        percentage.setFont(new Font("Arial", Font.PLAIN, 15));

        revives.setFont(new Font("Arial", Font.PLAIN, 15));
        revives.setText("Διασώσεις: 0                   ");

        turn.setFont(new Font("Arial", Font.PLAIN, 15));
        turn.setText("Γύρος: 1");

        captures.setFont(new Font("Arial", Font.BOLD, 25));
        captures.setBorder(new EmptyBorder(80, 30, 0, 30));


        this.add(stats);
        this.add(playerTurn);
        this.add(percentage);
        this.add(revives);
        this.add(turn);
        this.add(captures);
    }
}