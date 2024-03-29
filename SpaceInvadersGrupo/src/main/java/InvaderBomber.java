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

public class InvaderBomber extends BasicElement {
  private Image image1, image2;
  private int RELOAD_TIME = 1000000000; // Time is in nanoseconds
  private int shot_timer = 0;
  private int vida = 3;

  public InvaderBomber(int px, int py) {
    super(px, py);
    try {
      // Carrega a imagem ajustando a altura para 30 pixels
      // mantendo a proporção em ambas dimensões
      image1 = new Image("invaderBomber.png", 0, 60, true, true);
      image2 = new Image("invaderBomber2.png", 0, 60, true, true);
      this.setLargAlt((int) image1.getWidth(), (int) image1.getHeight());
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
      if (this.vida == 1) {
        Game.getInstance().incPontosBomber();
        deactivate();
      }
      this.vida--;
      this.colidiu = false;
    }

    setPosX(getX() + getDirH() * getSpeed());
    shot();
    if (shot_timer > 0) {
      shot_timer -= deltaTime;
    }
    // Se chegou no lado direito da tela ...
    if (getX() >= getLMaxH() - 100 || getX() < getLMinH() + 100) {
      // Inverte a direção
      setDirH(getDirH() * -1);
      // Sorteia o passo de avanço [1,5]
      // setSpeed(Params.getInstance().nextInt(5) + 5);
      // Se ainda não chegou perto do chão, desce
      /* if (getY() < 450){ */
      // setPosY(getY() + 30);
      // }

    }

  }

  public void shot() {
    if (shot_timer <= 0) {
      Game.getInstance().addChar(new ShotInvader(getX(), getY() + 20 + 35));
      shot_timer = RELOAD_TIME;
      shot();
    }
  }

  public void Draw(GraphicsContext graphicsContext) {
    if (shot_timer <= 500000000) {
      graphicsContext.drawImage(image2, getX(), getY());
    } else {
      graphicsContext.drawImage(image1, getX(), getY());
    }
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
