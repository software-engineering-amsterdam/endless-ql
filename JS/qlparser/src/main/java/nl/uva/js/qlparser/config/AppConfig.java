package nl.uva.js.qlparser.config;

import nl.uva.js.qlparser.ui.panes.LogPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("nl.uva.js.qlparser")
public class AppConfig {

//    @Autowired
//    private LogPane logPane;

//    @Bean
//    public LogPane logPane(){
//        return new LogPane();
//    }
}
