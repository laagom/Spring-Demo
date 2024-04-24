package lagom.demo.board.dto;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class BoardDTO {
    @Data
    public static class Request {
        private String keyword;
        private String sort;

        // 정렬에 대한 sort String 데이터 정제
        public String getOrderBySql() {
            String [] split = StringUtils.split(sort ,",");
            return "order by " + split[0] + " " + split[1];
        }
    }

    @Data
    public static class Response {
        private String boardId;
        private String boardType;
        private String title;
        private String contents;
        private String writer;
        private String isPublic;
        private Date regDate;
    }
}
