package rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rental.entity.Car;

import java.util.List;

@Repository
public interface CarDAO extends JpaRepository<Car, Long> {

    @Query("from Car as c where c.id not in (select b.car.id from BorrowedDate as b)")
    List<Car> newCars();

    List<Car> findAll();

    Car findById(Long id);
    
    @Query("select NEW rental.entity.Car "+
            "(c.id, c.name, c.description, c.price, c.imgName) "+
            "from Car as c")
    List<Car> showCars();
    
    @Query("select NEW rental.entity.Car "+
            "(c.id, c.name, c.description, c.price, c.imgName) "+
            "from Car as c where c.name LIKE %:name%")
    List<Car> findCarByName(@Param("name") String name);
}
