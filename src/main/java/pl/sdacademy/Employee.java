package pl.sdacademy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * http://dominisz.pl
 * 23.11.2017
 */
@Data
@AllArgsConstructor
public abstract class Employee {

    private String name;
    private String address;
    private String bankAccountNumber;

    public abstract boolean isPaymentDay(LocalDate day);

    public abstract BigDecimal calculatePayment(LocalDate day);

}
