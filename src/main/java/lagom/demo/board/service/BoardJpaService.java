package lagom.demo.board.service;

import jakarta.persistence.EntityExistsException;
import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.dto.mapping.BoardDtoMapping;
import lagom.demo.board.entity.Board;
import lagom.demo.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardJpaService {
    private final BoardRepository boardRepository;

    /* (JPA) 게시글 조회 */
    public List<BoardDTO.Response> getBoards(BoardDTO.Request req){
        /*
        // keyword 값이 빈값이면 전체로 조회
        String keyword = req.getKeyword();
        List<Board> boards = StringUtils.isEmpty(keyword)
                               ? boardRepository.findAll()
                               : boardRepository.findByTitleContaining(keyword);

        // 응답시 사용할 수 있는 DTO 형태로 변경
        return BoardDtoMapping.INSTANCE.toDto(boards);
         */
        
        // DSL을 이용한 조회 쿼리
        return boardRepository.findAllByQueryDSL(req);
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

    @Transactional(rollbackFor = {Exception.class})
    public int saveBoard(BoardDTO.RequestSave req) {
        //1. Builder를 통한 저장
        /*
        Board entity = Board.builder()
                .boardId(UUID.randomUUID().toString())
                .boardType(req.getBoardType())
                .isPublic(req.getIsPublic())
                .contents(req.getIsPublic())
                .writer(req.getWriter())
                .title(req.getTitle())
                .regDate(new Date())
                .build();
        boardRepository.save(entity);
         */

        // 2. Mapping 객체를 통해 저장
        Board board = BoardDtoMapping.INSTANCE.toEntity(req);
        board.createBoard();
        boardRepository.save(board);
        return 1;
    }

    /* (JPA) 게시글 삭제 */
    @Transactional(rollbackFor = {Exception.class})
    public void deleteBoard(String boardId) {
        boardRepository.deleteById(boardId);
    }

    /* (JPA) 게시글 수정 */
    @Transactional(rollbackFor = {Exception.class})
    public void updateBoard(BoardDTO.RequestSave req, String boardId) {
        boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityExistsException("게시물이 없습니다."))
                .updateBoard(req);
    }
}
