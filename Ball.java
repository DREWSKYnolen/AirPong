import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a ball bouncing on the screen. checks for collision of walls and
 * paddles.
 * @author Drew Nolen
 * @version 4/4/2016
 *
 */
public class Ball
{
	private int x = 200;
	private int y = 200;

	
	private int xVelocity = 5;
	private int yVelocity = -5;

	private int size = 25;
	
	/**
	 * Returns the x value of the ball
	 * @return the x value of the ba;;
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Returns the y value of the ball
	 * @return the y value of the ball
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Sets the x value of the ball
	 * @param change the new x value of the ball
	 */
	public void setX(int change)
	{
		x=change;
	}
	
	/**
	 * Sets the y value of the ball
	 * @param change the new y value of the ball
	 */
	public void setY(int change)
	{
		y=change;
	}
	
	/**
	 * Updates the movement of the ball, sets boundaries for the ball to "bounce"
	 */
	public void update()
	{
		x = x + xVelocity;
		y = y + yVelocity;

		
		if (x<0)
		{
			xVelocity=5;
		}
		else if (x + size >GamePanel.WINDOW_WIDTH)
		{
			xVelocity = -5;
		}
				
		if (y<32)
		{
			yVelocity=5;
		}
		else if (y + size >GamePanel.WINDOW_HEIGHT-27)
		{
			yVelocity = -5;
		}		
	}
	
	/**
	 * Paints the ball element blue
	 * @param g graphics element
	 */
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x,y,size,size);
	}
	
	/**
	 * Reverses the velocity of the ball in the x direction for a bounce
	 */
	private void reverseXDirection()
	{
		this.xVelocity = xVelocity*-1;
	}
	
	/**
	 * Sets the X and Y velocities of the ball to 0, stopping the ball from movement
	 */
	public void stopBall()
	{
		xVelocity=0;
		yVelocity=0;		
	}
	
	/**
	 * Checks the ball's position against P1's position, "bouncing" if in contact
	 * @param player allows user to get player's position
	 */
	public void checkP1Collision(Player player)
	{
		if (this.x >= player.getX() - player.getWidth() && this.x <= player.getX() + player.getWidth())
		{
			if (this.y >= player.getY() && this.y <= player.getY() + player.getHeight())
			{
				reverseXDirection();
			}
		}
		if (player.getY()+player.getHeight() >= Pong.WINDOW_HEIGHT - 25)
		{
			player.setY(Pong.WINDOW_HEIGHT - player.getHeight()-25);
		}
		else if (player.getY() < 25)
		{
			player.setY(25);
		}
	}
	
	/**
	 * Checks the ball's position against P2's position, "bouncing" if in contact
	 * @param player allows user to get player's position
	 */
	public void checkP2Collision(Player2 player)
	{
		if (this.x >= player.getX() - player.getWidth() && this.x <= player.getX() + player.getWidth())
		{
			if (this.y >= player.getY() && this.y <= player.getY() + player.getHeight())
			{
				reverseXDirection();
			}
		}
		if (player.getY()+player.getHeight() >= Pong.WINDOW_HEIGHT - 25)
		{
			player.setY(Pong.WINDOW_HEIGHT - player.getHeight()-25);
		}
		else if (player.getY() < 25)
		{
			player.setY(25);
		}
	}
	
	/**
	 * Checks to see of the ball is the left goal, adding a point to P2 if it is and resets ball position
	 * @param p1 instance of player 1
	 * @param p2 instance of player 2
	 */
	public void checkP1Goal(Player p1, Player2 p2)
	{
		if (this.x <0)
		{
			if (this.y >= 175 && this.y < 275)
			{
				p2.addScore(p2);
				x = Pong.WINDOW_WIDTH/2;
				reverseXDirection();;
			}
		}
	}
	
	/**
	 * Checks to see of the ball is the right goal, adding a point to P1 if it is and resets ball position
	 * @param p1 instance of player 1
	 * @param p2 instance of player 2
	 */
	public void checkP2Goal(Player p1, Player2 p2)
	{
		if (this.x >= 726)
		{
			if (this.y >= 175 && this.y < 275)
			{
				p1.addScore(p1);
				x = Pong.WINDOW_WIDTH/2;
				reverseXDirection();;
			}
		}
	}
	
	/**
	 * Resets game back to default settings for a new games
	 */
	public void resetGame()
	{
		x = 200;
		y = 200;
		xVelocity = 5;
		yVelocity = -5;
	}
}
