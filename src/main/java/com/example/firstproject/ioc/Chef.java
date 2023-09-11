package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {

    private IngredientFactory ingredientFactory;

    // 생성자 주입
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        // 재료준비
//        Pork pork = new Pork("한돈 등심");
        // 요리 반환
//        return pork.getName() + "으로 만든 " + menu;
        // 재료준비
//        Beef beef = new Beef("한우 꽃등심");
        Ingredient ingredient = ingredientFactory.get(menu);
        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
