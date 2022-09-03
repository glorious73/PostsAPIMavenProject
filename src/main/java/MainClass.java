import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class MainClass {
    public static void main(String []args) {
        try {
            JSONArray ja = PerformPostsGetHttpRequest("https://jsonplaceholder.typicode.com/posts");

            for (int i=0; i<ja.length(); i++) {
                JSONObject joPost = (JSONObject) ja.get(i);
                System.out.println("Title (" + (i+1) + "): " + joPost.get("title").toString());
            }
        }
        catch(IOException ex) {
            System.out.println("An error occurred while retrieving posts. Please try again.");
        }

    }

    public static JSONArray PerformPostsGetHttpRequest(String endPoint) throws IOException {
        //Creating a HttpClient object
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Creating a HttpGet object
        HttpGet httpget = new HttpGet(endPoint);

        //Printing the method used
        System.out.println("Request Type: "+httpget.getMethod());

        JSONArray jsonArray = null;
        try {
            //Executing the Get request
            HttpResponse httpresponse = httpclient.execute(httpget);
            // code and value
            System.out.println(httpresponse.getStatusLine());

            HttpEntity entity = httpresponse.getEntity();

            if (entity != null) {
                String retSrc = EntityUtils.toString(entity);
                // parsing JSON
                jsonArray = new JSONArray(retSrc); //Convert String to JSON Object
            }
        }
        catch(Exception ex) {
            throw ex;
        }

        return jsonArray;
    }
}
