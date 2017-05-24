package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import exception.ManagerException;
import vo.Tango;

public class SearchUI extends JFrame implements ActionListener{
	
	
	private JPanel btn_panel;
	private ClientManager cm = new ClientManager();
	private ArrayList<Tango> list;
	private DefaultTableModel defaultTableModel;
	private JTable tangoTable; 
	private boolean updateResult = false;

	public SearchUI()
	{	//list 가져오기
		ArrayList<Tango> list = null;
		try {
			list = cm.getTangoList();
			if(list != null)System.out.println("[System] 단어장 가져오기 성공");
			//Collections.reverse(list);
		} catch (ManagerException e) {
			e.printStackTrace();
		}			
		//Frame 생성
		setTitle("단어장");
		
		//버튼 추가
		btn_panel = new JPanel(new GridLayout(0,6,10,20));
		addButton("검색",btn_panel);
		addButton("수정",btn_panel);
		addButton("삭제",btn_panel);
		addButton("전체삭제",btn_panel);
		addButton("전체출력",btn_panel);
		addButton("돌아가기",btn_panel);
		
		//테이블 생성
		String columnNames[] =
		{"번호", "사진", "한자", "히라가나", "뜻"};
		
		//테이블 default값 => 비워둠
		Object[][] rowData = {};
		
		//DefaultTableModel을 선언하고 데이터 담기
		defaultTableModel = new DefaultTableModel(rowData, columnNames);

		//행 추가
		for(Tango tango :list){
			ImageIcon image = getScaledImage(tango.getimage(), 100, 100);
			Object [] tangoRow = { tango.getRow_id(), image ,tango.getHanja(), tango.getHiragana(),tango.getMeaning()};
			defaultTableModel.addRow(tangoRow);
		}
		
		//JTable에 DefaultTableModel을 담기
		tangoTable = new JTable(defaultTableModel){
            //  사진 주소값 사진으로 바꿔서 넣기
            public Class getColumnClass(int column){
                return getValueAt(1, column).getClass();
            }                     
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };         
        
		
		//JTable 크기/ 정렬 / 폰트 / 수정 가능 여부 지정
		tangoTable.setFont(new Font("굴림", Font.PLAIN, 32));
		tangoTable.getTableHeader().setFont(new Font("굴림", Font.PLAIN, 32));
		tangoTable.setRowHeight(100);
		tangoTable.getTableHeader().setReorderingAllowed(false);
		tableCellCenter(tangoTable);
		
		//JScrollPane에 JTable을 담기
		JScrollPane jScollPane = new JScrollPane(tangoTable);		

		//JFrame 나머지설정
		
		setLayout(new BorderLayout());
		btn_panel.setLayout(new GridLayout(0,5,20,0));
		add(jScollPane, BorderLayout.PAGE_START);		
		add(btn_panel,BorderLayout.PAGE_END);
		setSize(1028, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
      tcm.getColumn(2).setCellRenderer(dtcr);  
      tcm.getColumn(3).setCellRenderer(dtcr);  
      tcm.getColumn(4).setCellRenderer(dtcr);
    }	  	 
	
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//검색
		try {
			list = cm.getTangoList();
		} catch (ManagerException e3) {
			e3.printStackTrace();
		}
		if(e.getActionCommand().equals("검색")){
			String[] selections = {"히라가나", "한자", "뜻"};
			String selected = (String) JOptionPane.showInputDialog(null, "검색할 항목을 선택해주세요", "검색 항목 선택", JOptionPane.QUESTION_MESSAGE, null, selections, "히라가나");
			String search = (String) JOptionPane.showInputDialog(null, "검색할 단어를 입력해주세요");
			try {
				switch(selected){
				case "히라가나" : list = cm.findTango_hiragana(search);		break;
				case "한자" 	: list = cm.findTango_hanja(search); 		break;
				case "뜻" 		: list = cm.findTango_meaing(search);		break;
				}
				//테이블 초기화
				defaultTableModel.setRowCount(0);
				
				//행 추가
				for(Tango tango :list){
					ImageIcon image = getScaledImage(tango.getimage(), 100, 100);
					Object [] tangoRow = { tango.getRow_id(), image ,tango.getHanja(), tango.getHiragana(),tango.getMeaning()};				
					defaultTableModel.addRow(tangoRow);
				}			
				defaultTableModel.fireTableDataChanged();
			} catch (ManagerException e2) {
				e2.printStackTrace();
			}			
		}
		//수정
		else if(e.getActionCommand().equals("수정")){
			//업데이트해서 들어갈 단어객체
			Tango newData = new Tango();
			
			//Row선택 (선택안되면 메세지확인 창)
			
			int selectedRowIndex = tangoTable.getSelectedRow();
						
			if(selectedRowIndex == -1) JOptionPane.showMessageDialog(null, "수정할 항목을 클릭해주세요");
			else{
				//선택된 열의 단어가져오기
				System.out.println(selectedRowIndex);
				Tango tango = list.get(selectedRowIndex);
				
				//선택 창
				String[] selections = {"사진", "히라가나", "한자", "뜻"};
				String selected = (String) JOptionPane.showInputDialog(null, "수정할 항목을 선택해주세요", "수정", JOptionPane.QUESTION_MESSAGE, null, selections, "사진");
				
				if(selected == null) return;
				try {
					
					//사진이 선택되었다면
					if(selected.equals("사진")){
						//사진을 찾기위한 윈도우 탐색창
						JFrame selectImage = new JFrame();
						JFileChooser fc = new JFileChooser();
				        fc.setMultiSelectionEnabled(false);
				        fc.setCurrentDirectory(new File("C:\\tmp"));
				        
	
				        JButton imageBtn = new JButton("사진 검색");
				        
				        selectImage.add(imageBtn, new BorderLayout());
				        selectImage.setSize(300,300);
				        selectImage.setVisible(true);
				        
				        imageBtn.addActionListener(new ActionListener() {
				        	
				        	@Override						
				            public void actionPerformed(ActionEvent e) {
				        		Tango newData = null;
				        		int selectedRowIndex = tangoTable.getSelectedRow();
				        		int result = fc.showOpenDialog(null);
				                if (result == JFileChooser.APPROVE_OPTION) {
				                    File imageFile = fc.getSelectedFile();
				                    newData = new Tango(tango.getRow_id(), tango.getHiragana(), tango.getHanja(), tango.getMeaning(), imageFile);
				                    try {
										updateResult 	= cm.updateTango(newData);
										list 			= cm.getTangoList();
										
										if(updateResult){
											Object [] tangoRow = { newData.getRow_id(), newData.getimage() ,newData.getHanja(), newData.getHiragana(),newData.getMeaning()};
											defaultTableModel.removeRow(selectedRowIndex);
											defaultTableModel.addRow(tangoRow);
											defaultTableModel.fireTableDataChanged();
										}
										selectImage.dispose();
									} catch (ManagerException e1) {
										e1.printStackTrace();
									}
				                }		                    
				            }
				        });
					}else{
						//수정할 히라가나/한자/뜻 입력
						
						String update = (String) JOptionPane.showInputDialog(null, "수정할 단어를 입력해주세요");
						switch(selected){
						
						case "히라가나" : 
							updateResult	= cm.updateTango(newData);
							newData 		= new Tango(tango.getRow_id(), update, tango.getHanja(), tango.getMeaning(), tango.getimage());
							break;
						case "한자" 	: 
							updateResult 	= cm.updateTango(newData);
							newData 		= new Tango(tango.getRow_id(), tango.getHiragana(), update, tango.getMeaning(), tango.getimage());
							break;
						case "뜻" 		: 
							updateResult 	= cm.updateTango(newData);
							newData 		= new Tango(tango.getRow_id(), tango.getHiragana(), tango.getHanja(), update, tango.getimage());
							break;
						default : return;
						}
						updateResult 	= cm.updateTango(newData);
						list 			= cm.getTangoList();
						
						if(updateResult){
							ImageIcon image = tango.getimage();
							image = getScaledImage(image, 100, 100);
							Object [] tangoRow = { newData.getRow_id(), image ,newData.getHanja(), newData.getHiragana(),newData.getMeaning()};
							defaultTableModel.removeRow(selectedRowIndex);
							defaultTableModel.addRow(tangoRow);
							RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(defaultTableModel); 
							tangoTable.setRowSorter(sorter); 
							defaultTableModel.fireTableDataChanged();
						}
					}
					
					
				} catch (ManagerException e2) {
					e2.printStackTrace();
				}			
			}
		}else if(e.getActionCommand().equals("삭제")){
			
			int selectedRowIndex = tangoTable.getSelectedRow();			
			if(selectedRowIndex == -1) JOptionPane.showMessageDialog(null, "삭제할 항목을 클릭해주세요");
			else{
				int answer = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
				Tango deleteTango = new Tango();
				deleteTango = list.get(selectedRowIndex);
				if(answer == JOptionPane.OK_OPTION)
					try {
						cm.deleteTango(deleteTango.getRow_id());
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
			}			
		}else if(e.getActionCommand().equals("전체삭제")){
			int answer = JOptionPane.showConfirmDialog(null, "전체 삭제하시겠습니까?", "전체삭제", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(answer == JOptionPane.OK_OPTION) {
				cm.deleteAll();
				list = null;
				defaultTableModel.setRowCount(0);
				RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(defaultTableModel); 
				tangoTable.setRowSorter(sorter); 
				defaultTableModel.fireTableDataChanged();
			}
			
		}else if(e.getActionCommand().equals("전체출력")){
			try {
				list =cm.getTangoList();
			} catch (ManagerException e1) {
				e1.printStackTrace();
			}
		}		
		else if(e.getActionCommand().equals("돌아가기")){
			new MainUI();			
			dispose();
		}
		defaultTableModel.fireTableDataChanged();
		
	}
	
}

