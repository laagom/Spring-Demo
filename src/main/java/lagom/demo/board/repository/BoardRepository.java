package lagom.demo.board.repository;

import lagom.demo.board.entity.Board;
import lagom.demo.board.repository.custom.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, String>, BoardRepositoryCustom {
    /* (JPA) 게시물 조회 */
    List<Board> findByTitleContaining(String keyword);
}
