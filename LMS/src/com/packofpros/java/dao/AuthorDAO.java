package com.packofpros.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.packofpros.java.entity.Author;

public class AuthorDAO extends BaseDAO<Author>{
	
	
	
	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws SQLException, ClassNotFoundException
	{
		save("insert into tbl_author (authorName) values(?)",new Object[] {author.getAuthorName()});
	}
	
	public void updateAuthor(Author author) throws SQLException, ClassNotFoundException
	{
		save("update tbl_author set authorName = ? where authorId = ?",new Object[] {author.getAuthorName(),author.getAuthorId()});
		
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		
		save("delete from tbl_author where authorId=?",new Object[] {author.getAuthorId()});	
	}
	
	public List<Author> ReadAllAuthors() throws ClassNotFoundException, SQLException
	{
		
		return read("select * from tbl_author",null);	
	}
	
	public List<Author> readAuthorsByName(String authorName) throws ClassNotFoundException, SQLException
	{
		authorName="%"+authorName+"%";
		
		return read("select * from tbl_author where authorName like ?",new Object[] {authorName});	
	}
	
	
	public Author readAuthorsById(Integer authorId) throws ClassNotFoundException, SQLException
	{
		List<Author> authors= read("select * from tbl_author where authorId = ?",new Object[] {authorId});
		if(authors!=null)
		{
			return authors.get(0);
		}
		return null;
	}
	
	public List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		BookDAO bdao=new BookDAO(conn);
		
		List<Author> authors=new ArrayList<>();
		while(rs.next()){
			Author author=new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			author.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId=?)", new Object[] {author.getAuthorId()}));
			authors.add(author);
		}
		return authors;
	}
	
	
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		
		List<Author> authors=new ArrayList<>();
		while(rs.next()){
			Author author=new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}
	

}