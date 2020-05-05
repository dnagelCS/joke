package nagel.joke;

import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class JokeTest {

    @Test
    public void getJoke() throws IOException {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JokeService service = retrofit.create(JokeService.class);

        //when
        Joke joke = service.getJoke("programming").execute().body();

        //then
        assertNotNull(joke);
        assertNotNull(joke.id);
        assertNotNull(joke.type);
        assertNotNull(joke.setup);
        assertNotNull(joke.punchline);
    }
}
