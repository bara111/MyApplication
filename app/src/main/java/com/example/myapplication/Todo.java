package com.example.myapplication;

public class Todo {
   private boolean completed;
   private String name;
   private String userid;

   public Todo() {

   }
   public Todo(boolean completed, String name, String userid) {
      this.completed = completed;
      this.name = name;
      this.userid = userid;
   }

   public boolean isCompleted() {
      return completed;
   }

   public void setCompleted(boolean completed) {
      this.completed = completed;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }
}
