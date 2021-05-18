package com.frostdev.todolist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {

    List<ToDo>list = new ArrayList<>();
    ToDoAdapter toDoAdapter;
    DatabaseHelper myDB;
    RecyclerView tvTodo;
    EditText edtsearchBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        myDB = new DatabaseHelper(getActivity());
        list.addAll(myDB.getAllData());

        edtsearchBar = v.findViewById(R.id.edtSearchBar);
        tvTodo = v.findViewById(R.id.tv_todo);

        tvTodo.setLayoutManager(new LinearLayoutManager(getActivity()));
        toDoAdapter = new ToDoAdapter(getActivity(),list);
        toDoAdapter.notifyDataSetChanged();
        tvTodo.setAdapter(toDoAdapter);

        edtsearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (edtsearchBar.length()!=0){
                    List<ToDo>searchlist = myDB.SearchData(edtsearchBar.getText().toString());
                    if (searchlist !=null) {
                        tvTodo.setAdapter(new ToDoAdapter(getActivity(), searchlist));
                    }
                    }else {
                        List<ToDo> searchlist = myDB.getAllData();
                        if (searchlist !=null){
                            tvTodo.setAdapter(new ToDoAdapter(getActivity(),searchlist));
                        }
                    }
                }
        });
        return v;
    }
}