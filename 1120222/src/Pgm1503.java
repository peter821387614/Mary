class F1Car extends Thread {
	int carNo; // �ɨ��s��
	int distance; // ���ɤ�����
	boolean gameOver = false;
	
	public F1Car(int carNo,int distance) {
		this.carNo = carNo;
		this.distance = distance;
	}
	
	public void run() {
		while(!gameOver) {
			try {
				sleep(1000);
			}
			catch(InterruptedException e) {}
			int move = (int)(Math.random() * 30); // �C���̦h�e�i 30 ����
			distance -= move;
			System.out.println("��" + carNo + "���e�i " + move + "�X����(�Z���I�٦�" + distance + "����)");
			if(distance <= 0)
				gameOver = true;
		}
		System.out.println("��" + carNo + "���ɨ���F���I");
	}
}
	
public class Pgm1503 {
	public static void main(String[] argv) {
		int numOfCars = 2; // �ɨ���
		int distance = 50; // ������
		F1Car[] cars = new F1Car[numOfCars];
		
		for(int i = 0;i < numOfCars;i++)
			cars[i] = new F1Car(i,distance);

		for(int i = 0;i < numOfCars;i++)
			cars[i].start();
			
		for(int i = 0;i < numOfCars;i++)
			try {
				cars[i].join();
			}
			catch(InterruptedException e) {}
	}
}

	