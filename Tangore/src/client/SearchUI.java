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
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import exception.ManagerException;
import vo.Tango;

public class SearchUI extends JFrame implements ActionListener{
	
	private int row_id;
	private String hanja;
	private String hiragana;
	private String meaning;
	private JPanel btn_panel;
	private ClientManager cm = new ClientManager();
	private ArrayList<Tango> list;
	private DefaultTableModel defaultTableModel;
	private JTable tangoTable; 
	private Tango newData;
	private boolean updateResult = false;

	public SearchUI()
	{	//list ��������
		ArrayList<Tango> list = null;
		try {
			list = cm.getTangoList();
			if(list != null)System.out.println("[System] �ܾ��� �������� ����");
			Collections.reverse(list);
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
		//{"��ȣ", "����", "����", "���󰡳�", "��", "����"};
		
		//���̺� default�� => �����
		Object[][] rowData = {};
		
		//DefaultTableModel�� �����ϰ� ������ ���
		defaultTableModel = new DefaultTableModel(rowData, columnNames);

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
        

		//�� �߰�
		int rowSize =0;
		for(Tango tango :list){
			ImageIcon image = getScaledImage(tango.getimage(), 100, 100);
			Object [] tangoRow = { tango.getRow_id(), image ,tango.getHanja(), tango.getHiragana(),tango.getMeaning()};
			/*���� ��ư �߰���
			JRadioButton radio = new JRadioButton();
			radio.setHorizontalAlignment(JRadioButton.CENTER);
			Object [] tangoRow = { tango.getRow_id(), image ,tango.getHanja(), tango.getHiragana(),tango.getMeaning(), radio};
			*/
			defaultTableModel.addRow(tangoRow);
			rowSize++;
		}
		/*
		tangoTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		tangoTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		tangoTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		tangoTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		tangoTable.getColumnModel().getColumn(4).setPreferredWidth(100);
				*/
		//JTable ũ��/ ���� / ��Ʈ / ���� ���� ���� ����
		tangoTable.setFont(new Font("����", Font.PLAIN, 32));
		tangoTable.getTableHeader().setFont(new Font("����", Font.PLAIN, 32));
		tangoTable.setRowHeight(100);
		tableCellCenter(tangoTable);
		//JScrollPane�� JTable�� ���
		JScrollPane jScollPane = new JScrollPane(tangoTable);		
		
		
		/*
		//RadioButtonGroup
		ButtonGroup bg = new ButtonGroup();
		for(int i =0; i<rowSize; i++){
			bg.add((JRadioButton)defaultTableModel.getValueAt(i, 5));
		}
		//���� ��ư�� ������ �߰�
		tangoTable.getColumn("����").setCellRenderer(new Radiorenderer());
		tangoTable.getColumn("����").setCellEditor(new RadioEditor(new JCheckBox()));
		*/
	
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
      /*tcm.getColumn(0).setCellRenderer(dtcr);  
      tcm.getColumn(2).setCellRenderer(dtcr);  
      tcm.getColumn(3).setCellRenderer(dtcr);  
      tcm.getColumn(4).setCellRenderer(dtcr);*/
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
			} catch (ManagerException e2) {
				e2.printStackTrace();
			}			
		}
		//����
		else if(e.getActionCommand().equals("����")){
			int selectedRowIndex = tangoTable.getSelectedRow();
			if(selectedRowIndex == -1) JOptionPane.showMessageDialog(null, "������ �׸��� Ŭ�����ּ���");
			else{
				System.out.println(selectedRowIndex +"�̶�"+ list.get(selectedRowIndex));
				Tango tango = list.get(selectedRowIndex);			
				String[] selections = {"����", "���󰡳�", "����", "��"};
				String selected = (String) JOptionPane.showInputDialog(null, "������ �׸��� �������ּ���", "����", JOptionPane.QUESTION_MESSAGE, null, selections, "����");
				try {
					if(selected.equals("����")){
						JFrame selectImage = new JFrame();
						JFileChooser fc = new JFileChooser();
				        fc.setMultiSelectionEnabled(false);
				        fc.setCurrentDirectory(new File("C:\\tmp"));
				 
				        JButton imageBtn = new JButton("���� �˻�");
				        imageBtn.addActionListener(new ActionListener() {
				        	
				        	@Override						
				            public void actionPerformed(ActionEvent e) {
				        		int result = fc.showOpenDialog(null);
				                if (result == JFileChooser.APPROVE_OPTION) {
				                    File imageFile = fc.getSelectedFile();
				                    newData = new Tango(tango.getRow_id(), tango.getHiragana(), tango.getHanja(), tango.getMeaning(), imageFile);
				                    try {
										updateResult 	= cm.updateTango(newData);
										list 			= cm.getTangoList();
										if(updateResult)defaultTableModel.fireTableDataChanged();
									} catch (ManagerException e1) {
										e1.printStackTrace();
									}
				                }		                    
				            }
				        });
				        selectImage.add(imageBtn);
					
					}else{
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
						}
						updateResult 	= cm.updateTango(newData);
						list 			= cm.getTangoList();
						if(updateResult)defaultTableModel.fireTableDataChanged();
					}
					
					
				} catch (ManagerException e2) {
					e2.printStackTrace();
				}			
			}
		}else if(e.getActionCommand().equals("����")){
			int answer = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			//if(answer == JOptionPane.OK_OPTION) cm.delete();
			
		}else if(e.getActionCommand().equals("��ü����")){
			int answer = JOptionPane.showConfirmDialog(null, "��ü �����Ͻðڽ��ϱ�?", "��ü����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(answer == JOptionPane.OK_OPTION) {
				cm.deleteAll();
				list = null;
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
	
	/*
	//Ŭ���� ������
	class Radiorenderer implements TableCellRenderer {
		public Radiorenderer() {
			 
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int col) {
			if(value == null) return null;
			return (Component)value;
		}
		
	}
	
	class RadioEditor extends DefaultCellEditor implements ItemListener{
		
		JRadioButton radio;
		public RadioEditor(JCheckBox chk){
			super(chk);	
		}
		
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if(value == null) return null;
			
			radio = (JRadioButton)value;
			radio.addItemListener(this);
			return (Component)value;
		}
		@Override
		public Object getCellEditorValue() {
			radio.removeItemListener(this);
			return radio;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			super.fireEditingStopped();
			repaint();
		}		
	}
	*/
}

