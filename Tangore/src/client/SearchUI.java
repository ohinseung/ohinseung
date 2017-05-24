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
	{	//list ��������
		ArrayList<Tango> list = null;
		try {
			list = cm.getTangoList();
			if(list != null)System.out.println("[System] �ܾ��� �������� ����");
			//Collections.reverse(list);
		} catch (ManagerException e) {
			e.printStackTrace();
		}			
		//Frame ����
		setTitle("�ܾ���");
		
		//��ư �߰�
		btn_panel = new JPanel(new GridLayout(0,6,10,20));
		addButton("�˻�",btn_panel);
		addButton("����",btn_panel);
		addButton("����",btn_panel);
		addButton("��ü����",btn_panel);
		addButton("��ü���",btn_panel);
		addButton("���ư���",btn_panel);
		
		//���̺� ����
		String columnNames[] =
		{"��ȣ", "����", "����", "���󰡳�", "��"};
		
		//���̺� default�� => �����
		Object[][] rowData = {};
		
		//DefaultTableModel�� �����ϰ� ������ ���
		defaultTableModel = new DefaultTableModel(rowData, columnNames);

		//�� �߰�
		for(Tango tango :list){
			ImageIcon image = getScaledImage(tango.getimage(), 100, 100);
			Object [] tangoRow = { tango.getRow_id(), image ,tango.getHanja(), tango.getHiragana(),tango.getMeaning()};
			defaultTableModel.addRow(tangoRow);
		}
		
		//JTable�� DefaultTableModel�� ���
		tangoTable = new JTable(defaultTableModel){
            //  ���� �ּҰ� �������� �ٲ㼭 �ֱ�
            public Class getColumnClass(int column){
                return getValueAt(1, column).getClass();
            }                     
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };         
        
		
		//JTable ũ��/ ���� / ��Ʈ / ���� ���� ���� ����
		tangoTable.setFont(new Font("����", Font.PLAIN, 32));
		tangoTable.getTableHeader().setFont(new Font("����", Font.PLAIN, 32));
		tangoTable.setRowHeight(100);
		tangoTable.getTableHeader().setReorderingAllowed(false);
		tableCellCenter(tangoTable);
		
		//JScrollPane�� JTable�� ���
		JScrollPane jScollPane = new JScrollPane(tangoTable);		

		//JFrame ����������
		
		setLayout(new BorderLayout());
		btn_panel.setLayout(new GridLayout(0,5,20,0));
		add(jScollPane, BorderLayout.PAGE_START);		
		add(btn_panel,BorderLayout.PAGE_END);
		setSize(1028, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//���� ũ�� ���� �޼ҵ�
	private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
	    g2.dispose();
	    ImageIcon resizedImageIcon = new ImageIcon(resizedImg);
	    return resizedImageIcon;
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
      tcm.getColumn(2).setCellRenderer(dtcr);  
      tcm.getColumn(3).setCellRenderer(dtcr);  
      tcm.getColumn(4).setCellRenderer(dtcr);
    }	  	 
	
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//�˻�
		try {
			list = cm.getTangoList();
		} catch (ManagerException e3) {
			e3.printStackTrace();
		}
		if(e.getActionCommand().equals("�˻�")){
			String[] selections = {"���󰡳�", "����", "��"};
			String selected = (String) JOptionPane.showInputDialog(null, "�˻��� �׸��� �������ּ���", "�˻� �׸� ����", JOptionPane.QUESTION_MESSAGE, null, selections, "���󰡳�");
			String search = (String) JOptionPane.showInputDialog(null, "�˻��� �ܾ �Է����ּ���");
			try {
				switch(selected){
				case "���󰡳�" : list = cm.findTango_hiragana(search);		break;
				case "����" 	: list = cm.findTango_hanja(search); 		break;
				case "��" 		: list = cm.findTango_meaing(search);		break;
				}
				//���̺� �ʱ�ȭ
				defaultTableModel.setRowCount(0);
				
				//�� �߰�
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
		//����
		else if(e.getActionCommand().equals("����")){
			//������Ʈ�ؼ� �� �ܾü
			Tango newData = new Tango();
			
			//Row���� (���þȵǸ� �޼���Ȯ�� â)
			
			int selectedRowIndex = tangoTable.getSelectedRow();
						
			if(selectedRowIndex == -1) JOptionPane.showMessageDialog(null, "������ �׸��� Ŭ�����ּ���");
			else{
				//���õ� ���� �ܾ������
				System.out.println(selectedRowIndex);
				Tango tango = list.get(selectedRowIndex);
				
				//���� â
				String[] selections = {"����", "���󰡳�", "����", "��"};
				String selected = (String) JOptionPane.showInputDialog(null, "������ �׸��� �������ּ���", "����", JOptionPane.QUESTION_MESSAGE, null, selections, "����");
				
				if(selected == null) return;
				try {
					
					//������ ���õǾ��ٸ�
					if(selected.equals("����")){
						//������ ã������ ������ Ž��â
						JFrame selectImage = new JFrame();
						JFileChooser fc = new JFileChooser();
				        fc.setMultiSelectionEnabled(false);
				        fc.setCurrentDirectory(new File("C:\\tmp"));
				        
	
				        JButton imageBtn = new JButton("���� �˻�");
				        
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
						//������ ���󰡳�/����/�� �Է�
						
						String update = (String) JOptionPane.showInputDialog(null, "������ �ܾ �Է����ּ���");
						switch(selected){
						
						case "���󰡳�" : 
							updateResult	= cm.updateTango(newData);
							newData 		= new Tango(tango.getRow_id(), update, tango.getHanja(), tango.getMeaning(), tango.getimage());
							break;
						case "����" 	: 
							updateResult 	= cm.updateTango(newData);
							newData 		= new Tango(tango.getRow_id(), tango.getHiragana(), update, tango.getMeaning(), tango.getimage());
							break;
						case "��" 		: 
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
		}else if(e.getActionCommand().equals("����")){
			
			int selectedRowIndex = tangoTable.getSelectedRow();			
			if(selectedRowIndex == -1) JOptionPane.showMessageDialog(null, "������ �׸��� Ŭ�����ּ���");
			else{
				int answer = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
				Tango deleteTango = new Tango();
				deleteTango = list.get(selectedRowIndex);
				if(answer == JOptionPane.OK_OPTION)
					try {
						cm.deleteTango(deleteTango.getRow_id());
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
			}			
		}else if(e.getActionCommand().equals("��ü����")){
			int answer = JOptionPane.showConfirmDialog(null, "��ü �����Ͻðڽ��ϱ�?", "��ü����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(answer == JOptionPane.OK_OPTION) {
				cm.deleteAll();
				list = null;
				defaultTableModel.setRowCount(0);
				RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(defaultTableModel); 
				tangoTable.setRowSorter(sorter); 
				defaultTableModel.fireTableDataChanged();
			}
			
		}else if(e.getActionCommand().equals("��ü���")){
			try {
				list =cm.getTangoList();
			} catch (ManagerException e1) {
				e1.printStackTrace();
			}
		}		
		else if(e.getActionCommand().equals("���ư���")){
			new MainUI();			
			dispose();
		}
		defaultTableModel.fireTableDataChanged();
		
	}
	
}

