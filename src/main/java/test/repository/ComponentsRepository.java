package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.entity.ComputerComponent;

import java.util.List;
import java.util.Optional;

//репозиторий это более высокий уровень абстракции чем DAO. Репозитории это из DDD
//@Repository - (Доменный слой) Аннотация показывает, что класс функционирует как репозиторий и требует наличия
// прозрачной трансляции исключений. Преимуществом трансляции исключений является то, что слой сервиса будет иметь
// дело с общей иерархией исключений от Spring (DataAccessException) вне зависимости от используемых технологий доступа
// к данным в слое данных.
@Repository("ComponentsRepository")
public interface ComponentsRepository extends JpaRepository<ComputerComponent, Long> {
    Optional<ComputerComponent> findById(Integer id);
    Integer deleteById(Integer id);
    List<ComputerComponent> findAll();
    //Spring сам формирует запросы в зависимости от имени метода.
    //Но можно указать свой запрос: @Query("select b from Bank b where b.name = :name")

    List<ComputerComponent> findByName(String name);
    List<ComputerComponent> findByPrimary(boolean primary);

}
