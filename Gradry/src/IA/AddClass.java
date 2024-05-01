package IA;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.*;

public class AddClass extends JFrame {

	private JPanel contentPane;
	private JTextField ClassName; 
	private JTable AlrInClassTable;


	public AddClass(Teacher CurrentTeacher) { 
		
		
		
		setSize(500, 500);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		ClassName = new JTextField();
		ClassName.setColumns(10);
		

		JLabel ClassNameLbl = new JLabel("ClassName");
		
		JLabel StudentsLabel = new JLabel("Students");
		
		String[] Students = CSVEditor.getStudentNames();
		
		
		
		ArrayList<String> StudentsSelected = new ArrayList<String>();
		
		
		JList StudentList = new JList(Students);
		JButton AddStudent = new JButton("AddStudent");
		DefaultListModel dm = new DefaultListModel();
		StudentList.setModel(dm);
		
		JList AddedStudentsList = new JList();
		DefaultListModel dm2 = new DefaultListModel();
		AddedStudentsList.setModel(dm2);
		
		for (int i = 0; i <Students.length; i++) {
			dm.add(i, (String)Students[i]);
		}
		AddStudent.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String selectedElem = "";
		                int selectedIndices[] = StudentList.getSelectedIndices();
		                for (int j = 0; j < selectedIndices.length; j++) {
		                    selectedElem = (String) StudentList.getModel().getElementAt(selectedIndices[j]);
		                }
		            int index = StudentList.getSelectedIndex();
		            if (index != -1) {
		            	int indextoAdd = dm2.getSize();
		            	dm2.add(0, StudentList.getSelectedValue());
			            dm.remove(index);
		            }
		            
		            
		            StudentsSelected.add(selectedElem);
		            }
			
		});
		
		JButton SubmitButton = new JButton("AddClass");
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ClassNameFinal = ClassName.getText();
				int SizeInt = StudentsSelected.size();
				int TeacherID = CurrentTeacher.getId();
				CSVEditor.addClass(ClassNameFinal, SizeInt, TeacherID);
				String ClassID = CSVEditor.getClassId(ClassNameFinal);
				
				for (int i = 0; i < StudentsSelected.size(); i++) {
					String CurStudentID = CSVEditor.getStudentIdWithName(StudentsSelected.get(i));
					CSVEditor.AddStudentClass(CurStudentID, ClassID);
					
				}
				
				CSVEditor.AddTeacherClass(String.valueOf(CurrentTeacher.getId()), ClassID);
				ClassView classView = new ClassView(CurrentTeacher);
				setVisible(false);
				dispose();
				
				
			}
		});
		
		
		
		
		

	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(194)
					.addComponent(SubmitButton)
					.addContainerGap(193, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(ClassNameLbl)
								.addComponent(StudentsLabel))
							.addGap(18)
							.addComponent(ClassName, 186, 186, 186))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(172)
									.addComponent(AddStudent))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(32)
									.addComponent(StudentList, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(AddedStudentsList, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
					.addGap(182))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ClassNameLbl)
						.addComponent(ClassName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(StudentsLabel)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(AddStudent))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(AddedStudentsList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(StudentList, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))))
					.addGap(72)
					.addComponent(SubmitButton)
					.addGap(33))
		);
		
		
		

		contentPane.setLayout(gl_contentPane);
		
		
		
	}
}

