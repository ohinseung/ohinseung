package client;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import exception.ManagerException;
import manager.Manager;
import vo.Tango;


public class ClientManager implements Manager{
	
	private final int PORT = 7777; // 포트 번호

	private ArrayList<Tango> list = new ArrayList<>(); // tango 객체 정보 리스트

	private Socket socket;	
	private InputStream is; // 인풋 스트림
	private OutputStream os; // 아웃풋 스트림
	private ObjectInputStream ois; // 오브젝트 인풋 스트림
	private ObjectOutputStream oos; // 오브젝트 아웃풋 스트림

	/**
	 * 생성자
	 */
	public ClientManager() {
		try {
			// 포트 번호를 통해 소켓을 생성한다
			socket = new Socket("localhost", PORT);

			// 인풋 아웃풋 스트림 생성
			is = socket.getInputStream();
			os = socket.getOutputStream();

			// 오브젝트 스트림 생성
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
			
		} catch (Exception e) {
			System.out.println("[INFO] 접속 도중 에러가 나타났습니다");
			closeStreams();
			System.exit(0);
		}
	}
	
	/**
	 * Tango 객체를 서버에 저장한다
	 * 
	 * @param tango
	 *            서버에 저장할 Tango 객체
	 */
	@Override
	public boolean insertTango(Tango tango) throws ManagerException {
		Object[] msg = {"insert", tango};
		boolean insertResult = (Boolean) sendRequest(msg);
		return insertResult;		
	}
	
	/**
	 * 요청된 meaning로 Tango 객체를 찾는다
	 * 
	 * @param mean
	 *            찾고자 하는 단어의 뜻
	 */
	@Override
	public ArrayList<Tango> findTango_meaning(String meaning) throws ManagerException {
		Object[] msg = {"find_meaning", meaning};
		ArrayList<Tango> findResult = (ArrayList<Tango>) sendRequest(msg);
		return findResult;
	}
	
	
	
	/**
	 * 지정된 row_id로 Tango 객체를 찾는다
	 * 
	 * @param row_id
	 *            찾고자 하는 시퀀스 넘버
	 */
	@Override
	public Tango findTango_row_id(int row_id) throws ManagerException {
		Object[] msg = {"find_row_id", row_id};
		Tango findResult = (Tango) sendRequest(msg);
		return findResult;
	}
	
	/**
	 * 요청된 hanja로 Tango 객체를 찾는다
	 * 
	 * @param hanja
	 *            찾고자 하는 한자
	 */
	@Override
	public ArrayList<Tango> findTango_hanja(String hanja) throws ManagerException {
		Object[] msg = {"find_hanja", hanja};
		ArrayList<Tango> findResult = (ArrayList<Tango>) sendRequest(msg);
		return findResult;
	}
	/**
	 * 요청된 hiragana로 Tango 객체를 찾는다
	 * 
	 * @param hiragana
	 *            찾고자 하는 히라가나
	 */
	@Override
	public ArrayList<Tango> findTango_hiragana(String hiragana) throws ManagerException {
		Object[] msg = {"find_hiragana", hiragana};
		ArrayList<Tango> findResult = (ArrayList<Tango>) sendRequest(msg);
		return findResult;
	}
	/**
	 * 서버에서 지정된 시퀀스 넘버를 가지는 tango 객체를 삭제한다
	 * 
	 * @param row_id
	 *            삭제하고자 하는 시퀀스 넘버
	 */
	@Override
	public boolean deleteTango(int row_id) throws ManagerException {
		Object[] msg = {"delete", row_id};
		boolean deleteResult = (Boolean) sendRequest(msg);
		return deleteResult;
	}
	
	@Override
	public ArrayList<Tango> getTangoList() throws ManagerException {
		Object[] msg = {"getList",null};
		list = (ArrayList<Tango>) sendRequest(msg);
		return list;
	}
	
	@Override
	public boolean updateTango(Tango newData) throws ManagerException {
		Object[] msg = {"update", newData};
		boolean updateResult = (Boolean) sendRequest(msg);
		return updateResult;
	}
	

	
	public boolean deleteAll() {
		Object[] msg = {"deleteAll", null};
		boolean deleteResult = (Boolean) sendRequest(msg);
		return deleteResult;
	}	
	
	public ArrayList<Tango> getQuizList(int quizNum) {
		Object[] msg = {"getQuizList", quizNum};
		ArrayList<Tango> quizList = (ArrayList<Tango>) sendRequest(msg);
		return quizList;
	}

	/**
	 * 서버로 요청을 보낸다
	 * 
	 * @param message
	 *            서버로 보낼 메시지
	 * @return 받는 메시지
	 */
	public Object sendRequest(Object[] message) {
		Object received = null;
		try {
			oos.writeObject(message);
			received = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return received;
	}


	/**
	 * 열어 놓은 스트림들을 모두 닫는다.
	 */
	public void closeStreams() {
		// 인풋 스트림이 존재하면 닫는다
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 아웃풋 스트림이 존재하면 닫는다
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 오브젝트 인풋 스트림이 존재하면 닫는다
		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 오브젝트 아웃풋 스트림이 존재하면 닫는다
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//사진 크기 설정 메소드
	public ImageIcon getScaledImage(byte[] imagebuf, int w, int h) {		
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    InputStream in = new ByteArrayInputStream(imagebuf); 
	    BufferedImage bufferedImage = null;		
		try {
			bufferedImage = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon image = new ImageIcon(bufferedImage);
		g2.drawImage(image.getImage(), 0, 0, w, h, null);
		g2.dispose();		
	    ImageIcon resizedImageIcon = new ImageIcon(resizedImg);
	    return resizedImageIcon;
	}

}
