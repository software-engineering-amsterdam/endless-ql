package frontend.controller;

import frontend.model.InterpretedOutput;
import frontend.model.UpdateJSON;
import frontend.model.UserInput;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.stringtemplate.v4.ST;
import ql.ASTBuilder;
import ql.ast.Form;
import ql.ast.statements.Question;
import ql.evaluator.Evaluator;
import ql.gui.controls.QLControl;
import ql.values.BooleanValue;

import javax.servlet.http.HttpServletRequest;
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
    public String inputQLForm(Model model, HttpServletRequest request) {
        model.addAttribute("userInput", new UserInput());
        request.getSession().setAttribute("userInput", new UserInput());
        return "home";
    }

    @PostMapping("/")
    public String inputQLSubmit(@ModelAttribute("userInput") @Validated UserInput userInput, Model model, HttpServletRequest request) {
        log.info("Inbound message: " + userInput);
        model.addAttribute("processedQLData", userInput);
        model.addAttribute("originalHTMLInput", userInput.getHtmlRequestInput());

        request.getSession().setAttribute("userInput",userInput);
        request.getSession().setAttribute("processedQLData", userInput);
        request.getSession().setAttribute("originalHTMLInput", userInput.getHtmlRequestInput());
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
        request.getSession().setAttribute("interpretedOutput", interpretedOutput);
        return "home";
    }

    @PostMapping(value = "/reset", produces = "text/html;charset=UTF-8")
    public String inputQLSSubmit(@RequestBody UpdateJSON updateJSON, Model model, HttpServletRequest request) {
        log.info("Inbound message: " + updateJSON);
        log.info("Inbound message: " + request.getSession().getAttribute("userInput"));
        model.addAttribute("userInput",request.getSession().getAttribute("userInput"));
        model.addAttribute("originalHTMLInput", request.getSession().getAttribute("originalHTMLInput"));
        InterpretedOutput interpretedOutput = new InterpretedOutput();
        ASTBuilder ast = new ASTBuilder();

        try {
            log.info("blah" + request.getSession().getAttribute("originalHTMLInput").toString());
            Evaluator initialEvaluator = new Evaluator();
            Evaluator evaluator = new Evaluator();
            initialEvaluator.visit(ast.build(new ByteArrayInputStream(request.getSession().getAttribute("originalHTMLInput").toString().getBytes())));
            initialEvaluator.questions().forEach(question -> {
                log.info("Checking: " + question.getIdentifier());
                if(question.getIdentifier().toString().equals(updateJSON.getIdentifier())){
                    log.info("Found: " + question.getIdentifier());
                    evaluator.valueTable().add(question.getIdentifier(), new BooleanValue(true));
                }
            });
            evaluator.visit(ast.build(new ByteArrayInputStream(request.getSession().getAttribute("originalHTMLInput").toString().getBytes())));
            interpretedOutput.setQuestions(evaluator.questions());
            log.info("Questions results: " + interpretedOutput);
        } catch (IOException e) {
            log.error("Error while parsing", e);
        }
        //visitor.visit(parseTree);
        model.addAttribute("interpretedOutput", interpretedOutput);
        return "home";

    }
}