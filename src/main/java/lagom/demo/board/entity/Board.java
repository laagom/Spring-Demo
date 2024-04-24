package lagom.demo.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@ToString
@Builder            // Setter를 우회 or 대신하기 위해 Builder() 사용
@DynamicUpdate      // 변경된 값이 없는 경우 update 처리를 하지 않음
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "edu_board")
public class Board implements Persistable<String> {
    /* @Persistable
    * isNew() 메서드를 오버라이드 할 수 있음
    * - isNew라는 @Transient 필드를 만들고, 초기값을 true로 설정
    * - @Transient 어노테이션으로 인해, 해당 필드응 실제 DB에 저장되지 않는다.
    * */

    @Id
    private String boardId;
    private String boardType;
    private String writer;
    private String title;
    private String contents;
    private String isPublic;
    private Date regDate;

    @Override
    public String getId() {
        return boardId;
    }

    @Transient  // 일시적인 필드라는 것을 명시
    private Boolean isNew;
    @Override
    public boolean isNew() {
        return boardId==null || this.isNew==null ? false : this.isNew.booleanValue();
    }

    public void createBoard() {
        this.boardId = UUID.randomUUID().toString();
        this.regDate = new Date();
        this.isNew = true;
    }
}
