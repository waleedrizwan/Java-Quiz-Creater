/*Author : Waleed Rizwan
 * Program description: This program allows a user (teacher) to choose the amount
 * of true or false questions they want and then create the quiz
 * and print out all of the necessary details onto a text field called quiz.txt
 * 
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Quizcreator {
		//These arrays right here are meant for the user to input
		//what the question is and the amount displayed
		//will depends on the amount that the user wants to be displayed
		static int numberofMC;
		static int numberofTF;
		static JTextField topTitle,multipleChoiceNumber,trueFalseNumber;
		static JLabel topLabel,welcomeTitle,mChoicelabel,truefalselabel,blankSpace,blankLabel2,shortAnswer,quizLabel;
		static JButton finishIntro;
		static JPanel introPanel,mainPanel,creatorPanel,quizTitlePanel,bottomPanel;
		
		static JFrame mainFrame;
		static JButton finishQuiz;
		static String quizTitle;
		static ButtonGroup trueButtons ;
		static JTextField shortTextfield;
		
		//creates  the class that will print the values out to the text files
		//Creates arrays needed to take the text from the questions
		static String [] multiplequestionTitles = new String [100];
		static String [] stringQuestion1 = new String [100];
		static String [] stringQuestion2  = new String [100];
		static String [] stringQuestion3 =  new String [100];
		static String [] stringQuestion4 =  new String [100];
		// array of 36 because there are 4 buttons per multiple choice question
		static ButtonGroup [] multiplechoiceButtonGroup = new ButtonGroup[400];// each index holds the four radio buttons for the four answers
		static String [] multipechoiceAnswers=   new String [100];
			static JLabel [] blankLabels = new JLabel [100];
		static int [] truefalseAnswers = new int [100];
		
		
		static String [] truefalsequestionTitles = new String [100];
		static String []  trueAnswers = new String [100];
		static String [] falseAnswers = new String [100];
			
		static int C = 0;
		static int D = 0;
		
		// all GUI components required for each question are here contained on the question panel array which will hold the folliwing blueprint can be found on google classroom
		static JPanel []questionPanel = new JPanel [1000];
		static JPanel [] questiontitlePanel = new JPanel [100];
		static JLabel enterTitleHerepanel;
		static JTextField [] quesitonTitlefield = new JTextField[100];
		static JPanel []  questiongridPanel = new JPanel [100];
		static JPanel [] questionselectPanel = new JPanel [100];
		
		//EACH INDEX OF THIS ARRAY HOLDS 1 QUESTIONS TRUE AND FALSE BUTTONS SO THAT A USER CANNOT SELECT MORE THAN ONE AT THE SAME TIME
		static ButtonGroup [] truefalseButtongroups = new ButtonGroup [200];
		// a 1 means the answer is button 1 and so on....
		static int [] multiplechoiceAnswers = new int [100];
			
		//For each button there is , there exists an array so that the buttongroup can be used
		static JRadioButton answer1Button [] = new JRadioButton [100];
		static JRadioButton answer2Button [] = new JRadioButton [100];
		static JRadioButton answer3Button [] = new JRadioButton [100];
		static JRadioButton answer4Button [] = new JRadioButton [100];
		
		static JRadioButton YESbutton [] = new JRadioButton [100];
		static JRadioButton NObutton [] = new JRadioButton [100];
		
		
		//blank labels are used for spacing 
		static JLabel  []   blankLabel4 = new JLabel[100];
	
		//These j texts fields will hold the what the teacher wants the answers to be
		static JLabel YES[] = new JLabel[100];
		static JLabel NO [] = new JLabel [100];
		static JTextField answer1[] = new JTextField [100];
		static JTextField answer2 [] = new JTextField [100];       	 //These arrays are related 																				
		static JTextField answer3 [] = new JTextField [100];		//answer1[0] is tied to multipleChoice[0]
		static JTextField answer4 [] = new JTextField [100];	
		static JLabel  enterhereLabel [] = new JLabel [100];
		
		//This method runs the gui
		private static void guiApp()
		{
			//All Gui code must be here
			final JFrame mainFrame = new JFrame("Waleeds Superior quiz Creator");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// main panel holds the introduction panel and creator panels
			//the quiz title panel is contained on the creator panel to hold where the user gets to input the title
			 mainPanel = new JPanel (new GridLayout (0,1));
			//the intro panel hold the components that are on the very first frame which are the jlabels and textfields
			 introPanel = new JPanel (new GridLayout (0,1));
			 //the creator panel holds the empty text fields where the user enters the questions and the answers and also the radio buttons where you select the correct answer
			 creatorPanel = new JPanel (new GridLayout (0,1));
			 ///quiz title panel holds where the user can type the titles
			 quizTitlePanel = new JPanel(new GridLayout(0,2));
			 bottomPanel = new JPanel (new GridLayout(0,3));
			
			 //all components required for the Introduction are created and have values assigned to them
			 welcomeTitle = new JLabel("              Welcome to the Java QUIZ Creator!");
			 Font player1ScoreFont = new Font("BernardMTMS", Font.BOLD, 30);
			 welcomeTitle.setFont(player1ScoreFont);
			
			quizLabel = new JLabel ("                          Java Quiz Creator");
			quizLabel.setFont(player1ScoreFont);
			mChoicelabel = new JLabel ("                   How Many Multiple Choice Questions Do You Want?");
			Font mlabelfont = new Font ("ComicSansMS", Font.BOLD, 20);
			 
			mChoicelabel.setFont(mlabelfont);
			multipleChoiceNumber = new JTextField("");
			truefalselabel = new JLabel ("                     How many true false questions do you want?");
			truefalselabel.setFont(mlabelfont);
			
			trueFalseNumber = new JTextField();
			finishIntro = new JButton("Finish");
			finishIntro.setActionCommand("introFinished");
			blankSpace = new JLabel();
			topTitle = new JTextField();
			topLabel = new JLabel("Enter Your Quiz Title Here");
			Font titleFont = new Font("ComicSansMS",Font.BOLD,15);
			topLabel.setFont(titleFont);
			blankLabel2 = new JLabel("");
			finishQuiz = new JButton("Finish Quiz");
			finishQuiz.setActionCommand("finishQuiz");
			shortTextfield = new JTextField();
			
			//components for the creator panel are created
			for (int i= 0; i < 100; i ++)// 0 to 99 for however many the user wants to be added
			{					
					multiplechoiceButtonGroup[i] = new ButtonGroup ();
					truefalseButtongroups[i] = new ButtonGroup();			
					truefalseAnswers[i] = 0;
					multiplechoiceAnswers[i]= 0;
							
					YES [i] = new JLabel("True");
					NO [i] = new JLabel("False");
					YESbutton[i] = new JRadioButton();
					NObutton[i] = new JRadioButton();
			 		blankLabels[i] = new JLabel ();	
					answer1[i] = new JTextField ();
			 		answer2 [i] = new JTextField ();                         																			
			 		answer3 [i] = new JTextField ();							
			 		answer4 [i] = new JTextField ();
			 		questionPanel[i] = new JPanel (new GridLayout (0,1));// panel holds question title panel and question grid panel
			 		
			 		questiontitlePanel[i] = new JPanel (new GridLayout (0,2));
			 		
			 		quesitonTitlefield[i] = new JTextField();			 		
			 		questiongridPanel[i]= new JPanel (new GridLayout (0,2));// holds question select panel
			 		questionselectPanel[i]   =  new JPanel(new GridLayout (0,2));	 		
			 		questionPanel[i] = new JPanel (new GridLayout(0,1));			 		
			 		questiontitlePanel[i] = new JPanel (new GridLayout(0,1));
			 		
			 		questiongridPanel[i] = new JPanel (new GridLayout(0,2));
			 		questionselectPanel[i] = new JPanel (new GridLayout(0,2));
					enterhereLabel[i] = new JLabel ("Enter your questions/answers here and select the correct answer to the question");
					answer1Button [i] = new JRadioButton();
					answer2Button [i] = new JRadioButton();
					answer3Button [i] = new JRadioButton();
					answer4Button [i] = new JRadioButton();
					
					blankLabel4[i] = new JLabel("");
										
					//add every single true/false question button to a button grouped so that the isselected method can be used		
					truefalseButtongroups[i].add(YESbutton[i]);
					truefalseButtongroups[i].add(NObutton[i]);
					multiplechoiceButtonGroup[i].add(answer1Button [i]);
					multiplechoiceButtonGroup[i].add(answer2Button [i]);
					multiplechoiceButtonGroup[i].add(answer3Button [i]);
					multiplechoiceButtonGroup[i].add(answer4Button [i]);
					
					
			}
			//button handler instance is created
			ButtonHandler onClick = new ButtonHandler();
			finishIntro.addActionListener(onClick);	//add an action listener to the buttons
			finishQuiz.addActionListener(onClick);	
			//components added to the Intro panel		
			introPanel.add(welcomeTitle);
			introPanel.add(mChoicelabel);
			introPanel.add(	multipleChoiceNumber);
			introPanel.add(truefalselabel);
			introPanel.add(trueFalseNumber);
			introPanel.add(blankSpace);
			introPanel.add(finishIntro);											
			mainPanel.add(introPanel);
			
			//JSP is the scroll panel added to the main panel
			JScrollPane jsp = new JScrollPane(mainPanel);
			introPanel.setVisible(true);
			mainFrame.add(jsp);
			mainFrame.setSize(800, 800);
			mainFrame.setVisible(true);		
		}
		
		
			// create custom event handler 
			private static class ButtonHandler implements ActionListener{
				
				public void actionPerformed(ActionEvent e){						
					// which button?
					String command = e.getActionCommand();
					//get the string that is the number of each question is entered and then convert it into a intger
					String truefalseString= trueFalseNumber.getText();
					String multipleChoiceString = multipleChoiceNumber.getText();
									
					if(command.equals("finishQuiz")) 
					{
						try {
							quizWriter();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
						//If the user enters 0 for both values and they click the finish button run this code
					if (command.equals("introFinished")&&truefalseString == ("0") || multipleChoiceString == ("0")  )
					{
						JOptionPane.showMessageDialog(null, "You must enter a value for both");
		
					}	
					//if the user clicks the finish button and the user has not entered anything then run the main code
					if( command.equals("introFinished") && truefalseString != (null) && multipleChoiceString != (null))
					{
								
					//Whatever the user has entered take it and convert it into a string with getText and then parse it into an int	
					String stringtruefalse=	trueFalseNumber.getText();
					int	numberTrueFalse = Integer.parseInt(stringtruefalse);
					String stringMultiplechoice  = multipleChoiceNumber.getText();
					int numberMultiplechoice = Integer.parseInt(stringMultiplechoice);				
					//handles user selected correct answers
					if ((numberTrueFalse + numberMultiplechoice) == 0 )
					{
						JOptionPane.showMessageDialog(null, "You must have at least 1 question sir");
		
					}
					// if the user enters less then or 99 questions then call the quiz creator method and add all the components to the java gui
					if ( numberMultiplechoice + numberTrueFalse  <= 99 && (numberTrueFalse + numberMultiplechoice) != 0)
					{
						quizCreator(numberTrueFalse,numberMultiplechoice);
						
					}
					//if the user enters more than 99 total questions then execute this code				
					if (numberTrueFalse > 99 || numberMultiplechoice > 99||numberTrueFalse+ numberMultiplechoice > 99 )
					{
						JOptionPane.showMessageDialog(null, "Sorry sir but you have exceeded the 99 question limit");
					}					
					}//			
				}		
			}//end of button handler
		
		//quiz creator method that creates all of the questions and adds them to the main panel 
		public static void quizCreator ( int numberTrueFalse ,int numberMultiplechoice)
		{
			//these values are crucial to the functioning of this entire program and assignment
			
			
			numberofMC = numberMultiplechoice;
			numberofTF = numberTrueFalse;
			//remove the original components from the frame and add the new panel with the user selected components on it
			introPanel.setVisible(false);
			mainPanel.remove(introPanel);
			mainPanel.add(creatorPanel);
			creatorPanel.setVisible(true);
			creatorPanel.add(quizTitlePanel);
			quizTitlePanel.add(quizLabel);
			//quizTitlePanel.add(topLabel);
			//quizTitlePanel.add(topTitle);
			//quizTitlePanel.setSize(50,50);
			// C,D is the index and depending on what size the user wants that many of each array will be accesed and added
			while ( C < numberMultiplechoice)
			{
				C = C + 1;
				
				creatorPanel.add(questionPanel[C]);
				questionPanel[C].add(questiontitlePanel[C]);
				questiontitlePanel[C].add(enterhereLabel[C]);
				questiontitlePanel[C].add(quesitonTitlefield[C]);
				questionPanel[C].add(questiongridPanel[C]);
				questiongridPanel[C].add(questionselectPanel[C]);
				questionselectPanel[C].add(answer1Button[C]);
				questionselectPanel[C].add(answer1[C]);
				questionselectPanel[C].add(answer2Button[C]);
				questionselectPanel[C].add(answer2[C]);
				questionselectPanel[C].add(answer3Button[C]);
				questionselectPanel[C].add(answer3[C]);
				questionselectPanel[C].add(answer4Button[C]);
				questionselectPanel[C].add(answer4[C]);
			
			}		
			while (D < numberTrueFalse)
			{
				// here the index C is still used so that whatever index was accessed for multiple choice is not used 
				D = D + 1;
				C= C +1 ;
				creatorPanel.add(questionPanel[C]);
				questionPanel[C].add(questiontitlePanel[C]);
				questiontitlePanel[C].add(enterhereLabel[C]);
				questiontitlePanel[C].add(quesitonTitlefield[C]);
				questionPanel[C].add(questiongridPanel[C]);

				questiongridPanel[C].add(questionselectPanel[C]);
				questionselectPanel[C].add(YESbutton[C]);
				questionselectPanel[C].add(YES[C]);
				questionselectPanel[C].add(NObutton[C]);
				questionselectPanel[C].add(NO[C]);			
			}
			//add the bottom panel to the main panel
			creatorPanel.add(bottomPanel);
			//add components to the bottom panel
			// the blank labels are used for spacing reasons because grid layout uses an equal amount of space for all components
			bottomPanel.add(blankLabels[3]);
			bottomPanel.add(blankLabels[4]);
			bottomPanel.add(blankLabels[5]);
			bottomPanel.add(blankLabels[0]);
			bottomPanel.add(finishQuiz);
			bottomPanel.add(blankLabels[1]);
		
		}//quiz creator
		
		public static void quizWriter () throws IOException  
		{
					// depending on what radio button the user has selected when they click the finish button
					// It will register that as the correct answer using 1,2,3,4 to write it onto the text file
					for (int i = 0 ; i < 100; i++){
					if (YESbutton[i].isSelected())
					{
						truefalseAnswers[i] = 1;
						
					}
					if(NObutton[i].isSelected())
					{
						truefalseAnswers[i] = 2;
					}
					
					if(answer1Button[i].isSelected())
					{
						multiplechoiceAnswers[i] = 1;
					}
						
					if(answer2Button[i].isSelected())
					{
						multiplechoiceAnswers[i] = 2;
					}
					
					if(answer3Button[i].isSelected())
					{
						multiplechoiceAnswers[i] = 3;
					}
					
					if(answer4Button[i].isSelected())
					{
						multiplechoiceAnswers[i] = 4;
					}
					
					}//end of for loop
					
										
					//This loop uses the get text method to grab all the string value that the user has inputed
					final PrintWriter fileOut = new PrintWriter (new FileWriter ("Quiz.txt"));				
					// the pattern is number of mc then number of true false then a blank line the title
					fileOut.println(numberofMC);
					fileOut.println(numberofTF);
					fileOut.println("");
					quizTitle = topTitle.getText();
					fileOut.println(quizTitle);
					fileOut.println("");
					
					// if the user has selected multiple choice and no true and false run this code to print only those values
					if (numberofMC > 0 && numberofTF == 0)//for loop in if statement
					{
					for (int i = 1; i <=numberofMC ; i ++)
					{
					//Get text grabs whatever string the user has entered into each text field
					// and fileout println prints whatever it is and prints it onto a line in the text document
					multiplequestionTitles[i] = quesitonTitlefield[i].getText();
					fileOut.println(multiplequestionTitles[i]);
					
					stringQuestion1[i] = answer1[i].getText();
					fileOut.println(stringQuestion1[i]);
					
					
					stringQuestion2[i]= answer2[i].getText();		//the pattern for printing onto the text file is all multiple choice first and then true and false
																	// it goes question titles answers and then the correct answer as a number 1 - 4 for multiple choice																	
					fileOut.println(stringQuestion2[i]);			// which represents the 4 answers and 1 or 2 for true false, there is also a space between each set
					
					stringQuestion3[i]= answer3[i].getText();
					fileOut.println(stringQuestion3[i]);
					
					stringQuestion4[i]= answer4[i].getText();
					fileOut.println(stringQuestion4[i]);
					fileOut.println(multiplechoiceAnswers[i]);
					fileOut.println("");				
	 				}
				
					}
					//if the user has selected only true and false run this code
					if (numberofMC == 0 && numberofTF > 0){
					for (int i = 1; i <= numberofTF ; i ++)
					{
						
						truefalsequestionTitles[i] = quesitonTitlefield[i].getText();
						fileOut.println(truefalsequestionTitles[i]);
						fileOut.println(truefalseAnswers[i]);
						fileOut.println("");
						}				
						}

					
							//if the user chose multiple choice and true and false
							if (numberofMC > 0 && numberofTF > 0){					
							for (int i = 1; i <= numberofMC; i ++)
							{
							//get text is used so that whatever the user enters into the text fields gets stored in an array
							//each multiple question title is stored in the multiple question titles array and this works for the rest of the text fields
							multiplequestionTitles[i] = quesitonTitlefield[i].getText();		
							fileOut.println(multiplequestionTitles[i]);
							
							stringQuestion1[i] = answer1[i].getText();
							fileOut.println(stringQuestion1[i]);
							
							
							stringQuestion2[i]= answer2[i].getText();
							fileOut.println(stringQuestion2[i]);
							
							stringQuestion3[i]= answer3[i].getText();
							fileOut.println(stringQuestion3[i]);
							stringQuestion4[i]= answer4[i].getText();
							fileOut.println(stringQuestion4[i]);
							fileOut.println(multiplechoiceAnswers[i]);
							fileOut.println("");																					
							}//for
							int N;					
							N = numberofMC + 1;
							int M = numberofMC + numberofTF;
							for (int L = N; L <= M;L ++){												
							truefalsequestionTitles[N] = quesitonTitlefield[N].getText();
							fileOut.println(truefalsequestionTitles[N]);		
							fileOut.println(truefalseAnswers[L]);
							N = N + 1;					
							fileOut.println("");
							}
						}//if
					//close the text document when the printing is finished				
					
					fileOut.close();
					//exits the program
					System.exit(0);
		}		
		//the main method
		public  static void main(String[] args) throws IOException, NumberFormatException
		{			
			javax.swing.SwingUtilities.invokeLater(new Runnable ()
			{
				//calls the GUI app method that controls the gui of the program
				public void run()
				{
					guiApp();
				}
			});
		}
}
