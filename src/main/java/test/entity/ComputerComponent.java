package test.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data  // Это аннотация Lombok. Позволяет автоматически генерировать геттеры и сеттеры
@Entity // Снова из DDD
@Table(name = "components")
public class ComputerComponent {

    // тут указываем соотношения полей и столбцов таблицы

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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

    @Column(name = "isPrimary")
    private boolean primary;
}
