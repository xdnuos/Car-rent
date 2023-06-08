package rental.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = -5499172417961772372L;

    public Customer() {
        super();
    }

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(min = 5, max = 60)
    @Column(name = "phone", length = 60)
    private String phone;

    @Size(min = 2, max = 50)
    @Column(name = "fullName", length = 50)
    private String fullName;

    @Size(min = 5, max = 50)
    @Column(name = "role", length = 50)
    private String role;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "total_price")
    private BigDecimal totalPrice = new BigDecimal(0);

    @Column(name = "is_paid")
    private boolean isPaid = false;

    @Column(name = "borrowed_cars")
    private int borrowedCars;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Car> cars = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getBorrowedCars() {
        return borrowedCars;
    }

    public void setBorrowedCars(int borrowedCars) {
        this.borrowedCars = borrowedCars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}