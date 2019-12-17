package sk.paz1c.Elibrary.gui;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class ReturnBookModalController {

	private RentedBook rentedBook;
	private Stage stage;
	private ObservableList<RentedBook> rentedBooks;
	private String type;

	public ReturnBookModalController(RentedBook rentedBook, ObservableList<RentedBook> rentedBooks, String type) {
		this.rentedBook = rentedBook;
		this.rentedBooks = rentedBooks;
		this.type = type;
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
		this.rentedBook = DaoFactory.INSTANCE.getRentedBookDao().returnBook(rentedBook);
		
		if(type.equals("admin")) {
			List<RentedBook> result = DaoFactory.INSTANCE.getRentedBookDao().getAllRentedBooksForAdmin();
			rentedBooks = FXCollections.observableArrayList(result);
		} else {
			List<RentedBook> result = DaoFactory.INSTANCE.getRentedBookDao().getNonReturnedRentedBookById(rentedBook.getId());
			rentedBooks = FXCollections.observableArrayList(result);
		}
		
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
