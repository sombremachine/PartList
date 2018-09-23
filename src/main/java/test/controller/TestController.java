package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import test.entity.ComputerComponent;
import test.service.TestService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Controller - (Слой представления) Аннотация для маркировки java класса, как класса контроллера.
// Данный класс представляет собой компонент, похожий на обычный сервлет (HttpServlet) (работающий с
// объектами HttpServletRequest и HttpServletResponse), но с расширенными возможностями от Spring Framework.
@Controller("TestController")
public class TestController {
    private static final int itemsOnPage = 10;
    // Сюда подставится экземпляр класса TestService
    @Autowired
    private TestService service;

    //@RequestMapping - Аннотация используется для маппинга url-адреса запроса на указанный метод или класс.
    // Можно указывать конкретный HTTP-метод, который будет обрабатываться (GET/POST), передавать параметры запроса.
    @RequestMapping(value={"/","/{pagenum}"}, method = RequestMethod.GET)
    public ModelAndView mainscreen(@PathVariable Optional<Integer> pagenum) {
        ModelAndView modelAndView = new ModelAndView();
        List<ComputerComponent> components = new ArrayList<>();
        if (pagenum.isPresent()) {
            components.addAll(service.getpaged(pagenum.get(), itemsOnPage));
        }else{
            components.addAll(service.getpaged(0, itemsOnPage));
        }
        List<Integer> pages = new ArrayList<>();

        for (int i = 0; i < Math.ceil((float)service.getCount()/itemsOnPage); i++){
            pages.add(i);
        }
        modelAndView.addObject("component", new ComputerComponent());
        modelAndView.addObject("pages", pages);
        modelAndView.addObject("components", components);
        modelAndView.addObject("count", getComputersCount());
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @RequestMapping(value={"/component/edit/{id}"}, method = RequestMethod.GET)
    public ModelAndView editComponent(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        ComputerComponent component = service.findComponentById(id);
        modelAndView.addObject("component", component);
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @Transactional
    @RequestMapping(value={"/component/delete/{id}"}, method = RequestMethod.GET)
    public String deleteComponent(@PathVariable Integer id) {
        service.deleteById(id);
//        ModelAndView modelAndView = new ModelAndView();
//        List<ComputerComponent> components = new ArrayList<>();
//        components.addAll(service.getAllComponents());
//        modelAndView.addObject("components", components);
//        modelAndView.addObject("count", getComputersCount());
//        modelAndView.setViewName("list");
//        return modelAndView;
        return "redirect:/";
    }

    @Transactional
    @RequestMapping(value={"/component/update"}, method = RequestMethod.POST)
    public String updateComponent(@Valid ComputerComponent component, BindingResult bindingResult) {
        service.saveComponent(component);
//        ModelAndView modelAndView = new ModelAndView();
//        List<ComputerComponent> components = new ArrayList<>();
//        components.addAll(service.getAllComponents());
//        modelAndView.addObject("components", components);
//        modelAndView.addObject("count", getComputersCount());
//        modelAndView.setViewName("list");
//        return modelAndView;
        return "redirect:/";
    }

    @Transactional
    @RequestMapping(value={"/component/search"}, method = RequestMethod.POST)
    public ModelAndView searchComponent(@RequestParam(value = "name", required = false) String componentName) {
        ModelAndView modelAndView = new ModelAndView();
        List<ComputerComponent> components = service.fuzzySearch(componentName);
        modelAndView.addObject("searching", componentName);
        modelAndView.addObject("components", components);
        modelAndView.addObject("count", getComputersCount());
        modelAndView.setViewName("list");
        return modelAndView;
    }

    int getComputersCount(){
        return service.getComputersCount();
    }
}
