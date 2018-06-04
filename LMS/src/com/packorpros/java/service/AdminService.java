package com.packorpros.java.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.packofpros.java.dao.AuthorDAO;
import com.packofpros.java.dao.BookCopiesDAO;
import com.packofpros.java.dao.BookDAO;
import com.packofpros.java.dao.BookLoansDAO;
import com.packofpros.java.dao.BorrowerDAO;
import com.packofpros.java.dao.GenreDAO;
import com.packofpros.java.dao.LibraryBranchDAO;
import com.packofpros.java.dao.PublisherDAO;
import com.packofpros.java.entity.Author;
import com.packofpros.java.entity.Book;
import com.packofpros.java.entity.BookCopies;
import com.packofpros.java.entity.BookLoans;
import com.packofpros.java.entity.Borrower;
import com.packofpros.java.entity.Genre;
import com.packofpros.java.entity.LibraryBranch;
import com.packofpros.java.entity.Publisher;

public class AdminService {

	public ConnectionUtil connUtil= new ConnectionUtil();
	
	public void saveAuthor(Author author) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			AuthorDAO adao=new AuthorDAO(conn);
			adao.addAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void saveLibraryBranch(LibraryBranch libraryBranch) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			LibraryBranchDAO lbdao=new LibraryBranchDAO(conn);
			lbdao.addLibraryBranch(libraryBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void saveGenre(Genre genre) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			GenreDAO gndao=new GenreDAO(conn);
			gndao.addGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void saveBorrower(Borrower borrower) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BorrowerDAO brdao=new BorrowerDAO(conn);
			brdao.addBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	
	public void saveBook(Book book) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bdao=new BookDAO(conn);
			Integer bookId=bdao.addBookWithId(book);
			
			for(Author a : book.getAuthors())
			{
				bdao.addBookAuthors(bookId, a.getAuthorId());
			}
			
			for(Genre g: book.getGenres())
			{
				bdao.addBookGenres(bookId, g.getGenreId());
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	
	public void saveBookCopy(BookCopies bookCopy) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookCopiesDAO bcdao=new BookCopiesDAO(conn);
			bcdao.addBookCopies(bookCopy);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void saveBookLoan(BookLoans bookLoan) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			bldao.addBookLoans(bookLoan);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void returnBookLoan(BookLoans bookLoan) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			bldao.returnBookLoans(bookLoan);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void loanBookCopies(BookCopies bookCopy) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookCopiesDAO bcdao=new BookCopiesDAO(conn);
			bcdao.LoanBookCopies(bookCopy);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	
	public void savePublisher(Publisher publisher) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			PublisherDAO pdao=new PublisherDAO(conn);
			pdao.addPublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public List<Publisher> readPublisher() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			PublisherDAO pdao=new PublisherDAO(conn);
			List<Publisher> publishers=pdao.ReadAllPublishers();
			conn.commit();
			return publishers;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	public List<Author> readAuthor() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			AuthorDAO authdao=new AuthorDAO(conn);
			List<Author> authors=authdao.ReadAllAuthors();
			conn.commit();
			return authors;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	public List<LibraryBranch> readLibraryBranch() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			LibraryBranchDAO lbdao=new LibraryBranchDAO(conn);
			List<LibraryBranch> libraryBranches=lbdao.ReadAllLibraryBranches();
			conn.commit();
			return libraryBranches;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	
	public List<BookCopies> readBookCopiesById(Integer bookId,Integer branchId) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookCopiesDAO bcdao=new BookCopiesDAO(conn);
			List<BookCopies> bookCopies=bcdao.ReadBookCopiesById(bookId, branchId);
			conn.commit();
			return bookCopies;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	
	public List<BookLoans> readAllBookLoans() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			List<BookLoans> bookLoans=bldao.ReadAllBookLoans();
			conn.commit();
			return bookLoans;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	
	public List<BookLoans> ReadBookLoansByUserBranch(Integer branchId,Integer cardNo) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			List<BookLoans> bookLoans=bldao.ReadBookLoansByUserBranch(branchId, cardNo);
			conn.commit();
			return bookLoans;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	
	public List<Book> ReadBooksByBookID(BookLoans bookLoan) throws SQLException  //for return book
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bdao=new BookDAO(conn);
			List<Book> books=bdao.ReadBooksByBookID(bookLoan);
			conn.commit();
			return books;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	public List<Genre> readGenre() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			GenreDAO gndao=new GenreDAO(conn);
			List<Genre> genres=gndao.ReadAllGenres();
			conn.commit();
			return genres;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	public List<Borrower> readBorrower() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BorrowerDAO brdao=new BorrowerDAO(conn);
			List<Borrower> borrowers=brdao.ReadAllBorrowers();
			conn.commit();
			return borrowers;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	
	public List<Book> readBook() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bookdao=new BookDAO(conn);
			List<Book> books=bookdao.ReadAllBooks();
			conn.commit();
			return books;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			PublisherDAO pdao=new PublisherDAO(conn);
			pdao.updatePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void updateGenre(Genre genre) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			GenreDAO gndao=new GenreDAO(conn);
			gndao.updateGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	
	public void updateBookCopies(BookCopies bookCopy) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookCopiesDAO bcdao=new BookCopiesDAO(conn);
			bcdao.updateBookCopies(bookCopy);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	
	
	public void updateAuthor(Author author) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			AuthorDAO authdao=new AuthorDAO(conn);
			authdao.updateAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	
	public void updateBorrower(Borrower borrower) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BorrowerDAO brdao=new BorrowerDAO(conn);
			brdao.updateBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void updateBook(Book book) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bookdao=new BookDAO(conn);
			bookdao.updateBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			LibraryBranchDAO lbdao=new LibraryBranchDAO(conn);
			lbdao.updateLibraryBranch(libraryBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			PublisherDAO pdao=new PublisherDAO(conn);
			pdao.deletePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void deleteBook(Book book) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bookdao=new BookDAO(conn);
			bookdao.deleteBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void deleteAuthor(Author author) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			AuthorDAO authdao=new AuthorDAO(conn);
			authdao.deleteAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void deleteGenre(Genre genre) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			GenreDAO gndao=new GenreDAO(conn);
			gndao.deleteGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BorrowerDAO brdao=new BorrowerDAO(conn);
			brdao.deleteBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			LibraryBranchDAO lbdao=new LibraryBranchDAO(conn);
			lbdao.deleteLibraryBranch(libraryBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
}
