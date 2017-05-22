package exception;

/**
 * 서버 매니저에서 예외가 발생하여 클라이언트에 보낼 경우 사용하는 예외 클래스
 *
 */
public class ClientManagerException extends ManagerException {

	/**
	 * 생성자
	 */
	public ClientManagerException() {
		System.out.println("[ERR] 서버에서 에러를 보냈습니다");
	}
}
