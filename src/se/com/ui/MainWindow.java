package se.com.ui;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import se.com.package1.GoalManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A Swing program that demonstrates how to use JRadioButton component.
 * 
 * @author www.codejava.net
 * 
 */
public class MainWindow extends JFrame {
	private JButton buttonOK = new JButton("Start Scenario");
	private JRadioButton optionNoviceBerit = new JRadioButton("Novice repairer Berit");
	private JRadioButton optionExpertGunnar = new JRadioButton("Expert repairer Gunnar");
	private JLabel labelEvents = new JLabel("Event log");
	private JTextArea eventsJTextField = new JTextArea();
	private JTextArea ECOutputJTextField = new JTextArea();
	private JLabel labelECOutput = new JLabel("Current EC");

	public MainWindow() {
		super("System simulation");

		eventsJTextField.setEditable(false);
		eventsJTextField.setText("");
		eventsJTextField.setFont(eventsJTextField.getFont().deriveFont(16f));
		eventsJTextField.setMargin(getInsets());
		ECOutputJTextField.setEditable(false);
		ECOutputJTextField.setText("");
		ECOutputJTextField.setFont(eventsJTextField.getFont().deriveFont(16f));
		ECOutputJTextField.setMargin(getInsets());
		
		ButtonGroup group = new ButtonGroup();
		group.add(optionNoviceBerit);
		group.add(optionExpertGunnar);
		optionNoviceBerit.setSelected(true);

		// jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		// jp.setAlignmentX(CENTER_ALIGNMENT);

		Box top = Box.createVerticalBox();
		top.add(optionNoviceBerit);
		top.add(optionExpertGunnar);
		top.add(buttonOK);

		Box bottom = Box.createVerticalBox();
		bottom.add(labelEvents);
		bottom.add(eventsJTextField);

		Box right = Box.createVerticalBox();
		right.add(labelECOutput );
		right.add(ECOutputJTextField);
		

		Box allBoxes = Box.createHorizontalBox();
		allBoxes.add(top);
		allBoxes.add(bottom);
		allBoxes.add(right);

		this.setSize(new Dimension(2200, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.add(allBoxes);

		// LISTENERS
		RadioButtonActionListener actionListener = new RadioButtonActionListener();
		optionNoviceBerit.addActionListener(actionListener);
		optionExpertGunnar.addActionListener(actionListener);

		buttonOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				 Thread scenarioThread = new Thread() {
					public void run() {
						// buttonOK.setEnabled(false);
						eventsJTextField.setText("");
						ECOutputJTextField.setText("");
						GoalManager rs = new se.com.package1.GoalManager(MainWindow.this);
						if (optionNoviceBerit.isSelected()) {
							rs.startECForming(2);
						}
						if (optionExpertGunnar.isSelected()) {
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

	public void populateECOutputLog(String newEvent) {
		String currentText = ECOutputJTextField.getText();
		String newEventToAppend = currentText + "\n\n   " + newEvent;
		ECOutputJTextField.setText(newEventToAppend);
	}

	class RadioButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JRadioButton button = (JRadioButton) event.getSource();
			if (button == optionNoviceBerit) {
				// labelImage.setIcon(iconLinux);
			} else if (button == optionExpertGunnar) {
				// labelImage.setIcon(iconWin);
			}
		}
	}
}