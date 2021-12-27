package com.solarity.neural;

public class Neuron {
    public float[] weight;
    public int bias;

    public Neuron(int n){
        weight = new float[n];
        for (int i = 0; i < weight.length; i++) {
            weight[i] = (float) Math.round((Math.random() * 30) - 15);
            System.out.println(weight[i]);
        }
    }


    public float activation(float[] inp){
        float razr = 0;
        for (int i=0;i<inp.length;i++){
            razr += (inp[i]*weight[i])+bias;
        }
        razr = (float) (1/(1+ Math.exp(-razr)));
        return razr;
    }
}
