package sk.paz1c.Elibrary.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.mysql.DaoFactory;

public class RentBookController {

    

    @FXML
    private URL location;

    @FXML
    private Label bookLabel;

    @FXML
    private Label authorLabel;
    
    @FXML
    private Label categoryLabel;

    @FXML
    private TextArea desctiptionTF;

    @FXML
    private Button rentButton;

    @FXML
    private TextField rentBookField;
    
    private Book book;
    
    public RentBookController(Book book) {
    	this.book = new Book();
    	System.out.println(book.toString());
    	this.book.setName(book.getName());
    	this.book.setCategory(book.getCategory());
    	this.book.setDescription(book.getDescription());
    	this.book.setAuthor(book.getAuthor());
    	this.book.setId(book.getId());
    }
//Long.getLong(rentBookField.getText())
    @FXML
    void rentBook(ActionEvent event) {
    	DaoFactory.INSTANCE.getRentedBookDao().addRentedBook(Long.parseLong(rentBookField.getText()),book.getId());
    	rentButton.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
    	bookLabel.setText(book.getName());
    	authorLabel.setText(book.getAuthor());
    	categoryLabel.setText(book.getCategory());
    	desctiptionTF.setText(book.getDescription());
    	desctiptionTF.setEditable(false);
    	//desctiptionTF.setLineWrap(true);
    	desctiptionTF.setWrapText(true);
    	

    	
    }
}
