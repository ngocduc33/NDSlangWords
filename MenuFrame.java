import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class MainFrame extends JFrame {
	private JTextField txtKeyword;
	private JTextField txtSlang;
	private JTextField txtDefinition;
	private JButton btnHistory;
	private JButton btnSearch;
	private JComboBox cbSearchType;
	private JLabel lblRandom;
	private JButton btnRandom;
	private JLabel lblKeyword;
	private JLabel lblSlang;
	private JLabel lblDefinition;
	private JButton btnAdd;
	private JButton btnEdit;
	private JLabel lblQuiz;
	private JLabel lblQuizMode;
	private JComboBox cbQuizMode;
	
	private String[] columnNames = new String [] {
            "No.", "Slang", "Definition"};

	private SlangWord slangWord;
	private JTable tbSlangWord;
	private JButton btnRestoreDefault;
	private JButton btnDelete;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public MainFrame() throws IOException {
		initialize();
		slangWord = SlangWord.getInstance();
		display();
	}

	public void initialize() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 19));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(724, 115, 502, 557);
		
		JLabel lblNewLabel = new JLabel("Slang Words Dictionary");
		lblNewLabel.setBounds(0, 10, 1236, 41);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		lblKeyword = new JLabel("Keyword:");
		lblKeyword.setBounds(29, 116, 98, 23);
		lblKeyword.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		lblRandom = new JLabel("#!@#: ...................................");
		lblRandom.setBounds(337, 61, 508, 31);
		lblRandom.setHorizontalAlignment(SwingConstants.CENTER);
		lblRandom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnRandom = new JButton("Random");
		btnRandom.setBounds(855, 61, 131, 31);
		btnRandom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		cbSearchType = new JComboBox();
		cbSearchType.setBounds(527, 115, 141, 31);
		cbSearchType.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(137, 166, 131, 31);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnHistory = new JButton("History");
		btnHistory.setBounds(306, 166, 131, 31);
		btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		lblSlang = new JLabel("Slang:");
		lblSlang.setBounds(29, 236, 98, 23);
		lblSlang.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		txtKeyword = new JTextField();
		txtKeyword.setBounds(137, 116, 368, 29);
		txtKeyword.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtKeyword.setColumns(10);
		
		lblDefinition = new JLabel("Definition:");
		lblDefinition.setBounds(29, 280, 98, 23);
		lblDefinition.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(137, 331, 131, 31);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(443, 331, 131, 31);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(286, 331, 131, 31);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		txtSlang = new JTextField();
		txtSlang.setBounds(137, 233, 531, 29);
		txtSlang.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtSlang.setColumns(10);	
		
		tbSlangWord = new JTable();
		tbSlangWord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane.setViewportView(tbSlangWord);
		getContentPane().setLayout(null);
		getContentPane().add(btnHistory);
		getContentPane().add(cbSearchType);
		getContentPane().add(lblRandom);
		getContentPane().add(btnAdd);
		getContentPane().add(btnDelete);
		getContentPane().add(btnEdit);
		getContentPane().add(lblDefinition);
		getContentPane().add(txtSlang);
		getContentPane().add(lblKeyword);
		getContentPane().add(txtKeyword);
		getContentPane().add(lblSlang);
		getContentPane().add(btnSearch);
		getContentPane().add(scrollPane);
		getContentPane().add(lblNewLabel);
		getContentPane().add(btnRandom);
		
		txtDefinition = new JTextField();
		txtDefinition.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtDefinition.setColumns(10);
		txtDefinition.setBounds(137, 277, 531, 29);
		getContentPane().add(txtDefinition);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 372, 639, 371);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblQuiz = new JLabel("Quiz Game");
		lblQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuiz.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuiz.setBounds(0, 10, 639, 23);
		panel.add(lblQuiz);
		
		lblQuizMode = new JLabel("Mode:");
		lblQuizMode.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblQuizMode.setBounds(10, 47, 98, 23);
		panel.add(lblQuizMode);
		
		cbQuizMode = new JComboBox();
		cbQuizMode.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cbQuizMode.setBounds(111, 43, 141, 31);
		panel.add(cbQuizMode);
		
		JLabel lblQuestionTitle = new JLabel("Question:");
		lblQuestionTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblQuestionTitle.setBounds(10, 101, 98, 23);
		panel.add(lblQuestionTitle);
		
		JLabel lblQuizQuestion = new JLabel("");
		lblQuizQuestion.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblQuizQuestion.setBounds(111, 101, 528, 23);
		panel.add(lblQuizQuestion);
		
		JButton btnQuizAnswerA = new JButton("A.");
		btnQuizAnswerA.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnQuizAnswerA.setBounds(10, 149, 290, 56);
		panel.add(btnQuizAnswerA);
		
		JButton btnQuizAnswerB = new JButton("B.");
		btnQuizAnswerB.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnQuizAnswerB.setBounds(339, 149, 290, 56);
		panel.add(btnQuizAnswerB);
		
		JButton btnQuizAnswerC = new JButton("C.");
		btnQuizAnswerC.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnQuizAnswerC.setBounds(10, 241, 290, 56);
		panel.add(btnQuizAnswerC);
		
		JButton btnQuizAnswerD = new JButton("D.");
		btnQuizAnswerD.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnQuizAnswerD.setBounds(339, 241, 290, 56);
		panel.add(btnQuizAnswerD);
		
		JButton btnQuizStart = new JButton("Start");
		btnQuizStart.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnQuizStart.setBounds(64, 319, 131, 31);
		panel.add(btnQuizStart);
		
		JButton btnQuizNextQuestion = new JButton("Next Question");
		btnQuizNextQuestion.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnQuizNextQuestion.setBounds(240, 319, 153, 31);
		panel.add(btnQuizNextQuestion);
		
		JButton btnQuizStop = new JButton("Stop");
		btnQuizStop.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnQuizStop.setBounds(437, 319, 131, 31);
		panel.add(btnQuizStop);
		
		btnRestoreDefault = new JButton("Restore Default");
		btnRestoreDefault.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnRestoreDefault.setBounds(871, 691, 197, 31);
		getContentPane().add(btnRestoreDefault);
		this.setBounds(100, 100, 1250, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void display() throws IOException {
		if (slangWord.readFile(FileNameUtils.DEFAULT)) {
			DefaultTableModel model = new DefaultTableModel(null, columnNames);
			int i = 0;
			Object[] data;
			for (Entry<String, List<String>> entry : slangWord.getData().entrySet()) {
				List<String> definitions = entry.getValue();
				for(String definition : definitions) {
					data = new Object[] {
							++i, entry.getKey(), definition 
					};
					model.addRow(data);
				}
			}
			tbSlangWord.setModel(model);
		}
	
		tbSlangWord.getColumnModel().getColumn(0).setMaxWidth(50);
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
	}
	
}
