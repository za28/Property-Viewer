import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * PropertyViewerGUI provides the GUI for the project. It displays the property
 * and strings, and it listens to button clicks.
 * It displays statistics about the properties.
 *
 * @author Michael Kölling, David J Barnes, Josh Murphy, and Zahra Amaan K21011879
 * @version 3.0
 */
public class PropertyViewerGUI
{
    // fields:
    private JFrame frame;
    private JPanel propertyPanel;
    private JLabel idLabel;
    private JLabel favouriteLabel;
    
    private JTextField hostIDLabel;
    private JTextField hostNameLabel;
    private JTextField neighbourhoodLabel;
    private JTextField roomTypeLabel;
    private JTextField priceLabel;
    private JTextField minNightsLabel;
    private JTextArea descriptionLabel;
    
    private Property currentProperty;
    private PropertyViewer viewer;
    private boolean fixedSize;
    
    
    private JFrame statsFrame;
    
    private JTextField viewsLabelText;
    private JTextField averagePriceLabelText;
        
    /**
     * Create a PropertyViewer and display its GUI on screen.
     */
    public PropertyViewerGUI(PropertyViewer viewer)
    {
        currentProperty = null;
        this.viewer = viewer;
        fixedSize = false;
        makeFrame();
        this.setPropertyViewSize(400, 250);
    }


    // ---- public view functions ----
    
    /**
     * Display a given property
     */
    public void showProperty(Property property)
    {
        hostIDLabel.setText(property.getHostID());
        hostNameLabel.setText(property.getHostName());
        neighbourhoodLabel.setText(property.getNeighbourhood());
        roomTypeLabel.setText(property.getRoomType());
        priceLabel.setText("£" + property.getPrice());
        minNightsLabel.setText(property.getMinNights());
        //descriptionLabel.setText(property.getDescription());
    }
    
    /**
     * Set a fixed size for the property display. If set, this size will be used for all properties.
     * If not set, the GUI will resize for each property.
     */
    public void setPropertyViewSize(int width, int height)
    {
        propertyPanel.setPreferredSize(new Dimension(width, height));
        frame.pack();
        fixedSize = true;
    }
    
    /**
     * Show a message in the status bar at the bottom of the screen.
     */
    public void showFavourite(Property property)
    {
        String favouriteText = " ";
        if (property.isFavourite()){
            favouriteText += "This is one of your favourite properties!";
        }
        favouriteLabel.setText(favouriteText);
    }
    
    /**
     * Show the ID in the top of the screen.
     */
    public void showID(Property property){
        idLabel.setText("Current Property ID:" + property.getID());
    }
    
    // ---- implementation of button functions ----
    
    /**
     * Called when the 'Next' button was clicked.
     */
    private void nextButton()
    {
        viewer.nextProperty();
    }

    /**
     * Called when the 'Previous' button was clicked.
     */
    private void previousButton()
    {
        viewer.previousProperty();
    }
    
    /**
     * Called when the 'View on Map' button was clicked.
     */
    private void viewOnMapsButton()
    {
        try{
         viewer.viewMap();
        }
        catch(Exception e){
            System.out.println("URL INVALID");
        }
        
    }
    
    /**
     * Called when the 'Toggle Favourite' button was clicked.
     */
    private void toggleFavouriteButton(){
        viewer.toggleFavourite();     
    }
    
    /**
     * Called when the 'Statistics' button was clicked.
     * Displasys a GUI on screen.
     */
    private void statisticsButton(){
        statsFrame();
        
        String stringViews = String.valueOf(viewer.getNumberOfPropertiesViewed());// gets the integer value for 'views' from the viewer class and converts it to a String.
        viewsLabelText.setText(stringViews);
        
        String stringAverage = String.valueOf(viewer.averagePropertyPrice());// gets the integer value for 'averagePropertyPrice' from the viewer class and converts it to a String.
        averagePriceLabelText.setText(stringAverage);
    }

    // ---- swing stuff to build the frame and all its components ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("Portfolio Viewer Application");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        // Create the property pane in the center
        propertyPanel = new JPanel();
        propertyPanel.setLayout(new GridLayout(6,2));
        
        propertyPanel.add(new JLabel("HostID: "));
        hostIDLabel = new JTextField("default");
        hostIDLabel.setEditable(false);
        propertyPanel.add(hostIDLabel);
        
        propertyPanel.add(new JLabel("Host Name: "));
        hostNameLabel = new JTextField("default");
        hostNameLabel.setEditable(false);
        propertyPanel.add(hostNameLabel);
        
        propertyPanel.add(new JLabel("Neighbourhood: "));
        neighbourhoodLabel = new JTextField("default");
        neighbourhoodLabel.setEditable(false);
        propertyPanel.add(neighbourhoodLabel);
        
        propertyPanel.add(new JLabel("Room type: "));
        roomTypeLabel = new JTextField("default");
        roomTypeLabel.setEditable(false);
        propertyPanel.add(roomTypeLabel);
        
        propertyPanel.add(new JLabel("Price: "));
        priceLabel = new JTextField("default");
        priceLabel.setEditable(false);
        propertyPanel.add(priceLabel);
        
        propertyPanel.add(new JLabel("Minimum nights: "));
        minNightsLabel = new JTextField("default");
        minNightsLabel.setEditable(false);
        propertyPanel.add(minNightsLabel);

        propertyPanel.setBorder(new EtchedBorder());
        contentPane.add(propertyPanel, BorderLayout.CENTER);
        
        // Create two labels at top and bottom for the file name and status message
        idLabel = new JLabel("default");
        contentPane.add(idLabel, BorderLayout.NORTH);

        favouriteLabel = new JLabel(" ");
        contentPane.add(favouriteLabel, BorderLayout.SOUTH);
        
        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0, 1));
        
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { nextButton(); }
                           });
        toolbar.add(nextButton);
        
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { previousButton(); }
                           });
        toolbar.add(previousButton);

        JButton mapButton = new JButton("View Property on Map");
        mapButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { viewOnMapsButton(); }
                           });
        toolbar.add(mapButton);
        
        JButton favouriteButton = new JButton("Toggle Favourite");
        favouriteButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { toggleFavouriteButton(); }
                           });
        toolbar.add(favouriteButton);

        JButton statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { statisticsButton(); }
                           });
        toolbar.add(statisticsButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);
        
        contentPane.add(flow, BorderLayout.WEST);
        
        // building is done - arrange the components     
        frame.pack();
        
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }  
    
    /**
     * Creates a Swing frame and its contents.
     * Displays the statistics: total number of properties viewed and their average price.
     * This code is adapted from the 'makeFrame' method in the 'PropertyViewerGUI' class.
     */
    private void statsFrame(){
        statsFrame = new JFrame("Statistics");
        JPanel contentPanel = (JPanel)statsFrame.getContentPane();
        contentPanel.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Specify the layout manager with nice spacing
        contentPanel.setLayout(new BorderLayout(6, 6));
        
        // Create the statsPanel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(6,2));
        
        // Create a label and text field to display the total views and add to statsPanel
        statsPanel.add(new JLabel("Views: "));
        viewsLabelText = new JTextField("default");
        viewsLabelText.setEditable(false);
        statsPanel.add(viewsLabelText);
        
        // Create a label and text field to display the average propety price and add to statsPanel
        statsPanel.add(new JLabel("Average price: "));
        averagePriceLabelText = new JTextField("default");
        averagePriceLabelText.setEditable(false);
        statsPanel.add(averagePriceLabelText);
        
        //add statsPanel to contentPanel
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // arrange the components and make the frame visible
        statsFrame.pack();
        statsFrame.setVisible(true);
    }
}
