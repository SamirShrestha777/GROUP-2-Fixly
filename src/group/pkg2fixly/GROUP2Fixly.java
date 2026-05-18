
package group.pkg2fixly;

import database.MySqlConnector;
import database.db;


public class GROUP2Fixly {

  
    public static void main(String[] args) {
         db data = new MySqlConnector();

       if(data.openConnection() !=null){

           System.out.println("Connection succesful");

         

       }else{

           System.out.println("Not successful");
    }

    }
    
}
