package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class BooksAdminController {
	private ObservableList<Book> books = FXCollections.observableArrayList();
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ListView<Book> booksListView;

	@FXML
	private Button addCategoryButton;

	@FXML
	private Button addBookButton;

	@FXML
    void onClickAddBookButton(ActionEvent event) {
		openBookModal();
    }
	@FXML
	void onClickAddCategory(ActionEvent event) {
		openCategoryModal();
    }
	 
	@FXML
	void initialize() {
		List<Book> result = DaoFactory.INSTANCE.getBookDao().getAllBooks();
		books = FXCollections.observableArrayList(result);
		booksListView.setItems(books);
	}
	
	private void openBookModal() {
		// pass readers list to constructor, when we create new user in
		// CreateReaderController , table of readers will re-render
		AddBookController controller = new AddBookController(books);
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