package sk.paz1c.Elibrary.gui;

import javafx.fxml.FXML;

import java.sql.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class ReaderProfile {
	
	public ReaderProfile(Reader reader) {
		this.reader = reader;
		
		List<RentedBook> result = DaoFactory.INSTANCE.getRentedBookDao().getNonReturnedRentedBookById(reader.getId());
		rentedBooks = FXCollections.observableArrayList(result);
	}
	private Reader reader;
	private ObservableList<RentedBook> rentedBooks = FXCollections.observableArrayList();
	
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
	void initialize() {
		fullNameLabel.setText(reader.getName()+" "+reader.getSurname());
		dateOfBirthLabel.setText(reader.getBirthDate().toString());
		userNameLabel.setText(reader.getUsername());
		
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
		
		

	}

}
