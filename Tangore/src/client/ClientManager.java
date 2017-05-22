package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import exception.ManagerException;
import manager.Manager;
import vo.Tango;


public class ClientManager implements Manager{
	
	private final int PORT = 6666; // ��Ʈ ��ȣ

	private ArrayList<Tango> list; // tango ��ü ���� ����Ʈ

	private Socket socket;	
	private InputStream is; // ��ǲ ��Ʈ��
	private OutputStream os; // �ƿ�ǲ ��Ʈ��
	private ObjectInputStream ois; // ������Ʈ ��ǲ ��Ʈ��
	private ObjectOutputStream oos; // ������Ʈ �ƿ�ǲ ��Ʈ��

	/**
	 * ������
	 */
	public ClientManager() {
		try {
			// ��Ʈ ��ȣ�� ���� ������ �����Ѵ�
			socket = new Socket("localhost", PORT);

			// ��ǲ �ƿ�ǲ ��Ʈ�� ����
			is = socket.getInputStream();
			os = socket.getOutputStream();

			// ������Ʈ ��Ʈ�� ����
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
			
		} catch (Exception e) {
			System.out.println("[INFO] ���� ���� ������ ��Ÿ�����ϴ�");
			closeStreams();
			System.exit(0);
		}
	}
	
	/**
	 * Tango ��ü�� ������ �����Ѵ�
	 * 
	 * @param tango
	 *            ������ ������ Tango ��ü
	 */
	@Override
	public boolean insertTango(Tango tango) throws ManagerException {
		Object[] msg = {"insert", tango};
		boolean insertResult = (Boolean) sendRequest(msg);
		return insertResult;		
	}
	/**
	 * ������ row_id�� Tango ��ü�� ã�´�
	 * 
	 * @param row_id
	 *            ã���� �ϴ� ������ �ѹ�
	 */
	@Override
	public Tango findTango_row_id(int row_id) throws ManagerException {
		Object[] msg = {"find_row_id", row_id};
		Tango findResult = (Tango) sendRequest(msg);
		return findResult;
	}
	
	/**
	 * ��û�� hanja�� Tango ��ü�� ã�´�
	 * 
	 * @param hanja
	 *            ã���� �ϴ� ����
	 */
	@Override
	public Tango findTango_hanja(String hanja) throws ManagerException {
		Object[] msg = {"find_hanja", hanja};
		Tango findResult = (Tango) sendRequest(msg);
		return findResult;
	}
	/**
	 * ��û�� hiragana�� Tango ��ü�� ã�´�
	 * 
	 * @param hiragana
	 *            ã���� �ϴ� ���󰡳�
	 */
	@Override
	public Tango findTango_hiragana(String hiragana) throws ManagerException {
		Object[] msg = {"find_hiragana", hiragana};
		Tango findResult = (Tango) sendRequest(msg);
		return findResult;
	}
	/**
	 * �������� ������ ������ �ѹ��� ������ tango ��ü�� �����Ѵ�
	 * 
	 * @param row_id
	 *            �����ϰ��� �ϴ� ������ �ѹ�
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
	

	/**
	 * ������ ��û�� ������
	 * 
	 * @param message
	 *            ������ ���� �޽���
	 * @return �޴� �޽���
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
	 * ���� ���� ��Ʈ������ ��� �ݴ´�.
	 */
	public void closeStreams() {
		// ��ǲ ��Ʈ���� �����ϸ� �ݴ´�
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// �ƿ�ǲ ��Ʈ���� �����ϸ� �ݴ´�
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// ������Ʈ ��ǲ ��Ʈ���� �����ϸ� �ݴ´�
		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// ������Ʈ �ƿ�ǲ ��Ʈ���� �����ϸ� �ݴ´�
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
