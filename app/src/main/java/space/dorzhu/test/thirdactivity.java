package space.dorzhu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.ConversationAction;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.channels.AsynchronousByteChannel;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class thirdactivity extends AppCompatActivity {
      // TextView textView;
    public custom_list adapter;
    XmlParser parser = new XmlParser();
    ListView listView;


    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdactivity);
        listView= findViewById(R.id.setadapter);
        DownloadData downloadData = new DownloadData();
        downloadData.execute("https://www.cbr.ru/scripts/XML_daily.asp?date_req=09/11/2020");
    }

    public void vivod(){
        adapter=new custom_list(this,parser.getCourses());
        listView.setAdapter((ListAdapter) adapter);
    }

    public void vihod(View view) {
        finish();
    }

    private class DownloadData extends AsyncTask<String,Void,String> implements space.dorzhu.test.DownloadData {
        private static final String TAG="DownloadFile";



        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            Log.d(TAG, "xml document: "+s);
            if(s!=null && parser.parse(s)){
                vivod();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try{
                content = downloadXML(strings[0]);
            }catch (IOException ex){
                Log.e(TAG, "downloadXML: IO Exception reading data: " + ex.getMessage());
            }
            return content;
        }
        private String downloadXML(String urlPath) throws IOException{
            StringBuilder xmlResult = new StringBuilder();
            BufferedReader reader = null;
            try{
                URL url = new URL(urlPath);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                String line = null;
                while ((line = reader.readLine())!=null){
                    xmlResult.append(line);
                }
                return xmlResult.toString();
            }catch (MalformedURLException e){
                Log.e(TAG, "downloadXML: InvalidUrl" + e.getMessage());
            }catch(IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            } catch(SecurityException e) {
                Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.getMessage());
            }
            finally {
                if(reader != null){
                    reader.close();
                }
            }
            return null;
    }

}
}