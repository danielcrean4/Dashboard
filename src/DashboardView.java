import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardView {
	
	//Component instance variables
	private BorderPane rootPane;
	private VBox leftPanel;
	private VBox centerPanel;
	private VBox rightPanel;
	private HBox topPanel;
	private TextField subjectInputText;
	private TextField noteInputText;
	private DashboardController theController;
	
	
	public DashboardView(DashboardController theController) {
		
		rootPane = new BorderPane();
		
		this.theController = theController;
		
		createLeftPanel();
		createCenterPanel();
		createRightPanel();

	}
	
	
	
	// Methods to Instantiate left/right/top/center panels and add to BorderPane

	
	
	
	public void createLeftPanel() {
		leftPanel = new VBox();
		
		HBox subjInputBox = new HBox();
		subjectInputText = new TextField();
		Button addSubjectButton = new Button("Add New Subject");
		
		
		//Creates New Subject with Title as input in subjectInputText when ENTER is input
		
		subjectInputText.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        @Override
	        public void handle(KeyEvent ke){
	            if (ke.getCode().equals(KeyCode.ENTER)) {
	                theController.createNewSubject();
	            }
	        }
	    });
		
		//Creates New Subject with Title as input in subjectInputText when addSubjectButton is clicked
		
		addSubjectButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				theController.createNewSubject();
			}
		});
		
		
		//Add Input Text / Button to HBox
		subjInputBox.getChildren().add(subjectInputText);
		subjInputBox.getChildren().add(addSubjectButton);
		
		
		//Add Elements to Left Panel
		leftPanel.getChildren().add(subjInputBox);

		
		
		//Add CSS Ids
		leftPanel.setId("left-panel");	
		subjectInputText.setId("subject-input-text");
		addSubjectButton.setId("add-subject-button");
		subjInputBox.setId("subject-input-hbox");
		
		
		rootPane.setLeft(leftPanel);
		
	}
	
	public void createCenterPanel() {
		
		centerPanel = new VBox();
				
		//Create a button and text field to create new notes
		
		noteInputText = new TextField();
	
		//Add listeners to Button to create a new note with text input in the field
		Button createNoteButton = new Button("Create New Note");
		createNoteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				theController.createNewNote();
			}
		});
		
		createNoteButton.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        @Override
	        public void handle(KeyEvent ke){
	            if (ke.getCode().equals(KeyCode.ENTER)) {
	                theController.createNewNote();
	            }
	        }
	    });
		
		//Add these fields to an HBox
		HBox noteInputBox = new HBox(noteInputText, createNoteButton);
		
		//Add the button to the centerPanel, and add this to the BorderPane
		
		centerPanel.getChildren().add(noteInputBox);
		
		//Set CSS Ids
		noteInputBox.setId("note-hbox");
		centerPanel.setId("center-panel");
		noteInputText.setId("note-input-text");
		createNoteButton.setId("create-note-button");
		
		
		rootPane.setCenter(centerPanel);
		
	}

	public void createRightPanel() {
		rightPanel = new VBox();		
		
		
		//Set CSS IDs
		rightPanel.setId("right-panel");
		
		
		//Add Right Panel to BorderPane
		rootPane.setRight(rightPanel);
		
		
	}

	
	//Access methods for Panels
	
	public BorderPane getRootPane() {
		return rootPane;
	}

	public VBox getLeftPanel() {
		return leftPanel;
	}
	
	public VBox getCenterPanel() {
		return centerPanel;
	}
	
	public VBox getRightPanel() {
		return rightPanel;
	}
	
	public TextField getSubjectInputText() {
		return subjectInputText;
	}
	
	public TextField getNoteInputText() {
		return noteInputText;
	}
	
	
	//Setter methods for Panels

	
	public void setLeftPanel(VBox leftPanel) {
		this.leftPanel = leftPanel;
	}
	
	public void setCenterPanel(VBox centerPanel) {
		this.centerPanel = centerPanel;
	}
	
	public void setRightPanel(VBox rightPanel) {
		this.rightPanel = rightPanel;
	}
	

}
