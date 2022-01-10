package com.lowelostudents.caloriecounter.ui.foodhub;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lowelostudents.caloriecounter.R;
import com.lowelostudents.caloriecounter.models.Food;
import com.lowelostudents.caloriecounter.models.Meal;

import java.util.Arrays;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    private List<?> dataSet;
    private LayoutInflater layoutInflater;

    // data is passed into the constructor
    FoodListAdapter(Context context, List<?> foodList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataSet = foodList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.foodhub_card, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object data = dataSet.get(position);
        Class<?> dataClass = data.getClass();

        holder.cardType.setText(dataClass.getSimpleName());

        if(dataClass == Food.class)
            init((Food) data, holder);

        if(dataClass == Meal.class)
            init((Meal) data, holder);
    }

    private void init(Food food, ViewHolder holder) {
        holder.cardTitle.setText(food.getName());
        String nutrients = food.getGramTotal() + "g" + " / " + (food.getCalTotal() + "cal");
        holder.cardNutrients.setText(nutrients);
    }

    private void init(Meal meal, ViewHolder holder){
        holder.cardTitle.setText(meal.getName());
        String nutrients = meal.getGramTotal() + "g" + " / " + (meal.getCalTotal()) + "cal";
        holder.cardNutrients.setText(nutrients);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardItem;
        TextView cardTitle, cardType, cardNutrients;
        ImageButton cardToggleForDay;

        ViewHolder(View itemView) {
            super(itemView);
            cardItem = itemView.findViewById(R.id.foodItem);
            cardTitle = itemView.findViewById(R.id.cardTitle);
            cardType = itemView.findViewById(R.id.cardType);
            cardNutrients = itemView.findViewById(R.id.cardNutrients);
            cardToggleForDay = itemView.findViewById(R.id.toggleForDay);

            cardItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.i("DIESE FUNKTIONIERT", "DIESE FUNKTIONIERT CARDVIEW");
                }
            });

            cardToggleForDay.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Log.i("DIESE AUCH FUNKTIONIERT", "DIESE FUNKTIONIERT ADDBUTTON");
                }
            });
        }
    }

//        // convenience method for getting data at click position
//        String getItem(int id) {
//            return mData.get(id);
//        }

}
