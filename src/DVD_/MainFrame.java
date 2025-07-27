package DVD_;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame{
	   private CardLayout cardLayout;
	    private JPanel cardPanel;

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
	        cardPanel.add(new ListPanel(this), "LIST");

	        add(cardPanel);
	        setVisible(true);
	    }

	    public void showPanel(String name) {
	        cardLayout.show(cardPanel, name);
	    }
}