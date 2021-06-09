package com.miex.exception;

import java.io.IOException;

public class ESException extends IOException {
    public ESException() {
        super();
    }

    public ESException(String message){super(message);}

    public ESException(String message, Throwable cause) {
        super(message, cause);
    }

    public ESException(Throwable cause) {
        super(cause);
    }
}
