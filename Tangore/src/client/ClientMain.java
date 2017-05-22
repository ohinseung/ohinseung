package client;

import java.util.InputMismatchException;

import exception.ClientManagerException;
import vo.Tango;

public class ClientMain {
	private ClientManager manager = new ClientManager(); // ��û�� ���õ� ó���� �ϱ� ���� ������ ClientManager Ŭ������ ��ü
}
	while(true)	{
		// �޴��� ����Ѵ�
		// printMainMenu();

		try {
			// ���ڸ� �Է¹޴´�
			int no = sc.nextInt();

			switch (no) {
			// Tango ��ü�� �����Ͽ� �߰��Ѵ�
			case 1:
				// insertTango();
				break;

			// row_id�� �Է¹޾� �ش��ϴ� Tango ��ü�� ã�´�
			case 2:

				// row_id�� �Է¹޴´�
				String row_id = "";
				
				// �Ŵ��� ��ü�κ��� �ֹι�ȣ�� �ش��ϴ� ��ü�� ã�´�
				Tango find_tango = manager.(find_jumin);

				break;

			// �ֹι�ȣ�� �ش��ϴ� Tango ��ü�� �����Ѵ�
			case 3:

				// ������ �ѹ��� �Է¹޴´�
				String delete_row_id = "";

				// �Ŵ��� ��ü�κ��� ������ �ѹ��� �ش��ϴ� ��ü�� ������ �õ��ϰ� ���� ���θ� �����޴´�
				boolean delete_result = manager.deleteTango(delete_row_id);

				if (delete_result) {
					System.out.println("���������� ���� �Ǿ����ϴ�.");
				} else {
					System.out.println("�赥�� �����Ͱ� �����ϴ�.");
				}
				break;

			// ���� ����Ǿ� �ִ� ��� Tango ��ü�� ����Ѵ�
			case 4:		
				break;

			case 5:				
				break;
			case 6:				
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
		} catch (ClientManagerException e) {
			// ����� ���� �ͼ����� �Ͼ ��� ó��
			// e.printStackTrace();
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
