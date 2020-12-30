package com.ykredi.izin.exception;

import java.util.ResourceBundle;

public class ContentNotFoundException extends RuntimeException {
    public ContentNotFoundException(String contentName) {
        super(contentName + " " + ResourceBundle.getBundle("messages").getString("content_not_found"));
    }
}
