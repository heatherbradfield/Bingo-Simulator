/**
 *
 * @author Heather Bradfield
 * @version 11/01/2015
 */
 
import java.util.*;

public class BingoGame 
{
	private final int NUM_OF_CHIPS = 75;
	private int numOfPlayers;
	private ArrayList<BingoChip> bingoDrum;
	private BingoCard[] playersCards;

	public BingoGame(int numOfPlayers) 
	{
        setNumOfPlayers(numOfPlayers);
        this.bingoDrum = new ArrayList<>(NUM_OF_CHIPS);
        this.playersCards = new BingoCard[this.numOfPlayers];
        createBingoDrum();
        createPlayersCards();
	}
	
	private void createBingoDrum()
	{
        for (int i = 1; i <= NUM_OF_CHIPS; i++)
        {
            this.bingoDrum.add(new BingoChip(i));
        }
	}
	
	private void createPlayersCards()
	{
        for (int i = 0; i < this.numOfPlayers; i++)
        {
            System.out.println("---> Creating bingo card for Player " + (i+1));
            this.playersCards[i] = new BingoCard();
            //this.playersCards[i].printCard();
            System.out.println();
        }
	}
	
	private void setNumOfPlayers(int numOfPlayers)
	{
        if (numOfPlayers > 0) this.numOfPlayers = numOfPlayers;
        else this.numOfPlayers = 1;
	}

	public int getNumberOfChips()
	{
		return NUM_OF_CHIPS - this.bingoDrum.size();
	}

	public BingoChip pullChip()
	{
        Random rand = new Random();
        BingoChip pulled = this.bingoDrum.remove(rand.nextInt(bingoDrum.size()));
        System.out.println("--> Calling " + pulled.getLetter() + " " + pulled.getNumber());
        return pulled;
	}
	
	public void play()
	{
        boolean done = false;
        int player = 0;
        while (!done)
        {
            BingoChip picked = pullChip();
            for (player = 0; player < this.numOfPlayers; player++)
            {
                this.playersCards[player].checkNumber(picked);
                System.out.println("Player " + (player+1) + "'s card:");
                this.playersCards[player].printCard();
                System.out.println();
            }
            for (player = 0; player < this.numOfPlayers; player++)
            {
                if (this.playersCards[player].isWinner())
                {
                    done = true;
                    System.out.println("!!! Player " + (player+1) + " says BINGO !!!");
                }
            }
        }
        System.out.println(getNumberOfChips() + " chips were called.");
	}
}