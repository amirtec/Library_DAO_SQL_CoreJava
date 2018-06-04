package com.packofpros.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.packofpros.java.entity.BookCopies;
import com.packofpros.java.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans>{

	public BookLoansDAO(Connection conn) {
		super(conn);
	}
	
	
	public void addBookLoans(BookLoans bookLoans) throws ClassNotFoundException, SQLException
	{
		save("insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate)  values(?,?,?,?,?)",new Object[] {bookLoans.getBookId(),bookLoans.getBranchId(),bookLoans.getCardNo(),bookLoans.getDateOut(),bookLoans.getDueDate()});
	}
	
	
	public void returnBookLoans(BookLoans bookLoans) throws ClassNotFoundException, SQLException
	{
		save("update tbl_book_loans set dateIn=? where bookId=? and branchId=? and cardNo=?",new Object[] {bookLoans.getDateIn(),bookLoans.getBookId(),bookLoans.getBranchId(),bookLoans.getCardNo()});
	}
	
	
	public List<BookLoans> ReadBookLoansById(Integer bookId,Integer branchId,Integer cardNo) throws ClassNotFoundException, SQLException
	{
		
		return read("select * from tbl_book_loans where bookId= ? and branchId= ? and cardNo= ?",new Object[] {bookId,branchId,cardNo});	
	}
	
	
	public List<BookLoans> ReadBookLoansByUserBranch(Integer branchId,Integer cardNo) throws ClassNotFoundException, SQLException
	{
		
		return read("select * from tbl_book_loans where branchId= ? and cardNo= ? and dateIn is NULL",new Object[] {branchId,cardNo});	
	}
	
	public List<BookLoans> ReadAllBookLoans() throws ClassNotFoundException, SQLException
	{
		return read("select * from tbl_book_loans",null);	
	}
	
	public void changeDueDate(Integer bookId,Integer branchId,Integer cardNo,Date dueDate) throws ClassNotFoundException, SQLException
	{
		save("update tbl_book_loans set dueDate=? where bookId=? and branchId=? and cardNo=?",new Object[] {dueDate,bookId,branchId,cardNo});
	}

	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoans> bookLoans=new ArrayList<>();
		while(rs.next()){
		
			BookLoans bookLoan = new BookLoans();
			bookLoan.setBookId(rs.getInt("bookId"));
			bookLoan.setBranchId(rs.getInt("branchId"));
			bookLoan.setCardNo(rs.getInt("cardNo"));
			bookLoan.setDateIn(rs.getDate("dateOut"));
			bookLoan.setDateOut(rs.getDate("dueDate"));
			bookLoan.setDateIn(rs.getDate("dateIn"));
			
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

	@Override
	public List<BookLoans> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoans> bookLoans=new ArrayList<>();
		while(rs.next()){
		
			BookLoans bookLoan = new BookLoans();
			bookLoan.setBookId(rs.getInt("bookId"));
			bookLoan.setBranchId(rs.getInt("branchId"));
			bookLoan.setCardNo(rs.getInt("cardNo"));
			bookLoan.setDateIn(rs.getDate("dateOut"));
			bookLoan.setDateOut(rs.getDate("dueDate"));
			bookLoan.setDateIn(rs.getDate("dateIn"));
			
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

}
