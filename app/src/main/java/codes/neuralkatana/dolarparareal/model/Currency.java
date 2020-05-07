package codes.neuralkatana.dolarparareal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Currency is only used to grab the USD object via GSON
public class Currency {

    @SerializedName("USD")
    @Expose
    private USD uSD;

    public USD getUSD() {
        return uSD;
    }

    public void setUSD(USD uSD) {
        this.uSD = uSD;
    }

}