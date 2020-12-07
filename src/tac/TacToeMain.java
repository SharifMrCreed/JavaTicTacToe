package tac;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class TacToeMain {
	

	static int position = 0;
	static Scanner scanner = new Scanner(System.in);
	static boolean isNumber = false;
	static String user = null;
	static int count = 0;

	static ArrayList<Integer> userPosition = new ArrayList<>();
	static ArrayList<Integer> computerPosition = new ArrayList<>();
	static ArrayList<Integer> positionArray = new ArrayList<>();

	public static void main(String[] args) {
		
		char [][] gameBoard = {
				{ ' ','1', ' ', '|',' ','2', ' ', '|',' ','3', ' '},
				
				{ '-','-','-', '+', '-','-','-', '+','-','-','-' },

				{ ' ','4', ' ', '|',' ','5', ' ', '|',' ','6', ' '},

				{ '-','-','-', '+', '-','-','-', '+','-','-','-' },

				{ ' ','7', ' ', '|',' ','8', ' ', '|',' ','9', ' '},
		};
		
		printGameboard(gameBoard);
		
		char [][] gameBoardNew = {
				{ ' ',' ', ' ', '|',' ',' ', ' ', '|',' ',' ', ' '},
				
				{ '-','-','-', '+', '-','-','-', '+','-','-','-' },

				{ ' ',' ', ' ', '|',' ',' ', ' ', '|',' ',' ', ' '},

				{ '-','-','-', '+', '-','-','-', '+','-','-','-' },

				{ ' ',' ', ' ', '|',' ',' ', ' ', '|',' ',' ', ' '}
		};
		
		instructions();
		
		checkPosition();
		user = "player";
		placeSymbol(gameBoardNew);
		
	}
	
	private static void instructions() {

		JOptionPane.showMessageDialog(null, "INSTRUCTIONS"
				+ "The gameboard");
		
	}

	public static void placeSymbol(char [][] gameBoard) {
	
		char symbol = ' ';
		
		if(user == "player") {
			symbol = 'X';
		}else {
			symbol = '0';
		}
		switch (position){
		case 1:
			gameBoard[0][1] = symbol;
			break;
		case 2:
			gameBoard[0][5] = symbol;
			break;
		case 3:
			gameBoard[0][9] = symbol;
			break;
		case 4:
			gameBoard[2][1] = symbol;
			break;
		case 5:
			gameBoard[2][5] = symbol;
			break;
		case 6:
			gameBoard[2][9] = symbol;
			break;
		case 7:
			gameBoard[4][1] = symbol;
			break;
		case 8:
			gameBoard[4][5] = symbol;
			break;
		case 9:
			gameBoard[4][9] = symbol;
			break;
		default:
			break;
		}
		printGameboard(gameBoard);
		count ++;
		
		if (count <5) {
			if (user == "player") {
				user = "computer";
				Random random = new Random();
				int num = random.nextInt(9) + 1;
				while(positionArray.contains(num)) {
					num = random.nextInt(9) + 1;
				}
				position = num;
				positionArray.add(position);
				computerPosition.add(position);
				placeSymbol(gameBoard);
			} else {
				user = "player";
				checkPosition();
				placeSymbol(gameBoard);
			}
		}else if(count < 8) {
			if (!checkWin()) {
				if (user == "player") {
					user = "computer";
					Random random = new Random();
					int num = random.nextInt(9) + 1;
					while(positionArray.contains(num)) {
						num = random.nextInt(9) + 1;
					}
					position = num;
					positionArray.add(position);
					computerPosition.add(position);
					placeSymbol(gameBoard);
				} else {
					user = "player";
					checkPosition();
					placeSymbol(gameBoard);
				}
			}
		} else {
			System.out.println("It a draw");
		}
	}
	
	public static boolean checkWin() {
		int[][] combinations = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
				{1,4,7},
				{2,5,8},
				{3,6,9},
				{1,5,9},
				{7,5,3}		
		};
		for(int[] row : combinations) {
			ArrayList<Integer> comb = new ArrayList<>();
			
			for(int integer: row) {
				comb.add(integer);
				
			}

			if(userPosition.containsAll(comb)){
				JOptionPane.showMessageDialog(null, "You won");
				return true;
			}else if(computerPosition.containsAll(comb)){
				JOptionPane.showMessageDialog(null, "You Lose");
				return true;
			}
			
		}
		return false;
		
	}
	
	
	
	public static void checkPosition() {

		System.out.println();
		System.out.print("Enter a number Corresponding to the box you want to put an x: ");

		String input = JOptionPane.showInputDialog("Enter a number Corresponding to the box you want to put an x: ");
		try {
			position = Integer.valueOf(input);
			while(positionArray.contains(position)) {
				input = JOptionPane.showInputDialog("Position  already filled, choose Again");
			}

			positionArray.add(position);
			userPosition.add(position);
			isNumber = true;
			
		} catch (Exception e) {
			System.out.println("please enter a number, dots commas letters and all other characters are not supported: ");
		
		}
		
		if(isNumber == false) {
			checkPosition();
			
		}
	}
	
	public static void printGameboard(char [][] gameBoard) {
		System.out.println();
		for (char [] row: gameBoard) {
			System.out.print("                            ");
			for(char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
