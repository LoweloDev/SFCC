package com.lowelostudents.caloriecounter.ui.viewmodels;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lowelostudents.caloriecounter.data.repositories.DayFoodRepo;
import com.lowelostudents.caloriecounter.data.repositories.DayRepo;
import com.lowelostudents.caloriecounter.data.repositories.FoodRepo;
import com.lowelostudents.caloriecounter.data.repositories.MealFoodRepo;
import com.lowelostudents.caloriecounter.enums.ActivityMode;
import com.lowelostudents.caloriecounter.models.entities.Day;
import com.lowelostudents.caloriecounter.models.entities.Day_Food;
import com.lowelostudents.caloriecounter.models.entities.Food;
import com.lowelostudents.caloriecounter.models.entities.Meal;
import com.lowelostudents.caloriecounter.models.entities.Meal_Food;
import com.lowelostudents.caloriecounter.models.entities.Nutrients;

import java.util.Calendar;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class FoodViewModel extends AndroidViewModel {
    @Getter
    private final LiveData<List<Food>> foods;
    private final FoodRepo repo;
    private final DayFoodRepo dayFoodRepo;
    private final MealFoodRepo mealFoodRepo;
//    @Getter @Setter
//    private ActivityMode activityMode = ActivityMode.CREATE;

    public FoodViewModel(Application context) {
        super(context);
        repo = new FoodRepo(context.getApplicationContext());
        dayFoodRepo = new DayFoodRepo(context.getApplicationContext());
        mealFoodRepo = new MealFoodRepo(context.getApplicationContext());
        foods = repo.getFoods();
    }

    public Long insert(Food t) {
        return repo.insert(t);
    }

    public void addToDay(Food food) {
        Calendar cal = Calendar.getInstance();
        Day_Food day_food = new Day_Food(food.getId() , cal.get(Calendar.DATE));

//        if (this.activityMode == ActivityMode.CREATE) {
            dayFoodRepo.insert(day_food);
//            this.activityMode = ActivityMode.UPDATE;
//        } else {
//            dayFoodRepo.delete(day_food);
//            this.activityMode = ActivityMode.CREATE;
//        }
    }

    public void removeFromDay(Food food) {
        dayFoodRepo.delete(Day_Food.class, food.getId());
    }

    public Long[] insertAll(List<Food> t) {
        return repo.insert(t);
    }

    public void update(Food t) {
        repo.update(t);
    }

    public void updateAll(List<Food> t) {
        repo.update(t);
    }

    public void delete(Food t) {
        repo.delete(t);
        dayFoodRepo.delete(Day_Food.class, t.getId());
        mealFoodRepo.delete(Meal_Food.class, t.getId());
    }

    public void deleteAll(Food t) {
        repo.delete(t);
    }

    public <A extends Activity> void finish(A activity) {
        activity.finish();
    }
}