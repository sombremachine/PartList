package test.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data  // Это аннотация Lombok. Позволяет автоматически генерировать геттеры и сеттеры
@Entity // Снова из DDD
@Table(name = "components")
public class ComputerComponent {

    // тут указываем соотношения полей и столбцов таблицы

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide a name for configuration")
    private String name;

    @Column(name = "count")
    private Integer count;

    @Column(name = "isPrimary")
    private Boolean primary;
}
