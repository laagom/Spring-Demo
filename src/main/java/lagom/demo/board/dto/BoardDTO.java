package lagom.demo.board.dto;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class BoardDTO {
    /* 조회 요청 시 사용 */
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
    
    /* 저장 요청 시 사용 */
    @Data
    public static class RequestSave{
        private String boardId;
        private String boardType;
        private String title;
        private String contents;
        private String writer;
        private String isPublic;
        private Date regDate;
    }
    
    /* 응답 시 사용*/
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
