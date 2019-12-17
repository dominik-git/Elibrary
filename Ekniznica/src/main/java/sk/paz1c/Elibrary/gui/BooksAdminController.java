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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class BooksAdminController {
	
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
    private TextField searchTextField;

    @FXML
    private Button searchButton;
    
    
    private ObservableList<Book> books = FXCollections.observableArrayList();
    
    @FXML
    void searchBook(ActionEvent event) {
    	String searchedName = searchTextField.getText();
		System.out.println(searchedName + " search name");
		List<Book> result = DaoFactory.INSTANCE.getBookDao().getAllBooksByName(searchedName);
		books = FXCollections.observableArrayList(result);
		booksListView.setItems(books);
		System.out.println(result);
    }
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