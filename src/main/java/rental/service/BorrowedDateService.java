package rental.service;

import java.util.Calendar;
import java.util.List;

import rental.entity.Car;
import rental.entity.BorrowedDate;

public interface BorrowedDateService {

    List<Car> checkAvailableCarById(Calendar startDate, Calendar endDate, Long id);

    List<Car> checkAvailableCars(Calendar startDate, Calendar endDate);

    List<BorrowedDate> findAll();
    
    List<BorrowedDate> findAllByCarId(Long id);

    BorrowedDate findByCustomerId(Long id);

    BorrowedDate findByCarId(Long id);
    
    void deleteById(Long id);

    void save(BorrowedDate borrowedDate);

    long countDays(BorrowedDate borrowedDate);
}
