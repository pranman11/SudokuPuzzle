package com.example.pranavmanglani.sudokupuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        ListView levels= (ListView) findViewById(R.id.listView);

        String[] levelsString={"Very Easy","Easy","Medium","Difficult","Proffesional"};
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(LevelActivity.this,android.R.layout.simple_list_item_1,levelsString);

        levels.setAdapter(arrayAdapter);
        levels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String abc = (String) parent.getItemAtPosition(position);
//                if (abc.equals("Very Easy"))
                if (position==0){
                    int []sublevels = new int[20];
                    for(int j=0;j<20;j++)
                        sublevels[j]=j+1;
                    Intent i = new Intent(LevelActivity.this,SubLevelActivity.class);
                    i.putExtra("level","Very Easy");
                    i.putExtra("sublevels",sublevels);
                    startActivity(i);
                }
                else if(position==1){
                    int []sublevels = new int[20];
                    for(int j=0;j<20;j++)
                        sublevels[j]=j+21;
                    Intent i = new Intent(LevelActivity.this,SubLevelActivity.class);
                    i.putExtra("level","Easy");
                    i.putExtra("sublevels",sublevels);
                    startActivity(i);
                }
                else if(position==2){
                    int []sublevels = new int[20];
                    for(int j=0;j<20;j++)
                        sublevels[j]=j+41;
                    Intent i = new Intent(LevelActivity.this,SubLevelActivity.class);
                    i.putExtra("level","Medium");
                    i.putExtra("sublevels",sublevels);
                    startActivity(i);
                }
                else if(position==3) {
                    int []sublevels = new int[20];
                    for(int j=0;j<20;j++)
                        sublevels[j]=j+61;
                    Intent i = new Intent(LevelActivity.this, SubLevelActivity.class);
                    i.putExtra("level","Difficult");
                    i.putExtra("sublevels",sublevels);
                    startActivity(i);
                }
                else if(position==4){
                    int []sublevels = new int[20];
                    for(int j=0;j<20;j++)
                        sublevels[j]=j+81;
                    Intent i = new Intent(LevelActivity.this,SubLevelActivity.class);
                    i.putExtra("level","Proffesional");
                    i.putExtra("sublevels",sublevels);
                    startActivity(i);
                }
            }
        });
    }
}
