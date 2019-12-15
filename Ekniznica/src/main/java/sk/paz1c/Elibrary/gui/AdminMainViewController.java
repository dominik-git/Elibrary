package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.util.ArrayList;
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
	private Button readerViewButton;
	
	@FXML
    private Button booksViewButton;
	
	private List<Button> buttons = new ArrayList<Button>();

	@FXML
	void switchScene(ActionEvent event) {
		
		Button btn = (Button) event.getSource();
		
		String id = btn.getId();
		System.out.println(id);
		
		if(id.equals("readerViewButton")) {
			ReadersTableAdminController controller = new ReadersTableAdminController();
			showView(controller,"readersAdminView.fxml");
			
		}else if(id.equals("vypozickyButton")) {
			RentedBookAdminController controller = new RentedBookAdminController();
			showView(controller,"rentedBookView.fxml");
		}
		else if(id.equals("booksViewButton")) {
			BooksAdminController controller = new BooksAdminController();
			showView(controller,"booksAdminView.fxml");
		}
		disableButton(btn);
	}

	private void showView(Object controller, String view) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
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
	private void disableButton(Button button) {
		
	for (Button btn : buttons) {
		btn.setDisable(false);
	}
	button.setDisable(true);
	}

	@FXML
	void initialize() {
		buttons.add(readerViewButton);
		buttons.add(vypozickyButton);
	    buttons.add(booksViewButton);

	}

}
