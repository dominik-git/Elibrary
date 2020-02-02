package sk.paz1c.Elibrary.gui;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;
import sk.paz1c.Elibrary.mysql.DaoFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

//responsible for render table of readers
public class ReadersTableAdminController {
	@FXML
	private TableView<Reader> readersTableView;

//create readers list as observable list
	private ObservableList<Reader> readers = FXCollections.observableArrayList();

	@FXML
	private Button createReaderButton;

	@FXML
	private Button searchButton;

	@FXML
	private TextField searchTextField;

	@FXML
	void searchReaders(ActionEvent event) {
		String searchedName = searchTextField.getText();
		System.out.println(searchedName + " search name");
		List<Reader> result = DaoFactory.INSTANCE.getReaderDao().getAllReadersByFullName(searchedName);
		readers = FXCollections.observableArrayList(result);
		readersTableView.setItems(readers);
		System.out.println(result);
	}

	// + button, responsible for open create reader modal
	@FXML
	void createNewReaderAction(ActionEvent event) {
		openCreateReaderModal();
	}

//	public ReadersTableAdminController() {
//		List<Reader> result = DaoFactory.INSTANCE.getReaderDao().getAllReaders();
//		readers = FXCollections.observableArrayList(result);
//	}

	@FXML
	void initialize() {
		List<Reader> result = DaoFactory.INSTANCE.getReaderDao().getAllReaders();
		readers = FXCollections.observableArrayList(result);
		// get all readers
		// make arrayList as observable list
		readersTableView.setItems(readers);
		// create table column
		// name of column
		TableColumn<Reader, String> nameCol = new TableColumn<>("Name");
		// value from object model (Reader.getName())
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		readersTableView.getColumns().add(nameCol);

		TableColumn<Reader, String> surnameCol = new TableColumn<>("Surname");
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		readersTableView.getColumns().add(surnameCol);

		TableColumn<Reader, String> userNameCol = new TableColumn<>("username");
		userNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
		readersTableView.getColumns().add(userNameCol);

		TableColumn<Reader, Date> birthCol = new TableColumn<>("Date Of Birth");
		birthCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		readersTableView.getColumns().add(birthCol);

		TableColumn<Reader, String> genderCol = new TableColumn<>("Gender");
		genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
		readersTableView.getColumns().add(genderCol);
		
		TableColumn<Reader, String> readerIdCol = new TableColumn<>("Reader Id");
		readerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		readersTableView.getColumns().add(readerIdCol);

		// add click listener on row
		readersTableView.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				System.out.println("click");
				// check if we do double click on row
				if (event.getClickCount() == 2) {
					// get index of clicked row
					System.out.println(readersTableView.getSelectionModel().getSelectedIndex());
					int index = readersTableView.getSelectionModel().getSelectedIndex();
					// Fulfil rentedBook object by values from clicked row
					Reader reader = readersTableView.getItems().get(index);
					// if book in clicked row was already returned then dont open modal and go out
					// from func.
					if (reader == null) {
						return;
					}
					openReaderProfileModal(reader);
				}

			}
		});

	}

	// open create Reader modal
	private void openCreateReaderModal() {
		// pass readers list to constructor, when we create new user in
		// CreateReaderController , table of readers will re-render
		CreateReaderController controller = new CreateReaderController(readers);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createNewReaderView.fxml"));
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
		List<Reader> result = DaoFactory.INSTANCE.getReaderDao().getAllReaders();
		readers = FXCollections.observableArrayList(result);
		// get all readers
		// make arrayList as observable list
		readersTableView.setItems(readers);
	}

	private void openReaderProfileModal(Reader reader) {
		// pass readers list to constructor, when we create new user in
		// CreateReaderController , table of readers will re-render
		ReaderProfile controller = new ReaderProfile(reader);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("readerProfile.fxml"));
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
		List<Reader> result = DaoFactory.INSTANCE.getReaderDao().getAllReaders();
		readers = FXCollections.observableArrayList(result);
		// get all readers
		// make arrayList as observable list
		readersTableView.setItems(readers);
	}
}
