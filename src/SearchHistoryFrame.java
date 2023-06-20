import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchHistoryFrame extends JFrame {
	
	private String[] columnNames = new String [] {
            "No.", "Slang", "Definition"};

	private SlangWord slangWord;
	private String oldDefinition;
	private JTable tbSlangWord;
	private JButton btnBack;

	public SearchHistoryFrame() throws IOException {
		initialize();
		initializeEvent();
		slangWord = SlangWord.getInstance();
		slangWord.readFile(FileNameUtils.HISTORY);
		displaySlangWord(slangWord.getData());
	}

	public void initialize() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 19));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 94, 502, 557);
		
		JLabel lblNewLabel = new JLabel("Search History");
		lblNewLabel.setBounds(0, 28, 909, 41);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		tbSlangWord = new JTable();
		tbSlangWord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane.setViewportView(tbSlangWord);
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		getContentPane().add(lblNewLabel);
		
		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnBack.setEnabled(true);
		btnBack.setBounds(736, 602, 152, 49);
		getContentPane().add(btnBack);
		this.setBounds(100, 100, 923, 732);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}
	
}
