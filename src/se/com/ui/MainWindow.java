package se.com.ui;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import se.com.package1.GoalManager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Main window.
 * 
 */
public class MainWindow extends JFrame {
	private ECWindow aECWindow;
	private JButton buttonOK = new JButton("Start Scenario");
	private JRadioButton optionScenarioOne = new JRadioButton("Scenario one");
	private JRadioButton optionScenarioTwo = new JRadioButton("Scenario two");
	private JLabel labelEvents = new JLabel("Event log");
	private JTextArea eventsJTextField = new JTextArea();

	public MainWindow() {
		super("System simulation");
		aECWindow = new ECWindow();
		aECWindow.setVisible(true);
		eventsJTextField.setEditable(false);
		eventsJTextField.setFont(eventsJTextField.getFont().deriveFont(20f));
		eventsJTextField.setMargin(getInsets());
		
		ButtonGroup group = new ButtonGroup();
		group.add(optionScenarioOne);
		group.add(optionScenarioTwo);
		optionScenarioOne.setSelected(true);

		Box top = Box.createVerticalBox();
		top.add(optionScenarioOne);
		top.add(optionScenarioTwo);
		top.add(buttonOK);

		Box bottom = Box.createVerticalBox();
		bottom.add(labelEvents);
		bottom.add(eventsJTextField);

		Box right = Box.createVerticalBox();

		Box allBoxes = Box.createHorizontalBox();
		allBoxes.add(top);
		allBoxes.add(bottom);
		allBoxes.add(right);

		this.setSize(new Dimension(1300, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.add(allBoxes);

		// LISTENERS
		RadioButtonActionListener actionListener = new RadioButtonActionListener();
		optionScenarioOne.addActionListener(actionListener);
		optionScenarioTwo.addActionListener(actionListener);

		buttonOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				 Thread scenarioThread = new Thread() {
					public void run() {
						// buttonOK.setEnabled(false);
						eventsJTextField.setText("");
						GoalManager rs = new se.com.package1.GoalManager(MainWindow.this, aECWindow);
						if (optionScenarioOne.isSelected()) {
							rs.startECForming(2);
						}
						if (optionScenarioTwo.isSelected()) {
							rs.startECForming(7);
						}
					}
				};
				scenarioThread.interrupt();
				scenarioThread.start();
			}
		});
	}

	public void populateEventLog(String newEvent) {
		String currentText = eventsJTextField.getText();
		String newEventToAppend = currentText + "\n\n   " + newEvent;
		eventsJTextField.setText(newEventToAppend);
	}

	class RadioButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JRadioButton button = (JRadioButton) event.getSource();
			if (button == optionScenarioOne) {
				// labelImage.setIcon(iconLinux);
			} else if (button == optionScenarioTwo) {
				// labelImage.setIcon(iconWin);
			}
		}
	}
}