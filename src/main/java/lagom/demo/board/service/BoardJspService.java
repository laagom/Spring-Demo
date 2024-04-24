package lagom.demo.board.service;

import lagom.demo.board.entity.Board;
import lagom.demo.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardJspService {
    private final BoardRepository boardRepository;

    /* (JPA) 게시글 조회*/
    public List<Board> getBoards(){
        return boardRepository.findAll();
    }
}
