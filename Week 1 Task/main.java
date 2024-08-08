import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class QuizManager {
    private List<Quiz> quizzes;

    public QuizManager() {
        quizzes = new ArrayList<>();
    }

    public void createQuiz(String title) {
        quizzes.add(new Quiz(title));
    }

    public Quiz getQuiz(String title) {
        for (Quiz quiz : quizzes) {
            if (quiz.getTitle().equalsIgnoreCase(title)) {
                return quiz;
            }
        }
        return null;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void takeQuiz(Quiz quiz) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : quiz.getQuestions()) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Your answer: ");
            int answerIndex = scanner.nextInt() - 1;

            if (question.isCorrect(answerIndex)) {
                System.out.println("Correct Answer!\n");
                score++;
            } else {
                System.out.println(
                        "Wrong! The correct answer is: " + options.get(question.getCorrectAnswerIndex()) + "\n");
            }
        }

        System.out.println("Your score: " + score + "/" + quiz.getQuestions().size());
        System.out.println("Thank you for taking the quiz!");
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager quizManager = new QuizManager();

        while (true) {
            System.out.println("\nQuiz Generator Application");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add a question to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter quiz title: ");
                    String title = scanner.nextLine();
                    quizManager.createQuiz(title);
                    System.out.println("Quiz created successfully!");
                    break;
                case 2:
                    System.out.print("Enter quiz title: ");
                    String quizTitle = scanner.nextLine();
                    Quiz quiz = quizManager.getQuiz(quizTitle);
                    if (quiz == null) {
                        System.out.println("Quiz not found!");
                        break;
                    }
                    System.out.print("Enter question text: ");
                    String questionText = scanner.nextLine();
                    List<String> options = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Enter option " + (i + 1) + ": ");
                        options.add(scanner.nextLine());
                    }
                    System.out.print("Enter the number of the correct option: ");
                    int correctAnswerIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    Question question = new Question(questionText, options, correctAnswerIndex);
                    quiz.addQuestion(question);
                    System.out.println("Question added successfully!");
                    break;
                case 3:
                    System.out.print("Enter quiz title: ");
                    String quizToTake = scanner.nextLine();
                    Quiz quizToTakeObj = quizManager.getQuiz(quizToTake);
                    if (quizToTakeObj == null) {
                        System.out.println("Quiz not found!");
                        break;
                    }
                    quizManager.takeQuiz(quizToTakeObj);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
