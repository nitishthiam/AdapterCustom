package com.india.nitish.adaptercustom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText empno,empname,empsal;
    Button addbutton;
    ListView listView;
    ArrayList<Emp> arrayList;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empno = (EditText) findViewById(R.id.editText);
        empname = (EditText) findViewById(R.id.editText2);
        empsal = (EditText) findViewById(R.id.editText3);
        addbutton = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<Emp>();
        myAdapter = new MyAdapter();

        listView.setAdapter(myAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                View v = listView.getChildAt(position);
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);
                if(checkBox.isChecked())
                {
                    Emp e = arrayList.get(position);
                    Toast.makeText(MainActivity.this,e.getEno()+".."+e.getEname()+".."+e.getEsal(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"please select box",Toast.LENGTH_SHORT).show();

                }
                return  true;
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eno = empno.getText().toString();
                String ename = empname.getText().toString();
                String esal = empsal.getText().toString();

                Emp e = new Emp();

                e.setEno(eno);
                e.setEname(ename);
                e.setEsal(esal);

                arrayList.add(e);
                myAdapter.notifyDataSetChanged();
                empno.setText("");
                empname.setText("");
                empsal.setText("");
                empname.requestFocus();
            }
        });
    }
        public class MyAdapter extends BaseAdapter
        {
            public int getCount()
            {
                //return size of your source
                return arrayList.size();
            }
            public Object getItem(int pos)
            {
                return arrayList.get(pos);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                //load data
                Emp e = arrayList.get(position);
                //load row.xml
                View v = getLayoutInflater().inflate(R.layout.row,null);
                TextView textView = (TextView) v.findViewById(R.id.textview);
                TextView textView2 = (TextView) v.findViewById(R.id.textview2);
                TextView textView3 = (TextView) v.findViewById(R.id.textview3);
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);

                textView.setText(e.getEno());
                textView2.setText(e.getEname());
                textView3.setText(e.getEsal());

                return v;
            }
        }
}