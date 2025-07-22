package DVD_;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MenberPanel {
    public MenberPanel(MainFrame frame) {
        //レイアウトに新しいGridLayout(3, 2)
    	setLayout(new GridLayout(3,2));
        //IDのテキストフィールド
    	JTextField idField =new JTextField();
    	//名前のテキストフィールド
    	JTextField nameField =new JTextField();
    	//登録ボタン
    	JButton registerBtn  = new JButton("登録");
        //TOPに戻るボタン
    	JButton backBtn =new JButton("TOPへ戻る");
    	//登録ボタンリスナーで押されたらDBのインサート発動
    	registerBtn.addActionListener(e -> {
    		DB.insertMember(idField.getText(),nameField.getText());
            JOptionPane.showMessageDialog(this, "会員登録が完了しました。");
        });
        
        //TOPに戻るボタン
    	backBtn.addActionListener(e -> frame.showPanel("TOP"));
        //カードに部品を追加
    	add(new JLabel("会員ID"));
    	add(idField);
    	add(new JLabel("氏名"));
    	add(nameField);
    	add(registerBtn);
    	add(backBtn);
    }
}
