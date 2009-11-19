package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import core.Element;
import core.JointMassDistribution;
import core.MassDistribution;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class NewSwingApp extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private JMenuItem deleteMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JTable resultTable;
	private JTabbedPane resultTab;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu jMenu4;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	private ArrayList<MassDistribution> jointResults;
	private JTabbedPane inputTab;
	private ArrayList<MassDistribution> input;
	private JTable inputTable;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewSwingApp inst = new NewSwingApp();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public NewSwingApp() {
		super();
		// initGUI();
	}

	private void setResultsTable() {
		int rows = jointResults.size();
		int column = 10;
		// int column = ((MassDistribution) jointResults.get(0)).getElements()
		// .size();

		String[][] data = new String[rows][column];
		String[] columnTitle = new String[column];

		for (int i = 0; i < rows; i++) {
			JointMassDistribution massDistribution = (JointMassDistribution) jointResults
					.get(i);
			data[i][0] = massDistribution.getOperator();

			for (int j = 0; j < massDistribution.getFocalElements().size(); j++) {
				Element element = (Element) massDistribution.getFocalElements().get(
						j);
				// data[i][j] = Double.toString(element.getBpa());
				data[i][j + 1] = element.toString();

				columnTitle[j + 1] = element.toString();
			}

		}

		TableModel resultTableModel = new DefaultTableModel(data, columnTitle);
		resultTable = new JTable();
		resultTab.addTab("Results", null, getResultTable(), null);
		resultTable.setModel(resultTableModel);
		resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	private void setInputTable() {
		int rows = input.size();
		int column = 10; // ((MassDistribution)
		// input.get(0)).getElements().size();

		String[][] data = new String[rows][column];
		String[] columnTitle = new String[column];

		for (int i = 0; i < rows; i++) {
			MassDistribution massDistribution = (MassDistribution) input.get(i);
			data[i][0] = "" + (i + 1);
			for (int j = 0; j < massDistribution.getFocalElements().size(); j++) {

				Element element = (Element) massDistribution.getFocalElements().get(
						j);
				// data[i][j] = Double.toString(element.getBpa());
				data[i][j + 1] = element.toString();

				columnTitle[j + 1] = element.toString();
			}

		}

		TableModel inputTableModel = new DefaultTableModel(data, columnTitle);
		inputTable = new JTable();
		inputTab.addTab("Input", null, getInputTable(), null);
		inputTable.setModel(inputTableModel);

	}

	public void initGUI() {

		setLocationRelativeTo(null);
		setVisible(true);
		try {
			{
				resultTab = new JTabbedPane();

				getContentPane().add(resultTab, BorderLayout.EAST);
				setResultsTable();
			}

			{
				inputTab = new JTabbedPane();
				getContentPane().add(inputTab, BorderLayout.WEST);
				setInputTable();
			}
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("File");
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("New");
					}
					{
						openFileMenuItem = new JMenuItem();
						jMenu3.add(openFileMenuItem);
						openFileMenuItem.setText("Open");
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("Save");
					}
					{
						saveAsMenuItem = new JMenuItem();
						jMenu3.add(saveAsMenuItem);
						saveAsMenuItem.setText("Save As ...");
					}
					{
						closeFileMenuItem = new JMenuItem();
						jMenu3.add(closeFileMenuItem);
						closeFileMenuItem.setText("Close");
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("Exit");
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("Edit");
					{
						cutMenuItem = new JMenuItem();
						jMenu4.add(cutMenuItem);
						cutMenuItem.setText("Cut");
					}
					{
						copyMenuItem = new JMenuItem();
						jMenu4.add(copyMenuItem);
						copyMenuItem.setText("Copy");
					}
					{
						pasteMenuItem = new JMenuItem();
						jMenu4.add(pasteMenuItem);
						pasteMenuItem.setText("Paste");
					}
					{
						jSeparator1 = new JSeparator();
						jMenu4.add(jSeparator1);
					}
					{
						deleteMenuItem = new JMenuItem();
						jMenu4.add(deleteMenuItem);
						deleteMenuItem.setText("Delete");
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("Help");
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("Help");
					}
				}
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MassDistribution> getJointResults() {
		return jointResults;
	}

	public void setJointResults(ArrayList<MassDistribution> jointResults) {
		this.jointResults = jointResults;
	}

	public JTable getResultTable() {
		return resultTable;
	}

	/**
	 * @return the input
	 */
	public ArrayList<MassDistribution> getInput() {
		return input;
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(ArrayList<MassDistribution> input) {
		this.input = input;
	}

	/**
	 * @return the inputTable
	 */
	public JTable getInputTable() {
		return inputTable;
	}

	/**
	 * @param inputTable
	 *            the inputTable to set
	 */
	public void setInputTable(JTable inputTable) {
		this.inputTable = inputTable;
	}
}
