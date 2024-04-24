package lagom.demo.board.repository;

import lagom.demo.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    /* (JPA) 게시물 조회 */
    List<Board> findByTitleContaining(String keyword);
}
