package vo;

import java.awt.image.BufferedImage;
import java.sql.Blob;

public class Tango {
	
	private int row_id;
	private String hiragana;
	private String hanja;
	private String meaning;
	private Blob image;
	
	
	
	public Tango() {
		super();
	}
	public Tango(int row_id, String hiragana, String hanja, String meaning, Blob image) {
		super();
		this.row_id = row_id;
		this.hiragana = hiragana;
		this.hanja = hanja;
		this.meaning = meaning;
		this.image = image;
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
	public Blob getimage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}	
}
