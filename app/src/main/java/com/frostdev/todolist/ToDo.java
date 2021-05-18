package com.frostdev.todolist;

public class ToDo {

    String namaToDo;
    String StatusTodo;
    String idTodo;
    public ToDo(){

    }

    public String getNamaToDo() {
        return namaToDo;
    }

    public void setNamaToDo(String namaToDo) {
        this.namaToDo = namaToDo;
    }

    public String getStatusTodo() {
        return StatusTodo;
    }

    public void setStatusTodo(String statusTodo) {
        StatusTodo = statusTodo;
    }

    public String getmTodo() {
        return idTodo;
    }

    public void setmTodo(String mTodo) {
        this.idTodo = mTodo;
    }

    public ToDo(String namaToDo, String statusTodo, String mTodo) {
        this.namaToDo = namaToDo;
        StatusTodo = statusTodo;
        this.idTodo = mTodo;
    }
}
