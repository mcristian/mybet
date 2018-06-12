package com.cm.mybet.objects.grouping.sorting.strategy;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Object representing a Grouping and Strategy configuration.
 *
 * @author mcristian
 */
public class GroupingAndSortingStrategyConfig {

    private String id;
    private String name;
    private String description;
    private String className;
    private String beanName;


    // required for Jackson
    public GroupingAndSortingStrategyConfig() {
    }

    public GroupingAndSortingStrategyConfig(GroupingAndSortingStrategyConfig other) {
        this.id = other.id;
        this.name = other.name;
        this.description = other.description;
        this.className = other.className;
        this.beanName = other.beanName;
    }

    public GroupingAndSortingStrategyConfig(String id, String name, String description, String className, String beanName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.className = className;
        this.beanName = beanName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupingAndSortingStrategyConfig that = (GroupingAndSortingStrategyConfig) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("className", className)
                .add("beanName", beanName)
                .toString();
    }
}
