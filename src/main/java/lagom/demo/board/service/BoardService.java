package lagom.demo.board.service;

import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    /* 게시글 조회 */
    public List<BoardDTO.Response> getBoards(BoardDTO.Request req){
        return boardMapper.findAll(req);
    }

    /* 게시글 단건 조회 */
    public BoardDTO.Response getBoard(String boardId){
        return boardMapper.findById(boardId);
    }
}
