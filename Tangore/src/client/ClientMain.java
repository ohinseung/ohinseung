package client;

import java.util.InputMismatchException;

import exception.ClientManagerException;
import vo.Tango;

public class ClientMain {
	private ClientManager manager = new ClientManager(); // 요청과 관련된 처리를 하기 위해 생성한 ClientManager 클래스의 객체
}
	while(true)	{
		// 메뉴를 출력한다
		// printMainMenu();

		try {
			// 숫자를 입력받는다
			int no = sc.nextInt();

			switch (no) {
			// Tango 객체를 생성하여 추가한다
			case 1:
				// insertTango();
				break;

			// row_id를 입력받아 해당하는 Tango 객체를 찾는다
			case 2:

				// row_id를 입력받는다
				String row_id = "";
				
				// 매니저 객체로부터 주민번호로 해당하는 객체를 찾는다
				Tango find_tango = manager.(find_jumin);

				break;

			// 주민번호로 해당하는 Tango 객체를 삭제한다
			case 3:

				// 시퀀스 넘버를 입력받는다
				String delete_row_id = "";

				// 매니저 객체로부터 시퀀스 넘버에 해당하는 객체를 삭제를 시도하고 성공 여부를 돌려받는다
				boolean delete_result = manager.deleteTango(delete_row_id);

				if (delete_result) {
					System.out.println("정상적으로 삭제 되었습니다.");
				} else {
					System.out.println("삭데할 데이터가 없습니다.");
				}
				break;

			// 현재 저장되어 있는 모든 Tango 객체를 출력한다
			case 4:		
				break;

			case 5:				
				break;
			case 6:				
				break;
			// 프로그램을 종료한다
			case 9:
				manager.closeStreams();
				System.exit(0);
				break;

			// 위의 번호에 해당하지 않는다면 무시한다
			default:
			}
		} catch (InputMismatchException e) {
			// 숫자로 입력받을 수 없는 에러가 날 경우 Exception 처리
			// e.printStackTrace();
		} catch (ClientManagerException e) {
			// 사용자 정의 익셉션이 일어날 경우 처리
			// e.printStackTrace();
		}

	}

	/**
	 * 탕고래 메인 메뉴를 출력한다.
	 */
	public void printMainMenu() {
		System.out.print(" 메뉴 번호를 선택하세요=> ");
	}

	/**
	 * 탕고래 단어 등록 메뉴를 출력한다.
	 */
	public void printInsertMenu() {
		System.out.print(" 메뉴 번호를 선택하세요=> ");
	}
}
