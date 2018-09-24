package com.example.nikhilkgupta.pt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikhilkgupta.pt.APICalls.APIClient;
import com.example.nikhilkgupta.pt.APICalls.APIInterface;
import com.example.nikhilkgupta.pt.APICalls.Request;
import com.example.nikhilkgupta.pt.APICalls.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private Button btn;
    String s;
    String[] str = null;

    //API calls Code starts
    private APIInterface mApiInterface;
    String baseURL = "http://192.168.43.137:8080/api/webapi/";
    String url = "PTdata/enterdata";
    final Gson gson = new Gson();
    private int responseCode;
    private Map<String, String> donarHeader = new HashMap<>();
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        btn = (Button) findViewById(R.id.button);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });



        donarHeader.put("Accept", "application/json");
        donarHeader.put("Content-Type", "application/json");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length == 6){
                request = makeRequestPOJO();
                mApiInterface = APIClient.getClient(baseURL).create(APIInterface.class);
                final Call<Response> userVerifyCall = mApiInterface.getUserVerify(donarHeader, url, request);

                userVerifyCall.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        String printResponse = gson.toJson(response.body());
                        responseCode = response.code();
                        Log.d("Voice Success", printResponse);

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.d("Voice Failed", "Voice Failed");
                    }
                });
                }else{
                    Toast.makeText(getApplicationContext(),"Please check your input", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
//    private void sendData2Server()
//    {
//        final JSONObject root = new JSONObject();
//        try{
//            JSONArray data =new JSONArray();
////            data.put
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    mVoiceInputTv.setText(result.get(0));
                    s=result.get(0);
                    mVoiceInputTv.setText(s);
                    Log.d("Nikhil",s);

                    str = s.split(" ");
                }
                break;
            }

        }
    }

    public Request makeRequestPOJO() {
        Request request = new Request();
            request.setPtStartTime(str[1]);
            request.setPtTestName(str[3]);
            request.setTestId(str[5]);
        return request;
        }

}
