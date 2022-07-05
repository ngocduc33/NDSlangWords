package slang;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuFrame extends JFrame implements ActionListener {
	JButton b1, b2, b3, b4, b5, b6, b7, b8;
	SlangWord slangWord;

	MenuFrame() {
		slangWord = SlangWord.getInstance();
		// A Label
		JLabel label = new JLabel("Slang Words");
		label.setForeground(Color.green);
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
		label.setAlignmentX(CENTER_ALIGNMENT);
		// label.addMouseListener(this);
		// A Grid
		b1 = new JButton("1. List Slang Words");
		b1.addActionListener(this);
		b1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b1.setFocusable(false);

		b2 = new JButton("2. Find Slang word");
		b2.addActionListener(this);
		b2.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b2.setFocusable(false);

		b3 = new JButton("3. Add slang word");
		b3.addActionListener(this);
		b3.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b3.setFocusable(false);

		b4 = new JButton("4. Random Slang Words");
		b4.addActionListener(this);
		b4.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b4.setFocusable(false);

		b5 = new JButton("5. History");
		b5.addActionListener(this);
		b5.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b5.setFocusable(false);

		b6 = new JButton("6. Delete Slang Word");
		b6.addActionListener(this);
		b6.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b6.setFocusable(false);

		b7 = new JButton("7. Reset Slang Word");
		b7.addActionListener(this);
		b7.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b7.setFocusable(false);

		b8 = new JButton("8. Quiz");
		b8.addActionListener(this);
		b8.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		b8.setFocusable(false);

		JPanel panelCenter = new JPanel();
		// panelCenter.setBackground(Color.gray);
		panelCenter.setLayout(new GridLayout(3, 3, 10, 10));
		panelCenter.add(b1);
		panelCenter.add(b2);
		panelCenter.add(b3);
		panelCenter.add(b4);
		panelCenter.add(b5);
		panelCenter.add(b6);
		panelCenter.add(b7);
		panelCenter.add(b8);

		Dimension size2 = new Dimension(600, 500);
		panelCenter.setMaximumSize(size2);
		panelCenter.setPreferredSize(size2);
		panelCenter.setMinimumSize(size2);
		Container con = this.getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(label);
		con.add(Box.createRigidArea(new Dimension(0, 30)));
		con.add(panelCenter);

		// Setting Frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Menu Window");
		this.setVisible(true);
		this.setSize(700, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b1) {
			this.dispose();
			try {
				new ListSWFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == b2) {
			System.out.println("Change Actitity");
			this.dispose();
			try {
				new FindSWFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == b3) {
			// Add a slang word
			this.dispose();
			new AddWordFrame();

		} else if (e.getSource() == b4) {
			this.dispose();
			new RandomFrame();

		} else if (e.getSource() == b5) {
			this.dispose();
			new HistoryFrame();

		} else if (e.getSource() == b6) {
			this.dispose();
			try {
				new DeleteFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == b7) {
			// default icon, custom title
			int n = JOptionPane.showConfirmDialog(this, "Do you really want to reset Slang Word?", "An Inane Question",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				slangWord.reset();
				JOptionPane.showMessageDialog(this, "Reset success.");
			}
		} else if (e.getSource() == b8) {
			this.dispose();
			new QuizFrame();
		}
	}

}
