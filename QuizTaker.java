/*Programmer: Waleed Rizwan
 * This program uses the text file Quiz.txt created by the quizCreator Class
 * using the data provided by the teacher the student runs the program and takes the quiz their name mark and the date is then printed onto a text field called student.txt
 */
//import statements
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.util.Date;

public class QuizTaker {
	// the string true false and multiple choice are used to parse it
	static String stringnumberTrueFalse;
	static String stringnumberMultipleChoice;
	static String theTitle;
	static int numberMC;
	static int numberTF;
	static int userScore = 0;
	static String quizTitle;
	
	// these Strings hold the titles of every question
	static String[] tfquestionTitles = new String[100];
	static String[] mcquestionTitles = new String[100];
	static JLabel titleLabel;
	
	//these hold the correct answers that are read from the text file that the teacher created
	static int[] mcAnswers = new int[100];
	static int[] tfAnswers = new int[100];
	// these hold what answers the user chooses as correct and compares it to the correct answer to mark it
	static int[] usermcAnswers = new int[100];
	static int[] usertfAnswers = new int[100];

	static String[] answer1 = new String[110];
	static String[] answer2 = new String[110];
	static String[] answer3 = new String[110];
	static String[] answer4 = new String[110];

	static JLabel[] trueLabel = new JLabel[10];
	static JLabel[] falseLabel = new JLabel[10];

	static JLabel[] trueFalse = new JLabel[100];
	static JLabel[] multipleChoice = new JLabel[100];

	static String[] truefalseAnswers = new String[100];
	static String[] multiplechoiceAnswers = new String[110];

	static JRadioButton answer1Button[] = new JRadioButton[110];
	static JRadioButton answer2Button[] = new JRadioButton[110];
	static JRadioButton answer3Button[] = new JRadioButton[110];
	static JRadioButton answer4Button[] = new JRadioButton[110];

	static JRadioButton trueButton[] = new JRadioButton[100];
	static JRadioButton falseButton[] = new JRadioButton[100];
	static JLabel mcquestionLabel[] = new JLabel[100];
	// this array holds the blank spaces that separate the text values that are
	// on the text file depending
	static String[] blankLines = new String[20];
	static JFrame mainFrame;
	static JPanel mainPanel;
	static JPanel quizPanel;
	static JPanel[] questionPanel = new JPanel[100];
	static JPanel[] questiongridPanel = new JPanel[100]; // holds each set of
															// buttons and
															// JLabels for each
															// question
	static JPanel[] questiontitlePanel = new JPanel[100];// holds each question
	 														// title
	//these labels are created to take the string on the text and display it to the user
	static JLabel[] mcTitles = new JLabel[100];// as you can see mc titles holds the multiple choice titles and so on
	static JLabel[] tfTitles = new JLabel[100];
	static JLabel[] choice1Label = new JLabel[100];
	static JLabel[] choice2Label = new JLabel[100];
	static JLabel[] choice3Label = new JLabel[100];
	static JLabel[] choice4Label = new JLabel[100];
	static JPanel bottomPanel;
	static JLabel enternameHere;
	//name field is the text field where the user enters there name
	static JTextField nameField;
	
	static JButton submitButton;
	//for every question that is possible there is a button group so that only one radio button can be selected which is just one answer for each question
	static ButtonGroup[] multiplechoicebuttonGroup = new ButtonGroup[100];
	static ButtonGroup[] trueFalsebuttonGroup = new ButtonGroup[100];
	//blank j labels are used for spacing for the overall design because grid layout is in place 
	static JLabel[] blankLabels = new JLabel[20];

	static JPanel topPanel;
	static JPanel namePanel;

	// guiApp() holds all of the java gui
	private static void guiApp() throws IOException {
		
		quizTitle = new String();
		BufferedReader myFile2 = new BufferedReader(new FileReader("Quiz.txt"));
		blankLines[0] =	myFile2.readLine();  //ignores the first 3 lines of the text file so that it can only read the and store the title string to be printed onto the gui
		blankLines[0] =	myFile2.readLine();
		blankLines[0] =	myFile2.readLine();
		quizTitle = myFile2.readLine();
		myFile2.close();
				
		quizReader();
		final JFrame mainFrame = new JFrame("QuizTaker");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//main panel holds all of the other panels
		mainPanel = new JPanel(new GridLayout(0, 1));
		topPanel = new JPanel(new GridLayout(0, 1));
		namePanel = new JPanel(new GridLayout(0, 2));
		bottomPanel = new JPanel(new GridLayout(0, 3));

		enternameHere = new JLabel("Enter Your Name Here");

		nameField = new JTextField();
		submitButton = new JButton("Click here to submit");
		submitButton.setActionCommand("finished");
		titleLabel = new JLabel();
		//for loop to initialize the blank labels as "" or nothing
		for (int i = 0; i < 20; i++) {
			blankLabels[i] = new JLabel("");
		}
		//depending on how many questions the user has selected initialize that many of each component by using 'i' to access the indexes accordingly
		for (int i = 0; i < (numberTF + numberMC); i++) {
			questionPanel[i] = new JPanel(new GridLayout(0, 1));
			questiontitlePanel[i] = new JPanel(new GridLayout(0, 1));//All components are created in this format
			questiongridPanel[i] = new JPanel(new GridLayout(0, 2));//For every question there is a question panel is created and the following components are added to it
			tfTitles[i] = new JLabel();								//This way only however many questions the user selected will be initialized from each array,increasing effieceincy
			mcTitles[i] = new JLabel();
			choice1Label[i] = new JLabel();//each question field, title field and button is an array
			choice2Label[i] = new JLabel();
			choice3Label[i] = new JLabel();
			choice4Label[i] = new JLabel();
			trueLabel[i] = new JLabel("True");
			falseLabel[i] = new JLabel("False");
			answer1Button[i] = new JRadioButton();
			answer2Button[i] = new JRadioButton();
			answer3Button[i] = new JRadioButton();
			answer4Button[i] = new JRadioButton();
			multiplechoicebuttonGroup[i] = new ButtonGroup();
			multiplechoicebuttonGroup[i].add(answer1Button[i]);
			multiplechoicebuttonGroup[i].add(answer2Button[i]);
			multiplechoicebuttonGroup[i].add(answer3Button[i]);
			multiplechoicebuttonGroup[i].add(answer4Button[i]);

			trueButton[i] = new JRadioButton();//button groups created for the true and false so that only 1 can be selected for each at a time
			falseButton[i] = new JRadioButton();
			trueFalsebuttonGroup[i] = new ButtonGroup();
			trueFalsebuttonGroup[i].add(trueButton[i]);//add the true/false buttons to the buttongroups
			trueFalsebuttonGroup[i].add(falseButton[i]);
			truefalseAnswers[i] = ("");			//initialize each correct answer as a blank value, so that the quiz creator string values can be applied
			multiplechoiceAnswers[i] = ("");
			usermcAnswers[i] = 0;		//initialize both arrays of answers to 0 so that the is selected can be used to find what the users selected
			usertfAnswers[i] = 0;
		}
		//create a new button handler instance 
		
		ButtonHandler onClick = new ButtonHandler();
		//add an action listener and button handler to the submit button
		submitButton.addActionListener(onClick);
		/// add the mainPanel to the main frame and the other panels to the main
		/// panel
		mainFrame.add(mainPanel);
		mainPanel.add(topPanel);
		topPanel.add(blankLabels[0]);
		topPanel.add(blankLabels[0]);
		topPanel.add(enternameHere);
		topPanel.add(nameField);
		topPanel.add(blankLabels[0]);
		topPanel.add(blankLabels[0]);

		// call the quiz creator method
		quizCreator();
		//add the scroll pane 'jsp' to the main panel
		JScrollPane jsp = new JScrollPane(mainPanel);
		mainFrame.add(jsp);
		mainFrame.setSize(700, 500);
		mainFrame.setVisible(true);

	}// end of guiApp()

	private static class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// which button?
			String command = e.getActionCommand();
			
			//if the user clicks the finish button then run this code
			if (command.equals("finished")) {
				
				//for int i = 0 run while i is less then the number of true and false questions
				for (int i = 0; i < numberTF; i++) {
			//if the user selected the first radio button in the first question then register their answer so that it can be compared to the list of correct answers
			//if they selected the first radio button their answer gets registered as 1 and so on for the rest				
					if (trueButton[i].isSelected()) {
						usertfAnswers[i] = 1;

					}

					if (falseButton[i].isSelected()) {
						usertfAnswers[i] = 2;

					}
				}
				for (int i = 0; i < numberMC; i++) {
					if (answer1Button[i].isSelected()) {
						usermcAnswers[i] = (1);
					}
					if (answer2Button[i].isSelected()) {
						usermcAnswers[i] = (2);
					}
					if (answer3Button[i].isSelected()) {
						usermcAnswers[i] = (3);
					}

					if (answer4Button[i].isSelected()) {
						usermcAnswers[i] = (4);
					}
				}
				try {
					//call the quiz marker method
					quizMarker();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}// end of button handler
	//quiz reader method reads all the data from the text file containing the quiz data
	public static void quizReader() throws IOException {
		BufferedReader myFile = new BufferedReader(new FileReader("Quiz.txt"));

		stringnumberMultipleChoice = myFile.readLine();
		stringnumberTrueFalse = myFile.readLine();
		blankLines[0] = myFile.readLine();
		theTitle = myFile.readLine();

		System.out.println("The program is functioning correctly");

		numberMC = Integer.parseInt(stringnumberMultipleChoice);
		numberTF = Integer.parseInt(stringnumberTrueFalse);

		blankLines[1] = myFile.readLine();
		//If there are no true false questions then only read data in this pattern
		// question title answer 1 answer 2 answer 3 answer 4 the correct answer parse that string answer into an int and 
		//then read the blank line that separates the pattern of answers for as many as the teacher or quiz creator had chosen 
		if (numberTF == 0) {
			for (int a = 0; a < numberMC; a++) {
				mcquestionTitles[a] = myFile.readLine();
				answer1[a] = myFile.readLine();
				answer2[a] = myFile.readLine();
				answer3[a] = myFile.readLine();
				answer4[a] = myFile.readLine();
				multiplechoiceAnswers[a] = myFile.readLine();
				mcAnswers[a] = Integer.parseInt(multiplechoiceAnswers[a]);
				blankLines[a + 1] = myFile.readLine();

			}
		}
		//if there are no multiple choice questions then read the data from the text file in this order
		//the question title and the correct answer then parse it into an integer value
		if (numberMC == 0) {
			for (int a = 0; a < numberTF; a++) {
				tfquestionTitles[a] = myFile.readLine();
				truefalseAnswers[a] = myFile.readLine();
				tfAnswers[a] = Integer.parseInt(truefalseAnswers[a]);
				blankLines[0] = myFile.readLine();
			}
		}
		//If there are multiple choice questions and true and false then use this pattern to read it all, which is the multiple choice first then true and false
		if (numberMC > 0 && numberTF > 0) {
			for (int a = 0; a < numberMC; a++) {
				mcquestionTitles[a] = myFile.readLine();
				answer1[a] = myFile.readLine();
				answer2[a] = myFile.readLine();
				answer3[a] = myFile.readLine();
				answer4[a] = myFile.readLine();
				multiplechoiceAnswers[a] = myFile.readLine();
				//read the correct answers and convert from string to integer
				mcAnswers[a] = Integer.parseInt(multiplechoiceAnswers[a]);

				blankLines[a + 1] = myFile.readLine();
			}
			// for loop runs for however many true false question there are
			for (int a = 0; a < numberTF; a++) {
				tfquestionTitles[a] = myFile.readLine();
				truefalseAnswers[a] = myFile.readLine();
				tfAnswers[a] = Integer.parseInt(truefalseAnswers[a]);

				blankLines[0] = myFile.readLine();
			}
			myFile.close();
		}
	}//

	public static void quizCreator() {
		//while x is less then the number of multiple choice questions run this code
		
		for (int x = 0; x < numberMC; x++) {
			mcTitles[x].setText(mcquestionTitles[x]);
			choice1Label[x].setText(answer1[x]);
			choice2Label[x].setText(answer2[x]);			//basically this takes all the answers that were previously read and stored into the answer1,answer2 etc
															// and it uses setText to change the labels to what the person who made the quiz entered
			choice3Label[x].setText(answer3[x]);
			choice4Label[x].setText(answer4[x]);
		}

		for (int x = 0; x < numberTF; x++) {
			tfTitles[x].setText(tfquestionTitles[x]);
		} // for
		// if there are no tf questions then use this pattern to add the components onto the gui
		if (numberTF == 0) {
			for (int C = 0; C < numberMC; C++) {
				mainPanel.add(questionPanel[C]);			//the main panel holds each question panel which holds a question titlepanel and a question grid panel
				questionPanel[C].add(questiontitlePanel[C]);//the question title panels hold each question title label and jlabel for the titles of each question
				questionPanel[C].add(questiongridPanel[C]);	// the question grid panels hold each j button and j label that has each question
				questiontitlePanel[C].add(mcTitles[C]);
				questiongridPanel[C].add(answer1Button[C]);
				questiongridPanel[C].add(choice1Label[C]);
				questiongridPanel[C].add(answer2Button[C]);
				questiongridPanel[C].add(choice2Label[C]);
				questiongridPanel[C].add(answer3Button[C]);
				questiongridPanel[C].add(choice3Label[C]);
				questiongridPanel[C].add(answer4Button[C]);
				questiongridPanel[C].add(choice4Label[C]);
			} // for loop
		} // if statement
		//if there are both types of questions then use this pattern to add the components to the panel
		if (numberMC > 0 && numberTF > 0) {
			for (int C = 0; C < numberMC; C++) {
				mainPanel.add(questionPanel[C]);
				questionPanel[C].add(questiontitlePanel[C]);
				questionPanel[C].add(questiongridPanel[C]);
				questiontitlePanel[C].add(mcTitles[C]);
				questiongridPanel[C].add(answer1Button[C]);
				questiongridPanel[C].add(choice1Label[C]);
				questiongridPanel[C].add(answer2Button[C]);
				questiongridPanel[C].add(choice2Label[C]);
				questiongridPanel[C].add(answer3Button[C]);
				questiongridPanel[C].add(choice3Label[C]);
				questiongridPanel[C].add(answer4Button[C]);
				questiongridPanel[C].add(choice4Label[C]);
			}
			int r = numberMC;
			int d = 0;
			// r equals number mc so that the index of the question panels can be accessed starting off wherever the multiple choice questions left off
			while (d < numberTF) {
				mainPanel.add(questionPanel[r]);
				questionPanel[r].add(questiontitlePanel[r]);
				questionPanel[r].add(questiongridPanel[r]);
				questiontitlePanel[r].add(tfTitles[d]);
				questiongridPanel[r].add(trueButton[d]);
				questiongridPanel[r].add(trueButton[d]);
				questiongridPanel[r].add(trueLabel[d]);
				questiongridPanel[r].add(falseButton[d]);
				questiongridPanel[r].add(falseLabel[d]);

				r = r + 1;
				d = d + 1;

			}
		} // if
		
		if (numberMC == 0) {
			for (int f = 0; f < numberTF; f++) {
				mainPanel.add(questionPanel[f]);
				questionPanel[f].add(questiontitlePanel[f]);
				questionPanel[f].add(questiongridPanel[f]);
				questiontitlePanel[f].add(tfTitles[f]);
				questiongridPanel[f].add(trueButton[f]);
				questiongridPanel[f].add(trueLabel[f]);
				questiongridPanel[f].add(falseButton[f]);
				questiongridPanel[f].add(falseLabel[f]);
			} // for
		} // if
		//add other components
		mainPanel.add(bottomPanel);
		bottomPanel.add(blankLabels[0]);	//the blank labels are there for spacing because I am using grid layout
		bottomPanel.add(blankLabels[2]);
		bottomPanel.add(blankLabels[4]);
		bottomPanel.add(blankLabels[5]);
		bottomPanel.add(submitButton);
		bottomPanel.add(blankLabels[6]);

	}// end of Quiz creator method

	public static void quizMarker() throws IOException {
		PrintWriter fileOut = new PrintWriter(new FileWriter("Studentmark.txt"));
		// file out prints the name date and mark to a text file called studentmark.txt
		String name = nameField.getText();
		nameField.getText();
		//Gets the users name from the nameField Text field
		for (int i = 0; i < numberTF; i++) {			
																	
			if (usertfAnswers[i] == tfAnswers[i]) 
			{		//for integer i is 0 run while i is less then the number of true and false
				userScore = userScore + 1;
			}
		}
		for (int i = 0; i < numberMC; i++) {
			if (usermcAnswers[i] == mcAnswers[i]) {
				userScore = userScore + 1;
			}
		}
		//creates instance of the date object
		Date date = new Date();

		JOptionPane.showMessageDialog(null, name + " You scored " + userScore + " out of " + (numberTF + numberMC));
		fileOut.println("Name: " + name);
		fileOut.println("Mark " + userScore + "/ " + (numberTF + numberMC));
		fileOut.println("Quiz date " + date.toString());
		fileOut.close();
		System.exit(0);

	}

	public static void main(String[] args) {
		// main method to run the GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					guiApp();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
