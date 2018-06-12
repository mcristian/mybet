package com.cm.mybet.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.mybet.objects.grouping.sorting.strategy.GroupingAndSortingStrategyConfig;
import com.cm.mybet.repository.GroupingAndSortingStrategyConfigRepository;
import com.cm.mybet.services.GroupingAndSortingStrategyConfigService;

/**
 * Implementation of the Grouping and Sorting configuration service.
 *
 * @author mcristian
 */
@Service("groupingAndSortingStrategyConfigService")
@Transactional
@ComponentScan(basePackages = {"com.cm.mybet.repository"})
public class GroupingAndSortingStategyConfigServiceImpl implements GroupingAndSortingStrategyConfigService {

    private static final Logger LOG = LogManager.getLogger();

    private final GroupingAndSortingStrategyConfigRepository strategyConfigRepository;

    @Autowired
    public GroupingAndSortingStategyConfigServiceImpl(GroupingAndSortingStrategyConfigRepository strategyConfigRepository) {
        this.strategyConfigRepository = strategyConfigRepository;
    }

    @Override
    public List<GroupingAndSortingStrategyConfig> getAllGroupingAndSortingStrategyConfig() {
        return strategyConfigRepository.findAll();
    }

    @Override
    public GroupingAndSortingStrategyConfig getGroupingAndSortingStrategyConfig(String id) {
        LOG.info(() -> "Fetching Grouping and Sorting Strategy config for id: " + id);
        return strategyConfigRepository.findById(id);
    }

    @Override
    public GroupingAndSortingStrategyConfig updateGroupingAndSortingStrategyConfig(GroupingAndSortingStrategyConfig config) {
        LOG.info(() -> "About to update Grouping and Sorting Strategy config: " + config);
        return strategyConfigRepository.save(config);
    }

    @Override
    public GroupingAndSortingStrategyConfig createGroupingAndSortingStrategyConfig(GroupingAndSortingStrategyConfig config) {
        LOG.info(() -> "About to create Grouping and Sorting Strategy config: " + config);
        return strategyConfigRepository.save(config);
    }

    @Override
    public GroupingAndSortingStrategyConfig deleteGroupingAndSortingStrategyConfig(String id) {
        LOG.info(() -> "About to delete Grouping and Sorting Strategy config for id: " + id);
        return strategyConfigRepository.delete(id);
    }
}