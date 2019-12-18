package sk.paz1c.Elibrary.gui;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.modelFx.ReaderFx;
import sk.paz1c.Elibrary.mysql.DaoFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class AddBookController {
	public AddBookController() {
	
	}
	private ObservableList<Category> categories = FXCollections.observableArrayList();
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField autorTextField;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private DatePicker bookDatePicker;

	@FXML
	private Button addBookButton;

	@FXML
	private ChoiceBox<Category> categoryChoiceBox;
	private Book newBook = new Book();

	@FXML
	void onClickAddBook(ActionEvent event) {	
		if(nameTextField.getText().isEmpty()|| autorTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()|| newBook.getCategory() == null	) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("You have to fill all inputs!");
			alert.showAndWait();
    		
		}else {
			newBook.setName(nameTextField.getText());
			newBook.setAuthor(autorTextField.getText());
			newBook.setDescription(descriptionTextArea.getText());
			Date date = java.sql.Date.valueOf(bookDatePicker.getValue());
			newBook.setYearOfPublication(date);
			Book savedBook = DaoFactory.INSTANCE.getBookDao().addBook(newBook);
			addBookButton.getScene().getWindow().hide();
		}

		
	}

	@FXML
	void initialize() {
		List<Category> result = DaoFactory.INSTANCE.getCategoryDao().getAllCategories();
		categories = FXCollections.observableArrayList(result);
		categoryChoiceBox.setItems(categories);

		categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
			@Override
			public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
				String id = String.valueOf(newValue.getId());
				newBook.setCategory(id);
			}

		});

	}
}