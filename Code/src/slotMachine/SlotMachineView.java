package slotMachine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class SlotMachineView extends JFrame {
	private static final long serialVersionUID = 8882467269092855088L;

	/* Defining labels */
	private JLabel lblBetAmountDisplay;
	private JLabel lblBetAmount;
	private JLabel lblCreditDisplay;
	private JLabel lblCredit;
	private JLabel lblErrMsg;

	/* Defining labels for 3 reels */
	private JLabel lblReel1;
	private JLabel lblReel2;
	private JLabel lblReel3;

	/* Defining buttons */
	private JButton btnAddCoin;
	private JButton btnBetOne;
	private JButton btnBetMax;
	private JButton btnSpin;
	private JButton btnReset;
	private JButton btnStatistics;
	private JButton btnPrintStat; 

	/* Defining borders for selected and unselected stages of the reel labels */
	private Border unselectedBorder = new MatteBorder(5, 5, 5, 5, Color.BLACK);
	private Border selectedBorder = new CompoundBorder(new LineBorder(new Color(255, 255, 0, 191), 3),
			new EmptyBorder(2, 2, 2, 2));

	/*
	 * Declaring the constructor and calling the relevant methods to execute. In
	 * here the main JFrame is created
	 */
	public SlotMachineView() {
		
		/* Initializing labels */
		lblBetAmountDisplay = new JLabel("Bet Area : ");
		lblBetAmount = new JLabel();
		lblCreditDisplay = new JLabel("Credit Area : ");
		lblCredit = new JLabel();
		lblErrMsg = new JLabel("ERR");

		/* Initializing labels for 3 reels */
		lblReel1 = new JLabel();
		lblReel2 = new JLabel();
		lblReel3 = new JLabel();

		/* Initializing buttons */
		btnAddCoin = new JButton("Add Coin");
		btnBetOne = new JButton("Bet One");
		btnBetMax = new JButton("Bet Max");
		btnSpin = new JButton("SPIN");
		btnReset = new JButton("RESET");
		btnStatistics = new JButton("Statistics");
		
		/* Defining main JPanel to hold sub JPanel components */
		JPanel mainPanel = new JPanel();
		createView(mainPanel);

		this.setTitle("Slot Machine Game");
		this.add(mainPanel);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Adding a window listener to display a confirmation box when clicking
		// exit button
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				confirmAndExit();
			}
		});
	}

	/*
	 * Setter method for lblErrMsg label. This lblErrMsg label displays all the
	 * related messages
	 */
	public void setErrMsg(String message) {
		this.lblErrMsg.setText(message);
	}

	/* Getter methods for Reel labels */
	public JLabel getLblReel1() {
		return this.lblReel1;
	}

	public JLabel getLblReel2() {
		return this.lblReel2;
	}

	public JLabel getLblReel3() {
		return this.lblReel3;
	}
	
	/* Defining getter methods for buttons */
	public JButton getBtnAddCoin() {
		return btnAddCoin;
	}

	public JButton getBtnBetOne() {
		return btnBetOne;
	}

	public JButton getBtnBetMax() {
		return btnBetMax;
	}

	public JButton getBtnSpin() {
		return btnSpin;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public JButton getBtnStatistics() {
		return btnStatistics;
	}

	public JButton getBtnPrintStat() {
		return btnPrintStat;
	}

	/* Defining getter methods for Borders */
	public Border getUnselectedBorder() {
		return unselectedBorder;
	}

	public Border getSelectedBorder() {
		return selectedBorder;
	}

	/* Getter and Setter methods for lblBetAmount and lblCredit labels */
	public int getBetAmount() {
		return Integer.parseInt(lblBetAmount.getText());
	}

	public void setBetAmount(int betAmount) {
		this.lblBetAmount.setText(Integer.toString(betAmount));
	}

	public int getCredit() {
		return Integer.parseInt(lblCredit.getText());
	}

	public void setCredit(int score) {
		this.lblCredit.setText(Integer.toString(score));
	}
	
	/* Creating sub panels and adding components to them */
	public void createView(JPanel mainPanel) {
		
		/* Defining JPanels to hold components */
		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();

		String url = "Images/redseven.png";
		ImageIcon imgObj = new ImageIcon(
				new ImageIcon(url).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		lblReel1.setIcon(imgObj);
		lblReel2.setIcon(imgObj);
		lblReel3.setIcon(imgObj);

		p0.setLayout(new GridLayout(2, 1));
		lblErrMsg.setHorizontalAlignment(JLabel.CENTER);
		p0.add(lblErrMsg);

		p1.setLayout(new GridLayout(1, 4));
		lblBetAmountDisplay.setHorizontalAlignment(JLabel.RIGHT);
		lblCreditDisplay.setHorizontalAlignment(JLabel.RIGHT);

		p1.add(lblBetAmountDisplay);
		p1.add(lblBetAmount);
		p1.add(lblCreditDisplay);
		p1.add(lblCredit);
		p0.add(p1);

		p2.setLayout(new GridLayout(1, 3));

		lblReel1.setHorizontalAlignment(JLabel.CENTER);
		lblReel2.setHorizontalAlignment(JLabel.CENTER);
		lblReel3.setHorizontalAlignment(JLabel.CENTER);

		lblReel1.setBorder(unselectedBorder);
		lblReel2.setBorder(unselectedBorder);
		lblReel3.setBorder(unselectedBorder);

		p2.add(lblReel1);
		p2.add(lblReel2);
		p2.add(lblReel3);
		p2.setBorder(new EmptyBorder(10, 10, 10, 10));

		p3.add(btnAddCoin);
		p3.add(btnBetOne);
		p3.add(btnBetMax);
		p3.add(btnStatistics);
		p3.setBorder(new EmptyBorder(10, 10, 10, 10));

		p4.add(btnSpin);
		p4.add(btnReset);
		p3.add(p4);

		mainPanel.setLayout(new GridLayout(3, 1));
		mainPanel.add(p0);
		mainPanel.add(p2);
		mainPanel.add(p3);
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
	}

	/*
	 * Disables or enables all the buttons according to the passing boolean flag
	 * value
	 */
	public void buttonControl(boolean flag) {
		btnAddCoin.setEnabled(flag);
		btnBetOne.setEnabled(flag);
		btnBetMax.setEnabled(flag);
		btnSpin.setEnabled(flag);
		btnReset.setEnabled(flag);
		btnStatistics.setEnabled(flag);
	}

	/* Confirmation box on exit */
	public void confirmAndExit() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Please confirm",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/*
	 * Displays the statistics which are Wins,Loses and Average credits netted
	 * per game in a separate Window. To represent the this statistical data I
	 * used a separate JFrame which pop up in the middle of the main JFrame. I
	 * thought it is better to have a separate JFrame because I can place
	 * labels,buttons and charts according to my will. To represent the wins and
	 * loses I used a pie chart because at a glance the user can get a clear
	 * picture of his performance by looking at the chart
	 */
	public void statWindow(int[] statisticsArray) {
		JFrame statFrame = new JFrame();
		JPanel mPanel = new JPanel();

		MyPieChart pChart = new MyPieChart(statisticsArray, "Wins", "Loses");

		String avgCredits = "";
		try {
			avgCredits = Integer.toString((statisticsArray[2]) / statisticsArray[3]);
		} catch (ArithmeticException e) {
			avgCredits = "0";
		}

		JLabel lblWinTitle = new JLabel("Wins:  " + statisticsArray[0]);
		JLabel lblLossTitle = new JLabel("Loses:  " + statisticsArray[1]);
		JLabel lblAvgCreditTitle = new JLabel("Average credits netted per game:  " + avgCredits);

		btnPrintStat = new JButton("Save Statistics");

		lblWinTitle.setHorizontalAlignment(JLabel.CENTER);
		lblLossTitle.setHorizontalAlignment(JLabel.CENTER);
		lblAvgCreditTitle.setHorizontalAlignment(JLabel.CENTER);

		JPanel panel1 = new JPanel();
		panel1.add(btnPrintStat);

		// Adding components to mPanel
		mPanel.add(pChart.getPanel());
		mPanel.add(lblWinTitle);
		mPanel.add(lblLossTitle);
		mPanel.add(lblAvgCreditTitle);
		mPanel.add(panel1);

		// Setting the JFrame
		statFrame.setTitle("Game Statistics");
		statFrame.add(mPanel);
		statFrame.setSize(300, 350);
		statFrame.setLocationRelativeTo(null);
		statFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		statFrame.setVisible(true);
	}
}
