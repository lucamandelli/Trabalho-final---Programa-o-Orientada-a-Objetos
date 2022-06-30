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

public class Canhao3 extends Canhao {
  private int RELOAD_TIME = 100000000; // Time is in nanoseconds
  private int shot_timer = 0;
  private Image image;

  public Canhao3(int px, int py) {
    super(px, py);
    try {
      // Carrega a imagem ajustando a altura para 40 pixels
      // mantendo a proporção em ambas dimensões
      image = new Image("canhao3.png", 0, 40, true, true);
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
        Game.getInstance().addChar(new Shot3(getX() + 20, getY() - 20));
        shot_timer = RELOAD_TIME;
      }
    }
    // if (keyCode == KeyCode.UP) {
    // if (getY() >= Params.WINDOW_HEIGHT - 200) {
    // int dv = isPressed ? -1 : 0;
    // setDirV(dv);
    // } else {
    // int dv = isPressed ? 0 : 0;
    // setDirV(dv);
    // }

    // }
    // if (keyCode == KeyCode.DOWN) {
    // if (getY() <= Params.WINDOW_HEIGHT - 50) {
    // int dv = isPressed ? 1 : 0;
    // setDirV(dv);
    // } else if (getY() <= Params.WINDOW_HEIGHT - 40) {
    // int dv = isPressed ? 1 : 0;
    // setDirV(dv);
    // } else if (getY() <= Params.WINDOW_HEIGHT - 30) {
    // int dv = isPressed ? 1 : 0;
    // setDirV(dv);
    // } else {
    // int dv = isPressed ? 0 : 0;
    // setDirV(dv);
    // }

    // }

  }

  public void testaColisao(Character outro) {
    if (outro instanceof Canhao3) {
      return;
    } else if (outro instanceof Shot3) {
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
