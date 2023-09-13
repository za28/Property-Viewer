import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * Details about the interaciton with properties can be viewed.
 * 
 * @author Michael Kölling and Josh Murphy and Zahra Amaan K21011879
 * @version 1.0
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui;     // the Graphical User Interface
    private Portfolio portfolio;
    private Property currentProperty;
    int propertyIndex = 0;
    int totalViews = 0;
    int totalPrice;
    
    /**
     * Create a PropertyViewer and display its GUI on screen.
     */
    public PropertyViewer()
    {
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        this.viewProperty(0);
    }
    
    /**
     * Displays a property, its ID and whether it is a favourite.
     */
    public void viewProperty(int index)
    {
        currentProperty = portfolio.getProperty(index);
        gui.showProperty(currentProperty);
        gui.showID(currentProperty);
        gui.showFavourite(currentProperty);
        totalViews += 1;
        totalPrice += currentProperty.getPrice();
    }
    
    /**
     * Called when the 'Next' button is clicked.
     * Displays the next property in the portfolio.
     */
    public void nextProperty()
    {
        // 'propertyIndex' is incremented by 1. When the 'Next' button is clicked while viewing the last property in the portfolio, the 'propertyIndex' is set to 0 and the first property in the portfolio is displayed.
        propertyIndex += 1;
        if (propertyIndex == portfolio.numberOfProperties())
        {
            propertyIndex = 0;
        }
        viewProperty(propertyIndex);
    }

    /**
     * Called when the 'Previuos' button is clicked.
     * Displays the previous property in the portfolio.
     */
    public void previousProperty()
    {
        // 'propertyIndex' is decremented by 1. When the 'Previous' button is clicked while viewing the first property in the portfolio, the last property in the portfolio is displayed.
        propertyIndex -= 1;
        if (propertyIndex == -1)
        {
            propertyIndex = portfolio.numberOfProperties() - 1;
        }
        viewProperty(propertyIndex);
    }

    /**
     * Called when the 'Toggle Favourite' button is clicked.
     * If the current property is not a favourite, it becomes a favourite.
     * If the current property is a favouite, it becomes not a favourite. 
     * When a property is a favourite, a message is displayed and when a property is not a favourite, the message is removed.
     */
    public void toggleFavourite()
    {
        currentProperty.toggleFavourite();
        gui.showFavourite(currentProperty);
    }
    

    //----- methods for challenge tasks -----
    
    /**
     * This method opens the system's default internet browser
     * The Google maps page should show the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
       double latitude = currentProperty.getLatitude();
       double longitude = currentProperty.getLongitude();
       
       URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
       java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * Returns the total number of properties that have been viewed so far.
     */
    public int getNumberOfPropertiesViewed()
    {
        return totalViews;
    }
    
    /**
     * Calculates and returns the average property price of all the properties that have been viewed so far.
     */
    public int averagePropertyPrice()
    {
        int averagePrice = totalPrice/totalViews;
        return averagePrice;
    }
}
