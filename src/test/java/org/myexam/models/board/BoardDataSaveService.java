package org.myexam.models.board;

import lombok.RequiredArgsConstructor;
import org.myexam.commons.validators.Validator;
import org.myexam.controllers.boards.BoardForm;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataSaveService {

    private final BoardValidator validator;

    public void save(BoardForm boardForm) {
        validator.check(boardForm);
    }
}
