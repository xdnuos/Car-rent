package rental.service;

import rental.entity.Car;
import rental.entity.BorrowedDate;
import rental.repository.BorrowedDateDAO;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BorrowedDateServiceImpl implements BorrowedDateService {

    private BorrowedDateDAO borrowedDateDAO;

    public BorrowedDateServiceImpl(BorrowedDateDAO borrowedDateDAO) {
        this.borrowedDateDAO = borrowedDateDAO;
    }

    @Override
    public BorrowedDate findByCustomerId(Long id) {
        return this.borrowedDateDAO.findByCustomerId(id);
    }

    @Override
    public BorrowedDate findByCarId(Long id) {
        return this.borrowedDateDAO.findByCarId(id);
    }
  

    @Override
    public List<BorrowedDate> findAll() {
        return this.borrowedDateDAO.findAll();
    }
    
    @Override
    public List<BorrowedDate> findAllByCarId(Long id) {
        return this.borrowedDateDAO.findAllByCarId(id);
    }

    @Override
    public void save(BorrowedDate borrowedDate) {
        this.borrowedDateDAO.save(borrowedDate);
    }
    
    @Override
    public void deleteById(Long id) {
        this.borrowedDateDAO.delete(id);
    }

    @Override
    public List<Car> checkAvailableCars(Calendar startDate, Calendar endDate) {
        return this.borrowedDateDAO.checkAvailableCars(startDate, endDate);
    }

    @Override
    public List<Car> checkAvailableCarById(Calendar startDate, Calendar endDate, Long id) {
        return this.borrowedDateDAO.checkAvailableCarById(startDate, endDate, id);
    }
   
    @Override
    public long countDays(BorrowedDate borrowedDate) {
        long days;
        Calendar start = borrowedDate.getStartDate();
        Calendar end = borrowedDate.getEndDate();
        days = daysBetween(start, end);
        return days;
    }

    private long daysBetween(Calendar startDate, Calendar endDate) {
        endDate.add(Calendar.DATE, 1);
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }
}
