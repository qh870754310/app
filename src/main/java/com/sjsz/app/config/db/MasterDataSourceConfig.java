package com.sjsz.app.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.sjsz.app.config.db.annotation.Master;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Author: qh
 * @Date: 2019/3/1 17:23
 * @Description: 主数据源配置
 */
@Configuration
@MapperScan(basePackages = "com.sjsz.app.dao", annotationClass = Master.class, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    @Autowired
    private Environment environment;


    @Primary
    @Bean(name = "masterDataSouce")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    /**
     * druid 监控配置:主要实现WEB监控的配置处理
     * @return
     */
    @Primary
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean= new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1,localhost");// 白名单
        servletRegistrationBean.addInitParameter("deny", "192.168.1.200"); //黑名单
        servletRegistrationBean.addInitParameter("loginUsername", "root"); // 用户名
        servletRegistrationBean.addInitParameter("loginPassword", "root"); // 密码
        servletRegistrationBean.addInitParameter("resetEnable", "false");  //是否可以重置数据源
        return servletRegistrationBean;
    }

    /**
     * 配置一个web监控的filter
     * @return
     */
    @Primary
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*")); // 所有请求进行监控处理
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * Mybatis的SQL会话工厂
     * @param dataSource 数据源
     * @return
     * @throws Exception 创建SqlSessionFactory发生异常
     */
    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSouce") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //mapper.xml所在目录
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(environment.getProperty("mybatis.mapperLocations")));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(environment.getProperty("mybatis.configLocation")));
        return sqlSessionFactoryBean.getObject();

    }

    /**
     *
     * @param sqlSessionFactory
     * @return
     */
    @Primary
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     *
     * @param dataSource
     * @return
     */
    @Primary
    @Bean(name = "masterDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("masterDataSouce") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
