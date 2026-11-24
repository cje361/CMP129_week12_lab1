import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXInstallTest extends Application {

    private int clickCount = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label status = new Label("JavaFX started successfully.");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        Button testButton = new Button("Test JavaFX");

        testButton.setOnAction(event -> {
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                status.setText("Enter a name, then try again.");
            } else {
                clickCount++;
                status.setText("Hello, " + name + "! Button clicks: " + clickCount);
            }
        });

        VBox root = new VBox(12, status, nameField, testButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 440, 180);
        stage.setTitle("CMP 128 Honors JavaFX Installation Test");
        stage.setScene(scene);
        stage.show();
    }
}
