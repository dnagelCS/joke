package nagel.joke;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class JokeFrame extends JFrame {

    private JPanel promptPanel;
    private JLabel prompt;

    private JPanel typePanel;
    private JButton general;
    private JButton programming;
    private JButton knockKnock;

    private JPanel jokePanel;
    private JLabel joke;

    public JokeFrame() {
        setSize(600, 400);
        setTitle("Random Joke");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        promptPanel = new JPanel();
        promptPanel.setLayout(new BorderLayout());
        prompt = new JLabel();
        prompt.setText("Choose a type of joke:");
        promptPanel.add(prompt);

        typePanel = new JPanel();
        typePanel.setLayout(new BorderLayout());
        general = new JButton("General");
        general.addActionListener(actionEvent -> getJokeType());
        programming = new JButton("Programming");
        programming.addActionListener(actionEvent -> getJokeType());
        knockKnock = new JButton("Knock-Knock");
        knockKnock.addActionListener(actionEvent -> getJokeType());
        typePanel.add(general);
        typePanel.add(programming);
        typePanel.add(knockKnock);

        jokePanel = new JPanel();
        jokePanel.setLayout(new BorderLayout());
        joke = new JLabel();
        jokePanel.add(joke);

        add(promptPanel, BorderLayout.NORTH);
        add(typePanel, BorderLayout.CENTER);
        add(jokePanel, BorderLayout.SOUTH);
    }

    private void getJokeType() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JokeService service = retrofit.create(JokeService.class);

        String type = null;
        switch (type) {
           case "General":
               service.getJoke("general");
               break;
           case "Programming":
               service.getJoke("programming");
               break;
           case "Knock-Knock":
               service.getJoke("knock-knock");
               break;
       }

        JokeController controller = new JokeController(service, joke);
        controller.requestData(type);
    }

    public static void main(String[] args) {
        new JokeFrame().setVisible(true);
    }
}
