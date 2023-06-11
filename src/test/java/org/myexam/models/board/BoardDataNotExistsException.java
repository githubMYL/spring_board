package org.myexam.models.board;

import org.myexam.commons.CommonException;
import org.springframework.http.HttpStatus;

public class BoardDataNotExistsException extends CommonException {
    public BoardDataNotExistsException() {
        super(bundleValidation.getString("Validation.boardData.notExists"), HttpStatus.BAD_REQUEST);
    }
}
