package il.ac.tau.cs.sw1.trivia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TriviaGUI {

	private static final int MAX_ERRORS = 3;
	private Shell shell;
	private Label scoreLabel;
	private Composite questionPanel;
	private Label startupMessageLabel;
	private Font boldFont;
	private String lastAnswer;
	private int strikes;
	private int score;
	private int count5050; //for indication if the user used it's free token "50-50"
	private int countPass; //for indication if the user used it's free token for "Pass"
	private static Map<String[],List<String>> riddlesMap = new HashMap<String[],List<String>>();
	private static List<String[]> riddlesList = new ArrayList<>();
	private int readingIndex = 0;
	private int questionsAnswered;
	private String[] currentRiddle;
	private String currentAnswer;
	private String endTitles = "Your final score is " + score + " after " + readingIndex + " questions.";
	
	// Currently visible UI elements.
	Label instructionLabel;
	Label questionLabel;
	private List<Button> answerButtons = new LinkedList<>();
	private Button passButton;
	private Button fiftyFiftyButton;

	public void open() {
		createShell();
		runApplication();
	}

	/**
	 * Creates the widgets of the application main window
	 */
	private void createShell() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		shell.setText("Trivia");

		// window style
		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width / 3,
				monitor_bounds.height / 4));
		shell.setLayout(new GridLayout());

		FontData fontData = new FontData();
		fontData.setStyle(SWT.BOLD);
		boldFont = new Font(shell.getDisplay(), fontData);

		// create window panels
		createFileLoadingPanel();
		createScorePanel();
		createQuestionPanel();
	}

	/**
	 * Creates the widgets of the form for trivia file selection
	 */
	private void createFileLoadingPanel() {
		final Composite fileSelection = new Composite(shell, SWT.NULL);
		fileSelection.setLayoutData(GUIUtils.createFillGridData(1));
		fileSelection.setLayout(new GridLayout(4, false));

		final Label label = new Label(fileSelection, SWT.NONE);
		label.setText("Enter trivia file path: ");

		// text field to enter the file path
		final Text filePathField = new Text(fileSelection, SWT.SINGLE
				| SWT.BORDER);
		filePathField.setLayoutData(GUIUtils.createFillGridData(1));

		// "Browse" button
		final Button browseButton = new Button(fileSelection, SWT.PUSH);
		browseButton.setText("Browse");

		// "Play!" button
		final Button playButton = new Button(fileSelection, SWT.PUSH);
		playButton.setText("Play!");
		
		browseButton.addSelectionListener(new browseListener(filePathField));	
		playButton.addSelectionListener(new playListener(filePathField));
	}

	/**
	 * Creates the panel that displays the current score
	 */
	private void createScorePanel() {
		Composite scorePanel = new Composite(shell, SWT.BORDER);
		scorePanel.setLayoutData(GUIUtils.createFillGridData(1));
		scorePanel.setLayout(new GridLayout(2, false));

		final Label label = new Label(scorePanel, SWT.NONE);
		label.setText("Total score: ");

		// The label which displays the score; initially empty
		scoreLabel = new Label(scorePanel, SWT.NONE);
		scoreLabel.setLayoutData(GUIUtils.createFillGridData(1));
	}

	/**
	 * Creates the panel that displays the questions, as soon as the game
	 * starts. See the updateQuestionPanel for creating the question and answer
	 * buttons
	 */
	private void createQuestionPanel() {
		questionPanel = new Composite(shell, SWT.BORDER);
		questionPanel.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		questionPanel.setLayout(new GridLayout(2, true));

		// Initially, only displays a message
		startupMessageLabel = new Label(questionPanel, SWT.NONE);
		startupMessageLabel.setText("No question to display, yet.");
		startupMessageLabel.setLayoutData(GUIUtils.createFillGridData(2));
	}

	/**
	 * Serves to display the question and answer buttons
	 */
	private void updateQuestionPanel(String question, List<String> answers) {
		// Save current list of answers.
		List<String> currentAnswers = answers;
		answerButtons.clear();
		
		// clear the question panel
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		// create the instruction label
		instructionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		instructionLabel.setText(lastAnswer + "Answer the following question:");
		instructionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the question label
		questionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		questionLabel.setText(question);
		questionLabel.setFont(boldFont);
		questionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the answer buttons
		for (int i = 0; i < 4; i++) {
			Button answerButton = new Button(questionPanel, SWT.PUSH | SWT.WRAP);
			answerButton.setText(answers.get(i));
			GridData answerLayoutData = GUIUtils.createFillGridData(1);
			answerLayoutData.verticalAlignment = SWT.FILL;
			answerButton.setLayoutData(answerLayoutData);
			
			answerButton.addSelectionListener(new answerListener(answerButton)); 		
			answerButtons.add(answerButton);
		}

		// create the "Pass" button to skip a question
		passButton = new Button(questionPanel, SWT.PUSH);
		passButton.setText("Pass");
		GridData data = new GridData(GridData.END, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		passButton.setLayoutData(data);
		passButton.addSelectionListener( new passListener());
		
		// create the "50-50" button to show fewer answer options
		fiftyFiftyButton = new Button(questionPanel, SWT.PUSH);
		fiftyFiftyButton.setText("50-50");
		data = new GridData(GridData.BEGINNING, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		fiftyFiftyButton.setLayoutData(data);
		fiftyFiftyButton.addSelectionListener( new fiftyFiftyListener());
		
		// two operations to make the new widgets display properly
		questionPanel.pack();
		questionPanel.getParent().layout();
		
		
		// Setting the hint buttons to be pressable only when they should be
		
		if (countPass == 0) {
			passButton.setEnabled(true);
			
		} else {
			
			if (score>0) {
				passButton.setEnabled(true);
				
			} else {
				passButton.setEnabled(false);
			}
		}
		
		if (count5050 == 0) {
			fiftyFiftyButton.setEnabled(true);
			
		} else {
			
			if (score>0) {
				
				fiftyFiftyButton.setEnabled(true);
				
			} else {
				fiftyFiftyButton.setEnabled(false);
			}
		}
		

	}

	/**
	 * Opens the main window and executes the event loop of the application
	 */
	private void runApplication() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		boldFont.dispose();
	}
	
	private static List<String> readAllTokens(File file)
			throws IOException {
		
		//Taking the file and inserts each line to a list of Strings
		
		List<String> tokens = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(
				new FileReader(file));) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				String[] split = line.split(System.lineSeparator());
				for (String token : split) {
					if (token.length() > 0) {
						//add non-empty tokens to the list
						tokens.add(token);
					}
				}
				tokens.add(line);
				
			}
		}
		return tokens;
	}
	
	private static void addToRiddlesMap (String[] currentRiddle){
		
		List<String> res = new ArrayList<String>();
		String[] answersArr = Arrays.copyOfRange(currentRiddle, 1, currentRiddle.length );;
		
		for (String answer : answersArr) {
			res.add(answer);
		}
		
		riddlesMap.put(currentRiddle, res);
		
		
	}
	
	private class browseListener implements SelectionListener  {
		
		Text filePathField;
		
		public browseListener(Text filePathField) {
			this.filePathField = filePathField;
		}
		public void widgetDefaultSelected(SelectionEvent arg0) {
		return;
	}

	public void widgetSelected(SelectionEvent arg0) {
		if (arg0.getSource() instanceof Button) {
			String filePath = GUIUtils.getFilePathFromFileDialog(shell);
			
			if (filePath == null) {
				return;
			}
			
			filePathField.setText(filePath);
		}
	}
	
}
	
	private class answerListener implements SelectionListener {
		
		Button answerButton;
		
		public answerListener(Button answerButton) {
			this.answerButton = answerButton;
		}
		public void widgetDefaultSelected(SelectionEvent arg0) {
			return;
			
		}

		public void widgetSelected(SelectionEvent arg0) {
			
			//currentAnswer = riddlesMap.get(currentRiddle).get(0);
			if (answerButton.getText().equals(currentAnswer)) {
				
				//If the button pressed contains the correct answer to the current question
				lastAnswer = "Correct! ";
				strikes =0;
				score += 3;
				
				scoreLabel.setText(Integer.toString(score));
				
				if (readingIndex == riddlesList.size()) {
					
					//The Game needs to end
					GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " + score + " after " + readingIndex + " questions.");
				}
				
				//Onto the next question
				questionsAnswered++;
				readingIndex++; //Increasing the index to read the next riddle
				currentRiddle = riddlesList.get(readingIndex); //loading the next riddle
				currentAnswer = riddlesMap.get(currentRiddle).get(0); //loading the current answer
				List<String> currentAnswers = riddlesMap.get(currentRiddle);	//the other answers
				Collections.shuffle(currentAnswers); //suffeling the answers
				updateQuestionPanel(currentRiddle[0], currentAnswers); //presenting the user with the next question
				
			} else {
				//If the button pressed is the wrong answer
				
				lastAnswer = "Wrong! ";
				strikes++;
				score -= 2;
				scoreLabel.setText(Integer.toString(score));
				
				if (strikes == MAX_ERRORS || readingIndex == riddlesList.size()) {
					
					//The Game needs to end
					GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " + score + " after " + questionsAnswered + " questions.");
				}
				
				//Onto the next question
				questionsAnswered++;
				readingIndex++; //Increasing the index to read the next riddle
				currentRiddle = riddlesList.get(readingIndex); //loading the next riddle
				currentAnswer = riddlesMap.get(currentRiddle).get(0); //loading the current answer
				List<String> currentAnswers = riddlesMap.get(currentRiddle);	//the other answers
				Collections.shuffle(currentAnswers); //shuffeling the answers
				updateQuestionPanel(currentRiddle[0], currentAnswers); //presenting the user with the next question	
			}			
		}
	}
	
	private class playListener implements SelectionListener {
		
		Text filePathField;
		
		public playListener(Text filePathField) {
			this.filePathField = filePathField;
		}
		
		public void widgetDefaultSelected(SelectionEvent arg0) {
			return;
		}

		public void widgetSelected(SelectionEvent arg0) {
			
			questionsAnswered = 0;
			count5050 = 0;
			countPass = 0;
			
			if (arg0.getSource() instanceof Button) {
				String filePath = filePathField.getText();
				
				File file = new File(filePath);
				//Loading the file into the system
				
				
				try {
					
					//Reading all the lines in the file to a list of strings
					List<String> tempList = readAllTokens(file);
					
					
					for (String riddle : tempList) {
						
						//Adding to the map a pair of specific riddle (question with
						//it's answers) and a list of it's answers
						
						String[] currentRiddle = riddle.split("\t");
						//currentRiddle[0] = currentRiddle[0]+"?";
						addToRiddlesMap(currentRiddle);
						//We also need an ordered list we can iterate over
						
						riddlesList.add(currentRiddle);
					}
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				Collections.shuffle(riddlesList);
				
				lastAnswer = "";
				String[] firstQuestion = riddlesList.get(0);
				List<String> firstQuestionAnswers = riddlesMap.get(firstQuestion);
				questionsAnswered++;
				readingIndex++;
				score = 0;
				currentRiddle = riddlesList.get(0);
				currentAnswer = riddlesMap.get(currentRiddle).get(0);
				Collections.shuffle(firstQuestionAnswers);
				updateQuestionPanel(firstQuestion[0], firstQuestionAnswers);
			}
		}
		
	}

	private class passListener implements SelectionListener{

		public void widgetDefaultSelected(SelectionEvent arg0) {
			return;		
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {

			if (countPass == 0) {
				countPass++;
				score--;
				scoreLabel.setText(Integer.toString(score));
				lastAnswer = "";
				
				//Onto the next question, without updating "Questions Answeres"
				readingIndex++; //Increasing the index to read the next riddle
				currentRiddle = riddlesList.get(readingIndex); //loading the next riddle
				currentAnswer = riddlesMap.get(currentRiddle).get(0); //loading the current answer
				List<String> currentAnswers = riddlesMap.get(currentRiddle);	//the other answers
				Collections.shuffle(currentAnswers); //suffeling the answers
				updateQuestionPanel(currentRiddle[0], currentAnswers); //presenting the user with the next question
			} else {
				if (score>0) {
					countPass++;
					score--;
					scoreLabel.setText(Integer.toString(score));
					lastAnswer = "";
					
					//Onto the next question, without updating "Questions Answeres"
					readingIndex++; //Increasing the index to read the next riddle
					currentRiddle = riddlesList.get(readingIndex); //loading the next riddle
					currentAnswer = riddlesMap.get(currentRiddle).get(0); //loading the current answer
					List<String> currentAnswers = riddlesMap.get(currentRiddle);	//the other answers
					Collections.shuffle(currentAnswers); //suffeling the answers
					updateQuestionPanel(currentRiddle[0], currentAnswers); //presenting the user with the next question
				} else {
					return;
				}
			}
			
		}
		
		
	}

	private class fiftyFiftyListener implements SelectionListener{

		public void widgetDefaultSelected(SelectionEvent arg0) {
			return;		
		}


		public void widgetSelected(SelectionEvent arg0) {

			if (count5050 == 0) {
				count5050++;
				score--;
				scoreLabel.setText(Integer.toString(score));
				
				int hideCounter = 0;
				List<Integer> numArray = new ArrayList<Integer>();
				
				for (int i=0; i<4 ; i++) {
					Integer addition = i; 
					numArray.add(addition);
				}
				Collections.shuffle(numArray);
				
				int j=0;
				while (hideCounter <2) {
					
					int index = numArray.get(j);
					Button currentButton = answerButtons.get(index);
					
					if (!currentButton.getText().equals(currentAnswer)) {
						currentButton.setEnabled(false);
						hideCounter++;
					}
					
					j++;
					
				}
				
				
			} else {
				if (score>0) {
					count5050++;
					score--;
					scoreLabel.setText(Integer.toString(score));
					
					int hideCounter = 0;
					List<Integer> numArray = new ArrayList<Integer>();
					
					for (int i=0; i<4 ; i++) {
						Integer addition = i; 
						numArray.add(addition);
					}
					Collections.shuffle(numArray);
					
					int j=0;
					while (hideCounter <2) {
						
						int index = numArray.get(j);
						Button currentButton = answerButtons.get(index);
						
						if (!currentButton.getText().equals(currentAnswer)) {
							currentButton.setEnabled(false);
							hideCounter++;
						}
						
						j++;
						
					}
					
				} else {
					return;
				}
			}
			
		}
		
		
	}
}
