package com.packofpros.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.packofpros.java.entity.Author;
import com.packofpros.java.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower>{
	
	
	
	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	public void addBorrower(Borrower borrower) throws SQLException, ClassNotFoundException
	{
		save("insert into tbl_borrower (name,address,phone) values(?,?,?)",new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone()});
	}
	
	public void updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException
	{
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone(),borrower.getCardNo()});
		
	}
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		
		save("delete from tbl_borrower where cardNo=?",new Object[] {borrower.getCardNo()});	
	}
	
	public List<Borrower> ReadAllBorrowers() throws ClassNotFoundException, SQLException
	{
		
		return read("select * from tbl_borrower",null);	
	}
	
//	public List<Author> readAuthorsByName(String authorName) throws ClassNotFoundException, SQLException
//	{
//		authorName="%"+authorName+"%";
//		
//		return read("select * from tbl_author where authorName like ?",new Object[] {authorName});	
//	}
//	
//	
//	public Author readAuthorsById(Integer authorId) throws ClassNotFoundException, SQLException
//	{
//		List<Author> authors= read("select * from tbl_author where authorId = ?",new Object[] {authorId});
//		if(authors!=null)
//		{
//			return authors.get(0);
//		}
//		return null;
//	}
	
	public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException
	{
//		BookDAO bdao=new BookDAO(conn);
		
		List<Borrower> borrowers=new ArrayList<>();
		while(rs.next()){
			Borrower borrower=new Borrower();
			
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setPhone(rs.getString("phone"));
			borrower.setAddress(rs.getString("address"));
			
//			author.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId=?)", new Object[] {author.getAuthorId()}));
			borrowers.add(borrower);
		}
		return borrowers;
	}
	
	
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		
		List<Borrower> borrowers=new ArrayList<>();
		while(rs.next()){
			Borrower borrower=new Borrower();
			
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setPhone(rs.getString("phone"));
			borrower.setAddress(rs.getString("address"));
			borrowers.add(borrower);
		}
		return borrowers;
	}
	

}