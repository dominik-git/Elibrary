package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.mysql.DaoFactory;

//responsible for rented books
public class RentedBookAdminController {

	private ObservableList<RentedBook> rentedBooks = FXCollections.observableArrayList();
	private ObservableList<RentedBook> returnedBooks = FXCollections.observableArrayList();

	@FXML
	private Button returnedButton;

	@FXML
	private Button rentedButton;

	@FXML
	void loadReturned(ActionEvent event) {
		List<RentedBook> result = DaoFactory.INSTANCE.getRentedBookDao().getAlReturnedBooksForAdmin();
		returnedBooks = FXCollections.observableArrayList(result);
		returnedButton.setDisable(true);
		rentedButton.setDisable(false);
		rentedBookTableView.setItems(returnedBooks);

	}

	@FXML
	void loadRented(ActionEvent event) {
		List<RentedBook> result = DaoFactory.INSTANCE.getRentedBookDao().getAllRentedBooksForAdmin();
		rentedBooks = FXCollections.observableArrayList(result);
		returnedButton.setDisable(false);
		rentedButton.setDisable(true);
		rentedBookTableView.setItems(rentedBooks);
	}

	@FXML
	private TableView<RentedBook> rentedBookTableView;

	// click on row
	@FXML
	void rowClicked(MouseEvent event) {
		// get index of clicked row
		int index = rentedBookTableView.getSelectionModel().getSelectedIndex();
		// Fulfil rentedBook object by values from clicked row
		RentedBook rentedBook = rentedBookTableView.getItems().get(index);
		// if book in clicked row was already returned then dont open modal and go out
		// from func.
		if (rentedBook.getIsReturned() == true) {
			return;
		}
		if (event.getClickCount() == 2) {
			// create ReturnBookModalController and pass rentedbook to constructor
			ReturnBookModalController controller = new ReturnBookModalController(rentedBook, rentedBooks, "admin");
			// load view
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("returnBookModal.fxml"));
				fxmlLoader.setController(controller);
				Parent parent = fxmlLoader.load();
				Scene scene = new Scene(parent);
				Stage modalStage = new Stage();
				modalStage.setScene(scene);
				modalStage.initModality(Modality.APPLICATION_MODAL);
				modalStage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<RentedBook> result = DaoFactory.INSTANCE.getRentedBookDao().getAllRentedBooksForAdmin();
			rentedBooks = FXCollections.observableArrayList(result);

			// make array list as observable list, that means if something will change you
			// should see changes immediately change without refresh
			
			rentedBookTableView.setItems(rentedBooks);
		}
		// refresh table

	}

	// initialize table
	@FXML
	void initialize() {

		List<RentedBook> result = DaoFactory.INSTANCE.getRentedBookDao().getAllRentedBooksForAdmin();
		rentedBooks = FXCollections.observableArrayList(result);

		// make array list as observable list, that means if something will change you
		// should see changes immediately change without refresh
		
		rentedBookTableView.setItems(rentedBooks);
		rentedButton.setDisable(true);

		TableColumn<RentedBook, String> nameBookCol = new TableColumn<>("Book name");
		nameBookCol.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getBook().getName()));
		rentedBookTableView.getColumns().add(nameBookCol);

		TableColumn<RentedBook, String> catCol = new TableColumn<>("Category");
		catCol.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getBook().getCategory().getName()));

		rentedBookTableView.getColumns().add(catCol);

		TableColumn<RentedBook, String> nameReadCol = new TableColumn<>("Reader name");
		nameReadCol.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getReader().getFullName()));
		rentedBookTableView.getColumns().add(nameReadCol);

		TableColumn<RentedBook, Boolean> isReturnedCol = new TableColumn<>("Returned");
		isReturnedCol.setCellValueFactory(new PropertyValueFactory<>("isReturned"));
		rentedBookTableView.getColumns().add(isReturnedCol);

		TableColumn<RentedBook, Date> dateOfRentCol = new TableColumn<>("Date Of Rent");
		dateOfRentCol.setCellValueFactory(new PropertyValueFactory<>("dateOfRent"));
		rentedBookTableView.getColumns().add(dateOfRentCol);

		TableColumn<RentedBook, Date> deadlineCol = new TableColumn<>("Deadline");
		deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		rentedBookTableView.getColumns().add(deadlineCol);

		TableColumn<RentedBook, Date> dateOfReturnCol = new TableColumn<>("Date Of Return");
		dateOfReturnCol.setCellValueFactory(new PropertyValueFactory<>("dateOfReturn"));
		rentedBookTableView.getColumns().add(dateOfReturnCol);

	}

}
