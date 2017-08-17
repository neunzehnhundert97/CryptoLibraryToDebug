package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Gui extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Listener listener;
	
	JButton button;
	JButton button2;
	JLabel label;

	public Gui()
	{	
		super("Crypto Library to Debug - GUI");
		listener = new Listener(this);
		this.place_middle(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(listener);
		this.setLayout(null);
		
		label = new JLabel("<html><i>Hello</i></html>");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.TOP);
		this.add(label);
		label.setToolTipText("Füße");
		
		button = new JButton("Klick Mich!");
		this.add(button);
		button2 = new JButton("Ich bin 2");
		this.add(button2);
		
		button2.setBounds(10, 10, 100, 100);
		button.setBounds(120, 10, 100, 100);
		label.setBounds(10, 120, 100, 100);
		button2.addMouseListener(listener);
		button.addMouseListener(listener);
		
		JTextArea field = new JTextArea();
		JScrollPane pane = new JScrollPane(field);
		
		JComboBox<String> box = new JComboBox<>(new String[] {"Hallo", "Tshüss"});
		this.add(box);
		box.setBounds(10, 400, 200, 50);
		
		this.add(pane);
		pane.setBounds(10, 300, 200, 50);
		pane.setEnabled(true);
		//JTextArea
		//JRadioButton
		
		label.setOpaque(true);
		
	}
	
	public void button_clicked(String s) {
		switch (s) {
		case "Klick Mich!":
			label.setBackground(new Color(255, 0, 0));
			break;
		case "Ich bin 2":
			JFileChooser c = new JFileChooser("C:\\User\\");
			c.showOpenDialog(null);
			System.out.println(c.getSelectedFile());
			break;
		}
	}

	private void place_middle(int width, int height)
	{
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		this.setBounds(d.width / 2 - width / 2, d.height / 2 - height / 2, width, height);
	}
	
	
}
