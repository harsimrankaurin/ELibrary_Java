
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddMembers extends JInternalFrame
 {

	private JPanel northPanel = new JPanel();
	private JLabel northLabel = new JLabel("MEMBER INFORMATION");
	private JPanel centerPanel = new JPanel();
	private JPanel informationLabelPanel = new JPanel();
	private JLabel[] informationLabel = new JLabel[7];
	private String[] informaionString = 
	{	" Member ID: ", " The Password: ", " Rewrite the password: ",
		" The Name: ", " E-MAIL: ", " Major Branch:", " Expired: "
	};
	private JPanel informationTextFieldPanel = new JPanel();
	private JTextField[] informationTextField = new JTextField[5];
	private JPasswordField[] informationPasswordField = new JPasswordField[2];
	private JPanel insertInformationButtonPanel = new JPanel();
	private JButton insertInformationButton = new JButton("Insert the Information");
	private JPanel southPanel = new JPanel();
	private JButton OKButton = new JButton("Exit");
	private Members member;
	private String[] data;

	//for checking the password
	public boolean isPasswordCorrect() 
{
		if (informationPasswordField[0].getText().equals(informationPasswordField[1].getText()))
			data[1] = informationPasswordField[1].getText();
		else if (!informationPasswordField[0].getText().equals(informationPasswordField[1].getText()))
			return false;

		return true;
	}

	//for checking the information from the text field
	public boolean isCorrect() 
	{
		data = new String[6];
		for (int i = 0; i < informationLabel.length; i++) 
		{
			if (i == 0) 
			{
				if (!informationTextField[i].getText().equals("")) 
				{
					data[i] = informationTextField[i].getText();
				}
				else
					return false;
			}
			if (i == 1 || i == 2) 
			{
				if (informationPasswordField[i - 1].getText().equals(""))
					return false;
			}
			if (i == 3 || i == 4 || i == 5 || i == 6)
				{
				if (!informationTextField[i - 2].getText().equals("")) 
				{
					data[i - 1] = informationTextField[i - 2].getText();
				}
				else
					return false;
			}
		}
		return true;
	}

	public void clearTextField() 
	{
		for (int i = 0; i < informationLabel.length; i++) 
		{
			if (i == 0)
				informationTextField[i].setText(null);
			if (i == 1 || i == 2)
				informationPasswordField[i - 1].setText(null);
			if (i == 3 || i == 4 || i == 5 || i == 6)
				informationTextField[i - 2].setText(null);
		}
	}

	//constructor of addMembers
	public AddMembers() 
	{
		super("Add Members", false, true, false, true);
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/Add16.jpg")));
		Container cp = getContentPane();

		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		Color myStripeColor = new Color(200,125, 155, 110);  northPanel.setBackground(myStripeColor);
		northLabel.setFont(new Font("Arial Rounded MT", Font.BOLD, 17));
		northPanel.add(northLabel);
		cp.add("North", northPanel);

		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBorder(BorderFactory.createTitledBorder("Add a new member:"));
		
		informationLabelPanel.setLayout(new GridLayout(7, 1, 4, 4));
		informationTextFieldPanel.setLayout(new GridLayout(7, 1, 4, 4));
		for (int i = 0; i < informationLabel.length; i++) 
		{
			informationLabelPanel.add(informationLabel[i] = new JLabel(informaionString[i]));
			informationLabel[i].setFont(new Font("Arial", Font.BOLD, 13));
		}
		centerPanel.add("West", informationLabelPanel);

		for (int i = 0; i < informationLabel.length; i++)
			{
			if (i == 1 || i == 2) 
			{
				informationTextFieldPanel.add(informationPasswordField[i - 1] = new JPasswordField(25));
				informationPasswordField[i - 1].setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			}
			if (i == 0)
			{
				informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));
				informationTextField[i].setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			}
			if (i == 3 || i == 4 || i == 5 || i == 6) 
			{
				informationTextFieldPanel.add(informationTextField[i - 2] = new JTextField(25));
				informationTextField[i - 2].setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			}
		}
		centerPanel.add("East", informationTextFieldPanel);

		insertInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		insertInformationButton.setFont(new Font("Arial Rounded MT", Font.BOLD, 13));
		insertInformationButtonPanel.add(insertInformationButton);
		centerPanel.add("South", insertInformationButtonPanel);
		cp.add("Center", centerPanel);

		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		OKButton.setFont(new Font("Arial Rounded MT", Font.BOLD, 13));
		southPanel.add(OKButton);
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		cp.add("South", southPanel);

		insertInformationButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				//for checking if there is a missing information
				if (isCorrect()) 
				{
					if (isPasswordCorrect()) 
					{
						Thread runner = new Thread()
						{
							public void run() 
							{
								member = new Members();
								Date d=new Date();
								//for checking if there is no same information in the database
								member.connection("SELECT * FROM Members WHERE ID = " + data[0]);
								int ID = member.getID();
								if (Integer.parseInt(data[0]) != ID) 
								{
									member.update("INSERT INTO Members (ID,Password,Name,EMail,Major,Expired) VALUES (" +
									        data[0] + ", '" + data[1] + "','" + data[2] + "','" +
									        data[3] + "','" + data[4] + "','" + data[5] + "')");
									//for setting the array of JTextField & JPasswordField to null
									clearTextField();
								}
								else
									JOptionPane.showMessageDialog(null, "Member is in the Library", "Error", JOptionPane.ERROR_MESSAGE);
							}
						};
						runner.start();
					}
					else
						JOptionPane.showMessageDialog(null, "the passowrd is wrong", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		OKButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				dispose();
			}
		});
		setVisible(true);
		pack();
	}
}