package com.packofpros.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.packofpros.java.entity.BookCopies;
import com.packofpros.java.entity.LibraryBranch;

public class BookCopiesDAO extends BaseDAO<BookCopies>{

	public BookCopiesDAO(Connection conn) {
		super(conn);
	}
	
	
	public void addBookCopies(BookCopies bookCopy) throws ClassNotFoundException, SQLException
	{
		save("insert into tbl_book_copies  values(?,?,?)",new Object[] {bookCopy.getBookId(),bookCopy.getBranchId(),bookCopy.getNoOfCopies()});
	}

	
	public List<BookCopies> ReadBookCopiesById(Integer bookId,Integer branchId) throws ClassNotFoundException, SQLException
	{
		
		return read("select * from tbl_book_copies where bookId=? and branchId=?",new Object[] {bookId,branchId});	
	}
	
	public void updateBookCopies(BookCopies bookCopy) throws SQLException, ClassNotFoundException
	{
		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId=?",new Object[] {bookCopy.getNoOfCopies(),bookCopy.getBookId(),bookCopy.getBranchId()});
		
	} 
	
	
	public void LoanBookCopies(BookCopies bookCopy) throws SQLException, ClassNotFoundException // both to Loan And Return
	{
		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId=?",new Object[] {bookCopy.getNoOfCopies(),bookCopy.getBookId(),bookCopy.getBranchId()});
		
	}  
	

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookCopies> bookCopies=new ArrayList<>();
		while(rs.next()){
		
			BookCopies bookCopy = new BookCopies();
			bookCopy.setBookId(rs.getInt("bookId"));
			bookCopy.setBranchId(rs.getInt("branchId"));
			bookCopy.setNoOfCopies(rs.getInt("noOfCopies"));
			
			bookCopies.add(bookCopy);
		}
		return bookCopies;
	}


	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookCopies> bookCopies=new ArrayList<>();
		while(rs.next()){
		
			BookCopies bookCopy = new BookCopies();
			bookCopy.setBookId(rs.getInt("bookId"));
			bookCopy.setBranchId(rs.getInt("branchId"));
			bookCopy.setNoOfCopies(rs.getInt("noOfCopies"));
			
			bookCopies.add(bookCopy);
		}
		return bookCopies;
	}
	
	

}
