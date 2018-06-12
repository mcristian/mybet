package com.cm.mybet.repository;

import java.util.List;

import com.cm.mybet.objects.grouping.sorting.strategy.GroupingAndSortingStrategyConfig;

/**
 * The Grouping and Sorting Strategy configuration repository.
 *
 * @author cmilasan
 */
public interface GroupingAndSortingStrategyConfigRepository {

	List<GroupingAndSortingStrategyConfig> findAll();

	GroupingAndSortingStrategyConfig findById(String id);

	GroupingAndSortingStrategyConfig save(GroupingAndSortingStrategyConfig config);

	GroupingAndSortingStrategyConfig delete(String id);
}
