package com.cm.mybet.core.engine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.cm.mybet.Bet;
import com.cm.mybet.core.util.ConfigurableComponentFactory;
import com.cm.mybet.grouping.sorting.strategy.api.GroupingAndSortingStrategy;
import com.cm.mybet.objects.grouping.sorting.strategy.GroupingAndSortingStrategyConfig;
import com.cm.mybet.reporting.api.Reporting;
import com.cm.mybet.repository.BetRepository;
import com.cm.mybet.services.GroupingAndSortingStrategyConfigService;

@Component
@ComponentScan(basePackages = { "com.cm.mybet" })
public class MyBetEngine {

	private static final Logger LOG = LogManager.getLogger();

	private final Map<String, GroupingAndSortingStrategyConfig> strategyDescriptions = new HashMap<>();
	private BetRepository betRepository;
	private GroupingAndSortingStrategy groupingAndSortingStrategyImpl;
	private Reporting reporting;

	// Services
	private final GroupingAndSortingStrategyConfigService strategyConfigService;

	@Autowired
	private ApplicationContext springContext;

	@Autowired
	public MyBetEngine(BetRepository betRepository, GroupingAndSortingStrategyConfigService strategyConfigService, Reporting reporting) {

		LOG.info(() -> "Initialising My Bet Engine...");
		this.betRepository = betRepository;
		this.strategyConfigService = strategyConfigService;
		this.reporting = reporting;
	}

	public void start() throws IllegalStateException {

		initConfig();
		reporting.init(groupingAndSortingStrategyImpl.execute());
		reporting.execute();
	}

	private void initConfig() {

		LOG.info(() -> "Initialising CSV Importer config...");
//		initiliseBetImporter();
		
		loadGroupingAndReportingStrategyConfig();
		initialiseGroupingAndReportingStrategies();
	}

	private void loadGroupingAndReportingStrategyConfig() {

		final List<GroupingAndSortingStrategyConfig> strategies = strategyConfigService.getAllGroupingAndSortingStrategyConfig();
		LOG.debug(() -> "Fetched Grouping and Sorting Strategy config from repository: " + strategies);

		for (final GroupingAndSortingStrategyConfig strategy : strategies) {
			strategyDescriptions.put(strategy.getId(), strategy);
		}
	}

	private void initialiseGroupingAndReportingStrategies() {
		// assumed that the first Grouping and Sorting strategy is wanted
		groupingAndSortingStrategyImpl = obtainTradingStrategyInstance(strategyDescriptions.values().iterator().next());
		groupingAndSortingStrategyImpl.init(betRepository.findAllBets(), 1.14d);
	}

	private GroupingAndSortingStrategy obtainTradingStrategyInstance(GroupingAndSortingStrategyConfig strategy) {
		final String strategyClassname = strategy.getClassName();
		final String strategyBeanName = strategy.getBeanName();

		GroupingAndSortingStrategy strategyImpl = null;
		if (strategyBeanName != null) {
			// if beanName is configured, try get the bean first
			try {
				strategyImpl = (GroupingAndSortingStrategy) springContext.getBean(strategyBeanName);

			} catch (NullPointerException e) {
				final String errorMsg = "Failed to obtain bean [ " + strategyBeanName + "] from spring context";
				LOG.error(errorMsg);
				throw new IllegalArgumentException(errorMsg);
			}
		}
		if (strategyImpl == null) {
			// if beanName not configured use className
			strategyImpl = ConfigurableComponentFactory.createComponent(strategyClassname);
		}
		return strategyImpl;
	}
	
	private List<Bet> getSomeBets() {
		return Arrays.asList(new Bet("Bet-10", 1489490156000l, 1, "Selection-1", 0.5, 6.0, "GBP"), new Bet("Bet-11", 1489490156000l, 2, "Selection-2", 1.25, 4.0, "EUR"), new Bet("Bet-12", 1489230956000l, 4, "Selection-4", 5.0, 4.5, "GBP"),
				new Bet("Bet-13", 1489403756000l, 3, "Selection-3", 4.5, 5.5, "GBP"), new Bet("Bet-14", 1489144556000l, 2, "Selection-2", 7.9, 7.0, "EUR"), new Bet("Bet-15", 1489140956000l, 1, "Selection-1", 3.4, 6.5, "EUR"),
				new Bet("Bet-16", 1489227356000l, 4, "Selection-4", 2.5, 6.5, "GBP"), new Bet("Bet-17", 1489313756000l, 2, "Selection-2", 1.5, 11.0, "EUR"), new Bet("Bet-18", 1489310156000l, 2, "Selection-2", 3.8, 5.5, "GBP"),
				new Bet("Bet-19", 1489482956000l, 3, "Selection-3", 3.4, 4.0, "GBP"), new Bet("Bet-20", 1489396556000l, 4, "Selection-4", 2.25, 5.0, "EUR"), new Bet("Bet-21", 1489137356000l, 2, "Selection-2", 5.4, 6.5, "EUR"),
				new Bet("Bet-22", 1489223756000l, 3, "Selection-3", 6.7, 6.5, "GBP"), new Bet("Bet-23", 1489310156000l, 3, "Selection-3", 1.1, 4.5, "EUR"), new Bet("Bet-24", 1489324556000l, 4, "Selection-4", 2.0, 6.5, "GBP"),
				new Bet("Bet-25", 1489151756000l, 2, "Selection-2", 3.2, 6.0, "GBP"), new Bet("Bet-26", 1489497356000l, 2, "Selection-2", 4.2, 5.0, "EUR"), new Bet("Bet-27", 1489410956000l, 3, "Selection-3", 2.1, 4.5, "EUR"),
				new Bet("Bet-28", 1489324556000l, 1, "Selection-1", 7.8, 5.5, "GBP"), new Bet("Bet-29", 1489320956000l, 4, "Selection-4", 6.2, 6.5, "GBP"), new Bet("Bet-30", 1489493756000l, 4, "Selection-4", 8.4, 7.5, "EUR"),
				new Bet("Bet-31", 1489407356000l, 1, "Selection-1", 10.5, 7.3, "GBP"), new Bet("Bet-32", 1489320956000l, 3, "Selection-3", 5, 5.5, "GBP"), new Bet("Bet-33", 1489234556000l, 2, "Selection-2", 0.75, 7.0, "EUR"));
		}
}

