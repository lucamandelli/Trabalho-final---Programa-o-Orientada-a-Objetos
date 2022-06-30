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
public class Shot2 extends Shot {
  private Image image;

  public Shot2(int px, int py) {
    super(px, py);
    try {
      // Carrega a imagem ajustando a altura para 40 pixels
      // mantendo a proporção em ambas dimensões
      image = new Image("shot2.png", 0, 30, true, true);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
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
    } else if (outro instanceof Canhao2) {
      return;
    } else if (outro instanceof ShotPanacao) {
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
