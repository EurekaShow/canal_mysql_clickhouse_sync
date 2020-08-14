package com.star.sync.client;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
@Configuration
public class ClickhouseClient  {

    @Value("${spring.datasource.click.driverClassName}")
    private String driverClassName ;
    @Value("${spring.datasource.click.url}")
    private String url ;

    @Value("${spring.datasource.click.userName}")
    private String userName ;
    @Value("${spring.datasource.click.password}")
    private String password ;
    @Value("${spring.datasource.click.initialSize}")
    private Integer initialSize ;
//    @Value("${spring.datasource.click.timeOut}")
//    private Integer timeOut ;
    @Value("${spring.datasource.click.maxActive}")
    private Integer maxActive ;
    @Value("${spring.datasource.click.minIdle}")
    private Integer minIdle ;
    @Value("${spring.datasource.click.maxWait}")
    private Integer maxWait ;

    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setUsername(userName);
        datasource.setPassword(password);
//        datasource.setQueryTimeout();
        return datasource;
    }


//        ClickHouseConnection conn = null;
//        ClickHouseProperties properties = new ClickHouseProperties();
//        properties.setUser(clickhouseUsername);
//        properties.setPassword(clickhousePassword);
//        properties.setDatabase(clickhouseDB);
//        properties.setSocketTimeout(clickhouseSocketTimeout);
//        String[] url=clickhouseAddress.split(",");
//        for (int i = 0; i < url.length; i++) {
//            ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(url[i], properties);
//            try {
//                conn = clickHouseDataSource.getConnection();
//                return conn;
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

//    private static final Logger LOG = LoggerFactory.getLogger(ClickhouseClient.class);
//    private static String clickhouseAddress;
//
//    private static String clickhouseUsername;
//
//    private static String clickhousePassword;
//
//    private static String clickhouseDB;
//
//    private static Integer clickhouseSocketTimeout;
//
//    @Value("${spring.clickhouse.address}")
//    private void setClickhouseAddress(String address) {
//        ClickhouseClient.clickhouseAddress = address;
//    }
//    @Value("${spring.clickhouse.username}")
//    private void setClickhouseUsername(String username) {
//        ClickhouseClient.clickhouseUsername = username;
//    }
//    @Value("${spring.clickhouse.password}")
//    private void setClickhousePassword(String password) {
//        ClickhouseClient.clickhousePassword = password;
//    }
//    @Value("${spring.clickhouse.db}")
//    private void setClickhouseDB(String db) {
//        ClickhouseClient.clickhouseDB = db;
//    }
//    @Value("${spring.clickhouse.socketTimeout}")
//    private void setClickhouseSocketTimeout(Integer socketTimeout) {
//        ClickhouseClient.clickhouseSocketTimeout = socketTimeout;
//    }
//    private ClickHouseConnection getConn() {
//        ClickHouseConnection conn = null;
//        ClickHouseProperties properties = new ClickHouseProperties();
//        properties.setUser(clickhouseUsername);
//        properties.setPassword(clickhousePassword);
//        properties.setDatabase(clickhouseDB);
//        properties.setSocketTimeout(clickhouseSocketTimeout);
//        String[] url=clickhouseAddress.split(",");
//        for (int i = 0; i < url.length; i++) {
//            ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(url[i], properties);
//            try {
//                conn = clickHouseDataSource.getConnection();
//                return conn;
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//    public List<Map> exeSelect(String sql) {
//        Connection connection = getConn();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet results = statement.executeQuery(sql);
//            ResultSetMetaData rsmd = results.getMetaData();
//            List<Map> list = new ArrayList();
//            while (results.next()) {
//                Map row = new HashMap();
//                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                    row.put(rsmd.getColumnName(i), results.getString(rsmd.getColumnName(i)));
//                }
//                list.add(row);
//            }
//            return list;
//        } catch (SQLException e) {
//            LOG.error("ExeSql:{}", sql);
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void execute(String sql,List<Object> params) {
//        ClickHouseConnection connection = getConn();
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            AtomicInteger i= new AtomicInteger(1);
//            params.forEach(param ->{
//                try {
//                    pstmt.setObject(i.get(),param);
//                } catch (SQLException e) {
//                    LOG.error("params ERROR:{}", param);
//                }
//                i.getAndIncrement();
//            });
//
//        } catch (SQLException e) {
//            LOG.error("ExeSql:{}", sql);
//            e.printStackTrace();
//        }
//    }
}
