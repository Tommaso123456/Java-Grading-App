package IA;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;



public class ShowStudentLineGraph extends JFrame {

	private JPanel contentPane;

	
	public ShowStudentLineGraph(String[] TestNames, int[] StudentTestScores, String StudentName) {
		setSize(1050, 700);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		String Title = StudentName + "'s performance";
		JFreeChart lineChart = ChartFactory.createLineChart(
				Title,
				"Test", "Grade %",
				createDataset(TestNames, StudentTestScores),
				PlotOrientation.VERTICAL,
				true, true, false);
		ChartPanel chartPanel = new ChartPanel(lineChart);	
		
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		
		setContentPane(chartPanel);	
	}
	
	private DefaultCategoryDataset createDataset(String[] TestNames, int[] StudentScores) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < TestNames.length; i++) {
			dataset.addValue(StudentScores[i], "Student performance", TestNames[i]);
		}
		return dataset;
		
	}
	
	
	

}
