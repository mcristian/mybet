package com.cm.mybet.importer.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cm.mybet.Bet;
import com.cm.mybet.importer.api.BetImporter;
import com.cm.mybet.repository.BetRepository;

@Component("betImporter")
public class BetImporterImpl implements BetImporter {
		
	@Autowired
	private BetRepository betRepository;
	
	@Autowired
	public BetImporterImpl()
	{
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		// maybe add the delimiter, header info, etc
	}

	@Override
	public List<Bet> getBets() {
		return betRepository.findAllBets();
	}
}
