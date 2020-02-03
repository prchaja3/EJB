package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class AddEjb
 */
@Stateless
@LocalBean
public class AddEjb {

	private int i,j,k;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	
	public void secti() {
		k = i+j;
	}
}
