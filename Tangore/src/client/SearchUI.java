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
	private JPanel search_Panel; //일단 필요 없을지도 모름. 

	private JButton delete_All_btn; //전체 삭제 버튼
	private JButton update_btn;	//수정 버튼
	private JButton delete_btn;	//삭제 버튼

	private JLabel image_Label; 	//사진 레이블
	private JLabel hanja_Label; 	//한자 레이블
	private JLabel hiragana_Label; //히라가나 레이블
	private JLabel meaning_Label; 	//뜻 레이블
	private JLabel update_Label; 	//수정 레이블
	private JLabel delete_Label; 	//삭제 레이블
	
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
		setTitle("검색");
		
		search_Panel 	= new JPanel();
		image_Label 	= new JLabel("사진");
		hanja_Label 	= new JLabel("한자");
		hiragana_Label 	= new JLabel("히라가나");
		meaning_Label 	= new JLabel("뜻");
		update_Label 	= new JLabel("수정");
		delete_Label 	= new JLabel("삭제");
		
		delete_All_btn = new JButton("전체 삭제");
		
		search_Panel.add(image_Label);
		search_Panel.add(hanja_Label);
		search_Panel.add(hiragana_Label);
		search_Panel.add(meaning_Label);
		search_Panel.add(update_Label);
		search_Panel.add(delete_Label);
		
		for(Tango t: list){
			//버튼 추가
			delete_btn = new JButton("삭제");
			update_btn = new JButton("수정");
			
			deleteButtonList.add(delete_btn);
			updateButtonList.add(update_btn);
			
			//레이블추가
		
			
		   	
			
		   // image_Label		= new JLabel(new ImageIcon(image));//사진 레이블
			hanja_Label 	= new JLabel(t.getHanja()); 	//한자 레이블
			hiragana_Label	= new JLabel(t.getHiragana()); //히라가나 레이블
			meaning_Label 	= new JLabel(t.getMeaning()); 	//뜻 레이블
			
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
