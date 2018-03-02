import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Organizer extends Application{

	 public static void main(String[] args) {
	        launch(args);
	    }
	 
	 public void start(Stage primaryStage) {
		 
		 primaryStage.setTitle("Dashboard");
		 
		 DashboardModel theModel = new DashboardModel();
		 DashboardController theController = new DashboardController(theModel);
	     DashboardView mainDashWin = new DashboardView(theController);
	     
	     DashboardModel.loadFromFile();

	     
	     theController.setView(mainDashWin);
	     
	     Scene scene = new Scene(mainDashWin.getRootPane(), 1000, 800);
	     scene.getStylesheets().add("style.css");
	     primaryStage.setScene(scene);
	     primaryStage.show();
	     
	     theController.refreshLeftPanel();
	     theController.refreshCenterPanel();
	     theController.refreshRightPanel();
	     
	
		 
	 }
	 
	 @Override
	 public void stop() {
		 DashboardModel.saveToFile();
	 }
	 
}
