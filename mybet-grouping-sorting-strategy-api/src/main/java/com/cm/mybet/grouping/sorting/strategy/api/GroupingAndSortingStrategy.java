package com.cm.mybet.grouping.sorting.strategy.api;

import java.util.List;

import com.cm.mybet.Bet;
import com.cm.mybet.BetReport;

/**
 * <p>
 * All defined Grouping and Sorting Strategies must implement this interface.
 * </p>
 *
 * @author mcristian
 * @since 1.0
 */
public interface GroupingAndSortingStrategy {

    void init(List<Bet> bets, double exchageRate);
    List<BetReport> execute();
}
