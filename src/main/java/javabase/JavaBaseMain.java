package javabase;

import atm.Bank;
import atm.DataSource;
import atm.ATM;
import atm.ATMSimulator;
import javabase.Config.DataSourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBaseMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        DataSource file = context.getBean(DataSource.class);
        Bank bank = new Bank(file);
        ATM atm = new ATM(bank);
        ATMSimulator atmSimulator = new ATMSimulator(atm);
        atmSimulator.run();
    }
}
