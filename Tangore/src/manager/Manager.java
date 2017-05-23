package manager;

import java.util.ArrayList;

import exception.ManagerException;
import vo.Tango;

public interface Manager {

	/**
	 * 새로운 Tango 객체를 등록한다.
	 * 
	 * @param row_id
	 *            등록할 Tango 클래스의 객체
	 */
	public boolean insertTango(Tango tango) throws ManagerException;

	/**
	 * 등록된 Tango 객체를 row_id로 검색한다.
	 * 
	 * @param row_id
	 *            검색할 Tango의 시퀀스넘버
	 * @return Tango 배열에 등록된 객체들 중 파라메터로 전달된 와 일치되는 Tango 객체, 검색 결과가 없을 시
	 *         null을 반환한다.
	 */
	
	public ArrayList<Tango> findTango_row_id(int row_id) throws ManagerException;
	/**
	 * 등록된 Tango 객체를 row_id로 검색한다.
	 * 
	 * @param row_id
	 *            검색할 Tango의 시퀀스넘버
	 * @return Tango 배열에 등록된 객체들 중 파라메터로 전달된 와 일치되는 Tango 객체, 검색 결과가 없을 시
	 *         null을 반환한다.
	 */
	
	public ArrayList<Tango>  findTango_hanja(String hanja) throws ManagerException;
	/**
	 * 등록된 Tango 객체를 row_id로 검색한다.
	 * 
	 * @param row_id
	 *            검색할 Tango의 시퀀스넘버
	 * @return Tango 배열에 등록된 객체들 중 파라메터로 전달된 와 일치되는 Tango 객체, 검색 결과가 없을 시
	 *         null을 반환한다.
	 */
	
	public ArrayList<Tango>  findTango_hiragana(String hiragana) throws ManagerException;


	/**
	 * 등록된 Tango 객체를 삭제한다.
	 * 
	 * @param row_id
	 *            삭제할 Tango의 시퀀스 넘버
	 * @return 주어진 시퀀스 넘버와 일치되는 Tango 객체의 삭제 결과, Tango 배열에 등록된 객체들 중 파라메터로 전달된
	 *         시퀀스 넘버와 일치되는 Tango 객체가 발견되어 삭제를 성공하면 true를 그렇지 않으면 false를 반환
	 */
	public boolean deleteTango(int row_id) throws ManagerException;

	ArrayList<Tango>  findTango_meaing(String meaning) throws ManagerException;

	
	/**
	 * Tango 객체 정보 리스트를 읽어들인다.
	 * 
	 * @param 모든
	 *            저장된 Tango 객체 정보
	 */
	public ArrayList<Tango> getTangoList() throws ManagerException;

	/**
	 * 등록된 Tango 객체를 갱신한다.
	 * 
	 * @param newData
	 */
	public boolean updateTango(Tango newData) throws ManagerException;

	public boolean deleteAll() throws ManagerException;


}
