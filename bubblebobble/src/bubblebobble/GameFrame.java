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
		initListener();
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
					if(!player.isLeft()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(!player.isRight()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
						if(!player.isUp() && !player.isDown()) {
							System.out.println(" up키 누름");
							player.up();
					}
					break;
				case KeyEvent.VK_DOWN:
					player.down();
					break;
				}
			}
			
		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				player.setLeft(false);
				break;
			case KeyEvent.VK_RIGHT:
				player.setRight(false);
				break;
			case KeyEvent.VK_UP:
				player.setUp(false);
				break;
			case KeyEvent.VK_DOWN:
				player.setDown(false);
				break;
			}
		}
		});
	
		
	}
	
	
	public static void main(String[] args) {
		new GameFrame();
	}
	
}
