package IA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.opencsv.CSVWriter;



public class CSVEditor{
	
	private static Scanner x;

	public static void RemoveStudent(String StudentID, String filepath) {
		String tempFile = "temp.csv";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		String StudentIDCur = ""; 
		String TestID = "";
		String TestGrade = "";
		String ClassID = "";
		
		try {
			FileWriter fw = new FileWriter(tempFile, true);

			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			x = new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			while (x.hasNext()) {
				
				StudentIDCur = x.next().replaceAll("^\"|\"$", "");
				
				TestID = x.next().replaceAll("^\"|\"$", "");
				
				TestGrade = x.next().replaceAll("^\"|\"$", "");
				
				ClassID = x.next().replaceAll("^\"|\"$", "");
				
			
			if(!StudentIDCur.equals(StudentID)) {
				pw.println(StudentIDCur + "," + TestID + "," + TestGrade + "," + ClassID);
			}
			}
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filepath); 
			newFile.renameTo(dump);
		}
		catch(Exception e) {
			
		}
		
	}
	
	public static void RemoveStudentClass(String StudentID) {
		String tempFile = "temp.csv";
		File oldFile = new File("StudentClass.csv");
		File newFile = new File(tempFile);
		String ID1 = ""; 
		String Name1 = "";
		try {
			FileWriter fw = new FileWriter(tempFile, true);

			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			x = new Scanner(new File("StudentClass.csv"));
			x.useDelimiter("[,\n]");
			while (x.hasNext()) {
				ID1 = x.next();
				String ID = ID1.replaceAll("^\"|\"$", "");
				Name1 = x.next();
				String Name = Name1.replaceAll("^\"|\"$", "");
				
			if(!ID.equals(StudentID)) {
				pw.println(ID.replaceAll("^\"|\"$", "")  + "," +  Name.replaceAll("^\"|\"$", ""));
			}
			
			}
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File("StudentClass.csv"); 
			newFile.renameTo(dump);
		}
		catch(Exception e) {
			
		}
		
	}
	
	public static void RemoveStudent(String StudentName) {
		String tempFile = "temp.csv";
		File oldFile = new File("Students.csv");
		File newFile = new File(tempFile);
		String ID = ""; 
		String Name1 = "";
		try {
			FileWriter fw = new FileWriter(tempFile, true);

			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			x = new Scanner(new File("Students.csv"));
			x.useDelimiter("[,\n]");
			while (x.hasNext()) {
				ID = x.next();
				Name1 = x.next();
				String Name = Name1.replaceAll("^\"|\"$", "");
				
			if(!Name.equals(StudentName)) {
				pw.println(ID.replaceAll("^\"|\"$", "") + "," + Name1.replaceAll("^\"|\"$", ""));
			}
			
			}
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File("Students.csv"); 
			newFile.renameTo(dump);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void CreateClassTable() throws IOException{
		File f = new File("Classes.csv");
		if (!f.exists()) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("Classes.csv");
				fileWriter.append("ClassID, ClassName, StudentNumber, TeacherID \n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
		
		
	}
	
	public static void CreateStudentTable() throws IOException{
		File f = new File("Students.csv");
		if (!f.exists()) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("Students.csv");
				fileWriter.append("StudentID, StudentName \n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	public static void CreateUserTable() throws IOException{
		File f = new File("Users.csv");
		if (!f.exists()) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("Users.csv");
				fileWriter.append("UserID, Username, Password, Admin \n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	public static void CreateTeacherTable() throws IOException{
		File f = new File("Teachers.csv");
		if (!f.exists()) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("Teachers.csv");
				fileWriter.append("UserID, Username, Password \n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
	}

	public static void CreateTeacherClass() throws IOException{
		File f = new File("TeacherClass.csv");
		if (!f.exists()) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("TeacherClass.csv");
				fileWriter.append("TeacherID,  ClassID\n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	public static void CreateStudentClass() throws IOException{
		File f = new File("StudentClass.csv");
		if (!f.exists()) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("StudentClass.csv");
				fileWriter.append("StudentID, ClassID\n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	public static void AddTeacherClass(String TeacherID, String ClassID) {
		CSVWriter StudentClasswriter;
		try {
			StudentClasswriter = new CSVWriter(new FileWriter("TeacherClass.csv", true));
			BufferedReader reader = null;
			String line = "";
			reader = new BufferedReader(new FileReader("TeacherClass.csv"));
			
			String[] record = {TeacherID, ClassID};
			StudentClasswriter.writeNext(record);
			StudentClasswriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void AddStudentClass(String StudentID, String ClassID) {
		CSVWriter StudentClasswriter;
		try {
			StudentClasswriter = new CSVWriter(new FileWriter("StudentClass.csv", true));
			BufferedReader reader = null;
			String line = "";
			reader = new BufferedReader(new FileReader("StudentClass.csv"));
			
			String[] record = {StudentID, ClassID};
			StudentClasswriter.writeNext(record);
			StudentClasswriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void AddTest(String TestName, String TestDate, String ClassID) {
		CSVWriter TestWriter;
		try {
			TestWriter = new CSVWriter(new FileWriter("Tests.csv", true));
			BufferedReader reader = null;
			String line = "";
			reader = new BufferedReader(new FileReader("Tests.csv"));
			int NextIndex = 1;
			String[][] Tests = GetValues("Tests.csv"); 
			if (Tests.length!=0) {
				NextIndex = (Integer.parseInt(Tests[Tests.length - 1][0].substring(1, Tests[Tests.length - 1][0].length() - 1))) + 1;
			}
			
			String[] record = {String.valueOf(NextIndex) , TestName, String.valueOf(TestDate), ClassID};
			TestWriter.writeNext(record);
			TestWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getClassId(String ClassName) {
		String[][] ClassValues = GetValues("Classes.csv");
		for (int i = 0; i < ClassValues.length; i++) {
			String CurrentName = ClassValues[i][1].replaceAll("^\"|\"$", "");
			if (CurrentName.equals(ClassName)) {
				String ClassID = ClassValues[i][0].replaceAll("\\D", "");
				return ClassID;
			}
		}
		return null;
	}
	
	public static ArrayList<Tests> GetTestsWithClassName(String ClassName) {
		String ClassID = getClassId(ClassName);
		ArrayList<Tests> TestsArray = new ArrayList<Tests>();
		String[][] Testvalues = GetValues("Tests.csv");
		for (int i = 0; i < Testvalues.length; i++){
			if (Testvalues[i][3].replaceAll("^\"|\"$", "").equals(ClassID)) {
				String TestID = Testvalues[i][0].replaceAll("^\"|\"$", "");
				TestsArray.add(InstantiateTest(TestID, i));
			}
		}
		
		return TestsArray;
	}
	
	public static int getClassNum(String ClassID) {
		String[][] Classes = GetValues("Classes.csv");
		
		for (int i = 0; i < Classes.length; i++) {
			for (int j = 0; j < Classes[i].length; j++) {
			}
		}
		for (int i = 0; i <Classes.length; i++) {
			if(Classes[i][0].replaceAll("^\"|\"$", "").equals(ClassID)) {
				String Length = Classes[i][2].replaceAll("^\"|\"$", "");
				return Integer.parseInt(Length);
			}
			
		}
		return -1;
		
		
	}

	public static String getStudentNameWithId(String StudentID) {
		String[][] Students = GetValues("Students.csv");
		for (int i = 0; i <Students.length; i++) {
			if(Students[i][0].replaceAll("^\"|\"$", "").equals(StudentID)) {
				return Students[i][1].replaceAll("^\"|\"$", "");
			}
			
		}
		return null;
		
	}
	
	public static String getStudentIdWithName(String StudentName) {
		String[][] Students = GetValues("Students.csv");
		for (int i = 0; i <Students.length; i++) {
			if(Students[i][1].replaceAll("^\"|\"$", "").equals(StudentName)) {
				return Students[i][0].replaceAll("^\"|\"$", "");
			}
			
		}
		return null;
		
	}
	
	public static String GetTestScore(String StudentID, String TestID) {
		String[][] StudentTest = GetValues("StudentTest.csv");
		for (int i = 0; i<StudentTest.length; i++) {
			if (StudentTest[i][0].replaceAll("^\"|\"$", "").equals(StudentID) && StudentTest[i][1].replaceAll("^\"|\"$", "").equals(TestID)) {
				return StudentTest[i][2].replaceAll("^\"|\"$", "");
			}
		}
		
		return null;
		
	}
	
	public static Tests InstantiateTest(String TestID, int i) {
		
		try {
			Tests InstanceTest = new Tests();
			InstanceTest.setTestID(Integer.parseInt(TestID));
			String[][] TestVal = GetValues("Tests.csv");
			InstanceTest.setTestName(TestVal[i][1].replaceAll("^\"|\"$", "")); 
			String TestDate = TestVal[i][2].replaceAll("^\"|\"$", "");
			Date Date1 =  new SimpleDateFormat ("yyyy.MM.dd").parse(TestDate);
			InstanceTest.setTestDate(Date1);
			InstanceTest.setClassID(Integer.parseInt(TestVal[i][3].replaceAll("^\"|\"$", "")));
			
			return InstanceTest;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	public static void CreateStudentTest() {
		File f = new File("StudentTest.csv");
		if (!f.exists()) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("StudentTest.csv");
				fileWriter.append("StudentID, TestID, TestGrade, ClassID \n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	public static void CreateTests() {
		File f = new File("Tests.csv");
		if (!f.exists()) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("Tests.csv");
				fileWriter.append("TestID,  TestName, TestDate, ClassID \n");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	public static String GetTestIDwithName(String TestName) {
		
		String[][] Tests = GetValues("Tests.csv");
		for (int i = 0; i <Tests.length; i++) {
			if(Tests[i][1].replaceAll("^\"|\"$", "").equals(TestName)) {
				return Tests[i][0].replaceAll("^\"|\"$", "");
			}
			
		}
		return null;
		
	}
	
	public static void addStudent(String StudentName) {
		CSVWriter Studentwriter;
		try {
			Studentwriter = new CSVWriter(new FileWriter("Students.csv", true));
			BufferedReader reader = null;
			String line = "";
			reader = new BufferedReader(new FileReader("Students.csv"));
			int NextIndex = 1;
			String[][] ClassValues = GetValues("Students.csv");
			if (ClassValues.length!=0) {
				NextIndex = (Integer.parseInt(ClassValues[ClassValues.length - 1][0].replaceAll("^\"|\"$", ""))) + 1;
			}
			
			String[] record = {String.valueOf(NextIndex),StudentName};
			Studentwriter.writeNext(record);
			Studentwriter.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String[] getStudentNames() {
		String[][] Students = GetValues("Students.csv");
		String[] toReturn = new String[Students.length];
		for (int i = 0; i < Students.length; i++) {
			
			toReturn[i] = Students[i][1].replaceAll("^\"|\"$", "");
		}
		return toReturn;
		
	}
	
	public static String[] getStudentNameswithclassName(String ClassName) {
		String ClassID = getClassId(ClassName);
		
		String[][] StudentClass = GetValues("StudentClass.csv");
		int count = 0;
		for (int i = 0; i < StudentClass.length; i++) {
			if (StudentClass[i][1].replaceAll("^\"|\"$", "").equals(ClassID)) {
			count++;
			}	
		}
		String[] toReturn = new String[count];
		int toReturnIndex = 0;
		for (int i = 0; i < StudentClass.length; i++) {
			if (StudentClass[i][1].replaceAll("^\"|\"$", "").equals(ClassID)) {
			String StudentId = StudentClass[i][0].replaceAll("^\"|\"$", "");
			toReturn[toReturnIndex] = getStudentNameWithId(StudentId);
			toReturnIndex++;
			}	
		}
		return toReturn;
		
	}
	
	public static void addClass(String ClassName, int StudentNum, int TeacherID) {
		CSVWriter Userwriter;
		try {
			Userwriter = new CSVWriter(new FileWriter("Classes.csv", true));
			CSVWriter Teacherwriter = new CSVWriter(new FileWriter("Classes.csv", true));
			BufferedReader reader = null;
			String line = "";
			reader = new BufferedReader(new FileReader("Classes.csv"));
			int NextIndex = 1;
			String[][] ClassValues = GetValues("Classes.csv");
			if (ClassValues.length!=0) {
				NextIndex = (Integer.parseInt(ClassValues[ClassValues.length - 1][0].substring(1, ClassValues[ClassValues.length - 1][0].length() - 1))) + 1;
			}
			String[] record = {String.valueOf(NextIndex), ClassName, String.valueOf(StudentNum), String.valueOf(TeacherID)};
			Userwriter.writeNext(record);
			Userwriter.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void addStudentTest(String StudentID, String TestID, String TestGrade, String ClassID){
		CSVWriter StudentTestwriter;
		try {
			StudentTestwriter = new CSVWriter(new FileWriter("StudentTest.csv", true));
			
			String[] record = {StudentID, TestID, TestGrade, ClassID};
			StudentTestwriter.writeNext(record);
			StudentTestwriter.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//Add Teacher
	public static void addTeacher(String Username, String Password) throws Exception {
		CSVWriter Userwriter = new CSVWriter(new FileWriter("Users.csv", true));
		CSVWriter Teacherwriter = new CSVWriter(new FileWriter("Teachers.csv", true));
		List<Teacher> TeacherList = new ArrayList<Teacher>();
		BufferedReader reader = null;
		String line = "";
		reader = new BufferedReader(new FileReader("Users.csv"));
		int counter = 0;
		while((line = reader.readLine()) != null) {
			counter++;
		}
		int Id = counter;
		
		
		Teacher newTeach = new Teacher();
		newTeach.setName(Username);
		newTeach.setPassword(Password);
		newTeach.setId(Id);
		
		
		
		TeacherList.add(newTeach);
		String[] record = {String.valueOf(Id), Username, Password, "FALSE"};
		String[] teacherRecord = {String.valueOf(Id), Username, Password};
	
		Userwriter.writeNext(record);
		Userwriter.close();
		
		Teacherwriter.writeNext(teacherRecord);
		Teacherwriter.close();
	}
	
	//Add Admin
	public static void addAdmin(String Username, String Password) throws Exception{
		CSVWriter writer = new CSVWriter(new FileWriter("Users.csv", true));
		List<Admin> AdminList = new ArrayList<Admin>();
		BufferedReader reader = null;
		String line = "";
		reader = new BufferedReader(new FileReader("Users.csv"));
		int counter = 0;
		while((line = reader.readLine()) != null) {
			counter++;
		}
		int Id = counter;
		
		
		Admin newAdmin = new Admin();
		newAdmin.setName(Username);
		newAdmin.setPassword(Password);
		newAdmin.setId(Id);
		
		AdminList.add(newAdmin);
		String[] record = {String.valueOf(Id), Username, Password, "TRUE"};
	
		writer.writeNext(record);
		writer.close();
		
	}
	
	//Check if login
	public static Teacher LoginAction(String Username, String Password) {
		String[][] Parameters = GetValues("Users.csv");
			for (int row = 0; row < Parameters.length; row++){
				if (Parameters[row][1].equals('"' + Username + '"')) {
					if (Parameters[row][2].equals('"' + Password + '"')) {
						if (Parameters[row][3].equals('"' + "FALSE" + '"')) {
						//teacher login
							Teacher CurrentTeacher = new Teacher();
							CurrentTeacher.setName(Username);
							CurrentTeacher.setPassword(Password);
							int TeacherID =Integer.valueOf(Parameters[row][0].replaceAll("\\D", ""));
							CurrentTeacher.setId(TeacherID);
							return CurrentTeacher;
							
						}
						
						else {
							AdminView adminView = new AdminView();
						}
						
					}
				}
			}
			
			return null;		
	
		
	}
	
	//Get username, password and admin
	public static String[][] GetValues(String CSVName){
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		
		
		try {
			String line = "";
			reader1 = new BufferedReader(new FileReader(CSVName));
			int vertical = 0;
			int horitzontal = 0;
			while((line = reader1.readLine()) != null) {
				String[] fields = line.split(",");
				horitzontal = fields.length;
				vertical++;
				
			}
			line = "";
			String[][] Parameters;
			if (vertical == 0) {
				 Parameters = new String[vertical][horitzontal];
			}
			else {

				Parameters = new String[vertical - 1][horitzontal];
			}
			int a = 0;
			reader2 = new BufferedReader(new FileReader(CSVName));
			while((line = reader2.readLine()) != null) {
				String[] fields = line.split(",");
				for (int i = 0; i< fields.length;i++) {
					if (a!=0) {
						Parameters[a-1][i] = fields[i];
					}
					
					}
				
				if (a == vertical - 1) {
					return Parameters;
				}
				a++;
			}
			
			return Parameters;
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	public static ArrayList<Class> GetClassesWithTeacher(Teacher t){
			ArrayList<Class> TeacherClass = new ArrayList<Class>();
			String[][] Parameters = GetValues("TeacherClass.csv");
			
			for (int i = 0; i < Parameters.length; i++) {
				String CurrentTeacherID = Parameters[i][0].replaceAll("\\D", "");
				if (CurrentTeacherID.equals(String.valueOf(t.getId()))) {
					String ClassID = Parameters[i][1].replaceAll("\\D", "");
					TeacherClass.add(createClasswithID(ClassID));
				}
			}
			return TeacherClass;
	}
	
	public static Class createClasswithID(String ClassID) {
		Class current = new Class();

		String[][] ClassValues = GetValues("Classes.csv");
		
		
		
		for (int i = 0; i < ClassValues.length; i++) {
			
			
			String CurrentClassID = ClassValues[i][0].replaceAll("\\D", "");
			if (CurrentClassID.equals(ClassID)) {
				current.setClassName(ClassValues[i][1].replaceAll("^\"|\"$", "")); //wrong regex
				current.setStudentNum(Integer.parseInt(ClassValues[i][2].replaceAll("\\D", "")));
				current.setTeacherId(Integer.parseInt(ClassValues[i][3].replaceAll("\\D", "")));
				return current;
			}
		}
		return current;
		
		
	}
	
	
}

