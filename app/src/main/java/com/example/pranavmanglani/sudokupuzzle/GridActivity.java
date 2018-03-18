package com.example.pranavmanglani.sudokupuzzle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

//class MyTextWatcher implements TextWatcher{
//    private EditText mEditText;
//    public MyTextWatcher(EditText editText){
//        mEditText=editText;
//    }
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//}


public class GridActivity extends AppCompatActivity {

    private static EditText et;
    LinearLayout hLinearLayout;
    EditText editText;
    LinearLayout hll;

    String numbers;
    String level;
    int tag;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.solveSudoku:
                Chronometer chronometer= (Chronometer) findViewById(R.id.chronometer);
                chronometer.stop();
                Intent intent = new Intent(this, SudokuSolverActivity.class);
                intent.putExtra("number",numbers);
                intent.putExtra("intent","GridActivity");
                startActivity(intent);
                return true;
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent i = new Intent(this, SubLevelActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.app_bar,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);



        Intent intent=getIntent();
        numbers=intent.getStringExtra("numbers");
        level=intent.getStringExtra("level");
        tag=intent.getIntExtra("tag",21);

        LinearLayout puzzleLL= (LinearLayout) findViewById(R.id.puzzleLinearLayout);
        puzzleLL.setVisibility(View.VISIBLE);
        Chronometer chronometer= (Chronometer) findViewById(R.id.chronometer);
        chronometer.start();

        LinearLayout vLinearLayout = (LinearLayout) findViewById(R.id.linearVertical);

        for(int i=0;i<vLinearLayout.getChildCount();i++) {
            hLinearLayout= (LinearLayout) vLinearLayout.getChildAt(i);
            for(int j=0;j<hLinearLayout.getChildCount();j++) {
                editText= (EditText) hLinearLayout.getChildAt(j);
                editText.setBackgroundColor(Color.TRANSPARENT);
                if(numbers.charAt(9*i+j)!='0'){
                    editText.setText(String.valueOf(numbers.charAt(9*i+j)));
                    editText.setEnabled(false);
                }
            }
        }
    }

//    boolean validate1(EditText editTextCheck, CharSequence s, LinearLayout hll, LinearLayout vll){
//        LinearLayout hllCheck= (LinearLayout) vll.getFocusedChild();
//        String[][] grid=new String[9][9];
//        for(int i=0;i<vll.getChildCount();i++) {
//            hll= (LinearLayout) vll.getChildAt(i);
//            for(int j=0;j<hll.getChildCount();j++) {
//                et= (EditText) hll.getChildAt(j);
//                if(et.getText()!=null)
//                    grid[i][j]= String.valueOf(et.getText());
//                else
//                    grid[i][j]="0";
//            }
//        }
//
//        int x=vll.indexOfChild(hllCheck);
//        Toast.makeText(GridActivity.this, String.valueOf(x), Toast.LENGTH_SHORT).show();
//        int y=hll.indexOfChild(editTextCheck);
//        Toast.makeText(GridActivity.this, String.valueOf(y), Toast.LENGTH_SHORT).show();
//        if(isRowValid1(grid,s,x,y)&&isBoxValid1(grid,s,x,y)&&isColumnValid1(grid,s,x,y))
//            return true;
//        else
//            return false;
//    }
//


    private static boolean isRowValid1(String[][] grid, CharSequence s, int x, int y) {
        for(int i=0;i<9;i++){
            if(i!=y) {
                if (grid[x][i] == s.toString()) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isColumnValid1(String[][] grid, CharSequence s, int x, int y) {
        for(int i=0;i<9;i++){
            if(i!=x) {
                if (grid[i][y] == s.toString()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isBoxValid1(String[][] grid, CharSequence s, int x, int y) {
        for(int i=3*(x/3);i<3*(x/3+1);i++){
            for(int j=3*(y/3);j<3*(y/3+1);j++){
                if(i!=x&&j!=y){
                    if(grid[i][j]==s.toString())
                        return false;
                }
            }
        }
        return true;
    }

    public void textChange(View view) {
        final EditText editTextCheck= (EditText) view;
        LinearLayout hll= (LinearLayout) editTextCheck.getParent();
        final LinearLayout vll= (LinearLayout) hll.getParent();
        editTextCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(GridActivity.this, s, Toast.LENGTH_SHORT).show();
                        String id= getResources().getResourceEntryName(editTextCheck.getId());
                        String x= id.substring(8,9);
                        String y= id.substring(9);
                        if(validate2(s,editTextCheck,Integer.parseInt(x),Integer.parseInt(y),vll)){
                            editTextCheck.setTextColor(Color.GREEN);
                        }
                        else
                            editTextCheck.setTextColor(Color.RED);
            }

            @Override
            public void afterTextChanged(Editable s) {
//                String id= getResources().getResourceEntryName(editTextCheck.getId());
//                String x= id.substring(8,9);
//                String y= id.substring(9);
//                if(validate2(s,editTextCheck,Integer.parseInt(x),Integer.parseInt(y),vll)){
//                    editTextCheck.setTextColor(Color.GREEN);
//                }
//                else
//                    editTextCheck.setTextColor(Color.RED);
            }
        });
    }

    private boolean validate2(CharSequence s, EditText editTextCheck, int x, int y, LinearLayout vll) {

        String[][] grid=new String[9][9];
        for(int i=0;i<vll.getChildCount();i++) {
            hll= (LinearLayout) vll.getChildAt(i);
            for(int j=0;j<hll.getChildCount();j++) {
                et= (EditText) hll.getChildAt(j);
                if(TextUtils.isEmpty(et.getText()))
                    grid[i][j]= String.valueOf(et.getText());
                else
                    grid[i][j]="0";
            }
        }
//        Toast.makeText(GridActivity.this, String.valueOf(x), Toast.LENGTH_SHORT).show();
//        Toast.makeText(GridActivity.this, String.valueOf(y), Toast.LENGTH_SHORT).show();
        if(isRowValid1(grid,s,x,y)&&isBoxValid1(grid,s,x,y)&&isColumnValid1(grid,s,x,y))
            return true;
        else
            return false;
    }


    public void getHint(View view) {
    }


    public void submit(View view) {
        Chronometer chronometer= (Chronometer) findViewById(R.id.chronometer);
        chronometer.stop();
        String time= String.valueOf(chronometer.getText());

        Toast.makeText(GridActivity.this, time, Toast.LENGTH_SHORT).show();

        DatabaseHelper db=new DatabaseHelper(getApplicationContext(),level);
        Puzzle puzzle=db.getPuzzle(tag);
        puzzle.setTime(time);
        db.updatePuzzle(puzzle);
    }

//    public void setNumber(View view) {
//
//    }


}
