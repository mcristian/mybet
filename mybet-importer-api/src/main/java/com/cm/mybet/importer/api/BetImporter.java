package com.cm.mybet.importer.api;

import java.util.List;

import com.cm.mybet.Bet;

public interface BetImporter {
	
	void init();
	List<Bet> getBets();
}
