class F1Car extends Thread {
	int carNo; // 賽車編號
	int distance; // 比賽公里數
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
			int move = (int)(Math.random() * 30); // 每次最多前進 30 公里
			distance -= move;
			System.out.println("第" + carNo + "車前進 " + move + "幾公里(距終點還有" + distance + "公里)");
			if(distance <= 0)
				gameOver = true;
		}
		System.out.println("第" + carNo + "號賽車抵達終點");
	}
}
	
public class Pgm1503 {
	public static void main(String[] argv) {
		int numOfCars = 2; // 賽車數
		int distance = 50; // 公里數
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

	