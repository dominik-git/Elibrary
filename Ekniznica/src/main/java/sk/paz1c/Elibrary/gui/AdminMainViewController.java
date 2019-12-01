package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class AdminMainViewController {

	@FXML
	private AnchorPane mainContentPane;

	@FXML
	private Button vypozickyButton;

	@FXML
	void switchScene(ActionEvent event) {
		System.out.println("klik");
		RentedBookAdminController controller = new RentedBookAdminController();
		FXMLLoader fxmlLoader = 
				new FXMLLoader(getClass().getResource("rentedBookView.fxml"));
		fxmlLoader.setController(controller);
		Parent parent;
		try {
			parent = fxmlLoader.load();
			mainContentPane.getChildren().clear();
			mainContentPane.getChildren().add(parent);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
		

	@FXML
	void initialize() {
//		System.out.println("init2");
//		Button button = new Button("text");
//		button.setMinWidth(100);
//		button.setMinHeight(100);
		
		

	}

}
