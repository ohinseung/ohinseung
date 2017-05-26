package server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import javax.swing.ImageIcon;

import exception.ManagerException;
import manager.Manager;
import vo.Tango;


public class serverManager implements Manager{
	
	public boolean insertTango(Tango tango) {
		boolean result = false;
		
		Connection con = ConnectionManager.getConnection();
		
		try {
			String sql = "insert into Tangore values (SEQ_TANGORE.nextval, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			//히라가나
			pstmt.setString(1, tango.getHiragana());
			//한자
			pstmt.setString(2, tango.getHanja());
			//뜻
			pstmt.setString(3, tango.getMeaning());
			//사진
			ByteArrayInputStream bais = new ByteArrayInputStream(tango.getimagebuf());
			pstmt.setBinaryStream(4, bais);			
			pstmt.executeUpdate();
			result = true;
			
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
		Tango tango = null;
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore where row_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, row_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String hanja 	= rs.getString("hanja");
				String hiragana = rs.getString("hiragana");
				String meaning 	= rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("image");
				byte[] imagebuf = toByteArray(in);
				tango = new Tango(row_id, hiragana, hanja, meaning, imagebuf);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return tango;
	}

	@Override
	public ArrayList<Tango> findTango_hanja(String hanja) throws ManagerException {
		ArrayList<Tango> list = new ArrayList<>();
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore where hanja like '%"+hanja+"%'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int row_id 					= rs.getInt("row_id");
				String findHanja			= rs.getString("hanja");
				String hiragana 			= rs.getString("hiragana");
				String meaning 				= rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("image");
				byte[] imagebuf = toByteArray(in);
				Tango tango = new Tango(row_id, hiragana, findHanja, meaning, imagebuf);				
				list.add(tango);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return list;
	}

	@Override
	public ArrayList<Tango> findTango_hiragana(String hiragana) throws ManagerException {
		ArrayList<Tango> list = new ArrayList<>();
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore where hiragana like '"+ hiragana +"%'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, hiragana);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int row_id 					= rs.getInt("row_id");
				String findHiragana 		= rs.getString("hiragana");
				String hanja 				= rs.getString("hanja");
				String meaning 				= rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("image");
				byte[] imagebuf = toByteArray(in);
				Tango tango = new Tango(row_id, findHiragana, hanja, meaning, imagebuf);				
				list.add(tango);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return list;
	}
	@Override
	public ArrayList<Tango> findTango_meaning(String meaning) throws ManagerException {
		ArrayList<Tango> list = new ArrayList<>();
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore where meaning like '"+meaning+"%'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, meaning);
			System.out.println("어디까지 왔나");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int row_id 			= rs.getInt("row_id");
				String hanja 		= rs.getString("hanja");
				String hiragana 	= rs.getString("hiragana");
				String findMeaning 	= rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in 		= rs.getBinaryStream("image");
				byte[] imagebuf		= toByteArray(in);
				Tango tango			= new Tango(row_id, hiragana, hanja, findMeaning, imagebuf);				
				list.add(tango);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return list;
	}

	@Override
	public boolean deleteTango(int row_id) throws ManagerException {
		boolean result = false;
		Connection con = null;
		
		try {
			// 매니저로부터 Connection 객체를 얻는다
			con = ConnectionManager.getConnection();			
			
			String sql = "delete from tangore where row_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, row_id);
			pstmt.executeUpdate();
			
			// 결과값은 true
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 끝나면 Connection을 종료한다
			ConnectionManager.close(con);
		}	
		return result;
		
	}

	@Override
	public ArrayList<Tango> getTangoList() throws ManagerException {
		ArrayList<Tango> list = new ArrayList<>();
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				int row_id 		= rs.getInt("row_id");
				String hiragana = rs.getString("hiragana");
				String hanja 	= rs.getString("hanja");
				String meaning 	= rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in 	= rs.getBinaryStream("image");
				byte[] imagebuf = toByteArray(in);
				Tango tango 	= new Tango(row_id, hiragana, hanja, meaning, imagebuf);				
				list.add(tango);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return list;
	}

	@Override
	public boolean updateTango(Tango newData) throws ManagerException {
		boolean result = false;
		Connection con = null;
		System.out.println("들어온단어 확인 : [히라가나] "+ newData.getHiragana() + " [한자] "+ newData.getHanja() + " [뜻] " + newData.getMeaning()  );
		try {
			con = ConnectionManager.getConnection();
			if(newData.getimagebuf() != null){
				String sql = "update Tangore set hiragana=?, hanja=?, meaning=?, image =? where row_id = ?";
				PreparedStatement pstmt 	= con.prepareStatement(sql);
				ByteArrayInputStream bais 	= null;
				bais = new ByteArrayInputStream(newData.getimagebuf());
				pstmt.setString(1, newData.getHiragana());
				pstmt.setString(2, newData.getHanja());
				pstmt.setString(3, newData.getMeaning());
				pstmt.setBinaryStream(4, bais);
				pstmt.setInt(5, newData.getRow_id());
				pstmt.executeUpdate();
				result = true;
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		return result;
	}


	@Override
	public boolean deleteAll() throws ManagerException {
		boolean result = false;
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "delete from Tangore";
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Tango> getQuizList(int quizNum) throws ManagerException {
		ArrayList<Tango> list = new ArrayList<>();
		ArrayList<Tango> quizList = new ArrayList<>();
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				int row_id 		= rs.getInt("row_id");
				String hiragana = rs.getString("hiragana");
				String hanja 	= rs.getString("hanja");
				String meaning 	= rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("image");
				byte[] imagebuf = toByteArray(in);
				Tango tango = new Tango(row_id, hiragana, hanja, meaning, imagebuf);				
				list.add(tango);
			}
			
			Random r = new Random();
			//정해준 문제수 만큼 반복
			while (quizList.size() < quizNum){
				//랜덤생성
				int index = r.nextInt(list.size());
				Tango t = list.get(index);				
				//존재 여부 확인 후 리스트에 저장
				if(quizList.contains(t)) continue;
				else{
					quizList.add(t);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}		
		return quizList;
	}
	
	public byte[] toByteArray(InputStream inputStream) throws IOException {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    byte [] buffer = new byte[1024];
	    while(true) {
	        int len = inputStream.read(buffer);
	        if(len < 0) {
	            break;
	        }
	        bout.write(buffer, 0, len);
	    }
	    return bout.toByteArray();
	}
}
