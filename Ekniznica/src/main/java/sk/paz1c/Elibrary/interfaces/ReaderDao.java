package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Reader;

public interface ReaderDao {

	public Reader getReaderByUsernameAndPassword(String username, String password);
	
	public Reader getReaderByName(String name);
	
	public Reader saveReader(Reader reader);
	
	public List<Reader> getAllReaders();
	
	public List<Reader> getAllReadersByFullName(String name);

}
