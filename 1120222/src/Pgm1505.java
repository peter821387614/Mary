
class SushiMaster extends Thread { // 壽司師傅
	public boolean stop = false; // 食客吃飽了，停止做壽司
	public SushiEater eater; // 壽司食客
	public void run() {
		while(!stop) {
			stop = eater.putSushi();
		}
	}
}

class SushiEater extends Thread { // 壽司食客
	boolean sushiReady = false; // 壽司師傅做好了嗎？
	int taken = 0; // 吃幾份了？
	int notHungry; // 吃幾份才會飽？
	
	public SushiEater(int notHungry) {
		this.notHungry =  notHungry;
	}
		
	public synchronized boolean putSushi() { // 壽司師傅放壽司
		while(sushiReady) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		if(taken >= notHungry)
			return true; // 已經吃飽，可以停了
		sushiReady = true;
		System.out.println("壽司好了");
		notify(); // 通知食客可以進食了
		return false; // 還沒吃飽，請師傅繼續做
	}
	
	public synchronized void eatSushi() { // 食客吃壽司
		while(!sushiReady) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		sushiReady = false;
		taken++;
		System.out.println("吃第 " + taken + "份壽司了");
		notify(); // 通知壽司師傅可以做新的壽司了
	}
	
	public void run() {
		while(taken < notHungry) { // 吃 10 盤就吃飽了
			eatSushi();
		}
		System.out.println("喔，吃飽了！");
	}
}

public class Pgm1505 {
	public static void main(String[] argv) {
		SushiMaster master = new SushiMaster();
		SushiEater eater = new SushiEater(10);
		master.eater = eater;
		master.start();
		eater.start();
		try {
			master.join(); // 等師傅停止做壽司
			eater.join(); // 等食客吃飽
		}
		catch(InterruptedException e) {}
	}
}