import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * 
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Shot2 extends Shot {
  public Shot2(int px, int py) {
    super(px, py);
  }

  @Override
  public void start() {
    setDirV(-1);
    setSpeed(10);
  }

  @Override
  public void testaColisao(Character outro) {
    // Não verifica colisão de um tiro com outro tiro
    if (outro instanceof Shot2) {
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
      if (getY() <= getLMinV()) {
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
    graphicsContext.setFill(Paint.valueOf("#FFFAFA"));
    graphicsContext.fillOval(getX(), getY(), 4, 8);
  }
}
