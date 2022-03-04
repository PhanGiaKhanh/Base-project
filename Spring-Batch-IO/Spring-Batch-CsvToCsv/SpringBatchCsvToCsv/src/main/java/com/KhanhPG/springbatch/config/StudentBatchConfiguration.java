package com.KhanhPG.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.KhanhPG.springbatch.model.Student;
import com.KhanhPG.springbatch.processor.StudentProcessor;

/**
 * @author: KhanhPG
 * @since: 04/03/2022
 */
@Configuration
@EnableBatchProcessing
public class StudentBatchConfiguration {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public FlatFileItemReader<Student> readDataFromCsv() {
		// FlatFileItemReader hỗ trợ đọc file line by line.
		FlatFileItemReader<Student> reader = new FlatFileItemReader<>();
		// FileSystemResource
		reader.setResource(new FileSystemResource("D://My_Project/My_Java/io-spring-batch/Student.csv"));
		reader.setLineMapper(new DefaultLineMapper<Student>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(Student.fields());
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {
					{
						setTargetType(Student.class);
					}
				});
			}
		});
		return reader;
	}
	
	@Bean
	public StudentProcessor processor() {
		return new StudentProcessor();
	}
	
	@Bean
	public FlatFileItemWriter<Student> writer() {
		FlatFileItemWriter<Student> writer = new FlatFileItemWriter<>();
		writer.setResource(new FileSystemResource("D://My_Project/My_Java/io-spring-batch/Student-output.csv"));
		DelimitedLineAggregator<Student> aggregator = new DelimitedLineAggregator<>();
		BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(Student.fields());
		writer.setLineAggregator(aggregator);
		return writer;
	}
	
	@Bean
	public Step executeStudentStep() {
		return stepBuilderFactory.get("execiteStudentStep").<Student, Student>chunk(5)
		.reader(readDataFromCsv()).processor(processor()).writer(writer()).build();
	}

	@Bean
	public Job processStudentJob() {
		return jobBuilderFactory.get("processStudentJob").flow(executeStudentStep()).end().build();
	}
}
