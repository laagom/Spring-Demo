package lagom.demo.board.service;

import jakarta.persistence.EntityExistsException;
import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.dto.mapping.BoardDtoMapping;
import lagom.demo.board.entity.Board;
import lagom.demo.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardJspService {
    private final BoardRepository boardRepository;

    /* (JPA) 게시글 조회 */
    public List<BoardDTO.Response> getBoards(BoardDTO.Request req){
        //List<Board> boards = boardRepository.findAll();
        
        // keyword 값이 빈값이면 전체로 조회
        String keyword = req.getKeyword();
        List<Board> boards = StringUtils.isEmpty(keyword)
                               ? boardRepository.findAll()
                               : boardRepository.findByTitleContaining(keyword);

        // 응답시 사용할 수 있는 DTO 형태로 변경
        return BoardDtoMapping.INSTANCE.toDto(boards);
    }

    /* (JPA) 게시글 단건 조회 */
    public BoardDTO.Response getBoard(String boardId) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        if(boardOptional.isPresent()) {
            Board board = boardOptional.get();
            return BoardDtoMapping.INSTANCE.toDto(board);
        }else {
            throw new EntityExistsException("해당하는 게시물이 없습니다.");
        }
    }
}
