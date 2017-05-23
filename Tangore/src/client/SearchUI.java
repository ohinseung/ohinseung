package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exception.ManagerException;
import vo.Tango;

public class SearchUI{
	

	

	private String hanja;
	private String hiragana;
	private int row_id;
	private String meaning;


	public SearchUI()
	{
		
		JFrame jFrame = new JFrame("단어장");
		
		JButton delete = new JButton("삭제");
		JButton update = new JButton("수정");
		ClientManager cm = new ClientManager();
		
		ArrayList<Tango> list = null;
		try {
			list = cm.getTangoList();
		} catch (ManagerException e) {
			e.printStackTrace();
		}	
		
		String columnNames[] =
		{"번호" , "사진", "한자", "히라가나", "뜻" };
		
		Object[][] rowData = {{new Integer(1),new ImageIcon(),"", "","",""} };
					
		//DefaultTableModel을 선언하고 데이터 담기
		DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);

		//JTable에 DefaultTableModel을 담기
		JTable jTable = new JTable(defaultTableModel);
		
		//JScrollPane에 JTable을 담기
		JScrollPane jScollPane = new JScrollPane(jTable);
		jFrame.add(jScollPane);
		
		//행 한줄 추가!
		for(Tango tango :list){
			BufferedImage image =null;
			try {
				InputStream in = tango.getimage().getBinaryStream();
				image = ImageIO.read(in);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object [] tangoRow = { tango.getRow_id(), new ImageIcon(image) ,tango.getHanja(), tango.getHiragana(),tango.getMeaning()};
			defaultTableModel.addRow(tangoRow);
		}
		
		//행과 열 갯수 구하기
		System.out.println(defaultTableModel.getRowCount());
		System.out.println(defaultTableModel.getColumnCount());
		
		//컬럼(열)의 index는 0부터 시작한다!!
		System.out.println(defaultTableModel.getColumnName(0));
				

		jFrame.setSize(500, 300);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	public static void main(String[] args)
	{
		new SearchUI();
	}


	
}


