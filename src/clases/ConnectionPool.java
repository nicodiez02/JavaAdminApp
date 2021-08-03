/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;

public class ConnectionPool {
    
    public DataSource dataSource;
    public final String DB = "sql10429079";
    public final String URL = "jdbc:mysql://sql10.freesqldatabase.com:3306/" + DB;
    public final String USER = "sql10429079";
    public final String PASS = "gADpf7X9wA";

    private void inicializarDataSource() {

        BasicDataSource basicDataSource = new BasicDataSource();
        
        basicDataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        
        
        dataSource = basicDataSource;
    }

    public ConnectionPool(){
        inicializarDataSource();
    }
}
