package DVD_;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {
	//DBアクセス＆ユーザー名とパスワード
	
	private static final String URL = "jdbc:mysql://localhost/DVD_shop";
	private static final String USER ="root";
	private static final String PASS ="";
	
	//会員の登録
	
	public static void insertMember(String id,String name) {
		try(Connection conn =DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO member(id,name) VALUES(?,?)")){
			ps.setString(1, id);
			ps.setString(2, name);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//DVDの登録
	
	public static void insertDVD(String code,String title) {
		try(Connection conn =DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO dvd(code,title,is_lent) VALUES(?,?,false)")){
			ps.setString(1, code);
			ps.setString(2, title);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//DVDを貸出状態に更新する処理 
	
	public static void lendDVD(String id, String code) {
		try(Connection conn =DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("UPDATE dvd set is_lent =true WHERE code=?;")){
			ps.setString(1, code);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//DVDを返却済み状態に更新する処理
	
	public static void returnDVD(String code) {
		try(Connection conn =DriverManager.getConnection(URL,USER,PASS);
				PreparedStatement ps = conn.prepareStatement("UPDATE dvd set is_lent =false WHERE code=?;")){
			ps.setString(1, code);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	//貸し出し中のDVDリスト
	
	public static List<String> getLentDVDs(){
		List<String> dvds = new ArrayList<>();
		try(Connection conn =DriverManager.getConnection(URL,USER,PASS);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT code,title FROM book WHERE is_lent = true")){
			while(rs.next()) {
				dvds.add(rs.getString("code")+"-"+rs.getString("title"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dvds;
	}
}