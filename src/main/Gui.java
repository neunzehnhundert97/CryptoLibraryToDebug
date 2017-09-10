package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Gui extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Listener listener;

	private JButton button;
	private JButton button2;

	private JLabel options_label;
	private JLabel categories_label;
	private JLabel algorithms_label;

	private JPanel option_panel;
	
	private JComboBox<String> algorithm_categories_comboBox;
	private JComboBox<String> algorithms_comboBox;

	public Gui()
	{
		listener = new Listener(this);
		this.setup();
		// label.setToolTipText("Füße");
		// label.setOpaque(true);

		/*
		 * button = new JButton("Klick Mich!"); this.add(button); button2 = new
		 * JButton("Ich bin 2"); this.add(button2);
		 * 
		 * button2.setBounds(10, 100, 100, 100); button.setBounds(120, 100, 100, 100);
		 * button2.addMouseListener(listener); button.addMouseListener(listener);
		 * 
		 * JTextArea field = new JTextArea(); JScrollPane pane = new JScrollPane(field);
		 * 
		 * JComboBox<String> box = new JComboBox<>(new String[] { "Hallo", "Tshüss" });
		 * this.add(box); box.setBounds(100, 400, 200, 50);
		 * 
		 * this.add(pane); pane.setBounds(100, 300, 200, 50); pane.setEnabled(true); //
		 * JTextArea // JRadioButton
		 */

	}

	public void button_clicked(String s)
	{
		switch (s)
		{
		case "Klick Mich!":
			options_label.setBackground(new Color(255, 0, 0));
			break;
		case "Ich bin 2":
			JFileChooser c = new JFileChooser("C:\\User\\");
			c.showOpenDialog(null);
			System.out.println(c.getSelectedFile());
			break;
		}
	}

	public void comboBox_handler(String s)
	{
		algorithms_comboBox.setVisible(true);
		//algorithm_categories_comboBox.removeAllItems();
		//algorithm_categories_comboBox.addItem("Hash");
		//algorithm_categories_comboBox.addItem("Crypto");
		
		
		switch (s)
		{
		case "Hash":
			System.out.println("comboBox_handler");
			
			break;
		}
	}

	private void place_middle(int width, int height)
	{
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		this.setBounds(d.width / 2 - width / 2, d.height / 2 - height / 2, width, height);
	}

	private void setup()
	{
		this.place_middle(1000, 750);

		option_panel = new JPanel();
		option_panel.setLayout(new GridLayout(20, 1));
		option_panel.setPreferredSize(new Dimension(250, 100));

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(listener);
		this.setLayout(new BorderLayout());

		// setup the options Panel

		// headline OPTIONS
		options_label = new JLabel("Options");
		options_label.setFont(options_label.getFont().deriveFont(18f));
		option_panel.add(options_label);

		// option_panel.add

		// Menu categories
		categories_label = new JLabel("Categories:");
		option_panel.add(categories_label);

		String[] algorithm_categories =
		{ "---", "Hash", "Crypto", "Misc" };
		algorithm_categories_comboBox = new JComboBox<String>(algorithm_categories);
		algorithm_categories_comboBox.setPreferredSize(new Dimension(400, 100));
		algorithm_categories_comboBox.addActionListener(listener);
		option_panel.add(algorithm_categories_comboBox);

		// algorithm chooser
		algorithms_label = new JLabel("Algorithms:");
		algorithms_label.setVisible(false);
		option_panel.add(algorithms_label);

		String[] algorithms =
		{ "---", "hash1", "hash2", "hash3" };
		algorithms_comboBox = new JComboBox<String>(algorithms);
		algorithms_comboBox.setPreferredSize(new Dimension(400, 100));
		algorithms_comboBox.setVisible(false);
		option_panel.add(algorithms_comboBox);
		

		// algorithm_categories_comboBox.removeAllItems();
		// algorithm_categories_comboBox.addItem("test");

		this.add(option_panel, BorderLayout.LINE_START);

		this.setVisible(true);
	}

}
