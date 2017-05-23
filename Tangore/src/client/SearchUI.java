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
		
		JFrame jFrame = new JFrame("�ܾ���");
		
		JButton delete = new JButton("����");
		JButton update = new JButton("����");
		ClientManager cm = new ClientManager();
		
		ArrayList<Tango> list = null;
		try {
			list = cm.getTangoList();
		} catch (ManagerException e) {
			e.printStackTrace();
		}	
		
		String columnNames[] =
		{"��ȣ" , "����", "����", "���󰡳�", "��" };
		
		Object[][] rowData = {{new Integer(1),new ImageIcon(),"", "","",""} };
					
		//DefaultTableModel�� �����ϰ� ������ ���
		DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);

		//JTable�� DefaultTableModel�� ���
		JTable jTable = new JTable(defaultTableModel);
		
		//JScrollPane�� JTable�� ���
		JScrollPane jScollPane = new JScrollPane(jTable);
		jFrame.add(jScollPane);
		
		//�� ���� �߰�!
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
		
		//��� �� ���� ���ϱ�
		System.out.println(defaultTableModel.getRowCount());
		System.out.println(defaultTableModel.getColumnCount());
		
		//�÷�(��)�� index�� 0���� �����Ѵ�!!
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


