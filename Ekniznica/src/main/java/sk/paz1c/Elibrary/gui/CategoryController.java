package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;
import sk.paz1c.Elibrary.mysql.DaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

public class CategoryController {
	
	private ObservableList<Category> categories = FXCollections.observableArrayList();
	
	@FXML
    private TableView<Category> categoryTable;

    @FXML
    private Button addCategory;

    @FXML
    void onAddCategory(ActionEvent event) {
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
		List<Category> result = DaoFactory.INSTANCE.getCategoryDao().getAllCategories();
		categories = FXCollections.observableArrayList(result);
		categoryTable.setItems(categories);
    }
    @FXML
    void rowClicked(MouseEvent event) {
    	if (event.getButton().equals(MouseButton.PRIMARY)) {
			// check if we do double click on row
			if (event.getClickCount() == 2) {
				// get index of clicked row

				int index = categoryTable.getSelectionModel().getSelectedIndex();
				// Fulfil rentedBook object by values from clicked row
				Category category = categories.get(index);
				// if book in clicked row was already returned then dont open modal and go out
				// from func.
				if (category == null) {
					return;
				}
				
				EditCategoryController controller = new EditCategoryController(category);
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
				List<Category> result = DaoFactory.INSTANCE.getCategoryDao().getAllCategories();
				categories = FXCollections.observableArrayList(result);
				categoryTable.setItems(categories);
				System.out.println("callback");
				
			}

		}
    }
    
    @FXML
    void initialize() {
    	List<Category> result = DaoFactory.INSTANCE.getCategoryDao().getAllCategories();
		categories = FXCollections.observableArrayList(result);
		categoryTable.setItems(categories);
		
		TableColumn<Category, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		categoryTable.getColumns().add(nameCol);
		
		TableColumn<Category, String> idCol = new TableColumn<>("Category id");
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		categoryTable.getColumns().add(idCol);

    }
    


}
