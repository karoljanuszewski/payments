package pl.sdacademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * http://dominisz.pl
 * 24.11.2017
 */
class HourlyEmployeeTest {

    private HourlyEmployee hourlyEmployee;

    @BeforeEach
    void init() {
        hourlyEmployee = new HourlyEmployee("name", "address", "number", BigDecimal.ONE);
    }

    @Test
    void shouldReturnZeroForNonPaymentDay() {
        BigDecimal payment = hourlyEmployee.calculatePayment(LocalDate.of(2017, 11, 25));
        assertEquals(payment, BigDecimal.ZERO);
    }

    @Test
    void shouldReturnRegularPayment() {
        WorkingDay workingDay = new WorkingDay(LocalDate.of(2017, 11, 24), 8);
        hourlyEmployee.addWorkingDay(workingDay);
        BigDecimal payment = hourlyEmployee.calculatePayment(LocalDate.of(2017, 11, 24));
        assertEquals(payment, BigDecimal.ONE.multiply(new BigDecimal(8)));
    }

    @Test
    void isPaymentDayTest() {
        assertTrue(hourlyEmployee.isPaymentDay(LocalDate.of(2017, 11, 24)), "should return true for Friday");
        assertFalse(hourlyEmployee.isPaymentDay(LocalDate.of(2017, 11, 25)), "should return false when not Friday");
    }

    @Test
    void findFirstDayOfWorkingPeriod() {
        LocalDate firstDay = hourlyEmployee.findFirstDayOfWorkingPeriod(LocalDate.of(2017, 11, 24));
        assertTrue(firstDay.equals(LocalDate.of(2017, 11, 20)));
    }

}