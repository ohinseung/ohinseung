package vo;

public class Tango {
	
	private int row_id;
	private String hiragana;
	private String hanja;
	private String meaning;
	private String image_url;
	
	
	
	public Tango() {
		super();
	}

	public Tango(int row_id, String hiragana, String hanja, String meaning, String image_url) {
		super();
		this.row_id = row_id;
		this.hiragana = hiragana;
		this.hanja = hanja;
		this.meaning = meaning;
		this.image_url = image_url;
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
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}	
}
