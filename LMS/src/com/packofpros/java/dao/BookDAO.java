package com.packofpros.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.packofpros.java.entity.Book;
import com.packofpros.java.entity.BookLoans;

public class BookDAO extends BaseDAO<Book>{
	
	public BookDAO(Connection conn) {
		super(conn);
	}

	public void addBook(Book book) throws SQLException, ClassNotFoundException
	{
		save("insert into tbl_book (title,pubId) values(?,?)",new Object[] {book.getTitle(),book.getPublisher().getPublisherId()});
		
	}
	
	public Integer addBookWithId(Book book) throws SQLException, ClassNotFoundException
	{
		System.out.println("---"+book.getTitle()+"---"+book.getPublisher().getPublisherId());
		return saveWithId("insert into tbl_book (title,pubId) values(?,?)",new Object[] {book.getTitle(),book.getPublisher().getPublisherId()});
	}
	
	
	public void addBookAuthors(Integer bookId, Integer authorId) throws SQLException, ClassNotFoundException
	{
		save("insert into tbl_book_authors  values(?,?)",new Object[] {bookId,authorId});
	}
	
	public void addBookGenres(Integer bookId, Integer genreId) throws SQLException, ClassNotFoundException
	{
		save("insert into tbl_book_genres  values(?,?)",new Object[] {genreId,bookId});
	}
	
	
	public void updateBook(Book book) throws SQLException, ClassNotFoundException
	{
		save("update tbl_book set title = ?  where bookId = ?",new Object[] {book.getTitle(),book.getBookId()});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException
	{
		
		save("delete from tbl_book where bookId=?",new Object[] {book.getBookId()});	
	}
	
	public List<Book> ReadAllBooks() throws ClassNotFoundException, SQLException
	{
		return read("select * from tbl_book",null);	
	}
	
	public List<Book> ReadBooksByBookID(BookLoans bookLoan) throws ClassNotFoundException, SQLException //added for borrower return
	{
		return read("select * from tbl_book where bookId=?",new Object[] {bookLoan.getBookId()});	
	}
	
	public List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException
	{
//		AuthorDAO adao=new AuthorDAO(conn);
		
		List<Book> books=new ArrayList<>();
		while(rs.next()){
			Book book=new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
//			book.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId in (select authorId from tbl_book_authors where bookId=?", new Object[] {book.getBookId()}));
			//set genres
			//set publisher
			books.add(book);
		}
		return books;
	}
	
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		
		List<Book> books=new ArrayList<>();
		while(rs.next()){
			Book book=new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			books.add(book);
		}
		return books;
	}
	
	
}
