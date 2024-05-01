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
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class AdminView extends JFrame {

	private JPanel contentPane;
	private JTextField AddStudentField;

	public AdminView() {
		setSize(1050, 700);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel AdminViewLbl = new JLabel("Admin View");
		AdminViewLbl.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 30));
		
		String[] Students = CSVEditor.getStudentNames();
		JList StudentList = new JList(Students);
		DefaultListModel dm = new DefaultListModel();
		StudentList.setModel(dm);
		
		for (int i = 0; i < Students.length; i++) {
			dm.add(i, Students[i]);
			
		}
		
		JButton DltStudentBtn = new JButton("Delete Student");
		DltStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String StudentName = "";
                int selectedIndices[] = StudentList.getSelectedIndices();
                for (int j = 0; j < selectedIndices.length; j++) {
                	StudentName = (String) StudentList.getModel().getElementAt(selectedIndices[j]);
                }
                String Filepath = "StudentTest.csv";
                
                CSVEditor.RemoveStudent(StudentName);
                CSVEditor.RemoveStudent(CSVEditor.getStudentIdWithName(StudentName), Filepath);
                CSVEditor.RemoveStudentClass(CSVEditor.getStudentIdWithName(StudentName));
                
                String[] UpdatedStudents = CSVEditor.getStudentNames();
				
				dm.removeAllElements();
				for (int i = 0; i < UpdatedStudents.length; i++) {
					dm.add(i, UpdatedStudents[i]);
				}
			}
		});
		
		AddStudentField = new JTextField();
		AddStudentField.setColumns(10);
		String[] SelectionTypes = {"Alphabetical (A-Z)","Alphabetical (Z-A)"}; 
		JComboBox SortTypeselection = new JComboBox(SelectionTypes);
		
		
		
		JButton AddStudentBtn = new JButton("Add student");
		AddStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String StudentName = AddStudentField.getText();
				CSVEditor.addStudent(StudentName);
				String[] UpdatedStudents = CSVEditor.getStudentNames();
				
				dm.removeAllElements();
				for (int i = 0; i < UpdatedStudents.length; i++) {
					dm.add(i, UpdatedStudents[i]);
				}
			}
		});
		
		JButton CloseBtn = new JButton("Close");
		CloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				setVisible(false);
				dispose();
			}
		});
		
		JButton SortBtn = new JButton("Go");
		SortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lo = 0; int high = Students.length - 1;
				String[] UpdatedList = mergeSort(Students, lo, high);
				String CurrentSortTypes = (String) SortTypeselection.getSelectedItem();
				if (CurrentSortTypes.equals("Alphabetical (A-Z)"))
				{
					dm.removeAllElements();
					for (int i = 0; i < UpdatedList.length; i++) {
						dm.add(i, UpdatedList[i]);
					}
				}
				else if (CurrentSortTypes.equals("Alphabetical (Z-A)"))
				{
					UpdatedList = reverse(UpdatedList, UpdatedList.length);
					dm.removeAllElements();
					for (int i = 0; i < UpdatedList.length; i++) {
						dm.add(i, UpdatedList[i]);
					}
				}
				else {
					
					//output error message
				}
				
				
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(167)
							.addComponent(AdminViewLbl, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(66)
							.addComponent(StudentList, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(6)
								.addComponent(SortTypeselection, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(DltStudentBtn)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(CloseBtn)
											.addGap(24))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(AddStudentField, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
											.addGap(30)
											.addComponent(AddStudentBtn))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(SortBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(216, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(AdminViewLbl, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(StudentList, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(AddStudentField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(AddStudentBtn))
							.addGap(117)
							.addComponent(DltStudentBtn)
							.addGap(122)
							.addComponent(CloseBtn)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(SortTypeselection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(SortBtn))))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
			
	static String[] mergeSort(String[] Arr, int lowerbound, int upperbound)
		{
			if (lowerbound == upperbound) {
				String[] toReturn = { Arr[lowerbound] };
				return toReturn;
			}
			int MidPoint = lowerbound + (upperbound - lowerbound) / 2;
			String[] bottomArray = mergeSort(Arr, lowerbound, MidPoint);
			String[] topArray = mergeSort(Arr, MidPoint + 1, upperbound);
			
			String[] FinalArray = merge(bottomArray, topArray);
			return FinalArray;
		}
			
	static String[] merge(
			        String[] bottomArray, String[] topArray)
			    {
			        int Length1 = bottomArray.length;
			        int Length2 = topArray.length;
			        String[] FinalArray = new String[Length1 + Length2];
			 
			        int index = 0;
			 
			        int i = 0;
			        int j = 0;
			 
			        while (i < Length1 && j < Length2) {
			            if (AlphabetCheck(
			            		bottomArray[i], topArray[j])) {
			 
			            	FinalArray[index] = bottomArray[i];
			                i++;
			                index++;
			            }
			            else {
			            	FinalArray[index] = topArray[j];
			                j++;
			                index++;
			            }
			        }
			        while (i < Length1) {
			        	FinalArray[index] = bottomArray[i];
			            i++;
			            index++;
			        }
			        while (j < Length2) {
			        	FinalArray[index] = topArray[j];
			            j++;
			            index++;
			        }
			        return FinalArray;
			    }		
			
	static String[] reverse(String a[], int n)
    {
        String[] b = new String[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }
  
        // printing the reversed array
        
        return b;
    }
	
	static boolean AlphabetCheck(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        if (str1.compareTo(str2) < 0) {
            return true;
        }
        return false;
	    }
}
