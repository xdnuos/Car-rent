package rental.service;

import rental.entity.Car;
import rental.repository.CarDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDAO carDAO;

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public void save(Car car) {
        this.carDAO.save(car);
    }

    @Override
    public List<Car> newCars() {
        return this.carDAO.newCars();
    }

    @Override
    public List<Car> findAll() {
        return this.carDAO.findAll();
    }

    @Override
    public Car findById(Long id) {
        return this.carDAO.findById(id);
    }
    
    @Override
    public void deleteById(Long id) {
    	this.carDAO.delete(id);
    }
    
    @Override
    public List<Car> showCars() {
        return this.carDAO.showCars();
    }
    
    @Override
    public List<Car> findCarByName(String name) {
        return this.carDAO.findCarByName(name);
    }

}
