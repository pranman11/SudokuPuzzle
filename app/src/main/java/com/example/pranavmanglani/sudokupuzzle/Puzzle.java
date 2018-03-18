package com.example.pranavmanglani.sudokupuzzle;

import android.util.Log;

/**
 * Created by Pranav Manglani on 28-02-2017.
 */
public class Puzzle {
    String level;
    int tag;
    String numbers;
    String time;
    String solved;

    public Puzzle(int tag,String level,String numbers, String time){
        this.level=level;
        this.tag=tag;
        this.numbers=numbers;
        this.time=time;
        this.solved="false";
    }

    public Puzzle() {
    }

    public void setLevel(String level){
        this.level=level;
    }
    public void setTag(int tag){
        this.tag=tag;
    }
    public void setNumbers(String numbers){
        this.numbers=numbers;
    }
    public String getNumbers(){
        return numbers;
    }
    public String getLevel(){
        return level;
    }
    public int getTag(){
        return tag;
    }
    public void setTime(String time){this.time=time;
        Log.i("time","updated");}
    public String getTime(){return time;}
    public String isSolved(){return solved;}
    public void setSolved(String solved){this.solved=solved;}

}
