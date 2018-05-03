import java.util.*;
public class NumberConverter {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("This program can convert any decimal number to any base under ten (except one) and to hexadecimal.");
		int  playAgain = 1;
		while (playAgain == 1) {
			askForNumAndBase();
			System.out.print("Would you like to convert another number? Press 1 for Yes, and 0 for No. ");
			playAgain = userInput.nextInt();
		}
	}
	
	public static void fromDecimal(int x, int newBase, int oldBase, int originalNumber) {
		String[] letters = {"A", "B", "C", "D", "E", "F"};
		int convert = x;
		String converted = "";
		while (convert > 0) {
			if (convert % newBase < 10) {
				converted = convert % newBase  + converted;
			} else {
				converted = letters[convert % newBase - 10] + converted;
			}
				convert /= newBase;
		}
		System.out.println("The base " + oldBase + " number " + originalNumber + " in base " + newBase + ": " + converted);
	}
	
	public static int toDecimal(String x, int base) {
		int[] numbers = {10, 11, 12, 13, 14, 15};
		String[] letters = {"A", "B", "C", "D", "E", "F"};
		int total = 0;
		String toBeConverted = x;
		for (int i = 0; i <= toBeConverted.length() - 1; i++) {
			if (!(toBeConverted.substring(i, i + 1).equals("A") ||
				 toBeConverted.substring(i, i + 1).equals("B") ||
				 toBeConverted.substring(i, i + 1).equals("C") ||
				 toBeConverted.substring(i, i + 1).equals("D") ||
				 toBeConverted.substring(i, i + 1).equals("E") ||
				 toBeConverted.substring(i, i + 1).equals("F"))) {
					total += Integer.parseInt(toBeConverted.substring(i, i + 1)) * (int) Math.pow(base, toBeConverted.length() - 1 - i); 
			} else {
				int index = 0;
					for (int j = 0; j <= letters.length - 1; j++) {
						if (letters[j].equals(toBeConverted.substring(i, i + 1))) {
							index = j;
						}
					}
					total += numbers[index] * (int) Math.pow(base, toBeConverted.length() - 1 - i);
			}
		}
		return total;
	}
	
	public static void askForNumAndBase() {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Please enter a number. ");
		int needsConversion = userInput.nextInt();
		int oldBase = askForOldBase(needsConversion);
		int newBase = askForNewBase();
		System.out.println();
		fromDecimal(toDecimal("" + needsConversion, oldBase), newBase, oldBase, needsConversion);
		System.out.println();
	}
	
	public static int askForOldBase(int needsConversion) {
		Scanner userInput = new Scanner(System.in);
		System.out.print("What base is this number in? ");
		int oldBase = userInput.nextInt();
		if (oldBase == 2) {
			String checkForBinary = "" + needsConversion;
			for (int i = 0; i < checkForBinary.length(); i++) {
				if (Integer.parseInt(checkForBinary.substring(i, i + 1)) != 1 &&
					Integer.parseInt(checkForBinary.substring(i, i + 1)) != 0) {
						System.out.println("Please type a binary number.");
						askForOldBase(needsConversion);
				}
			}
		} else if (!((oldBase != 1 && oldBase <= 10) || oldBase == 16)) {
			System.out.println("Please choose a valid base!");
			askForOldBase(needsConversion);
		}
		return oldBase;
	}
	
	public static int askForNewBase() {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Enter a base to convert this number to. ");
		int newBase = userInput.nextInt();
		if (!((newBase != 1 && newBase <= 10) || newBase == 16)) {
			System.out.println("Please choose a valid base!");
			askForNewBase();
		}
		return newBase;
	}

}