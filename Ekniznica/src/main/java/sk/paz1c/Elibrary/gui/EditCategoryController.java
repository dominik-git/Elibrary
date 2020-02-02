package sk.paz1c.Elibrary.gui;

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

public class EditCategoryController {

	@FXML
	private TextField categoryNameTextField;

	@FXML
	private Button addCategoryButton;
	
	@FXML
	private Button deleteButton;


	private Category newCategory = new Category();

	private ObservableList<Category> categories = FXCollections.observableArrayList();

	private Boolean isEdit;

	private Category category;

	public EditCategoryController(Category category) {
		this.isEdit = isEdit;
		this.category = category;
	}

	@FXML
	void onCategoryDelete(ActionEvent event) {
		DaoFactory.INSTANCE.getCategoryDao().deleteCategoryById(category.getId());
		deleteButton.getScene().getWindow().hide();
	}

	@FXML
	void onClickAddCategory(ActionEvent event) {
		if (categoryNameTextField.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("You have to fill all inputs!");
			alert.showAndWait();
		} else {

			category.setName(categoryNameTextField.getText());
			DaoFactory.INSTANCE.getCategoryDao().updateCategory(category);
			addCategoryButton.getScene().getWindow().hide();
			System.out.println("edit");
		}

	}

	@FXML
	void initialize() {
		System.out.println(category.getName() + " " + category.getId());
		categoryNameTextField.setText(category.getName());

	}
}
