package sk.paz1c.Elibrary.gui;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class AddCategoryController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField categoryNameTextField;

	@FXML
	private Button addCategoryButton;

	private Category newCategory = new Category();
	private ObservableList<Category> categories = FXCollections.observableArrayList();

	@FXML
	void onClickAddCategory(ActionEvent event) {		
		if(categoryNameTextField.getText().isEmpty()) {	
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("You have to fill all inputs!");
			alert.showAndWait();
		}else {
			newCategory.setName(categoryNameTextField.getText());
			Category category = DaoFactory.INSTANCE.getCategoryDao().addCategory(newCategory);
			categories.add(category);
			addCategoryButton.getScene().getWindow().hide();
		}
	}

	@FXML
	void initialize() {
		List<Category> result = DaoFactory.INSTANCE.getCategoryDao().getAllCategories();
		categories = FXCollections.observableArrayList(result);
	}
}