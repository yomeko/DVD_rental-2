package DVD_;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // ListPanel を参照するための変数
    private ListPanel listPanel;

    public MainFrame() {
        setTitle("DVDレンタルシステム");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // すべての機能パネルを登録
        cardPanel.add(new TOPPanel(this), "TOP");
        cardPanel.add(new DVDPanel(this), "DVD");
        cardPanel.add(new MenberPanel(this), "MENBER");
        cardPanel.add(new LendPanel(this), "LEND");
        cardPanel.add(new ReturnPanel(this), "RETURN");

        // ListPanelのインスタンスを変数に格納
        listPanel = new ListPanel(this);
        cardPanel.add(listPanel, "LIST");

        add(cardPanel);
        setVisible(true);
    }

    // パネルを切り替えるメソッド
    public void showPanel(String name) {
        // 「LIST」パネルに切り替える前に一覧の更新をかける
        if (name.equals("LIST")) {
            listPanel.updateList(); // ←ここでDBから最新のデータを取得して表示を更新
        }
        cardLayout.show(cardPanel, name);
    }
}