package duke;

import java.util.ArrayList;

/**
 * The main class for the Duke application.
 */
public class Duke {
    protected static Storage storage = new Storage();
    protected static ArrayList<Task> taskList = new TaskList();
    protected static int index = 0;

    public String getResponse(String input) {
        index = taskList.size();
        return Parser.parse(taskList, index, storage, input);
    }
}
