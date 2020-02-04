package sk.paz1c.Elibrary.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class ChangePasswordController {

	public ChangePasswordController(Long id) {
		this.readerId = id;
	}

	private Long readerId;
	@FXML
	private Button changePasswordButton;

	@FXML
	private TextField newPasswordText;

	@FXML
	private TextField confirmPasswordText;

	@FXML
	private Label incorectPasswordLabel;

	@FXML
	void onChangePassword(ActionEvent event) {
		String newPass = newPasswordText.getText();
		String confirmPass = confirmPasswordText.getText();
		if (newPass.equals(confirmPass)) {
			DaoFactory.INSTANCE.getReaderDao().changePasswordById(readerId, newPass);
			incorectPasswordLabel.getScene().getWindow().hide();
		}else {
			incorectPasswordLabel.setVisible(true);
		}
	}

	@FXML
	void initialize() {
		incorectPasswordLabel.setVisible(false);
	}

}
