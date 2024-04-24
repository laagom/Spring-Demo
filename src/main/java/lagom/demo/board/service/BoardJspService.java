package lagom.demo.board.service;

import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.dto.mapping.BoardDtoMapping;
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
    public List<BoardDTO.Response> getBoards(){
        List<Board> boards = boardRepository.findAll();

        /* 응답시 사용할 수 있는 DTO 형태로 변경 */
        return BoardDtoMapping.INSTANCE.toDto(boards);
    }
}
