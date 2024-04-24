package lagom.demo.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Map;

public class HelloDTO {
    @Setter
    @Getter
    @ToString
    public static class Request {
        @NotBlank(message="ID는 필수항목입니다.")
        private String id;
        private String name;
    }

    @Data
    public static class RequestJSON {
        private String id;
        private String name;
        private List<Map<String, Object>> roles;
    }
    @Data
    public static class RequestRoles {
        private String id;
        private String name;
        private List<Role> roles;
    }

    @Data
    public static class Role {
        private String role;
        private String comId;
        private Boolean isHas;
    }

    @Data
    @Builder
    public static class Response {
        private String code;
        private String msg;
    }
}
