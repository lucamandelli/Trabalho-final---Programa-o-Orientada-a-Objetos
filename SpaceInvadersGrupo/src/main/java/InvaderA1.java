import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

public class InvaderA1 extends BasicElement {
  private Image image;

  public InvaderA1(int px, int py) {
    super(px, py);
    try {
      // Carrega a imagem ajustando a altura para 30 pixels
      // mantendo a proporção em ambas dimensões
      image = new Image("invaderA1.png", 0, 20, true, true);
      this.setLargAlt((int) image.getWidth(), (int) image.getHeight());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  @Override
  public void start() {
    setDirH(1);
    setSpeed(8);
  }

  @Override
  public void Update(long deltaTime) {
    if (jaColidiu()) {
      Game.getInstance().incPontosA1();
      deactivate();
    } else {
      setPosX(getX() + getDirH() * getSpeed());

      // Se chegou no lado direito da tela ...
      if (getX() >= getLMaxH() - 100 || getX() < getLMinH() + 100) {
        // Inverte a direção
        setDirH(getDirH() * -1);

        setSpeed(getSpeed() + 2);

        setPosY(getY() + 30);

      }

    }
  }

  public void Draw(GraphicsContext graphicsContext) {
    graphicsContext.drawImage(image, getX(), getY());
  }

  @Override
  public void testaColisao(Character outro) {
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
}
