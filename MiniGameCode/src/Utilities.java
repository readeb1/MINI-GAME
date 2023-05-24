public class Utilities {

	public static void main(String[] args) {
		// Testing Methods
		clrsnc();
		// prn("hello ");
		// prn(nameOfCard(12));

		// prn("You have " + quadraticDiscriminent(3, -5, 11) + " Zeros ");
		// prn("The hypotenuse " + hypotenuse(4, 9));
		// prn("The power is" + power(5, 2));

		// String fancyHello = fancify("Hello");
		// prn(fancyHello);

		// String redHello = red("Hello");
		// prn(redHello);
		prn(largestDouble(3.5, 4.5, 5.5, 6.5));
		// repeatPrint("$", 40);
		prn("" + isLeapYear(2000));

		printRightTriangle("*", 10);


	}
	/**
	 * Prints a right angle triangle of spcified size using string picked
	 * @param string The charcter used when making the triangle
	 * @param number The length of the triangle(how many rows down)
	 */
	public static void printRightTriangle(String string, int size){
		if (size <= 0 || string == null || string.isEmpty())
		//This returns early if the proper input doesn't work
		return; // Early return due to nothing to print

		for(int row = 1; row <= size; row ++){
			repeatPrint(string, row);
			System.out.println();
		}

	}
	public static void printBalancedTriangle(String string, int size){
		if (size >= 0 || string == null || string.isEmpty())
		return; //Returning early if nothing is in the input
		
		for (int row = 1; row <= size; row++){
			repeatPrint(" ", size - row);
			repeatPrint(string, 2 * row - 1);
		}

	
	}
/**
 * Finds if the year is a leap year
 * @param year the year inputted by user
 * @return true if it is a leap false if its not
 */
	public static boolean isLeapYear(int year) {

		if (year % 100                                                                                                                                                                                          == 0) {
			return false;
		}
		
		if (year % 4 == 0) {
			return true;
		}

		return false;

	}

	public static String red(String input) {
		return "\033[0;31m" + input + "\033[0m";
	}

	// Random Int
	public static int randomInt(int low, int high) {
		return (int) ((high - low + 1) * Math.random() + low);
	}

	// Println
	public static <T> void prn(T thing) {
		System.out.println(thing);
	}

	// Fancify
	public static String fancify(String string) {
		return "gjg " + string + "|/_";
	}

	// Print
	public static <T> void prt(T thing) {
		System.out.print(thing);
	}

	// Clear Screen
	public static void clrsnc() {
		prn("\033[H\033[2J");
	}

	// Name of Weekday
	public static String nameOfWeekday(int weekday) {
		weekday = Math.floorMod(weekday, 7); // Bonus: positive remainder when divided by 7.

		if (weekday == 0)
			return "Sun";
		else if (weekday == 1)
			return "Mon";
		else if (weekday == 2)
			return "Tues";
		else if (weekday == 3)
			return "Wed";
		else if (weekday == 4)
			return "Thur";
		else if (weekday == 5)
			return "Fri";
		else if (weekday == 6)
			return "Sat";
		else
			return "Invalid weekday number.";
	}

	// Die Roll
	public static int dieRoll(int times) {
		int total = 0;
		for (int i = 0; i < times; i++) {
			// total += getRandomInt(1, 6);
		}
		return total;
	}

	// Celsuis
	public static double celsiusToFahrenheit(double celsius) {
		return celsius * 9 / 5 + 32;
	}

	// Power of
	public static double power(double base, int exponent) {
		double result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}

		return result;
	}

	// Card Number
	public static String nameOfCard(int cardNumber) {

		String cardName;
		if (cardNumber >= 2 && cardNumber <= 10) {
			cardName = "" + cardNumber;
		} else if (cardNumber == 1) {
			cardName = "Ace";
		} else if (cardNumber == 11) {
			cardName = "Jack";
		} else if (cardNumber == 12) {
			cardName = "Queen";
		} else if (cardNumber == 13) {
			cardName = "King";
		} else {
			cardName = "Invalid Card";
		}
		return cardName;

	}

	// Discriminent
	public static int quadraticDiscriminent(double a, double b, double c) {

		int discriminent = (int) (-b + ((b * b) - 4 * a * c)) / (int) (2 * a);

		if (discriminent == 0) {
			return 1;
		} else if (discriminent > 0) {

			return 2;
		} else {
			return 0;
		}

	}

	// Hypo
	public static double hypotenuse(double a, double b) {
		double hypotenuse = (a * a) + (b * b);
		hypotenuse = Math.sqrt(hypotenuse);
		return hypotenuse;
	}

	public static void repeatPrint(String string, int times) {
		for (int i = 0; i < times; i++) {
			System.out.print(string);
		}
	}

	/**
	 * Finds the largest double number
	 * @param a 1st number
	 * @param b 2nd number
	 * @param c 3rd number
	 * @param d 4th number
	 * @return returns the largest double 
	 */
	public static double largestDouble(double a, double b, double c, double d) {
		double largest = a;
		if (b > largest) {
			largest = b;
		}
		if (c > largest) {
			largest = c;
		}
		if (d > largest) {
			largest = d;
		}
		return largest;

	}

/**
     * Calculates and returns the sum of all integers between two specified numbers (inclusive).
     * @param low The lower number.
     * @param high The higher number.
     * @return The sum of integers between low and high. If high is a smaller number than low, this method will return 0.
     */
    public static int sumSequence(int low, int high) {
        if (high < low)
            return 0; // Early return. No numbers to add up.
        
        int sum = 0;
        for (int number = low; number <= high; number++) {
            sum += number;
        }

        return sum;
    }
}
