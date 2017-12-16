import java.awt.Color;
import java.awt.Graphics;

/**
 * Sets and controls all things Player 1
 * @author Drew Nolen
 * @version 4/6/2016
 */
public class Player 
{
	private int y = (Pong.WINDOW_HEIGHT/2) - 50;
	private int yVelocity = 0;
	private int width = 25;
	private int height = 100;
	private int score; 
	private String name;
	
	/**
	 * Sets score to 0 for a new instance of player 1
	 */
	public Player()
	{
		score = 0;
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
	 * Increments score when player 1 scores
	 * @param player the instance of player 1
	 */
	public void addScore(Player player)
	{
		player.score++;
	}
	
	/**
	 * Sets a new score for player 1
	 * @param newScore new score
	 */
	public void setScore(int newScore)
	{
		score=newScore;
	}
	
	/**
	 * Returns the score for player 1
	 * @return the score for player 1
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
		g.fillRect(75, y, width, height);
		g.setColor(Color.RED);
		g.fillRect(0, Pong.WINDOW_HEIGHT-275, width/2, height); //goal
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
	 * @return 75
	 */
	public int getX()
	{
		return 75;
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
	 * Sets the y position of the paddle
	 * @param pos y new y position of the paddle
	 */
	public void setY(int pos)
	{
		y = pos;
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
	
	
}
