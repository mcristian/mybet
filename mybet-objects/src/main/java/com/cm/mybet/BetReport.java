package com.cm.mybet;

import com.google.common.base.MoreObjects;

/**
 * Class representing a bet report model.
 * 
 * @author mcristian
 *
 */
public class BetReport {

	private String selectionName;
	private String currency;
	private int noOfBets;
	private double totalStakes;
	private double totalPayout;
	private Double exRate = 1.14;

	public BetReport() {

	}

	public BetReport(String selectionName, String currency, int noOfBets, double totalStakes, double totalPayout, Double exRate) {
		super();
		this.selectionName = selectionName;
		this.currency = currency;
		this.noOfBets = noOfBets;
		this.totalStakes = totalStakes;
		this.totalPayout = totalPayout;
		this.exRate = exRate;
	}

	/**
	 * @return the selectionName
	 */
	public String getSelectionName() {
		return selectionName;
	}

	/**
	 * @param selectionName
	 *            the selectionName to set
	 */
	public void setSelectionName(String selectionName) {
		this.selectionName = selectionName;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the noOfBets
	 */
	public int getNoOfBets() {
		return noOfBets;
	}

	/**
	 * @param noOfBets
	 *            the noOfBets to set
	 */
	public void setNoOfBets(int noOfBets) {
		this.noOfBets = noOfBets;
	}

	/**
	 * @return the totalStakes
	 */
	public double getTotalStakes() {
		return totalStakes;
	}

	/**
	 * @param totalStakes
	 *            the totalStakes to set
	 */
	public void setTotalStakes(double totalStakes) {
		this.totalStakes = totalStakes;
	}

	/**
	 * @return the exRate
	 */
	public Double getExRate() {
		return exRate;
	}

	/**
	 * @param exRate
	 *            the exRate to set
	 */
	public void setExRate(Double exRate) {
		this.exRate = exRate;
	}

	/**
	 * @return the totalPayout
	 */
	public double getTotalPayout() {
		return totalPayout;
	}

	/**
	 * @param totalPayout
	 *            the totalPayout to set
	 */
	public void setTotalPayout(double totalPayout) {
		this.totalPayout = totalPayout;
	}

	public Double getTotalPayoutInGBP() {
		if ("EUR".equalsIgnoreCase(currency)) {
			return totalPayout / exRate;
		}
		return totalPayout;
	}

	public String formatCurrency(double amount) {
		if ("EUR".equalsIgnoreCase(currency)) {
			return String.format("\u20AC%,.2f", amount);
		}
		return String.format("\u00A3%,.2f", amount);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("selectionName", selectionName).add("currency", currency).add("noOfBets", noOfBets).add("totalStakes", totalStakes).add("totalPayout", totalPayout).toString();
	}

}
