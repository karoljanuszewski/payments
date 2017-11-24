package pl.sdacademy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * http://dominisz.pl
 * 23.11.2017
 */
public class HourlyEmployee extends Employee {

    private static final int REGULAR_HOURS = 8;
    private static final BigDecimal OVERTIME_RATE = new BigDecimal("1.5");

    private BigDecimal hourlyRate;
    private List<WorkingDay> workingDays;

    public HourlyEmployee(String name, String address, String bankAccountNumber, BigDecimal hourlyRate) {
        super(name, address, bankAccountNumber);
        this.hourlyRate = hourlyRate;
        workingDays = new LinkedList<>();
    }

    @Override
    public boolean isPaymentDay(LocalDate day) {
        return false;
    }

    @Override
    public BigDecimal calculatePayment(LocalDate day) {
        return null;
    }
}
