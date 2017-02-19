package slotMachine;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class MyPieChart {
	/*Declaring JPanel object*/
	private JPanel panel;

	/*Declaring the constructor*/
	public MyPieChart(int[] array, String str1, String str2) {
		this.panel = createDemoPanel(array, str1, str2);
	}

	/*Getter method for JPanel object*/
	public JPanel getPanel() {
		return panel;
	}

	/*Creates and returns DefaultPieDataset object*/
	private static PieDataset createDataset(int[] array, String str1, String str2) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(str1, array[0]);
		dataset.setValue(str2, array[1]);
		return dataset;
	}

	/*creates and returns JFreeChart object*/
	private static JFreeChart createChart(PieDataset dataset) {
		return ChartFactory.createPieChart("Slot Machine Statistics", dataset, true, true, false);
	}

	/*creates and returns ChartPanel object*/
	public static JPanel createDemoPanel(int[] array, String str1, String str2) {
		JFreeChart chart = createChart(createDataset(array, str1, str2));
		ChartPanel cPanel = new ChartPanel(chart);
		cPanel.setPreferredSize(new Dimension(300, 200));
		return cPanel;
	}
}