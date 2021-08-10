package com.yara;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TagMetadataExceptionHandler implements ExceptionMapper<TagMetadataException> {
    @Override
    public Response toResponse(TagMetadataException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }
}