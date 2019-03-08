package com.rollanddice.comunidice.exception;

public class DuplicateInstanceException extends InstanceException {

    public DuplicateInstanceException(Object key, String className) {
        super("Duplicate instance", key, className);
    }    

}
