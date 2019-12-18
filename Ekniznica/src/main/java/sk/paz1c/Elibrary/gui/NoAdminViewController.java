package sk.paz1c.Elibrary.gui;

import java.sql.Date;
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
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.modelFx.BookFxModel;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class NoAdminViewController {

	public NoAdminViewController(String name) {
		readerName = name;
	}

	@FXML
	private TableView<RentedBook> rentedBookTable;
    private String readerName;
	@FXML
	void initialize() {
		List<RentedBook> result = new ArrayList<RentedBook>();
        Reader reader = DaoFactory.INSTANCE.getReaderDao().getReaderByName(readerName);
		result = DaoFactory.INSTANCE.getRentedBookDao().getRentedBookById(reader.getId());
        
		
		rentedBookTable.setItems(FXCollections.observableArrayList(result));

		TableColumn<RentedBook, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rentedBookTable.getColumns().add(nameCol);


		TableColumn<RentedBook, String> percentageCol = new TableColumn<>("Category");
		percentageCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		rentedBookTable.getColumns().add(percentageCol);
		
		TableColumn<RentedBook, Date> dateOfRentCol = new TableColumn<>("Date Of Rent");
		dateOfRentCol.setCellValueFactory(new PropertyValueFactory<>("dateOfRent"));
		rentedBookTable.getColumns().add(dateOfRentCol);

		TableColumn<RentedBook, Date> deadlineCol = new TableColumn<>("Deadline");
		deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		rentedBookTable.getColumns().add(deadlineCol);
	}
}
