package model;

import java.util.List;
import java.util.function.Function;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class Model {

	private static final int MAX_LENGTH = 11;
	private static Function<String, Double> toDouble = Double::parseDouble;

	public Model() {
		new Model();
	}

	public static String makeItSuitable(String num) {
		if (num.length() <= MAX_LENGTH) {
			if (num.charAt(num.length() - 1) == '0')
				num = num.substring(0, num.length() - 2);
			return num;
		} else {
			for (int i = num.length() - MAX_LENGTH; i > 0; i--)
				num = num.substring(0, num.length() - 1);
			return num;
		}
	}

	public static int factorial(int num) {
		if (num == 0 || num == 1)
			return 1;
		else
			return num * factorial(num - 1);
	}

	public static double calculate(String num1S, String num2S, String op) {
		JShell jshell = JShell.create();
		StringBuilder sb = new StringBuilder();
		double num1D = toDouble.apply(num1S);
		double num2D = toDouble.apply(num2S);
		if (op.equals("^x"))
			sb.append("Math.pow(" + num1D + ", " + num2D + ")");
		else
			sb.append(num1D + op + num2D);
		String toJShell = sb.toString();

		try (jshell) {
			List<SnippetEvent> events = jshell.eval(toJShell);
			for (SnippetEvent e : events) {
				if (e.causeSnippet() == null) {
					switch (e.status()) {
					case VALID:
						if (e.value() != null && Double.isFinite(toDouble.apply(e.value()))) {
							System.out.printf("%s = %s\n", toJShell, e.value());
							return toDouble.apply(e.value());
						}
						else
							throw new IllegalArgumentException("Illegal operation!");
					default:
						System.out.printf("Error\n");
						throw new IllegalArgumentException("Illegal operation!");
					}
				}
			}
		}
		return 0;
	}

	public static double calculate(String numS, String op) {
		JShell jshell = JShell.create();
		StringBuilder sb = new StringBuilder();
		double numD = toDouble.apply(numS);
		if (op.equals("fact")) {
			if ((int) numD != numD)
				throw new IllegalArgumentException("Illegal operation!");
			if( numD > 10 )
				throw new IllegalArgumentException("Too big number!");
			String fact = "public static int factorial(int num) {\r\n if (num == 0 || num == 1)\r\n return 1;\r\n else\r\n return num * factorial(num - 1);\r\n }";
			jshell.eval(fact);
			sb.append("factorial(" + (int) numD + ")");
		} else {
			sb.append("Math.sqrt(" + numD + ")");
		}

		String toJShell = sb.toString();
		try (jshell) {
			List<SnippetEvent> events = jshell.eval(toJShell);
			for (SnippetEvent e : events) {
				if (e.causeSnippet() == null) {
					switch (e.status()) {
					case VALID:
						if (e.value() != null && Double.isFinite(toDouble.apply(e.value()))) {
							System.out.printf("%s = %s\n", toJShell, e.value());
							return toDouble.apply(e.value());
						} else
							throw new IllegalArgumentException("Illegal operation!");
					default:
						System.out.printf("Error\n");
						throw new IllegalArgumentException("Illegal operation!");
					}
				}
			}
		}
		return 0;
	}

}