package codes.neuralkatana.dolarparareal.json;

import codes.neuralkatana.dolarparareal.model.Currency;
import codes.neuralkatana.dolarparareal.model.USD;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AwesomeApiQuotation {
    //a GET request to the baseURL
    @GET("USD-BRL")
    Call<Currency> getDollarQuotation();
}
