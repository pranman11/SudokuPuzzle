package com.example.pranavmanglani.sudokupuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubLevelActivity extends AppCompatActivity {

    DatabaseHelper db;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);

        Intent intent= getIntent();
        int subLevelsInt[] = intent.getIntArrayExtra("sublevels");
        final String level=intent.getStringExtra("level");
        Log.i("level",level);

        String []puzzleString=new String[20];

        if(level.equals("Very Easy")){
            puzzleString=getResources().getStringArray(R.array.very_easy_puzzle);
        } else if(level.equals("Easy")){
            puzzleString=getResources().getStringArray(R.array.easy_puzzle);
        } else if (level.equals("Medium")){
            puzzleString=getResources().getStringArray(R.array.medium_puzzle);
        } else if(level.equals("Difficult")){
            puzzleString=getResources().getStringArray(R.array.difficult_puzzle);
        } else if (level.equals("Proffesional")){
            puzzleString=getResources().getStringArray(R.array.proffesional_puzzle);
        }


        db = new DatabaseHelper(getApplicationContext(),level);
        boolean flag=false;

        Log.i("Insert:","Inserting...");
        for(int i=0;i<20;i++)
            flag=db.createPuzzle(new Puzzle(i+1,level,puzzleString[i],"0000"));

        Log.i("db",String.valueOf(flag));

        ListView subLevels= (ListView) findViewById(R.id.listView2);
        String[] subLevelsString = new String[20];
        for(int i=0;i<subLevelsString.length;i++)
            subLevelsString[i]="Level "+subLevelsInt[i];


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(SubLevelActivity.this,android.R.layout.simple_list_item_1,subLevelsString);
        subLevels.setAdapter(arrayAdapter);

        subLevels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newGame=new Intent(getApplicationContext(),GridActivity.class);
                if(level.equals("Very Easy")) {
                    Puzzle puzzle = db.getPuzzle(position + 1);
                    newGame.putExtra("numbers", puzzle.getNumbers());
                    newGame.putExtra("level",level);
                    newGame.putExtra("tag",position+1);
                    startActivity(newGame);
                }
                else if(level.equals("Easy")){
                    Puzzle puzzle = db.getPuzzle(position + 1);
                    newGame.putExtra("numbers", puzzle.getNumbers());
                    newGame.putExtra("level",level);
                    newGame.putExtra("tag",position+1);
                    startActivity(newGame);
                }
                else if(level.equals("Medium")){
                    Puzzle puzzle = db.getPuzzle(position + 1);
                    newGame.putExtra("numbers", puzzle.getNumbers());
                    newGame.putExtra("level",level);
                    newGame.putExtra("tag",position+1);
                    startActivity(newGame);
                }
                else if(level.equals("Difficult")){
                    Puzzle puzzle = db.getPuzzle(position + 1);
                    newGame.putExtra("numbers", puzzle.getNumbers());
                    newGame.putExtra("level",level);
                    newGame.putExtra("tag",position+1);
                    startActivity(newGame);
                }
                else if(level.equals("Proffesional")){
                    Puzzle puzzle = db.getPuzzle(position + 1);
                    newGame.putExtra("numbers", puzzle.getNumbers());
                    newGame.putExtra("level",level);
                    newGame.putExtra("tag",position+1);
                    startActivity(newGame);
                }
            }
        });
    }
}
