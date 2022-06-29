import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * 
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private boolean gameOver;
    private int pontos;
    private LocalDate ld;
    private Levels levelAtual;

    private Game() {
        gameOver = false;
        pontos = 0;
        ld = LocalDate.now();

    }

    public LocalDate getDate() {
        return ld;
    }

    public void setGameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getPontos() {
        return pontos;
    }

    public void perdePontos() {
        pontos -= 10;
    }

    public void incPontosA1() {
        pontos++;
    }

    public void incPontosB1() {
        pontos += 2;
    }

    public void incPontosC1() {
        pontos += 3;
    }

    public void incPontosBomber() {
        pontos += 4;
    }

    public void incPontosPanacao() {
        pontos += 50;
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return (game);
    }

    public void addChar(Character c) {
        activeChars.add(c);
        c.start();
    }

    public void eliminate(Character c) {
        activeChars.remove(c);
    }

    public void Start() {
        carregaLevelAtual();

    }

    public void carregaLevelAtual() {
        fase1();
        levelAtual = Levels.Level1;
    }

    public void highScore() {
        try {
            FileWriter writer = new FileWriter("HighScore.txt");
            String s = String.valueOf(getPontos());
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Update(long currentTime, long deltaTime) {
        if (gameOver) {
            return;
        }

        int personagens = 0;

        for (int i = 0; i < activeChars.size(); i++) {
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            for (int j = 0; j < activeChars.size(); j++) {
                Character outro = activeChars.get(j);
                if (este != outro) {
                    este.testaColisao(outro);
                }
            }

            if (!(este instanceof Shot) && !(este instanceof ShotPanacao)) {
                personagens++;
            }
        }

        if (personagens == 1) {
            switch (levelAtual) {
                case Level1:
                    fase2();
                    levelAtual = Levels.Level2;
                    break;
                case Level2:
                    fase3();
                    levelAtual = Levels.Level3;
                    break;
                case Level3:
                    faseBoss();
                    levelAtual = Levels.LevelPanacao;
                    break;
                case LevelPanacao:
                    levelAtual = Levels.GameOver;

                    break;
                case GameOver:
                    highScore();
                    setGameOver();
                    break;

                default:
                    break;
            }
        }

    }

    public void fase1() {
        activeChars = new LinkedList<>();

        canhao = new Canhao(350, 500);
        activeChars.add(canhao);

        activeChars.add(new InvaderB1(200, 120));
        activeChars.add(new InvaderB1(300, 120));
        activeChars.add(new InvaderB1(400, 120));
        activeChars.add(new InvaderB1(500, 120));
        activeChars.add(new InvaderB1(600, 120));
        activeChars.add(new InvaderB1(200, 180));
        activeChars.add(new InvaderB1(300, 180));
        activeChars.add(new InvaderB1(400, 180));
        activeChars.add(new InvaderB1(500, 180));
        activeChars.add(new InvaderB1(600, 180));

        activeChars.add(new InvaderC1(200, 220));
        activeChars.add(new InvaderC1(300, 220));
        activeChars.add(new InvaderC1(400, 220));
        activeChars.add(new InvaderC1(500, 220));
        activeChars.add(new InvaderC1(600, 220));

        activeChars.add(new InvaderA1(200, 260));
        activeChars.add(new InvaderA1(300, 260));
        activeChars.add(new InvaderA1(400, 260));
        activeChars.add(new InvaderA1(500, 260));
        activeChars.add(new InvaderA1(600, 260));

        for (Character c : activeChars) {
            c.start();
        }

    }

    public void fase2() {
        activeChars = new LinkedList<>();

        canhao = new Canhao2(canhao.getX(), canhao.getY());

        activeChars.add(canhao);

        activeChars.add(new InvaderBomber(200, 30));
        activeChars.add(new InvaderBomber(300, 30));
        activeChars.add(new InvaderBomber(400, 30));
        activeChars.add(new InvaderBomber(500, 30));
        activeChars.add(new InvaderBomber(600, 30));

        activeChars.add(new InvaderB1(200, 90));
        activeChars.add(new InvaderB1(300, 90));
        activeChars.add(new InvaderB1(400, 90));
        activeChars.add(new InvaderB1(500, 90));
        activeChars.add(new InvaderB1(600, 90));

        activeChars.add(new InvaderC1(200, 120));
        activeChars.add(new InvaderC1(300, 120));
        activeChars.add(new InvaderC1(400, 120));
        activeChars.add(new InvaderC1(500, 120));
        activeChars.add(new InvaderC1(600, 120));

        activeChars.add(new InvaderA1(200, 160));
        activeChars.add(new InvaderA1(300, 160));
        activeChars.add(new InvaderA1(400, 160));
        activeChars.add(new InvaderA1(500, 160));
        activeChars.add(new InvaderA1(600, 160));

        for (Character c : activeChars) {
            c.start();
        }

    }

    public void fase3() {
        activeChars = new LinkedList<>();

        canhao = new Canhao3(canhao.getX(), canhao.getY());

        activeChars.add(canhao);

        activeChars.add(new InvaderBomber(200, 30));
        activeChars.add(new InvaderBomber(300, 30));
        activeChars.add(new InvaderBomber(400, 30));
        activeChars.add(new InvaderBomber(500, 30));
        activeChars.add(new InvaderBomber(600, 30));

        activeChars.add(new InvaderC1(200, 120));
        activeChars.add(new InvaderC1(300, 120));
        activeChars.add(new InvaderC1(400, 120));
        activeChars.add(new InvaderC1(500, 120));
        activeChars.add(new InvaderC1(600, 120));

        activeChars.add(new InvaderA1(200, 160));
        activeChars.add(new InvaderA1(300, 160));
        activeChars.add(new InvaderA1(400, 160));
        activeChars.add(new InvaderA1(500, 160));
        activeChars.add(new InvaderA1(600, 160));

        activeChars.add(new InvaderA1(200, 200));
        activeChars.add(new InvaderA1(300, 200));
        activeChars.add(new InvaderA1(400, 200));
        activeChars.add(new InvaderA1(500, 200));
        activeChars.add(new InvaderA1(600, 200));

        for (Character c : activeChars) {
            c.start();
        }
    }

    public void faseBoss() {
        activeChars = new LinkedList<>();

        canhao = new Canhao3(canhao.getX(), canhao.getY());

        activeChars.add(canhao);

        activeChars.add(new Panacao(400, 0));

        for (Character c : activeChars) {
            c.start();
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {
        // canhao.OnInput(keyCode, isPressed);
        // canhao2.OnInput(keyCode, isPressed);
        canhao.OnInput(keyCode, isPressed);
        if (keyCode == KeyCode.F1) {
            fase1();
            levelAtual = Levels.Level1;
        }
        if (keyCode == KeyCode.F2) {
            fase2();
            levelAtual = Levels.Level2;
        }
        if (keyCode == KeyCode.F3) {
            fase3();
            levelAtual = Levels.Level3;
        }
        if (keyCode == KeyCode.F4) {
            faseBoss();
            levelAtual = Levels.LevelPanacao;
        }
        if (keyCode == KeyCode.F5) {
            levelAtual = Levels.GameOver;
        }
    }

    public void Draw(GraphicsContext graphicsContext) {
        for (Character c : activeChars) {
            c.Draw(graphicsContext);
        }
    }
}
