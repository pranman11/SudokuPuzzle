package com.example.pranavmanglani.sudokupuzzle;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SudokuSolverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        Intent intent=getIntent();
        String name=intent.getStringExtra("intent");
        Log.i("intent",name);
        if(name.equals("GridActivity")){
            String numbers=intent.getStringExtra("number");

            LinearLayout vLinearLayout = (LinearLayout) findViewById(R.id.linearVertical);
            LinearLayout hLinearLayout;
            EditText editText;

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


        LinearLayout linearSolverButtons= (LinearLayout) findViewById(R.id.linearSolverButton);
        linearSolverButtons.setVisibility(View.VISIBLE);

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Enter the digits in each square. Press CLOSE to enter in grid. Press ENTER STRING to enter string of all digits(0 for blank spaces).");
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("ENTER STRING", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setString= (LinearLayout) findViewById(R.id.setString);
                setString.setVisibility(View.VISIBLE);
            }
        });

        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
    LinearLayout setString;

    public void setString(View view) {
        EditText numbers= (EditText) findViewById(R.id.numbers);
        String numString=numbers.getText().toString();
        Toast.makeText(SudokuSolverActivity.this, numString, Toast.LENGTH_SHORT).show();
        setString.setVisibility(View.INVISIBLE);
    }
    int[][] numberGrid=new int[9][9];

    public void solve(View view) {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                numberGrid[i][j]=Integer.parseInt(grid[i][j]);
            }
        }
        sudokuSolver(numberGrid);
    }

    String[][] grid=new String[9][9];

    public void setGrid(View view) {
        LinearLayout vll= (LinearLayout) findViewById(R.id.linearVertical);
        LinearLayout hll;
        EditText et;
        for(int i=0;i<vll.getChildCount();i++) {
            hll= (LinearLayout) vll.getChildAt(i);
            for(int j=0;j<hll.getChildCount();j++) {
                et= (EditText) hll.getChildAt(j);
                if(!(TextUtils.isEmpty(et.getText()))) {
                    grid[i][j] = String.valueOf(et.getText());
                    et.setBackgroundColor(Color.TRANSPARENT);
                    et.setEnabled(false);
                    et.setTextColor(Color.GREEN);
                }
                else
                    grid[i][j]="0";
            }
        }
//        StringBuffer stringBuffer=new StringBuffer("");
//        for(int i=0;i<9;i++){
//            for(int j=0;j<9;j++){
//                stringBuffer.append(grid[i][j]);
//            }
//        }
//        Log.i("numbers are:", String.valueOf(stringBuffer));

    }
    private static boolean isFull(int [][]grid){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(grid[i][j]==0){
//					System.out.println("Board yet unsolved");
                    return false;
                }
            }
        }
        return true;
    }
    private static int[] possibleEntries(int [][]grid,int x,int y){
        int possibilities[]= new int[10];
        for(int k=0;k<9;k++)
            possibilities[k]=0;
        for(int i=0;i<9;i++){					//row
            if(grid[x][i]!=0)
                possibilities[grid[x][i]]=1;
        }
        for(int i=0;i<9;i++){					//column
            if(grid[i][y]!=0)
                possibilities[grid[i][y]]=1;
        }
        for(int i=3*(x/3);i<3*(x/3+1);i++){		//box
            for(int j=3*(y/3);j<3*(y/3+1);j++){
                if(grid[i][j]!=0)
                    possibilities[grid[i][j]]=1;
            }
        }
        for(int k=0;k<=9;k++){
            if(possibilities[k]==0)
                possibilities[k]=k;
            else
                possibilities[k]=0;
        }
        return possibilities;
    }
    private void sudokuSolver(int grid[][]){
        int x=0,y=0;
        int []possibilities=new int[10];
        if(isFull(grid)){
            LinearLayout vll= (LinearLayout) findViewById(R.id.linearVertical);
            LinearLayout hll;
            EditText et;
            for(int i=0;i<vll.getChildCount();i++) {
                hll = (LinearLayout) vll.getChildAt(i);
                for (int j = 0; j < hll.getChildCount(); j++) {
                    et = (EditText) hll.getChildAt(j);
                    if ((TextUtils.isEmpty(et.getText()))) {
                        et.setText(String.valueOf(grid[i][j]));
                        et.setBackgroundColor(Color.TRANSPARENT);
                        et.setEnabled(false);
                    }
                }
            }
            return;
        }
        else{
            outerloop:
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    if(grid[i][j]==0){
                        x=i; y=j; break outerloop;
                    }
                }
            }
            possibilities=possibleEntries(grid,x,y);
			/*for(int k:possibilities){
				System.out.print(k+"\t");
				System.out.println();
			}*/
            for(int k:possibilities){
                if(k!=0){
                    grid[x][y]=k;
                    sudokuSolver(grid);
                }
            }
            grid[x][y]=0;
        }
    }
    public void clear(View view) {
        LinearLayout vll= (LinearLayout) findViewById(R.id.linearVertical);
        LinearLayout hll;
        EditText et;
        for(int i=0;i<vll.getChildCount();i++) {
            hll = (LinearLayout) vll.getChildAt(i);
            for (int j = 0; j < hll.getChildCount(); j++) {
                et = (EditText) hll.getChildAt(j);
                if (!(TextUtils.isEmpty(et.getText()))) {
                    et.setText("");
//                    et.setBackgroundColor(Color.RED);
                    et.setEnabled(true);
                }
            }
        }
    }
}
