package com.C10G14.WorldFitBackend.dto.exercise;

import com.C10G14.WorldFitBackend.entity.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercise_RoutineResponseDto {

    private long id;
    private String title;
    private String description;
    private String media;
    private String type;
    private String unit;
    private int quantity;
    private int repetitions;
    private int seriesNumber;
    private List series;

    public Exercise_RoutineResponseDto(Exercise exercise, int quantity, int repetitions, int seriesNumber) {

        this.id = exercise.getId();
        this.title = exercise.getTitle();
        this.description = exercise.getDescription();
        this.media = exercise.getMedia();
        //todo corregir desde el front
        String unitName = exercise.getUnit().getName().toString();
        if (unitName.equals("None")){unitName = "";}
        this.unit = unitName;
        this.type = (this.unit.equals("Km"))? "Distancia" :
                    (this.unit.equals("Kg"))? "Peso" :
                    (this.unit.equals("Minutos"))? "Tiempo" : "";
        this.quantity = quantity;
        this.repetitions = repetitions;
        this.seriesNumber = seriesNumber;
        this.series = new ArrayList<>();
        for (int i = 0; i<seriesNumber; i++) {
            series.add(new Object());
        }
    }

}
