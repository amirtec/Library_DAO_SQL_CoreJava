package com.packofpros.java.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.packofpros.java.entity.Book;
import com.packofpros.java.entity.BookCopies;
import com.packofpros.java.entity.LibraryBranch;
import com.packorpros.java.service.AdminService;

public class LibrarianMenu {
	
	public void LibrarianOptions()
	{
		System.out.println("---------Entered Librarian Menu--------------");

		System.out.println("1> Enter Branch you manage");
		System.out.println("2> Enter 0 to go back to the previous menu");
		System.out.println("Enter 1,0 as your choice ");
		
		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());
		
		AdminService ads=new AdminService();
		
		switch(input)
		{
		case 0 :
			LibraryManagementSystem lms= new LibraryManagementSystem();
			lms.StartApplication();
			break;
			
		case 1:
			int loop1=1;
			while(loop1!=0)
			{
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
				
			System.out.println("1> Enter BranchId to Login");
			System.out.println("Enter 0 to go back to Previous menu");
			System.out.println("Enter 1,0 as your choice ");
			
			scan=new Scanner(System.in);
			loop1=Integer.parseInt(scan.nextLine());
			if(loop1==0)
			{
				LibrarianOptions();
			}
			
			int loop2=loop1;
			LibraryBranch lbChosen=new LibraryBranch();
			
			for(LibraryBranch lb:libraryBranches)
			{
				if(lb.getBranchId()==loop1)
				{
					lbChosen=lb;
				}
				
			}
			
			while(loop2!=0)
			{
				System.out.println("1> Update the details of the Library");
				System.out.println("2> Add copies of Book to the Branch");
				System.out.println("Enter 0 to go back to Previous menu");
				System.out.println("Enter 1,2,0 as your choice ");
				
				scan=new Scanner(System.in);
				loop2=Integer.parseInt(scan.nextLine());
				
				if(loop2==0)
				{
					break;
				}
				
				if(loop2==1)
				{

					while(loop2==1)
					{

					System.out.println("BranchId \t BranchName \t BranchAddress");


					System.out.println(lbChosen.getBranchId()+"\t \t"+lbChosen.getBranchName()+ "\t \t "+lbChosen.getBranchAddress());

					System.out.println("Please enter new branch name or enter 0 to cancel");
					scan=new Scanner(System.in);
					String brName=scan.nextLine();
					
					if(brName.equals("0"))
					{
						break;
					}
					
					System.out.println("Please enter new branch Address or enter 0 to cancel");
					String brAddress=scan.nextLine();
					if(brAddress.equals("0"))
					{
						break;
					}

					LibraryBranch lBranch=new LibraryBranch();
					lBranch.setBranchId(lbChosen.getBranchId());
					lBranch.setBranchName(brName);
					lBranch.setBranchAddress(brAddress);

					try {
						ads.updateLibraryBranch(lBranch);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}//while loop

				} //loop2==1 ends here
				
				if(loop2==2)
				{
					int loop3=loop2;
					while(loop3==2)
					{
					
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
					
					System.out.println("Enter 0 to go back to Previous menu");
					System.out.println("Pick the BookId you want to add copies of, to your branch:");
					
					scan=new Scanner(System.in);
					loop3=Integer.parseInt(scan.nextLine());
					
					if(loop3==0)
					{
						break;
					}
					
					Book book2=new Book();
					for(Book book1:books)
					{

						if(book1.getBookId()==loop3)
						{
							book2=book1;
						}
					}
					List<BookCopies> bcCopies=new ArrayList<>();
					try {
							bcCopies= ads.readBookCopiesById(book2.getBookId(), lbChosen.getBranchId());
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(bcCopies.isEmpty())
					{
						int loop4=1;
						while(loop4!=0)
						{
						System.out.println("Existing number of copies: 0");
						System.out.println("Enter number of copies or press 0 to exit");
						scan= new Scanner(System.in);
						int ncopies=Integer.parseInt(scan.nextLine());
						
						if(ncopies==0)
						{
							break;
						}
						
						BookCopies bcCopy=new BookCopies();
						bcCopy.setBookId(book2.getBookId());
						bcCopy.setBranchId(lbChosen.getBranchId());
						bcCopy.setNoOfCopies(ncopies);
						try {
							ads.saveBookCopy(bcCopy);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						loop4=0;
						}//while loop
						
					}
					else
					{
						int loop4=1;
						while(loop4!=0)
						{

							System.out.println("Existing number of copies: "+bcCopies.get(0).getNoOfCopies());
							System.out.println("Enter new number of copies or press 0 to exit");

							scan= new Scanner(System.in);
							int ncopies=Integer.parseInt(scan.nextLine());

							if(ncopies==0)
							{
								break;
							}

							BookCopies bcCopy=new BookCopies();
							bcCopy.setBookId(book2.getBookId());
							bcCopy.setBranchId(lbChosen.getBranchId());
							bcCopy.setNoOfCopies(ncopies);
							try {
								ads.updateBookCopies(bcCopy);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							loop4=0;
						}//while loop

					}
					
					}//while loop
				}// loop==2
				
			}
			
			
			}
			
			
			break;
		
		default: 
			LibrarianOptions();
			
		}
		
	}

}
