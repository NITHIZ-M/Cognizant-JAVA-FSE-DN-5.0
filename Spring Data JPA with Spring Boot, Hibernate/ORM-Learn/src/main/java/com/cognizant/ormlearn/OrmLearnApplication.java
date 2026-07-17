package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.*;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.*;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    // Services & Repositories
    private static CountryService countryService;
    private static CountryRepository countryRepository;
    private static StockRepository stockRepository;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;
    private static AttemptService attemptService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        // Initialize all beans
        countryService = context.getBean(CountryService.class);
        countryRepository = context.getBean(CountryRepository.class);
        stockRepository = context.getBean(StockRepository.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);
        attemptService = context.getBean(AttemptService.class);

        // --- UNCOMMENT THE TEST YOU WANT TO RUN ---

        // File 1 - Country CRUD
         testGetAllCountries();
         testFindCountryByCode();
         testAddCountry();
         testUpdateCountry();
         testDeleteCountry();

        // File 2 - Query Methods
         testCountryQueryMethods();
         testStockQueryMethods();

        // File 2 - O/R Mapping
         testGetEmployee();
         testGetDepartment();
         testGetEmployeeWithSkills();
         testAddSkillToEmployee();

        // File 3 - HQL & Native Queries
         testGetAllPermanentEmployees();
         testGetAverageSalary();
         testGetAllEmployeesNative();

        // File 3 - Quiz Attempt (HQL)
         testGetAttempt();
    }

    // ================================================
    // FILE 1: COUNTRY CRUD TESTS
    // ================================================

    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    private static void testFindCountryByCode() {
        LOGGER.info("Start");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country found: {}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testAddCountry() {
        LOGGER.info("Start");
        Country country = new Country();
        country.setCode("AU");
        country.setName("Australia");
        countryService.addCountry(country);
        try {
            Country saved = countryService.findCountryByCode("AU");
            LOGGER.debug("Added country: {}", saved);
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start");
        try {
            countryService.updateCountry("AU", "Commonwealth of Australia");
            Country updated = countryService.findCountryByCode("AU");
            LOGGER.debug("Updated country: {}", updated);
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("AU");
        try {
            countryService.findCountryByCode("AU");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country deleted successfully (not found): {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    // ================================================
    // FILE 2: QUERY METHODS TESTS
    // ================================================

    private static void testCountryQueryMethods() {
        LOGGER.info("Start");

        List<Country> containsOu = countryRepository.findByNameContainingIgnoreCaseOrderByNameAsc("ou");
        LOGGER.debug("Countries containing 'ou' (sorted):");
        containsOu.forEach(c -> LOGGER.debug("  {} - {}", c.getCode(), c.getName()));

        List<Country> startsWithZ = countryRepository.findByNameStartingWithIgnoreCase("Z");
        LOGGER.debug("Countries starting with 'Z':");
        startsWithZ.forEach(c -> LOGGER.debug("  {} - {}", c.getCode(), c.getName()));

        LOGGER.info("End");
    }

    private static void testStockQueryMethods() {
        LOGGER.info("Start");

        Calendar cal = Calendar.getInstance();
        cal.set(2019, 8, 1);
        Date start = cal.getTime();
        cal.set(2019, 8, 30);
        Date end = cal.getTime();

        List<Stock> fbSept = stockRepository.findByCodeAndDateBetweenOrderByDateAsc("FB", start, end);
        LOGGER.debug("Facebook Sept 2019 ({} records):", fbSept.size());
        fbSept.forEach(s -> LOGGER.debug("  {} | {} | {}", s.getDate(), s.getOpen(), s.getClose()));

        List<Stock> googHigh = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", BigDecimal.valueOf(1250));
        LOGGER.debug("Google close > 1250 ({} records):", googHigh.size());
        googHigh.forEach(s -> LOGGER.debug("  {} | {}", s.getDate(), s.getClose()));

        List<Stock> topVolume = stockRepository.findTop3ByOrderByVolumeDesc();
        LOGGER.debug("Top 3 volumes:");
        topVolume.forEach(s -> LOGGER.debug("  {} | {} | {}", s.getCode(), s.getDate(), s.getVolume()));

        List<Stock> nflxLow = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        LOGGER.debug("Lowest 3 NFLX closes:");
        nflxLow.forEach(s -> LOGGER.debug("  {} | {}", s.getDate(), s.getClose()));

        LOGGER.info("End");
    }

    // ================================================
    // FILE 2: O/R MAPPING TESTS
    // ================================================

    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());
        LOGGER.info("End");
    }

    private static void testGetDepartment() {
        LOGGER.info("Start");
        Department department = departmentService.get(1);
        LOGGER.debug("Department: {}", department);
        LOGGER.debug("Employees in this department:");
        department.getEmployeeList().forEach(e -> LOGGER.debug("  {}", e));
        LOGGER.info("End");
    }

    private static void testGetEmployeeWithSkills() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Skills:");
        employee.getSkillList().forEach(s -> LOGGER.debug("  {}", s));
        LOGGER.info("End");
    }

    private static void testAddSkillToEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        Skill skill = skillService.get(3);
        employee.getSkillList().add(skill);
        employeeService.save(employee);
        LOGGER.debug("Employee after adding Python: {}", employee);
        LOGGER.debug("Skills: {}", employee.getSkillList());
        LOGGER.info("End");
    }

    // ================================================
    // FILE 3: HQL & NATIVE QUERIES TESTS
    // ================================================

    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        employees.forEach(e -> {
            LOGGER.debug("Employee: {}", e);
            LOGGER.debug("  Dept: {}", e.getDepartment());
            LOGGER.debug("  Skills: {}", e.getSkillList());
        });
        LOGGER.info("End");
    }

    private static void testGetAverageSalary() {
        LOGGER.info("Start");
        double avg = employeeService.getAverageSalary(1);
        LOGGER.debug("Average salary for department 1: {}", avg);
        LOGGER.info("End");
    }

    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        employees.forEach(e -> LOGGER.debug("Native Employee: {}", e));
        LOGGER.info("End");
    }

    // ================================================
    // FILE 3: QUIZ ATTEMPT (HQL) TEST
    // ================================================

    private static void testGetAttempt() {
        LOGGER.info("Start");
        Attempt attempt = attemptService.getAttempt(1, 1);
        LOGGER.debug("User: {}", attempt.getUser().getName());
        LOGGER.debug("Attempt Date: {}", attempt.getDate());
        LOGGER.debug("Score: {}", attempt.getScore());   // BigDecimal prints fine

        attempt.getAttemptQuestions().forEach(aq -> {
            LOGGER.debug("Question: {}", aq.getQuestion().getText());
            aq.getAttemptOptions().forEach(ao -> {
                LOGGER.debug("  Option: {} | Score: {} | Selected: {}",
                        ao.getOption().getText(),
                        aq.getQuestion().getScore(),         // BigDecimal
                        ao.getOption().isCorrect() ? "true" : "false"
                );
            });
        });
        LOGGER.info("End");
    }
}