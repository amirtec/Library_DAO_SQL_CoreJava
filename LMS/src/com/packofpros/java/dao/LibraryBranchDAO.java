package com.packofpros.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.packofpros.java.entity.Author;
import com.packofpros.java.entity.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch>{
	
	
	
	public LibraryBranchDAO(Connection conn) {
		super(conn);
	}

	public void addLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException
	{
		save("insert into tbl_library_branch (branchName,branchAddress) values(?,?)",new Object[] {libraryBranch.getBranchName(),libraryBranch.getBranchAddress()});
	}
	
	public void updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException
	{
		save("update tbl_library_branch set branchName = ?,branchAddress = ? where branchId = ?",new Object[] {libraryBranch.getBranchName(),libraryBranch.getBranchAddress(),libraryBranch.getBranchId()});
		
	}
	
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException
	{
		
		save("delete from tbl_library_branch where branchId=?",new Object[] {libraryBranch.getBranchId()});	
	}
	
	public List<LibraryBranch> ReadAllLibraryBranches() throws ClassNotFoundException, SQLException
	{
		
		return read("select * from tbl_library_branch",null);	
	}
	
	
//	public List<Author> readAuthorsByName(String authorName) throws ClassNotFoundException, SQLException
//	{
//		authorName="%"+authorName+"%";
//		
//		return read("select * from tbl_author where authorName like ?",new Object[] {authorName});	
//	}
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
	
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException
	{
//		BookDAO bdao=new BookDAO(conn);
		
		List<LibraryBranch> libraryBranches=new ArrayList<>();
		while(rs.next()){
		
			LibraryBranch libraryBranch = new LibraryBranch();
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			
//			author.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId=?)", new Object[] {author.getAuthorId()}));
			libraryBranches.add(libraryBranch);
		}
		return libraryBranches;
	}
	
	
	public List<LibraryBranch> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		
		List<LibraryBranch> libraryBranches=new ArrayList<>();
		while(rs.next()){
			LibraryBranch libraryBranch = new LibraryBranch();
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			
			libraryBranches.add(libraryBranch);
		}
		return libraryBranches;
	}
	

}