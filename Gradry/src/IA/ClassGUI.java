package IA;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

public class ClassGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public ClassGUI(String Classname, String Name, String Pass) {
		
		setSize(1050, 700);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		
		//Create table
		String[] Students = CSVEditor.getStudentNameswithclassName(Classname);


		ArrayList<Tests> TestsArray = CSVEditor.GetTestsWithClassName(Classname);
		
		
		String[] Header = new String[TestsArray.size() + 2];
		String[] TestNames = new String[TestsArray.size()];
		
		Header[0] = "Student name";
		for (int i = 0; i < TestsArray.size(); i++) {
			TestNames[i] = TestsArray.get(i).getTestName();
			Header[i + 1] = TestsArray.get(i).getTestName();				
		}
		
		Header[TestsArray.size() + 1] = "Student Average";
		
		
		String[][] StudentTest = CSVEditor.GetValues("StudentTest.csv");
		TestsArray = CSVEditor.GetTestsWithClassName(Classname);
		
		int verticallength = CSVEditor.getClassNum(CSVEditor.getClassId(Classname));
	
		int horizontallength = TestsArray.size()+2;
		
		String[][] Data = new String[verticallength][horizontallength];
		
		String[][] StudentClassVals = CSVEditor.GetValues("StudentClass.csv");
		
		
		
		int row = 0;
		for (int i = 0; i < verticallength; i++) {
			
			Data[row][0] = Students[i];
			row++;
		}
		
		
		
		
		for (int i = 0; i < verticallength; i++) {
			int count = 0;
			int total = 0;
			int avg;
			for (int j = 1; j < horizontallength; j++) {
				if (j!=horizontallength-1) {
					String StudentID = CSVEditor.getStudentIdWithName(Data[i][0]);
					
					String TestID = CSVEditor.GetTestIDwithName(Header[j]);	
					
					Data[i][j] = CSVEditor.GetTestScore(StudentID, TestID);
					if (CSVEditor.GetTestScore(StudentID, TestID) != null) {
						total += Integer.parseInt(CSVEditor.GetTestScore(StudentID, TestID));
						count++;
					}
				}
				else {
					if (total > 0) {
						avg = total/count;
					}
					else {
						avg = 0;
					}
					
					Data[i][j] = String.valueOf(avg);
				}
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel ClassName = new JLabel(Classname);
		
		JButton AddTestButton = new JButton("Add Test");
		AddTestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTest addTest = new AddTest(Classname, Name, Pass);
				setVisible(false);
				dispose();
			}
		});
		
		
		
		JList StudentList = new JList(Students);
		DefaultListModel dm = new DefaultListModel();
		StudentList.setModel(dm);
		
		for (int i = 0; i < Students.length; i++) {
			dm.add(i, Students[i]);
		}
		
		JLabel ShowStudLbL = new JLabel("Show student graph:");
		
		String[] GraphTypes = {"Line chart", "Bar chart", "Pie chart"};
		JComboBox GraphType = new JComboBox(GraphTypes);
		
		JButton ShowStudentGraphBtn = new JButton("Show student's graph");
		ShowStudentGraphBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedElem = "";
                int selectedIndices[] = StudentList.getSelectedIndices();
                for (int j = 0; j < selectedIndices.length; j++) {
                    selectedElem = (String) StudentList.getModel().getElementAt(selectedIndices[j]);
                }
                
				String StudentName = selectedElem;
				String StudentID = CSVEditor.getStudentIdWithName(StudentName);
				String[] Testname = TestNames;
				
				int[] TestScores = new int[TestNames.length];
				
				for(int i = 0; i < TestNames.length; i++) {
					
					String TestID = CSVEditor.GetTestIDwithName(TestNames[i]);
					int TestScore = Integer.parseInt(CSVEditor.GetTestScore(StudentID, TestID));
					TestScores[i] = TestScore;
				}
						
				String SelectedGraphType = (String)GraphType.getSelectedItem();
				if (SelectedGraphType.equals("Line chart")) {
					ShowStudentLineGraph chart = new ShowStudentLineGraph(Testname, TestScores, StudentName);
					chart.pack();
					chart.setVisible(true);
				}
				
				if (SelectedGraphType.equals("Bar chart")) {
					ShowStudentBarGraph chart = new ShowStudentBarGraph(Testname, TestScores, StudentName);
					chart.pack();
					chart.setVisible(true);
				}
				
				if (SelectedGraphType.equals("Pie chart")) {
					String Title = StudentName + "'s performance";
					DefaultPieDataset dataset = new DefaultPieDataset();
					for (int i = 0; i < TestNames.length; i++) {
						dataset.setValue(TestNames[i], TestScores[i]);
					}
					JFreeChart pieChart = ChartFactory.createPieChart(
							Title,
							dataset,
							true, true, true);
					
					PiePlot P=(PiePlot)pieChart.getPlot();
					ChartFrame frame = new ChartFrame("Pie Chart", pieChart);
					frame.setVisible(true);
					frame.setSize(500, 500);
					
				}
				
				
				
				
				
				
			}
		});
		
		JButton BackBtn = new JButton("Back");
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teacher CurrentTeacher = CSVEditor.LoginAction(Name, Pass);
				ClassView back = new ClassView(CurrentTeacher);
				setVisible(false);
				dispose();
			}
		});
		
		JButton LogOutBtn = new JButton("Log out");
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage LogOut = new LoginPage();
				setVisible(false);
				dispose();
			}
		});
		
		
		
		
		
	
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(160)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(ShowStudLbL, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addComponent(StudentList, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(GraphType, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(ShowStudentGraphBtn))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(218)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(BackBtn)
										.addComponent(AddTestButton)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(ClassName, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(LogOutBtn))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(179, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ClassName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(LogOutBtn))
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(72)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(AddTestButton)
								.addComponent(ShowStudLbL))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(StudentList, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(BackBtn))
							.addContainerGap(62, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(GraphType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(ShowStudentGraphBtn))
							.addGap(163))))
		);
		
		
		JTable table_1 = new JTable(Data, Header);
		scrollPane.setViewportView(table_1);
		table_1.setFillsViewportHeight(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		contentPane.setLayout(gl_contentPane);
	}
}
