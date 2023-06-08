package rental.service;

import rental.entity.BorrowedDate;
import rental.entity.Car;
import rental.entity.Customer;

public interface MailService {

    void sendMailTest();

    void sendMail(Customer customer, BorrowedDate borrowedDate, Car car);
}
