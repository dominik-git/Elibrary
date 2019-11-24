package sk.paz1c.Elibrary.modelFx;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.paz1c.Elibrary.model.Reader;

public class ReaderFx {
	private Long id;
	private StringProperty name = new SimpleStringProperty();
	private ObservableList<String> students = FXCollections.observableArrayList(); 
	
//	public void load(Subject subject) {
//		id = subject.getId();
//		setName(subject.getName());
//		students.setAll(subject.getStudents());
//	}
	
	public Reader getReader() {
		Reader subject = new Reader();
		subject.setId(id);
		subject.setName(getName());
//		subject.setStudents(new ArrayList<String>(students));
		return subject;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public ObservableList<String> getStudents() {
		return students;
	}

}
