package exception;

/**
 * 서버 매니저가 데이터를 관리하는 도중 에러가 발생하는 경우 사용하기 위한 사용자 정의 예외
 *
 */
public class ServerManagerException extends ManagerException {

	/**
	 * 생성자
	 */
	public ServerManagerException() {
		System.out.println("[ERR] 데이터 관리 도중 에러가 발생했습니다");
	}
}
