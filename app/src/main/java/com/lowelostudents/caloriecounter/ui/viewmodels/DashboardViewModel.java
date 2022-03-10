package com.lowelostudents.caloriecounter.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lowelostudents.caloriecounter.data.repositories.DayFoodRepo;
import com.lowelostudents.caloriecounter.data.repositories.DayRepo;
import com.lowelostudents.caloriecounter.data.repositories.FoodRepo;
import com.lowelostudents.caloriecounter.models.entities.Day_Meal;
import com.lowelostudents.caloriecounter.models.entities.Food;
import com.lowelostudents.caloriecounter.models.entities.Meal;
import com.lowelostudents.caloriecounter.models.relations.Day_Food_Relation;
import com.lowelostudents.caloriecounter.models.relations.Day_Meal_Relation;

import java.util.List;

import lombok.Getter;

public class DashboardViewModel extends AndroidViewModel {

    private final DayRepo repo;
    @Getter
    private final LiveData<List<Food>> dayFoods;
    @Getter
    private final LiveData<List<Meal>> dayMeals;

    public DashboardViewModel(Application context) {
        super(context);
        repo = new DayRepo(context.getApplicationContext());
        dayFoods = repo.getDay_foods();
        dayMeals = repo.getDay_meals();
    }
}