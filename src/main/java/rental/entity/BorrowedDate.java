package rental.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "borrowed_date")
public class BorrowedDate implements Serializable {
    private static final long serialVersionUID = -1713505055304086201L;

    public BorrowedDate() {
        super();
    }
    
    public BorrowedDate(Long id, Calendar startDate, Calendar endDate, Car car, Customer customer) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.car = car;
		this.customer = customer;
	}
    
	public BorrowedDate(Long id, Calendar startDate, Calendar endDate, String customerName) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerName = customerName;
	}

	@Id
    @Column(name = "borrowed_date_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_date")
    private Calendar startDate;

    @Column(name = "end_date")
    private Calendar endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    private String customerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
    
}
