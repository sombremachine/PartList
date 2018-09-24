package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.entity.ComputerComponent;
import test.repository.ComponentsRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//(Сервис-слой приложения) Аннотация, объявляющая, что этот класс представляет собой сервис – компонент сервис-слоя.
//Сервис является подтипом класса @Component. Использование данной аннотации позволит искать бины-сервисы автоматически.

@Service("TestService")
public class TestService {
    @Autowired
    private ComponentsRepository repository;

    @Autowired
    public TestService(ComponentsRepository repository) {
        this.repository = repository;
    }

    public List<ComputerComponent> getAllComponents(){
//        return repository.findAllByOderByNeed();
        return null;
    }

    public ComputerComponent findComponentById(Integer id) {
        return repository.findById(id).get();
    }
    public Integer deleteById(Integer id) {
        return repository.deleteById(id);
    }

    public void saveComponent(ComputerComponent component) {
        repository.save(component);
    }

    public List<ComputerComponent> fuzzySearch(String searchTerm) {
        return repository.findByName(searchTerm);
    }

    public int getComputersCount() {
        List<ComputerComponent> result = repository.findByNeed(true);
        int count = 0;
        if ((result != null)&&(!result.isEmpty())) {
            count = result.stream().min((a, b) -> Integer.compare(a.getCount(), b.getCount())).get().getCount();
        }
        return count;
    }

    public List<ComputerComponent> getpaged(int i, int i1, Sort.Direction sortDirection) {
        List<ComputerComponent> result = new ArrayList<>();
        if (sortDirection != null) {
            repository.findAll(new PageRequest(i, i1, sortDirection, "need", "id")).forEach((c) -> result.add(c));
        }else{
            repository.findAll(new PageRequest(i, i1, Sort.Direction.ASC,  "id")).forEach((c) -> result.add(c));
        }
        return result;
    }

    public long getCount(){
        return repository.count();
    }
}
