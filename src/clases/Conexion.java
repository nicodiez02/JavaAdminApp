/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nico
 */
public class Conexion {
    
    public static Connection conectar(){
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://b1ewsttkrfq2qgsqjlls-mysql.services.clever-cloud.com/b1ewsttkrfq2qgsqjlls", 
                        "utsjuqhtdofjqifb", "iwdIyw16h64hG1A6E0Nr");
            return cn;
            
        }catch(SQLException e){
            System.out.println("El error es:" + e);
        }
        return (null);
    }
}
    


