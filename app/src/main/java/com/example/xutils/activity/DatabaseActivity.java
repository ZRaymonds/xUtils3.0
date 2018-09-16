package com.example.xutils.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xutils.R;
import com.example.xutils.bean.xUtils;
import com.example.xutils.db.PersonTable;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.List;

@ContentView(R.layout.activity_database)
public class DatabaseActivity extends AppCompatActivity {

    DbManager.DaoConfig daoConfig = xUtils.getDaoConfig();
    DbManager db = x.getDb(daoConfig);


    @ViewInject(R.id.btn_insert)
    Button btn_insert;

    @ViewInject(R.id.btn_update)
    Button btn_update;

    @ViewInject(R.id.btn_select)
    Button btn_select;

    @ViewInject(R.id.btn_delete)
    Button btn_delete;

    @ViewInject(R.id.btn_deleteDB)
    Button btn_deleteDB;

    @ViewInject(R.id.tv_showDatas)
    TextView tv_showDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event({R.id.btn_insert,R.id.btn_update,R.id.btn_select,R.id.btn_delete,R.id.btn_deleteDB})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_select:
                select();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_deleteDB:
                deleteDB();
                break;
        }
    }

    /**
     * 删除数据库
     * */
    private void deleteDB() {
        try {
            db.dropDb();
            Toast.makeText(this,"删除数据库成功-->",Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据
     * */
    private void delete() {
        try {
            /**
             * 根据id删除
             * */
     /*       db.deleteById(PersonTable.class,0);
            Toast.makeText(this,"删除成功--->",Toast.LENGTH_SHORT).show();*/
            /**
             * 特定删除
             * */
           /* PersonTable person = db.selector(PersonTable.class).where("name","=","小丽").findFirst();
            db.delete(person);
            Toast.makeText(this,"删除成功--->",Toast.LENGTH_SHORT).show();*/
           /**
            * where条件删除
            * */
         /*   db.delete(PersonTable.class, WhereBuilder.b("sex","=","woman").and("salary","=","5000"));
            Toast.makeText(this,"删除成功--->",Toast.LENGTH_SHORT).show();*/
         /**
          * 删除所有
          * */
            db.delete(PersonTable.class);
            Toast.makeText(this,"删除成功--->",Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            e.printStackTrace();
            Toast.makeText(this,"删除失败--->",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查询数据
     * */
    private void select() {
        try {
            /**
             * 根据id查询
             * */
              /*  PersonTable person = db.findById(PersonTable.class,"0");
                Toast.makeText(this,"查询成功--->"+person.toString(),Toast.LENGTH_SHORT).show();
                tv_showDatas.setText("查询成功--->"+person.toString());*/

            /**
             * 查询第一条
             * */
           /* PersonTable person = db.findFirst(PersonTable.class);
            Log.i("TAG",person.toString());
            Toast.makeText(this,"查询成功--->"+person.toString(),Toast.LENGTH_SHORT).show();
            tv_showDatas.setText("查询成功--->"+person.toString());*/
           /**
            * 查询所有
            * */
            List<PersonTable> person = db.findAll(PersonTable.class);
            Log.i("TAG",person.toString());
            Toast.makeText(this,"查询成功--->"+person.toString(),Toast.LENGTH_SHORT).show();
            tv_showDatas.setText("查询成功--->"+person.toString());
           /**
            * 特定查询
            * */
         /*  List<PersonTable> persons = db.selector(PersonTable.class).where("age",">",15).and("sex","=","woman").findAll();
            for (PersonTable person : persons){
                Log.i("TAG",person.toString());
                Toast.makeText(this,"查询成功--->"+person.toString(),Toast.LENGTH_SHORT).show();
                tv_showDatas.setText("查询成功--->"+person.toString());
            }*/
        } catch (DbException e) {
            e.printStackTrace();
            Toast.makeText(this,"查询数据失败--->"+e.getMessage(),Toast.LENGTH_SHORT).show();
            Log.i("TAG",e.getMessage());
        }
    }

    /**
     * 修改数据
     * */
    private void update() {
        try {
            List<PersonTable> persons = db.findAll(PersonTable.class);
            for (PersonTable person : persons){
                person.setSalary(8000);
                db.update(person, String.valueOf(WhereBuilder.b("sex","=","woman")),"salary");
                Toast.makeText(this,"修改数据成功--->",Toast.LENGTH_SHORT).show();
            }
        } catch (DbException e) {
            e.printStackTrace();
            Toast.makeText(this,"修改数据失败--->"+e.getMessage(),Toast.LENGTH_SHORT).show();
            Log.i("TAG",e.getMessage());
        }


    }

    /**
     * 插入数据
     * */
    private void insert() {
        try {
            PersonTable person = new PersonTable();
            person.setName("小丽");
            person.setAge(19);
            person.setSex("woman");
            person.setSalary(5000);
            db.save(person);
            Toast.makeText(this,"插入数据成功--->"+person.toString(),Toast.LENGTH_SHORT).show();
            Log.i("TAG",person.toString());
            tv_showDatas.setText("插入数据成功--->"+person.toString());
        } catch (DbException e) {
            e.printStackTrace();
            Toast.makeText(this,"插入数据失败--->"+e.getMessage(),Toast.LENGTH_SHORT).show();
            Log.i("TAG",e.getMessage());
        }
    }
}
