package DVD_;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReturnPanel extends JPanel{
	public ReturnPanel(MainFrame frame) {
    //ボタンとか作成
	setLayout (new GridLayout(2, 2));
	JTextField codeField = new JTextField(); 
	JButton returnBtn=new JButton("返却");
	JButton backBtn=new JButton("TOPへ戻る");
	//DB返却処理
	returnBtn.addActionListener(e -> {
    	DB.returnDVD(codeField.getText());
    	JOptionPane.showMessageDialog(this, "返却しました。");
    	});
	//TOPに戻るボタン
	backBtn.addActionListener(e -> {
        DB.getLentDVDs();  // ← この行がDB更新（ListPanelでの再取得に対応）
        frame.showPanel("TOP");
    });
	//パネルに部品の追加
	add(new JLabel("DVDコード"));
	add(codeField);
	add(returnBtn); //返却
	add(backBtn); //topへ戻る
}
}
