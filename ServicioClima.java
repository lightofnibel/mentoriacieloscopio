import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class ServicioClima {

    private static final String API_KEY = "92a0ac25104da40d2f4b46459bd25dcb";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public String obtenerClima(String ciudad) throws Exception{
        String respuestaJson = obtenerDatosClima(ciudad);
        JsonObject jsonObject = JsonParser.parseString(respuestaJson).getAsJsonObject();
        String nombreCiudad = jsonObject.get("name").getAsString();
        double temperatura = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        return "Ciudad: " + nombreCiudad + ", Temperatura: " + temperatura+ "C";
    }

    private String obtenerDatosClima(String ciudad) throws Exception{
        String url = BASE_URL + "?q=" + ciudad + "&appid=" + API_KEY + "&units=metric";

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet request = new HttpGet(url);
            try(CloseableHttpResponse response = httpClient.execute(request)){
                HttpEntity entity = response.getEntity();
                if(entity != null){
                    return EntityUtils.toString(entity);
                }
            }
        }

        return null;
    }

}
