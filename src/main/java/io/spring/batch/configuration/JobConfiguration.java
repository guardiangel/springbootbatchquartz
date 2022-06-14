package io.spring.batch.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import io.spring.batch.domain.CustomerInput;
import io.spring.batch.domain.CustomerOutput;
import io.spring.batch.domain.CustomerRowMapper;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Minella
 */
@Configuration
public class JobConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  public DataSource dataSource;

  @Bean
  public JdbcPagingItemReader<CustomerInput> pagingItemReader() {
    JdbcPagingItemReader<CustomerInput> reader = new JdbcPagingItemReader<>();

    reader.setDataSource(this.dataSource);
    reader.setFetchSize(10);
    reader.setRowMapper(new CustomerRowMapper());

    MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
    queryProvider.setSelectClause("ID, FIRSTNAME, LASTNAME");
    queryProvider.setFromClause("from CUSTOMER_INPUT");

    Map<String, Order> sortKeys = new HashMap<>(1);

    sortKeys.put("ID", Order.ASCENDING);

    queryProvider.setSortKeys(sortKeys);

    reader.setQueryProvider(queryProvider);

    return reader;
  }

  @Bean
  public JdbcBatchItemWriter<CustomerOutput> customerItemWriter() {
    JdbcBatchItemWriter<CustomerOutput> itemWriter = new JdbcBatchItemWriter<>();

    itemWriter.setDataSource(this.dataSource);
    itemWriter.setSql("INSERT INTO CUSTOMER_OUTPUT(FIRSTNAME,LASTNAME) VALUES (:firstName, :lastName)");
    itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
    itemWriter.afterPropertiesSet();
    return itemWriter;
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
      .<CustomerInput, CustomerOutput>chunk(10)
      .reader(pagingItemReader())
      .writer(customerItemWriter())
      .build();
  }

  @Bean
  public Job job() {
    return jobBuilderFactory.get("job")
      .start(step1())
      .build();
  }
}