package DVD_;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListPanel extends JPanel {
    private JTextArea area;  // クラス変数にして再利用可能に

    public ListPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);

        // 最初の表示
        updateList();

        // ボタン
        JButton updateBtn = new JButton("更新");
        updateBtn.addActionListener(e -> updateList());

        JButton TOPbtn = new JButton("TOPに戻る");
        TOPbtn.addActionListener(e -> frame.showPanel("TOP"));

        // 下部パネルにボタンを並べて追加
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(updateBtn);
        bottomPanel.add(TOPbtn);

        // パネルに追加
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // DBから再取得してTextAreaに反映
    private void updateList() {
        area.setText(""); // 一度クリア
        List<String> DVDs = DB.getLentDVDs();
        for (String DVD : DVDs) {
            area.append(DVD + "\n");
        }
    }
}