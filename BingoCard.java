/**
 *
 * @author Heather Bradfield
 * @version 11/01/2015
 */
 
import java.util.*;
public class BingoCard 
{
	private TreeSet<Character> bingoChars;
	private HashMap<Character, TreeSet<Integer>> card;
    public final static String BINGO_KEYS = "BINGO";
    public final static int MAX_VALUES_PER_LETTER = 15;
    public final static int NUMBERS_PER_LETTER = 5;

	public BingoCard()
    {
        this.bingoChars = new TreeSet<>();
        this.card = new HashMap<>();
        Random rand = new Random();
        for (int i = 0; i < BINGO_KEYS.length(); i++)
        {
            TreeSet<Integer> values = new TreeSet<>();
            int max = (i+1)*MAX_VALUES_PER_LETTER;
            int min = (i*MAX_VALUES_PER_LETTER)+1;
            while(values.size() < NUMBERS_PER_LETTER)
            {
                values.add(rand.nextInt((max-min)+1)+min);
            }
            this.card.put(BINGO_KEYS.charAt(i),values);
        }
        printCard();
	}

	public void checkNumber(BingoChip chip)
	{
        Character letter = chip.getLetter();
        TreeSet<Integer> values = this.card.get(letter);
        boolean found = false;
        for (Integer value : values)
        {
            if (value == chip.getNumber()) found = true;
        }
        if (found)
        {
            values.add(0);
            this.card.replace(letter,values);
            this.bingoChars.add(letter);
        }
	}
	
	public boolean isWinner()
	{
		return this.bingoChars.size() == NUMBERS_PER_LETTER;
	}
	
	public void printCard()
	{
        for (int i = 0; i < BINGO_KEYS.length(); i++)
        {
            System.out.print(BINGO_KEYS.charAt(i) + "  ");
            TreeSet<Integer> values = this.card.get(BINGO_KEYS.charAt(i));
            for (Integer value : values)
            {
                System.out.printf("%3d",value);
                System.out.print(" ");
            }
            System.out.println();
        }
	}
}