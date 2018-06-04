package com.packofpros.java.ui;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.packofpros.java.entity.Author;
import com.packofpros.java.entity.Book;
import com.packofpros.java.entity.BookLoans;
import com.packofpros.java.entity.Borrower;
import com.packofpros.java.entity.Genre;
import com.packofpros.java.entity.LibraryBranch;
import com.packofpros.java.entity.Publisher;
import com.packorpros.java.service.AdminService;


public class AdminMenu {

	public void AdminOptions()
	{
		System.out.println("---------Entered Administrator Menu--------------");

		System.out.println("1> Add/Update/Delete/Read Book and Author");
		System.out.println("2> Add/Update/Delete/Read Publishers");
		System.out.println("3> Add/Update/Delete/Read Library Branches");
		System.out.println("4> Add/Update/Delete/Read Borrowers");
		System.out.println("5> Over-ride Due Date for a Book Loan");
		System.out.println("6> Add/Update/Delete/Read Genres");
		System.out.println("7> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,2,3,4,5,6,0 as your choice ");

		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());

		switch(input)
		{
		case 0 :
			LibraryManagementSystem lms= new LibraryManagementSystem();
			lms.StartApplication();
			break; // break is optional   

		case 1 :
			BookAuthorOptions();
			break; // break is optional

		case 2 :
			PublisherOptions();

			break; // break is optional

		case 3 :
			LibraryBranchOptions();
			break; // break is optional
		case 4 :
			BorrowerOptions();
			break; // break is optional
		case 5 :
			OverRideDueDate();
			break; // break is optional
		case 6 :
			GenreOptions();
			break; // break is optional

			// We can have any number of case statements
			// below is default statement,used when none of the cases is true. 
			// No break is needed in the default case.
		default : 
			System.out.println("Incorrect value Enter your choice again");
			AdminMenu am=new AdminMenu();
			am.AdminOptions();
		}



	}

	private void OverRideDueDate() {

		System.out.println("------------Over-ride Due Date for a Book Loan--------------");
		AdminService ads=new AdminService();
		List<BookLoans> boookLoans=new ArrayList<>();
		
			try {
				
					boookLoans=ads.readAllBookLoans();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
		if(boookLoans.isEmpty()||boookLoans==null)
		{
			System.out.println("There is no Entry in Book Loans");
			
			AdminOptions();
		}

		if((!boookLoans.isEmpty())||boookLoans!=null)
		{



			System.out.println("BookId. \t BranchId \t CardNo \t DateOut \t DueDate \t DateIn");
			for(BookLoans bl:boookLoans)
			{

				System.out.println(bl.getBookId()+"\t \t"+bl.getBranchId()+"\t \t"+bl.getCardNo()+"\t \t"+bl.getDateOut()+"\t \t"+bl.getDueDate()+"\t \t"+bl.getDateIn());
			}

			System.out.println(" Enter BookId or Press 0 to to go back to previous menu");
			Scanner scan=new Scanner(System.in);
			int bookId=Integer.parseInt(scan.nextLine());
			if(bookId==0)
			{
				AdminOptions();
			}

			System.out.println(" Enter BranchId or Press 0 to Cancel");
			scan=new Scanner(System.in);
			int branchId=Integer.parseInt(scan.nextLine());
			if(branchId==0)
			{
				AdminOptions();
			}

			System.out.println(" Enter CardNo or Press 0 to Cancel");
			scan=new Scanner(System.in);
			int cardNo=Integer.parseInt(scan.nextLine());
			if(cardNo==0)
			{
				AdminOptions();
			}

		}

	}

	private void BorrowerOptions() {
		System.out.println("------------Add/Update/Delete/Read Borrower--------------");
		System.out.println("1> Add Borrower");
		System.out.println("2> Update Borrower");
		System.out.println("3> Delete Borrower");
		System.out.println("4> Read All Borrowers");
		System.out.println("5> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,2,3,4,0 as your choice ");

		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());
		AdminService ads=new AdminService();

		switch(input)
		{
		case 0 :
			AdminMenu am=new AdminMenu();
			am.AdminOptions();
			break;

		case 1 :

			String borrowerName="",borrowerAddress="",borrowerPhone="";
			System.out.println("Enter Borrower Name");
			scan=new Scanner(System.in);
			borrowerName=scan.nextLine();
			System.out.println("Enter Borrower Address");
			scan=new Scanner(System.in);
			borrowerAddress=scan.nextLine();
			System.out.println("Enter Borrower Phone");
			scan=new Scanner(System.in);
			borrowerPhone=scan.nextLine();


			Borrower borrower=new Borrower();
			borrower.setName(borrowerName);
			borrower.setAddress(borrowerAddress);
			borrower.setPhone(borrowerPhone);


			try {
				ads.saveBorrower(borrower);
				BorrowerOptions();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case 2:
			List<Borrower> borrowers=new ArrayList<>();
			try {
				borrowers=ads.readBorrower();

				System.out.println("CardNo. \t Name \t Address \t Phone");
				for(Borrower br:borrowers)
				{

					System.out.println(br.getCardNo()+"\t \t"+br.getName()+"\t \t"+br.getAddress()+"\t \t"+br.getPhone());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter CardNo. to Update");
			scan=new Scanner(System.in);
			int cardNo=Integer.parseInt(scan.nextLine());

			System.out.println("Enter Name");
			scan=new Scanner(System.in);
			borrowerName=scan.nextLine();
			System.out.println("Enter Address");
			scan=new Scanner(System.in);
			borrowerAddress=scan.nextLine();
			System.out.println("Enter Phone");
			scan=new Scanner(System.in);
			borrowerPhone=scan.nextLine();

			borrower=new Borrower();
			borrower.setCardNo(cardNo);
			borrower.setName(borrowerName);
			borrower.setAddress(borrowerAddress);
			borrower.setPhone(borrowerPhone);


			try {
				ads.updateBorrower(borrower);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BorrowerOptions();
			break;  

		case 3 :
			borrowers=new ArrayList<>();
			try {
				borrowers=ads.readBorrower();

				System.out.println("CardNo. \t Name \t Address \t Phone");
				for(Borrower br:borrowers)
				{

					System.out.println(br.getCardNo()+"\t \t"+br.getName()+"\t \t"+br.getAddress()+"\t \t"+br.getPhone());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter cardNo to Delete");
			scan=new Scanner(System.in);
			cardNo=Integer.parseInt(scan.nextLine());



			borrower=new Borrower();
			borrower.setCardNo(cardNo);

			try {
				ads.deleteBorrower(borrower);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BorrowerOptions();
			break;

		case 4: 
			borrowers=new ArrayList<>();
			try {
				borrowers=ads.readBorrower();

				System.out.println("CardNo. \t Name \t Address \t Phone");
				for(Borrower br:borrowers)
				{

					System.out.println(br.getCardNo()+"\t \t"+br.getName()+"\t \t"+br.getAddress()+"\t \t"+br.getPhone());
				}
				BorrowerOptions();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		default : 
			System.out.println("Incorrect value Enter your choice again");
			BorrowerOptions();
		}
	}

	private void GenreOptions() {
		System.out.println("------------Add/Update/Delete/Read Genre--------------");
		System.out.println("1> Add Genre");
		System.out.println("2> Update Genre");
		System.out.println("3> Delete Genre");
		System.out.println("4> Read All Genres");
		System.out.println("5> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,2,3,4,0 as your choice ");

		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());
		AdminService ads=new AdminService();

		switch(input)
		{
		case 0 :
			AdminMenu am=new AdminMenu();
			am.AdminOptions();
			break;

		case 1 :

			String genreName="";
			System.out.println("Enter Genre Name");
			scan=new Scanner(System.in);
			genreName=scan.nextLine();


			Genre genre=new Genre();
			genre.setGenreName(genreName);
			try {
				ads.saveGenre(genre);
				GenreOptions();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case 2:
			List<Genre> genres=new ArrayList<>();
			try {
				genres=ads.readGenre();

				System.out.println("GenreId \t GenreName");
				for(Genre gn:genres)
				{

					System.out.println(gn.getGenreId()+"\t \t"+gn.getGenreName());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter GenreID to Update");
			scan=new Scanner(System.in);
			int genreId=Integer.parseInt(scan.nextLine());

			System.out.println("Enter Genre Name");
			scan=new Scanner(System.in);
			genreName=scan.nextLine();

			genre=new Genre();
			genre.setGenreId(genreId);
			genre.setGenreName(genreName);


			try {
				ads.updateGenre(genre);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GenreOptions();
			break;  

		case 3 :
			genres=new ArrayList<>();
			try {
				genres=ads.readGenre();

				System.out.println("GenreId \t GenreName");
				for(Genre gn:genres)
				{

					System.out.println(gn.getGenreId()+"\t \t"+gn.getGenreName());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter GenreID to Delete");
			scan=new Scanner(System.in);
			genreId=Integer.parseInt(scan.nextLine());



			genre=new Genre();
			genre.setGenreId(genreId);

			try {
				ads.deleteGenre(genre);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GenreOptions();
			break;

		case 4: 
			genres=new ArrayList<>();
			try {
				genres=ads.readGenre();

				System.out.println("GenreId \t GenreName");
				for(Genre gn:genres)
				{

					System.out.println(gn.getGenreId()+"\t \t"+gn.getGenreName());
				}
				GenreOptions();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default : 
			System.out.println("Incorrect value Enter your choice again");
			GenreOptions();
		}
	}

	private void LibraryBranchOptions() {
		System.out.println("------------Add/Update/Delete/Read Library Branch--------------");
		System.out.println("1> Add Library Branch");
		System.out.println("2> Update Library Branch");
		System.out.println("3> Delete Library Branch");
		System.out.println("4> Read All Library Branch");
		System.out.println("5> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,2,3,4,0 as your choice ");

		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());
		AdminService ads=new AdminService();

		switch(input)
		{
		case 0 :
			AdminMenu am=new AdminMenu();
			am.AdminOptions();
			break;

		case 1 :

			String branchName="",branchAddress;
			System.out.println("Enter Branch Name");
			scan=new Scanner(System.in);
			branchName=scan.nextLine();
			System.out.println("Enter Branch Address");
			scan=new Scanner(System.in);
			branchAddress=scan.nextLine();

			LibraryBranch libraryBranch=new LibraryBranch();
			libraryBranch.setBranchName(branchName);
			libraryBranch.setBranchAddress(branchAddress);
			try {
				ads.saveLibraryBranch(libraryBranch);
				LibraryBranchOptions();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case 2:
			List<LibraryBranch> libraryBranches=new ArrayList<>();
			try {
				libraryBranches=ads.readLibraryBranch();

				System.out.println("BranchId \t BranchName \t BranchAddress");
				for(LibraryBranch lb:libraryBranches)
				{

					System.out.println(lb.getBranchId()+"\t \t"+lb.getBranchName()+ "\t \t "+lb.getBranchAddress());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter BranchID to Update");
			scan=new Scanner(System.in);
			int branchId=Integer.parseInt(scan.nextLine());

			System.out.println("Enter Branch Name");
			scan=new Scanner(System.in);
			branchName=scan.nextLine();
			System.out.println("Enter Branch Address");
			scan=new Scanner(System.in);
			branchAddress=scan.nextLine();


			libraryBranch=new LibraryBranch();
			libraryBranch.setBranchId(branchId);
			libraryBranch.setBranchName(branchName);
			libraryBranch.setBranchAddress(branchAddress);


			try {
				ads.updateLibraryBranch(libraryBranch);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LibraryBranchOptions();
			break;  

		case 3 :
			libraryBranches=new ArrayList<>();
			try {
				libraryBranches=ads.readLibraryBranch();

				System.out.println("BranchId \t BranchName \t BranchAddress");
				for(LibraryBranch lb:libraryBranches)
				{

					System.out.println(lb.getBranchId()+"\t \t"+lb.getBranchName()+ "\t \t "+lb.getBranchAddress());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter BranchID to Delete");
			scan=new Scanner(System.in);
			branchId=Integer.parseInt(scan.nextLine());



			libraryBranch=new LibraryBranch();
			libraryBranch.setBranchId(branchId);

			try {
				ads.deleteLibraryBranch(libraryBranch);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LibraryBranchOptions();
			break;

		case 4: 
			libraryBranches=new ArrayList<>();
			try {
				libraryBranches=ads.readLibraryBranch();

				System.out.println("BranchId \t BranchName \t BranchAddress");
				for(LibraryBranch lb:libraryBranches)
				{

					System.out.println(lb.getBranchId()+"\t \t"+lb.getBranchName()+ "\t \t "+lb.getBranchAddress());
				}


				LibraryBranchOptions();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default : 
			System.out.println("Incorrect value Enter your choice again");
			LibraryBranchOptions();
		}



	}

	private void BookAuthorOptions() {
		System.out.println("------------Add/Update/Delete/Read Book and Author--------------");
		System.out.println("1> Add/Update/Delete/Read Book");
		System.out.println("2> Add/Update/Delete/Read Author");
		System.out.println("3> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,2,0 as your choice ");
		AdminMenu am=new AdminMenu();

		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());

		switch(input)
		{
		case 0: 
			am.AdminOptions();	
			break;

		case 1:
			BookOptions();
			break;

		case 2:
			AuthorOptions();
			break;

		default:
			System.out.println("Incorrect value Enter your choice again");
			BookAuthorOptions();
		}


		scan.close();

	}

	private void AuthorOptions() {
		System.out.println("--------Add/Update/Delete/Read Author--------");
		System.out.println("1> Add Author");
		System.out.println("2> Update Author");
		System.out.println("3> Delete Author");
		System.out.println("4> Read All Authors");
		System.out.println("5> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,2,3,4,0 as your choice ");

		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());
		AdminService ads=new AdminService();

		switch(input)
		{
		case 0:
			BookAuthorOptions();
			break;

		case 1:
			String authorName="";
			System.out.println("Enter Author Name");
			scan=new Scanner(System.in);
			authorName=scan.nextLine();

			Author author=new Author();
			author.setAuthorName(authorName);
			try {
				ads.saveAuthor(author);
				AuthorOptions();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;

		case 2:
			List<Author> authors=new ArrayList<>();
			try {
				authors=ads.readAuthor();

				System.out.println("AuthorId \t AuthorName");
				for(Author auth:authors)
				{

					System.out.println(auth.getAuthorId()+"\t \t"+auth.getAuthorName());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter AuthorID to Update");
			scan=new Scanner(System.in);
			int authIdCase2=Integer.parseInt(scan.nextLine());

			System.out.println("Enter Author Name");
			scan=new Scanner(System.in);
			authorName=scan.nextLine();


			author=new Author();
			author.setAuthorId(authIdCase2);
			author.setAuthorName(authorName);


			try {
				ads.updateAuthor(author);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			AuthorOptions();
			break;

		case 3 :
			authors=new ArrayList<>();
			try {
				authors=ads.readAuthor();

				System.out.println("AuthorId \t AuthorName");
				for(Author auth:authors)
				{

					System.out.println(auth.getAuthorId()+"\t \t"+auth.getAuthorName());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter AuthorID to delete");
			scan=new Scanner(System.in);
			int authorId=Integer.parseInt(scan.nextLine());



			author=new Author();
			author.setAuthorId(authorId);

			try {
				ads.deleteAuthor(author);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			AuthorOptions();
			break;

		case 4: 
			authors=new ArrayList<>();
			try {
				authors=ads.readAuthor();

				System.out.println("AuthorId \t AuthorName");
				for(Author auth:authors)
				{

					System.out.println(auth.getAuthorId()+"\t \t"+auth.getAuthorName());
				}

				AuthorOptions();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		default:
			System.out.println("Incorrect value Enter your choice again");
			AuthorOptions();
		}
	}

	private void BookOptions() {
		System.out.println("--------Add/Update/Delete/Read Book--------");
		System.out.println("1> Add Book");
		System.out.println("2> Update Book");
		System.out.println("3> Delete Book");
		System.out.println("4> Read All Book");
		System.out.println("5> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,2,3,4,0 as your choice ");
		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());
		AdminService ads=new AdminService();
		
		switch(input)
		{
		case 0 :
			BookAuthorOptions();
			break; 
		case 1:
			
			Book book=new Book();
			String title="";
			System.out.println("Enter Book Title");
			scan=new Scanner(System.in);
			title=scan.nextLine();
			book.setTitle(title);

			//ask user to Choose from Publisher Id
			List<Publisher>publishers=new ArrayList<>();
			try {
				publishers=ads.readPublisher();

				System.out.println("PublisherId \t \t  PublisherName \t \t PublisherAddress \t \t PublisherPhone");
				for(Publisher pub:publishers)
				{

					System.out.println(pub.getPublisherId()+"\t \t"+pub.getPublisherName()+"\t \t"+pub.getPublisherAddress()+"\t \t"+pub.getPublisherPhone());
				}


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter Publisher Id to assign publisher to the Book");
			scan=new Scanner(System.in);
			int pubId=Integer.parseInt(scan.nextLine());
			
			for(Publisher pub:publishers)
			{
				if(pub.getPublisherId()==pubId)
				{
					book.setPublisher(pub);
				}
			}
			
				
			//ask user  to add authors
			int authorInput=1;
			List<Author> authors2=new ArrayList<>();
			while(authorInput==1)
			{
				
				List<Author> authors=new ArrayList<>();
				try {
					authors=ads.readAuthor();

					System.out.println("AuthorId \t AuthorName");
					for(Author auth:authors)
					{

						System.out.println(auth.getAuthorId()+"\t \t"+auth.getAuthorName());
					}

					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Enter AuthorId to add Author to Book");
				System.out.println("Enter 0 to Skip");
				scan=new Scanner(System.in);
				int authorId=Integer.parseInt(scan.nextLine());
				if(authorId==0)
				{
					break;
				}
				
				
				for(Author auth:authors)
				{

					if(auth.getAuthorId()==authorId)
					{
						authors2.add(auth);
					}
				}
				
			}
			book.setAuthors(authors2);
			
			//ask user to Add Genre
			List<Genre> genres2=new ArrayList<>();
			int genreInput=1;
			while(genreInput==1)
			{
				List<Genre> genres=new ArrayList<>();
				try {
					genres=ads.readGenre();

					System.out.println("GenreId \t GenreName");
					for(Genre gn:genres)
					{

						System.out.println(gn.getGenreId()+"\t \t"+gn.getGenreName());
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Enter GenreId to add Genre to Book");
				System.out.println("Enter 0 to Skip");
				scan=new Scanner(System.in);
				int genreId=Integer.parseInt(scan.nextLine());
				if(genreId==0)
				{
					break;
				}
				
				
				for(Genre gn:genres)
				{

					if(gn.getGenreId()==genreId)
					{
						genres2.add(gn);
					}
				}
				
				
				
			}
			book.setGenres(genres2);
			
			try {
				ads.saveBook(book);
				BookOptions();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			break;
			
			
		case 2:
			List<Book> books=new ArrayList<>();
			try {
				books=ads.readBook();

				System.out.println("BookId \t \t  BookTitle");
				for(Book book1:books)
				{

					System.out.println(book1.getBookId()+"\t \t"+book1.getTitle());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter BookID to Update");
			scan=new Scanner(System.in);
			int bookIdCase2=Integer.parseInt(scan.nextLine());

			System.out.println("Enter Book Title");
			scan=new Scanner(System.in);
			title=scan.nextLine();


			book=new Book();
			book.setBookId(bookIdCase2);
			book.setTitle(title);

			try {
				ads.updateBook(book);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BookOptions();

			break;
			
		case 3:
			books=new ArrayList<>();
			try {
				books=ads.readBook();

				System.out.println("BookId \t \t  BookTitle");
				for(Book book1:books)
				{

					System.out.println(book1.getBookId()+"\t \t"+book1.getTitle());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter BookID to Delete");
			scan=new Scanner(System.in);
			bookIdCase2=Integer.parseInt(scan.nextLine());



			book=new Book();
			book.setBookId(bookIdCase2);

			try {
				ads.deleteBook(book);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BookOptions();
			break;
			
		case 4:
			books=new ArrayList<>();
			try {
				books=ads.readBook();

				System.out.println("BookId \t \t  BookTitle");
				for(Book book1:books)
				{

					System.out.println(book1.getBookId()+"\t \t"+book1.getTitle());
				}

				BookOptions();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default :
			System.out.println("Incorrect value Enter your choice again");
			BookOptions();
			
		}
		

	}

	public void PublisherOptions()
	{

		System.out.println("--------Add/Update/Delete/Read Publisher--------");
		System.out.println("1> Add Publisher");
		System.out.println("2> Update Publisher");
		System.out.println("3> Delete Publisher");
		System.out.println("4> Read All Publishers");
		System.out.println("5> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,2,3,4,0 as your choice ");

		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());
		AdminService ads=new AdminService();
		switch(input)
		{
		case 0 :
			AdminMenu am=new AdminMenu();
			am.AdminOptions();
			break; 

		case 1 :
			String pName="",pAddress="",pPhone="";
			System.out.println("Enter Publisher Name");
			scan=new Scanner(System.in);
			pName=scan.nextLine();
			System.out.println("Enter Publisher Address");
			scan=new Scanner(System.in);
			pAddress=scan.nextLine();
			System.out.println("Enter Publisher Phone");
			scan=new Scanner(System.in);
			pPhone=scan.nextLine();

			Publisher publisher=new Publisher();
			publisher.setPublisherName(pName);
			publisher.setPublisherAddress(pAddress);
			publisher.setPublisherPhone(pPhone);


			try {
				ads.savePublisher(publisher);
				PublisherOptions();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break; 

		case 2 : 
			List<Publisher> publishers=new ArrayList<>();
			try {
				publishers=ads.readPublisher();

				System.out.println("PublisherId \t \t  PublisherName \t \t PublisherAddress \t \t PublisherPhone");
				for(Publisher pub:publishers)
				{

					System.out.println(pub.getPublisherId()+"\t \t"+pub.getPublisherName()+"\t \t"+pub.getPublisherAddress()+"\t \t"+pub.getPublisherPhone());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter PublisherID to Update");
			scan=new Scanner(System.in);
			int pubIdCase2=Integer.parseInt(scan.nextLine());

			System.out.println("Enter Publisher Name");
			scan=new Scanner(System.in);
			pName=scan.nextLine();

			System.out.println("Enter Publisher Address");
			scan=new Scanner(System.in);
			pAddress=scan.nextLine();

			System.out.println("Enter Publisher Phone");
			scan=new Scanner(System.in);
			pPhone=scan.nextLine();

			publisher=new Publisher();
			publisher.setPublisherId(pubIdCase2);
			publisher.setPublisherName(pName);
			publisher.setPublisherAddress(pAddress);
			publisher.setPublisherPhone(pPhone);

			try {
				ads.updatePublisher(publisher);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PublisherOptions();

			break;  
		case 3 :
			publishers=new ArrayList<>();
			try {
				publishers=ads.readPublisher();

				System.out.println("PublisherId \t \t  PublisherName \t \t PublisherAddress \t \t PublisherPhone");
				for(Publisher pub:publishers)
				{

					System.out.println(pub.getPublisherId()+"\t \t"+pub.getPublisherName()+"\t \t"+pub.getPublisherAddress()+"\t \t"+pub.getPublisherPhone());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter PublisherID to delete");
			scan=new Scanner(System.in);
			int pubIdCase3=Integer.parseInt(scan.nextLine());



			publisher=new Publisher();
			publisher.setPublisherId(pubIdCase3);

			try {
				ads.deletePublisher(publisher);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PublisherOptions();
			break;
		case 4 :
			publishers=new ArrayList<>();
			try {
				publishers=ads.readPublisher();

				System.out.println("PublisherId \t \t  PublisherName \t \t PublisherAddress \t \t PublisherPhone");
				for(Publisher pub:publishers)
				{

					System.out.println(pub.getPublisherId()+"\t \t"+pub.getPublisherName()+"\t \t"+pub.getPublisherAddress()+"\t \t"+pub.getPublisherPhone());
				}

				PublisherOptions();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default : 
			System.out.println("Incorrect value Enter your choice again");
			PublisherOptions();
		}
		scan.close();

	}

}
