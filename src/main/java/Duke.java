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

//            if (validInput) {
                int listCounter = 0;
                while (!input.equals("bye")) {
                        if (!input.equals("list") && !input.contains("done")) {
                            char[] inputArr = input.toCharArray();
                            String desc = "";
                            if (input.contains("todo")) {
                                try {
                                    for (int i = 5; i < inputArr.length; i++) {
                                        desc += inputArr[i];
                                    }
                                    Todo task = new Todo(desc);
                                    boolean carryOn = task.checkToDo(desc);
                                    if (carryOn) {
                                        arr[listCounter] = task;
                                        System.out.println("Got it. I've added this task:");
                                        System.out.println("  " + task.toString());
                                        listCounter++;
                                        System.out.println("Now you have " + listCounter + " tasks in the list.");
                                    }
                                } catch (DukeException e) {
                                    System.out.println(e);
                                }
                            } else if (input.contains("event")) {
                                int marker = 0;
                                String date = "";
                                for (int i = inputArr.length - 1; (inputArr[i] != '/'); i--) {
                                    marker = i;
                                }
                                for (int i = marker + 3; i < inputArr.length; i++) {
                                    date += inputArr[i];
                                }
                                System.out.println(date);
                                for (int i = 6; i < marker - 2; i++) {
                                    desc += inputArr[i];
                                }
                                System.out.println(desc);
                                Event task = new Event(desc, date);
                                arr[listCounter] = task;
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  " + task.toString());
                                listCounter++;
                                System.out.println("Now you have " + listCounter + " tasks in the list.");
                            } else if (input.contains("deadline")) {
                                int marker = 0;
                                String by = "";
                                for (int i = inputArr.length - 1; (inputArr[i] != '/'); i--) {
                                    marker = i;
                                }
                                for (int i = marker + 3; i < inputArr.length; i++) {
                                    by += inputArr[i];
                                }
                                System.out.println(by);
                                for (int i = 9; i < marker - 2; i++) {
                                    desc += inputArr[i];
                                }
                                System.out.println(desc);
                                Deadline task = new Deadline(desc, by);
                                arr[listCounter] = task;
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  " + task.toString());
                                listCounter++;
                                System.out.println("Now you have " + listCounter + " tasks in the list.");
                            }
                        } else if (input.equals("list")) {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < listCounter; i++) {
                                System.out.println(i + 1 + "." + arr[i].toString());
                            }
                        } else {
                            char charArr[] = input.toCharArray();
                            String taskNum = "";
                            for (int i = 5; i < charArr.length; i++) {
                                taskNum += charArr[i];
                            }
                            int taskInt = Integer.parseInt(taskNum);
                            taskInt -= 1;
                            arr[taskInt].isDone = true;
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  [" + arr[taskInt].getStatusIcon() + "] " + arr[taskInt].getDescription());
                        }
                        input = sc.nextLine();
                }
                System.out.println("Bye. Hope to see you again soon!");
//            }
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
