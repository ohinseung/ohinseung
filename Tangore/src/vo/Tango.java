package vo;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Tango implements Serializable {

	private int row_id;
	private String hiragana;
	private String hanja;
	private String meaning;
	private byte[] imagebuf;

	public Tango() {
		super();
	}
	public Tango(int row_id, String hiragana, String hanja, String meaning, byte[] imagebuf) {
		super();
		this.row_id = row_id;
		this.hiragana = hiragana;
		this.hanja = hanja;
		this.meaning = meaning;
		this.imagebuf = imagebuf;
	}

	public Tango(String hiragana, String hanja, String meaning, byte[] imagebuf) {
		super();
		this.hiragana = hiragana;
		this.hanja = hanja;
		this.meaning = meaning;
		this.imagebuf = imagebuf;
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

	public byte[] getimagebuf() {
		return imagebuf;
	}

	public void setimagebuf(byte[] imagebuf) {
		this.imagebuf = imagebuf;
	}
}
