package bubblebobble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	private GameFrame mContext;
	private Player player;
	private Enemy enemy;
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

	public Bubble(GameFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
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

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);

			if (backgroundBubble.leftWall()) {
				left = false;
				break;
			}

			if ((Math.abs(x - enemy.getX())  < 10) && Math.abs(y - enemy.getY()) > 0
					&& Math.abs(y - enemy.getY()) < 50) {
				if (enemy.getState() == 0) {
					attack();
					break;
				}
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
		for (int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);

			if (backgroundBubble.rightWall()) {
				right = false;
				break;
			}

			if ((Math.abs(x - enemy.getX())  < 10) && Math.abs(y - enemy.getY()) > 0
					&& Math.abs(y - enemy.getY()) < 50) {
				if (enemy.getState() == 0) {
					attack();
					break;
				}
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
		while (up) {
			y--;
			setLocation(x, y);

			if (backgroundBubble.topWall()) {
				up = false;
				break;
			}

			try {
				if (state == 0) {
					Thread.sleep(1);
				} else {
					Thread.sleep(6);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(state == 0) {
			clearBubble();
		}
	}

	@Override
	public void attack() {
		state = 1;
		enemy.setState(1);
		setIcon(bubbled);
		mContext.remove(enemy);
		mContext.repaint();
	}

	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this);
			mContext.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
