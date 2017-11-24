package pl.sdacademy;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * http://dominisz.pl
 * 23.11.2017
 */
public class MonthlyEmployee extends Employee {

    public MonthlyEmployee(String name, String address, String bankAccountNumber) {
        super(name, address, bankAccountNumber);
    }

    @Override
    public boolean isPaymentDay(LocalDate day) {
        return false;
    }

    @Override
    public BigDecimal calculatePayment(LocalDate day) {
        return null;
    }

    @Override
    public LocalDate findFirstDayOfWorkingPeriod(LocalDate paymentDate) {
        return null;
    }
}
