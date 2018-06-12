package com.cm.mybet.reporting.api;

import java.util.List;

import com.cm.mybet.BetReport;

public interface Reporting {
	
	void init(List<BetReport> bets);
	void execute();
}
