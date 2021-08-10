package com.yara;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class TagMetaDataRepository implements PanacheRepository<TagDetails> {

    public static List<TagDetails> getTagMetadataByHistorianName(String historianName) {
        return TagDetails.find("historianName", historianName).list();
    }
}
