package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static duke.Events.DATE_TIME_FORMATTER;
import static duke.Storage.saveTasksToFile;

public class Parser {
    final static String EXIT_PHRASE = "bye";
    final static String LIST_PHRASE = "list";
    final static String TODO_PHRASE = "todo";
    final static String DEADLINE_PHRASE = "deadline";
    final static String EVENT_PHRASE = "event";
    final static String DELETE_PHRASE = "delete";

    private static boolean isRunning = true;


    public static void parse(String userInput, ArrayList<Task> taskList, int i, Ui ui) throws DukeException {
        while (!userInput.equals(EXIT_PHRASE)) {
            if (userInput.equals(LIST_PHRASE)) {
                Ui.print("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    Ui.print(j + 1 + "." +
                            taskList.get(j).toString());
                }
                userInput = ui.next();
                continue;
            }
//            if (userInput.equals("")) {
//                userInput = ui.next();
//                continue;
//            }

            if (userInput.equals("mark")) {
                userInput = ui.next();
                Task curr = taskList.get(Integer.parseInt(userInput) - 1);
                curr.mark();
                Ui.print( "Nice! I've marked this task as done: \n" + "[X] " + curr.getDescription());
                userInput = ui.next();
                continue;
            }

            if (userInput.equals("unmark")) {
                userInput = ui.next();
                Task curr = taskList.get(Integer.parseInt(userInput) - 1);
                curr.unmark();
                Ui.print( "OK, I've marked this task as not done yet: \n" + "[ ] " + curr.getDescription());
                userInput = ui.next();
                continue;
            }

            if (userInput.equals(DELETE_PHRASE)) {
                userInput = ui.next();
                Task curr = taskList.get(Integer.parseInt(userInput) - 1);
                taskList.remove(Integer.parseInt(userInput) - 1);
                i--;
                Ui.print("Noted. I've removed this task:");
                Ui.print(curr.toString());
                Ui.print("Now you have " + i + " tasks in the list.");
                userInput = ui.next();
                continue;
            }

            if (userInput.equals(TODO_PHRASE)) {
                userInput = ui.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                taskList.add(new Todo(userInput));
                Ui.print("Got it. I've added this task: ");
                Ui.print(taskList.get(i).toString());
                i++;
                Ui.print("Now you have " + i + " tasks in the list.");
                userInput = ui.next();
                continue;
            }
            if (userInput.equals(DEADLINE_PHRASE)) {
                userInput = ui.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                Ui.print("To be done by?");
                String userInputBy = ui.nextLine();
                LocalDate by;
                if (userInputBy.equals("today")) {
                    by = LocalDate.now();
                } else {
                    by = LocalDate.parse(userInputBy);
                }
                taskList.add(new Deadlines(userInput, by));
                Ui.print("Got it. I've added this task: ");
                Ui.print(taskList.get(i).toString());
                i++;
                Ui.print("Now you have " + i + " tasks in the list.");
                userInput = ui.next();
                continue;
            }
            if (userInput.equals(EVENT_PHRASE)) {
                userInput = ui.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                Ui.print("From?");
                LocalDateTime from = LocalDateTime.parse(ui.nextLine(), DATE_TIME_FORMATTER);
                Ui.print("To?");
                LocalDateTime to = LocalDateTime.parse(ui.nextLine(), DATE_TIME_FORMATTER);

                taskList.add(new Events(userInput, from, to));
                Ui.print("Got it. I've added this task: ");
                Ui.print(taskList.get(i).toString());
                i++;
                Ui.print("Now you have " + i + " tasks in the list.");
                userInput = ui.next();
                continue;
            }
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        isRunning = false;
    }

    public boolean getRunning() {
        return isRunning;
    }
}