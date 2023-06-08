package rental.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "car")
public class Car implements Serializable {
    private static final long serialVersionUID = 7034352443015914334L;

    public Car() {
        super();
    }
    

    public Car(Long id, String name, String description, BigDecimal price, String imgName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgName = imgName;
	}


	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(min = 20, max = 300)
    @Column(name = "description", length = 300)
    private String description;

    @Digits(integer = 9, fraction = 3)
    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    private List<Customer> customers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<BorrowedDate> borrowedDates = new ArrayList<>();
    
    @Column(name = "imgName", length = 50)
    private String imgName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<BorrowedDate> getBorrowedDates() {
        return borrowedDates;
    }

    public void setBorrowedDates(List<BorrowedDate> borrowedDates) {
        this.borrowedDates = borrowedDates;
    }

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
    
    
}
