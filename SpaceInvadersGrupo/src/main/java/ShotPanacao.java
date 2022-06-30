import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

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
public class ShotPanacao extends BasicElement {

  public ShotPanacao(int px, int py) {
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
    } else if (outro instanceof InvaderC1) {
      return;
    } else if (outro instanceof ShotPanacao) {
      return;
    } else if (outro instanceof Panacao) {
      return;
    } else if (outro instanceof Shot3) {
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
    graphicsContext.setFill(Paint.valueOf("#00FF00"));
    graphicsContext.fillOval(getX(), getY(), 20, 40);
  }
}
