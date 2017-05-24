package vo;

import java.io.File;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Tango implements Serializable{
	
	private int row_id;
	private String hiragana;
	private String hanja;
	private String meaning;
	private ImageIcon image;
	private File imageFile;
	
	
	
	public Tango() {
		super();
	}
	public Tango(int row_id, String hiragana, String hanja, String meaning, ImageIcon image) {
		super();
		this.row_id = row_id;
		this.hiragana = hiragana;
		this.hanja = hanja;
		this.meaning = meaning;
		this.image = image;
	}
	public Tango(String hiragana, String hanja, String meaning, ImageIcon image) {
		super();
		
		this.hiragana = hiragana;
		this.hanja = hanja;
		this.meaning = meaning;
		this.image = image;
	}
	public Tango(int row_id, String hiragana, String hanja, String meaning, File imageFile) {
		super();
		this.row_id = row_id;
		this.hiragana = hiragana;
		this.hanja = hanja;
		this.meaning = meaning;
		this.imageFile = imageFile;
	}
	public Tango(String hiragana, String hanja, String meaning, File imageFile) {
		super();		
		this.hiragana = hiragana;
		this.hanja = hanja;
		this.meaning = meaning;
		this.imageFile = imageFile;
	}
	
	public int getRow_id() {
		return row_id;
	}
	public void setRow_id(int row_id) {
		this.row_id = row_id;
	}
	public String getHiragana() {
		return hiragana;
	}
	public void setHiragana(String hiragana) {
		this.hiragana = hiragana;
	}
	public String getHanja() {
		return hanja;
	}
	public void setHanja(String hanja) {
		this.hanja = hanja;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public ImageIcon getimage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}	
	public File getimageFile() {
		return imageFile;
	}
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}	
}
