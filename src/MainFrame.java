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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

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
	private JButton btnUpdate;
	private JLabel lblQuiz;
	private JLabel lblQuizMode;
	private JComboBox cbQuizMode;
	
	private String[] columnNames = new String [] {
            "No.", "Slang", "Definition"};

	private SlangWord slangWord;
	private String oldDefinition;
	private JTable tbSlangWord;
	private JButton btnRestoreDefault;
	private JButton btnDelete;
	private JButton btnClear;
	
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
		initializeEvent();
		slangWord = SlangWord.getInstance();	
		slangWord.readFile(FileNameUtils.DEFAULT);
		displaySlangWord(slangWord.getData());
		random();
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
		
		btnRandom = new JButton("See More");
		btnRandom.setBounds(855, 61, 131, 31);
		btnRandom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		cbSearchType = new JComboBox();
		cbSearchType.setModel(new DefaultComboBoxModel(new String[] {"Slang", "Definition"}));
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
		btnDelete.setBounds(806, 691, 105, 31);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(340, 331, 131, 31);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
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
		getContentPane().add(btnUpdate);
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
		btnRestoreDefault.setBounds(955, 691, 197, 31);
		getContentPane().add(btnRestoreDefault);
		
		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnClear.setBounds(537, 331, 131, 31);
		getContentPane().add(btnClear);
		this.setBounds(100, 100, 1250, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initializeEvent() {
		tbSlangWord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillSlangWordFromTable();
			}
		});
		
		btnClear.addActionListener(e -> clearSlangWord());
		
		btnAdd.addActionListener(e -> {
			try {
				add();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		btnUpdate.addActionListener(e -> {
			try {
				update();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		btnDelete.addActionListener(e -> {
			try {
				delete();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		
		btnSearch.addActionListener(e -> {
			try {
				search();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	public void displaySlangWord(Map<String, List<String>> slangWords) throws IOException {
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		int i = 0;
		Object[] data;
		for (Entry<String, List<String>> entry : slangWords.entrySet()) {
			List<String> definitions = entry.getValue();
			for(String definition : definitions) {
				data = new Object[] {
						++i, entry.getKey(), definition 
				};
				model.addRow(data);
			}
		}
		tbSlangWord.setModel(model);
		
		tbSlangWord.getColumnModel().getColumn(0).setMaxWidth(50);
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}
	
	public void fillSlangWordFromTable() {
		int index = tbSlangWord.getSelectedRow();
		txtSlang.setText(tbSlangWord.getValueAt(index, 1).toString());
		txtDefinition.setText(tbSlangWord.getValueAt(index, 2).toString());
		oldDefinition = tbSlangWord.getValueAt(index, 2).toString();
		
		txtSlang.setEnabled(false);
		btnAdd.setEnabled(false);		
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
	}

	public void clearSlangWord() {
		txtSlang.setText("");
		txtDefinition.setText("");
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}
	
	public void random() {
		Map.Entry<String, String> result = slangWord.random().entrySet().iterator().next();
		lblRandom.setText(result.getKey() + ": " + result.getValue());
	}
	
	public boolean validateSlangWord() {
		return "".equals(txtSlang.getText().trim()) || "".equals(txtDefinition.getText().trim());
	}
	
	public void add() throws IOException {
		if(validateSlangWord()) {
			JOptionPane.showMessageDialog(this, "Slang or definition can't be blank", "Notification", JOptionPane.ERROR_MESSAGE);
		} else {
			String slang = txtSlang.getText();
			String definition = txtDefinition.getText();
			
			if(slangWord.isExists(slang)) {
				Object[] options = { "Duplicate", "Override" };
				int n = JOptionPane.showOptionDialog(this,
						"Slang '" + slang + "' have already exist on  SlangWord  List", "Notification",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if(n == 0) { // override
					slangWord.add(slang, definition, AddType.DUPLICATE);
				} else if (n == 1) { // duplicate
					slangWord.add(slang, definition, AddType.OVERRIDE);
				}
			} else {
				slangWord.add(slang, definition, AddType.NEW);
			}
			
			JOptionPane.showMessageDialog(this, "Add Successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
			clearSlangWord();
			displaySlangWord(slangWord.getData());
		}
	}
	
	public void update() throws IOException {
		if(validateSlangWord()) {
			JOptionPane.showMessageDialog(this, "Definition can't be blank", "Notification", JOptionPane.ERROR_MESSAGE);
		} else {
			String slang = txtSlang.getText();
			String definition = txtDefinition.getText();
			slangWord.update(slang, definition, oldDefinition);	
			
			JOptionPane.showMessageDialog(this, "Update Successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
			clearSlangWord();
			displaySlangWord(slangWord.getData());
		}
	}
	
	public void delete() throws IOException {
		int index = tbSlangWord.getSelectedRow();
		String slang = tbSlangWord.getValueAt(index, 1).toString();
		String definition = tbSlangWord.getValueAt(index, 2).toString();
		slangWord.delete(slang, definition);	
		
		JOptionPane.showMessageDialog(this, "Delete Successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
		clearSlangWord();
		displaySlangWord(slangWord.getData());
		btnDelete.setEnabled(false);
	}
	
	public void search() throws IOException {
		String keyword = txtKeyword.getText();
		if("".equals(keyword.trim())) {
			displaySlangWord(slangWord.getData());
		} else {
			Map<String, List<String>> result = new LinkedHashMap<>();
			String searchType = cbSearchType.getSelectedItem().toString();
			
			if ("Slang".equals(searchType)) {
				result = slangWord.findBySlang(keyword);
			} else if ("Definition".equals(searchType)) {
				result = slangWord.findByDefinition(keyword);
			}
			displaySlangWord(result);
		}
	}
}
