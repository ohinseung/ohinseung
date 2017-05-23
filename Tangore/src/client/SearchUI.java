package client;

import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exception.ManagerException;
import vo.Tango;

public class SearchUI extends JFrame{
	

	

	private String hanja;
	private String hiragana;
	private int row_id;
	private String meaning;


	public SearchUI()
	{
		
		setTitle("단어장");		
		JButton delete = new JButton("삭제");
		JButton update = new JButton("수정");
		ClientManager cm = new ClientManager();
		ArrayList<Tango> list = null;
		try {
			list = cm.getTangoList();
		} catch (ManagerException e) {
			e.printStackTrace();
		}	
		
		if(list != null){
			System.out.println("가져왔당");
		}
		
		String columnNames[] =
		{"번호", "사진", "한자", "히라가나", "뜻"};
		
		Object[][] rowData = {{"","","","","",""} };
					
		//DefaultTableModel을 선언하고 데이터 담기
		DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);

		//JTable에 DefaultTableModel을 담기
		JTable jTable = new JTable(defaultTableModel);
		
		//JScrollPane에 JTable을 담기
		JScrollPane jScollPane = new JScrollPane(jTable);
		add(jScollPane);
		
		//행 한줄 추가!
		
		for(Tango tango :list){
			JLabel label = new JLabel(tango.getimage());
			Object [] tangoRow = { tango.getRow_id(), label,tango.getHanja(), tango.getHiragana(),tango.getMeaning()};
			defaultTableModel.addRow(tangoRow);
		}
		
			
		
		setSize(500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	public static void main(String[] args)
	{
		new SearchUI();
	}


	
}


