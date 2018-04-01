package nl.uva.js.qlparser.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.uva.js.qlparser.exceptions.ParseException;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.logic.FormBuilder;
import nl.uva.js.qlparser.logic.QLSChecker;
import nl.uva.js.qlparser.logic.StylesheetBuilder;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.models.qls.elements.Page;
import nl.uva.js.qlparser.ui.components.form.ComponentBuilder;
import nl.uva.js.qlparser.ui.components.gui.ButtonBar;
import nl.uva.js.qlparser.ui.components.gui.FormPanel;
import nl.uva.js.qlparser.ui.components.gui.TextPanel;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static nl.uva.js.qlparser.ui.components.gui.ButtonBar.BUTTON_HEIGHT;

public class GUIBuilder {

    private static final int FORM_VIEW_HEIGHT = 700;
    private static final int LOG_HEIGHT       = 100;
    private static final int INPUT_HEIGHT     = FORM_VIEW_HEIGHT - 50;
    private static final int FOOTER_HEIGHT    = BUTTON_HEIGHT + LOG_HEIGHT;

    private static final int PAGE_BUTTON_WIDTH = 100;
    private static final int FORM_HEIGHT       = 2000;
    public  static final int FORM_WIDTH        = 700;
    public  static final int INPUT_WIDTH       = 350;
    private static final int CONTROL_WIDTH     = 250;

    private static final int FULL_HEIGHT = FORM_VIEW_HEIGHT + FOOTER_HEIGHT;
    public  static final int FULL_WIDTH  = INPUT_WIDTH + FORM_WIDTH + INPUT_WIDTH;

    private static Form globalForm;

    private static Frame guiFrame;
    private static JPanel    topPanel;
    private static JPanel    bottomPanel;
    private static TextPanel qlPanel;
    private static FormPanel formPanel;
    private static TextPanel qlsPanel;
    private static TextPanel console;

    private static QLSChecker qlsChecker;

    public static Frame getGUI() {
        qlsChecker = new QLSChecker();
        formPanel  = new FormPanel(FORM_VIEW_HEIGHT, FORM_WIDTH, FORM_HEIGHT);

        guiFrame    = getGuiFrame();
        topPanel    = getTopPanel(null);
        bottomPanel = getBottomPanel();
        qlPanel     = getInputPanelWithPreset("ql.file");
        qlsPanel    = getInputPanelWithPreset("qls.file");

        guiFrame.add(topPanel,    BorderLayout.PAGE_START);
        guiFrame.add(qlPanel,     BorderLayout.LINE_START);
        guiFrame.add(formPanel,   BorderLayout.CENTER);
        guiFrame.add(qlsPanel,    BorderLayout.LINE_END);
        guiFrame.add(bottomPanel, BorderLayout.PAGE_END);

        guiFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guiFrame.dispose();
            }
        });

        return guiFrame;
    }

    private static TextPanel getInputPanelWithPreset(String property) {
        TextPanel inputPanel = new TextPanel(INPUT_WIDTH, INPUT_HEIGHT, Color.darkGray, true);
        inputPanel.setText(loadDefaultFile(property));

        return inputPanel;
    }

    private static String loadDefaultFile(String property) {
        return NonNullRun.function(System.getProperty(property), file -> {
            try {
                Path filePath = Paths.get(GUIBuilder.class.getClassLoader().getResource(file).getFile());
                return new String(Files.readAllBytes(filePath));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                return "";
            }
        });
    }

    private static void log(String message) {
        console.setText(console.getText() + "\n" + message);
        console.revalidate();
        console.repaint();
    }

    private static Frame getGuiFrame() {
        Frame frame = new Frame();

        frame.setSize(new Dimension(FULL_WIDTH, FULL_HEIGHT));
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        return frame;
    }

    private static JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel();

        console = new TextPanel(FULL_WIDTH, LOG_HEIGHT, Color.black, false);
        console.setTextColor(new Color(225, 110, 110));

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(getBottomButtons(), BorderLayout.PAGE_START);
        bottomPanel.add(console, BorderLayout.PAGE_END);

        return bottomPanel;
    }

    private static JPanel getTopPanel(LinkedList<Page> pages) {
        ButtonBar topPanel = new ButtonBar();

        NonNullRun.consumer(pages, __ -> pages.forEach(page -> {
            JButton pageButton = getButton(page.getName(), PAGE_BUTTON_WIDTH);
            pageButton.addActionListener(e -> formPanel.setPage(page.getName()));

            topPanel.centerPanel.add(pageButton);
        }));

        return topPanel;
    }

    private static JPanel getBottomButtons() {
        ButtonBar buttonBar = new ButtonBar();

        JButton renderButton = getButton("Render form", CONTROL_WIDTH);
        renderButton.addActionListener(GUIBuilder::renderForm);

        JButton exportButton = getButton("Export form", CONTROL_WIDTH);
        exportButton.addActionListener(GUIBuilder::exportForm);

        buttonBar.leftPanel.add(ComponentBuilder.buildSectionHeader("QL"));
        buttonBar.centerPanel.add(renderButton);
        buttonBar.centerPanel.add(exportButton);
        buttonBar.rightPanel.add(ComponentBuilder.buildSectionHeader("QLS"));

        return buttonBar;
    }

    private static void setPageButtons(LinkedList<Page> pages) {
        topPanel.removeAll();
        topPanel.add(getTopPanel(pages));
        topPanel.revalidate();
        topPanel.repaint();
    }

    private static JButton getButton(String text, int width) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, BUTTON_HEIGHT));
        return button;
    }

    private static void renderForm(ActionEvent e) {
        try {
            globalForm = FormBuilder.parseFormFromString(qlPanel.getText());
            formPanel.apply(globalForm);
            guiFrame.setTitle(globalForm != null ? globalForm.getHumanizedName() : "");

            setPageButtons(null);

            List<String> errors = new ArrayList<>();
            Stylesheet stylesheet = null;

            if (!StringUtils.isBlank(qlsPanel.getText()) && globalForm != null) {
                stylesheet = StylesheetBuilder.parseStylesheetFromString(qlsPanel.getText());
                errors = qlsChecker.checkForErrors(globalForm, stylesheet);
            }

            if (globalForm == null) {
                errors.add("No QL entered.");
            }

            if (errors.size() == 0 && stylesheet != null) {
                formPanel.apply(stylesheet);
                setPageButtons(stylesheet.getPages());
            }
            errors.forEach(GUIBuilder::log);

        } catch (ParseException exception) {
            log(exception.getMessage());
        }
    }

    private static void exportForm(ActionEvent e) {
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .registerModule(new Jdk8Module())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            JFileChooser saveDialog = new JFileChooser();
            saveDialog.setFileFilter(new FileNameExtensionFilter("QL Export", "qle"));

            if (saveDialog.showSaveDialog(guiFrame) == JFileChooser.APPROVE_OPTION) {
                mapper.writer().writeValue(saveDialog.getSelectedFile(), globalForm);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
