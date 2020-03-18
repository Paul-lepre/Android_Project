package paul.project_application;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Game_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_1);

        Intent intent = getIntent();
        int pj = intent.getIntExtra("Partie Jouer",0);
        final int pg = intent.getIntExtra("Partie Gagné", 0);
        pj = pj+1;
        final TextView Niveau_view = (TextView) findViewById(R.id.niveau);
        final TextView Score_view = (TextView) findViewById(R.id.score);
        final TextView pj_view = (TextView) findViewById(R.id.pj_v);

        Niveau_view.setText(" NIveau "+pj);
        Score_view.setText(String.valueOf(pg));
        pj_view.setText(String.valueOf(pj));



        final TextView Title_1_View = (TextView) findViewById(R.id.Title_1);
        final TextView Title_2_View = (TextView) findViewById(R.id.Title_2);

        final TextView pop_1_View = (TextView) findViewById(R.id.populariter_1);
        final TextView pop_2_View = (TextView) findViewById(R.id.populariter_2);

        final ImageView image1 = (ImageView) findViewById(R.id.image_1);
        final ImageView image2 = (ImageView) findViewById(R.id.image_2);

        final TextView resultat = (TextView) findViewById(R.id.reponse_view);

            int Film1 = (int) (1 + Math.random() * (500));
            int Film2 = (int) (1 + Math.random() * (500));
            while (Film1 == Film2) {
                Film2 = (int) (1 + Math.random() * (500));
            }
            String url_Film_1 = "https://api.themoviedb.org/3/movie/" + Film1 + "?api_key=498c290270f6020be6453cb4e9fe2bbc";
            String url_Film_2 = "https://api.themoviedb.org/3/movie/" + Film2 + "?api_key=498c290270f6020be6453cb4e9fe2bbc";


            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url_Film_1, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Title_1_View.setText("          " + response.getString("original_title"));
                                pop_1_View.setText("          " + response.getString("popularity"));
                                String img = response.getString("poster_path");
                                Picasso.with(getBaseContext()).load("https://image.tmdb.org/t/p/original" + img).into(image1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Title_1_View.setText("erreur");

                        }
                    });
            queue.add(jsonObjectRequest);
            RequestQueue queue2 = Volley.newRequestQueue(this);
            final int finalFilm = Film2;
            JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest
                    (Request.Method.GET, url_Film_2, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Title_2_View.setText("          " + response.getString("original_title"));
                                pop_2_View.setText("          " + response.getString("popularity"));
                                String img = response.getString("poster_path");
                                Picasso.with(getBaseContext()).load("https://image.tmdb.org/t/p/original" + img).into(image2);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Title_2_View.setText("erreur");

                        }
                    });
            queue2.add(jsonObjectRequest2);
        Button C1 = (Button)findViewById(R.id.choix_1);
        Button C2 = (Button)findViewById(R.id.choix_2);
        final Button N = (Button) findViewById(R.id.next);


        C1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final float pop_1 = Float.parseFloat((String) pop_1_View.getText());
                final float pop_2= Float.parseFloat((String) pop_2_View.getText());
                if ( pop_2 < pop_1){
                    resultat.setText("GAGNER");
                    resultat.setVisibility(View.VISIBLE);
                    pop_1_View.setVisibility(View.VISIBLE);
                    pop_2_View.setVisibility(View.VISIBLE);
                    N.setVisibility(View.VISIBLE);
                }
                else {
                    resultat.setText("PERDU !");
                    resultat.setVisibility(View.VISIBLE);
                    pop_1_View.setVisibility(View.VISIBLE);
                    pop_2_View.setVisibility(View.VISIBLE);
                    N.setVisibility(View.VISIBLE);
                }
            }
        });
        C2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final float pop_1 = Float.parseFloat((String) pop_1_View.getText());
                final float pop_2= Float.parseFloat((String) pop_2_View.getText());
                if ( pop_2 > pop_1){
                    resultat.setText("GAGNER");
                    resultat.setVisibility(View.VISIBLE);
                    pop_1_View.setVisibility(View.VISIBLE);
                    pop_2_View.setVisibility(View.VISIBLE);
                    N.setVisibility(View.VISIBLE);
                }
                else {
                    resultat.setText("PERDU !");
                    resultat.setVisibility(View.VISIBLE);
                    pop_1_View.setVisibility(View.VISIBLE);
                    pop_2_View.setVisibility(View.VISIBLE);
                    N.setVisibility(View.VISIBLE);
                }
            }
        });
        N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if ((String) resultat.getText() == "GAGNER"){
                    int pg = Integer.parseInt((String) Score_view.getText());
                    pg += 1;
                }
                int pj = Integer.parseInt((String) pj_view.getText());
                if (pj==10){
                    Intent intent = new Intent(Game_1.this, Fin.class);
                    intent.putExtra("Partie Jouer", pj);
                    intent.putExtra("Partie Gagné", pg);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Game_1.this, Game_1.class);
                    intent.putExtra("Partie Jouer", pj);
                    intent.putExtra("Partie Gagné", pg);
                    startActivity(intent);
                }

            }
        });


// Access the RequestQueue through your singleton class.
        // MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
