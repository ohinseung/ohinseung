package server;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
			System.out.println(tango.getHanja().toString()+tango.getHiragana().toString()+tango.getMeaning().toString()+tango.getimageFile().toString());
			
			//히라가나
			pstmt.setString(1, tango.getHiragana());
			//한자
			pstmt.setString(2, tango.getHanja());
			//뜻
			pstmt.setString(3, tango.getMeaning());
			//사진
			FileInputStream fis = new FileInputStream(tango.getimageFile());
			pstmt.setBinaryStream(4, fis);
			
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
	public ArrayList<Tango> findTango_row_id(int row_id) throws ManagerException {
		ArrayList<Tango> list = new ArrayList<>();
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore where row_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, row_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String hanja = rs.getString("hanja");
				String hiragana = rs.getString("hiragana");
				String meaning = rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("image");
				// BufferedImage를 생성하면 ImageIO를 통해 브라우저에 출력하기가 쉽다.
				BufferedImage bufferedImage = ImageIO.read(in);
				ImageIcon image = new ImageIcon(bufferedImage);
				
				Tango tango = new Tango(row_id, hanja, hiragana, meaning, image);
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
	public ArrayList<Tango> findTango_hanja(String hanja) throws ManagerException {
		ArrayList<Tango> list = new ArrayList<>();
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore where hanja like '%?%'?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hanja);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int row_id = rs.getInt("row_id");
				String hiragana = rs.getString("hiragana");
				String meaning = rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("image");
				// BufferedImage를 생성하면 ImageIO를 통해 브라우저에 출력하기가 쉽다.
				BufferedImage bufferedImage = ImageIO.read(in);
				ImageIcon image = new ImageIcon(bufferedImage);
				Tango tango 	= new Tango(row_id, hanja, hiragana, meaning, image);
				
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
			String sql = "select * from Tangore where hiragana like '?%'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hiragana);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int row_id = rs.getInt("row_id");
				String hanja = rs.getString("hanja");
				String meaning = rs.getString("meaning");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("image");
				// BufferedImage를 생성하면 ImageIO를 통해 브라우저에 출력하기가 쉽다.
				BufferedImage bufferedImage = ImageIO.read(in);
				ImageIcon image = new ImageIcon(bufferedImage);
				Tango tango 	= new Tango(row_id, hanja, hiragana, meaning, image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(con);
		}
		
		return list;
	}
	@Override
	public ArrayList<Tango> findTango_meaing(String meaning) throws ManagerException {
		ArrayList<Tango> list = new ArrayList<>();
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from Tangore where meanging like '?%'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, meaning);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int row_id = rs.getInt("row_id");
				String hanja = rs.getString("hanja");
				String hiragana = rs.getString("hiragana");
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("image");
				// BufferedImage를 생성하면 ImageIO를 통해 브라우저에 출력하기가 쉽다.
				BufferedImage bufferedImage = ImageIO.read(in);
				ImageIcon image = new ImageIcon(bufferedImage);
				
				Tango tango 	= new Tango(row_id, hanja, hiragana, meaning, image);
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
		
		return false;
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
				InputStream in = rs.getBinaryStream("image");
				// BufferedImage를 생성하면 ImageIO를 통해 브라우저에 출력하기가 쉽다.
				BufferedImage bufferedImage = ImageIO.read(in);
				ImageIcon image = new ImageIcon(bufferedImage);

				Tango tango 	= new Tango(row_id, hiragana, hanja, meaning, image);
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
		System.out.println("들어온단어 확인 : "+ newData.getHiragana());
		try {
			con = ConnectionManager.getConnection();
			String sql = "update Tangore set hiragana=?, hanja=?, meaning=? where row_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newData.getHiragana());
			pstmt.setString(2, newData.getHanja());
			pstmt.setString(3, newData.getMeaning());
			pstmt.setInt(4, newData.getRow_id());
			
			pstmt.executeUpdate();
			
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
}
