import swiftbot.Button;
import java.util.Random;
import swiftbot.SwiftBotAPI;
import java.util.Scanner;


public class Main {
	
	
	static SwiftBotAPI sb = new SwiftBotAPI();
	static Scanner scanner = new Scanner(System.in);
	
	int colors[][] = {
			{0,0,255}, // Blue
			{0,255,0}, // Green
			{255,0,0}, // Red
			{255,255,0} // Yellow
			};
	
	public static void main(String[] args) throws InterruptedException {
		
		
		System.out.println("******Welcome to Simon Says******");
		System.out.println("******---------------------******");
		System.out.println("Press 1 to Start the Game");
		System.out.println("Press 0 to Exit the Game");
		
		
		
		GetInp();
		
		

		
	}
	
	private static void GetInp() throws InterruptedException {
		int userInp = scanner.nextInt();
		System.out.println("You entered: " + userInp);
		try {
			scanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(userInp == 1) {
			clearScreen();
			System.out.println("Thanks For Playing this game");
			int[] colourToLightUp = {0,0,255};
			sb.fillUnderlights(colourToLightUp);
			Thread.sleep(3000);
			sb.disableUnderlights();
			System.exit(0);
			}
		else if(userInp == 0) {
			System.out.println("Thanks For Playing Amigo This will close automatically in 5 sec");
			Thread.sleep(5000);
			System.exit(0);
		}
		else {
			System.out.println("Please Enter a valid input");
			GetInp();
		}
	}
	
	
	private void StartGame() {
		 int colornum = getRandomNumber(0,3);
	     sb.setUnderlight(null, null);
	      
	      
	}
	
	private static void clearScreen() {
		System.out.print("\033[H\033[2J");
        System.out.flush();
	}
	
	public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
