package sk.paz1c.Elibrary.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.mysql.DaoFactory;
import sk.paz1c.Elibrary.mysql.MysqlBookDao;

public class App extends Application{
	public static Stage window;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		LoginController controller = new LoginController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginView.fxml"));
		fxmlLoader.setController(controller);
		Parent parent = fxmlLoader.load();
		
		Scene scene = new Scene(parent);

		
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		
		launch(args);
	}

}
