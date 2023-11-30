import swiftbot.Button;

import java.util.ArrayList;
import java.util.Random;
import swiftbot.SwiftBotAPI;
import swiftbot.Underlight;
import java.util.concurrent.CountDownLatch;

import java.util.Scanner;

public class Main {
	
	// The Program is a little different from the flowchart and pseudo code 
	// as i took a little creative freedom with things and we didn't had time to add in additional functionalities 
	// But we made everything with 100% of our best effort
	// Made by - Sahid and Parv 
	
	
	// Initialising all the Variables / Classes that would be used later
    static SwiftBotAPI sb = new SwiftBotAPI();
    // initialse the scanner that is used for taking inputs from terminal
    static Scanner scanner = new Scanner(System.in);
    // this variable is used for checking if user have entered wrong buttons and failed the game
    static boolean[] IsUserGood = {
        true
    };
    
    // It is used for storing random number in an array to display lights for swiftbot
    static ArrayList<Integer> RandomGenNum = new ArrayList<>();
    // this is used for storring User Entered button
    static ArrayList<Button> UserArray = new ArrayList<>();
    // Initialise the userlives
    

    

    // Main Function just to start the Game 
    public static void main(String[] args) throws InterruptedException {

        System.out.println("******Welcome to Simon Says******");
        System.out.println("******---------------------******");
        System.out.println("Press 1 to Start the Game");
        System.out.println("Press 0 to Exit the Game");

        
        // Calls the Get Input Function Which takes Integer from user in Terminal
        GetInp();

    }
 
    private static void GetInp() throws InterruptedException {
        
    	String userInp = scanner.next(); // Gets Integer 
        
    	 
    	int number = 0;
        
    	 try {
    		 // Converts the string to integer
             number = Integer.valueOf(userInp);
             System.out.println("You entered: " + number);

             
         } catch (NumberFormatException e) {
        	 // if input is non convertible i.e. characters returns error
             System.out.println("Invalid integer input");
             number = -1;
         }

        
        						// Checks for input and do things accordingly
        if (number == 1) {
            clearScreen(); 		// Clears The Terminal
            System.out.println("Thanks For Playing this game");
            Thread.sleep(1000);
            StartGame();
        } else if (number == 0) {
        	clearScreen();   	// Clears The Terminal
            System.out.println("See You Again Champ");
            Thread.sleep(3000);
            System.exit(0);		// Closes the program
        } else {
            System.out.println(" \nPlease Enter a valid input");  // Tells the user to input a valid answer
            GetInp();		// Calls The same program again
        }
    }
    
 // Clears the Screen/ Terminal by using ANSI escape codes
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    
    // Generates a random number from min to max using random class as the Math method was not enough random for smaller number between 1-4
    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    
    		// Handles The Under Lights Generation with Taking no as Argument to start the corresponding lights
    private static void handleUnderLights(int no) throws InterruptedException {
    	
    	// blue rgb value assign to integer array
    		int[] blue = {
                0,
                0,
                255
            };
    	// red rgb value assign to integer array
            int[] red = {
                255,
                0,
                0
            };
        // green rgb value assign to integer array
            int[] green = {
                0,
                255,
                0
            };
         // yellow rgb value assign to integer array
            int[] yellow = {
                255,
                0,
                255
            };
            
            
            // Starts the under lights of Swiftbot corresponding to the integer given to the function
            if(no == 1) {
            	sb.setUnderlight(Underlight.FRONT_LEFT, blue);
                Thread.sleep(2000);
                sb.disableUnderlights();
            }
            else if (no == 2 ) {
            	sb.setUnderlight(Underlight.FRONT_RIGHT, red);
                Thread.sleep(2000);
                sb.disableUnderlights();
            }
            else if (no == 3 ) {
            	sb.setUnderlight(Underlight.BACK_LEFT, green);
                Thread.sleep(2000);
                sb.disableUnderlights();
            }
            else if (no == 4) {
            	sb.setUnderlight(Underlight.BACK_RIGHT, yellow);
                Thread.sleep(2000);
                sb.disableUnderlights();
            }
    	
    }

    
    // Handles If User has inputed a wrong answer
    private static void handleWrongAnswer() {
        System.out.println("You were wrong");
        IsUserGood[0] = false;	// Sets the boolean to false

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
 // Handles If User has inputed a right answer
    private static void handleCorrectAnswer() {
        System.out.println("Yes, you're right");

    }
    
    
    // Returns Button Element that corresponds to the number
    private static Button getButtonForUnderlight(int led) {
        switch (led) {
            case 1:
                return Button.A;
            case 2:
                return Button.X;
            case 3:
                return Button.B;
            case 4:
                return Button.Y;
            default:
                throw new IllegalArgumentException("Invalid UnderLights number");
        }
    }
    
    
    
    // Controls all the button Logic of SwiftBot i.e. checks if the generated button array is equal to User inputed Button Array
    private static void GetButtonInput() {
    	
    	System.out.println("Your turn! Enter the sequence:");
    	
    	for(int i=0;i<RandomGenNum.size();i++){
    		final Button[] pressedButton = {null};
    		
    		
    		// This is a synchronisation function in Java which helps Program (Thread) to wait for a task to be done
    		CountDownLatch latch = new CountDownLatch(1);
    		
    		
    		sb.enableButton(Button.A, () -> {
                pressedButton[0] = Button.A; // Adds Button.A to an Button Array that is later used to compare Random generated Button array
                latch.countDown(); // This Indicates the Program(Thread) that task is done
            });

            sb.enableButton(Button.B, () -> {
                pressedButton[0] = Button.B;
                latch.countDown(); // This Indicates the Program(Thread) that task is done
            });

            sb.enableButton(Button.X, () -> {
                pressedButton[0] = Button.X;
                latch.countDown(); // This Indicates the Program(Thread) that task is done
            });

            sb.enableButton(Button.Y, () -> {
                pressedButton[0] = Button.Y;
                latch.countDown(); // This Indicates the Program(Thread) that task is done
            });
            
            
            try {
                latch.await();	// This indicates Program (Thread) to wait for the task
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            sb.disableAllButtons();	// Disables all the Button 
            
            
            UserArray.add(pressedButton[0]); 	// Add the Pressed Button to UserArray 
            
            
            if (pressedButton[0] != getButtonForUnderlight(RandomGenNum.get(i))) {
                handleWrongAnswer();
                return; // End the game if the input is incorrect
            }
            
            // Sends the call to Correct Answer if the array is matched 
            handleCorrectAnswer();
        }
    	
    }
    
    //Asks User if want to continue or exit the Program
    private static void AskUserContinue(int Round , int score) {
    	clearScreen();
    	System.out.println("Hey you're going good its been " + Round + " Going Good huh! ");
    	System.out.println("Do you wanna keep playing or maybe exit cause bored ?");
    	System.out.println("Enter 1 to continue");
    	System.out.println("Enter 0 to exit");
    	
    	String userInp = scanner.next(); // Gets Integer 
        
    	int number = 0;
        
   	 	try {
            number = Integer.valueOf(userInp);
            System.out.println("You entered: " + number);

            
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer input");
            number = -1;
        }
        
        
        
        if(number == 1) {
        	return;
        }
        else if(number == 0) {
        	if(score >= 5) {	// if score is more than or equal to 5 Does Victory Dance
        		System.out.println("Thanks For Playing! Your Score was " + score);
        		System.out.println("SwiftBot will do The celebration dive");
        		
        		System.out.println(" \nPlace Your Swiftbot on ground! After Press Enter");
        		try (Scanner scanner = new Scanner(System.in)) {
                    while (true) {
                        String userInput = scanner.nextLine();
                        if (userInput.isEmpty()) {	// Checks if userInput is empty 
                            break; // Break out of the loop if Enter is pressed
                        } else {
                            System.out.println("Please press Enter to continue.");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        		DoVictoryDance();
        		
        		System.out.println("See You Again Champ");
        		try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        		try {
                    scanner.close();  			// Closes the Input and responds with error if any
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
                System.exit(0);
        		
        	}
        	else {
        		System.out.println("Thanks For Playing! Your Score was " + score);
        		System.out.println("See You Again Champ");
        		try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        		try {
                    scanner.close();  			// Closes the Input and responds with error if any
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
                System.exit(0);
        	}
        }
        else {
        	System.out.println(" \n Sorry Wrong Input \n Exiting the program");
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.exit(0);
        	
        }
    	
    }
    
private static void handleUnderLightsForVictoryDance(int no) throws InterruptedException {
    	
    	// blue rgb value assign to integer array
    		int[] blue = {
                0,
                0,
                255
            };
    	// red rgb value assign to integer array
            int[] red = {
                255,
                0,
                0
            };
        // green rgb value assign to integer array
            int[] green = {
                0,
                255,
                0
            };
         // yellow rgb value assign to integer array
            int[] yellow = {
                255,
                0,
                255
            };
            
            
            // Starts the under lights of Swiftbot corresponding to the integer given to the function
            if(no == 1) {
            	sb.fillUnderlights(blue);
               
                
            }
            else if (no == 2 ) {
            	sb.fillUnderlights(green);
                
            }
            else if (no == 3 ) {
            	sb.fillUnderlights(red);
                
            }
            else if (no == 4) {
            	sb.fillUnderlights(yellow);
                
            }
    	
    }


    
    // Does the Victory dance 
    private static void DoVictoryDance() {
    	
    	// Uses a different thread to generate random lights and show them
    	// Initialise the thread with what to do 
    	Thread lightingThread = new Thread(() -> {
    		
    		// Checks if the thread is doing something if yes then not run this and wait for thread to finish the task
			while (!Thread.interrupted()) {
                int randomNum = getRandomNumber(1, 4);	// Gets the random number and assigns to randomnum
                try {
                	//takes the random number and generates random color 
                    handleUnderLightsForVictoryDance(randomNum);
                    // wait for .5 sec
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;  // Break out of the loop if interrupted
                }
            }
        });
    	
    	// Starts the other thread
        lightingThread.start();
		
		// Moves forward at 100 velocity for 2sec
		sb.move(100, 100,2000);
		//Moves Backward at 100 velocity for 2sec
		sb.move(-100, -100, 2000);
		// Turns at 45 degree roughly
		sb.move(50,0,1000);
		// Moves forward at 100 velocity for 2sec
		sb.move(100, 100,2000);
		// Moves backward at 100 velocity for 2sec
		sb.move(-100, -100, 2000);
		// Turns at 45 degree roughly
		sb.move(0,50,1000);
		
		// Interupts the thread and closes the thread
		lightingThread.interrupt();
		
		// disables the underlights 
		sb.disableUnderlights();
		
	}

	// Main Start Game Function 
    private static void StartGame() throws InterruptedException {

        

        

        int score = 0; // Initialise score and round
        int RoundNo = 1;
        int MAX_ROUND = 30;

        	
        //IMPORTANT - SET THE MAX_ROUND TO ANY INT FOR MAXIMUM NUMBER OF ROUNDS
        	
        	// Check If IsUserGood boolean is true
            if(IsUserGood[0]) {
            	
            	// initialises an for loop with number of rounds as Z its currently set to 30 but can change to number of rounds you wanna play
            	for(int z = 0; z <= MAX_ROUND; z++) {
            		
            		clearScreen(); // Clears The Terminal
            		
            		System.out.println("Please wait for the instructions to click the button it wont work if you click button at showing time");
            		System.out.println("\nAlso If the Button does not show any message in terminal when clicked wait for 1 sec or .5 sec and click again \n");
            		System.out.println("\nThe Button Assigments to Lights are as follows: ");
            		System.out.println("A = Front left light = Blue");
            		System.out.println("X = Front right light = Red");
            		System.out.println("B = Back left light = Green");
            		System.out.println("Y = Back right light = Yellow");
            		int colornum = getRandomNumber(1, 4); // Gets the Random number between 1 and 4 including them
                    
                    RandomGenNum.add(colornum); // Adds the random number to arraylist
                    System.out.println("\n ");
                    System.out.println("Round No: " + RoundNo);	// Shows Your Current Round and Score
                    System.out.println("Your Current Score: " + score);
                    System.out.println(" ");
                    System.out.println("Showing you the Squence --- Wait For it to finish before doing anything");
                    
                    // Loops over the array showing all the numbers randomly generated as Colors
                    for(int i=0;i<RandomGenNum.size();i++){
                    	
                    	
                    	 try {
                             Thread.sleep(1000);		// Wait for 1 sec before the other color is shown
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                    	 handleUnderLights(RandomGenNum.get(i));
                    }
                    
                    GetButtonInput();		// Gets the Input and its logic is explained above
                    	
                    
                    // Checks if IsUserGood is false
                     if(!IsUserGood[0]) {
                    	clearScreen();
                    	if(score >= 5) {	// if score is more than or equal to 5 Does Victory Dance
                    		System.out.println("Thanks For Playing! Your Score was " + score);
                    		System.out.println("SwiftBot will do The celebration dive");
                    		System.out.println("");
                    		System.out.println(" \nPlace Your Swiftbot on ground! After Press Enter");
                    		try (Scanner scanner = new Scanner(System.in)) {
                                while (true) {
                                    String userInput = scanner.nextLine();
                                    if (userInput.isEmpty()) {	// Checks if userInput is empty 
                                        break; // Break out of the loop if Enter is pressed
                                    } else {
                                        System.out.println("Please press Enter to continue.");
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                    		DoVictoryDance();
                    		
                    		System.out.println("See You Again Champ");
                    	}
                    	else {
                    		System.out.println("Thanks For Playing! Your Score was " + score);
                    		System.out.println("See You Again Champ");
                    		try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                    		try {
                                scanner.close();  			// Closes the Input and responds with error if any
                            } catch (Exception e) {
                                
                                e.printStackTrace();
                            }
                            System.exit(0);
                    	}
                    	 
                        break;	// Exit the loop if IsUserGood is false
                    }
                     
                     score += 1; // Increases the Score by 1
                     
                     if(RoundNo % 5 == 0) {		// if round is 5 , 10 or divisible by 5 it ask the user to continue or exit
                    	 AskUserContinue(RoundNo,score);
                     }
                    
                     System.out.println("Round " + RoundNo + " Over");
                     
                     RoundNo += 1;	// Increases the Round No by 1
                     
                     
                     System.out.println("Your Score: " + score);
                     
                     
                     
                     try {
                         Thread.sleep(3000);	// waits for 3sec after the next round starts
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     
                }
            }
            
            
            // wait for 2 sec after displaying Game Over
            try {
                scanner.close();  			// Closes the Input and responds with error if any
            } catch (Exception e) {
                
                e.printStackTrace();
            }
        System.exit(0);
    }
    
    
}
