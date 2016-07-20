package club.charleyking.thread;

public class AddMoneyThread implements Runnable {
	private Bank bank;
	private double money;
	
	public AddMoneyThread(Bank bank, double money) {
		this.bank = bank;
		this.money = money;
	}
	
	@Override 
	public void run() {
		bank.deposit(money);
	}
}
