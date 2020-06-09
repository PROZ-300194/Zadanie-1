package expression;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Expression{
	private String firstNum;
	private String op;
	private String secondNum;
	
	public Expression() {}
	
	public Expression(String firstNum, String op, String secondNum) {
		this.firstNum = firstNum;
		this.secondNum = secondNum;
		this.op = op;
	}
	
	public Expression(String firstNum, String op) {
		this.firstNum = firstNum;
		this.op = op;
	}
	
	public String getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(String firstNum) {
		this.firstNum = firstNum;
	}
	public String getSecondNum() {
		return secondNum;
	}
	public void setSecondNum(String secondNum) {
		this.secondNum = secondNum;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	
}