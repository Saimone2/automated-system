package org.saimone.exceptions;

import java.io.IOException;

public class EmptyFileException extends IOException {
    public EmptyFileException(String errorMessage) {
        super(errorMessage);
    }
}
