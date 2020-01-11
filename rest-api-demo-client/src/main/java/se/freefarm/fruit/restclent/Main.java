package se.freefarm.fruit.restclent;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;
import java.util.regex.Pattern;

public class Main
{
    public static void main( String[] args ) throws IOException
    {
        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        // Create panel to hold components
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        final Label lblOutput = new Label("");

        panel.addComponent(new Label("Fruit"));
        final TextBox txtFruit = new TextBox().setValidationPattern(Pattern.compile("[a-z]*")).addTo(panel);

        panel.addComponent(new Label("Units"));
        final TextBox txtUnits = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(panel);

        panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        new Button("OK", new Runnable() {
            @Override
            public void run() {
                if ("".equals(txtUnits.getText())){
                    lblOutput.setText("");
                    return;
                }
                String name = txtFruit.getText();
                int units = Integer.parseInt(txtUnits.getText());
                PriceInformationDTO p = new FruitRestClient().getPriceInformation(units, name);
                lblOutput.setText(String.format("%.2f",p.getPrice()));
            }
        }).addTo(panel);

        panel.addComponent(new Label("Price:"));
        panel.addComponent(lblOutput);

        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setComponent(panel);

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }

}
