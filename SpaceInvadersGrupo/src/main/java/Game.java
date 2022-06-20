import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.List;
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
    private int pontosTroca;
    private int tipoTroca = 1;

    private Game() {
        gameOver = false;
        pontos = 0;
        ld = LocalDate.now();
        pontosTroca = 7;
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
        // Repositório de personagens
        activeChars = new LinkedList<>();

        // Adiciona o canhao

        canhao = new Canhao(400, 500);
        activeChars.add(canhao);

        // }

        // Adiciona bolas
        // for (int i = 0; i < 4; i++) {
        // activeChars.add(new InvaderBomber(100 + (i * 180), 30));
        // }

        // Adiciona invader
        // activeChars.add(new InvaderBomber(100, 100));
        // activeChars.add(new InvaderBomber(180, 100));
        activeChars.add(new InvaderB1(260, 100));
        activeChars.add(new InvaderB1(340, 100));
        activeChars.add(new InvaderB1(420, 100));
        activeChars.add(new InvaderA1(500, 150));
        // activeChars.add(new invaderC1(100, 200));
        // activeChars.add(new Invader1(180, 100));
        // activeChars.add(new Invader1(260, 100));
        // activeChars.add(new Invader1(340, 100));
        // activeChars.add(new Invader1(420, 100));
        // activeChars.add(new Invader1(500, 100));

        for (Character c : activeChars) {
            c.start();
        }
    }

    public void Update(long currentTime, long deltaTime) {
        if (gameOver) {
            return;
        }
        if (getPontos() >= pontosTroca && tipoTroca == 1) {
            int x = canhao.getX();
            int y = canhao.getY();
            canhao.deactivate();
            canhao = new Canhao2(x, y);
            activeChars.add(canhao);
            pontosTroca = 23;
            tipoTroca = 2;

            for (int i = 1; i < 5; i++) {
                activeChars.add(new InvaderBomber(100 * i, 30));
            }
            for (Character c : activeChars) {
                c.start();
            }

        }
        if (getPontos() >= pontosTroca && tipoTroca == 2) {
            int x = canhao.getX();
            int y = canhao.getY();
            canhao.deactivate();
            canhao = new Canhao3(x, y);
            activeChars.add(canhao);
            pontosTroca = 100000000;
            tipoTroca = 50000000;
            for (int i = 1; i < 5; i++) {
                activeChars.add(new InvaderA1(100 * i, 30));
            }
            for (Character c : activeChars) {
                c.start();
            }

        }

        for (int i = 0; i < activeChars.size(); i++) {
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            for (int j = 0; j < activeChars.size(); j++) {
                Character outro = activeChars.get(j);
                if (este != outro) {
                    este.testaColisao(outro);
                }
            }
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {
        // canhao.OnInput(keyCode, isPressed);
        // canhao2.OnInput(keyCode, isPressed);
        canhao.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        for (Character c : activeChars) {
            c.Draw(graphicsContext);
        }
    }
}
