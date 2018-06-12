package com.cm.mybet.datastore;


/**
 * Locations of XML and XSD files for the entities.
 *
 * @author mcristian
 */
public final class FileLocations {

    /*
     * Location of the XML config files relative to project/installation root.
     */
    public static final String GROUPING_AND_SORTING_STRATEGIES_CONFIG_XML_FILENAME = "config/grouping-sorting-strategies.xml";

    /*
     * XSD schema files for validating the XML config - their location in the main/resources folder.
     */
    public static final String GROUPING_AND_SORTING_STRATEGIES_CONFIG_XSD_FILENAME = "com/cm/mybet/datastore/config/grouping-sorting-strategies.xsd";
    
    public static final String MY_BETS_CSV = "data/bets.csv";

    private FileLocations() {
    }
}
