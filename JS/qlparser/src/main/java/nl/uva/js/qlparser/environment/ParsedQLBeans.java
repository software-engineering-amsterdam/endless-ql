package nl.uva.js.qlparser.environment;

import nl.uva.js.qlparser.logic.QLIngester;
import nl.uva.js.qlparser.models.Form;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@Configuration
@PropertySource("classpath:application.properties")
public class ParsedQLBeans {
    @Value("${ql.mode}")
    private String mode;

    @Value("${ql.file:#{null}}")
    private String qlFile;

    @Bean
    public Form parsedAndCheckedForm() throws IOException {
        return (mode.equals("file"))
                ? QLIngester.parseFormFromLocation(qlFile)
                : null;
    }
}
