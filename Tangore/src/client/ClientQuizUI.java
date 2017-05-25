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
		if(quizList != null)System.out.println("[System] �ܾ��� �������� ����");
		//Collections.reverse(list);			
		//Frame ����
		setTitle("�ܾ�����");
		
		//��ư �߰�
		btn_panel = new JPanel();
		addButton("����",btn_panel);
		
		
		//���̺� ����
		String columnNames[] =
		{"��ȣ", "����", "���󰡳�", "��","��Ʈ"};
		
		//���̺� default�� => �����
		Object[][] rowData = {};
		
		//DefaultTableModel�� �����ϰ� ������ ���
		defaultTableModel = new DefaultTableModel(rowData, columnNames);
		

		//�� �߰�
		for(Tango tango :quizList){			
			Object [] tangoRow = { tango.getRow_id() ,tango.getHanja(), tango.getHiragana(),tango.getMeaning(),""};
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
          /*  @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }*/
        };         
        tangoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//JTable ũ��/ ���� / ��Ʈ / ���� ���� ���� ����
		tangoTable.setFont(new Font("a����������3", Font.PLAIN, 32));
		tangoTable.getTableHeader().setFont(new Font("a����������3", Font.PLAIN, 32));
		tangoTable.setRowHeight(100);
		tangoTable.getTableHeader().setReorderingAllowed(false);
		tableCellCenter(tangoTable);
		
		//��ư �߰�
		ButtonCellRenderer renderer = new ButtonCellRenderer(tangoTable, defaultTableModel, quizList);
		TableColumn hintColumn = tangoTable.getColumnModel().getColumn(4);
		hintColumn.setCellEditor(renderer);
		hintColumn.setCellRenderer(renderer);

		
		//JScrollPane�� JTable�� ���
		JScrollPane jScollPane = new JScrollPane(tangoTable);		

		//JFrame ����������		
		setLayout(new BorderLayout());
		btn_panel.setLayout(new GridLayout(0,1));
		add(jScollPane, BorderLayout.CENTER);		
		add(btn_panel,BorderLayout.PAGE_END);
		setSize(1028, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

// ��Ʈ ��ư ������ ���� Ŭ����
class ButtonCellRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
	private final JButton button;
	private ClientManager cm = new ClientManager();
	
	public ButtonCellRenderer(final JTable table, final DefaultTableModel model , ArrayList<Tango> quizList) {
		this.button = new JButton("��Ʈ");
		//��ư�� ������ ��� ��Ʈ �����ֱ�
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
