package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.ComputerComponent;
import test.repository.ComponentsRepository;

import java.util.List;

//(Сервис-слой приложения) Аннотация, объявляющая, что этот класс представляет собой сервис – компонент сервис-слоя.
//Сервис является подтипом класса @Component. Использование данной аннотации позволит искать бины-сервисы автоматически.

@Service("TestService")
public class TestService {
    private ComponentsRepository repository;

    @Autowired
    public TestService(ComponentsRepository repository) {
        this.repository = repository;
    }

    public List<ComputerComponent> getAllComponents(){
        return repository.findAll();
    }

}
