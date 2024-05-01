package IA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddTest extends JFrame {

	private JPanel contentPane;
	private JTextField NewTestName;
	private JLabel TestDateLabel;
	private JTextField TestDateInput;
	private JLabel DateFormatLabel;
	private JList StudentSelector;

	
	public AddTest(String ClassName, String Name, String Pass) {
		
		setSize(1050, 700);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		JLabel AddTestLbL = new JLabel("Add New Test");
		AddTestLbL.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		
		NewTestName = new JTextField();
		NewTestName.setColumns(10);
		
		JLabel NewTestNameLabel = new JLabel("Test name");
		
		TestDateLabel = new JLabel("Test date:");
		
		TestDateInput = new JTextField();
		TestDateInput.setColumns(10);
		
		DateFormatLabel = new JLabel("(yyyy.MM.dd)");
		
		String[] Students = CSVEditor.getStudentNameswithclassName(ClassName);
		JList StudentSelector = new JList(Students);
		DefaultListModel dm = new DefaultListModel();
		StudentSelector.setModel(dm);
		
		for (int i = 0; i < Students.length; i++) {
			dm.add(i, Students[i]);
		}
		SpinnerModel sm = new SpinnerNumberModel(50, 0, 100, 1);
		JSpinner StudentGradeSpinner = new JSpinner(sm);

		
		JLabel StudentLabel = new JLabel("Student:");
		
		JLabel GradeLabel = new JLabel("Grade");
		
		
		String[][] StudentGrades = new String[Students.length][2];
		int curStud = 0;
		
		JButton AddGradeBtn = new JButton("Add grade");
		AddGradeBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {

				String selectedElem = "";
                int selectedIndices[] = StudentSelector.getSelectedIndices();
                for (int j = 0; j < selectedIndices.length; j++) {
                    selectedElem = (String) StudentSelector.getModel().getElementAt(selectedIndices[j]);
                }
				 String StudentName = selectedElem;
				 int Grade = (Integer) StudentGradeSpinner.getValue();
				 for (int i = 0; i < StudentGrades.length; i++) {
					 if (StudentGrades[i][0] == null) {
						 StudentGrades[i][0] = StudentName;
						 StudentGrades[i][1] = String.valueOf(Grade);
						 break;
					 }
					 
				 }
				 int index = StudentSelector.getSelectedIndex();
		            if (index != -1) {
			            dm.remove(index);
		            }
				 
			}
		});
		JLabel ErrorMsg = new JLabel("Make sure you have added a grade for each student");
		ErrorMsg.setForeground(new Color(237, 10, 7));
		ErrorMsg.setVisible(false);
		
		JButton AddTestBtn = new JButton("Add test");
		AddTestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if (dm.size() == 0) {
				String TestName = NewTestName.getText();
				CSVEditor.AddTest(TestName, TestDateInput.getText(), CSVEditor.getClassId(ClassName));
				String TestID = CSVEditor.GetTestIDwithName(TestName);
				for (int i = 0; i<StudentGrades.length; i++) {
					CSVEditor.addStudentTest(CSVEditor.getStudentIdWithName(StudentGrades[i][0]), TestID, StudentGrades[i][1], CSVEditor.getClassId(ClassName));
				}
				ClassGUI classGui = new ClassGUI(ClassName, Name, Pass);
				setVisible(false);
				dispose();
			}
			else {
				ErrorMsg.setVisible(true);
			}
			
			}
		});
		
		
		
	
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(ErrorMsg, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(86)
									.addComponent(AddTestLbL, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(StudentLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(32)
											.addComponent(StudentSelector, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(GradeLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(StudentGradeSpinner, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(AddGradeBtn))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(TestDateLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(NewTestNameLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addGap(42)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(TestDateInput)
												.addComponent(NewTestName, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(DateFormatLabel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(AddTestBtn)))
					.addGap(444))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addComponent(AddTestLbL, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(NewTestNameLabel)
								.addComponent(NewTestName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(TestDateLabel)
								.addComponent(TestDateInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(DateFormatLabel))
							.addGap(80)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(StudentSelector, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
								.addComponent(StudentLabel)
								.addComponent(GradeLabel)
								.addComponent(StudentGradeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(AddGradeBtn))
							.addGap(44))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(514, Short.MAX_VALUE)
							.addComponent(AddTestBtn)
							.addGap(42)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ErrorMsg)
					.addGap(62))
		);
		String TestName = NewTestName.getText();
		contentPane.setLayout(gl_contentPane);
		
		//TestName
		//TestDate
		//StudentGrade
	}
}
