package DVD_;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReturnPanel extends JPanel{
	public ReturnPanel(MainFrame frame) {
		CardLayout cardLayout = new CardLayout();
		frame.add(frame, "main");
    //ボタンとか作成
	setLayout (new GridLayout(2, 2));
	JTextField codeField = new JTextField(); 
	JButton button1=new JButton("返却");
	JButton button2=new JButton("TOPへ戻る");
	//DB返却処理
	button1.addActionListener(e -> {
    	DB.returnDVD(codeField.getText());
    	JOptionPane.showMessageDialog(this, "返却しました。");
    	});
	//TOPに戻るボタン
	button2.addActionListener(e -> cardLayout.show(frame, "main"));
	//パネルに部品の追加
	add(new JLabel("DVDコード"));
	add(codeField);
	add(button1); //返却
	add(button2); //topへ戻る
}
}
