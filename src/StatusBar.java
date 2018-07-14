import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.Font;


public class StatusBar extends JPanel 
{
	private JLabel statusBar = new JLabel("  ");

	//*************constructor of StatusBar
	public StatusBar()
	{
		statusBar.setFont(new Font("Verdana", Font.BOLD, 9));
		this.add(statusBar);
		this.setBorder(new SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
	}
}