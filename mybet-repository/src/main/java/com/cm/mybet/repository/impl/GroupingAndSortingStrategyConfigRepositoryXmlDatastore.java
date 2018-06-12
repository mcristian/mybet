package com.cm.mybet.repository.impl;

import static com.cm.mybet.datastore.FileLocations.GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME;
import static com.cm.mybet.datastore.FileLocations.GROUPING_AND_SORTING_STRATEGIES_CONFIG_XSD_FILENAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cm.mybet.datastore.ConfigurationManager;
import com.cm.mybet.datastore.grouping.sorting.strategy.generated.GroupingAndSortingStrategiesType;
import com.cm.mybet.datastore.grouping.sorting.strategy.generated.GroupingAndSortingStrategyType;
import com.cm.mybet.objects.grouping.sorting.strategy.GroupingAndSortingStrategyConfig;
import com.cm.mybet.repository.GroupingAndSortingStrategyConfigRepository;;

/**
 * An XML datastore implementation of the Grouping and Sorting Strategy config repository.
 *
 * @author mcristian
 */
@Repository("groupingAndSortingStrategyConfigRepository")
@Transactional
public class GroupingAndSortingStrategyConfigRepositoryXmlDatastore implements GroupingAndSortingStrategyConfigRepository {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public List<GroupingAndSortingStrategyConfig> findAll() {

        LOG.info(() -> "Fetching all Grouping and Sorting Strategy configs...");
        final GroupingAndSortingStrategiesType internalStrategiesConfig = ConfigurationManager.loadConfig(GroupingAndSortingStrategiesType.class,
        		GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XSD_FILENAME);
        return adaptAllInternalToAllExternalConfig(internalStrategiesConfig);
    }

    @Override
    public GroupingAndSortingStrategyConfig findById(String id) {

        LOG.info(() -> "Fetching config for Strategy id: " + id);

        final GroupingAndSortingStrategiesType internalStrategiesConfig = ConfigurationManager.loadConfig(GroupingAndSortingStrategiesType.class,
        		GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XSD_FILENAME);

        return adaptInternalToExternalConfig(
                internalStrategiesConfig.getGroupingAndSortingStrategy()
                        .stream()
                        .filter((item) -> item.getId().equals(id))
                        .distinct()
                        .collect(Collectors.toList()));
    }

    @Override
    public GroupingAndSortingStrategyConfig save(GroupingAndSortingStrategyConfig config) {

        final GroupingAndSortingStrategiesType internalStrategiesConfig = ConfigurationManager.loadConfig(GroupingAndSortingStrategiesType.class,
        		GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XSD_FILENAME);

        final List<GroupingAndSortingStrategyType> strategyTypes = internalStrategiesConfig.getGroupingAndSortingStrategy()
                .stream()
                .filter((item) -> item.getId().equals(config.getId()))
                .distinct()
                .collect(Collectors.toList());

        if (config.getId() == null || config.getId().isEmpty()) {

            LOG.info(() -> "About to create StrategyConfig: " + config);

            if (strategyTypes.isEmpty()) {

                final GroupingAndSortingStrategyConfig newStrategyConfig = new GroupingAndSortingStrategyConfig(config);
                newStrategyConfig.setId(generateUuid());

                internalStrategiesConfig.getGroupingAndSortingStrategy().add(adaptExternalToInternalConfig(newStrategyConfig));
                ConfigurationManager.saveConfig(GroupingAndSortingStrategiesType.class, internalStrategiesConfig,
                		GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME);

                final GroupingAndSortingStrategiesType updatedInternalStrategiesConfig = ConfigurationManager.loadConfig(
                		GroupingAndSortingStrategiesType.class, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XSD_FILENAME);

                return adaptInternalToExternalConfig(
                        updatedInternalStrategiesConfig.getGroupingAndSortingStrategy()
                                .stream()
                                .filter((item) -> item.getId().equals(newStrategyConfig.getId()))
                                .distinct()
                                .collect(Collectors.toList()));
            } else {
                throw new IllegalStateException("Trying to create new StrategyConfig but null/empty id already exists. " +
                        "StrategyConfig: " + config + " Existing StrategyConfigs: "
                        + adaptAllInternalToAllExternalConfig(internalStrategiesConfig));
            }

        } else {

            LOG.info(() -> "About to update StrategyConfig: " + config);

            if (!strategyTypes.isEmpty()) {

                internalStrategiesConfig.getGroupingAndSortingStrategy().remove(strategyTypes.get(0));
                internalStrategiesConfig.getGroupingAndSortingStrategy().add(adaptExternalToInternalConfig(config));
                ConfigurationManager.saveConfig(GroupingAndSortingStrategiesType.class, internalStrategiesConfig, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME);

                final GroupingAndSortingStrategiesType updatedInternalStrategiesConfig = ConfigurationManager.loadConfig(
                        GroupingAndSortingStrategiesType.class, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XSD_FILENAME);

                return adaptInternalToExternalConfig(
                        updatedInternalStrategiesConfig.getGroupingAndSortingStrategy()
                                .stream()
                                .filter((item) -> item.getId().equals(config.getId()))
                                .distinct()
                                .collect(Collectors.toList()));
            } else {
                LOG.warn("Trying to update StrategyConfig but id does not exist StrategyConfig: " + config +
                        " Existing StrategyConfig: " + adaptAllInternalToAllExternalConfig(internalStrategiesConfig));
                return null;
            }
        }
    }

    @Override
    public GroupingAndSortingStrategyConfig delete(String id) {

        LOG.info(() -> "Deleting Strategy config for id: " + id);

        final GroupingAndSortingStrategiesType internalStrategiesConfig = ConfigurationManager.loadConfig(GroupingAndSortingStrategiesType.class,
                GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME, GROUPING_AND_SORTING_STRATEGIES_CONFIG_XSD_FILENAME);

        final List<GroupingAndSortingStrategyType> strategyTypes = internalStrategiesConfig.getGroupingAndSortingStrategy()
                .stream()
                .filter((item) -> item.getId().equals(id))
                .distinct()
                .collect(Collectors.toList());

        if (!strategyTypes.isEmpty()) {

            final GroupingAndSortingStrategyType strategyToRemove = strategyTypes.get(0); // will only be 1 unique strat
            internalStrategiesConfig.getGroupingAndSortingStrategy().remove(strategyToRemove);
            ConfigurationManager.saveConfig(GroupingAndSortingStrategiesType.class, internalStrategiesConfig,
                    GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME);

            return adaptInternalToExternalConfig(Collections.singletonList(strategyToRemove));
        } else {
            LOG.warn("Trying to delete StrategyConfig but id does not exist. StrategyConfig id: " + id
                    + " Existing StrategyConfig: " + adaptAllInternalToAllExternalConfig(internalStrategiesConfig));
            return null;
        }
    }

    // ------------------------------------------------------------------------------------------------
    // Adapter methods
    // ------------------------------------------------------------------------------------------------

    private static List<GroupingAndSortingStrategyConfig> adaptAllInternalToAllExternalConfig(GroupingAndSortingStrategiesType internalStrategiesConfig) {

        final List<GroupingAndSortingStrategyConfig> strategyConfigItems = new ArrayList<>();

        final List<GroupingAndSortingStrategyType> internalStrategyConfigItems = internalStrategiesConfig.getGroupingAndSortingStrategy();
        internalStrategyConfigItems.forEach((item) -> {

            final GroupingAndSortingStrategyConfig strategyConfig = new GroupingAndSortingStrategyConfig();
            strategyConfig.setId(item.getId());
            strategyConfig.setName(item.getName());
            strategyConfig.setDescription(item.getDescription());
            strategyConfig.setClassName(item.getClassName());
            strategyConfig.setBeanName(item.getBeanName());
            strategyConfigItems.add(strategyConfig);
        });

        return strategyConfigItems;
    }

    private static GroupingAndSortingStrategyConfig adaptInternalToExternalConfig(List<GroupingAndSortingStrategyType> internalStrategyConfigItems) {

        if (!internalStrategyConfigItems.isEmpty()) {

            final GroupingAndSortingStrategyConfig strategyConfig = new GroupingAndSortingStrategyConfig();

            // Should only ever be 1 unique Strategy id
            final GroupingAndSortingStrategyType internalStrategyConfig = internalStrategyConfigItems.get(0);
            strategyConfig.setId(internalStrategyConfig.getId());
            strategyConfig.setName(internalStrategyConfig.getName());
            strategyConfig.setDescription(internalStrategyConfig.getDescription());
            strategyConfig.setClassName(internalStrategyConfig.getClassName());
            strategyConfig.setBeanName(internalStrategyConfig.getBeanName());
            return strategyConfig;
        }
        return null;
    }

    private static GroupingAndSortingStrategyType adaptExternalToInternalConfig(GroupingAndSortingStrategyConfig externalStrategyConfig) {
        final GroupingAndSortingStrategyType strategyType = new GroupingAndSortingStrategyType();
        strategyType.setId(externalStrategyConfig.getId());
        strategyType.setName(externalStrategyConfig.getName());
        strategyType.setDescription(externalStrategyConfig.getDescription());
        strategyType.setClassName(externalStrategyConfig.getClassName());
        strategyType.setBeanName(externalStrategyConfig.getBeanName());
        return strategyType;
    }

    // ------------------------------------------------------------------------------------------------
    // Util methods
    // ------------------------------------------------------------------------------------------------

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }
}

