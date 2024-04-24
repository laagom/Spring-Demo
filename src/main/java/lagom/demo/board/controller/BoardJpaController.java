package lagom.demo.board.controller;

import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.entity.Board;
import lagom.demo.board.service.BoardJspService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpaBoards")
public class BoardJpaController {
    private final BoardJspService boardJspService;

    /* (JPA) 게시글 조회 */
    @GetMapping
    public List<BoardDTO.Response> getBoards(BoardDTO.Request req){
        return boardJspService.getBoards(req);
    }

    /* (JPA) 게시글 단건 조회 */
    @GetMapping("/{boardId}")
    public BoardDTO.Response getBoard(@PathVariable("boardId") String boardId) {
        return boardJspService.getBoard(boardId);
    }
    
    /* (JPA) 게시글 저장 */
    @PostMapping
    public int saveBoard(@RequestBody BoardDTO.RequestSave req) {
        return boardJspService.saveBoard(req);
    }
    
    /* (JPA) 게시글 삭제 */
    @DeleteMapping
    public void deleteBoard(@PathVariable("boardId") String boardId) {
        boardJspService.deleteBoard(boardId);
    }
}
