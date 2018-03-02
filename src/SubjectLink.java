import javafx.scene.control.Hyperlink;

public class SubjectLink extends Hyperlink{
	SubjectNode theSubject;
	DashboardController theController;
	
	//Constructor - builds link to a SubjectNode that can call methods from the Controller
	
	public SubjectLink(DashboardController theController, SubjectNode theSubject) {
		super(theSubject.getTitle());
		this.theController = theController;
		this.theSubject = theSubject;
		
	}
	
	//When Link is clicked, subject content opens in Center pane
	
	public void arm() {
		theController.setActiveSubject(theSubject);
		theController.refreshCenterPanel();
	}
	
}
