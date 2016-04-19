/**
 *
 * @author Heather Bradfield
 * @version 11/01/2015
 */

public class BingoChip 
{
	private char letter;
	private int number;

	public BingoChip(int number)
	{
		setNumber( number );		
		setLetter();		
	}

	private void setNumber(int number)
	{
        this.number = number;
	}

	private void setLetter()
	{
        this.letter = BingoCard.BINGO_KEYS.charAt((this.number-1)/BingoCard.MAX_VALUES_PER_LETTER);
	}

	public int getNumber()
	{
		return this.number;
	}

	public char getLetter()
	{
		return this.letter;
	}

	public String toString()
	{
		return this.letter + " " + this.number;
	}
}