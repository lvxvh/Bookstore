package dao.mysqlDao.impl;

import java.util.List;

import model.mysql.Book;

import dao.mysqlDao.BookDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BookDaoImpl extends HibernateDaoSupport implements BookDao {

	public Integer save(Book book) {
		getHibernateTemplate().save(book);
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) getHibernateTemplate().find(
				"from Book as b where b.bookId=(select max(bookId) from Book)");
		return books.size() > 0 ? books.get(0).getBookId() : -1;
	}

	public void delete(Book book) {

		getHibernateTemplate().delete(book);
	}

	public void update(Book book) {
		getHibernateTemplate().merge(book);
	}

	public Book getBookById(int id) {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) getHibernateTemplate().find(
				"from Book as b where b.bookId=?", id);
		return books.size() > 0 ? books.get(0) : null;
	}

	public List<Book> getAllBooks() {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) getHibernateTemplate()
				.find("from Book");
		return books;
	}

}
