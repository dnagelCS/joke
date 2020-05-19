package nagel.joke;

import javafx.scene.layout.Border;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class JokeFrame extends JFrame {

    private JFrame frame;

    private JLabel prompt;

    private JButton general;
    private JButton programming;
    private JButton knockKnock;

    private JTextArea joke;

    public JokeFrame() {
        frame = new JFrame("Joke Frame");

        prompt = new JLabel();
        prompt.setHorizontalAlignment(JLabel.CENTER);
        prompt.setBounds(10,10,600,100);
        prompt.setFont(new Font ("Comic Sans MS", Font.BOLD, 20));
        prompt.setText("Choose a type of joke:");

        general = new JButton("General");
        general.setBounds(60,100,150,30);
        general.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        general.addActionListener(actionEvent -> getJokeType());
        programming = new JButton("Programming");
        programming.setBounds(220,100,150,30);
        programming.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        programming.addActionListener(actionEvent -> getJokeType());
        knockKnock = new JButton("Knock-Knock");
        knockKnock.setBounds(380,100,150,30);
        knockKnock.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        knockKnock.addActionListener(actionEvent -> getJokeType());

        joke = new JTextArea();
        joke.setBounds(10,190,320,100);
        joke.setWrapStyleWord(true);
        joke.setLineWrap(true);
        joke.setOpaque(false);
        joke.setEditable(false);
        joke.setFocusable(false);
        joke.setBackground(UIManager.getColor("Label.background"));
        joke.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        joke.setBorder(UIManager.getBorder("Label.border"));

        frame.add(prompt);
        frame.add(general);
        frame.add(programming);
        frame.add(knockKnock);
        frame.add(joke);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void getJokeType() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JokeService service = retrofit.create(JokeService.class);
        JokeController controller = new JokeController(service, joke);

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
        controller.requestData(type);
    }

    public static void main(String[] args) {
        new JokeFrame();
    }
}
