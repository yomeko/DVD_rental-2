package DVD_;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LendPanel extends JPanel {
	public LendPanel(MainFrame frame) {
        //ボタンとかフィールド作成
    	setLayout (new GridLayout(3, 2));
    	JTextField idField = new JTextField();
    	JTextField CodeField = new JTextField();
    	JButton lendBtn=new JButton("貸出");
    	JButton backBtn=new JButton("TOPへ戻る");
    	//DB実行
    	lendBtn.addActionListener(e -> {
        	DB.lendDVD(idField.getText(),( CodeField.getText()));
        	JOptionPane.showMessageDialog(this, "貸出しました。");
        	});
        //TOP戻る
    	backBtn.addActionListener(e -> frame.showPanel("TOP"));
        //部品追加
    	add(new JLabel("会員ID"));
    	add(idField);
    	add(new JLabel("DVDコード"));
    	add(CodeField);
    	add(lendBtn);
    	add(backBtn);
    }
}
