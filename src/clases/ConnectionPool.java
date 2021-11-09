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
    public final String URL = "jdbc:mysql://168.138.132.101:3306/AR_A213_SQL_S67";
    public final String USER = "AR_A213_SQL_S67";
    public final String PASS = "Hola1559200286";

    private void inicializarDataSource() {

        BasicDataSource basicDataSource = new BasicDataSource();
        
        basicDataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        basicDataSource.setInitialSize(50);
        
        
        dataSource = basicDataSource;
    }

    public ConnectionPool(){
        inicializarDataSource();
    }
}
