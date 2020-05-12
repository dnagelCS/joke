package nagel.joke;

//Request data from JokeService and populate the JokeView

import retrofit2.*;

public class JokeController {

    private JokeService service;
    private JokeView view;
    private String type;

    public JokeController(JokeService service, JokeView view) {
        this.service = service;
        this.view = view;
    }

    public void requestData() {
        service.getJoke(this.type).enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                Joke joke = response.body();
                view.setJoke(joke);
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {

            }
        });
    }
}
