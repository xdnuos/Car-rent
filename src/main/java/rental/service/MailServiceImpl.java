package rental.service;

import com.google.common.collect.Lists;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import rental.entity.BorrowedDate;
import rental.entity.Car;
import rental.entity.Customer;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
@Service
public class MailServiceImpl implements MailService {
    private EmailService emailService;
    private CustomerService customerService;

    public MailServiceImpl(EmailService emailService, CustomerService customerService) {
        this.emailService = emailService;
        this.customerService = customerService;
    }

    @Override
    public void sendMail(Customer customer, BorrowedDate borrowedDate, Car car) {
        try {
        	Calendar start = ( Calendar ) borrowedDate.getStartDate();
        	Calendar end = ( Calendar ) borrowedDate.getEndDate();
        	Date start_Date = start.getTime();
        	Date end_Date = end.getTime();
        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            final Email email = DefaultEmail.builder().from(new InternetAddress("goloshe61@gmail.com"))
                    .to(Lists.newArrayList(new InternetAddress(customer.getEmail())))
                    .subject("Thuexe.com - Xác nhận thuê xe")
                    .body("Xin chào, " + customer.getFullName() + "\n\nCảm ơn bạn đã sử dụng dịch vụ của chúng tôi."
                            + "\nThông tin thuê xe:" + "\nLoại xe: " + car.getName() + ", giá: "
                            + car.getPrice() + " VNĐ/ngày" + "\nThời gian thuê: " + sdf.format( start_Date )
                            + " -> " + sdf.format( end_Date ) + "\nTổng cộng: " + customer.getTotalPrice() + " VNĐ")
                    .encoding("UTF-8").build();
            emailService.send(email);
        } catch (AddressException e) {
            System.out.println(e.getMessage());
        }

    }

    public void sendMailTest() {
        try {
            final Email email = DefaultEmail.builder().from(new InternetAddress("goloshe61@gmail.com"))
                    .to(Lists.newArrayList(new InternetAddress("xdnuos@gmail.com")))
                    .subject("Thuexe.com - Xác nhận thuê xe").body("Xin chào, ").encoding("UTF-8").build();
            emailService.send(email);
        } catch (AddressException e) {
            System.out.println(e.getMessage());
        }

    }
}
