import java.awt.Color;
import java.awt.Graphics;

/**
 * Sets and controls all things Player 2
 * @author Drew Nolen
 * @version 4/6/2016
 */
public class Player2 
{
	private int y = (Pong.WINDOW_HEIGHT/2) - 50;
	private int yVelocity = 0;
	private int width = 25;
	private int height = 100;
	private int score;
	private String name;
	
	/**
	 * Sets score to 0 for a new instance of player 2
	 */
	public Player2()
	{
		score=0;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public String getName()
	{
		return name;
	}
	
	
	/**
	 * Increments score when player 2 scores
	 * @param player2 the instance of player 2
	 */
	public void addScore(Player2 player2)
	{
		player2.score++;
	}
	
	/**
	 * Sets a new score for player 2
	 * @param newScore new score
	 */
	public void setScore(int newScore)
	{
		score=newScore;
	}
	
	/**
	 * Returns the score for player 2
	 * @return the score for player 2
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * updates the velocity of the paddle when moving
	 */
	public void update()
	{
		y = y+yVelocity;
	}
	
	/**
	 * Paints the paddle and the goal on the left
	 * @param g graphics element
	 */
	public void paint (Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(650, y, width, height);
		g.setColor(Color.RED);
		g.fillRect(Pong.WINDOW_WIDTH-(width/2), Pong.WINDOW_HEIGHT-275, width/2, height); //goal
	}
	
	/**
	 * Sets the y velocity of the player 1 paddle
	 * @param speed new value for velocity
	 */
	public void setYVelocity(int speed)
	{
		yVelocity = speed;
	}
	
	/**
	 * Returns velocity
	 * @return velocity
	 */
	public int getVelocity()
	{
		return yVelocity;
	}
	
	/**
	 * Returns the constant position for x of the paddle
	 * @return 650
	 */
	public int getX()
	{
		return 650;
	}
	
	/**
	 * Returns the value of y for the paddle
	 * @return y of the paddle
	 */
	public int getY()
	{
		return y;
	}
	
	
	/**
	 * Returns the width of the paddle
	 * @return the width of the paddle
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Returns the height of the paddle
	 * @return the height of the paddle
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Sets the y position of the paddle
	 * @param pos y new y position of the paddle
	 */
	public void setY(int pos)
	{
		y = pos;
	}
}
