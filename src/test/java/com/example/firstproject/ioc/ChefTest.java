package com.example.firstproject.ioc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {

    @Autowired IngredientFactory ingredientFactory;
    @Autowired Chef chef;

    @Test
    void 돈까스_요리하기(){
        // given
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "돈까스";

        // when
        String food = chef.cook(menu);

        // then
        String expected = "한돈 등심으로 만든 돈까스";
        Assertions.assertThat(food).isEqualTo(expected);
    }

    @Test
    void 스테이크_요리하기(){
        // given
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";

        // when
        String food = chef.cook(menu);

        // then
        String expected = "한우 꽃등심으로 만든 스테이크";
        Assertions.assertThat(food).isEqualTo(expected);
    }


    @Test
    void 요리하기(){
        // given
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "크리스피 치킨";

        // when
        String food = chef.cook(menu);

        // then
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";
        Assertions.assertThat(food).isEqualTo(expected);
    }
}