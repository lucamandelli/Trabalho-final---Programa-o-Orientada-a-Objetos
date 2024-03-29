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

public class Panacao extends BasicElement {
  private Image image1;
  private int RELOAD_TIME = 500000000; // Time is in nanoseconds
  private int shot_timer = 0;
  private int vida = 50;

  public Panacao(int px, int py) {
    super(px, py);
    try {
      // Carrega a imagem ajustando a altura para 30 pixels
      // mantendo a proporção em ambas dimensões
      image1 = new Image("Panacao.png", 0, 200, true, true);
      this.setLargAlt((int) image1.getWidth(), (int) image1.getHeight());

    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  @Override
  public void start() {
    setDirH(1);
    setSpeed(5);
  }

  @Override
  public void Update(long deltaTime) {
    if (jaColidiu()) {
      if (this.vida == 1) {
        Game.getInstance().incPontosPanacao();
        deactivate();
      }
      vida--;
      colidiu = false;
    }

    setPosX(getX() + getDirH() * getSpeed());
    shot();
    if (shot_timer > 0) {
      shot_timer -= deltaTime;
    }
    // Se chegou no lado direito da tela ...
    if (getX() >= getLMaxH() - 100 || getX() < getLMinH()) {
      // Inverte a direção
      setDirH(getDirH() * -1);

    }

  }

  public void shot() {
    if (shot_timer <= 0) {
      Game.getInstance().addChar(new ShotPanacao(getX() + 150, getY() + 150));
      shot_timer = RELOAD_TIME;
      shot();
    }
  }

  public void Draw(GraphicsContext graphicsContext) {
    graphicsContext.drawImage(image1, getX(), getY());
  }

  @Override
  public void testaColisao(Character outro) {
    if (outro instanceof InvaderA1) {
      return;
    } else if (outro instanceof InvaderB1) {
      return;
    } else if (outro instanceof Panacao) {
      return;
    } else if (outro instanceof InvaderC1) {
      return;
    } else if (outro instanceof ShotInvader) {
      return;
    } else if (outro instanceof ShotPanacao) {
      return;
    } else {
      super.testaColisao(outro);
    }
  }
}
