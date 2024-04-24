package lagom.demo.board.controller;

import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.service.BoardJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpaBoards")
public class BoardJpaController {
    private final BoardJpaService boardJpaService;

    /* (JPA) 게시글 조회 */
    @GetMapping
    public List<BoardDTO.Response> getBoards(BoardDTO.Request req){
        return boardJpaService.getBoards(req);
    }

    /* (JPA) 게시글 단건 조회 */
    @GetMapping("/{boardId}")
    public BoardDTO.Response getBoard(@PathVariable("boardId") String boardId) {
        return boardJpaService.getBoard(boardId);
    }
    
    /* (JPA) 게시글 저장 */
    @PostMapping
    public int saveBoard(@RequestBody BoardDTO.RequestSave req) {
        return boardJpaService.saveBoard(req);
    }
    
    /* (JPA) 게시글 삭제 */
    @DeleteMapping
    public void deleteBoard(@PathVariable("boardId") String boardId) {
        boardJpaService.deleteBoard(boardId);
    }

    /* (JPA) 게시글 수정 */
    @PutMapping("/{boardId}")
    public void updateBoard(@RequestBody BoardDTO.RequestSave req, @PathVariable("boardId") String boardId) {
        boardJpaService.updateBoard(req, boardId);
    }
}
