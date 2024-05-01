package IA;

import java.util.ArrayList;

public class Class {
	
	private String ClassName;
	private int StudentNum;
	private int TeacherId;
	private ArrayList<Student> StudentList = new ArrayList<Student>();
	
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public int getStudentNum() {
		return StudentNum;
	}
	public void setStudentNum(int studentNum) {
		StudentNum = studentNum;
	}
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}
	public ArrayList<Student> getStudentList() {
		return StudentList;
	}
	public void setStudentList(ArrayList<Student> studentList) {
		StudentList = studentList;
	}
	
	
	
	
	
}
