import swiftbot.Button;
import swiftbot.SwiftBotAPI;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		SwiftBotAPI sb = new SwiftBotAPI();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("******Welcome to Simon Says******");
		System.out.println("******---------------------******");
		System.out.println("Press 1 to Start the Game");
		System.out.println("Press 0 to Exit the Game");
		
		String userInp = scanner.nextLine();
		System.out.println("You entered: " + userInp);
		scanner.close();
		
		if(Integer.parseInt(userInp) == 1) {
			clearScreen();
			System.out.println("Thanks For Playing this game");
			int[] colourToLightUp = {0,0,255};
			sb.fillUnderlights(colourToLightUp);
			}
		else if(Integer.parseInt(userInp) == 0) {
			System.exit(0);
		}
		else {
			System.out.println("Please Enter a valid input");
		}
		
		
		
		

		
	}
	
	private static void clearScreen() {
		System.out.print("\033[H\033[2J");
        System.out.flush();
	}
}
