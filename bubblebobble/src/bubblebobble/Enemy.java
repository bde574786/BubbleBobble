package bubblebobble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

	private GameFrame mContext;

	private ImageIcon enemyR, enemyL;

	private int x;
	private int y;

	private EnemyDirection enemyDirection;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private int state;
	
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	public Enemy(GameFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundEnemyService();
	}

	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
	}

	private void initSetting() {
		x = 480;
		y = 178;

		left = false;
		right = false;
		up = false;
		down = false;

		state = 0;
		
		leftWallCrash = false;
		rightWallCrash = false;

		enemyDirection = EnemyDirection.RIGHT;

		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);
	}

	private void initBackgroundEnemyService() {
	}

	@Override
	public void left() {
		enemyDirection = EnemyDirection.LEFT;
		left = true;
		new Thread(() -> {
			while (left) {
				setIcon(enemyL);
				try {
					x = x - 1;
					setLocation(x, y);
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void right() {
		enemyDirection = EnemyDirection.RIGHT;
		right = true;
		new Thread(() -> {
			while (right) {
				try {
					setIcon(enemyR);
					x = x + 1;
					setLocation(x, y);
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void up() {
		up = true;
		new Thread(() -> {
			synchronized (this) {
				for (int i = 0; i < 130; i++) {
					try {
						y = y - 1;
						setLocation(x, y);
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				up = false;
			}
			down();
		}).start();
	}

	@Override
	public void down() {
		down = true;
		new Thread(() -> {
			while (down) {
				try {
					y = y + 1;
					setLocation(x, y);
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}

}
