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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class thirdactivity extends AppCompatActivity {
    TextView mainDate;
    XmlParser parser = new XmlParser();
    ListView listView;
    public KAdapter adapter;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdactivity);
        listView = findViewById(R.id.setadapter);
        DownloadData downloadData = new DownloadData();
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());//получаем дату
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.textView11);
        mainDate.setText(dateText);
        downloadData.execute("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" +dateText);



    }


//вывод
        public void vivod() {
            adapter = new KAdapter(this, parser.getCourses());
            listView.setAdapter(adapter);
        }
    //переход на главную страницу
    public void vihod(View view) {
        finish();
    }




    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadFile";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "xml document: " + s);
            if (s != null && parser.parse(s)) {
                vivod();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = downloadXML(strings[0]);
            } catch (IOException ex) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + ex.getMessage());
            }
            return content;
        }

        private String downloadXML(String urlPath) throws IOException {
            StringBuilder xmlResult = new StringBuilder();
            BufferedReader reader = null;
            try {
                URL url = new URL(urlPath);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    xmlResult.append(line);
                }
                return xmlResult.toString();
            } catch (MalformedURLException e) {
                Log.e(TAG, "downloadXML: InvalidUrl" + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            } catch (SecurityException e) {
                Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.getMessage());
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
            return null;
        }
    }

}

