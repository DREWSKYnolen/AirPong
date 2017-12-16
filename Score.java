import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Creates an object with the name and score of the player that wins the game
 * @author Drew Nolen
 * @version 4/6/2016
 */
public class Score implements Comparable<Score>, Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	static ArrayList<Score> scoreBoard = new ArrayList<Score>();
	
	public Score(String _name, int _score)
	{
		this.name = _name;
		this.score = _score;
	}
	
	public int compareTo(Score s)
	{
		int result = 0;
		if (this.score>s.getScore())
		{
			result = 1;
		}
		else if (this.score < s.getScore()){
			result = -1;
		}
		return result;
	}
	
	/**
	 * Returns the score
	 * @return the score
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Sets the score for the player
	 * @param _score the score for the player
	 */
	public void setScore(int _score)
	{
		this.score = _score;
	}
	
	/**
	 * Returns the name for the player
	 * @return the name for the player
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the name for the player
	 * @param _name name for the player
	 */
	public void setName(String _name)
	{
		this.name = _name;
	}
	
		
	/**
	 * Sorts the list into ascending order
	 */
	public static void sort()
	{
		Collections.sort(scoreBoard);
	}

	/**
	 * Saves the high score list to a serialized file
	 */
	public static void save()
	{
		try{
			
			FileOutputStream fos = new FileOutputStream("HIGH_SCORES");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(scoreBoard);
			oos.flush();
			oos.close();
		}
		catch (Exception e)
		{
			System.out.println("Error :" + e.getMessage());
		}
	}
	
	/**
	 * Loads the list of HighScores
	 * @return List of high Scores
	 */
	@SuppressWarnings("unchecked")
	public static void load()
	{
		try{
			FileInputStream fis = new FileInputStream("HIGH_SCORES");
			ObjectInputStream ois = new ObjectInputStream(fis);
			scoreBoard = (ArrayList<Score>) ois.readObject();
			ois.close();
			fis.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}	
}
