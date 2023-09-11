package com.example.firstproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
    @Test
    public void 자바_객체를_JSON으로_변환() throws JsonProcessingException {
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
        Burger burger = new Burger("맥도날드 슈비버거", 5500, ingredients);


        // 수행
        String json = objectMapper.writeValueAsString(burger);  // burger객체를 넣어주면 문자열의 json String을 반환해준다.


        // 예상 - 결과를 복사해서 붙이기 한다.

        // String expected=" " ;
        String expected = "{\"name\":\"맥도날드 슈비버거\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";


        // 검증
        assertEquals(expected, json);
        System.out.println(json);
    }

}