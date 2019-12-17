package sk.paz1c.Elibrary.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class ReaderProfile {
	
	private Reader reader;
	private ObservableList<RentedBook> rentedBooks;
	
	public ReaderProfile(Reader reader) {
		this.reader = reader;
	}
	
	
	@FXML
	private Label fullNameLabel;

	
	@FXML
	private TableView<RentedBook> rentedBookTable;

	
	@FXML
	private Label dateOfBirthLabel;

	
	@FXML
	private Label userNameLabel;

	
	@FXML
	private Button addBookButton;

	
	@FXML
	void openAddBookModal(ActionEvent event) {
		
	}
	@FXML
	void rowClicked(MouseEvent event) {
	
	}

	
	@FXML
	void initialize() {
		fullNameLabel.setText(reader.getName()+" "+reader.getSurname());
		dateOfBirthLabel.setText(reader.getBirthDate().toString());
		userNameLabel.setText(reader.getUsername());
		
		List<RentedBook> result = DaoFactory.INSTANCE.getRentedBookDao().getNonReturnedRentedBookById(reader.getId());
		rentedBooks = FXCollections.observableArrayList(result);
		rentedBookTable.setItems(rentedBooks);

		TableColumn<RentedBook, String> nameBookCol = new TableColumn<>("Name");
		nameBookCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rentedBookTable.getColumns().add(nameBookCol);

		TableColumn<RentedBook, String> catCol = new TableColumn<>("Category");
		catCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		rentedBookTable.getColumns().add(catCol);

		TableColumn<RentedBook, String> nameReadCol = new TableColumn<>("Name");
		nameReadCol.setCellValueFactory(new PropertyValueFactory<>("readerFullName"));
		rentedBookTable.getColumns().add(nameReadCol);

		TableColumn<RentedBook, Boolean> isReturnedCol = new TableColumn<>("Returned");
		isReturnedCol.setCellValueFactory(new PropertyValueFactory<>("isReturned"));
		rentedBookTable.getColumns().add(isReturnedCol);

		TableColumn<RentedBook, Date> dateOfRentCol = new TableColumn<>("Date Of Rent");
		dateOfRentCol.setCellValueFactory(new PropertyValueFactory<>("dateOfRent"));
		rentedBookTable.getColumns().add(dateOfRentCol);

		TableColumn<RentedBook, Date> deadlineCol = new TableColumn<>("Deadline");
		deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		rentedBookTable.getColumns().add(deadlineCol);

		TableColumn<RentedBook, Date> dateOfReturnCol = new TableColumn<>("Date Of Return");
		dateOfReturnCol.setCellValueFactory(new PropertyValueFactory<>("dateOfReturn"));
		rentedBookTable.getColumns().add(dateOfReturnCol);
		
		// add click listener on row
		rentedBookTable.setOnMouseClicked((MouseEvent event) -> {
					if (event.getButton().equals(MouseButton.PRIMARY)) {
						System.out.println("click");
						// check if we do double click on row
						if (event.getClickCount() == 2) {
							// get index of clicked row
							int index = rentedBookTable.getSelectionModel().getSelectedIndex();
							// Fulfil rentedBook object by values from clicked row
							RentedBook rentedBook = rentedBookTable.getItems().get(index);
							// if book in clicked row was already returned then dont open modal and go out
							// from func.
							if (rentedBook.getIsReturned() == true) {
								return;
							}
							// create ReturnBookModalController and pass rentedbook to constructor
							ReturnBookModalController controller = new ReturnBookModalController(rentedBook,rentedBooks,"reader");
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
							rentedBookTable.refresh();
						}

					}
				});
		

	}

}
