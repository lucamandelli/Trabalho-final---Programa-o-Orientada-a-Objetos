import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

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
public class Canhao extends BasicElement implements KeyboardCtrl {
    private int RELOAD_TIME = 500000000; // Time is in nanoseconds
    private int shot_timer = 0;
    private Image image;
    private static int vida = 3;

    public Canhao(int px, int py) {
        super(px, py);
        try {
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image = new Image("canon.png", 0, 40, true, true);
            this.setLargAlt((int) image.getWidth(), (int) image.getHeight());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setLimH(0, Params.WINDOW_WIDTH);
        setLimV(0, Params.WINDOW_HEIGHT);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            if (getVida() == 1) {
                Game.getInstance().setGameOver();
            }
            Game.getInstance().perdePontos();
            setVida(getVida() - 1);
            colidiu = false;
        }
        setPosX(getX() + getDirH() * getSpeed());
        setPosY(getY() + getDirV() * getSpeed());
        if (shot_timer > 0) {
            shot_timer -= deltaTime;
        }
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {

        if (keyCode == KeyCode.LEFT) {
            if (getX() >= lminH + 100) {
                int dh = isPressed ? -1 : 0;
                setDirH(dh);
            } else {
                int dh = isPressed ? 0 : 0;
                setDirH(dh);
            }

        }
        if (keyCode == KeyCode.RIGHT) {
            if (getX() <= Params.WINDOW_WIDTH - 100) {
                int dh = isPressed ? 1 : 0;
                setDirH(dh);
            } else {
                int dh = isPressed ? 0 : 0;
                setDirH(dh);
            }
        }
        if (keyCode == KeyCode.SPACE) {
            if (shot_timer <= 0) {
                Game.getInstance().addChar(new Shot(getX() + 18, getY() - 20));
                shot_timer = RELOAD_TIME;
            }
        }

    }

    public void testaColisao(Character outro) {
        if (outro instanceof Canhao) {
            return;
        } else if (outro instanceof Shot) {
            return;
        } else {
            super.testaColisao(outro);
        }
    }

    public static int getVida() {
        return (vida);
    }

    public void setVida(int x) {
        vida = x;
    }

    @Override
    public int getAltura() {
        return this.altura;
    }

    @Override
    public int getLargura() {
        return this.largura;
    }

    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, getX(), getY());
    }
}
