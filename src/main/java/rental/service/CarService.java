package rental.service;

import java.util.List;

import rental.entity.AvailableCarsResult;
import rental.entity.Car;

public interface CarService {
    List<Car> findAll();

    List<Car> newCars();

    Car findById(Long id);

    void save(Car car);
    
    void deleteById(Long id);
    
    List<Car> showCars();
    
    List<Car> findCarByName(String name);
}
