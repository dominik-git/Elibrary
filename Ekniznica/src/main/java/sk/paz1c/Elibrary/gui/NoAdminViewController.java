package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.modelFx.BookFxModel;
import sk.paz1c.Elibrary.mysql.DaoFactory;
import javafx.event.ActionEvent;

public class NoAdminViewController {

	public NoAdminViewController(String name, Long id) {
		readerName = name;
		this.id = id;
	}

	@FXML
	private TableView<RentedBook> rentedBookTable;
	private String readerName;
	private Long id;
	@FXML
	private Button openPasswordModal;

	@FXML
	void onButtonClick(ActionEvent event) {
		ChangePasswordController controller = new ChangePasswordController(id);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("changePasswordWindow.fxml"));
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

	@FXML
	void initialize() {
		List<RentedBook> result = new ArrayList<RentedBook>();
		Reader reader = DaoFactory.INSTANCE.getReaderDao().getReaderByName(readerName);
		result = DaoFactory.INSTANCE.getRentedBookDao().getRentedBookById(reader.getId());

		System.out.println(result);
		rentedBookTable.setItems(FXCollections.observableArrayList(result));

		TableColumn<RentedBook, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBook().getName()));
		rentedBookTable.getColumns().add(nameCol);

		TableColumn<RentedBook, String> catCol = new TableColumn<>("Category");
		catCol.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getBook().getCategory().getName()));

		rentedBookTable.getColumns().add(catCol);

		TableColumn<RentedBook, Date> dateOfRentCol = new TableColumn<>("Date Of Rent");
		dateOfRentCol.setCellValueFactory(new PropertyValueFactory<>("dateOfRent"));
		rentedBookTable.getColumns().add(dateOfRentCol);

		TableColumn<RentedBook, Date> deadlineCol = new TableColumn<>("Deadline");
		deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		rentedBookTable.getColumns().add(deadlineCol);
	}
}
