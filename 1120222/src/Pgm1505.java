
class SushiMaster extends Thread { // �إq�v��
	public boolean stop = false; // ���ȦY���F�A����إq
	public SushiEater eater; // �إq����
	public void run() {
		while(!stop) {
			stop = eater.putSushi();
		}
	}
}

class SushiEater extends Thread { // �إq����
	boolean sushiReady = false; // �إq�v�Ű��n�F�ܡH
	int taken = 0; // �Y�X���F�H
	int notHungry; // �Y�X���~�|���H
	
	public SushiEater(int notHungry) {
		this.notHungry =  notHungry;
	}
		
	public synchronized boolean putSushi() { // �إq�v�ũ�إq
		while(sushiReady) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		if(taken >= notHungry)
			return true; // �w�g�Y���A�i�H���F
		sushiReady = true;
		System.out.println("�إq�n�F");
		notify(); // �q�����ȥi�H�i���F
		return false; // �٨S�Y���A�Юv���~��
	}
	
	public synchronized void eatSushi() { // ���ȦY�إq
		while(!sushiReady) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		sushiReady = false;
		taken++;
		System.out.println("�Y�� " + taken + "���إq�F");
		notify(); // �q���إq�v�ťi�H���s���إq�F
	}
	
	public void run() {
		while(taken < notHungry) { // �Y 10 �L�N�Y���F
			eatSushi();
		}
		System.out.println("��A�Y���F�I");
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
			master.join(); // ���v�Ű���إq
			eater.join(); // �����ȦY��
		}
		catch(InterruptedException e) {}
	}
}