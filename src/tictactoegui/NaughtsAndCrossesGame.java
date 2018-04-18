package tictactoegui;

import java.util.Random;

public class NaughtsAndCrossesGame {
	
	public int [] board; //board array with 2 spots for x or o and player turn
	public int playerTurn; //which player's turn it is
	private int xOrO; //if user is x or o
	public int winner; //who won game, used for recording data.
	public boolean over; //telling if the game is over
	private int move; //which spot on the board the player picks
        private int count;
	
	public NaughtsAndCrossesGame()
	{
		Random rn = new Random();
		int board1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // last two spots are for player turn and who is x and o, respectively.
		//if user is X's, the last spot will be a 1. If the user is O's, the last spot will be a 2. 
		//if player 1 is first, 2nd to last spot will be 1, if computer, will be 2. 
		playerTurn = rn.nextInt(2) + 1; //randomize who is first
		xOrO = rn.nextInt(2) + 1; //randomize who is x
		winner = 0;
		over = false;
		move = 0;
                count = 0;
		board1[9] = playerTurn;
		board1[10] = xOrO;//when putting in shape, this will be what tells system which shape to put corresponding to user or computer.
                board = board1;
	}
	
	public int[] smartMove() //intelligent game play mode method for computer choosing move
	{
		Random rand = new Random();
		//if comp is one move from winning, fills last spot with a board array with computer "2"
		if(board[0] == 2 && board[1] == 2 && board[3] == 0)
		{
			move = 2;
		}
		else if(board[0] == 2 && board[1] == 0 && board[3] == 2)
		{
			move = 1;
		}
		else if(board[0] == 0 && board[1] == 2 && board[3] == 2)
		{
			move = 0;
		}
		else if(board[3] == 2 && board[4] == 2 && board[5] == 0)
		{
			move = 5;
		}
		else if(board[3] == 2 && board[4] == 0 && board[5] == 2)
		{
			move = 4;
		}
		else if(board[3] == 0 && board[4] == 2 && board[5] == 2)
		{
			move = 3;
		}
		else if(board[6] == 2 && board[7] == 2 && board[8] == 0)
		{
			move = 8;
		}
		else if(board[6] == 2 && board[7] == 0 && board[8] == 2)
		{
			move = 7;
		}
		else if(board[6] == 0 && board[7] == 2 && board[8] == 2)
		{
			move = 6;
		}
		else if(board[0] == 2 && board[3] == 2 && board[6] == 0)
		{
			move = 6;
		}
		else if(board[0] == 2 && board[3] == 0 && board[6] == 2)
		{
			move = 3;
		}
		else if(board[0] == 0 && board[3] == 2 && board[6] == 2)
		{
			move = 0;
		}
		else if(board[1] == 2 && board[4] == 2 && board[7] == 0)
		{
			move = 7;
		}
		else if(board[1] == 2 && board[4] == 0 && board[7] == 2)
		{
			move = 4;
		}
		else if(board[1] == 0 && board[4] == 2 && board[7] == 2)
		{
			move = 1;
		}
		else if(board[2] == 2 && board[5] == 2 && board[8] == 0)
		{
			move = 8;
		}
		else if(board[2] == 2 && board[5] == 0 && board[8] == 2)
		{
			move = 5;
		}
		else if(board[2] == 0 && board[5] == 2 && board[8] == 2)
		{
			move = 2;
		}
		else if(board[0] == 2 && board[4] == 2 && board[8] == 0)
		{
			move = 8;
		}
		else if(board[0] == 2 && board[4] == 0 && board[8] == 2)
		{
			move = 4;
		}
		else if(board[0] == 0 && board[4] == 2 && board[8] == 2)
		{
			move = 0;
		}
		else if(board[2] == 2 && board[4] == 2 && board[6] == 0)
		{
			move = 6;
		}
		else if(board[2] == 2 && board[4] == 0 && board[6] == 2)
		{
			move = 4;
		}
		else if(board[2] == 0 && board[4] == 2 && board[6] == 2)
		{
			move = 2;
		}
		//if user is one move from winning, fills last spot with computer "2" to prevent user from winning in board array
		if(board[0] == 1 && board[1] == 1 && board[3] == 0)
		{
			move = 2;
		}
		else if(board[0] == 1 && board[1] == 0 && board[3] == 1)
		{
			move = 1;
		}
		else if(board[0] == 0 && board[1] == 1 && board[3] == 1)
		{
			move = 0;
		}
		else if(board[3] == 1 && board[4] == 1 && board[5] == 0)
		{
			move = 5;
		}
		else if(board[3] == 1 && board[4] == 0 && board[5] == 1)
		{
			move = 4;
		}
		else if(board[3] == 0 && board[4] == 1 && board[5] == 1)
		{
			move = 3;
		}
		else if(board[6] == 1 && board[7] == 1 && board[8] == 0)
		{
			move = 8;
		}
		else if(board[6] == 1 && board[7] == 0 && board[8] == 1)
		{
			move = 7;
		}
		else if(board[6] == 0 && board[7] == 1 && board[8] == 1)
		{
			move = 6;
		}
		else if(board[0] == 1 && board[3] == 1 && board[6] == 0)
		{
			move = 6;
		}
		else if(board[0] == 1 && board[3] == 0 && board[6] == 1)
		{
			move = 3;
		}
		else if(board[0] == 0 && board[3] == 1 && board[6] == 1)
		{
			move = 0;
		}
		else if(board[1] == 1 && board[4] == 1 && board[7] == 0)
		{
			move = 7;
		}
		else if(board[1] == 1 && board[4] == 0 && board[7] == 1)
		{
			move = 4;
		}
		else if(board[1] == 0 && board[4] == 1 && board[7] == 1)
		{
			move = 1;
		}
		else if(board[2] == 1 && board[5] == 1 && board[8] == 0)
		{
			move = 8;
		}
		else if(board[2] == 1 && board[5] == 0 && board[8] == 1)
		{
			move = 5;
		}
		else if(board[2] == 0 && board[5] == 1 && board[8] == 1)
		{
			move = 2;
		}
		else if(board[0] == 1 && board[4] == 1 && board[8] == 0)
		{
			move = 8;
		}
		else if(board[0] == 1 && board[4] == 0 && board[8] == 1)
		{
			move = 4;
		}
		else if(board[0] == 0 && board[4] == 1 && board[8] == 1)
		{
			move = 0;
		}
		else if(board[2] == 1 && board[4] == 1 && board[6] == 0)
		{
			move = 6;
		}
		else if(board[2] == 1 && board[4] == 0 && board[6] == 1)
		{
			move = 4;
		}
		else if(board[2] == 0 && board[4] == 1 && board[6] == 1)
		{
			move = 2;
		}
		else
		{
			move = rand.nextInt(9);
			while(board[move] != 0)
			{
				if(board[move] != 0)
				{
					move = rand.nextInt(9);
				}
			}
		}
		board[move] = 2;
		//draw shape
		playerTurn = 1;
		board[9] =playerTurn; //switches player turn in board array so that it can be passes through the protocol and connection.
		return board;
	}
	
	public int[] randomCompMove()
	{
		Random rand = new Random();
		move = rand.nextInt(9);
		while(board[move] != 0)
		{
			if(board[move] != 0)
			{
				move = rand.nextInt(9);
			}
		}
		board[move] = 2;
		//draw shape
		playerTurn = 1;
		board[9] = playerTurn;//switches player turn
		return board;
	}
	
	public int[] playerMove() //method for getting user's move
	{ 
		
		if(playerTurn == 1)
		{
			move = getPlayerTurn();
			while(move == 9)
			{
				move = getPlayerTurn();
			}
			while(board[move] != 0)//loop for checking if the spot user clicked is already occupied, telling them to choose again
			{
				move = getPlayerTurn();
			}
			board[move] = 1;
			//draw shape in board[p]
			playerTurn = 2;
		}
		else //AI
		{
			smartMove();
			playerTurn = 2;
		}
		board[9] = playerTurn;
		return board;
	}
	
	public int getPlayerTurn()//might not be necessary in this implementation. This was for seeing which box the user clicked in in Python version
	{
		move = 9;
		//p is which box the player clicks. if clicks outside the game, set to 9.
		//should have switch or if else loop for each action listener button.
		return move;
	}
	
	public boolean checkWinner()//should be checked on both user and computer's turn every time in case there is a winner. end game when over = true
	{
            count++;
		if(board[0] == 1 && board[1] == 1 && board[2] == 1)
		{
			over = true;
			winner = 1;
			//draw winning line in combo
		}
		else if(board[3] == 1 && board[4] == 1 && board[5] == 1)
		{
			over = true;
			winner = 1;
			//draw winning line in combo
		}
		else if(board[6] == 1 && board[7] == 1 && board[8] == 1)
		{
			over = true;
			winner = 1;
			//draw winning line in combo
		}
		else if(board[0] == 1 && board[3] == 1 && board[6] == 1)
		{
			over = true;
			winner = 1;
			//draw winning line in combo
		}
		else if(board[1] == 1 && board[4] == 1 && board[7] == 1)
		{
			over = true;
			winner = 1;
			//draw winning line in combo
		}
		else if(board[2] == 1 && board[5] == 1 && board[8] == 1)
		{
			over = true;
			winner = 1;
			//draw winning line in combo
		}
		else if(board[0] == 1 && board[4] == 1 && board[8] == 1)
		{
			over = true;
			winner = 1;
			//draw winning line in combo
		}
		else if(board[2] == 1 && board[4] == 1 && board[6] == 1)
		{
			over = true;
			winner = 1;
			//draw winning line in combo
		}
		else if(board[0] == 2 && board[1] == 2 && board[2] == 2)
		{
			over = true;
			winner = 2;
			//draw winning line in combo
		}
		else if(board[3] == 2 && board[4] == 2 && board[5] == 2)
		{
			over = true;
			winner = 2;
			//draw winning line in combo
		}
		else if(board[6] == 2 && board[7] == 2 && board[8] == 2)
		{
			over = true;
			winner = 2;
			//draw winning line in combo
		}
		else if(board[0] == 2 && board[3] == 2 && board[6] == 2)
		{
			over = true;
			winner = 2;
			//draw winning line in combo
		}
		else if(board[1] == 2 && board[4] == 2 && board[7] == 2)
		{
			over = true;
			winner = 2;
			//draw winning line in combo
		}
		else if(board[2] == 2 && board[5] == 2 && board[8] == 2)
		{
			over = true;
			winner = 2;
			//draw winning line in combo
		}
		else if(board[0] == 2 && board[4] == 2 && board[8] == 2)
		{
			over = true;
			winner = 2;
			//draw winning line in combo
		}
		else if(board[2] == 2 && board[4] == 2 && board[6] == 2)
		{
			over = true;
			winner = 2;
			//draw winning line in combo
		}
                else if(count == 9)
		{
			over = true;
			winner = 0;
			//this is the tie if no winning combination has been reached
		}
                else
                {
                    over = false;
                    winner = 0;
                }
		
		if(over == true && winner == 1)
		{
			//user wins. record data.
		}
		else if(over == true && winner == 2)
		{
			//comp wins. record data.
		}
		else if(over == true && winner == 0)
		{
			//tie. record data
		}
		else
		{
			over = false;
			//game continues. this just checks to see if game is going or not
		}
		return over; //end game here
	}
	
}
