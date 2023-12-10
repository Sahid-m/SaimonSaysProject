# SaimonSaysProject
This is a Game on a swiftbot ( an rasberry pi with wheels , and a lot of sensors attached to it ) made with the help of api that is used to interact with low level components so a developer just needs to write 
high level code for functionality. 

Step to get this working on your swiftbot:

Download the A-22-SimonSays file on your laptop  

Start your swiftbot (just connect the battery to it and red light should start) 

Wait for email for your swiftbot IP (If on campus) 

Start WinSCP (Download if you don’t have it as its very important for swiftbot) 

Enter your ip in hostname and pi as username and your password 

Navigate to you’re a-22-SimonSays file and drag and drop to your swiftbot on left of winSCP in Document folder 

Open Your CMD (type cmd in search or press WIN + R and type “cmd”. 

Write “ssh pi@yourswiftbotip” example “ssh pi@192.168.56.110”. 

Type password and enter 

You would see “pi@rasberrypi” in green that means youre connected to it 

Now navigate to folder that you put java file in using “cd Document/” 

To see if you are in folder that contains the java file write “ls” and enter it would show you all files in folder 

Make sure you have “jsch-0.1.55.jar” and “SwiftBotAPI-5.1.0.jar” files in same folder as well (see labsheet if you don’t know how to get that) 

Now run the following command “java -cp "SwiftBotAPI-5.1.0.jar": A-22-SimonSays.java” 

It would take few seconds to run 

