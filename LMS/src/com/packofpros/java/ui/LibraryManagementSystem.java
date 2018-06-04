package com.packofpros.java.ui;

//+++++++++++++++++++++++++++++++++++++++++

// Code written by Amir Poost Pardaz

//+++++++++++++++++++++++++++++++++++++++++
import java.util.Scanner;

public class LibraryManagementSystem {

	public static void main(String[] args) {
		LibraryManagementSystem lms=new LibraryManagementSystem();
		lms.StartApplication();
		
	}
	
	public void StartApplication()
	{
		
		System.out.println("Welcome to the Pack of Pros Library Management System. Which category of a user are you");
		System.out.println("1> Librarian");
		System.out.println("2> Administrator");
		System.out.println("3> Borrower");
		
		System.out.println("Enter 1,2 or 3 as your choice ");
		Scanner scan=new Scanner(System.in);
		int input=Integer.parseInt(scan.nextLine());
		
		switch(input)
		{
		   
		   case 1 :
		      LibrarianMenu lbm=new LibrarianMenu();
		      lbm.LibrarianOptions();
		      break; 
		   
		   case 2 :
			   	AdminMenu am= new AdminMenu();
			   	am.AdminOptions();
		      break; 
		      
		   case 3 :
			      BorrowerMenu brm=new BorrowerMenu();
			      brm.BorrowerOptions();
			      break; 
		   
		   
		   default : 
		      System.out.println("Incorrect value Enter your choice again");
		      LibraryManagementSystem lms=new LibraryManagementSystem();
			  lms.StartApplication();
		}
		
	}

}
