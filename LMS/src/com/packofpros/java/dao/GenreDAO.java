package com.packofpros.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.packofpros.java.entity.Author;
import com.packofpros.java.entity.Genre;

public class GenreDAO extends BaseDAO<Genre>{
	
	
	
	public GenreDAO(Connection conn) {
		super(conn);
	}

	public void addGenre(Genre genre) throws SQLException, ClassNotFoundException
	{
		save("insert into tbl_genre (genre_name) values(?)",new Object[] {genre.getGenreName()});
	}
	
	public void updateGenre(Genre genre) throws SQLException, ClassNotFoundException
	{
		save("update tbl_genre set genre_name = ? where genre_id = ?",new Object[] {genre.getGenreName(),genre.getGenreId()});
		
	}
	
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException
	{
		
		save("delete from tbl_genre where genre_id=?",new Object[] {genre.getGenreId()});	
	}
	
	public List<Genre> ReadAllGenres() throws ClassNotFoundException, SQLException
	{
		
		return read("select * from tbl_genre",null);	
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
	
	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException
	{
//		BookDAO bdao=new BookDAO(conn);
		
		List<Genre> genres=new ArrayList<>();
		while(rs.next()){
			Genre genre=new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
	
//			author.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId=?)", new Object[] {author.getAuthorId()}));
			
			genres.add(genre);
		}
		return genres;
	}
	
	
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException
	{

		List<Genre> genres=new ArrayList<>();
		while(rs.next()){
			Genre genre=new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));			
			genres.add(genre);
		}
		return genres;
	}
	
	

}