package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import exception.ManagerException;
import vo.Tango;

public class ClientQuizUI extends JFrame implements ActionListener {
	private JPanel btn_panel;
	private JTable tangoTable; 
	private DefaultTableModel defaultTableModel;
	private ClientManager cm = new ClientManager();
	private String [][] quizAnswer;
	private int totalNum; 
	public ClientQuizUI(){
		super();
	}
	
	public ClientQuizUI(ArrayList<Tango> quizList, int testCase) throws ManagerException {
		
		//Frame ����
		setTitle("�ܾ�����");
		
		//��ư �߰�
		btn_panel = new JPanel();
		addButton("����",btn_panel);
		
		//���̺� ����
		String columnNames[] =
			{"��ȣ", "����", "���󰡳�", "��","��Ʈ"};
			
		//���̺� default�� => �����
		Object[][] rowData = {};
		
		//DefaultTableModel�� �����ϰ� ������ ���
		defaultTableModel = new DefaultTableModel(rowData, columnNames);
		
		quizAnswer = new String [quizList.size()][4];
		//�� �߰�
		int row = 0;
		for(Tango tango :quizList){			
			String hanja = tango.getHanja();
			String hiragana = tango.getHiragana();
			String meaning = tango.getMeaning();
			Object [] tangoRow = { tango.getRow_id() ,hanja, hiragana, meaning, ""};
			defaultTableModel.addRow(tangoRow);
			quizAnswer[row][1] = hanja;
			quizAnswer[row][2] = hiragana;
			quizAnswer[row][3] = meaning;
			row++;
		}
		//JTable�� DefaultTableModel�� ���
		tangoTable = new JTable(defaultTableModel){
            //  ���� �ּҰ� �������� �ٲ㼭 �ֱ�
            public Class getColumnClass(int column){               
            	
            	if (getRowCount() > 0) {
                    return getValueAt(0, column).getClass();
                 }
                 return super.getColumnClass(column);
              } 
            @Override
            public boolean isCellEditable(int row, int column) {
				if (column == 0) return false;			   
	        	switch(testCase){
            	//���ڸ� �Է��ؾ��ϴ� ���
        		case 1 : 
        			if(column == 2) return false;
        			if(column == 3) return false;
        			break;
        		//���󰡳��� �Է��ؾ��ϴ� ���
        		case 5 : 
        			if(column == 1) return false;
        			if(column == 3) return false;
        			break;
        		//�游 �Է��ؾ��ϴ� ���
        		case 9 : 
        			if(column == 1) return false;
        			if(column == 2) return false;
        			break;
        		//���ڿ� ���󰡳��� �Է��ؾ��ϴ� ���
        		case 2 : 
        			if(column == 3) return false;
        			break;
        		//���󰡳��� ���� �Է��ؾ��ϴ� ���
        		case 6 : 
        			if(column == 1) return false;
        			break;
        		//���ڿ� ���� �Է��ؾ��ϴ� ���
        		case 10 : 
        			if(column == 2) return false;
        			break;
        		//��� �Է��ؾ��ϴ� ���
        		case 3: return true;        			
	        	}
				return true;					        	
            }
            
        };         
        tangoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//JTable ũ��/ ���� / ��Ʈ / ���� ���� ���� ����
		tangoTable.setFont(new Font("a����������3", Font.PLAIN, 32));
		tangoTable.getTableHeader().setFont(new Font("a����������3", Font.PLAIN, 32));
		tangoTable.setRowHeight(100);
		tangoTable.getTableHeader().setReorderingAllowed(false);
		tableCellCenter(tangoTable);
		
		
		//��ư �߰�
		ButtonCellRenderer renderer = new ButtonCellRenderer(tangoTable, defaultTableModel, quizList);
		TableColumn hintColumn = tangoTable.getColumnModel().getColumn(4);
		hintColumn.setCellEditor(renderer);
		hintColumn.setCellRenderer(renderer);

		
		//JScrollPane�� JTable�� ���
		JScrollPane jScollPane = new JScrollPane(tangoTable);		
		
		int rowNum = tangoTable.getRowCount();
		//testCase�� ���� ���躼 Cell ����α�
		switch(testCase){
    	//���ڸ� �Է��ؾ��ϴ� ���
		case 1 : 
			for(int i = 0; i<rowNum; i++) tangoTable.setValueAt("", i, 1);
			break;
		//���󰡳��� �Է��ؾ��ϴ� ���
		case 5 : 
			for(int i = 0; i<rowNum; i++) tangoTable.setValueAt("", i, 2);
			break;
		//�游 �Է��ؾ��ϴ� ���
		case 9 : 
			for(int i = 0; i<rowNum; i++) tangoTable.setValueAt("", i, 3);
			break;
		//���ڿ� ���󰡳��� �Է��ؾ��ϴ� ���
		case 2 : 
			for(int i = 0; i<rowNum; i++) {
				tangoTable.setValueAt("", i, 1);
				tangoTable.setValueAt("", i, 2);
			}
			break;
		//���󰡳��� ���� �Է��ؾ��ϴ� ���
		case 6 : 
			for(int i = 0; i<rowNum; i++) {
				tangoTable.setValueAt("", i, 2);
				tangoTable.setValueAt("", i, 3);
			}
			break;
		//���ڿ� ���� �Է��ؾ��ϴ� ���
		case 10 : 
			for(int i = 0; i<rowNum; i++) {
				tangoTable.setValueAt("", i, 1);
				tangoTable.setValueAt("", i, 3);
			}
			break;
		//��� �Է��ؾ��ϴ� ���
		case 3: for(int i = 0; i<rowNum; i++){
			tangoTable.setValueAt("", i, 1);
			tangoTable.setValueAt("", i, 2);
			tangoTable.setValueAt("", i, 3);
			}
			break;
		}
		
		//�� ���� ���� ����
		totalNum = (testCase %4) * rowNum;
		
		
		//JFrame ����������		
		setLayout(new BorderLayout());
		btn_panel.setLayout(new GridLayout(0,1));
		add(jScollPane, BorderLayout.CENTER);		
		add(btn_panel,BorderLayout.PAGE_END);
		setSize(1028, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//��ư �߰� �޼ҵ�
	private void addButton(String str, Container target){
		JButton button = new JButton(str);
		button.addActionListener(this);
		target.add(button);
	}
	
	// ���̺� ���� ��� �����ϱ�
	 public void tableCellCenter(JTable t){
      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // ����Ʈ���̺��������� ����
      dtcr.setHorizontalAlignment(SwingConstants.CENTER); // �������� ���������� CENTER��     
      TableColumnModel tcm = t.getColumnModel() ; // ������ ���̺��� �÷����� ������
            
      //Ư�� ���� ����
      tcm.getColumn(0).setCellRenderer(dtcr);  
      tcm.getColumn(1).setCellRenderer(dtcr);  
      tcm.getColumn(2).setCellRenderer(dtcr);  
      tcm.getColumn(3).setCellRenderer(dtcr);  
    }
	 
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getActionCommand();
		// �̺�Ʈ�κ��� �̺�Ʈ�� �߻���Ų �ҽ��� ���Ѵ�
		//�������� �� �������
		if(source == "����") {
			int select =JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�", "����Ȯ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(select == JOptionPane.OK_OPTION){
				int row = tangoTable.getRowCount();
				int wrongNum = 0;
				for(int i = 0; i <row; i++){
					boolean resultHanja 	= tangoTable.getValueAt(i, 1).equals(quizAnswer[i][1]);
					boolean resultHiragana 	= tangoTable.getValueAt(i, 2).equals(quizAnswer[i][2]);
					boolean resultMeaning 	= tangoTable.getValueAt(i, 3).equals(quizAnswer[i][3]);
					
					
					if(!resultHanja){
						wrongNum++;  
						System.out.println("���� : ("+i+",1)���� Ʋ�� \nƲ������ : "+ wrongNum);
					}
					if(!resultHiragana){
						wrongNum++;  
						System.out.println("���󰡳� : ("+i+",2)���� Ʋ�� \nƲ������ : "+ wrongNum);
					}
					if(!resultMeaning){
						wrongNum++;  
						System.out.println("�� : ("+i+",3)���� Ʋ�� \nƲ������ : "+ wrongNum);
					}
				}
				//���� ����� �޾ƿ���
				//int quizResult = cm.getQuizResult(wrongNum);
				int quizResult = 1;
				int correctNum = totalNum - wrongNum;
				int score = (correctNum / totalNum)*100;
				System.out.println("���� ���� : " 	+ correctNum);
				System.out.println("���� ����: " 	+ totalNum);
				System.out.println("�� ���� : " 	+ score);
				
				switch(quizResult){
				//�̰��� ��
				case 1 : new Quiz_Result_Win(correctNum , totalNum, score);  break;
				case 2 : new Quiz_Result_Fail(correctNum , totalNum, score); break;
				case 3 : new Quiz_Result_Draw(correctNum , totalNum, score); break;
				}
				dispose();
			}
		}		
	}	  
	
	public static void main(String[] args) throws ManagerException {
		int quizNum = 3;
		ClientManager cm = new ClientManager();
		ArrayList<Tango> quizList = cm.getQuizList(quizNum);
		if(quizList != null)System.out.println("[System] �ܾ��� �������� ����");
		
		new ClientQuizUI(quizList, 5);
	}
}

// ��Ʈ ��ư ������ ���� Ŭ����
class ButtonCellRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
	private final JButton button;
	private ClientManager cm = new ClientManager();
	
	public ButtonCellRenderer(final JTable table, final DefaultTableModel model , ArrayList<Tango> quizList) {
		this.button = new JButton("��Ʈ");
		//��ư�� ������ ��� ��Ʈ �����ֱ�
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seletedRow = table.getSelectedRow();
				int row_id = (int) table.getValueAt(seletedRow, 0);
				for(Tango t : quizList){
					if(t.getRow_id() == row_id){
						JFrame hint = new JFrame();
						ImageIcon image = cm.getScaledImage(t.getimagebuf(), 400, 400);
						JLabel imageLabel = new JLabel(image);
						hint.add(imageLabel);
						hint.setSize(500, 500);
						hint.setVisible(true);						
						break;
					}
				}
			}
		});
	}	
		
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return button;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return button;
	}

	public Object getCellEditorValue() {
		return button.getText();
	}
	
}
