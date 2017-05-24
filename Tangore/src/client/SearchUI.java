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
		String event = e.getActionCommand();
		
		//�˻�
		switch (event){
		case "�˻�" :
			//�˻��� �׸� ����
			String[] selections = {"����", "���󰡳�", "��"};
			String selected = (String) JOptionPane.showInputDialog(null, "�˻��� �׸��� �������ּ���", "�˻� �׸� ����", JOptionPane.QUESTION_MESSAGE, null, selections, "����");
			//��ҽ� ���� ����
			if(selected==null)return;
			
			//�˻��� �ܾ� �Է�
			String search = (String) JOptionPane.showInputDialog(null, "�˻��� �ܾ �Է����ּ���");
			
			//�˻��ؿ� �ܾ� �޾ƿ���
			list = new ArrayList<>();
			try {
				switch(selected){
				case "���󰡳�" : list = cm.findTango_hiragana(search);		break;
				case "����" 	: list = cm.findTango_hanja(search); 		break;
				case "��" 		: list = cm.findTango_meaning(search);		break;
				}
				//���̺� �ʱ�ȭ
				int tableSize = list.size();
				defaultTableModel.setRowCount(tableSize);
				System.out.println(list.size());
				//�� �߰�
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
		case "����":			
			//������Ʈ�ؼ� �� �ܾü
			Tango newData = new Tango();
			
			//Row_id���� (���þȵǸ� �޼���Ȯ�� â)
			
			int seletedRow = tangoTable.getSelectedRow();	
			if(seletedRow == -1) JOptionPane.showMessageDialog(null, "������ �׸��� Ŭ�����ּ���");
			else{								
				int row_id = (int) tangoTable.getValueAt(seletedRow, 0);
				System.out.println("row_id : " + row_id);
				Tango tango = null;
				try {
					tango = cm.findTango_row_id(row_id);
				} catch (ManagerException e4) {
					e4.printStackTrace();
				}
				//���� â
				String[] updateSelections = {"����", "����", "���󰡳�", "��"};
				String updateSelected = (String) JOptionPane.showInputDialog(null, "������ �׸��� �������ּ���", "����", JOptionPane.QUESTION_MESSAGE, null, updateSelections, "����");
				if(updateSelected == null) return;
				try {
					
					//������ ���õǾ��ٸ�
					if(updateSelected.equals("����")){
						//������ ã������ ������ Ž��â
						JFileChooser fc = new JFileChooser();
				        fc.setMultiSelectionEnabled(false);
				        fc.setCurrentDirectory(new File("C:\\tmp"));
				        int Checker = fc.showOpenDialog(null);
				        
				        //�̹��� ������ Ž��âȮ��
				        if(Checker == JFileChooser.APPROVE_OPTION){
				        	System.out.println("�̹��� ��� ã�� ��");
				        	File imageFile 	= fc.getSelectedFile();
				        	newData 		= new Tango(tango.getRow_id(), tango.getHiragana(), tango.getHanja(), tango.getMeaning(), imageFile);
				        	updateResult 	= cm.updateTango(newData);	
							if(updateResult){
								//���̺� ������Ʈ
								tango = cm.findTango_row_id(newData.getRow_id());
								ImageIcon image = getScaledImage(tango.getimage(), 100, 100);
								tangoTable.setValueAt(image, seletedRow, 1);
								defaultTableModel.fireTableDataChanged();
								
								//���õǾ��ִ°� ���ֱ�
								tangoTable.clearSelection();
							}
				        }else JOptionPane.showMessageDialog(null, "�ƹ��͵� ���õ��� �ʾҽ��ϴ�.");							
		      		}
				    else{
						//������ ���󰡳�/����/�� �Է�						
						String update = (String) JOptionPane.showInputDialog(null, "������ �ܾ �Է����ּ���");
						switch(updateSelected){							
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
						//������Ʈ ��� ��������
						updateResult 	= cm.updateTango(newData);
						if(updateResult){
							//���̺� ������Ʈ
							tangoTable.setValueAt(newData.getHanja(),	seletedRow, 2);
							tangoTable.setValueAt(newData.getHiragana(),seletedRow, 3);
							tangoTable.setValueAt(newData.getMeaning(),	seletedRow, 4);
							defaultTableModel.fireTableDataChanged();
							
							JOptionPane.showMessageDialog(null, "���������� �����Ǿ����ϴ�.");
							//���õǾ��ִ°� ���ֱ�
							tangoTable.clearSelection();
						}
		      		}
				} catch (ManagerException e2) {
					e2.printStackTrace();
				}			
			}
			break;
		case "����": 
			seletedRow = tangoTable.getSelectedRow();			
			if(seletedRow == -1) JOptionPane.showMessageDialog(null, "������ �׸��� Ŭ�����ּ���");
			else{
				int answer = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
				int row_id = (int) tangoTable.getValueAt(seletedRow, 0);
				System.out.println("row_id : " + row_id);				
				if(answer == JOptionPane.OK_OPTION){
					try {
						boolean deleteResult = cm.deleteTango(row_id);
						if(deleteResult){
							defaultTableModel.removeRow(seletedRow);
							defaultTableModel.fireTableDataChanged();
							JOptionPane.showMessageDialog(null, "���������� �����Ǿ����ϴ�.");
						}
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
				}			
			}
			break;
		case "��ü����":
			int answer = JOptionPane.showConfirmDialog(null, "��ü �����Ͻðڽ��ϱ�?", "��ü����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(answer == JOptionPane.OK_OPTION) {
				cm.deleteAll();
				list = null;
				defaultTableModel.setRowCount(0);
				defaultTableModel.fireTableDataChanged();
			}
			break;
		case "��ü���":
			try {
				list =cm.getTangoList();
				//���̺� �ʱ�ȭ
				defaultTableModel.setRowCount(0);
				
				//�� �߰�
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
		case "���ư���":
			new MainUI();			
			dispose();
			break;
		}
	}
}

