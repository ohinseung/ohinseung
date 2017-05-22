package client;

import java.util.InputMismatchException;

import exception.ManagerException;
import vo.Tango;

public class ClientMain {
	private ClientManager manager = new ClientManager(); // ��û�� ���õ� ó���� �ϱ� ���� ������ ClientManager Ŭ������ ��ü
	
	public ClientMain(){
	
		while(true)	{
			// �޴��� ����Ѵ�
			// printMainMenu();
	
			try {
				// ���ڸ� �Է¹޴´�
				int no = 0;
	
				switch (no) {
				
				// Tango ��ü�� �����Ͽ� �߰��Ѵ�
				case 1:
					// insertTango();
					break;
	
				// row_id�� �Է¹޾� �ش��ϴ� Tango ��ü�� ã�´�
				case 2:	
					// row_id�� �Է¹޴´�
					int row_id = 0;
					
					// �Ŵ��� ��ü�κ��� ������ �ѹ��� �ش��ϴ� ��ü�� ã�´�
					try {
						Tango find_tango = manager.findTango_row_id(row_id);
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
					break;
				// hanja�� �Է¹޾� �ش��ϴ� Tango ��ü�� ã�´�		
				case 3:
					// hanja�� �Է¹޴´�
					String hanja = "";
					
					// �Ŵ��� ��ü�κ��� ������ �ѹ��� �ش��ϴ� ��ü�� ã�´�
					try {
						Tango find_tango = manager.findTango_hanja(hanja);
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
					break;				
	
				// hiragana�� �Է¹޾� �ش��ϴ� Tango ��ü�� ã�´�
				case 4:	
					// hiragana�� �Է¹޴´�
					String hiragana = "";
					
					// �Ŵ��� ��ü�κ��� ������ �ѹ��� �ش��ϴ� ��ü�� ã�´�
					try {
						Tango find_tango = manager.findTango_hiragana(hiragana);
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
					break;	
				// ���� ����Ǿ� �ִ� ��� Tango ��ü�� ����Ѵ�
				case 5:		
					break;	
				// ������ �ѹ��� �ش��ϴ� Tango ��ü�� �����Ѵ�
				case 6:				
					break;
				// ������ �ѹ��� �ش��ϴ� Tango ��ü�� �����Ѵ�
				case 7:				
					// ������ �ѹ��� �Է¹޴´�
					int delete_row_id = 0;
	
					// �Ŵ��� ��ü�κ��� ������ �ѹ��� �ش��ϴ� ��ü�� ������ �õ��ϰ� ���� ���θ� �����޴´�
					boolean delete_result = false;
					try {
						delete_result = manager.deleteTango(delete_row_id);
					} catch (ManagerException e) {
						e.printStackTrace();
					}
	
					if (delete_result) {
						System.out.println("���������� ���� �Ǿ����ϴ�.");
					} else {
						System.out.println("�赥�� �����Ͱ� �����ϴ�.");
					}
					break;
				// ���α׷��� �����Ѵ�
				case 9:
					manager.closeStreams();
					System.exit(0);
					break;
	
				// ���� ��ȣ�� �ش����� �ʴ´ٸ� �����Ѵ�
				default:
				}
			} catch (InputMismatchException e) {
				// ���ڷ� �Է¹��� �� ���� ������ �� ��� Exception ó��
				// e.printStackTrace();
			}
		}
	}

	/**
	 * ���� ���� �޴��� ����Ѵ�.
	 */
	public void printMainMenu() {
		System.out.print(" �޴� ��ȣ�� �����ϼ���=> ");
	}

	/**
	 * ���� �ܾ� ��� �޴��� ����Ѵ�.
	 */
	public void printInsertMenu() {
		System.out.print(" �޴� ��ȣ�� �����ϼ���=> ");
	}
}
