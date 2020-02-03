package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Bank
 */
@Stateful(mappedName="Bank")
@LocalBean
public class Bank implements BankRemote {

	private int amount = 0;
	
	@Override
	public boolean withdraw(int amount) {
		if (amount <= this.amount) {
			this.amount -= amount;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void deposit(int amount) {
		this.amount += amount;
		
	}

	@Override
	public int getBalance() {
		return amount;
	}

}
