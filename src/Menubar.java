import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.management.JMException;

public class Menubar extends JMenuBar
 {
	public JMenu fileMenu,  bookMenu,  memberMenu,  searchMenu,  loanMenu;
    public JMenuItem   exit,  addBook,  listBook;
    public JMenuItem   addMember,  listMember;
    public JMenuItem  searchBooksAndMembers,  borrowBook,  returnBook;
    public ImageIcon[] icons;   	//array create 
	
	
    //********array created for displaying images****************
    public String[] imageName16 = 
	{	"images/Print16.gif", "images/Exit16.gif",
        "images/Add16.jpg", "images/List16.gif",
        "images/Edit16.gif", "images/Delete16.gif",
        "images/Information16.gif", "images/Find16.gif",
		"images/Export16.gif", "images/Import16.gif",
	};
    
	//**********constructor**********
	public Menubar() 
	{
        this.add(fileMenu = new JMenu("Program"));
        this.add(bookMenu = new JMenu("Books"));
        this.add(memberMenu = new JMenu("Members"));
		this.add(loanMenu = new JMenu("Counter"));
        this.add(searchMenu = new JMenu("Search"));
       
		fileMenu.setMnemonic('f');
        bookMenu.setMnemonic('b');
        memberMenu.setMnemonic('m');
        loanMenu.setMnemonic('l');
		searchMenu.setMnemonic('s');
		
        //for setting the image icons
        icons = new ImageIcon[12];
        for (int i = 0; i < imageName16.length; i++)
		{
            icons[i] = new ImageIcon(ClassLoader.getSystemResource(imageName16[i]));
        }

        //****************for adding text*************
        fileMenu.add(exit = new JMenuItem("Exit program", icons[1]));
        bookMenu.add(addBook = new JMenuItem("Add new book", icons[2]));
        bookMenu.add(listBook = new JMenuItem("List all books", icons[3]));
 		memberMenu.add(addMember = new JMenuItem("Add new member", icons[2]));
        memberMenu.add(listMember = new JMenuItem("List all members", icons[3]));
        searchMenu.add(searchBooksAndMembers = new JMenuItem("Search book/member", icons[7]));
        loanMenu.add(borrowBook = new JMenuItem("Borrow book for member", icons[8]));
        loanMenu.add(returnBook = new JMenuItem("Return book from member", icons[9]));

		//**********apply actionevent to shortcut of icons*************
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        searchBooksAndMembers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        addBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        listBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        addMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        listMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        borrowBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        returnBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
    }
}
