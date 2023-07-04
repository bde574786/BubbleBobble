package bubblebobble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame {

	private GameFrame gameFrame;
	private Player player;
	private JLabel background;
	
	public GameFrame() {
		initObject();
		initSetting();
		setVisible(true);
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initObject() {
		background = new JLabel(new ImageIcon("image/background.png"));
		setContentPane(background);
		
		player = new Player();
		add(player);
	}
	
	private void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.left();
					break;
				}
			}
		});
	}
	
	
	public static void main(String[] args) {
		new GameFrame();
	}
	
}
