package rental.controller;

import rental.entity.AvailableCarsResult;
import rental.entity.Car;
import rental.service.BorrowedDateService;
import rental.service.CarService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;

@Controller
public class Index {

    private BorrowedDateService borrowedDateService;
    private CarService carService;
    
    public Index(BorrowedDateService borrowedDateService, CarService carService) {
        this.borrowedDateService = borrowedDateService;
        this.carService = carService;
    }
    @RequestMapping(value = "/getcar", method = RequestMethod.GET)
    public String index(Model model,
            @RequestParam(value = "start_date", defaultValue = "1800-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
            @RequestParam(value = "end_date", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {
        List<Car> availableCars = borrowedDateService.checkAvailableCars(startDate, endDate);
        model.addAttribute("availableCars", availableCars);
        return "index";
    }
    
    @RequestMapping(value = "/")
    public String index(Model model) {
        List<Car> availableCars = carService.showCars();
        model.addAttribute("availableCars", availableCars);
        return "index";
    }
    
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String Search(Model model, @RequestParam(value = "search") String name) {
        List<Car> availableCars = carService.findCarByName(name);
        model.addAttribute("availableCars", availableCars);
        return "index";
    }
}
