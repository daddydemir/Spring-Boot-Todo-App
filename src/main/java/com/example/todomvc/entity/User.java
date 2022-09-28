package com.example.todomvc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "users")
@ApiModel(value = "User nesnesi")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty
    private int id;

    @NotBlank
    @ApiModelProperty
    private String name;

    @NotBlank
    @ApiModelProperty
    private String surname;

    @NotBlank
    @ApiModelProperty
    private String password;

    @NotBlank
    @ApiModelProperty
    private String email;
}
