package com.cm.mybet.rest.api.v1.config;

import static com.cm.mybet.rest.api.v1.config.AbstractConfigController.CONFIG_ENDPOINT_BASE_URI;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cm.mybet.objects.grouping.sorting.strategy.GroupingAndSortingStrategyConfig;
import com.cm.mybet.services.GroupingAndSortingStrategyConfigService;

/**
 * Controller for directing Grouping and Sorting Strategy config requests.
 *
 * @author mcristian
 * @since 1.0
 */
@RestController
@RequestMapping(CONFIG_ENDPOINT_BASE_URI)
public class GroupingAndSortingStrategyConfigController extends AbstractConfigController {

    private static final Logger LOG = LogManager.getLogger();
    private static final String STRATEGIES_RESOURCE_PATH = "/strategies";
    private final GroupingAndSortingStrategyConfigService strategyConfigService;

    @Autowired
    public GroupingAndSortingStrategyConfigController(GroupingAndSortingStrategyConfigService strategyConfigService) {
        this.strategyConfigService = strategyConfigService;
    }

    /**
     * Returns all of the Grouping and Sorting Strategy configuration.
     *
     * @param user the authenticated user.
     * @return all the Strategy configurations.
     */
    @RequestMapping(value = STRATEGIES_RESOURCE_PATH, method = RequestMethod.GET)
    public List<GroupingAndSortingStrategyConfig> getAllStrategies(@AuthenticationPrincipal User user) {

        LOG.info("GET " + STRATEGIES_RESOURCE_PATH + " - getAllGroupingAndSortingStrategies() - caller: " + user.getUsername());

        final List<GroupingAndSortingStrategyConfig> strategyConfigs = strategyConfigService.getAllGroupingAndSortingStrategyConfig();

        LOG.info("Response: " + strategyConfigs);
        return strategyConfigs;
    }

    /**
     * Returns the Grouping and Sorting Strategy configuration for a given id.
     *
     * @param user       the authenticated user.
     * @param strategyId the id of the Grouping and Sorting Strategy to fetch.
     * @return the Grouping and Sorting Strategy configuration.
     */
    @RequestMapping(value = STRATEGIES_RESOURCE_PATH + "/{strategyId}", method = RequestMethod.GET)
    public ResponseEntity<?> getStrategy(@AuthenticationPrincipal User user, @PathVariable String strategyId) {

        LOG.info("GET " + STRATEGIES_RESOURCE_PATH + "/" + strategyId + " - getGroupingAndSortingStrategy() - caller: " + user.getUsername());

        final GroupingAndSortingStrategyConfig strategyConfig = strategyConfigService.getGroupingAndSortingStrategyConfig(strategyId);
        return strategyConfig == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : buildResponseEntity(strategyConfig, HttpStatus.OK);
    }

    /**
     * Updates a given Grouping and Sorting Strategy configuration.
     *
     * @param user       the authenticated user.
     * @param strategyId id of the Grouping and Sorting Strategy config to update.
     * @param config     the updated Grouping and Sorting Strategy config.
     * @return 200 'OK' HTTP status code and updated Grouping and Sorting Strategy config in the body if update successful,
     * 404 'Not Found' HTTP status code if Grouping and Sorting Strategy config not found.
     */
    @RequestMapping(value = STRATEGIES_RESOURCE_PATH + "/{strategyId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStrategy(@AuthenticationPrincipal User user, @PathVariable String strategyId,
                                            @RequestBody GroupingAndSortingStrategyConfig config) {

        LOG.info("PUT " + STRATEGIES_RESOURCE_PATH + "/" + strategyId + " - updateGroupingAndSortingStrategy() - caller: " + user.getUsername());
        LOG.info("Request: " + config);

        if (config.getId() == null || !strategyId.equals(config.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final GroupingAndSortingStrategyConfig updatedConfig = strategyConfigService.updateGroupingAndSortingStrategyConfig(config);
        return updatedConfig == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : buildResponseEntity(updatedConfig, HttpStatus.OK);
    }

    /**
     * Creates a new Grouping and Sorting Strategy configuration.
     *
     * @param user   the authenticated user.
     * @param config the new Grouping and Sorting Strategy config.
     * @return 201 'Created' HTTP status code and created Grouping and Sorting Strategy config in response body if create successful,
     * some other status code otherwise.
     */
    @RequestMapping(value = STRATEGIES_RESOURCE_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> createStrategy(@AuthenticationPrincipal User user, @RequestBody GroupingAndSortingStrategyConfig config) {

        LOG.info("POST " + STRATEGIES_RESOURCE_PATH + " - createStrategy() - caller: " + user.getUsername());
        LOG.info("Request: " + config);

        final GroupingAndSortingStrategyConfig createdConfig = strategyConfigService.createGroupingAndSortingStrategyConfig(config);
        return createdConfig == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : buildResponseEntity(createdConfig, HttpStatus.CREATED);
    }

    /**
     * Deletes a Grouping and Sorting Strategy configuration for a given id.
     *
     * @param user       the authenticated user.
     * @param strategyId the id of the Grouping and Sorting Strategy configuration to delete.
     * @return 204 'No Content' HTTP status code if delete successful, 404 'Not Found' HTTP status code if
     * Grouping and Sorting Strategy config not found.
     */
    @RequestMapping(value = STRATEGIES_RESOURCE_PATH + "/{strategyId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStrategy(@AuthenticationPrincipal User user, @PathVariable String strategyId) {

        LOG.info("DELETE " + STRATEGIES_RESOURCE_PATH + "/" + strategyId + " - deleteGroupingAndSortingStrategyConfig() - caller: " + user.getUsername());

        final GroupingAndSortingStrategyConfig deletedConfig = strategyConfigService.deleteGroupingAndSortingStrategyConfig(strategyId);
        return deletedConfig == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

