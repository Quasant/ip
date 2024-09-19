package screwllum;

import java.io.IOException;
import java.util.List;

import screwllum.exception.ScrewllumException;
import screwllum.tasks.TaskManager;
import screwllum.utils.Parser;
import screwllum.utils.Storage;
import screwllum.utils.Ui;

/**
 * Represents the Chatbot object, which contains instances of other classes and utilizes their provided functionalities
 * to drive the chatbot experience.
 */
public class Screwllum {
    private Storage storage;
    private TaskManager taskManager;
    private Ui ui;

    public Screwllum(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager(storage.load());
        } catch (IOException e) {
            ui.showError("File does not exist, creating new file");
            taskManager = new TaskManager();
        } catch (ScrewllumException e) {
            // In the case that the file is corrupted, I force the application to stop
            ui.showError(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Starts the application by displaying a welcome message and entering the main loop.
     * Using other classes, the loop parses user inputs, executes them, and updates the save file.
     * Exceptions caught results in the respective error message being displayed.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String userInput = ui.getInput();
                List<String> tokens = Parser.parseUserInput(userInput);
                taskManager.execute(tokens, ui);
                storage.writeToFile(taskManager.getTaskList());
            } catch (ScrewllumException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        new Screwllum("Save.txt").run();
    }
}
