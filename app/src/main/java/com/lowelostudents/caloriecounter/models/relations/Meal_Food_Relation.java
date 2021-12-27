package com.lowelostudents.caloriecounter.models.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.lowelostudents.caloriecounter.models.Food;
import com.lowelostudents.caloriecounter.models.Meal;
import com.lowelostudents.caloriecounter.models.Meal_Food;

import java.util.List;

import lombok.Data;

@Data
public class Meal_Food_Relation {
    @Embedded
    private Meal meal;
    @Relation(
            parentColumn = "mealId",
            entityColumn = "foodId",
            associateBy = @Junction(Meal_Food.class))
    List<Food> foods;
}