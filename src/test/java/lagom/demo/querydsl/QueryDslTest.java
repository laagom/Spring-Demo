package lagom.demo.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lagom.demo.board.entity.Board;
import lagom.demo.board.entity.QBoard;
import lagom.demo.board.entity.QBoardType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryDslTest {
    @Autowired
    JPAQueryFactory queryFactory;
    private QBoard qBoard = QBoard.board;
    private QBoardType qBoardType = QBoardType.boardType;

    @Test
    void 일반조인() {
        List<Tuple> fetch = queryFactory
                .select(qBoard.title, qBoard.writer, qBoardType.type)
                .from(qBoard, qBoardType)
                .where(qBoard.boardType.eq(qBoardType.type))
                .limit(10)
                .orderBy(qBoard.regDate.desc(), qBoardType.type.asc())
                .fetch();

        for(Tuple t : fetch)
            System.out.println("title : " + t.get(qBoard.title));
    }

    @Test
    void 아우터조인() {
        List<Board> fetch = queryFactory
                .select(qBoard)
                .from(qBoard)
                    .leftJoin(qBoardType)
                .on(qBoard.boardType.eq(qBoardType.type))
                .limit(10)
                .orderBy(qBoard.regDate.desc())
                .fetch();

        for(Board board : fetch) {
            System.out.println("boardId : " + board.getBoardId());
            System.out.println("title : " + board.getTitle());
        }
    }

    @Test
    void 이그지스트 () {
        List<Board> fetch = queryFactory
                .selectFrom(qBoard)
                .where(JPAExpressions
                        .selectOne()
                        .from(qBoardType)
                        .where(qBoard.boardType.eq(qBoardType.type))
                        .exists()
                )
                .limit(10)
                .orderBy(qBoard.regDate.desc())
                .fetch();

        for(Board board : fetch) {
            System.out.println("boardId : " + board.getBoardId());
            System.out.println("title : " + board.getTitle());
        }
    }
}
