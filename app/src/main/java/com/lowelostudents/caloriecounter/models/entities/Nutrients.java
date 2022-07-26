package com.lowelostudents.caloriecounter.models.entities;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

// TODO add Protein etc // TODO MAP JSON RESPONSE AND AUTOFILL DATA VIA 3 WAY BINDING TO USER FACE

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Nutrients implements Serializable {
    protected boolean isAggregation = false;

    @Ignore
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    protected String name;
    @SerializedName("energy-kcal_100g")
    protected double calTotal = 0;
    // TODO actual quantity
    @SerializedName("product-quantity")
    protected double gramTotal = 0;
    @SerializedName("carbohydrates_100g")
    // TODO rename grams to gramPortion
    protected double carbsGram = 0;
    @SerializedName("fat_100g")
    protected double fatGram = 0;
    @SerializedName("proteins_100g")
    protected double proteinGram = 0;

    protected double carbsCal = 0;
    protected double proteinCal = 0;
    protected double fatCal = 0;


    protected double portionSize = 0;
    protected double calPerPortion = 0;
    protected double calPerGram = 0;
}
