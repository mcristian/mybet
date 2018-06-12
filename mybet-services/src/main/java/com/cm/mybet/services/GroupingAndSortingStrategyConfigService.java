package com.cm.mybet.services;

import java.util.List;

import com.cm.mybet.objects.grouping.sorting.strategy.GroupingAndSortingStrategyConfig;

/**
 * The Grouping and Sorting configuration service.
 *
 * @author mcristian
 */
public interface GroupingAndSortingStrategyConfigService {

	List<GroupingAndSortingStrategyConfig> getAllGroupingAndSortingStrategyConfig();
	
	GroupingAndSortingStrategyConfig getGroupingAndSortingStrategyConfig(String id);
	
	GroupingAndSortingStrategyConfig updateGroupingAndSortingStrategyConfig(GroupingAndSortingStrategyConfig config);
	GroupingAndSortingStrategyConfig createGroupingAndSortingStrategyConfig(GroupingAndSortingStrategyConfig config);
	
	GroupingAndSortingStrategyConfig deleteGroupingAndSortingStrategyConfig(String id);
}
