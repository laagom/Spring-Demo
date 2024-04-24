package lagom.demo.board.controller;

import jakarta.validation.Valid;
import lagom.demo.board.dto.HelloDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    /* GetMapping 라우팅
    * GetMapping에 path가 없는경우 최상위 경로를 따름
    * */
    @GetMapping
    public String hello(){
        return "HELLO";
    }

    /* Request 파라미터 전달
    * - @RequestParam이 추가되지 않으면 프레임워크가 인식하지 못함*/
    @GetMapping("/param1")
    public String param1(@RequestParam("id") String id, @RequestParam("name") String name){
        return "id :" + id + ", name :" + name;
    }

    /* Request 파라미터 참조객체 전달
    * - @RequestParam 추가되지 않으면 인식하지 못하지만 오류는 발생 X*/
    @GetMapping("/param2")
    public String param2(@RequestParam Map<String,Object> map){
        return map.toString();
    }

    /* Path 파라미터 전달
     * Request 파라미터와 다른 @PathVariable를 사용
     * - @GetMapping의 식별자와 @PathVariable의 식별자 값이 일치해야함
     * */
    @GetMapping("/param3/{id}/{name}")
    public String param3(@PathVariable("id") String id, @PathVariable("name") String name){
        return "id : " + id + ", name : " + name;
    }

    /* DTO 파라미터 전달
     * Request 파라미터로 전달시 DTO와 일치하는 key가 있으며 해당 객체로 세팅됨
     * */
    @GetMapping("/param4")
    public String param4(HelloDTO.Request dto){
        return dto.toString();
    }

    /* Builder를 통한 응답
     * DTO Response에 @Builder를 붙이면 사용 가능
     * - 단 값을 초기화 하지 않을 시 null로 출력됨
     * */
    @GetMapping("/param5")
    public HelloDTO.Response param5(@Valid HelloDTO.Request dto){
        HelloDTO.Response response = HelloDTO.Response.builder()
                .code(dto.getId())
                .msg(dto.getName())
                .build();
        return response;
    }

    /* PostMapping으로 서버요청
     * Post방식과 Json형태의 파라미터를 받을 때 @RequestBody사용
     * */
    @PostMapping("/param6")
    public HelloDTO.RequestJSON param6(@RequestBody HelloDTO.RequestJSON dto){
        return dto;
    }

    /* PostMapping
     * 참조객체 DTO 전달 받을 시 is, get, map등의 예약어는 생략함
     * Ex > boolean isHas를 불러오는 경우 has명칭으로 조회
     * - isHas를 사용하고 싶으면 Boolean 참조 객체를 사용해야 함
     * */
    @PostMapping("/param7")
    public HelloDTO.RequestRoles param7(@RequestBody HelloDTO.RequestRoles dto){
        return dto;
    }
}
