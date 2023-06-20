import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MenuFrame extends JFrame {

	private JButton btnList;
	private JButton btnRandom;
	private JButton btnSearchHistory;
	private JButton btnReset;
	private JButton btnQuizGame;

	private SlangWord slangWord;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame window = new MenuFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MenuFrame() throws IOException {
		initialize();
		initializeEvent();
		slangWord = SlangWord.getInstance();
		slangWord.readFile(FileNameUtils.DEFAULT);
	}

	private void initialize() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 19));
		setBounds(100, 100, 1100, 445);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SLANG WORD");
		lblNewLabel.setForeground(new Color(0, 206, 209));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 1066, 71);
		getContentPane().add(lblNewLabel);
		
		btnList = new JButton("List Slang Words");
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnList.setBounds(78, 145, 250, 60);
		getContentPane().add(btnList);
		
		btnRandom = new JButton("Random Slang Word");
		btnRandom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnRandom.setBounds(413, 145, 250, 60);
		getContentPane().add(btnRandom);
		
		btnSearchHistory = new JButton("Search History");
		btnSearchHistory.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnSearchHistory.setBounds(764, 145, 250, 60);
		getContentPane().add(btnSearchHistory);
		
		btnReset = new JButton("Reset Slang Words");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnReset.setBounds(245, 266, 250, 60);
		getContentPane().add(btnReset);
		
		btnQuizGame = new JButton("Quiz Game");
		btnQuizGame.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnQuizGame.setBounds(589, 266, 250, 60);
		getContentPane().add(btnQuizGame);
		this.setVisible(true);
	}

	public void initializeEvent() {
		btnList.addActionListener(e -> {
			try {
				this.dispose();
				new ListSlangWordFrame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnRandom.addActionListener(e -> {
			this.dispose();
			new RandomFrame();
		});
		
		btnSearchHistory.addActionListener(e -> {
			try {
				this.dispose();
				new SearchHistoryFrame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnReset.addActionListener(e -> {
			try {
				int option = JOptionPane.showConfirmDialog(this,
						"Would you like to delete this slang word?", "Notification",
						JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					slangWord.readFile(FileNameUtils.ORIGINAL);
					slangWord.writeFile(FileNameUtils.DEFAULT);
					JOptionPane.showMessageDialog(this, "Reset Successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnQuizGame.addActionListener(e -> {
			try {
				this.dispose();
				new QuizFrame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	
}
