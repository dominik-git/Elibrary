package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class RentedBookAdminController {

	@FXML
	private TableView<RentedBook> rentedBookTableView;

	@FXML
	void rowClicked(MouseEvent event) {
		
		int index = rentedBookTableView.getSelectionModel().getSelectedIndex();
		RentedBook rentedBook = rentedBookTableView.getItems().get(index);
		if(rentedBook.getIsReturned() == true) {
			return;
		}
		ReturnBookModalController controller = new ReturnBookModalController(rentedBook);
		try {
			FXMLLoader fxmlLoader = 
					new FXMLLoader(getClass().getResource("returnBookModal.fxml"));
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
		rentedBookTableView.refresh();
	}

	@FXML
	void initialize() {

		List<RentedBook> result = new ArrayList<RentedBook>();

		result = DaoFactory.INSTANCE.getRentedBookDao().getAllRenteBooksForAdmin();

		for (RentedBook book : result) {
			System.out.println(book.getIsReturned());
		}
		rentedBookTableView.setItems(FXCollections.observableArrayList(result));

		TableColumn<RentedBook, String> nameBookCol = new TableColumn<>("Name");
		nameBookCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rentedBookTableView.getColumns().add(nameBookCol);

		TableColumn<RentedBook, String> catCol = new TableColumn<>("Category");
		catCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		rentedBookTableView.getColumns().add(catCol);

		TableColumn<RentedBook, String> nameReadCol = new TableColumn<>("Name");
		nameReadCol.setCellValueFactory(new PropertyValueFactory<>("readerFullName"));
		rentedBookTableView.getColumns().add(nameReadCol);

		TableColumn<RentedBook, Boolean> isReturnedCol = new TableColumn<>("Returned");
		isReturnedCol.setCellValueFactory(new PropertyValueFactory<>("isReturned"));
		rentedBookTableView.getColumns().add(isReturnedCol);

		TableColumn<RentedBook, Date> dateOfRentCol = new TableColumn<>("Date Of Rent");
		dateOfRentCol.setCellValueFactory(new PropertyValueFactory<>("dateOfRent"));
		rentedBookTableView.getColumns().add(dateOfRentCol);

		TableColumn<RentedBook, Date> deadlineCol = new TableColumn<>("Deadline");
		deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		rentedBookTableView.getColumns().add(deadlineCol);

		TableColumn<RentedBook, Date> dateOfReturnCol = new TableColumn<>("Date Of Return");
		dateOfReturnCol.setCellValueFactory(new PropertyValueFactory<>("dateOfReturn"));
		rentedBookTableView.getColumns().add(dateOfReturnCol);

	}

}
