package nagel.joke;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class JokeFrame extends JFrame {

    public JokeFrame() {
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Random Joke");
        setLayout(new BorderLayout());

        JokeView view = new JokeView();
        add(view, BorderLayout.CENTER);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JokeService service = retrofit.create(JokeService.class);

        JokeController controller = new JokeController(service, view);
        controller.requestData();
    }

    public static void main(String[] args) {
        new JokeFrame().setVisible(true);
    }
}
