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

	private static final String URL = "jdbc:mysql://localhost/dvd_shop";
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

	// DVDを貸出状態に更新する処理（すでに貸出中なら処理しない）
	public static boolean lendDVD(String id, String code) {
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

	        // まず、そのDVDが貸出中か確認
	        try (PreparedStatement checkPs = conn.prepareStatement("SELECT is_lent FROM dvd WHERE code = ?")) {
	            checkPs.setString(1, code);
	            ResultSet rs = checkPs.executeQuery();
	            if (rs.next()) {
	                boolean isLent = rs.getBoolean("is_lent");
	                if (isLent) {
	                    return false; // すでに貸出中
	                }
	            } else {
	                return false; // DVDコードが存在しない
	            }
	        }

	        // 借りられる場合だけ更新
	        try (PreparedStatement ps = conn.prepareStatement("UPDATE dvd SET is_lent = true WHERE code = ?")) {
	            ps.setString(1, code);
	            ps.executeUpdate();
	        }

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
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
	        ResultSet rs = st.executeQuery("SELECT code,title FROM dvd WHERE is_lent = true")) {
	        
	        while(rs.next()) {
	            // 表示形式を「コード: XXX｜タイトル: YYY」に変更
	            dvds.add("コード: " + rs.getString("code") + "｜タイトル: " + rs.getString("title")); // ← 変更
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return dvds;
	}
}