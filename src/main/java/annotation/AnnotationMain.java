package annotation;

import atm.Bank;
import atm.DataSource;
import atm.ATM;
import atm.ATMSimulator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config-annotation.xml");
        DataSource file = context.getBean(DataSource.class);
        Bank bank = new Bank(file);
        ATM atm = new ATM(bank);
        ATMSimulator atmSimulator = new ATMSimulator(atm);
        atmSimulator.run();
    }
}
