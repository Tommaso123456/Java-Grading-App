package IA;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ClassView extends JFrame {

	private JPanel contentPane;

	public ClassView(Teacher CurrentTeacher) {
		
		
		CSVEditor.GetClassesWithTeacher(CurrentTeacher);
		
		setSize(500, 500);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		ArrayList<Class> Classes = CSVEditor.GetClassesWithTeacher(CurrentTeacher);
		
		int ClassNum = Classes.size();
		String[] ClassesForDropdown = new String[ClassNum]; //CSVEditor.getClassesWithId;
		for (int i = 0; i < ClassNum; i++) {
			ClassesForDropdown[i] = Classes.get(i).getClassName();
			
		}
		
		
		JComboBox ClassDropdown = new JComboBox(ClassesForDropdown);
		ClassDropdown.setForeground(Color.red);
		
		
		
		JLabel SlctClassLbl = new JLabel("Select class");
		
		JButton ClassButon = new JButton("Open class");
		
		ClassButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
    
			String ClassName = (String)ClassDropdown.getSelectedItem();
			ClassGUI ShowClass = new ClassGUI(ClassName, CurrentTeacher.getName(), CurrentTeacher.getPassword());
			
			}
			
		});
		
		JButton AddClass = new JButton("Add Class");
		AddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddClass newClass = new AddClass(CurrentTeacher);
				setVisible(false);
				dispose();
			}
		});
		
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage back = new LoginPage();
				setVisible(false);
				dispose();
			}
		});
		

		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(SlctClassLbl, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ClassDropdown, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addComponent(LogOutBtn))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(AddClass)
						.addComponent(ClassButon))
					.addGap(82))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ClassDropdown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(SlctClassLbl)
						.addComponent(ClassButon))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(AddClass)
						.addComponent(LogOutBtn))
					.addContainerGap(346, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
