package rental.controller;

import rental.entity.Car;
import rental.service.CarService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarDetail {

    private CarService carService;

    public CarDetail(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/carDetail{car_id}", method = RequestMethod.GET)
    public String carDetail(Model model, @RequestParam(value = "car_id") Long carId) {
        Car carById = carService.findById(carId);
        model.addAttribute("carById", carById);
        return "carDetail";
    }
}