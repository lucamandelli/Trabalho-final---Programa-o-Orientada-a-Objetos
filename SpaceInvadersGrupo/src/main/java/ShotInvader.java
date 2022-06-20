import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from up to bottom and then dismiss
 * 
 * @author Luca Mandelli
 */
public class ShotInvader extends BasicElement {
  public ShotInvader(int px, int py) {
    super(px, py);
  }

  @Override
  public void start() {
    setDirV(1);
    setSpeed(3);
  }

  @Override
  public void testaColisao(Character outro) {
    // Não verifica colisão de um tiro com outro tiro
    if (outro instanceof InvaderA1) {
      return;
    } else if (outro instanceof InvaderB1) {
      return;
    } else if (outro instanceof InvaderBomber) {
      return;
    } else if (outro instanceof invaderC1) {
      return;
    } else if (outro instanceof ShotInvader) {
      return;
    } else {
      super.testaColisao(outro);
    }
  }

  @Override
  public void Update(long deltaTime) {
    if (jaColidiu()) {
      deactivate();
    } else {
      setPosY(getY() + getDirV() * getSpeed());
      // Se chegou na parte superior da tela ...
      if (getY() >= getLMaxV()) {
        // Desaparece
        deactivate();
      }
    }
  }

  @Override
  public int getAltura() {
    return 8;
  }

  @Override
  public int getLargura() {
    return 4;
  }

  public void Draw(GraphicsContext graphicsContext) {
    graphicsContext.setFill(Paint.valueOf("#FF0000"));
    graphicsContext.fillOval(getX(), getY(), 4, 16);
  }
}
