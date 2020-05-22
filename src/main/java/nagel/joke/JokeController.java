package nagel.joke;

//Request data from JokeService and populate the JokeFrame

import retrofit2.*;
import javax.swing.*;

public class JokeController {

    private Joke jokeClass;
    private JokeService service;
    private JLabel joke;

    public JokeController(JokeService service, JLabel joke) {
        this.service = service;
        this.joke = joke;
    }

    public void getJoke(String type) {
        service.getJoke(type).enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                jokeClass = response.body();
                assert jokeClass != null;
                joke.setText("Joke ID: " + jokeClass.id + " Joke Type: " + jokeClass.type +
                        " Setup: " + jokeClass.setup + " Punchline: " + jokeClass.punchline);
                assert joke != null;
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
