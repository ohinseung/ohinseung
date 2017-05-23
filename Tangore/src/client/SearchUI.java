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
		
		setTitle("�ܾ���");		
		JButton delete = new JButton("����");
		JButton update = new JButton("����");
		ClientManager cm = new ClientManager();
		ArrayList<Tango> list = null;
		try {
			list = cm.getTangoList();
		} catch (ManagerException e) {
			e.printStackTrace();
		}	
		
		if(list != null){
			System.out.println("�����Դ�");
		}
		
		String columnNames[] =
		{"��ȣ", "����", "����", "���󰡳�", "��"};
		
		Object[][] rowData = {{"","","","","",""} };
					
		//DefaultTableModel�� �����ϰ� ������ ���
		DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);

		//JTable�� DefaultTableModel�� ���
		JTable jTable = new JTable(defaultTableModel);
		
		//JScrollPane�� JTable�� ���
		JScrollPane jScollPane = new JScrollPane(jTable);
		add(jScollPane);
		
		//�� ���� �߰�!
		
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


