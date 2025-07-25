package DVD_;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DVDPanel extends JPanel{

	public DVDPanel(MainFrame frame) {
        //レイアウトに新しいGridLayout(3, 2)
    	setLayout(new GridLayout(3,2));
        //ボタンやフィールド作成
    	JTextField codeField =new JTextField();
    	JTextField titleField = new JTextField();  
    	JButton registerBtn  = new JButton("登録");
    	JButton backBtn =new JButton("TOPへ戻る");
    	
    	registerBtn.addActionListener(e -> {
    	    String code = codeField.getText().trim();
    	    String title = titleField.getText().trim();

    	    if (code.isEmpty() || title.isEmpty()) {
    	        JOptionPane.showMessageDialog(this, "コードとタイトルの両方を入力してください", "エラー", JOptionPane.ERROR_MESSAGE);
    	        return;
    	    }

    	    if (!code.matches("\\d+")) {
    	        JOptionPane.showMessageDialog(this, "コードは数字のみで入力してください", "入力エラー", JOptionPane.ERROR_MESSAGE);
    	        return;
    	    }

    	    DB.insertDVD(code, title);
    	    JOptionPane.showMessageDialog(this, "登録しました");
    	    codeField.setText("");
    	    titleField.setText("");
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
