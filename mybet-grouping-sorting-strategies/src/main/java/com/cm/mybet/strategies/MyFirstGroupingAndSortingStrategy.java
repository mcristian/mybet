package com.cm.mybet.strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cm.mybet.Bet;
import com.cm.mybet.BetReport;
import com.cm.mybet.grouping.sorting.strategy.api.GroupingAndSortingStrategy;

import de.vandermeer.asciitable.AsciiTable;

@Component("myFirstGroupingAndSortingStrategy")
public class MyFirstGroupingAndSortingStrategy implements GroupingAndSortingStrategy {

	private static final Logger LOG = LogManager.getLogger();

	private List<Bet> bets;
	private double exchangeRate;

	@Override
	public void init(List<Bet> bets, double exchageRate) {
		this.bets = bets;
		this.exchangeRate = exchageRate;
	}

	@Override
	public List<BetReport> execute() {
		// grouping by selection name and currency
		Function<Bet, List<Object>> groupedBy = bet -> Arrays.<Object>asList(bet.getSelectionName(), bet.getCurrency());
		Stream<Bet> myBets = Stream.of(bets.toArray(new Bet[] {}));
		Map<Object, List<Bet>> map = myBets.collect(Collectors.groupingBy(groupedBy, Collectors.toList()));
		// System.out.println(map);

		// building the bet report model
		List<BetReport> betAggr = new ArrayList<>();
		for (Map.Entry<Object, List<Bet>> entry : map.entrySet()) {
			// System.out.println(entry.getKey());
			BetReport betRow = new BetReport();
			betRow.setSelectionName(((List<Object>) entry.getKey()).get(0).toString());
			betRow.setCurrency(((List<Object>) entry.getKey()).get(1).toString());
			for (Bet b : entry.getValue()) {
				betRow.setNoOfBets(betRow.getNoOfBets() + 1);
				betRow.setTotalStakes(betRow.getTotalStakes() + b.getStake());
				betRow.setTotalPayout(betRow.getTotalPayout() + (b.getStake() * b.getPrice()));
				// System.out.println(b);
			}
			betAggr.add(betRow);
		}

		// sort by total payout descending
		betAggr.sort(Comparator.comparing(BetReport::getTotalPayoutInGBP).reversed());
		
		return betAggr;
	}

}
