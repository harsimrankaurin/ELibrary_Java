import java.awt.*;
public class Center 
{
	//********for using the class in JLibrary.java
	JLibrary l; 
	public Center(JLibrary l) 
	{
		this.l = l;
	}

	public void LibraryCenter() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		l.setLocation((screenSize.width - l.getWidth()) / 2, (screenSize.height - l.getHeight()) / 2);
	}
}