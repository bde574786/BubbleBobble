package bubblebobble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	private Player player;
	private BackgroundBubble backgroundBubble;
	
	private int x;
	private int y;

	private boolean left;
	private boolean right;
	private boolean up;

	private int state;

	private ImageIcon bubble;
	private ImageIcon bubbled;
	private ImageIcon bomb;

	private boolean leftWallCrash;
	private boolean rightWallCrash;

	public Bubble(Player player) {
		this.player = player;
		initObject();
		initSetting();
		initThread();
	}

	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		
		backgroundBubble = new BackgroundBubble(this);
	}

	private void initSetting() {
		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);

		state = 0;
	}

	private void initThread() {
		new Thread(() -> {
			if (player.getPlayerDirection() == PlayerDirection.LEFT) {
				left();
			} else {
				right();
			}
		}).start();
	}

	@Override
	public void left() {
		left = true;
		for(int i=0; i < 400; i++) {
			x--;
			setLocation(x, y);
			
			if(backgroundBubble.leftWall()) {
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for(int i=0; i < 400; i++) {
			x++;
			setLocation(x, y);
			
			if(backgroundBubble.rightWall()) {
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while(up) {
			y--;
			setLocation(x, y);
			
			if(backgroundBubble.topWall()) {
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
