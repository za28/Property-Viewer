# Property-Viewer
An application to view properties.

<img width="755" alt="Screenshot 2023-09-08 at 23 55 32" src="https://github.com/za28/Property-Viewer/assets/114661472/e4d3151b-6521-4e7d-843f-01438866812a">

Property viewer 

An application that allows the user to view properties loaded from a spreadsheet. 


<br>

</br>
Project Overview Here is a quick overview of the existing classes: 

**• Property**

 – This class represents a single property.

**• Portfolio**

 – This represents a collection of properties.
 
 – A portfolio is built by specifying a spreadsheet on disk with the data on some properties in it (this is by default the file called airbnb-london.csv — this is real-world data are some of the actual AirBnB listings). 
 
 – The portfolio will automatically load all the properties that it finds in that spreadsheet. 

**• PropertyViewerGUI**

 – This class presents the GUI (Graphical User Interface) of the application. That is: it draws the main window, the buttons, and all the other things you see on the screen.
 
 – The class does two additional things: 
 (1) if the user clicks a button, that call is passed onto the relevant PropertyViewer method; 
 (2) the PropertyViewer class may call this one to display a Property or String in the interface. 

**• PropertyViewer**

– This class implements the logic of the property viewer. 

– This is also the class that you instantiate to start this application.

 – This is also the class that you instantiate to run the application. 


<br>

</br>
**Functionality:**

• When the application is started, the first property in the portfolio (index 0) is automatically displayed. 

• With any property that is displayed, the ID of the property is shown near the top of the window. The GUI class has a method to do this. 

• When the Toggle Favourite button is pressed, the isFavourite field of that property is updated. There is a method in the Property class to do this. 

• The bar at the bottom of the window shows whether the property has been marked by the user as one of their favourites. 

• When the Next button is pressed, the next property is displayed, with the correct data. Furthermore, the ID at the top is updated correctly, as well as whether the property is one of the user’s favourites. 

• When the Previous button is pressed, the previous property is displayed, with the correct data. Furthermore, the ID at the top is updated correctly, as well as whether the property is one of the user’s favourites. 

• The getNumberOfPropertiesViewed method returns the number of properties that have been viewd since the application was started. Viewing the same property twice counts as two views.

• The averagePropertyPrice method returns the average price of the properties viewed so far. 

• The View Property on Map button displays the location of the property on the map. 

• Statistics button opens a new window that displays the statistics information from getNumberOfPropertiesViewed and averagePropertyPrice when clicked.
