import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardModel {
	
	
	// theList variable stores the main subject list to which all subjectNodes will be added
	static private ArrayList<SubjectNode> theList;	
	
	
	// No Arg constructor - creates new list 
	public DashboardModel() {
		theList = new ArrayList<SubjectNode>();
	}
	
	
	// Accessor function for theList
	public ArrayList<SubjectNode> getSubjectList() {
		return theList;
		
	}
	
	// A new subject is added to theList , only a subject title is passed into the method as a string
	public void addSubject(String subject) {
		theList.add(new SubjectNode(subject));
	}
	
	
	// To be called upon starting the Application - will populate the ArrayLists of Subjects / Notes with saved content from previous runs
	
	public static void loadFromFile() {
		
		try {
			
			//Open the file with previously saved data
			FileReader fileReader = new FileReader("saveData.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			
			//Store all text in String line (file is only 1 line)
			String fileText = "";
			
			//Iterate through all lines to populate complete file text
			
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				fileText += line;
			}
								
			bufferedReader.close();
			
			// Find any text appearing between SubjectNode opening/closing tags using Regex
			
			Pattern subjNodePattern = Pattern.compile("<SubjectNode>(.+?)</SubjectNode>");
			Matcher subjNodeMatcher = subjNodePattern.matcher(fileText);
			
			boolean found = false;
			
			while(subjNodeMatcher.find()) {
				
				//Text appearing between <SubjectNode> tags
				String subjectLine = subjNodeMatcher.group(1);
				
				//Find the Text that appears before either a NoteNode Tag or Empty Subject Tag (this is subject title)
				Pattern subjTitleFinder = Pattern.compile("(.+?)<NoteNode>|(.+?)<EmptySubject>");
				Matcher subjTitleMatcher = subjTitleFinder.matcher(subjectLine);
				
				subjTitleMatcher.find();
				
				String subjTitle;
				
				// Finding the subject title from the regex - if the subject is empty , group(1) will be null --> we would store group(2) in this case
				if (subjTitleMatcher.group(1) != null) {
					subjTitle = subjTitleMatcher.group(1);
				}
				else {
					subjTitle = subjTitleMatcher.group(2);
				}
				
				
				// SubjectNode Constructor with the title that was read in

				SubjectNode newSubject = new SubjectNode(subjTitle);
				
				// Add the SubjectNode to the model's ArrayList
				
				theList.add(newSubject);
				
				
				//Isolates Note Nodes by checking between the tags
				Pattern noteFinder = Pattern.compile("<NoteNode>(.+?)</NoteNode>");
				Matcher noteMatcher = noteFinder.matcher(subjectLine);
				
				while(noteMatcher.find()) {
					String noteFound = noteMatcher.group(1);
					
					// Isolate Note Title / Note Content by checking between the corresponding tags
					Pattern noteTitleFinder = Pattern.compile("<NoteTitle>(.+)</NoteTitle>");
					Pattern noteContentFinder = Pattern.compile("<NoteContent>(.*)</NoteContent>");
					
					// Search for the patterns in the Note Text read in from the file
					Matcher noteTitleMatcher = noteTitleFinder.matcher(noteFound);
					Matcher noteContentMatcher = noteContentFinder.matcher(noteFound);
					
					// Loop iterates through all Note titles/content found
					while(noteTitleMatcher.find()){
						
						noteContentMatcher.find();
						
						//Gets string data from text file for title/content
						String newNoteTitle = noteTitleMatcher.group(1);
						String newNoteContent = noteContentMatcher.group(1);
						
						//calls constructor with this data and adds to the Subject's Note List
						newSubject.addLast(new NoteNode(newNoteTitle, newNoteContent));
						
						
					}
					
				}
				
				found = true;
			}
			
			// If the above block was not executed, prints to console

			if(found == false) {
				System.out.println("Nothing read in");
			}
			
			
			
			
		}
		
		//Exception letting us know if there was an issue reading in the file
		
		catch (Exception e) {
			System.out.println("file not found or not working correctly " + e);
		}

	}
	
	
	// Outputs Subject/Note data to text file. Nests the Subjects/Notes between corresponding tags so that they can be distinguished from user input data
	public static void saveToFile() {
		
		try {
			//Iterate through all SubjectNodes belonging to the list
			String output = "";
			for(SubjectNode sn : theList) {
				output += "<SubjectNode>"+ sn.getTitle();
							
				//If the Subject Node is not empty ~~ it has notes stored in it, we iterate through those notes to save them
				if (!sn.isEmpty()) {
					
					//Create Iterator for Note Nodes belonging to each subject
					Iterator<NoteNode> checkNN = sn.getNoteList().iterator();
					
					//output each note to the file
					while(checkNN.hasNext()) {
						
						// Surrounds each Note title/content text with corresponding tags
						NoteNode nn = checkNN.next();
						output += "<NoteNode><NoteTitle>" + nn.getTitle() + "</NoteTitle><NoteContent>" + nn.getContent() + "</NoteContent></NoteNode>";
					}
					
				}
				
				// If the SubjectNode does not contain any notes, we include a tag to denote this
				else {
					output += "<EmptySubject>";
				}
				
				// Adding a closing tag for the subject
				
				output += "</SubjectNode>";					
			}
					
			
			// Code to output the string to the 'saveData.txt' file
			
			PrintWriter writer = new PrintWriter("saveData.txt");
			writer.write(output);
			writer.close();
			
		}
		
		// If the list is empty, print a blank file to the destination
		catch (Exception e) {
			if(e.getMessage() == "Empty") {
				PrintWriter writer;
				try {
					writer = new PrintWriter("saveData.txt");
					writer.write("");
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
			//If another exception, print to console
			else System.out.println("There is an issue saving the file " + e);
			
		}
		
		
	}

}
