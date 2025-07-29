package DVD_;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LendPanel extends JPanel {
    public LendPanel(MainFrame frame) {
        setLayout(new GridLayout(3, 2));
        JTextField idField = new JTextField();
        JTextField CodeField = new JTextField();
        JButton lendBtn = new JButton("貸出");
        JButton backBtn = new JButton("TOPへ戻る");

        // 貸出ボタンの処理
        lendBtn.addActionListener(e -> {
            String dvdCode = CodeField.getText();
            String memberId = idField.getText();

            // すでに貸出中かチェック
            if (DB.isLentOut(dvdCode)) {
                JOptionPane.showMessageDialog(this, "このDVDはすでに貸出中です。", "エラー", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 貸出処理
            DB.lendDVD(memberId, dvdCode);
            JOptionPane.showMessageDialog(this, "貸出しました。");
        });

        // TOP戻る時にDB更新（→更新ボタンがある画面で反映される）
        backBtn.addActionListener(e -> {
            DB.getLentDVDs();  // ← 再取得
            frame.showPanel("TOP");
        });

        // 部品追加
        add(new JLabel("会員ID"));
        add(idField);
        add(new JLabel("DVDコード"));
        add(CodeField);
        add(lendBtn);
        add(backBtn);
    }
}