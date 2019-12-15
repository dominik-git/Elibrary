package sk.paz1c.Elibrary.gui;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.modelFx.ReaderFx;
import sk.paz1c.Elibrary.mysql.DaoFactory;
import javafx.scene.control.*;

//responsible for creating new reader
public class CreateReaderController extends ReadersTableAdminController {

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField surnameTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField emailTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Button saveButton;

	@FXML
	private RadioButton maleRadio;

	@FXML
	private RadioButton femaleRadio;

	@FXML
	private DatePicker datePicker;
	
	private ObservableList<Reader> readersList;
	
	// create new reader
	@FXML
	void saveReader(ActionEvent event) {
		Reader reader = new Reader();
		// put values from ReaderFx model to Reader model
		reader.mapFxReaderToReader(readerFxModel);
		// call save reader and DaoFactory.INSTANCE.getReaderDao().saveReader returns saved reader
		Reader savedReader = DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
		readersList.add(savedReader);
		// callback.methodToCallBack(savedReader);
		// hide current window
		saveButton.getScene().getWindow().hide();
		
	}

	private ReaderFx readerFxModel;

	// constructor , create empty ReaderFx model
	public CreateReaderController(ObservableList<Reader>  readersList) {
		readerFxModel = new ReaderFx();
		this.readersList = readersList;
	}

	@FXML
	void initialize() {

		// create group for radio buttons
		ToggleGroup tg = new ToggleGroup();
		// set group for radio button
		maleRadio.setToggleGroup(tg);
		// set value for radio button
		maleRadio.setUserData("M");
		// set group for radio button
		femaleRadio.setToggleGroup(tg);
		femaleRadio.setUserData("F");

		// add listener for radio buttons
		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// set gender when clicks on radio button
				if (tg.getSelectedToggle() != null) {
					String gender = tg.getSelectedToggle().getUserData().toString();
					readerFxModel.setGender(gender);
				}
			}
		});

		// two way binding, so basically it means that you are writing something to
		// field and you are fulfilling appropriate variable ...
		// or if you have some value in variable you will see that value in field
		nameTextField.textProperty().bindBidirectional(readerFxModel.nameProperty());
		surnameTextField.textProperty().bindBidirectional(readerFxModel.surnameProperty());
		usernameTextField.textProperty().bindBidirectional(readerFxModel.usernameProperty());
		emailTextField.textProperty().bindBidirectional(readerFxModel.emailProperty());
		passwordTextField.textProperty().bindBidirectional(readerFxModel.passwordProperty());
		datePicker.valueProperty().bindBidirectional(readerFxModel.dateProperty());

	}

}
