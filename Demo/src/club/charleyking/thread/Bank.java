package club.charleyking.thread;

public class Bank {
	private double balance;
	public void deposit(double money){
		double newBalance = balance + money;
		balance = newBalance;
	}
	public void setBalance(double money) {
		balance = money;
	}
	public double getBalance(){
		return balance;
	}
}
