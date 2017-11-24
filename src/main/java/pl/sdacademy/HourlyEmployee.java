package pl.sdacademy;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public boolean isPaymentDay(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }

    @Override
    public BigDecimal calculatePayment(LocalDate date) {
        if (!isPaymentDay(date)) {
            return BigDecimal.ZERO;
        } else {
            LocalDate firstDayOfWorkingPeriod = findFirstDayOfWorkingPeriod(date);
            List<WorkingDay> workingDays = findWorkingDays(firstDayOfWorkingPeriod, date);
            return calculatePayment(workingDays);
        }
    }

    private BigDecimal calculatePayment(List<WorkingDay> workingDays) {
        BigDecimal payment = BigDecimal.ZERO;
        for (WorkingDay workingDay : workingDays) {
            payment = payment.add(calculatePayment(workingDay));
        }
        return payment;
    }

    private BigDecimal calculatePayment(WorkingDay workingDay) {
        if (workingDay.getHours() <= REGULAR_HOURS) {
            return hourlyRate.multiply(new BigDecimal(workingDay.getHours()));
        } else {
            BigDecimal regularPayment
                    = hourlyRate.multiply(new BigDecimal(REGULAR_HOURS));
            BigDecimal overtimeRate
                    = hourlyRate.multiply(OVERTIME_RATE);
            BigDecimal overtimePayment
                    = overtimeRate.multiply(new BigDecimal(workingDay.getHours() - REGULAR_HOURS));
            return regularPayment.add(overtimePayment);
        }
    }

    private List<WorkingDay> findWorkingDays(LocalDate firstDay, LocalDate lastDay) {
        return workingDays.stream()
                .filter(workingDay -> workingDay.betweenDays(firstDay, lastDay))
                .collect(Collectors.toList());
    }

    @Override
    public LocalDate findFirstDayOfWorkingPeriod(LocalDate paymentDate) {
        return paymentDate.minusDays(4);
    }

}
