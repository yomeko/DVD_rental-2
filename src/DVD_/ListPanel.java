package DVD_;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListPanel extends JFrame{
    public ListPanel(MainFrame frame) {
        //レイアウト追加
        setLayout(new BorderLayout());

        //表示フィールド追加
        JTextArea area = new JTextArea();
        JButton TOPbtn = new JButton("TOPに戻る");

        //Listを作成string型
        area.setEditable(false);
        List<String> books = DB.getLentDVDs();
        for(String book : books) {
        	area.append(book + "\n");
        }

        //TOP戻るボタン
        TOPbtn.addActionListener(e -> frame.showPanel("TOP"));

        //パネルに部品の追加
        add(new JScrollPane(area),BorderLayout.CENTER);
        add(TOPbtn,BorderLayout.SOUTH);
}
