package com.lowelostudents.caloriecounter.models.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Transaction;

import com.lowelostudents.caloriecounter.models.CRUDDao;
import com.lowelostudents.caloriecounter.models.relations.Day_Food_Relation;
import com.lowelostudents.caloriecounter.models.relations.Day_Meal_Relation;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import lombok.Data;

@Entity
@Data
public class Day {
    @PrimaryKey
    private int dayId;
    private String name;

    public Day() {
        Calendar cal = Calendar.getInstance();
        this.dayId = cal.get(Calendar.DATE);
        this.name = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    @Dao
    public abstract static class DayDao extends CRUDDao<Day> {
        @Query("SELECT * FROM Day WHERE dayId = (SELECT MAX(dayId) FROM Day)")
        public abstract Day getLatest();

        @Query("SELECT * FROM Day")
        public abstract List<Day_Food_Relation> getFoodsPerDay();

        @Query("SELECT * FROM Day WHERE dayId = :date")
        public abstract Day_Food_Relation getFoodByDate(int date);

        @Query("SELECT * FROM Day")
        public abstract List<Day_Meal_Relation> getMealsPerDay();

        @Query("SELECT * FROM Day WHERE dayId = :date")
        public abstract Day_Meal_Relation getMealByDate(int date);

        @Query("SELECT * FROM Day")
        public abstract LiveData<List<Day>> getAllObservable();

        @Query("SELECT * FROM Day WHERE dayId = :date")
        public abstract LiveData<Day> getObservableByDate(int date);

        @Transaction
        @Query("SELECT * FROM Food INNER JOIN Day_Food ON Food.id = Day_Food.id WHERE Day_Food.dayId = :date")
        public abstract LiveData<List<Food>> getObservableFoodByDate(int date);

        @Transaction
        @Query("SELECT * FROM Day")
        public abstract LiveData<List<Day_Food_Relation>> getObservableFoodsPerDate();

        @Transaction
        @Query("SELECT * FROM Meal INNER JOIN Day_Meal ON Meal.id = Day_Meal.id WHERE Day_Meal.dayId = :date")
        public abstract LiveData<List<Meal>> getObservableMealByDate(int date);

        @Transaction
        @Query("SELECT * FROM Day")
        public abstract LiveData<List<Day_Meal_Relation>> getObservableMealsPerDate();
    }
}
