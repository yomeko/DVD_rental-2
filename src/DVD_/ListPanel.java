package DVD_;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListPanel extends JPanel {
    private JTextArea area;

    public ListPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);

        // 最初の表示
        updateList();

        JButton updateBtn = new JButton("更新");
        updateBtn.addActionListener(e -> updateList());

        JButton TOPbtn = new JButton("TOPに戻る");
        TOPbtn.addActionListener(e -> frame.showPanel("TOP"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(updateBtn);
        bottomPanel.add(TOPbtn);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // DBから最新の貸出中DVDリストを取得して表示を更新
    public void updateList() {
        area.setText(""); // 表示クリア
        
        List<String> DVDs = DB.getLentDVDs();
        for (String dvd : DVDs) {
            area.append(dvd + "\n");
        }
    }
}