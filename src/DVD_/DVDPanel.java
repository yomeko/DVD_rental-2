package DVD_;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DVDPanel {
    public DVDPanel(MainFrame frame) {
        //レイアウトに新しいGridLayout(3, 2)
    	setLayout(new GridLayout(3,2));
        //ボタンやフィールド作成
    	JTextField codeField =new JTextField();
    	JTextField titleField = new JTextField();  
    	JButton registerBtn  = new JButton("登録");
    	JButton backBtn =new JButton("TOPへ戻る");
    	//登録ボタンが押されたらIDとタイトル
    	registerBtn.addActionListener(e -> {
    		DB.insertDVD(codeField.getText(),titleField.getText());
    		JOptionPane.showMessageDialog(this, "登録しました");
    	});
    	//TOPに戻るボタン
    	backBtn.addActionListener(e -> frame.showPanel("TOP"));
    	//パネルに部品追加
    	add(new JLabel("DVDコード"));
    	add(codeField);
    	add(new JLabel("タイトル"));
    	add(titleField);
    	add(registerBtn);
    	add(backBtn);
    }
}
