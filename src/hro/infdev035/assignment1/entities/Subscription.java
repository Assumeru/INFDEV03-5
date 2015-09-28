package hro.infdev035.assignment1.entities;

public class Subscription {
	private int months;
	private double price;

	public Subscription(int months, double price) {
		this.months = months;
		this.price = price;
	}

	@Override
	public String toString() {
		return months+" months for €"+price;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
