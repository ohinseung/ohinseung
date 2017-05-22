package client;

import java.util.InputMismatchException;

import exception.ManagerException;
import vo.Tango;

public class ClientMain {
	private ClientManager manager = new ClientManager(); // 요청과 관련된 처리를 하기 위해 생성한 ClientManager 클래스의 객체
	
	public ClientMain(){
	
		while(true)	{
			// 메뉴를 출력한다
			// printMainMenu();
	
			try {
				// 숫자를 입력받는다
				int no = 0;
	
				switch (no) {
				
				// Tango 객체를 생성하여 추가한다
				case 1:
					// insertTango();
					break;
	
				// row_id를 입력받아 해당하는 Tango 객체를 찾는다
				case 2:	
					// row_id를 입력받는다
					int row_id = 0;
					
					// 매니저 객체로부터 시퀀스 넘버로 해당하는 객체를 찾는다
					try {
						Tango find_tango = manager.findTango_row_id(row_id);
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
					break;
				// hanja를 입력받아 해당하는 Tango 객체를 찾는다		
				case 3:
					// hanja를 입력받는다
					String hanja = "";
					
					// 매니저 객체로부터 시퀀스 넘버로 해당하는 객체를 찾는다
					try {
						Tango find_tango = manager.findTango_hanja(hanja);
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
					break;				
	
				// hiragana를 입력받아 해당하는 Tango 객체를 찾는다
				case 4:	
					// hiragana를 입력받는다
					String hiragana = "";
					
					// 매니저 객체로부터 시퀀스 넘버로 해당하는 객체를 찾는다
					try {
						Tango find_tango = manager.findTango_hiragana(hiragana);
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
					break;	
				// 현재 저장되어 있는 모든 Tango 객체를 출력한다
				case 5:		
					break;	
				// 시퀀스 넘버로 해당하는 Tango 객체를 수정한다
				case 6:				
					break;
				// 시퀀스 넘버로 해당하는 Tango 객체를 삭제한다
				case 7:				
					// 시퀀스 넘버를 입력받는다
					int delete_row_id = 0;
	
					// 매니저 객체로부터 시퀀스 넘버에 해당하는 객체를 삭제를 시도하고 성공 여부를 돌려받는다
					boolean delete_result = false;
					try {
						delete_result = manager.deleteTango(delete_row_id);
					} catch (ManagerException e) {
						e.printStackTrace();
					}
	
					if (delete_result) {
						System.out.println("정상적으로 삭제 되었습니다.");
					} else {
						System.out.println("삭데할 데이터가 없습니다.");
					}
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
			}
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
