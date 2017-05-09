package rflint2740ex3i;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings({ "unused", "serial" })
public class ExamForm extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private JList responsesList;
	@SuppressWarnings("rawtypes")
	private DefaultListModel responsesListModel;
	private JLabel resultLabel;
	private JLabel questNumLabel;
	private JTextField InputAnswerTextField;
	private JButton prevButton;
	private JButton nextButton;
	private DriverExam exam;
	private JLabel inputAnswerTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExamForm() {
		setEnabled(false);
		setTitle("Ex3I Driver Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResponses = new JLabel("Responses:");
		lblResponses.setBounds(10, 11, 79, 14);
		contentPane.add(lblResponses);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(140, 11, 46, 14);
		contentPane.add(lblResult);
		
		JList questNumList = new JList();
		questNumList.setBackground(UIManager.getColor("Button.background"));
		questNumList.setEnabled(false);
		questNumList.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		questNumList.setBounds(20, 37, 32, 165);
		contentPane.add(questNumList);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				do_responsesList_valueChanged(e);
			}
		});
		responsesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responsesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsesList.setBounds(72, 36, 32, 165);
		contentPane.add(responsesList);
		
		resultLabel = new JLabel("");
		resultLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		resultLabel.setBackground(UIManager.getColor("Button.background"));
		resultLabel.setBounds(128, 36, 129, 28);
		contentPane.add(resultLabel);
		
		JButton calcPassButton = new JButton("Pass");
		calcPassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_calcPassButton_actionPerformed(arg0);
			}
		});
		calcPassButton.setBounds(138, 75, 102, 23);
		contentPane.add(calcPassButton);
		
		JButton calcCorrectButton = new JButton("Correct");
		calcCorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcCorrectButton_actionPerformed(e);
			}
		});
		calcCorrectButton.setBounds(140, 109, 100, 23);
		contentPane.add(calcCorrectButton);
		
		JButton calcIncorrectButton = new JButton("Incorrect");
		calcIncorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcIncorrectButton_actionPerformed(e);
			}
		});
		calcIncorrectButton.setBounds(140, 143, 100, 23);
		contentPane.add(calcIncorrectButton);
		
		JButton listIncorrectButton = new JButton("List Incorrect");
		listIncorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_listIncorrectButton_actionPerformed(e);
			}
		});
		listIncorrectButton.setBounds(140, 177, 100, 23);
		contentPane.add(listIncorrectButton);
		
		questNumLabel = new JLabel("#0:");
		questNumLabel.setBounds(10, 213, 32, 14);
		contentPane.add(questNumLabel);
		
		InputAnswerTextField = new JTextField();
		InputAnswerTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_InputAnswerTextField_focusGained(arg0);
			}
		});
		InputAnswerTextField.setBounds(72, 210, 32, 20);
		contentPane.add(InputAnswerTextField);
		InputAnswerTextField.setColumns(10);
		
		prevButton = new JButton("Prev");
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_prevButton_actionPerformed(e);
			}
		});
		prevButton.setEnabled(false);
		prevButton.setBounds(114, 209, 72, 23);
		contentPane.add(prevButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_nextButton_actionPerformed(e);
			}
		});
		nextButton.setBounds(114, 237, 72, 23);
		contentPane.add(nextButton);
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		this.exam = mapper.getAllDriverExam();
		this.responsesListModel = exam.getAnswers();
		responsesList.setModel(this.responsesListModel);
	}
	@SuppressWarnings("rawtypes")
	protected void do_calcPassButton_actionPerformed(ActionEvent arg0) {
		this. exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
		
		}
		else {
			if (exam.passed()) resultLabel.setText("You Passed");
			else resultLabel.setText("You Failed");
		}
	}
	@SuppressWarnings("rawtypes")
	protected void do_calcCorrectButton_actionPerformed(ActionEvent e) {
		this. exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected void do_calcIncorrectButton_actionPerformed(ActionEvent e) {
		this. exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1)); 
			}
	}
	@SuppressWarnings("rawtypes")
	protected void do_listIncorrectButton_actionPerformed(ActionEvent e) {
		this. exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
		}
	}
	@SuppressWarnings("unchecked")
	protected void do_prevButton_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(
        inputAnswerTextField.getText().toUpperCase(), 
responsesList.getSelectedIndex());
				responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
			    questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
				inputAnswerTextField.setText((String)responsesList.getSelectedValue());

				nextButton.setEnabled(true);
				if (responsesList.getSelectedIndex() == 0) 
				prevButton.setEnabled(false);
				inputAnswerTextField.requestFocus();
	}
	@SuppressWarnings("unchecked")
	protected void do_nextButton_actionPerformed(ActionEvent e) {
		 this.responsesListModel.setElementAt(
				 inputAnswerTextField.getText().toUpperCase(), 
				 responsesList.getSelectedIndex());
				 responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
				 questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
				 inputAnswerTextField.setText((String)responsesList.getSelectedValue());
				 
				 prevButton.setEnabled(true);
				 if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
				 nextButton.setEnabled(false);
				 inputAnswerTextField.requestFocus();
	}
	protected void do_responsesList_valueChanged(ListSelectionEvent e) {
		questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		inputAnswerTextField.setText((String)responsesList.getSelectedValue());

		prevButton.setEnabled(true);
		nextButton.setEnabled(true);
		if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
		nextButton.setEnabled(false);
	    if (responsesList.getSelectedIndex() == 0) 
		prevButton.setEnabled(false);
		inputAnswerTextField.requestFocus();
	}
	protected void do_InputAnswerTextField_focusGained(FocusEvent arg0) {
		 InputAnswerTextField.selectAll();
	}
}
