package com.cm.mybet;

import com.google.common.base.MoreObjects;

/**
 * Class representing a Bet.
 * 
 * @author mcristian
 */
public class Bet {
	private String betId;
	private long betTimeStamp;
	private long selectionId;
	private String selectionName;
	private double stake;
	private double price;
	private String currency;

	public Bet() {
	}

	public Bet(String betId, long betTimeStamp, long selectionId, String selectionName, double stake, double price, String currency) {
		super();
		this.betId = betId;
		this.betTimeStamp = betTimeStamp;
		this.selectionId = selectionId;
		this.selectionName = selectionName;
		this.stake = stake;
		this.price = price;
		this.currency = currency;
	}

	public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public long getBetTimeStamp() {
		return betTimeStamp;
	}

	public void setBetTimeStamp(long betTimeStamp) {
		this.betTimeStamp = betTimeStamp;
	}

	public long getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(long selectionId) {
		this.selectionId = selectionId;
	}

	public String getSelectionName() {
		return selectionName;
	}

	public void setSelectionName(String selectionName) {
		this.selectionName = selectionName;
	}

	public double getStake() {
		return stake;
	}

	public void setStake(double stake) {
		this.stake = stake;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("betId", betId).add("betTimeStamp", betTimeStamp).add("selectionId", selectionId).add("selectionName", selectionName).add("stake", stake).add("price", price).add("currency", currency).toString();
	}
}
