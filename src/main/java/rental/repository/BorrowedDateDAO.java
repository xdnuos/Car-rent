package rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rental.entity.Car;
import rental.entity.BorrowedDate;

import java.util.Calendar;
import java.util.List;

@Repository
public interface BorrowedDateDAO extends JpaRepository<BorrowedDate, Long> {
    BorrowedDate findByCustomerId(Long id);
    BorrowedDate findByCarId(Long id);
    List<BorrowedDate> findAll();
    @Query("select NEW rental.entity.Car "+
            "(c.id, max(c.name), max(c.description), max(c.price), max(c.imgName)) "+
            "from Car as c "+
            "where c.id NOT IN (select DISTINCT bd.car.id "+
	        "from BorrowedDate bd "+
	        "where :startDate between bd.startDate and bd.endDate "+
	        "OR :endDate between bd.startDate and bd.endDate) " +
	        "group by c.id")
    List<Car> checkAvailableCars(@Param("startDate") Calendar startDate,
                                                 @Param("endDate") Calendar endDate);
  
    
    @Query("select NEW rental.entity.Car "+
            "(b.car.id, b.car.name, b.car.description, b.car.price, b.car.imgName) "+
            "from BorrowedDate as b "+
            "where (:startDate between b.startDate and b.endDate "+
            "OR :endDate between b.startDate and b.endDate) "+
            "and b.car.id = :carId")
    List<Car> checkAvailableCarById(@Param("startDate") Calendar startDate,
                                                    @Param("endDate") Calendar endDate,
                                                    @Param("carId") Long id);
    
    @Query("select NEW rental.entity.BorrowedDate "+
            "(b.id, b.startDate, b.endDate, b.customer.fullName) "+
            "from BorrowedDate as b "+
            "where b.car.id = :carId)")
    List<BorrowedDate> findAllByCarId(@Param("carId") Long id);
}
