
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;

public class ListBooks extends JInternalFrame 
{
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JLabel northLabel = new JLabel("THE LIST FOR THE BOOKS");
	private JButton printButton;
	private JTable table;
	private TableColumn column = null;
	private JScrollPane scrollPane;

	private ResultSetTableModel tableModel;

	private static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String DATABASE_URL = "jdbc:odbc:JLibrary";
	private static final String DEFAULT_QUERY = "SELECT BookID, Subject, Title, Author," +
	        "Publisher, Copyright, Edition, Pages, NumberOfBooks, ISBN, Library, Availble,ShelfNo FROM Books";

	public ListBooks()
	{
		super("Books", false, true, false, true);
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("images/List16.gif")));

		Container cp = getContentPane();

		try
		{
			tableModel = new ResultSetTableModel(JDBC_DRIVER, DATABASE_URL, DEFAULT_QUERY);
			//for setting the Query
			try
			{
				tableModel.setQuery(DEFAULT_QUERY);
			}
			catch (SQLException sqlException) 
			{
			}
		}
		catch (ClassNotFoundException classNotFound)
		{
			System.out.println(classNotFound.toString());
		}
		catch (SQLException sqlException) 
		{
			System.out.println(sqlException.toString());
		}
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(990, 200));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane = new JScrollPane(table);

		for (int i = 0; i < 13; i++) 
		{
			column = table.getColumnModel().getColumn(i);
			if (i == 0) //BookID
				column.setPreferredWidth(20);
			if (i == 1) //Subject
				column.setPreferredWidth(100);
			if (i == 2) //Title
				column.setPreferredWidth(150);
			if (i == 3) //Auther
				column.setPreferredWidth(50);
			if (i == 4) //Publisher
				column.setPreferredWidth(70);
			if (i == 5) //Copyright
				column.setPreferredWidth(40);
			if (i == 6) //Edition
				column.setPreferredWidth(40);
			if (i == 7) //Pages
				column.setPreferredWidth(40);
			if (i == 8) //NumberOfBooks
				column.setPreferredWidth(80);
			if (i == 9) //ISBN
				column.setPreferredWidth(70);
			if (i == 10) //Library
				column.setPreferredWidth(30);
			if (i == 11) //Availble
				column.setPreferredWidth(30);
            if (i == 12) //ShelfNo
				column.setPreferredWidth(30);
		}
		Color myStripeColor = new Color(200,125, 155, 110);  northPanel.setBackground(myStripeColor);
		northLabel.setFont(new Font("Arial Rounded MT", Font.BOLD, 16));
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		northPanel.add(northLabel);
		cp.add("North", northPanel);

		centerPanel.setLayout(new BorderLayout());
		ImageIcon printIcon = new ImageIcon(ClassLoader.getSystemResource("images/Print16.gif"));
		printButton = new JButton("print the books", printIcon);
		printButton.setToolTipText("Print");
		printButton.setFont(new Font("Arial", Font.PLAIN, 13));
		centerPanel.add(printButton, BorderLayout.NORTH);
		 .add(scrollPane, BorderLayout.CENTER);
		centerPanel.setBorder(BorderFactory.createTitledBorder("Books:"));
		cp.add("Center", centerPanel);

		printButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				Thread runner = new Thread() 
				{
					public void run() 
					{
						try 
						{
							PrinterJob prnJob = PrinterJob.getPrinterJob();
							prnJob.setPrintable(new PrintingBooks(DEFAULT_QUERY));
							if (!prnJob.printDialog())
								return;
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							prnJob.print();
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
						catch (PrinterException ex) 
						{
							System.out.println("Printing error: " + ex.toString());
						}
					}
				};
				runner.start();
			}
		});
		setVisible(true);
		pack();
	}
}