package exception;

/**
 * 매니저 상에서 예외가 발생했을 시에 사용되는 예외 클래스
 *
 */
public class ManagerException extends Exception {
	
	/**
	 * 생성자
	 */
	public ManagerException() {
		System.out.println("[ERR] 매니저 상에서 오류가 발생했습니다");
	}

}
