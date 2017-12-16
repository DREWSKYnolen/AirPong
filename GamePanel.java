import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 * Sets up game panel and playing area, paints components, shows results at end of game
 * Some blocks of code are from the video tutorial series from the related lab.
 * @author Drew Nolen
 * @version 4/6/2016
 *
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	Ball ball = new Ball();	
	Player player = new Player();
	Player2 player2 = new Player2();
	Score hScore;
	
	Date startTime = new Date();	
	SimpleDateFormat sdf = new SimpleDateFormat("mm:ss"); //adamldavis - StackOverflow
			
	static final int WINDOW_WIDTH = 750;
	static final int WINDOW_HEIGHT = 450;
	
	private int gameTime =0;
	
	JLabel gameTimeLable = new JLabel(""+gameTime);
	JLabel winMessage = new JLabel("");
	
	/**
	 * Starts the game panel
	 */
	public GamePanel(Player p1, Player2 p2)
	{		
		Timer time = new Timer(1000/90,this);
		time.start();
		setLayout(null);
		this.player = p1;
		this.player2 = p2;
		
		this.addKeyListener(this);
		this.setFocusable(true);
		winMessage.setText("");
	}
		
	
	/**
	 * Starts an instance of the Pong class
	 * @param args because I have to have this
	 */
	public static void main(String[] args)
	{
		new Pong();
	}
	
	/**
	 * Updates components for movement
	 */
	private void update()
	{
		player.update();
		player2.update();
		ball.update();
		ball.checkP1Collision(player);
		ball.checkP2Collision(player2);
		ball.checkP1Goal(player, player2);
		ball.checkP2Goal(player, player2);
		
		if (player.getScore() >=7)
		{
			p1Win();
		}
		else if (player2.getScore() >= 7)
		{
			p2Win();
		}
	}
	
	/**
	 * Paints the background, lines, and goals
	 */
	public void paintComponent(Graphics g)
	{
		Date rightNow = new Date();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 750, 600);
		g.setColor(Color.WHITE);
		g.drawLine(0,25,WINDOW_WIDTH, 25);
		g.setColor(Color.WHITE);
		g.drawLine(0,WINDOW_HEIGHT-25, WINDOW_WIDTH, WINDOW_HEIGHT-25);
		g.drawOval((WINDOW_HEIGHT/2)+75, (WINDOW_WIDTH/2)-225,150,150);
		g.drawLine(WINDOW_WIDTH/2, 25, WINDOW_WIDTH/2, WINDOW_HEIGHT-25);
		
		g.setFont(new Font("Arial", Font.PLAIN, 50));
		g.drawString(String.valueOf(player.getScore()),WINDOW_WIDTH - 650, WINDOW_HEIGHT + 25);
		g.drawString(String.valueOf(player2.getScore()), WINDOW_WIDTH - 100, WINDOW_HEIGHT + 25);
		
		g.drawString(sdf.format((rightNow.getTime() - startTime.getTime())), WINDOW_WIDTH-435, 475);
			
		player.paint(g);
		player2.paint(g);
		ball.paint(g);
	}
	
	/**
	 * Calls the update and repaint methods to update screen
	 */
	public void actionPerformed(ActionEvent e)
	{		
		update();
		repaint();
	}
	
	/**
	 * Handles key presses for control of paddles
	 */
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			player.setYVelocity(-10);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			player.setYVelocity(10);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			player2.setYVelocity(-10);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player2.setYVelocity(10);
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			
		}
	}
	
	/**
	 * Handles key releases to stop movement of paddles
	 */
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)
		{
			player.setYVelocity(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player2.setYVelocity(0);
		}
	}
	
	/**
	 * I don't know what this is for, it does nothing
	 */
	public void keyTyped(KeyEvent e)
	{
		
	}	
	
	private JFrame outcome = new JFrame("Results");
	private JFrame again = new JFrame("Play Again?");
	private JButton newGame = new JButton("Play Again");
	private JButton noMore = new JButton("Quit");
	
	
	/**
	 * Checks if player 1 has 7 points, shows results of final scores
	 */
	public void p1Win()
	{
		Score.load();
		long finalScore = 0;
		Date test = new Date();
		finalScore=((test.getTime() - startTime.getTime())/1000)*7;		
		int finalScoreInt = (int) finalScore;
		ball.stopBall();
		if (player.getScore() >=7)
		{
			again.setLocationRelativeTo(outcome);
			outcome.setLocationRelativeTo(this);
			winMessage.setText("<html>" + player.getName() + " has won!<br>Final score: " + finalScore + "<br>Score was: "
					+ player.getScore() +  " : " + player2.getScore() + "</html>");
			winMessage.setFont(new Font("Ariel", Font.BOLD, 30));
			winMessage.setLocation(0,50);
			outcome.add(winMessage);
			again.setLayout(null);
			again.setVisible(true);
			again.setSize(400,233);
			again.setLocationRelativeTo(outcome);
			again.add(newGame);
			newGame.addActionListener(new ButtonClickHandler());
			noMore.addActionListener(new ButtonClickHandler());
			newGame.setSize(200,200);
			newGame.setLocation(0,0);
			noMore.setSize(200,200);
			noMore.setLocation(200,0);
			again.add(newGame);
			again.add(noMore);
			outcome.setResizable(false);
			outcome.setAlwaysOnTop(true);
			outcome.pack();
			outcome.setVisible(true);
			hScore= new Score(player.getName(), finalScoreInt);
			Score.scoreBoard.add(hScore);
			Score.sort();
			Score.save();
			hScoreBox();
	}
		player.setScore(0);
		player2.setScore(0);
	}//p1win
	
	/**
	 * Checks if player 2 has 7 points, shows results of final scores
	 */
	public void p2Win()
	{
		Score.load();
		long finalScore = 0;
		Date nowTime = new Date();
		finalScore=((nowTime.getTime() - startTime.getTime())/1000)*7;
		int finalScoreInt = (int) finalScore;
		ball.stopBall();	
		if (player2.getScore() >=7)
		{
			again.setLocationRelativeTo(outcome);
			outcome.setLocationRelativeTo(this);			
			winMessage.setText("<html>" + player2.getName() + " has won!<br>Score: " + finalScore + "<br>Score was: "
					+ player.getScore() +  " : " + player2.getScore() + "</html>");
			winMessage.setFont(new Font("Ariel", Font.BOLD, 30));
			winMessage.setLocation(0,50);
			outcome.add(winMessage);
			again.setLayout(null);
			again.setVisible(true);
			again.setSize(400,233);
			again.setLocationRelativeTo(outcome);			
			noMore.addActionListener(new ButtonClickHandler());
			newGame.addActionListener(new ButtonClickHandler());
			newGame.setSize(200,200);
			newGame.setLocation(0,0);
			noMore.setSize(200,200);
			noMore.setLocation(200,0);
			again.add(newGame);
			again.add(noMore);			
			outcome.setResizable(false);
			outcome.setAlwaysOnTop(true);
			outcome.pack();
			outcome.setVisible(true);
			hScore= new Score(player2.getName(), finalScoreInt);
			Score.scoreBoard.add(hScore);
			Score.sort();
			Score.save();
			hScoreBox();
			

		}
		player.setScore(0);
		player2.setScore(0);		
	}	
	
	private JFrame lowScores = new JFrame();
	private JTextArea scoreList = new JTextArea();
	
	/**
	 * Creates and displays the list of high scores
	 */
	private void hScoreBox()
	{
		lowScores.setVisible(true);
		lowScores.setAlwaysOnTop(true);
		lowScores.setSize(250, 400);
		lowScores.setLocationRelativeTo(again);
		scoreList.setEditable(false);
		scoreList.setText("LOWEST SCORES:\n");
		int j= Score.scoreBoard.size();
		if (j>10)
			j=10;
		for (int i=0; i<j; i++)
			scoreList.append(Score.scoreBoard.get(i).getName() + ": " + Score.scoreBoard.get(i).getScore()+"\n");
		scoreList.setFont(new Font("Ariel", Font.BOLD, 24));
		scoreList.setVisible(true);
		scoreList.setLocation(0,0);
		lowScores.add(scoreList);
	}
		
	/**
	 * Handles what happens when buttons are clicked
	 * @author Drew Nolen
	 * @version 4/6/2016
	 */
	private class ButtonClickHandler implements ActionListener
	{
		/**
		 * Handles what happens when buttons are clicked
		 */
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == newGame)
			{
				ball.resetGame();
				outcome.dispose();
				startTime = new Date();
				again.dispose();
				lowScores.dispose();
			}
			else if(e.getSource() == noMore)
			{
				Score.save();
				System.exit(0);
			}
			
		}
	}
}