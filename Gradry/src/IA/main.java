package IA;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {
 
	public static void main(String args[]) {
		
		init();
		LoginPage loginPage = new LoginPage();
		
	}
	
	public static void init()
	{
		try {
			
			
			
			//entity tables
			CSVEditor.CreateStudentTable();
			CSVEditor.CreateTeacherClass();
			CSVEditor.CreateTests();
			CSVEditor.CreateStudentClass();
			CSVEditor.CreateClassTable();
			CSVEditor.CreateUserTable();

			
			
			

			
			//relations tables
			CSVEditor.CreateTeacherTable();
			CSVEditor.CreateStudentTest();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}


