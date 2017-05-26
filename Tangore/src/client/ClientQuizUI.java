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
		
		//Frame 생성
		setTitle("단어퀴즈");
		
		//버튼 추가
		btn_panel = new JPanel();
		addButton("제출",btn_panel);
		
		//테이블 생성
		String columnNames[] =
			{"번호", "한자", "히라가나", "뜻","힌트"};
			
		//테이블 default값 => 비워둠
		Object[][] rowData = {};
		
		//DefaultTableModel을 선언하고 데이터 담기
		defaultTableModel = new DefaultTableModel(rowData, columnNames);
		
		quizAnswer = new String [quizList.size()][4];
		//행 추가
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
		//JTable에 DefaultTableModel을 담기
		tangoTable = new JTable(defaultTableModel){
            //  사진 주소값 사진으로 바꿔서 넣기
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
            	//한자만 입력해야하는 경우
        		case 1 : 
        			if(column == 2) return false;
        			if(column == 3) return false;
        			break;
        		//히라가나만 입력해야하는 경우
        		case 5 : 
        			if(column == 1) return false;
        			if(column == 3) return false;
        			break;
        		//뜻만 입력해야하는 경우
        		case 9 : 
        			if(column == 1) return false;
        			if(column == 2) return false;
        			break;
        		//한자와 히라가나를 입력해야하는 경우
        		case 2 : 
        			if(column == 3) return false;
        			break;
        		//히라가나와 뜻을 입력해야하는 경우
        		case 6 : 
        			if(column == 1) return false;
        			break;
        		//한자와 뜻을 입력해야하는 경우
        		case 10 : 
        			if(column == 2) return false;
        			break;
        		//모두 입력해야하는 경우
        		case 3: return true;        			
	        	}
				return true;					        	
            }
            
        };         
        tangoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//JTable 크기/ 정렬 / 폰트 / 수정 가능 여부 지정
		tangoTable.setFont(new Font("a옛날사진관3", Font.PLAIN, 32));
		tangoTable.getTableHeader().setFont(new Font("a옛날사진관3", Font.PLAIN, 32));
		tangoTable.setRowHeight(100);
		tangoTable.getTableHeader().setReorderingAllowed(false);
		tableCellCenter(tangoTable);
		
		
		//버튼 추가
		ButtonCellRenderer renderer = new ButtonCellRenderer(tangoTable, defaultTableModel, quizList);
		TableColumn hintColumn = tangoTable.getColumnModel().getColumn(4);
		hintColumn.setCellEditor(renderer);
		hintColumn.setCellRenderer(renderer);

		
		//JScrollPane에 JTable을 담기
		JScrollPane jScollPane = new JScrollPane(tangoTable);		
		
		int rowNum = tangoTable.getRowCount();
		//testCase에 따라서 시험볼 Cell 비워두기
		switch(testCase){
    	//한자만 입력해야하는 경우
		case 1 : 
			for(int i = 0; i<rowNum; i++) tangoTable.setValueAt("", i, 1);
			break;
		//히라가나만 입력해야하는 경우
		case 5 : 
			for(int i = 0; i<rowNum; i++) tangoTable.setValueAt("", i, 2);
			break;
		//뜻만 입력해야하는 경우
		case 9 : 
			for(int i = 0; i<rowNum; i++) tangoTable.setValueAt("", i, 3);
			break;
		//한자와 히라가나를 입력해야하는 경우
		case 2 : 
			for(int i = 0; i<rowNum; i++) {
				tangoTable.setValueAt("", i, 1);
				tangoTable.setValueAt("", i, 2);
			}
			break;
		//히라가나와 뜻을 입력해야하는 경우
		case 6 : 
			for(int i = 0; i<rowNum; i++) {
				tangoTable.setValueAt("", i, 2);
				tangoTable.setValueAt("", i, 3);
			}
			break;
		//한자와 뜻을 입력해야하는 경우
		case 10 : 
			for(int i = 0; i<rowNum; i++) {
				tangoTable.setValueAt("", i, 1);
				tangoTable.setValueAt("", i, 3);
			}
			break;
		//모두 입력해야하는 경우
		case 3: for(int i = 0; i<rowNum; i++){
			tangoTable.setValueAt("", i, 1);
			tangoTable.setValueAt("", i, 2);
			tangoTable.setValueAt("", i, 3);
			}
			break;
		}
		
		//총 문제 개수 지정
		totalNum = (testCase %4) * rowNum;
		
		
		//JFrame 나머지설정		
		setLayout(new BorderLayout());
		btn_panel.setLayout(new GridLayout(0,1));
		add(jScollPane, BorderLayout.CENTER);		
		add(btn_panel,BorderLayout.PAGE_END);
		setSize(1028, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//버튼 추가 메소드
	private void addButton(String str, Container target){
		JButton button = new JButton(str);
		button.addActionListener(this);
		target.add(button);
	}
	
	// 테이블 내용 가운데 정렬하기
	 public void tableCellCenter(JTable t){
      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
      dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로     
      TableColumnModel tcm = t.getColumnModel() ; // 정렬할 테이블의 컬럼모델을 가져옴
            
      //특정 열에 지정
      tcm.getColumn(0).setCellRenderer(dtcr);  
      tcm.getColumn(1).setCellRenderer(dtcr);  
      tcm.getColumn(2).setCellRenderer(dtcr);  
      tcm.getColumn(3).setCellRenderer(dtcr);  
    }
	 
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getActionCommand();
		// 이벤트로부터 이벤트를 발생시킨 소스를 구한다
		//제출했을 때 점수계산
		if(source == "제출") {
			int select =JOptionPane.showConfirmDialog(null, "정말로 제출하시겠습니까", "제출확인", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(select == JOptionPane.OK_OPTION){
				int row = tangoTable.getRowCount();
				int wrongNum = 0;
				for(int i = 0; i <row; i++){
					boolean resultHanja 	= tangoTable.getValueAt(i, 1).equals(quizAnswer[i][1]);
					boolean resultHiragana 	= tangoTable.getValueAt(i, 2).equals(quizAnswer[i][2]);
					boolean resultMeaning 	= tangoTable.getValueAt(i, 3).equals(quizAnswer[i][3]);
					
					
					if(!resultHanja){
						wrongNum++;  
						System.out.println("한자 : ("+i+",1)에서 틀림 \n틀린개수 : "+ wrongNum);
					}
					if(!resultHiragana){
						wrongNum++;  
						System.out.println("히라가나 : ("+i+",2)에서 틀림 \n틀린개수 : "+ wrongNum);
					}
					if(!resultMeaning){
						wrongNum++;  
						System.out.println("뜻 : ("+i+",3)에서 틀림 \n틀린개수 : "+ wrongNum);
					}
				}
				//퀴즈 결과값 받아오기
				//int quizResult = cm.getQuizResult(wrongNum);
				int quizResult = 1;
				int correctNum = totalNum - wrongNum;
				int score = (correctNum / totalNum)*100;
				System.out.println("맞은 개수 : " 	+ correctNum);
				System.out.println("문제 개수: " 	+ totalNum);
				System.out.println("총 점수 : " 	+ score);
				
				switch(quizResult){
				//이겼을 때
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
		if(quizList != null)System.out.println("[System] 단어장 가져오기 성공");
		
		new ClientQuizUI(quizList, 5);
	}
}

// 힌트 버튼 생성을 위한 클래스
class ButtonCellRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
	private final JButton button;
	private ClientManager cm = new ClientManager();
	
	public ButtonCellRenderer(final JTable table, final DefaultTableModel model , ArrayList<Tango> quizList) {
		this.button = new JButton("힌트");
		//버튼이 눌렸을 경우 힌트 보여주기
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
