import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Canhao2 extends Canhao {
  private int RELOAD_TIME = 250000000; // Time is in nanoseconds
  private int shot_timer = 0;
  private Image image;

  public Canhao2(int px, int py) {
    super(px, py);

    try {
      // Carrega a imagem ajustando a altura para 40 pixels
      // mantendo a proporção em ambas dimensões
      image = new Image("Canhao2.png", 0, 40, true, true);
      this.setLargAlt((int) image.getWidth(), (int) image.getHeight());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  public void start() {
    setLimH(0, Params.WINDOW_WIDTH);
    setLimV(0, Params.WINDOW_HEIGHT);
  }

  public void Update(long deltaTime) {
    if (jaColidiu()) {
      if (getVida() == 1) {
        Game.getInstance().setGameOver();
      }
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
      if (getX() <= Params.WINDOW_WIDTH - 200) {
        int dh = isPressed ? 1 : 0;
        setDirH(dh);
      } else {
        int dh = isPressed ? 0 : 0;
        setDirH(dh);
      }
    }
    if (keyCode == KeyCode.SPACE) {
      if (shot_timer <= 0) {
        Game.getInstance().addChar(new Shot2(getX() + 18, getY() - 20));
        shot_timer = RELOAD_TIME;
      }
    }

  }

  public void testaColisao(Character outro) {
    if (outro instanceof Canhao2) {
      return;
    } else if (outro instanceof Shot2) {
      return;
    } else {
      super.testaColisao(outro);
    }
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
