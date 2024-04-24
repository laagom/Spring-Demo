package lagom.demo.board.service;

import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    /* 게시글 저장 */
    @Transactional
    public int saveBoard(BoardDTO.RequestSave req){
        req.setBoardId(UUID.randomUUID().toString());
        return boardMapper.save(req);
    }

}
