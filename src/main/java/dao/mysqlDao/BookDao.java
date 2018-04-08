package dao.mysqlDao;

import java.util.List;

import model.mysql.Book;

public interface BookDao {

	public Integer save(Book book);

	public void delete(Book book);

	public void update(Book book);

	public Book getBookById(int id);

	public List<Book> getAllBooks();

}