package IA;

import java.util.ArrayList;

public class Person {
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Teacher extends Person{
	private int TeacherId;
	private String password;
	private boolean Admin = false;
	
	private ArrayList<Class> TeacherClasses = new ArrayList<Class>();
	
	public ArrayList<Class> getClassesList(){
		return TeacherClasses;
	}
	public void setClassesList(ArrayList<Class> TeacherClasses){
		this.TeacherClasses = TeacherClasses;
	}
	

	public String getPassword() {
		return password;
	}
	
	public boolean getAdmin() {
		return Admin;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return TeacherId;
	}
	
	public void setId(int Id) {
		
		this.TeacherId = Id;
	}
	
	
}
class Student extends Person{
	private String StudentName;
	private String StudentID;
	private ArrayList<Class> StudentClassList = new ArrayList<Class>();
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getStudentID() {
		return StudentID; 
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public ArrayList<Class> getStudentClassList() {
		return StudentClassList;
	}
	public void setStudentClassList(ArrayList<Class> studentClassList) {
		StudentClassList = studentClassList;
	}
	
	
}


class Admin extends Person {
	private String password;
	private boolean Admin = true;
	private int AdminId;
	public String getPassword() {
		return password;
	}
	
	public boolean getAdmin() {
		return Admin;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return AdminId;
	}
	
	public void setId(int Id) {
		this.AdminId = Id;
	}
	
	
	
}
