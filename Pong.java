import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Starts a new playing field for the game
 * @author Drew Nolen
 * @version 4/4/2016
 */
public class Pong extends JFrame
{
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	static final int WINDOW_WIDTH = 750;
	static final int WINDOW_HEIGHT = 450;
	private JFrame p1Name = new JFrame();
	private JFrame p2Name = new JFrame();
	private JTextField p1NameArea = new JTextField("");
	private JTextField p2NameArea = new JTextField("");
	private JButton submit1 = new JButton("Submit");
	private JButton submit2 = new JButton("Submit");
	private JLabel p1NameQ = new JLabel("Player 1 name:");
	private JLabel p2NameQ = new JLabel("Player 2 name:");
	Player player1 = new Player();
	Player2 player2 = new Player2();
	
	/**
	 * Sets up a new playing field for Pong
	 */
	public Pong()
	{
		setSize(768,600);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		p1NameBox();
		
			
	}
	
	private void p1NameBox()
	{
		p1Name.setVisible(true);
		p1Name.setLayout(null);
		p1Name.setSize(220, 250);
		p1NameArea.setSize(200,50);
		p1NameArea.setLocation(0,50);
		p1NameQ.setSize(200,50);
		submit1.setSize(100,50);
		submit1.setLocation(50,125);
		submit1.addActionListener(new ButtonClickHandler());
		p1Name.add(p1NameQ);
		p1Name.add(p1NameArea);
		p1Name.add(submit1);
	}
	
	private void p2NameBox()
	{
		p2Name.setVisible(true);
		p2Name.setLayout(null);
		p2Name.setSize(220, 250);
		p2NameArea.setSize(200,50);
		p2NameArea.setLocation(0,50);
		p2NameQ.setSize(200,50);
		submit2.setSize(100,50);
		submit2.setLocation(50,125);
		submit2.addActionListener(new ButtonClickHandler());
		p2Name.add(p2NameQ);
		p2Name.add(p2NameArea);
		p2Name.add(submit2);
	}
	

	
	private class ButtonClickHandler implements ActionListener
	{
		/**
		 * Handles what happens when buttons are clicked
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == submit1)
			{
				player1.setName(p1NameArea.getText());
				p1Name.dispose();
				p2NameBox();
			}
			if (e.getSource() == submit2)
			{
				player2.setName(p2NameArea.getText());
				p2Name.dispose();
				Score.load();
				add(new GamePanel(player1, player2));
				setVisible(true);				
			}
		}
	}
}