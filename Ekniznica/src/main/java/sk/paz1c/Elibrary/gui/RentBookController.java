package sk.paz1c.Elibrary.gui;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class RentBookController {
	
	private List<Category> categories; 

	@FXML
	private TextField nameBookField;

	@FXML
	private TextField authorField;

	@FXML
	private ChoiceBox<Category> categoryChoiceBox;

	@FXML
	private TextArea desctiptionTF;

	@FXML
	private Button rentButton;

	@FXML
	private TextField rentBookField;

	@FXML
	private Button deleteBookButton;

	@FXML
	private Button editBookButton;

	@FXML
	private Button saveBookButton;

	@FXML
	private DatePicker bookDatePicker;

	@FXML
	void saveBook(ActionEvent event) {
		Book updateBook = new Book();
		updateBook.setName(nameBookField.getText());
		updateBook.setCategory(book.getCategory());
		updateBook.setDescription(desctiptionTF.getText());
		updateBook.setAuthor(authorField.getText());
		Date date = java.sql.Date.valueOf(bookDatePicker.getValue());
		updateBook.setYearOfPublication(date);
		updateBook.setId(book.getId());
		DaoFactory.INSTANCE.getBookDao().updateBook(updateBook);
		deleteBookButton.getScene().getWindow().hide();
	}

	@FXML
	void editView(ActionEvent event) {
		editBook();
	}

	@FXML
	void deleteBook(ActionEvent event) {
		DaoFactory.INSTANCE.getBookDao().deleteBookById(book.getId());
		deleteBookButton.getScene().getWindow().hide();
	}

	private Book book;

	private boolean isEditable = false;

	public RentBookController(Book book) {
		this.book = new Book();
		this.book.setName(book.getName());
		this.book.setCategory(book.getCategory());
		this.book.setDescription(book.getDescription());
		this.book.setAuthor(book.getAuthor());
		this.book.setYearOfPublication(book.getYearOfPublication());
		this.book.setId(book.getId());
	}

	@FXML
	void rentBook(ActionEvent event) {
		DaoFactory.INSTANCE.getRentedBookDao().addRentedBook(Long.parseLong(rentBookField.getText()), book.getId());
		rentButton.getScene().getWindow().hide();
	}

	@FXML
	void initialize() {
		editBook();
		nameBookField.setText(book.getName());
		authorField.setText(book.getAuthor());
		// categoryLabel.setText(book.getCategory());
		desctiptionTF.setText(book.getDescription());
		desctiptionTF.setWrapText(true);

		LocalDate localDate = new java.sql.Date(book.getYearOfPublication().getTime()).toLocalDate();

		bookDatePicker.setValue(localDate);

		categories = DaoFactory.INSTANCE.getCategoryDao().getAllCategories();
		ObservableList<Category> result = FXCollections.observableArrayList(categories);
		categoryChoiceBox.setItems(result);
		int index = findIndex();
		categoryChoiceBox.getSelectionModel().select(index);

		

		categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
			@Override
			public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
				System.out.println(newValue.getId());
				book.setCategory(newValue);
			}

		});

	}

	private void editBook() {
		if (isEditable) {
			authorField.setDisable(false);
			nameBookField.setDisable(false);
			bookDatePicker.setDisable(false);
			desctiptionTF.setEditable(true);
			categoryChoiceBox.setDisable(false);
			saveBookButton.setDisable(false);
			deleteBookButton.setDisable(false);
			rentButton.setDisable(true);
			rentBookField.setDisable(true);

		} else {
			authorField.setDisable(true);
			nameBookField.setDisable(true);
			bookDatePicker.setDisable(true);
			desctiptionTF.setEditable(false);
			categoryChoiceBox.setDisable(true);
			saveBookButton.setDisable(true);
			deleteBookButton.setDisable(true);
			rentBookField.setDisable(false);
		}
		isEditable = !isEditable;

	}
	private int findIndex() {
		Long id = book.getCategory().getId();
		int possition = 0;
		
		for (int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getId() == id) {
				possition =i;
			}
		}
		return possition;
	}
}
