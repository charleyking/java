package club.charleyking.thread;

public class BankThreadTest {
	public static void main(String[] args) {
		Bank aBank = new Bank();
		aBank.setBalance(23);
		AddMoneyThread a = new AddMoneyThread(aBank, 1000);
		Thread aThread = new Thread(a);
		aThread.start();
		System.out.println(aBank.getBalance());
	}
}
