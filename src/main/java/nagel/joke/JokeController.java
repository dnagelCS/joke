package nagel.joke;

//Request data from JokeService and populate the JokeFrame

import retrofit2.*;
import javax.swing.*;

public class JokeController {

    private JokeService service;

    public JokeController(JokeService service) {
        this.service = service;
    }

    public void requestData(String type) {
        service.getJoke(type).enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                Joke joke = response.body();
                assert joke != null;
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
