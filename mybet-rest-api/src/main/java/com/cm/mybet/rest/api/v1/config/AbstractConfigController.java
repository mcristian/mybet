package com.cm.mybet.rest.api.v1.config;

/**
 * Base class for all config controllers.
 *
 * @author mcristian
 * @since 1.0
 */
abstract class AbstractConfigController extends com.cm.mybet.rest.api.v1.AbstractController {

    static final String CONFIG_ENDPOINT_BASE_URI = API_ENDPOINT_BASE_URI + "/config";
}
