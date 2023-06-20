import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class QuizFrame extends JFrame {
	private SlangWord slangWord;
	private JButton btnBack;
	private JComboBox cbQuizMode;
	private JLabel lblQuizQuestion;
	private JButton btnQuizAnswerA;
	private JButton btnQuizAnswerB;
	private JButton btnQuizAnswerC;
	private JButton btnQuizAnswerD;
	private JButton btnQuizStart;
	private JButton btnQuizNextQuestion;
	private JButton btnQuizStop;
	
	Map<String, List<String>> result;

	public QuizFrame() throws IOException {
		initialize();
		initializeEvent();
		slangWord = SlangWord.getInstance();
		btnQuizNextQuestion.setEnabled(false);
		btnQuizStop.setEnabled(false);
		setVisibleAnswerToFalse();
	}

	private void initialize() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 19));
		getContentPane().setLayout(null);
		
		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnBack.setEnabled(true);
		btnBack.setBounds(859, 567, 152, 49);
		getContentPane().add(btnBack);
		
		JLabel lblQuizMode = new JLabel("Mode:");
		lblQuizMode.setBounds(200, 106, 98, 23);
		getContentPane().add(lblQuizMode);
		lblQuizMode.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		cbQuizMode = new JComboBox();
		cbQuizMode.setModel(new DefaultComboBoxModel(new String[] {"Slang", "Definition"}));
		cbQuizMode.setBounds(318, 102, 152, 31);
		getContentPane().add(cbQuizMode);
		cbQuizMode.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnQuizAnswerA = new JButton("A.");
		btnQuizAnswerA.setBounds(134, 263, 290, 56);
		getContentPane().add(btnQuizAnswerA);
		btnQuizAnswerA.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnQuizAnswerC = new JButton("C.");
		btnQuizAnswerC.setBounds(134, 378, 290, 56);
		getContentPane().add(btnQuizAnswerC);
		btnQuizAnswerC.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnQuizStart = new JButton("Start");
		btnQuizStart.setBounds(200, 477, 131, 31);
		getContentPane().add(btnQuizStart);
		btnQuizStart.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnQuizNextQuestion = new JButton("Next Question");
		btnQuizNextQuestion.setBounds(395, 477, 168, 31);
		getContentPane().add(btnQuizNextQuestion);
		btnQuizNextQuestion.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JLabel lblQuiz = new JLabel("Quiz Game");
		lblQuiz.setBounds(0, 20, 1011, 49);
		getContentPane().add(lblQuiz);
		lblQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuiz.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JLabel lblQuestionTitle = new JLabel("Question:");
		lblQuestionTitle.setBounds(200, 171, 98, 23);
		getContentPane().add(lblQuestionTitle);
		lblQuestionTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnQuizAnswerB = new JButton("B.");
		btnQuizAnswerB.setBounds(525, 263, 290, 56);
		getContentPane().add(btnQuizAnswerB);
		btnQuizAnswerB.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnQuizAnswerD = new JButton("D.");
		btnQuizAnswerD.setBounds(525, 378, 290, 56);
		getContentPane().add(btnQuizAnswerD);
		btnQuizAnswerD.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnQuizStop = new JButton("Stop");
		btnQuizStop.setBounds(636, 477, 131, 31);
		getContentPane().add(btnQuizStop);
		btnQuizStop.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		lblQuizQuestion = new JLabel("");
		lblQuizQuestion.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblQuizQuestion.setBounds(318, 171, 528, 23);
		getContentPane().add(lblQuizQuestion);
		setBounds(100, 100, 1035, 668);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
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
		
		btnQuizStart.addActionListener(e -> {
			try {
				setVisibleAnswerToTrue();
				btnQuizStart.setEnabled(false);
				btnQuizNextQuestion.setEnabled(true);
				btnQuizStop.setEnabled(true);
				btnQuizAnswerA.setBackground(this.getBackground());
				btnQuizAnswerB.setBackground(this.getBackground());
				btnQuizAnswerC.setBackground(this.getBackground());
				btnQuizAnswerD.setBackground(this.getBackground());
				quizGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnQuizNextQuestion.addActionListener(e -> {
			try {
				setVisibleAnswerToTrue();
				btnQuizAnswerA.setBackground(this.getBackground());
				btnQuizAnswerB.setBackground(this.getBackground());
				btnQuizAnswerC.setBackground(this.getBackground());
				btnQuizAnswerD.setBackground(this.getBackground());
				quizGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnQuizStop.addActionListener(e -> {
			btnQuizStart.setEnabled(true);
			btnQuizNextQuestion.setEnabled(false);
			btnQuizStop.setEnabled(false);
			setVisibleAnswerToFalse();
		});
		
		btnQuizAnswerA.addActionListener(e -> {
			String answer = btnQuizAnswerA.getText();
			if (checkAnswer(answer.substring(3))) {
				JOptionPane.showMessageDialog(this, "This is the correct answer", "Notification", JOptionPane.INFORMATION_MESSAGE);
				btnQuizAnswerA.setBackground(Color.GREEN);
			} else {
				JOptionPane.showMessageDialog(this, "Wrong answer", "Notification", JOptionPane.ERROR_MESSAGE);
				btnQuizAnswerA.setBackground(Color.RED);
			}
			setVisibleAnswerToFalse();
			showTrueAnswer();
		});
		
		btnQuizAnswerB.addActionListener(e -> {
			String answer = btnQuizAnswerB.getText();
			if (checkAnswer(answer.substring(3))) {
				JOptionPane.showMessageDialog(this, "This is the correct answer", "Notification", JOptionPane.INFORMATION_MESSAGE);
				btnQuizAnswerB.setBackground(Color.GREEN);
			} else {
				JOptionPane.showMessageDialog(this, "Wrong answer", "Notification", JOptionPane.ERROR_MESSAGE);
				btnQuizAnswerB.setBackground(Color.RED);
			}
			setVisibleAnswerToFalse();
			showTrueAnswer();
		});
		
		btnQuizAnswerC.addActionListener(e -> {
			String answer = btnQuizAnswerC.getText();
			if (checkAnswer(answer.substring(3))) {
				JOptionPane.showMessageDialog(this, "This is the correct answer", "Notification", JOptionPane.INFORMATION_MESSAGE);
				btnQuizAnswerC.setBackground(Color.GREEN);
			} else {
				JOptionPane.showMessageDialog(this, "Wrong answer", "Notification", JOptionPane.ERROR_MESSAGE);
				btnQuizAnswerC.setBackground(Color.RED);
			}
			setVisibleAnswerToFalse();
			showTrueAnswer();
		});
		
		btnQuizAnswerD.addActionListener(e -> {
			String answer = btnQuizAnswerD.getText();
			if (checkAnswer(answer.substring(3))) {
				JOptionPane.showMessageDialog(this, "This is the correct answer", "Notification", JOptionPane.INFORMATION_MESSAGE);
				btnQuizAnswerD.setBackground(Color.GREEN);
			} else {
				JOptionPane.showMessageDialog(this, "Wrong answer", "Notification", JOptionPane.ERROR_MESSAGE);
				btnQuizAnswerD.setBackground(Color.RED);
			}
			setVisibleAnswerToFalse();
			showTrueAnswer();
		});
	}

	public void quizGame() throws IOException {
		result = slangWord.quizGame();
		
		lblQuizQuestion.setText("What does \'" + result.get("question").get(0) + "\' mean?");
		
		List<String> answers = result.get("answers");
		btnQuizAnswerA.setText("A: " + answers.get(0));
		btnQuizAnswerB.setText("B: " + answers.get(1));
		btnQuizAnswerC.setText("C: " + answers.get(2));
		btnQuizAnswerD.setText("D: " + answers.get(3));
	}
	
	public boolean checkAnswer(String answer) {
		String correctAnswer = result.get("correctAnswer").get(0);
		return answer.equals(correctAnswer);
	}
	
	public void showTrueAnswer() {
		String answerA = btnQuizAnswerA.getText();
		String answerB = btnQuizAnswerB.getText();
		String answerC = btnQuizAnswerC.getText();
		String answerD = btnQuizAnswerD.getText();
		if(checkAnswer(answerA.substring(3))) {
			btnQuizAnswerA.setBackground(Color.GREEN);
		}
		if(checkAnswer(answerB.substring(3))) {
			btnQuizAnswerB.setBackground(Color.GREEN);
		}
		if(checkAnswer(answerC.substring(3))) {
			btnQuizAnswerC.setBackground(Color.GREEN);
		}
		if(checkAnswer(answerD.substring(3))) {
			btnQuizAnswerD.setBackground(Color.GREEN);
		}
	}
	
	public void setVisibleAnswerToFalse() {
		btnQuizAnswerA.setEnabled(false);
		btnQuizAnswerB.setEnabled(false);
		btnQuizAnswerC.setEnabled(false);
		btnQuizAnswerD.setEnabled(false);
	}
	
	public void setVisibleAnswerToTrue() {
		btnQuizAnswerA.setEnabled(true);
		btnQuizAnswerB.setEnabled(true);
		btnQuizAnswerC.setEnabled(true);
		btnQuizAnswerD.setEnabled(true);
	}
}
