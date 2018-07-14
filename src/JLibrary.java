import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;

public class JLibrary extends JFrame implements ActionListener
 {
    private JPanel searchPanel = new JPanel();   
    private JToolBar searchToolBar = new JToolBar(); 
    private JLabel searchLabel = new JLabel("Book title: "); 
    private JTextField searchTextField = new JTextField(15);
    private JButton goButton = new JButton("Go");
    private JDesktopPane desktop = new JDesktopPane();
    private JSplitPane splitPane;
    private JScrollPane desktopScrollPane;
    private JScrollPane treeScrollPane;
    private Menubar menu;
    private Toolbar toolbar;
    private StatusBar statusbar = new StatusBar();
	
	private ListBooks listBooks;
    private AddBooks addBooks;
    private BorrowBooks borrowBooks;
    private ReturnBooks returnBooks;
	private AddMembers addMembers;
    private ListMembers listMembers;
    private SearchBooksAndMembers search;
	private JMenuBar mbar;
	private JMenu mnu;
	private JLabel msg=new JLabel();

    //constructor of JLibrary
    public JLibrary()
	{
        
        super("E-Library System");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.getImage(ClassLoader.getSystemResource("images/appIcon.jpg"));
        setIconImage(image);
		
		
		//******************************************************
		msg.setText("Program By: Harsimran Kaur Sidhu");
		msg.setFont(new Font("Old English Text MT", Font.PLAIN, 20));
		msg.setForeground(Color.white);
		//******************************************************
       
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
        //********for color on menubar**************************
		mbar=new JMenuBar();
		Color myStripeColor = new Color(200,125, 155, 110);
		mbar.setBackground(myStripeColor);
		//******************************************************
		
		//**************for menubar background image************
		menu = new Menubar();
        toolbar = new Toolbar();
		setJMenuBar(menu);
		setJMenuBar(mbar);
		mbar.add(menu);
		menu.exit.addActionListener(this);
		menu.addBook.addActionListener(this);
        menu.listBook.addActionListener(this);
        menu.addMember.addActionListener(this);
        menu.listMember.addActionListener(this);
        menu.searchBooksAndMembers.addActionListener(this);
        menu.borrowBook.addActionListener(this);
        menu.returnBook.addActionListener(this);
		 //***************************************************************
        
        //get the graphical user interface components display the desktop
        Container cp = getContentPane();
		desktop.setBackground(Color.BLACK);
		
		// *******************************************
		 ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/123.jpg"));
         JLabel label = new JLabel(icon);
         label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		 label.setOpaque(true);
         desktop.add(label, new Integer(Integer.MIN_VALUE));
		// ***********************************
			
		
        cp.add("Center", desktop);
		//***********setting fonts**********************************
        searchLabel.setFont(new Font("Cooper", Font.BOLD, 11));
        searchTextField.setFont(new Font("Cooper", Font.PLAIN, 12));
        goButton.setFont(new Font("Cooper", Font.BOLD, 9));
        //***********************************************************
		
		//**************applying font*********************
		searchToolBar.add(searchLabel);
        searchToolBar.add(searchTextField);
        searchToolBar.add(goButton);
        
		//***************for applying actions*****************
		goButton.addActionListener(this);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add("Center", toolbar);
        cp.add("North", searchPanel);

		//***********for adding statusbar*********************
		statusbar = new StatusBar();
		statusbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		statusbar.setBackground(Color.BLACK);
		statusbar.add(msg, BorderLayout.WEST);
		cp.add("South", statusbar);
		//****************************************************
		
		//*************for adding action on button**************
        for (int i = 0; i < toolbar.imageName24.length; i++) 
		{
            toolbar.button[i].addActionListener(this);
        }		

        //for adding WindowListener to the program
        addWindowListener(new WindowAdapter() 
		{
            public void windowClosing(WindowEvent e)
			{
                System.exit(0);
            }
        });
        show();
    }

    public void actionPerformed(ActionEvent ae)
	{
        if (ae.getSource() == menu.addBook || ae.getSource() == toolbar.button[0])
		{
            Thread runner = new Thread() 
			{

                public void run()
				{
                    addBooks = new AddBooks();
                    desktop.add(addBooks);
                    try 
					{
                        addBooks.setSelected(true);
                    } 
					catch (java.beans.PropertyVetoException e) 
					{
                    }
                }
            };
            runner.start();
        }
        if (ae.getSource() == menu.listBook || ae.getSource() == toolbar.button[1])
		{
            Thread runner = new Thread() 
			{

                public void run() 
				{
                    listBooks = new ListBooks();
                    desktop.add(listBooks);
                    try 
					{
                        listBooks.setSelected(true);
                    } 
					catch (java.beans.PropertyVetoException e)
					{
                    }
                }
            };
            runner.start();
        }
      if (ae.getSource() == menu.addMember || ae.getSource() == toolbar.button[2])
	  {
            Thread runner = new Thread()
			{

                public void run() 
				{
                    addMembers = new AddMembers();
                    desktop.add(addMembers);
                    try 
					{
                        addMembers.setSelected(true);
                    }
					catch (java.beans.PropertyVetoException e) 
					{
                    }
                }
            };
            runner.start();
        }
        if (ae.getSource() == menu.listMember || ae.getSource() == toolbar.button[3]) 
		{
            Thread runner = new Thread() 
			{

                public void run() 
				{
                    listMembers = new ListMembers();
                    desktop.add(listMembers);
                    try 
					{
                        listMembers.setSelected(true);
                    }
					catch (java.beans.PropertyVetoException e) 
					{
                    }
                }
            };
            runner.start();
        }
        if (ae.getSource() == menu.searchBooksAndMembers || ae.getSource() == toolbar.button[4]) 
		{
            Thread runner = new Thread() 
			{

                public void run() 
				{
                    search = new SearchBooksAndMembers();
                    desktop.add(search);
                    try 
					{
                        search.setSelected(true);
                    }
					catch (java.beans.PropertyVetoException e) 
					{
                    }
                }
            };
            runner.start();
        }
        if (ae.getSource() == menu.borrowBook || ae.getSource() == toolbar.button[5]) 
		{
            Thread runner = new Thread() 
			{

                public void run() 
				{
                    borrowBooks = new BorrowBooks();
                    desktop.add(borrowBooks);
                    try 
					{
                        borrowBooks.setSelected(true);
                    }
					catch (java.beans.PropertyVetoException e) 
					{
                    }
                }
            };
            runner.start();
        }
        if (ae.getSource() == menu.returnBook || ae.getSource() == toolbar.button[6])
		{
            Thread runner = new Thread() 
			{
                public void run() 
				{
                    returnBooks = new ReturnBooks();
                    desktop.add(returnBooks);
                    try 
					{
                        returnBooks.setSelected(true);
                    }
					catch (java.beans.PropertyVetoException e) 
					{
                    }
                }
            };
            runner.start();
        }
       
        if (ae.getSource() == menu.exit || ae.getSource() == toolbar.button[7])
		{
            dispose();
            System.exit(0);
        }
    }
}
