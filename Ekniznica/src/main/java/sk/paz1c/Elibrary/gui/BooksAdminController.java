package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.mysql.DaoFactory;
import javafx.scene.input.MouseEvent;

public class BooksAdminController {
	private ObservableList<Book> books = FXCollections.observableArrayList();

	@FXML
	private TableView<Book> booksTableView;

	@FXML
	private Button addCategoryButton;

	@FXML
	private Button addBookButton;

	@FXML
	private Button searchButton;

	@FXML
	private TextField searchTextField;

	@FXML
	void onClickAddBookButton(ActionEvent event) {
		openBookModal();
	}

	@FXML
	void searchBook(ActionEvent event) {
		String searchedName = searchTextField.getText();
		System.out.println(searchedName + " search name");
		List<Book> result = DaoFactory.INSTANCE.getBookDao().getAllBooksByName(searchedName);
		books = FXCollections.observableArrayList(result);
		booksTableView.setItems(books);
	}

	@FXML
	void onClickAddCategory(ActionEvent event) {
		openCategoryModal();
	}

	//
	@FXML
	void rowClicked(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// check if we do double click on row
			if (event.getClickCount() == 2) {
				// get index of clicked row

				int index = booksTableView.getSelectionModel().getSelectedIndex();
				// Fulfil rentedBook object by values from clicked row
				Book book = books.get(index);
				// if book in clicked row was already returned then dont open modal and go out
				// from func.
				if (book == null) {
					return;
				}
				openRentBookModal(book);
			}

		}
	}

	@FXML
	void initialize() {
		List<Book> result = DaoFactory.INSTANCE.getBookDao().getAllBooks();
		books = FXCollections.observableArrayList(result);
		booksTableView.setItems(books);
		TableColumn<Book, String> nameBookCol = new TableColumn<>("Name");
		nameBookCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		booksTableView.getColumns().add(nameBookCol);

		TableColumn<Book, String> catCol = new TableColumn<>("Category");
		catCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
		booksTableView.getColumns().add(catCol);

		TableColumn<Book, String> nameReadCol = new TableColumn<>("Author");
		nameReadCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		booksTableView.getColumns().add(nameReadCol);

		TableColumn<Book, Date> isReturnedCol = new TableColumn<>("Date of publication");
		isReturnedCol.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));
		booksTableView.getColumns().add(isReturnedCol);

	}

	private void openRentBookModal(Book book) {

		RentBookController controller = new RentBookController(book);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rentBookView.fxml"));
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
		List<Book> result = DaoFactory.INSTANCE.getBookDao().getAllBooks();
		books = FXCollections.observableArrayList(result);
		booksTableView.setItems(books);
	}

	private void openBookModal() {

		AddBookController controller = new AddBookController();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addBookView.fxml"));
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
		List<Book> result = DaoFactory.INSTANCE.getBookDao().getAllBooks();
		books = FXCollections.observableArrayList(result);
		booksTableView.setItems(books);
	}

	private void openCategoryModal() {
		// pass readers list to constructor, when we create new user in
		// CreateReaderController , table of readers will re-render
		AddCategoryController controller = new AddCategoryController();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createCategoryView.fxml"));
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
	}
}