import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class invaderC1 extends BasicElement {
  private Image image;

  public invaderC1(int px, int py) {
    super(px, py);
    try {
      // Carrega a imagem ajustando a altura para 30 pixels
      // mantendo a proporção em ambas dimensões
      image = new Image("invaderC1.png", 0, 20, true, true);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  @Override
  public void start() {
    setDirH(1);
    setSpeed(2);
  }

  @Override
  public void Update(long deltaTime) {
    if (jaColidiu()) {
      Game.getInstance().incPontosC1();
      deactivate();
    } else {
      setPosX(getX() + getDirH() * getSpeed());

      // Se chegou no lado direito da tela ...
      if (getX() >= getLMaxH() - 100 || getX() < getLMinH() + 100) {
        // Inverte a direção
        setDirH(getDirH() * -1);
        // Sorteia o passo de avanço [1,5]
        // setSpeed(Params.getInstance().nextInt(5) + 5);
        // Se ainda não chegou perto do chão, desce
        /* if (getY() < 450){ */
        setPosY(getY() + 30);

        // }
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
    } else if (outro instanceof invaderC1) {
      return;
    } else if (outro instanceof ShotInvader) {
      return;
    } else {
      super.testaColisao(outro);
    }
  }
}