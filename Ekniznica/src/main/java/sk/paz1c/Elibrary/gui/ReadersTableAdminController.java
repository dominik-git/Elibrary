package sk.paz1c.Elibrary.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.paz1c.Elibrary.model.Reader;

public class ReadersTableAdminController {
	@FXML
	private TableView<Reader> readersTableView;

	@FXML
	private Button createReaderButton;
	
	  @FXML
	    void createNewReaderAction(ActionEvent event) {
		  openCreateReaderModal();
	    }

	@FXML
	void initialize() {

	}

	private void openCreateReaderModal() {
		CreateReaderController controller = new CreateReaderController();
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
	}

}
