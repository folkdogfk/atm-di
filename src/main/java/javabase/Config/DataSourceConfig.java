package javabase.Config;

import atm.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(){
        return new DataSource("customers.txt");
    }
}
