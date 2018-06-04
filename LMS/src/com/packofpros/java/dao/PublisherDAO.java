package com.packofpros.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.packofpros.java.entity.Author;
import com.packofpros.java.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{
	
	
	
	public PublisherDAO(Connection conn) {
		super(conn);
	}
	
	public void addPublisher(Publisher publisher) throws SQLException, ClassNotFoundException
	{
		System.out.println("address in PDAO "+publisher.getPublisherAddress());
		save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values(?,?,?)",new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone()});
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException, ClassNotFoundException
	{
		save("update tbl_publisher set publisherName = ?,publisherAddress=?,publisherPhone=? where publisherId = ?",new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone(),publisher.getPublisherId()});
		
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException
	{
		
		save("delete from tbl_publisher where publisherId=?",new Object[] {publisher.getPublisherId()});	
	}
	
	public List<Publisher> ReadAllPublishers() throws ClassNotFoundException, SQLException
	{
		
		return read("select * from tbl_publisher",null);	
	}
	
	public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException
	{
//		BookDAO bdao=new BookDAO(conn);
		
		List<Publisher> publishers=new ArrayList<>();
		while(rs.next()){
			Publisher publisher=new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			
//			author.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId=?)", new Object[] {author.getAuthorId()}));
			publishers.add(publisher);
		}
		return publishers;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		return null;
	}
	
	
	//---------------------------------------------------------------------

	
	
	
	
	
	
	
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
	
//	public List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException
//	{
//		BookDAO bdao=new BookDAO(conn);
//		
//		List<Author> authors=new ArrayList<>();
//		while(rs.next()){
//			Author author=new Author();
//			author.setAuthorId(rs.getInt("authorId"));
//			author.setAuthorName(rs.getString("authorName"));
//			author.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId=?)", new Object[] {author.getAuthorId()}));
//			authors.add(author);
//		}
//		return authors;
//	}
	
	
//	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException
//	{
//		
//		List<Author> authors=new ArrayList<>();
//		while(rs.next()){
//			Author author=new Author();
//			author.setAuthorId(rs.getInt("authorId"));
//			author.setAuthorName(rs.getString("authorName"));
//			authors.add(author);
//		}
//		return authors;
//	}
	

}