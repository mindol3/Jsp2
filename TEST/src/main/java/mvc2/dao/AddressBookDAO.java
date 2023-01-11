package mvc2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import market.ver01.dto.CartDTO;
import mvc2.dto.AddressBookDTO;

public class AddressBookDAO {

	Connection connection = null;
	PreparedStatement pstmt = null;
	
	String jdbc_driver = "org.mariadb.jdbc.Driver";
	String jdbc_url = "jdbc:mariadb://localhost:5000/webmarketdb";
	
	//DB 연결 메서드
	void connect() {
		try {
			Class.forName(jdbc_driver);
			
			connection = DriverManager.getConnection(jdbc_url,"root","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<AddressBookDTO> getDBList() {
		connect();
		ArrayList<AddressBookDTO> datas = new ArrayList<AddressBookDTO>();
		
		String sql = "SELECT * FROM address_book ORDER BY id DESC";
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				AddressBookDTO addressBookDTO = new AddressBookDTO();
				
				addressBookDTO.setId(rs.getInt("id"));
				addressBookDTO.setName(rs.getString("name"));
				addressBookDTO.setEmail(rs.getString("email"));
				addressBookDTO.setComdept(rs.getString("comdept"));
				addressBookDTO.setBirth(rs.getString("birth"));
				addressBookDTO.setTel(rs.getString("tel"));
				addressBookDTO.setMemo(rs.getString("memo"));
				datas.add(addressBookDTO);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return datas;
	}
	
	// 신규 주소록 메시지 추가 메서드
	public boolean insertDB(AddressBookDTO addressBookDTO) {
		connect();
		// sql 문자열, id 는 자동 들록 되므로 입력하지 않는다
		
		String sql="INSERT INTO address_book(name, email, birth, tel, comdept, memo) " +
				" VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, addressBookDTO.getName());
			pstmt.setString(2, addressBookDTO.getEmail());
			pstmt.setString(3, addressBookDTO.getBirth());
			pstmt.setString(4, addressBookDTO.getTel());
			pstmt.setString(5, addressBookDTO.getComdept());
			pstmt.setString(6, addressBookDTO.getMemo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	// 특정 주소록 게시글 가져오는 메서드
	public AddressBookDTO getDB(int id) {
		connect();
		
		String sql = "SELECT * FROM address_book WHERE id = ?";
		AddressBookDTO addressBookDTO = new AddressBookDTO();
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			// 데이터가 하나만 있으므로 rs.next()를 한번만 실행한다.
			rs.next();
			addressBookDTO.setId(rs.getInt("id"));
			addressBookDTO.setName(rs.getString("name"));
			addressBookDTO.setEmail(rs.getString("email"));
			addressBookDTO.setBirth(rs.getString("birth"));
			addressBookDTO.setTel(rs.getString("tel"));
			addressBookDTO.setComdept(rs.getString("comdept"));
			addressBookDTO.setMemo(rs.getString("memo"));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return addressBookDTO;
	}
	
	public boolean updateDB(AddressBookDTO addressBookDTO) {
		connect();
		
		String sql = "UPDATE address_book SET name=?, email=?, birth=?, tel=?, comdept=?, memo=? WHERE id=?";
				
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, addressBookDTO.getName());
			pstmt.setString(2, addressBookDTO.getEmail());
			pstmt.setString(3, addressBookDTO.getBirth());
			pstmt.setString(4, addressBookDTO.getTel());
			pstmt.setString(5, addressBookDTO.getComdept());
			pstmt.setString(6, addressBookDTO.getMemo());
			pstmt.setInt(7, addressBookDTO.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	// 특정 주소록 게시글 삭제 메서드
	public boolean deleteDB(int id) {
		connect();
		
		String sql = "DELETE FROM address_book WHERE id=?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	// 검색 기능
	public ArrayList<AddressBookDTO> getDBListSearch(String part, String keyword) {
		connect();
		ArrayList<AddressBookDTO> datas = new ArrayList<AddressBookDTO>();
		
		String sql = "SELECT * FROM address_book WHERE " + part + " LIKE ? ORDER BY id DESC";
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				AddressBookDTO addressBookDTO = new AddressBookDTO();
				
				addressBookDTO.setId(rs.getInt("id"));
				addressBookDTO.setName(rs.getString("name"));
				addressBookDTO.setEmail(rs.getString("email"));
				addressBookDTO.setComdept(rs.getString("comdept"));
				addressBookDTO.setBirth(rs.getString("birth"));
				addressBookDTO.setTel(rs.getString("tel"));
				addressBookDTO.setMemo(rs.getString("memo"));
				datas.add(addressBookDTO);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return datas;
	}
}
