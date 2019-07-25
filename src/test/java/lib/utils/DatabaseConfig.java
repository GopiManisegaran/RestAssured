package lib.utils;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("lib.utils.*")
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class DatabaseConfig {
	 @Bean
	    public JdbcTemplate jdbcTemplate(DataSource dataSource)
	    {
	        return new JdbcTemplate(dataSource);
	    }
	 
	    @Bean
	    public PlatformTransactionManager transactionManager(DataSource dataSource)
	    {
	        return new DataSourceTransactionManager(dataSource);
	    }
	 
	    @Bean
	    public DataSource dataSource()
	    {
	    	DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	    	dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	dataSourceBuilder.url("jdbc:sqlserver://localhost:1433;databaseName=TestDB;integratedSecurity=true");
	    	dataSourceBuilder.username("DIR\\kumar.vp");
	    	dataSourceBuilder.password("none");
	    	return dataSourceBuilder.build();
	    }

}
