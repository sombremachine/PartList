package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.entity.ComputerComponent;

//репозиторий это более высокий уровень абстракции чем DAO. Репозитории это из DDD
@Repository("ComponentsRepository")
public interface ComponentsRepository extends JpaRepository<ComputerComponent, Long> {
    //Spring сам формирует запросы в зависимости от имени метода.
    //Но можно указать свой запрос: @Query("select b from Bank b where b.name = :name")

}
