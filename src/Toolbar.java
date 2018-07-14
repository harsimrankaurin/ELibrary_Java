import javax.swing.*;

public class Toolbar extends JToolBar 
{
	public JButton[] button;
	public String[] imageName24 = {"images/Add24.jpg", "images/List24.gif",
	                           
	                             
	                           "images/Add24.jpg",
	                               "images/List24.gif", 
	                            
	                               "images/Find24.gif", "images/Export24.gif",
	                               "images/Import24.gif",
	                          
	                               "images/Exit24.gif"};
	public String[] tipText = {"Add new books", "List all books",
	                           
	                           "Add new members", "List all members", 
	                           "Search books/members", "Borrow book for member", "Return book from member", "Exit program"};

	public Toolbar() {
		button = new JButton[19];
		for (int i = 0; i < imageName24.length; i++) 
		{
			if (i == 2|| i ==4 || i == 5|| i == 7)
			//************for separator*************
				addSeparator();
			//************for buttons to toolBar**************
			add(button[i] = new JButton(new ImageIcon(ClassLoader.getSystemResource(imageName24[i]))));
			button[i].setToolTipText(tipText[i]);
		}
	}
}