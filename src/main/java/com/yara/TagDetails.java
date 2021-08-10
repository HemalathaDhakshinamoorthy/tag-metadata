package com.yara;

import com.fasterxml.jackson.annotation.JsonView;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;

enum Type {
    ANALOG,
    DIGITAL,
    DISCRETE,
    STRING
}
@Entity
public class TagDetails extends PanacheEntity {

    @JsonView(Views.Private.class)
    public Long id;

    @JsonView(Views.Private.class)
    public String historianName;

    @JsonView(Views.Public.class)
    public String name;

    @JsonView(Views.Public.class)
    public String description;

    @JsonView(Views.Public.class)
    public String units;

    @JsonView(Views.Public.class)
    public Type type;

    public TagDetails() {

    }
    public TagDetails(String historianName, String name, String description, String units, Type type) {
        this.historianName = historianName;
        this.name = name;
        this.description = description;
        this.units = units;
        this.type = type;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistorianName() {
        return historianName;
    }

    public void setHistorianName(String historianName) {
        this.historianName = historianName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
