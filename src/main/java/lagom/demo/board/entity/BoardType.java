package lagom.demo.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity
@Getter
@Table(name = "edu_boardtype")
public class BoardType implements Persistable<String> {
    @Id
    private String type;
    private String name;
    private String regDate;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
