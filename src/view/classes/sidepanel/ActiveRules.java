package view.classes.sidepanel;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * ActiveRules class that extends JPanel.
 * Class that represents the rule data selected from
 * the player
 * 
 * @author Ioannis Petsis csd4993
 */
public class ActiveRules extends JPanel{

    /**
     * The checkbox for the reduced army special rule.
     */
    private static JCheckBox reducedArmy;

    /**
     * The checkbox for the no fall back special rule.
     */
    private static JCheckBox noFallBack;

    /**
     * Label for the title of the panel.
     */
    JLabel rules;

    /**
     * Constructor.
     */
    public ActiveRules(boolean army, boolean fallback){
        super();

        rules = new JLabel("Ενεργοί Κανόνες:");

        reducedArmy = new JCheckBox("Μειωμένος Στρατός");

        noFallBack = new JCheckBox("Καμία Υποχώρηση");

        rules.setFont(new Font("Arial", Font.BOLD, 25));
        rules.setBorder(new EmptyBorder(25, 10, 20, 10));

        reducedArmy.setFont(new Font("Arial", Font.PLAIN, 15));
        reducedArmy.setEnabled(false);
        reducedArmy.setSelected(army);

        noFallBack.setFont(new Font("Arial", Font.PLAIN, 15));
        noFallBack.setEnabled(false);
        noFallBack.setSelected(fallback);

        this.add(rules);
        this.add(reducedArmy);
        this.add(noFallBack);
    }

    /**
     * JCheckBox function that returns the checkbox of the reduced army special rule.
     *
     * @return the reduced army checkbox
     */
    public static JCheckBox getReducedArmyBox(){ return reducedArmy; }

    /**
     * JCheckBox function that returns the checkbox of the no fall back special rule.
     *
     * @return the no fall back checkbox
     */
    public static JCheckBox getNoFallBackBox(){ return noFallBack; }
}