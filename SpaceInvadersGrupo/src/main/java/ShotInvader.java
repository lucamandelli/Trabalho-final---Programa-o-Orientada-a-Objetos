import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a shot that crosses the screen from up to bottom and then dismiss
 * 
 * @author Luca Mandelli
 */
public class ShotInvader extends BasicElement {
  private Image image;

  public ShotInvader(int px, int py) {
    super(px, py);
    try {
      // Carrega a imagem ajustando a altura para 40 pixels
      // mantendo a proporção em ambas dimensões
      image = new Image("shotInvader.png", 0, 30, true, true);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
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
    } else if (outro instanceof InvaderC1) {
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
