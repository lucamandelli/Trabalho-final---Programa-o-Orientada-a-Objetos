import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * 
 * @author Davi Barcellos de Lucca - 21200149
 * @author Luca Partichelli Mandelli - 20103327
 * @author Isabela Kuser Araujo - 21280763
 * 
 *         Link Projeto GitHub -
 *         https://github.com/lucamandelli/Trabalho-POO.git
 */

public class Main extends Application {
    Image image;

    @Override
    public void start(Stage stage) throws Exception {

        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);

        root.getChildren().add(canvas);

        // Setup Game object
        Game.getInstance().carregaLevelAtual();

        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        // Register Game Loop
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.font("Verdana", 12));
        gc.setFill(Color.WHITE);

        new AnimationTimer() {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                try {
                    // Carrega a imagem ajustando a altura para 40 pixels
                    // mantendo a proporção em ambas dimensões
                    image = new Image("background.png");
                    gc.drawImage(image, 0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
                String vidas = String.valueOf(Canhao.getVida());
                gc.fillText("Pontos: " + Game.getInstance().getPontos(), 10, 10);
                gc.fillText("data: " + Game.getInstance().getDate().getDayOfMonth() + "/"
                        + Game.getInstance().getDate().getMonth() + "/" + Game.getInstance().getDate().getYear(), 10,
                        30);
                gc.fillText("Vidas: " + vidas, 10, 50);

                Game.getInstance().Draw(gc);
                if (Game.getInstance().isGameOver()) {
                    stop();
                }

                lastNanoTime = currentNanoTime;
            }

        }.start();

        // Show window
        stage.show();
    }

    public void carregaFase1() {
        Game.getInstance().fase1();
    }

    public void carregaFase2() {
        Game.getInstance().fase2();
    }

    public void carregaFase3() {
        Game.getInstance().fase3();
    }

    public static void main(String args[]) {
        launch();
    }
}
