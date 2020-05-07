package codes.neuralkatana.dolarparareal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import codes.neuralkatana.dolarparareal.json.AwesomeApiQuotation;
import codes.neuralkatana.dolarparareal.model.Currency;
import codes.neuralkatana.dolarparareal.model.USD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private USD usd;
    private EditText dolar;
    private TextView tvDolar;
    private TextView tvReal;
    private String baseURL = "https://economia.awesomeapi.com.br/all/";

    //ButtonAction
    public void converterDolarParaReal(View view){
        dolar = findViewById(R.id.editText);
        tvDolar = findViewById(R.id.textViewDolar);
        tvReal = findViewById(R.id.textViewReal);
        double bid = Double.parseDouble(usd.getBid());
        double dolarValue = Double.parseDouble(dolar.getText().toString());
        double realValue = dolarValue*bid;

        String dolarString = String.format("%.2f",dolarValue);
        String realString = String.format("%.2f",realValue);

        tvDolar.setText("$"+dolarString);
        tvReal.setText("R$"+realString);

    }

    //starter for the RetroFit
    private void retroFitStarter(){
        //URL - Preparation
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //API Interface Preparation
        AwesomeApiQuotation awesome = retrofit.create(AwesomeApiQuotation.class);
        //Call to the URL Preparation
        Call<Currency> call = awesome.getDollarQuotation();

        //Async Callback to the URL
        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                if(!response.isSuccessful()){
                    Log.e("Callback Error",""+response.code());
                    return;
                }
                //Getting USD Object
                usd = response.body().getUSD();
                Toast.makeText(MainActivity.this, "Dolar Hoje:"+usd.getBid(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Log.e("Callback Error",t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Starting the Retrofit to GET the REST API
        this.retroFitStarter();
    }
}
