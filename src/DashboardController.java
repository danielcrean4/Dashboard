import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class DashboardController {
	
	
	//Views and Model controlled by the controller
	private DashboardModel theModel;
	private DashboardView theView;
	
	//Active Subjects/Notes
	private SubjectNode activeSubject;
	private NoteNode activeNote;
	
	
	
	//Constructor
	public DashboardController(DashboardModel theModel) {
		this.theModel = theModel;
		
	}
	
	
	//Setter Functions
	void setView(DashboardView theView) {
		this.theView = theView;
	}
	
	
	//Button Functions - called from DashboardView Class


	
	public void createNewSubject() {
				
		//Checks if TextField is empty - does not allow construction of SubjectWithout Title
		if (theView.getSubjectInputText().getText().equals("")) {
			theView.getSubjectInputText().clear();
			return;
		}
		
		
		//get text from the input field and create a Subject to be stored in the Model - the input field is cleared afterwards
		String subjectTitle = theView.getSubjectInputText().getText();
		
		theModel.addSubject(subjectTitle);
		
		theView.getSubjectInputText().clear();
		
		
		
		// Refresh Left Panel with links to all subjects that have been created
		
		refreshLeftPanel();
		
	}
	
	
	
	public void createNewNote() {
		
		//Constructs NoteNode with data input into the window, then clears the text for the next input
		
		String newNoteTitle = theView.getNoteInputText().getText();
		
		if(newNoteTitle.equals("") || newNoteTitle.trim().length() == 0) {
			Alert alert = new Alert(AlertType.ERROR, "No title was input. Please input a title before entering", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		
		NoteNode newNote = new NoteNode(theView.getNoteInputText().getText());
	
		//If a subject hasn't been selected a Note cannot be created
		//****** change this to include a warning
		if(activeSubject == null) {
			Alert alert = new Alert(AlertType.ERROR, "No Subject selected - Note was not created. Select a subject before entering", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		
		theView.getNoteInputText().clear();
		
		try{
			activeSubject.addLast(newNote);
			refreshCenterPanel();
		}
		catch (Exception e) {
			System.out.println("Issue with adding new note to subject node");
		}
		
	}
	
	
	//Refresh Panel Functions
	
	public void refreshLeftPanel() {
		
		//Creates new VBox for left panel, all elements from the SubjectList will be added as SubjectLinks to this view
		
		theView.createLeftPanel();
		
		if(theModel.getSubjectList().isEmpty()) return;
		//Iterate through the SubjectList and create new SubjectLinks for each and add these to the LeftPanel	
		
		try {
			
			//Get first Subject in the List to create the first link
			SubjectNode newSubject = theModel.getSubjectList().getFirst();
			
			//Loop through all Subjects in theList
			for(int i = 0; i < theModel.getSubjectList().size(); i++) {
				
				//Create new link for the current subject
				SubjectLink newLink = new SubjectLink(this, newSubject);
				
				//Add CSS ID
				newLink.setId("subject-link");
				
				//Add new link to Left Panel
				theView.getLeftPanel().getChildren().add(newLink);
				
				//Iterate to Next Subject
				newSubject = newSubject.getNextSubject();
			}
		}
		
		catch (Exception e) {
			System.out.println("Exception Refreshing the Left Panel " + e);
		}
		
	}
	
	public void refreshCenterPanel() {
		
		//Create new VBox for the CenterPanel
		theView.createCenterPanel();
		
		//Does not refresh the CenterPanel if there is no Active Subject selected
		if(activeSubject == null) return;
		
		Label subjectLabel = new Label(activeSubject.getTitle());
		
		subjectLabel.setId("subject-label");

		//Add a button to delete the selected subject
		Button deleteSubject = new Button("Delete Subject");
		
		//Set CSS Id
		deleteSubject.setId("delete-subject-button");
		
		deleteSubject.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try{ 
					theModel.getSubjectList().remove(activeSubject);
					activeSubject = null;
					activeNote = null;
					refreshLeftPanel();
					refreshCenterPanel();
					refreshRightPanel();
				}
				catch(Exception e) {
					System.out.println("Error removing the Subject");
				}
			}
		});
		
		//Add label and button to VBox
		HBox subjectTitleBox = new HBox(subjectLabel, deleteSubject);
		subjectTitleBox.setId("subject-delete-hbox");
		theView.getCenterPanel().getChildren().add(subjectTitleBox);
		
		
		//If no notes stored in the SubjectNode , only the label is added to the Center pane and we skip the remaining code
		if(activeSubject.isEmpty()) return;
		
		
		//Adds a new note link for each note stored in the subject
		try {
			
			//Get First Note to create link
			NoteNode newNote = activeSubject.getFirst();
			
			//Loop through all Notes Belonging to the Active Subject
			for(int i = 0; i < activeSubject.getNoteCount(); i++) {
				
				NoteLink newLink = new NoteLink(this, newNote);
				
				//Set CSS ID
				newLink.setId("note-link");
				
				//Add Link to Panel
				theView.getCenterPanel().getChildren().add(newLink);
				
				//Move to next note
				newNote = newNote.getNext();
			}
		
		}
		catch (Exception e) {
			System.out.println("Exception creating the note links in the Center Panel");
			
		}
		
		
		
	}
	
	public void refreshRightPanel() {
		
		//creates new Vbox for the right panel to which we will add title/content for the activeNote
		theView.createRightPanel();
		
		//Returns if there is no activeSubject/Note or if the Subject has no notes stored		
		if(activeNote == null || activeSubject == null || activeSubject.isEmpty()) return;
		
		
		//when the NoteLink is clicked, the RightPanel will update with the title/content of the note
		try {
			Label titleLabel = new Label(activeSubject.getTitle() + "\t>>\t" + activeNote.getTitle());
			
			
			
			//Create new TextArea containing the Note's Content
			TextArea contentArea = new TextArea(activeNote.getContent());
			contentArea.setWrapText(true);
			

			
			//Set content Area to default as uneditable
			contentArea.setEditable(false);
			
			Button editButton = new Button("Toggle Edit Mode");
			
			editButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if(!contentArea.isEditable())contentArea.setEditable(true);
					else contentArea.setEditable(false);
				}
			});
			
			Button saveEditButton = new Button("Save Updates to Note");
			
			saveEditButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					
					String noteContent = contentArea.getText();

					activeNote.setContent(noteContent);
					
					System.out.println(activeNote.getContent());
					refreshRightPanel();
					
				}
			});
			
			Button deleteNoteButton = new Button("Delete Note");
			
			deleteNoteButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					try{
						activeSubject.remove(activeNote);
						activeNote = null;
					}
					catch(Exception e) {
						System.out.println("Error Deleting the Note");
					}
					
					refreshCenterPanel();
					refreshRightPanel();
					
				}
			});
			
			//Create HBox for Buttons
			HBox noteButtonBox = new HBox(editButton, saveEditButton, deleteNoteButton);
				
			//Set CSS ID
			contentArea.setId("content-area");
			titleLabel.setId("title-label");
			noteButtonBox.setId("note-button-box");
			
			//Add Elements to Right Panel
			theView.getRightPanel().getChildren().addAll(titleLabel, contentArea, noteButtonBox);			
			
		}
		
		catch (Exception e) {
			System.out.println("There is an exception refreshing the Right Panel");
			
		}
	}
	

	
	//Get active note/subject methods
	
	public SubjectNode getActiveSubject() {
		return activeSubject;
	}
	
	public NoteNode getActiveNote() {
		return activeNote;
	}
	
	//Set active note/subject methods
	
	public void setActiveSubject(SubjectNode activeSubject) {
		this.activeSubject = activeSubject;
	}
	
	public void setActiveNote(NoteNode activeNote) {
		this.activeNote = activeNote;
	}
	
}
