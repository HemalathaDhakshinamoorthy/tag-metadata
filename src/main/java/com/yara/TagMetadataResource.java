package com.yara;

import com.fasterxml.jackson.annotation.JsonView;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/v2/tags")
public class TagMetadataResource {

    @POST
    @Transactional
    @Path("/addTagDetails")
    public String addTagDetails(List<TagDetails> tagDetails) {
        TagDetails.persist(tagDetails);
        return "All tag metadata details are saved";
    }

    @JsonView(Views.Private.class)
    @GET
    @Path("/allTagDetails")
    public List<TagDetails> getAllTagMetadata() {
        return TagDetails.listAll();
    }

    @JsonView(Views.Public.class)
    @GET
    @Path("/{historianName}")
    public List<TagDetails> getTagMetadataByHistorianName(@PathParam String historianName) throws TagMetadataException {
        List<TagDetails> tagMetadataList = TagMetaDataRepository.getTagMetadataByHistorianName(historianName);
        if(tagMetadataList.isEmpty()) throw new TagMetadataException("No tags available for given historianName : " + historianName);
        return tagMetadataList;
    }

}