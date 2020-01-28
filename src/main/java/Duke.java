import java.util.*;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] arr = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    Hello! I'm Duke\n" + "  What can I do for you?");
        String input = sc.nextLine();
        //Boolean validInput = false;
//        try { //1st input
//            validInput = validCommand(input);
//        } catch (DukeException e) {
//            System.out.println(e);
//            input = sc.nextLine();
//        }
                int listCounter = 0;
                while (!input.equals("bye")) {
                        if (!input.equals("list") && !input.contains("done")) {
                            char[] inputArr = input.toCharArray();
                            if (input.contains("todo")) { //create todo
                                try {
                                    String info = Todo.generateTodoDesc(inputArr);
                                    Todo task = Todo.createTodo(info);
                                    boolean carryOn = task.checkToDo(info);
                                    if (carryOn) {
                                        Task.addTask(task);
                                    }
                                } catch (DukeException e) {
                                    System.out.println(e);
                                }
                            } else if (input.contains("event")) { //create event
                                String date, desc;
                                date = Event.getEventDate(inputArr);
                                desc = Event.getEventDesc(inputArr);
                                Event task = Event.createEvent(desc, date);
                                Task.addTask(task);
                            } else if (input.contains("deadline")) { //create deadline
                                String by, desc;
                                by = Deadline.getBy(inputArr);
                                desc = Deadline.getDesc(inputArr);
                                Deadline task = Deadline.createDeadline(desc, by);
                                Task.addTask(task);
                            }
                        } else if (input.equals("list")) { //list
                            System.out.println(Task.showTasks());
                        } else { //done command
                            Task.taskDone(input);
                        }
                        input = sc.nextLine();
                }
                System.out.println("Bye. Hope to see you again soon!"); //bye
    }

    static boolean validCommand(String input) throws DukeException {
        if(input.contains("todo") || input.contains("list") || input.contains("done") || input.contains("event") ||
                input.contains("deadline") || input.contains("bye") || input.contains("delete")) {
            return true;
        } else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            return false;
        }
    }
}