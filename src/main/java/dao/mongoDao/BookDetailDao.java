package dao.mongoDao;

import java.util.List;

import model.mongo.BookDetail;

public interface BookDetailDao {

	public void save(BookDetail bookDetail);

	public void delete(BookDetail bookDetail);

	public void update(BookDetail bookDetail);

	public BookDetail getBookDetailById(int bookId);

	public List<BookDetail> getAllBookDetails();
}
