package com.cm.mybet.grouping.sorting.strategy.api;

/**
 *	Exception for Grouping and Sorting strategy
 * @author mcristian
 * @since 1.0
 */
public final class GroupingAndSortingStrategyException extends Exception {

    private static final long serialVersionUID = -5066890753686004758L;

    /**
     * Constructor builds exception with error message.
     *
     * @param msg the error message.
     */
    public GroupingAndSortingStrategyException(String msg) {
        super(msg);
    }

    /**
     * Constructor builds exception from original throwable.
     *
     * @param e the original exception.
     */
    public GroupingAndSortingStrategyException(Throwable e) {
        super(e);
    }

    /**
     * Constructor builds exception with error message and original throwable.
     *
     * @param msg the error message.
     * @param e   the original exception.
     */
    public GroupingAndSortingStrategyException(String msg, Throwable e) {
        super(msg, e);
    }
}