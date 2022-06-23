import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * 
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Shot extends BasicElement {
    private Image image;

    public Shot(int px, int py) {
        super(px, py);
        try {
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image = new Image("shot1.png", 0, 30, true, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setDirV(-1);
        setSpeed(5);
    }

    @Override
    public void testaColisao(Character outro) {
        // Não verifica colisão de um tiro com outro tiro
        if (outro instanceof Shot) {
            return;
        } else if (outro instanceof Canhao) {
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
