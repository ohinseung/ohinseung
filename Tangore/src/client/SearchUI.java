package client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.Tango;

public class SearchUI extends JFrame {
	private JPanel search_Panel; //�ϴ� �ʿ� �������� ��. 

	private JButton delete_All_btn; //��ü ���� ��ư
	private JButton update_btn;	//���� ��ư
	private JButton delete_btn;	//���� ��ư

	private JLabel image_Label; 	//���� ���̺�
	private JLabel hanja_Label; 	//���� ���̺�
	private JLabel hiragana_Label; //���󰡳� ���̺�
	private JLabel meaning_Label; 	//�� ���̺�
	private JLabel update_Label; 	//���� ���̺�
	private JLabel delete_Label; 	//���� ���̺�
	
	private ImageIcon image; 

	private ArrayList<JButton> deleteButtonList;
	private ArrayList<JButton> updateButtonList;
	private ArrayList<JLabel> imageLabelList;
	private ArrayList<JLabel> hanjaLabelList;
	private ArrayList<JLabel> hiraganaLabelList;
	private ArrayList<JLabel> meaningLabelList;
	
	public SearchUI(ArrayList<Tango> list){
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�˻�");
		
		search_Panel 	= new JPanel();
		image_Label 	= new JLabel("����");
		hanja_Label 	= new JLabel("����");
		hiragana_Label 	= new JLabel("���󰡳�");
		meaning_Label 	= new JLabel("��");
		update_Label 	= new JLabel("����");
		delete_Label 	= new JLabel("����");
		
		delete_All_btn = new JButton("��ü ����");
		
		search_Panel.add(image_Label);
		search_Panel.add(hanja_Label);
		search_Panel.add(hiragana_Label);
		search_Panel.add(meaning_Label);
		search_Panel.add(update_Label);
		search_Panel.add(delete_Label);
		
		for(Tango t: list){
			//��ư �߰�
			delete_btn = new JButton("����");
			update_btn = new JButton("����");
			
			deleteButtonList.add(delete_btn);
			updateButtonList.add(update_btn);
			
			//���̺��߰�
		
			
		   	
			
		   // image_Label		= new JLabel(new ImageIcon(image));//���� ���̺�
			hanja_Label 	= new JLabel(t.getHanja()); 	//���� ���̺�
			hiragana_Label	= new JLabel(t.getHiragana()); //���󰡳� ���̺�
			meaning_Label 	= new JLabel(t.getMeaning()); 	//�� ���̺�
			
			imageLabelList.add(image_Label);
			hanjaLabelList.add(hanja_Label);
			hiraganaLabelList.add(hiragana_Label);
			meaningLabelList.add(meaning_Label);	
			
			search_Panel.add(image_Label);		
			search_Panel.add(hanja_Label);
			search_Panel.add(hiragana_Label);
			search_Panel.add(meaning_Label);		
		}		
	}
	public static void main(String[] args) {
		
	}
}
