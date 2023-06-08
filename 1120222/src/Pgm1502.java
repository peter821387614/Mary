import java.io.*;

class Player extends Thread {
	public static int STONE = 0; // 石頭
	public static int PAPER = 1; // 布
	public static int CUTTER = 2; // 剪刀
	public static int UNKNOWN = -1; // 還未出拳
	public static String[] names = {"石頭","布","剪刀"};
	public static final Pgm1502 game = new Pgm1502();
	public static boolean gameOver = false;
		
	public int current = UNKNOWN; // 目前出拳，-1表示還未出拳
	public int timesWon = 0; // 贏的次數
	public String playerName; // 玩家名稱
	
	public Player(String playerName) {
		this.playerName = playerName;
	}
	
	public void run() {
		while(!gameOver) {
			while(current != UNKNOWN)
			{
				synchronized(this) {
					try {
						wait();
					}
					catch(InterruptedException e) {}
				}
			}
			if(gameOver)
				break;
			current = (int)(Math.random() * 3); // 出拳
			System.out.println(playerName + "出" + names[current]);
			synchronized(this) {
				notify();
			}
		}
	}
}
	
public class Pgm1502 {
	public static void main(String[] argv) {
		Player.game.play();
	}
	
	public void play() {
		Player p1 = new Player("甲");
		Player p2 = new Player("乙");
		p1.start();
		p2.start();
		
		while(!Player.gameOver) {
			while(p1.current == Player.UNKNOWN) {
				synchronized(p1) {
					try { // 等甲出拳
						p1.wait();
					}
					catch(InterruptedException e) {}
				}
			}
			while(p2.current == Player.UNKNOWN) {
				synchronized(p2) {
					try { // 等乙出拳
						p2.wait();
					}
					catch(InterruptedException e) {}
				}
			}
			if(p1.current == Player.CUTTER && p2.current == Player.STONE) {
				System.out.println(p2.playerName + "贏");
				p2.timesWon++;
			}
			else if(p2.current == Player.CUTTER && p1.current == Player.STONE) {
				System.out.println(p1.playerName + "贏");
				p1.timesWon++;
			}
			else if(p1.current > p2.current) {
				System.out.println(p1.playerName + "贏");
				p1.timesWon++;
			}
			else if(p2.current > p1.current) {
				System.out.println(p2.playerName + "贏");
				p2.timesWon++;
			}
			System.out.println("目前" + p1.playerName + "贏" + p1.timesWon + "次, " +
				p2.playerName + "贏" + p2.timesWon + "次");
			if(p1.timesWon > 5 || p2.timesWon > 5)
				Player.gameOver = true;
			p1.current = p2.current = Player.UNKNOWN;
			synchronized(p1) {
				p1.notify();
			}
			synchronized(p2) {
				p2.notify();
			}
		}
	}
}