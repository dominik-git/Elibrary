package sk.paz1c.Elibrary.gui;

import java.util.ArrayList;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.modelFx.BookFxModel;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class NoAdminViewController {

	@FXML
	private TableView<Book> rentedBookTable;

	@FXML
	void initialize() {
		List<Book> result = new ArrayList<Book>();

		result = DaoFactory.INSTANCE.getRentedBookDao().getRentedBookById(1);

		for (Book book : result) {
			System.out.println(book.getName());
		}
		rentedBookTable.setItems(FXCollections.observableArrayList(result));

		TableColumn<Book, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rentedBookTable.getColumns().add(nameCol);

		TableColumn<Book, Integer> countCol = new TableColumn<>("Author");
		countCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		rentedBookTable.getColumns().add(countCol);

		TableColumn<Book, String> percentageCol = new TableColumn<>("Category");
		percentageCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		rentedBookTable.getColumns().add(percentageCol);
	}
}
