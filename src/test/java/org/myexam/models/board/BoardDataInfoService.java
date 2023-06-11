package org.myexam.models.board;

import lombok.RequiredArgsConstructor;
import org.myexam.entities.BoardData;
import org.myexam.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataInfoService {

    private final BoardDataRepository boardDataRepository;

    public BoardData get(Long id) {

        BoardData boardData = boardDataRepository.findById(id).orElseThrow(BoardDataNotExistsException::new);

        return null;
    }
}
