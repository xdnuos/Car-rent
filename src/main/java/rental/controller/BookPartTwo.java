package rental.controller;

import rental.entity.BorrowedDate;
import rental.entity.Car;
import rental.entity.Customer;
import rental.service.BorrowedDateService;
import rental.service.CarService;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({ "customer", "borrowedDate" })
public class BookPartTwo {

    private CarService carService;
    private BorrowedDateService borrowedDateService;

    public BookPartTwo(CarService carService, BorrowedDateService borrowedDateService) {
        this.carService = carService;
        this.borrowedDateService = borrowedDateService;
    }

    @RequestMapping(value = "bookPartTwo{car_id}", method = RequestMethod.GET)
    public String showSessionCar(Model model, @RequestParam(value = "car_id") Long carId) {
        Car carById = carService.findById(carId);
        model.addAttribute("carById", carById);
        return "bookPartTwo";
    }

    @RequestMapping(value = "bookPartTwo", method = RequestMethod.POST)
    public String completeCustomer(Customer customer, BorrowedDate borrowedDate, RedirectAttributes redirectAttributes,
            @RequestParam(value = "car_id") Long CarId,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "email") String email) {
        long days = borrowedDateService.countDays(borrowedDate);
        customer.setTotalPrice(customer.getTotalPrice().multiply(new BigDecimal(days)));
        customer.setRole("ROLE_USER");
        customer.setPhone(phone);
        customer.setEmail(email);
        redirectAttributes.addAttribute("car_id", CarId);
        return "redirect:/bookResume";
    }
}
