package slotMachine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class SlotMachineController implements Serializable {

	private static final long serialVersionUID = -6781023477905821613L;

	/* Declaring relevant private attributes */
	private SlotMachineView theView;
	private SlotMachineModel theModel;
	private static final int INITIALCREDIT = 10;
	private static final int INITIALBET = 0;
	private static final String GAMEOVER = "You Lose!";
	private int[] statArray = new int[4]; 
	//[0]=wins, [1]=loses, [2]=total bets, [3]=total times

	/* Declaring thread which is going to use to animate reels */
	private MyThread thread1;
	private MyThread thread2;
	private MyThread thread3;

	// Checks whether the spin is on or off
	private boolean spin = false;

	/* Declaring the constructor */
	public SlotMachineController(SlotMachineView theView, SlotMachineModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
	}

	/* Getter method for statArray */
	public int[] getStatArray() {
		return statArray;
	}

	/* Initialize the relevant attributes */
	public void initialise() {
		theView.setCredit(INITIALCREDIT);
		theView.setBetAmount(INITIALBET);
		theView.setErrMsg("Welcome to Slot Machine Game");
		this.actionListnerCreator();
		this.mouseActionListnerCreator(theView.getLblReel1());
		this.mouseActionListnerCreator(theView.getLblReel2());
		this.mouseActionListnerCreator(theView.getLblReel3());
		theView.setVisible(true);
	}

	/* Action listeners for buttons */
	public void actionListnerCreator() {
		/*
		 * When the "Add Coin" button selected calls the addCoinControl() method
		 */
		theView.getBtnAddCoin().addActionListener(e -> {
			theView.setErrMsg("");
			addCoinControl();
		});
		/*
		 * When the "Bet One" button selected calls the betOneControl() method
		 */
		theView.getBtnBetOne().addActionListener(e -> {
			theView.setErrMsg("");
			betOneControl();
		});
		/*
		 * When the "Bet Max" button selected calls the betMaxControl() method
		 */
		theView.getBtnBetMax().addActionListener(e -> {
			theView.setErrMsg("");
			betMaxControl();
		});
		/*
		 * When the "SPIN" button selected disables all the buttons and calls
		 * threadControl() method
		 */
		theView.getBtnSpin().addActionListener(e -> {
			theView.setErrMsg("");
			theView.buttonControl(false);
			threadControl(true);
		});
		/* When the "RESET" button selected calls resetControl() method */
		theView.getBtnReset().addActionListener(e -> {
			theView.setErrMsg("");
			resetControl();
		});
		/* When the "Statistics" button selected calls statWindow() method */
		theView.getBtnStatistics().addActionListener(e -> {
			theView.setErrMsg("");
			theView.statWindow(statArray);
			/*
			 * when the Save Statistics button in statistics window clicked
			 * calls the saveToFile() method
			 */
			theView.getBtnPrintStat().addActionListener(event -> {
				saveToFile();
			});
		});
	}

	/* Mouse action listeners for JLabels which holds the reels */
	public void mouseActionListnerCreator(JLabel label) {
		label.addMouseListener(new MouseListener() {
			/*
			 * When mouse clicks stops reels and calculates the winning credits
			 * and enables buttons
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				threadControl(false);
				winCalculator();
				theView.buttonControl(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setBorder(theView.getSelectedBorder());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label.setBorder(theView.getUnselectedBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// Do nothing
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// Do nothing
			}
		});
	}

	/* Controls the "Add Coin" button operation.Adds one coin for Credit Area */
	public void addCoinControl() {
		int credit = theView.getCredit();
		int newCredit = theModel.addCoin(credit);
		theView.setCredit(newCredit);
	}

	/*
	 * Controls the "Bet One" button operation. Adds one coin for bet area if
	 * credits>0 and reduce one coin from Credit Area
	 */
	public void betOneControl() {
		int credit = theView.getCredit();
		if (credit > 0) {
			int oldBet = theView.getBetAmount();
			int newBet = theModel.betOne(oldBet);
			int newCredit = theModel.removeOneCoin(credit);
			theView.setCredit(newCredit);
			theView.setBetAmount(newBet);
		} else {
			theView.setErrMsg("You have no credits");
		}
	}

	/*
	 * Controls the "Bet Max" button operation.Add three coins for bet area if
	 * credits>2 and reduce three coins from Credit Area
	 */
	public void betMaxControl() {
		int credit = theView.getCredit();
		if (credit > 2) {
			int oldBet = theView.getBetAmount();
			int newCredit = theModel.betMax(credit, oldBet);
			theView.setCredit(newCredit);
			theView.setBetAmount(3);
		} else {
			theView.setErrMsg("You have less than 3 credits");
		}
	}

	/*
	 * Controls the "Reset" button operation. Sets Bet Area to 0 and return bet
	 * amount to Credit Area
	 */
	public void resetControl() {
		int existingCredit = theView.getCredit();
		theView.setCredit(existingCredit + theView.getBetAmount());
		theView.setBetAmount(0);
	}

	/*
	 * Checks whether pictures displayed in the reel label are same or not. If 3
	 * reel labels are matching corresponding values are added to credit area.
	 * If 2 reel labels are matching another spinning chance is given. If no
	 * reel labels are matching bet amount is reduced from credit area. This
	 * process happens if the spin is true.
	 */
	public void winCalculator() {
		if (spin) {
			int betAmnt = theView.getBetAmount();
			Symbol sym1 = thread1.getObj();
			Symbol sym2 = thread2.getObj();
			Symbol sym3 = thread3.getObj();

			boolean result1 = sym1.compareSymbols(sym1, sym2);
			boolean result2 = sym1.compareSymbols(sym2, sym3);
			boolean result3 = sym1.compareSymbols(sym1, sym3);

			int credits = theView.getCredit();
			spin = false;
			if (result1 && result2) {
				statArray[2] += theView.getBetAmount();
				theView.setBetAmount(INITIALBET);
				statArray[3] += 1;
				statArray[0]++;
				int symbolAmnt = sym1.getValue();
				int newCredits = theModel.addWinningCoins(credits, betAmnt, symbolAmnt);
				int wonCredits = newCredits - (credits + betAmnt);
				theView.setCredit(newCredits);
				theView.setErrMsg("YOU WIN " + wonCredits + "!");
			} else if (result1 || result2 || result3) {
				theView.setErrMsg("TRY AGAIN!");
			} else {
				statArray[2] += -theView.getBetAmount();
				theView.setBetAmount(INITIALBET);
				statArray[3] += 1;
				statArray[1]++;
				theView.setErrMsg(GAMEOVER);
			}
		}
	}

	/* starts and stops threads according to the boolean flag value */
	@SuppressWarnings("deprecation")
	public void threadControl(boolean flag) {
		int betAmnt = theView.getBetAmount();
		if (betAmnt > 0) {
			if (flag) {
				Reel reel1 = new Reel();
				Reel reel2 = new Reel();
				Reel reel3 = new Reel();

				Symbol[] symArray1 = reel1.spin();
				Symbol[] symArray2 = reel2.spin();
				Symbol[] symArray3 = reel3.spin();

				thread1 = new MyThread(symArray1, theView.getLblReel1());
				thread2 = new MyThread(symArray2, theView.getLblReel2());
				thread3 = new MyThread(symArray3, theView.getLblReel3());

				thread1.start();
				thread2.start();
				thread3.start();
				spin = true;
			} else if (spin) {
				thread1.stop();
				thread2.stop();
				thread3.stop();
			}
		} else {
			theView.buttonControl(true);
			theView.setErrMsg("You don't have any bet");
		}
	}

	/*
	 * Saving number of wins,losses and average number of credits netted per
	 * game
	 */
	public void saveToFile() {
		int averageCredits = 0;
		try {
			averageCredits = (statArray[2]) / statArray[3];
		} catch (ArithmeticException e) {
			averageCredits = 0;
		}
		try {
			String title = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.txt'").format(new Date());
			File file = new File(title);
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.println("Statistics of Slot Machine Game");
			writer.println("Wins :" + statArray[0]);
			writer.println("Loses:" + statArray[1]);
			writer.println("Average number of credits netted per game:" + averageCredits);
			writer.close();
		} catch (IOException e) {
			System.out.println("File Saving Error!");
		}
	}

	/* Inner class which is used to create threads */
	class MyThread extends Thread {
		private Symbol obj;
		private Symbol[] symArray;
		private JLabel label;

		/* Declaring the constructor */
		MyThread(Symbol[] symArray, JLabel label) {
			this.symArray = symArray;
			this.label = label;
		}

		/* Getter method for Symbol type object */
		public Symbol getObj() {
			return obj;
		}

		@Override
		public void run() {
			for (int i = 0; i <= symArray.length; i++) {
				try {
					obj = symArray[i];
					label.setIcon(symArray[i].getImage());
					Thread.sleep(100);
				} catch (ArrayIndexOutOfBoundsException e) {
					// when the i becomes 6 an ArrayIndexOutOfBoundsException is
					// thrown. Then this makes i=0
					i = 0;
				} catch (InterruptedException e) {
					System.out.println("InterruptedException");
				}
			}
		}
	}
}
