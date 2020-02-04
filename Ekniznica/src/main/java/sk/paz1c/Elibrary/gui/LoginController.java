package sk.paz1c.Elibrary.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class LoginController {

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private Button loginButton;

	@FXML
	void login(ActionEvent event) {
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();

		Reader reader = DaoFactory.INSTANCE.getReaderDao().getReaderByUsernameAndPassword(username, password);

		System.out.println(reader.toString() + " " + reader.getBirthDate());

		if (reader == null) {
			System.out.println("not found");
		} else {
			App.window.close();
			if (reader.getAdmin() == true) {
				loadAdminView();
			} else {
				loadNoAdminView(reader.getName(), reader.getId());

			}

		}

	}

	@FXML
	void initialize() {
		// loginButton.getStyleClass().setAll("button");
		// loginButton.getStyleClass().add("button success");
		loginButton.getStyleClass().setAll("button", "success");

		System.out.println("init");
	}

	private void loadNoAdminView(String name, Long id) {
		NoAdminViewController controller = new NoAdminViewController(name, id);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("noAdminView.fxml"));
		fxmlLoader.setController(controller);
		showScene(fxmlLoader);

	}

	private void loadAdminView() {
		AdminMainViewController controller = new AdminMainViewController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminMainView.fxml"));
		fxmlLoader.setController(controller);
		showScene(fxmlLoader);

	}

	private void showScene(FXMLLoader fxmlLoader) {

		try {
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			App.window.setScene(scene);
			App.window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
