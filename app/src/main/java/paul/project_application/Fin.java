package paul.project_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Fin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin);

        Intent intent = getIntent();
        int pj = intent.getIntExtra("Partie Jouer",0);
        int pg = intent.getIntExtra("Partie Gagné", 0);

        final TextView Score_view = (TextView) findViewById(R.id.Score);
        Score_view.setText("Vous avez repondue juste a "+pg+" sur 10");
        Button b = (Button)findViewById(R.id.menu);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    Intent intent = new Intent(Fin.this, MainActivity.class);
                    startActivity(intent);
            }
        });
    }
}
