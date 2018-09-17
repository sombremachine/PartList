package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import test.entity.ComputerComponent;
import test.service.TestService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@Controller - (Слой представления) Аннотация для маркировки java класса, как класса контроллера.
// Данный класс представляет собой компонент, похожий на обычный сервлет (HttpServlet) (работающий с
// объектами HttpServletRequest и HttpServletResponse), но с расширенными возможностями от Spring Framework.
@Controller
public class TestController {
    // Сюда подставится экземпляр класса TestService
    @Autowired
    private TestService service;

    //@RequestMapping - Аннотация используется для маппинга url-адреса запроса на указанный метод или класс.
    // Можно указывать конкретный HTTP-метод, который будет обрабатываться (GET/POST), передавать параметры запроса.
    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public ModelAndView mainscreen() {
        ModelAndView modelAndView = new ModelAndView();
        List<ComputerComponent> components = new ArrayList<>();
        components.addAll(service.getAllComponents());
        modelAndView.addObject("components", components);
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
    public ModelAndView deleteComponent(@PathVariable Integer id) {
        service.deleteById(id);
        ModelAndView modelAndView = new ModelAndView();
        List<ComputerComponent> components = new ArrayList<>();
        components.addAll(service.getAllComponents());
        modelAndView.addObject("components", components);
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @RequestMapping(value={"/component/update"}, method = RequestMethod.POST)
    public ModelAndView updateComponent(@Valid ComputerComponent component, BindingResult bindingResult) {
        service.saveComponent(component);
        ModelAndView modelAndView = new ModelAndView();
        List<ComputerComponent> components = new ArrayList<>();
        components.addAll(service.getAllComponents());
        modelAndView.addObject("components", components);
        modelAndView.setViewName("list");
        return modelAndView;
    }
}
