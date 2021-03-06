package nagel.joke;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class JokeFrame extends JFrame {

    private JokeService service;
    private JokeController controller;

    private JLabel prompt;

    private JPanel typePanel;
    private JButton general;
    private JButton programming;
    private JButton knockKnock;

    private JLabel joke;

    public JokeFrame() {

        setSize(600, 300);
        setTitle("Random Joke");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        prompt = new JLabel();
        prompt.setHorizontalAlignment(JLabel.CENTER);
        prompt.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        prompt.setText("Choose a type of joke:");

        typePanel = new JPanel();
        typePanel.setLayout(new GridLayout(1, 3));
        general = new JButton("General");
        general.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        general.addActionListener(actionEvent -> getGeneralJoke());
        programming = new JButton("Programming");
        programming.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        programming.addActionListener(actionEvent -> getProgrammingJoke());
        knockKnock = new JButton("Knock-Knock");
        knockKnock.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        knockKnock.addActionListener(actionEvent -> getKnockKnockJoke());
        typePanel.add(general);
        typePanel.add(programming);
        typePanel.add(knockKnock);

        joke = new JLabel();
        joke.setOpaque(true);

        add(prompt, BorderLayout.NORTH);
        add(typePanel, BorderLayout.CENTER);
        add(joke, BorderLayout.SOUTH);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(JokeService.class);
        controller = new JokeController(service, joke);
    }

    private void getGeneralJoke() {
        controller.getJoke("general");
    }

    private void getProgrammingJoke() {
        controller.getJoke("programming");
    }

    private void getKnockKnockJoke() {
        controller.getJoke("knock-knock");

}


    public static void main(String[] args) {
        new JokeFrame().setVisible(true);
    }
}
