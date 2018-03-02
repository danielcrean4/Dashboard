import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardModel {
	
	
	//theList variable stores the main subject list to which all subjectNodes will be added
	static private SubjectList theList;	
	
	
	//No Arg constructor - creates new list 
	public DashboardModel() {
		theList = new SubjectList();
	}
	
	
	//Accessor function for theList
	public SubjectList getSubjectList() {
		return theList;
		
	}
	
	//A new subject is added to theList , only a subject title is passed into the method as a string
	public void addSubject(String subject) {
		theList.addLast(new SubjectNode(subject));
	}
	
	
	public static void loadFromFile() {
		
		try {
			
			//Open the file with previously saved data
			FileReader fileReader = new FileReader("saveData.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			
			//Store all text in String line (file is only 1 line)
			String fileText = "";
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				fileText += line;
			}
								
			bufferedReader.close();
			
			Pattern subjNodePattern = Pattern.compile("<SubjectNode>(.+?)</SubjectNode>");
			Matcher subjNodeMatcher = subjNodePattern.matcher(fileText);
			
			boolean found = false;
			
			while(subjNodeMatcher.find()) {
				//Text appearing between <SubjectNode> tags
				String subjectLine = subjNodeMatcher.group(1);
				
				Pattern subjTitleFinder = Pattern.compile("(.+?)<NoteNode>|(.+?)<EmptySubject>");
				Matcher subjTitleMatcher = subjTitleFinder.matcher(subjectLine);
				
				subjTitleMatcher.find();
				
				String subjTitle;
				
				if (subjTitleMatcher.group(1) != null) {
					subjTitle = subjTitleMatcher.group(1);
				}
				else {
					subjTitle = subjTitleMatcher.group(2);
				}
				
				
				//Test that Subject Title was read correctly , will be replaced with SubjectNode Constructor
				//System.out.println("Subject: " + subjTitle);
				SubjectNode newSubject = new SubjectNode(subjTitle);
				
				theList.addLast(newSubject);
				
				
				//Isolates Note Nodes by checking between the tags
				Pattern noteFinder = Pattern.compile("<NoteNode>(.+?)</NoteNode>");
				Matcher noteMatcher = noteFinder.matcher(subjectLine);
				
				while(noteMatcher.find()) {
					String noteFound = noteMatcher.group(1);
					
					Pattern noteTitleFinder = Pattern.compile("<NoteTitle>(.+)</NoteTitle>");
					Pattern noteContentFinder = Pattern.compile("<NoteContent>(.*)</NoteContent>");
					
					Matcher noteTitleMatcher = noteTitleFinder.matcher(noteFound);
					Matcher noteContentMatcher = noteContentFinder.matcher(noteFound);
					
					while(noteTitleMatcher.find()){
						noteContentMatcher.find();
						//System.out.println("\tNote Title: " + noteTitleMatcher.group(1));
						//System.out.println("\tNote Content: " + noteContentMatcher.group(1));
						
						String newNoteTitle = noteTitleMatcher.group(1);
						String newNoteContent = noteContentMatcher.group(1);
						newSubject.addLast(new NoteNode(newNoteTitle, newNoteContent));
						
						
					}
					
				}
				
				found = true;
			}
			

			if(found == false) {
				System.out.println("Nothing read in");
			}
			
			
			
			
		}
		catch (Exception e) {
			System.out.println("file not found or not working correctly " + e);
		}

	}
	
	public static void saveToFile() {
		try {
			SubjectNode checkSN = theList.getFirst();
			String output = "";
			for(int i = 0; i < theList.size(); i++) {
				output += "<SubjectNode>"+ checkSN.getTitle();
								
				if (!checkSN.isEmpty()) {
					NoteNode checkNN = checkSN.getFirst();
					
					for(int j = 0; j < checkSN.getNoteCount(); j++) {
						output += "<NoteNode><NoteTitle>" + checkNN.getTitle() + "</NoteTitle><NoteContent>" + checkNN.getContent() + "</NoteContent></NoteNode>";
						checkNN = checkNN.getNext();
					}
					
				}
				else {
					output += "<EmptySubject>";
				}
				output += "</SubjectNode>";					
				checkSN = checkSN.getNextSubject();
			}
					
			PrintWriter writer = new PrintWriter("saveData.txt");
			writer.write(output);
			writer.close();
			
		}
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
			else System.out.println("There is an issue saving the file " + e);
			
		}
		
		
	}

}
