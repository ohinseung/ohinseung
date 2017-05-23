package manager;

import java.util.ArrayList;

import exception.ManagerException;
import vo.Tango;

public interface Manager {

	/**
	 * ���ο� Tango ��ü�� ����Ѵ�.
	 * 
	 * @param row_id
	 *            ����� Tango Ŭ������ ��ü
	 */
	public boolean insertTango(Tango tango) throws ManagerException;

	/**
	 * ��ϵ� Tango ��ü�� row_id�� �˻��Ѵ�.
	 * 
	 * @param row_id
	 *            �˻��� Tango�� �������ѹ�
	 * @return Tango �迭�� ��ϵ� ��ü�� �� �Ķ���ͷ� ���޵� �� ��ġ�Ǵ� Tango ��ü, �˻� ����� ���� ��
	 *         null�� ��ȯ�Ѵ�.
	 */
	
	public ArrayList<Tango> findTango_row_id(int row_id) throws ManagerException;
	/**
	 * ��ϵ� Tango ��ü�� row_id�� �˻��Ѵ�.
	 * 
	 * @param row_id
	 *            �˻��� Tango�� �������ѹ�
	 * @return Tango �迭�� ��ϵ� ��ü�� �� �Ķ���ͷ� ���޵� �� ��ġ�Ǵ� Tango ��ü, �˻� ����� ���� ��
	 *         null�� ��ȯ�Ѵ�.
	 */
	
	public ArrayList<Tango>  findTango_hanja(String hanja) throws ManagerException;
	/**
	 * ��ϵ� Tango ��ü�� row_id�� �˻��Ѵ�.
	 * 
	 * @param row_id
	 *            �˻��� Tango�� �������ѹ�
	 * @return Tango �迭�� ��ϵ� ��ü�� �� �Ķ���ͷ� ���޵� �� ��ġ�Ǵ� Tango ��ü, �˻� ����� ���� ��
	 *         null�� ��ȯ�Ѵ�.
	 */
	
	public ArrayList<Tango>  findTango_hiragana(String hiragana) throws ManagerException;


	/**
	 * ��ϵ� Tango ��ü�� �����Ѵ�.
	 * 
	 * @param row_id
	 *            ������ Tango�� ������ �ѹ�
	 * @return �־��� ������ �ѹ��� ��ġ�Ǵ� Tango ��ü�� ���� ���, Tango �迭�� ��ϵ� ��ü�� �� �Ķ���ͷ� ���޵�
	 *         ������ �ѹ��� ��ġ�Ǵ� Tango ��ü�� �߰ߵǾ� ������ �����ϸ� true�� �׷��� ������ false�� ��ȯ
	 */
	public boolean deleteTango(int row_id) throws ManagerException;

	ArrayList<Tango>  findTango_meaing(String meaning) throws ManagerException;

	
	/**
	 * Tango ��ü ���� ����Ʈ�� �о���δ�.
	 * 
	 * @param ���
	 *            ����� Tango ��ü ����
	 */
	public ArrayList<Tango> getTangoList() throws ManagerException;

	/**
	 * ��ϵ� Tango ��ü�� �����Ѵ�.
	 * 
	 * @param newData
	 */
	public boolean updateTango(Tango newData) throws ManagerException;

	public boolean deleteAll() throws ManagerException;


}
