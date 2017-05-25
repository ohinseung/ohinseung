package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private ArrayList<Tango> quizList;
	private DefaultTableModel defaultTableModel;
	private ClientManager cm = new ClientManager();

	public ClientQuizUI() throws ManagerException {
		int quizNum = 3;
		quizList = cm.getQuizList(quizNum);
		if(quizList != null)System.out.println("[System] 단어장 가져오기 성공");
		//Collections.reverse(list);			
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
		

		//행 추가
		for(Tango tango :quizList){			
			Object [] tangoRow = { tango.getRow_id() ,tango.getHanja(), tango.getHiragana(),tango.getMeaning(),""};
			defaultTableModel.addRow(tangoRow);
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
          /*  @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }*/
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
		// TODO Auto-generated method stub
		
	}	  
	
	public static void main(String[] args) throws ManagerException {
		new ClientQuizUI();
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
	//사진 크기 설정 메소드
	private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
	    g2.dispose();
	    ImageIcon resizedImageIcon = new ImageIcon(resizedImg);
	    return resizedImageIcon;
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
