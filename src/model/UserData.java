package model;

public class UserData {

    private int user_id;
  public int getId(){
      return user_id;
  }
  public void setId(int id){
  this.user_id=id;}
    

private String username;

public String getUsername(){
return username;}

public void setUsername(String username){
this.username=username;}

private String email;

public String getEmail(){
return email;}

public void setEmail(String email){
this.email=email;}

private String password;

public String getPassword(){
return password;}
public void setPassword(String password){
this.password=password;}
public UserData(String username, String email, String password){
this.username=username;
this.email=email;
this.password=password;
}

        
}