package expression;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {

	private double res;
	
	public Result() {}
	
	public Result(double res) {
		this.res = res;
	}
	
	public double getRes() {
		return res;
	}

	public void setRes(double res) {
		this.res = res;
	}
	
}