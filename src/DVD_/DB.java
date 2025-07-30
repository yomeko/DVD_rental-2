package DVD_;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DB {
	//DBアクセス＆ユーザー名とパスワード

	private static final String URL = "jdbc:mysql://localhost/dvd_shop";
	private static final String USER ="root";
	private static final String PASS ="";

	// 会員の登録（idとnameが空またはnullならエラー）
	public static void insertMember(String id, String name) {
	    if (id == null || id.isEmpty() || name == null || name.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "IDと名前の両方を入力してください。", "入力エラー", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
	         PreparedStatement ps = conn.prepareStatement("INSERT INTO member(id, name) VALUES(?, ?)")) {
	        ps.setString(1, id);
	        ps.setString(2, name);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "会員登録に失敗しました。", "登録エラー", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace(); // ← 開発中だけ残して、完成後に消してOK
	    }
	}

	// DVDの登録（codeが重複していたらエラー）
	public static void insertDVD(String code, String title) {
	    if (code == null || code.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "DVDコードを入力してください。", "入力エラー", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	        // コード重複チェック
	        PreparedStatement check = conn.prepareStatement("SELECT COUNT(*) FROM dvd WHERE code = ?");
	        check.setString(1, code);
	        ResultSet rs = check.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        if (count > 0) {
	            JOptionPane.showMessageDialog(null, "このコードはすでに登録されています。", "登録エラー", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // 登録処理
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO dvd(code, title, is_lent) VALUES(?, ?, false)");
	        ps.setString(1, code);
	        ps.setString(2, title);
	        ps.executeUpdate();

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "DVD登録に失敗しました。", "登録エラー", JOptionPane.ERROR_MESSAGE);
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
	        ResultSet rs = st.executeQuery("SELECT code,title FROM dvd WHERE is_lent = true")) {

	        while(rs.next()) {
	            // 表示形式を「コード: XXX｜タイトル: YYY」に変更
	            dvds.add("会員コード: " + rs.getString("code") + "｜タイトル: " + rs.getString("title")); // ← 変更
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return dvds;
	}
}