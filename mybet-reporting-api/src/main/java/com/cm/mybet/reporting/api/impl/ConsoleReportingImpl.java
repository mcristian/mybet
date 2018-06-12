package com.cm.mybet.reporting.api.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cm.mybet.BetReport;
import com.cm.mybet.reporting.api.Reporting;

import de.vandermeer.asciitable.AsciiTable;

@Component("consoleReporting")
public class ConsoleReportingImpl implements Reporting {

	List<BetReport> bets;

	@Override
	public void init(List<BetReport> bets) {
		this.bets = bets;
	}

	@Override
	public void execute() {

		// create the table
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow(Arrays.asList("Selection Name", "Currency", " No Of Bets", " Total Stakes", " Total Payout"));
		for (BetReport betRow : bets) {
			at.addRule();
			at.addRow(betRow.getSelectionName(), betRow.getCurrency(), betRow.getNoOfBets(), betRow.formatCurrency(betRow.getTotalStakes()), betRow.formatCurrency(betRow.getTotalPayout()));
		}
		at.addRule();
		System.out.println(at.render());
	}
}
