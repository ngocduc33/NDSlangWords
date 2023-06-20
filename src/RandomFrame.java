import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class RandomFrame extends JFrame {
	private SlangWord slangWord;
	private JButton btnRandom;
	private JLabel lblRandom;
	private JButton btnBack;
	
	public RandomFrame() {
		initialize();
		initializeEvent();
		slangWord = SlangWord.getInstance();
		random();
	}

	private void initialize() {
		this.setBounds(100, 100, 860, 265);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblRandom = new JLabel("<dynamic>: <dynamic>");
		lblRandom.setHorizontalAlignment(SwingConstants.CENTER);
		lblRandom.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRandom.setBounds(0, 35, 836, 50);
		getContentPane().add(lblRandom);
		
		btnRandom = new JButton("See More");
		btnRandom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnRandom.setBounds(346, 112, 131, 40);
		getContentPane().add(btnRandom);
		
		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnBack.setEnabled(true);
		btnBack.setBounds(659, 169, 152, 49);
		getContentPane().add(btnBack);
		this.setVisible(true);
	}

	public void random() {
		Map.Entry<String, String> result = slangWord.random().entrySet().iterator().next();
		lblRandom.setText(result.getKey() + ": " + result.getValue());
	}
	
	public void initializeEvent() {
		btnBack.addActionListener(e -> {
			try {
				this.dispose();
				new MenuFrame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnRandom.addActionListener(e -> random());
	}
}
