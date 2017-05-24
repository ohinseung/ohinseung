package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
               
            	
            	if (getRowCount() > 0) {
                    return getValueAt(0, column).getClass();
                 }

                 return super.getColumnClass(column);
              }                    
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };         
        tangoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		String event = e.getActionCommand();
		
		//검색
		switch (event){
		case "검색" :
			//검색할 항목 선택
			String[] selections = {"한자", "히라가나", "뜻"};
			String selected = (String) JOptionPane.showInputDialog(null, "검색할 항목을 선택해주세요", "검색 항목 선택", JOptionPane.QUESTION_MESSAGE, null, selections, "한자");
			//취소시 진행 안함
			if(selected==null)return;
			
			//검색할 단어 입력
			String search = (String) JOptionPane.showInputDialog(null, "검색할 단어를 입력해주세요");
			
			//검색해온 단어 받아오기
			list = new ArrayList<>();
			try {
				switch(selected){
				case "히라가나" : list = cm.findTango_hiragana(search);		break;
				case "한자" 	: list = cm.findTango_hanja(search); 		break;
				case "뜻" 		: list = cm.findTango_meaning(search);		break;
				}
				//테이블 초기화
				int tableSize = list.size();
				defaultTableModel.setRowCount(tableSize);
				System.out.println(list.size());
				//행 추가
				if(tableSize == 1) {
					Tango tango = list.get(0);
					ImageIcon image = getScaledImage(tango.getimage(), 100, 100);
					defaultTableModel.setRowCount(1);
					tangoTable.setValueAt(tango.getRow_id(),	0, 0);
					tangoTable.setValueAt(image,				0, 1);
					tangoTable.setValueAt(tango.getHanja(),		0, 2);
					tangoTable.setValueAt(tango.getHiragana(),	0, 3);
					tangoTable.setValueAt(tango.getMeaning(),	0, 4);
				}
				else{
					int row = 0;
					for(Tango tango :list){					
						ImageIcon image = getScaledImage(tango.getimage(), 100, 100);
						tangoTable.setValueAt(tango.getRow_id(),	row, 0);
						tangoTable.setValueAt(image,				row, 1);
						tangoTable.setValueAt(tango.getHanja(),		row, 2);
						tangoTable.setValueAt(tango.getHiragana(),	row, 3);
						tangoTable.setValueAt(tango.getMeaning(),	row, 4);
						row++;
					}			
				}
				defaultTableModel.fireTableDataChanged();
			} catch (ManagerException e2) {
				e2.printStackTrace();
			}
			break;
		case "수정":			
			//업데이트해서 들어갈 단어객체
			Tango newData = new Tango();
			
			//Row_id선택 (선택안되면 메세지확인 창)
			
			int seletedRow = tangoTable.getSelectedRow();	
			if(seletedRow == -1) JOptionPane.showMessageDialog(null, "수정할 항목을 클릭해주세요");
			else{								
				int row_id = (int) tangoTable.getValueAt(seletedRow, 0);
				System.out.println("row_id : " + row_id);
				Tango tango = null;
				try {
					tango = cm.findTango_row_id(row_id);
				} catch (ManagerException e4) {
					e4.printStackTrace();
				}
				//선택 창
				String[] updateSelections = {"사진", "한자", "히라가나", "뜻"};
				String updateSelected = (String) JOptionPane.showInputDialog(null, "수정할 항목을 선택해주세요", "수정", JOptionPane.QUESTION_MESSAGE, null, updateSelections, "사진");
				if(updateSelected == null) return;
				try {
					
					//사진이 선택되었다면
					if(updateSelected.equals("사진")){
						//사진을 찾기위한 윈도우 탐색창
						JFileChooser fc = new JFileChooser();
				        fc.setMultiSelectionEnabled(false);
				        fc.setCurrentDirectory(new File("C:\\tmp"));
				        int Checker = fc.showOpenDialog(null);
				        
				        //이미지 윈도우 탐색창확인
				        if(Checker == JFileChooser.APPROVE_OPTION){
				        	System.out.println("이미지 경로 찾기 중");
				        	File imageFile 	= fc.getSelectedFile();
				        	newData 		= new Tango(tango.getRow_id(), tango.getHiragana(), tango.getHanja(), tango.getMeaning(), imageFile);
				        	updateResult 	= cm.updateTango(newData);	
							if(updateResult){
								//테이블 업데이트
								tango = cm.findTango_row_id(newData.getRow_id());
								ImageIcon image = getScaledImage(tango.getimage(), 100, 100);
								tangoTable.setValueAt(image, seletedRow, 1);
								defaultTableModel.fireTableDataChanged();
								
								//선택되어있는것 없애기
								tangoTable.clearSelection();
							}
				        }else JOptionPane.showMessageDialog(null, "아무것도 선택되지 않았습니다.");							
		      		}
				    else{
						//수정할 히라가나/한자/뜻 입력						
						String update = (String) JOptionPane.showInputDialog(null, "수정할 단어를 입력해주세요");
						switch(updateSelected){							
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
						//업데이트 결과 가져오기
						updateResult 	= cm.updateTango(newData);
						if(updateResult){
							//테이블 업데이트
							tangoTable.setValueAt(newData.getHanja(),	seletedRow, 2);
							tangoTable.setValueAt(newData.getHiragana(),seletedRow, 3);
							tangoTable.setValueAt(newData.getMeaning(),	seletedRow, 4);
							defaultTableModel.fireTableDataChanged();
							
							JOptionPane.showMessageDialog(null, "성공적으로 수정되었습니다.");
							//선택되어있는것 없애기
							tangoTable.clearSelection();
						}
		      		}
				} catch (ManagerException e2) {
					e2.printStackTrace();
				}			
			}
			break;
		case "삭제": 
			seletedRow = tangoTable.getSelectedRow();			
			if(seletedRow == -1) JOptionPane.showMessageDialog(null, "삭제할 항목을 클릭해주세요");
			else{
				int answer = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
				int row_id = (int) tangoTable.getValueAt(seletedRow, 0);
				System.out.println("row_id : " + row_id);				
				if(answer == JOptionPane.OK_OPTION){
					try {
						boolean deleteResult = cm.deleteTango(row_id);
						if(deleteResult){
							defaultTableModel.removeRow(seletedRow);
							defaultTableModel.fireTableDataChanged();
							JOptionPane.showMessageDialog(null, "성공적으로 삭제되었습니다.");
						}
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
				}			
			}
			break;
		case "전체삭제":
			int answer = JOptionPane.showConfirmDialog(null, "전체 삭제하시겠습니까?", "전체삭제", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(answer == JOptionPane.OK_OPTION) {
				cm.deleteAll();
				list = null;
				defaultTableModel.setRowCount(0);
				defaultTableModel.fireTableDataChanged();
			}
			break;
		case "전체출력":
			try {
				list =cm.getTangoList();
				//테이블 초기화
				defaultTableModel.setRowCount(0);
				
				//행 추가
				for(Tango t :list){
					ImageIcon image 	= getScaledImage(t.getimage(), 100, 100);
					Object [] tangoRow 	= { t.getRow_id(), image ,t.getHanja(), t.getHiragana(),t.getMeaning()};				
					defaultTableModel.addRow(tangoRow);
				}			
				defaultTableModel.fireTableDataChanged();
			} catch (ManagerException e1) {
				e1.printStackTrace();
			}
			break;
		case "돌아가기":
			new MainUI();			
			dispose();
			break;
		}
	}
}

