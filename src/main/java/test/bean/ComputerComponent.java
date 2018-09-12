package test.bean;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Components")
public class ComputerComponent {

    // тут указываем соотношения полей и столбцов таблицы

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comp_id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide a name for configuration")
    private String name;

    @Column(name = "manufacturer")
    @NotEmpty(message = "*Please provide a manufacturer")
    private String manufacturer;

    @Column(name = "count")
    @NotEmpty(message = "*Please provide count")
    private int count;

    @Column(name = "primary")
    private boolean primary;

    // геттеры и сеттеры Spring может подставить сам
}
