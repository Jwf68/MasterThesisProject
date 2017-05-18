package se.com.ui;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 * EC log window.
 * 
 */
public class ECWindow extends JFrame {
	private JTextArea ECOutputJTextField = new JTextArea();
	private JLabel labelECOutput = new JLabel("Current EC");

	public ECWindow() {
		super("EC simulation");
		ECOutputJTextField.setEditable(false);
		ECOutputJTextField.setFont(ECOutputJTextField.getFont().deriveFont(20f));
		ECOutputJTextField.setMargin(getInsets());
		
		Box top = Box.createVerticalBox();

		Box bottom = Box.createVerticalBox();

		Box right = Box.createVerticalBox();
		right.add(labelECOutput );
		right.add(ECOutputJTextField);

		Box allBoxes = Box.createHorizontalBox();
		allBoxes.add(top);
		allBoxes.add(bottom);
		allBoxes.add(right);

		this.setSize(new Dimension(1300, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.add(allBoxes);
	}

	public void populateECOutputLog(String newEvent) {
		String currentText = ECOutputJTextField.getText();
		String newEventToAppend = currentText + "\n\n   " + newEvent;
		ECOutputJTextField.setText(newEventToAppend);
	}
}