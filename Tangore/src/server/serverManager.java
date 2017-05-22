package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import exception.ManagerException;
import manager.Manager;
import vo.Tango;

public class serverManager implements Manager{
	
	public boolean insertTango(Tango tango) {
		boolean result = false;
		
		Connection con = ConnectionManager.getConnection();
		
		try {
			String sql = "insert into Tango values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tango.getRow_id());
			//�������ѹ�
			pstmt.setString(2, tango.getHiragana());
			//����
			pstmt.setString(3, tango.getHanja());
			//����
			pstmt.setString(4, tango.getMeaning());
			//���󰡳�
			pstmt.setString(5, tango.getImage_url());
			//��
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		return result;
	}
	

	@Override
	public Tango findTango_row_id(int row_id) throws ManagerException {
		Tango t = null;
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from where row_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, row_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String hanja = rs.getString("hanja");
				String hiragana = rs.getString("hiragana");
				String meaning = rs.getString("meaning");
				String image_url = rs.getString("image_url");
				
				t = new Tango(row_id, hanja, hiragana, meaning, image_url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return t;
	}

	@Override
	public Tango findTango_hanja(String hanja) throws ManagerException {
		Tango t = null;
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from where hanja like '?%' = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hanja);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int row_id = rs.getInt("row_id");
				String hiragana = rs.getString("hiragana");
				String meaning = rs.getString("meaning");
				String image_url = rs.getString("image_url");
				
				t = new Tango(row_id, hanja, hiragana, meaning, image_url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return t;
	}

	@Override
	public Tango findTango_hiragana(String hiragana) throws ManagerException {
		Tango t = null;
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from where hiragana like '?%' = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hiragana);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int row_id = rs.getInt("row_id");
				String hanja = rs.getString("hanja");
				String meaning = rs.getString("meaning");
				String image_url = rs.getString("image_url");
				
				t = new Tango(row_id, hanja, hiragana, meaning, image_url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return t;
	}

	@Override
	public boolean deleteTango(int row_id) throws ManagerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Tango> getTangoList() throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTango(Tango newData) throws ManagerException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean deleteAll() throws ManagerException {
		// TODO Auto-generated method stub
		return false;
	}
}
