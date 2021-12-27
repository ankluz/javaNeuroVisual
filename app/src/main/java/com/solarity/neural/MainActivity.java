package com.solarity.neural;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public static Neuron[][] neuro_web;
    public static int num_layers;
    public static int num_input;
    public static float[][] data;
    public Button btn;
    public static void generateNet(int[] layers)
    /*
        * Принимает на вход массив целочисленных значений для генерации слоев.
     */
    {
        neuro_web = new Neuron[num_layers][];
        data = new float[num_layers][];
        for (int i = 0;i< num_layers; i++){
            neuro_web[i] = new Neuron[layers[i]];
            data[i] = new float[layers[i]];
        }
        for (int i = 1;i<neuro_web.length;i++){
            for (int j = 0; j < neuro_web[i].length;j++){
                neuro_web[i][j] = new Neuron(neuro_web[i-1].length);
            }
        }
        for (int i = 0;i<neuro_web[0].length;i++){
            neuro_web[0][i] = new Neuron(num_input);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        num_layers = 3;
        num_input = 4;
        btn = (Button) findViewById(R.id.Start);
        generateNet(new int[] {6,4,1});
    }

    @Override
    protected void onStart(){
        super.onStart();
        btn.setOnClickListener(this::Start);
    }
    protected void Start(View v){
            float[] input = new float[num_input];

            EditText buf;
            LinearLayout input_View = findViewById(R.id.input);
            for (int i=0;i<input_View.getChildCount();i++){
                // я получаю из лэйаута ребенка по номеру его в списке в виде editText,
                // из которого я должен получить текст в виде editable
                // который я паршу в стринг,
                // который я паршу в float
                buf = (EditText) input_View.getChildAt(i);
                input[i] = Float.parseFloat(buf.getText().toString());
                // Я чертов гений
            }
            for (int i =0;i<neuro_web.length;i++){
                for (int j=0;j<neuro_web[i].length;j++){
                    if (i==0){
                        data[i][j] = neuro_web[i][j].activation(input);
                    }
                    else{
                        data[i][j] = neuro_web[i][j].activation(data[i-1]);
                    }
                }
            }
            LinearLayout hiden = findViewById(R.id.hiden);
            LinearLayout curLayout;
            for (int i=0;i<data.length-1;i++) {
                curLayout = (LinearLayout) ((LinearLayout) hiden.getChildAt(i)).getChildAt(0);
                for (int j=0;j<data.length;j++) {
                    ((EditText) hiden.getChildAt(i)).setText(Float.toString(data[i][j]));
                }
            }

        }
    }

