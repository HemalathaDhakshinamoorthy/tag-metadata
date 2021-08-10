package com.yara;

import java.io.Serializable;

public class TagMetadataException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public TagMetadataException() {
        super();
    }
    public TagMetadataException(String msg) {
        super(msg);
    }
    public TagMetadataException(String msg, Exception e)  {
        super(msg, e);
    }
 }