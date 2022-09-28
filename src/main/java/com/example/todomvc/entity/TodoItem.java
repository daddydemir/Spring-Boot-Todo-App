package com.example.todomvc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todos")
@ApiModel(value = "Todo nesnesi")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty
    private int userId;

    @NotBlank
    @ApiModelProperty
    private String title;

    @ApiModelProperty
    private boolean completed;

    public TodoItem(String title, boolean completed){
        this.title = title;
        this.completed = completed;
    }
}
