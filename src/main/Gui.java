package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Gui extends JFrame
{
	private String[] algorithm_categories =
	{ "Please choose a category", "Hash", "Crypto", "Misc" };
	private String[] hashAlgorithms =
	{ "Please choose a algorithm", "MD2", "MD5", "SHA-1", "SHA-2", "SHA-3" };
	private String[] cryptoAlgorithms =
	{ "Please choose a algorithm", "DES", "AES" };
	private String[] miscAlgorithms =
	{ "Please choose a algorithms", "Square-and-Multiply", "Double-and-Add", "Extended Euclidian Algorithm" };

	private static final long serialVersionUID = 1L;

	private Listener listener;

	private JButton button;
	private JButton button2;

	private JLabel options_label;
	private JLabel categories_label;
	private JLabel algorithms_label;
	private JLabel verboseLevel_label;
	private JLabel input_label;
	private JLabel output_label;

	private JPanel option_panel;
	private JPanel textbox_panel;

	private JComboBox<String> algorithm_categories_comboBox;
	private JComboBox<String> algorithms_comboBox;

	private JSlider verboseLevel_slider;
	
	private JTextArea input_textArea;
	private JTextArea output_textArea;

	private boolean algorithm_categories_firstCall = true;
	private boolean algorithms_firstCall = true;

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
		System.out.println("combobox_handler");
		if (algorithm_categories_firstCall)
		{
			algorithm_categories_firstCall = false;
			algorithms_comboBox.setVisible(true);
			algorithms_label.setVisible(true);
			algorithm_categories_comboBox.removeItem("Please choose a category");
		}
		// algorithm_categories_comboBox.removeAllItems();
		// algorithm_categories_comboBox.addItem("Hash");
		// algorithm_categories_comboBox.addItem("Crypto");
		// algorithm_categories_comboBox.addItem("Misc");

		switch (s)
		{
		// Categories:
		case "Hash":
			algorithms_comboBox.removeAllItems();
			for (int i = 0; i < hashAlgorithms.length; i++)
			{
				algorithms_comboBox.addItem(hashAlgorithms[i]);
			}
			break;
		case "Crypto":
			algorithms_comboBox.removeAllItems();
			for (int i = 0; i < cryptoAlgorithms.length; i++)
			{
				algorithms_comboBox.addItem(cryptoAlgorithms[i]);
			}
			break;
		case "Misc":
			algorithms_comboBox.removeAllItems();
			for (int i = 0; i < miscAlgorithms.length; i++)
			{
				algorithms_comboBox.addItem(miscAlgorithms[i]);
			}
			break;
		// Algorithms:
		//HASHES:
		case "MD2":
			algorithms_firstcall_check();
			break;
		case "MD5":
			algorithms_firstcall_check();
			break;
		case "SHA-1":
			algorithms_firstcall_check();
			break;
		case "SHA-2":
			algorithms_firstcall_check();
			break;
		case "SHA-3":
			algorithms_firstcall_check();
			break;
		//CRYPTO:
		case "DES":
			algorithms_firstcall_check();
			break;
		case "AES":
			algorithms_firstcall_check();
			break;
		//MISC:
		case "Square-and-Multiply":
			algorithms_firstcall_check();
			break;
		case "Double-and-Add":
			algorithms_firstcall_check();
			break;
		case "Extended Euclidean Algorithm":
			algorithms_firstcall_check();
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
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(listener);
		this.setLayout(new BorderLayout());

		option_panel = new JPanel();
		option_panel.setLayout(new GridLayout(17, 1));
		option_panel.setPreferredSize(new Dimension(250, 100));
		
		textbox_panel = new JPanel();
		textbox_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();		

		// SETUP the options Panel

		// headline OPTIONS
		options_label = new JLabel("Options");
		options_label.setFont(options_label.getFont().deriveFont(18f));
		option_panel.add(options_label);

		// option_panel.add

		// Menu categories
		categories_label = new JLabel("Categories:");
		option_panel.add(categories_label);

		algorithm_categories_comboBox = new JComboBox<String>(algorithm_categories);
		algorithm_categories_comboBox.setPreferredSize(new Dimension(400, 100));
		algorithm_categories_comboBox.addActionListener(listener);
		option_panel.add(algorithm_categories_comboBox);

		// algorithm chooser
		algorithms_label = new JLabel("Algorithms:");
		algorithms_label.setVisible(false);
		option_panel.add(algorithms_label);

		// String[] algorithms = hashAlgorithms;
		algorithms_comboBox = new JComboBox<String>();
		algorithms_comboBox.setPreferredSize(new Dimension(400, 100));
		algorithms_comboBox.addActionListener(listener);
		algorithms_comboBox.setVisible(false);
		option_panel.add(algorithms_comboBox);

		verboseLevel_label = new JLabel("Verbose Level:");
		verboseLevel_label.setVisible(false);
		option_panel.add(verboseLevel_label);

		verboseLevel_slider = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
		verboseLevel_slider.setMajorTickSpacing(1);
		verboseLevel_slider.setPaintTicks(true);
		verboseLevel_slider.setPaintLabels(true);
		verboseLevel_slider.setVisible(false);
		option_panel.add(verboseLevel_slider);

		// algorithm_categories_comboBox.removeAllItems();
		// algorithm_categories_comboBox.addItem("test");
		
		// SETUP the textbox Panel
		
		input_label = new JLabel("Input:");
		input_label.setFont(input_label.getFont().deriveFont(18f));
		input_label.setVisible(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		textbox_panel.add(input_label, c);
		
		input_textArea = new JTextArea();
		input_textArea.setLineWrap(true);
		input_textArea.setEditable(true);
		//input_textArea.setPreferredSize(new Dimension(500, 150));
		input_textArea.setVisible(true);
		JScrollPane scroll_input = new JScrollPane(input_textArea);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 150;
		c.ipadx = 500;
		c.weighty = 0.5;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 1;
		textbox_panel.add(scroll_input, c);
		
		output_label = new JLabel("Output:");
		output_label.setFont(output_label.getFont().deriveFont(18f));
		output_label.setVisible(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0;
		textbox_panel.add(output_label, c);
		
		output_textArea = new JTextArea();
		output_textArea.setLineWrap(true);
		output_textArea.setEditable(false);
		//output_textArea.setPreferredSize(new Dimension(500, 450));
		output_textArea.setVisible(true);
		JScrollPane scroll_output = new JScrollPane(output_textArea);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.5;
		c.ipady = 400;
		c.gridheight = 3;
		c.gridx = 0;
		c.gridy = 3;
		textbox_panel.add(scroll_output, c);
		

		this.add(option_panel, BorderLayout.LINE_START);
		this.add(textbox_panel, BorderLayout.CENTER);
		

		this.setVisible(true);
	}

	private void algorithms_firstcall_check()
	{
		if (algorithms_firstCall)
		{
			algorithms_firstCall = false;
			verboseLevel_label.setVisible(true);
			verboseLevel_slider.setVisible(true);
			algorithms_comboBox.removeItem("Please choose a algorithm");
		}
	}

}
