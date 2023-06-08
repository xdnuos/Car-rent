package rental.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rental.entity.AvailableCarsResult;
import rental.entity.BorrowedDate;
import rental.entity.Car;
import rental.service.BorrowedDateService;
import rental.service.CarService;


@Controller

public class Admin {
	
    private CarService carService;
    private BorrowedDateService borrowedDateService;
    private String imgName = "";
    
    String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	
    public Admin(CarService carService, BorrowedDateService borrowedDateService) {
        this.carService = carService;
        this.borrowedDateService = borrowedDateService;
    }
	
    @RequestMapping(value = "admin")
    public String AdminPage(Model model) {
        List<Car> availableCars = carService.showCars();
        model.addAttribute("availableCars", availableCars);
        return "admin";
    }
    
    @RequestMapping(value = "admin",method = RequestMethod.POST)
    public String Search(Model model, @RequestParam(value = "search") String name) {
        List<Car> availableCars = carService.findCarByName(name);
        model.addAttribute("availableCars", availableCars);
        return "admin";
    }
    
    @RequestMapping(value = "admin/addCar")
    public String addCarPage() {
        return "addCar";
    }
    @RequestMapping(value = "admin/addCar",method = RequestMethod.POST)
    public String addCar(Car car, Model model,
    		 @RequestParam(value = "carName") String carName,
    		 @RequestParam(value = "desc") String desc,
    		 @RequestParam(value = "price") BigDecimal price,
    		 SessionStatus status,
    		 @RequestParam("image") MultipartFile image) {
        try {
            imgName = image.getOriginalFilename().replace(image.getOriginalFilename(), FilenameUtils.getBaseName(image.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(image.getOriginalFilename())).toLowerCase();
            File file = new File(this.getFolderUpload(), imgName);
            image.transferTo(file);
          } catch (Exception e) {
            e.printStackTrace();
          }
        
    	car.setName(carName);
    	car.setDescription(desc);
    	car.setPrice(price);
    	car.setImgName(imgName);
    	carService.save(car);
    	status.setComplete();
        return "redirect:/admin";
    }
    
    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.dir")+"/src/main/resources/static/img");
        if (!folderUpload.exists()) {
          folderUpload.mkdirs();
        }
        return folderUpload;
      }
    
    @RequestMapping(value = "admin/editCar{car_id}", method = RequestMethod.GET)
    public String editCar(ModelMap model, @RequestParam(value = "car_id") Long carId) {
        Car carById = carService.findById(carId);
        List<BorrowedDate> listBorrows = borrowedDateService.findAllByCarId(carId);
        model.addAttribute("carById", carById);
        model.addAttribute("listBorrows", listBorrows);
        System.out.println("Listsize: "+listBorrows.size());
        return "editCar";
    }
    
    @RequestMapping(value = "admin/editCar",method = RequestMethod.POST)
    public String editCar(Car car, Model model,
    	 @RequestParam(value = "carID") Long id,
   		 @RequestParam(value = "carName") String carName,
   		 @RequestParam(value = "desc") String desc,
   		 @RequestParam(value = "price") BigDecimal price,
   		 @RequestParam(value = "imgName") String oldImgName,
   		 SessionStatus status,
   		 @RequestParam("image") MultipartFile image) {
		if(image.isEmpty()) {
			imgName = oldImgName;
		}else {
	       try {
	           imgName = image.getOriginalFilename().replace(image.getOriginalFilename(), FilenameUtils.getBaseName(image.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(image.getOriginalFilename())).toLowerCase();
	           File file = new File(this.getFolderUpload(), imgName);
	           image.transferTo(file);
	         } catch (Exception e) {
	           e.printStackTrace();
	         }
		}
    car.setId(id);
   	car.setName(carName);
   	car.setDescription(desc);
   	car.setPrice(price);
   	car.setImgName(imgName);
   	carService.save(car);
   	status.setComplete();
       return "redirect:/admin";
	}
    
    @RequestMapping(value = "admin/deleteCar{car_id}",method = RequestMethod.GET )
    public String deleteCar(@RequestParam(value = "car_id") Long carId, SessionStatus status) {
    	carService.deleteById(carId);
    	status.setComplete();
        return "redirect:/admin";
    }
    
    @RequestMapping(value = "admin/deleteBorrow{borrow_id}")
    public String deleteBorrow(@RequestParam(value = "borrow_id") Long borrowId, SessionStatus status,RedirectAttributes redirectAttributes,
    		@RequestParam(value = "car_id") Long carId) {
    	borrowedDateService.deleteById(borrowId);
    	status.setComplete();
    	redirectAttributes.addAttribute("car_id", carId);
        return "redirect:/admin/editCar";
    }
}
