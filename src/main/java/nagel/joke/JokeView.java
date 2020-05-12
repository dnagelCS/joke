package nagel.joke;

//Display the Joke

import javax.swing.*;
import java.awt.*;

public class JokeView extends JComponent {

    private Joke joke;

    public void setJoke(Joke joke) {
        this.joke = joke;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (joke == null) {
            return;
        }


    }
}
