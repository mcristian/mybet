package com.cm.mybet.repository;

import java.util.List;

import com.cm.mybet.Bet;

/**
 * The Bets configuration repository.
 *
 * @author cmilasan
 */
public interface BetRepository {

	List<Bet> findAllBets();
}
