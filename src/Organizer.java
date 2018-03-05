import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Organizer extends Application{

	 public static void main(String[] args) {
	        launch(args);
	    }
	 
	 public void start(Stage primaryStage) {
		 
		 
		 primaryStage.setTitle("Dashboard");
		 
		 // Instantiate Model/View/Controller
		 DashboardModel theModel = new DashboardModel();
		 DashboardController theController = new DashboardController(theModel);
	     DashboardView mainDashWin = new DashboardView(theController);
	     
	     // Load previously saved data to populate lists
	     DashboardModel.loadFromFile();

	     // Allow Controller access to the View
	     theController.setView(mainDashWin);
	     
	     // Open main window
	     
	     Scene scene = new Scene(mainDashWin.getRootPane(), 1000, 800);
	     scene.getStylesheets().add("style.css");
	     primaryStage.setScene(scene);
	     primaryStage.show();
	     
	     // Refresh to reflect any saved data read in
	     
	     theController.refreshLeftPanel();
	     theController.refreshCenterPanel();
	     theController.refreshRightPanel();
	     
	
		 
	 }
	 
	 
	 // when the Application is closed, all ArrayList data for SubjectNode / NoteNode is output to a text file 
	 @Override
	 public void stop() {
		 DashboardModel.saveToFile();
	 }
	 
}
