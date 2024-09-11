package screwllum.utils;

import screwllum.tasks.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Represents a front-end CLI UI for interactions with users. Essentially, a wrapper for a scanner with methods that
 * utilise multiple System.out.println calls.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    
    public String getInput() {
        return sc.nextLine();
    }
    
    public void showWelcome() {
        print("Pleased to meet you");
    }

    /**
     * Displays an error message to the user, which is normally the description of a Screwllum Exception. 
     *
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        print(message);
    }

    /**
     * Displays a message to the user based on the command tokens and the current list of tasks.
     * Handles commands such as "bye", "list", "toggle", "delete", "todo", "deadline", and "event".
     *
     * @param tokens The parsed command tokens.
     * @param taskList The current list of tasks.
     */
    public void showMessage(List<String> tokens, List<Task> taskList) {
        switch (tokens.get(0)) {
        case "bye":
            print("It was my pleasure, good bye");
            System.exit(0);
        case "list":
            if (taskList.isEmpty()) {
                print("There are no tasks for you!");
            } else {
                for (int i = 0; i < taskList.size(); i++) {
                    print(String.format("%s. %s", i + 1, taskList.get(i).toString()));
                }
            }
            break;
        case "toggle":
            print("I have toggled the status of this task:");
            print(taskList.get(Integer.parseInt(tokens.get(1)) - 1).toString());
            break;
        case "delete":
            print("I have deleted this task: ");
            print(taskList.get(Integer.parseInt(tokens.get(1)) - 1).toString());
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            print("I have added the following task:");
            print(taskList.get(taskList.size() - 1).toString());
            print("Now you have " + taskList.size() + " tasks" );
            break;
        }
    }
    
    private void print(String message) {
        System.out.println(message);
    } 
}
