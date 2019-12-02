package sk.paz1c.Elibrary.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class ReturnBookModalController {

	private RentedBook rentedBook;
	private Stage stage;

	public ReturnBookModalController(RentedBook rentedBook) {
		this.rentedBook = rentedBook;
	}

	@FXML
	private Button yesButton;

	@FXML
	private Button noButton;

	@FXML
	private Label nameOfBookLabel;

	@FXML
	void goBack(ActionEvent event) {

		noButton.getScene().getWindow().hide();
	}

	@FXML
	void returnBook(ActionEvent event) {
		rentedBook = DaoFactory.INSTANCE.getRentedBookDao().returnBook(rentedBook);
		yesButton.getScene().getWindow().hide();
	}

	@FXML
	void initialize() {

		nameOfBookLabel.setText(rentedBook.getName());
	}

	public RentedBook getRentedBook() {
		return rentedBook;
	}
	

}
