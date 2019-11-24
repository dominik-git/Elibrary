package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Reader;

public interface ReaderDao {

	public Reader getReaderById(long id);

	public Reader getReaderByUsernameAndPassword(String username, String password);
	
	public Reader getReaderByUserName(String username);
	
	public void saveReader(Reader reader);
	
	public void deleteUserById(long id);
	
	public List<Reader> getAllReaders();

}
