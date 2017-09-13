package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Listener implements WindowListener, MouseListener, ActionListener
{
	Gui g;

	public Listener(Gui g)
	{
		this.g = g;
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{}

	@Override
	public void windowClosed(WindowEvent arg0)
	{}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		int option = JOptionPane.showConfirmDialog(null, "Sure you want to close?", "Close?", JOptionPane.YES_OPTION);
		if (option == 0)
		{
			System.exit(0);
		}

	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{}

	@Override
	public void windowIconified(WindowEvent arg0)
	{}

	@Override
	public void windowOpened(WindowEvent arg0)
	{}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		g.button_clicked(((JButton) e.getComponent()).getText());

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// System.out.println("action Event");
		JComboBox tmp = (JComboBox) arg0.getSource();
		String choosed = (String) tmp.getSelectedItem();
		// System.out.println(choosed);
		g.comboBox_handler(choosed);
	}

}
