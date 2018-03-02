import javafx.scene.control.Hyperlink;

public class NoteLink extends Hyperlink {
	NoteNode theNote;
	DashboardController theController;
	
	//Constructor - builds link to a NoteNode that can call methods from the Controller
	
	public NoteLink(DashboardController theController, NoteNode theNote) {
		super(theNote.getTitle());
		this.theNote = theNote;
		this.theController = theController;
	}
	
	//When Link is clicked - opens note content in Right Panel

	public void arm() {
		theController.setActiveNote(theNote);
		theController.refreshRightPanel();		
	}
	
}
