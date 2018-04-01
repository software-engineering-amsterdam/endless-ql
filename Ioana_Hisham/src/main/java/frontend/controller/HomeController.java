package frontend.controller;

import frontend.model.InterpretedOutput;
import frontend.model.UserInput;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ql.ASTBuilder;
import ql.ast.Form;
import ql.ast.statements.Question;
import ql.evaluator.Evaluator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Toshiba on 28/02/2018.
 */

@Controller
public class HomeController {

    private Logger log = Logger.getLogger(HomeController.class);

    @GetMapping("/")
    public String inputQLForm(Model model) {
        model.addAttribute("userInput", new UserInput());
        return "home";
    }

    @PostMapping("/")
    public String inputQLSubmit(@ModelAttribute("userInput") @Validated UserInput userInput, Model model) {
        log.info("Inbound message: " + userInput);
        model.addAttribute("processedQLData", userInput);
        //TODO: add processor for interpreting data from user
        //TODO return a InterpretedOutput from your processing class
        InterpretedOutput interpretedOutput = new InterpretedOutput();
        ASTBuilder ast = new ASTBuilder();
        String inputStringCombined = userInput.getHtmlRequestInput();
        try {
            Evaluator evaluator = new Evaluator();
            evaluator.visit(ast.build(new ByteArrayInputStream(inputStringCombined.getBytes())));
            interpretedOutput.setQuestions(evaluator.questions());
            log.info("Questions results: " + interpretedOutput);
        } catch (IOException e) {
            log.error("Error while parsing", e);
        }
        //visitor.visit(parseTree);
        model.addAttribute("interpretedOutput", interpretedOutput);
        model.addAttribute("userInput", new UserInput());
        return "home";
    }

    /*@PostMapping("/qls")
    public String inputQLSSubmit(@ModelAttribute("inputQLS") InterpretedOutput inputQLS, Model model) {
        log.info("Inbound message: " + inputQLS);
        model.addAttribute("processedQLSData", inputQLS);
        return "home";

    }*/
}