package fr.checkconsulting.formation122023.batch;

import fr.checkconsulting.formation122023.dto.PersonDto;
import fr.checkconsulting.formation122023.entity.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    @Autowired
    @Qualifier("p1")
    ItemProcessor processor1;

    @Autowired
    @Qualifier("p2")
    ItemProcessor processor2;

    @Autowired
    @Qualifier("p3")
    ItemProcessor processor3;

    @Autowired
    ItemWriter writer;


    @Bean
    public FlatFileItemReader<PersonDto> reader() {
        return new FlatFileItemReaderBuilder<PersonDto>()
                .name("personItemReader")
                .resource(new ClassPathResource("input.csv"))
                .delimited()
                .names("email", "firstName", "lastName", "old")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(PersonDto.class);
                }})
                .build();
    }


    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<PersonDto, Person>chunk(10000, transactionManager)
                .reader(reader())
                .processor(processor1)
                .writer(writer)
                .build();

    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository)
                .<PersonDto, Person>chunk(10000, transactionManager)
                .reader(reader())
                .processor(processor2)
                .writer(writer)
                .build();

    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step3", jobRepository)
                .<PersonDto, Person>chunk(10000, transactionManager)
                .reader(reader())
                .processor(processor3)
                .writer(writer)
                .build();

    }

    @Bean
    public Job migrationJob(JobRepository jobRepository, Step step1, Step step2, Step step3) {


        Flow myFlow = new FlowBuilder<SimpleFlow>("myFlow")
                .start(step1).from(step1).on("COMPLETED").to(step2)
                .from(step1).on("FAILED").to(step3)
                .end();

        Flow mySecondFlow = new FlowBuilder<SimpleFlow>("mon flow en parallèle")
                .start(step2).end();

        Flow myLastFlow = new FlowBuilder<SimpleFlow>("my last Flow")
                .start(step1).next(step2).end();

        return new JobBuilder("PersonMigrationJob", jobRepository)
                .start(myFlow)
                .split(new SimpleAsyncTaskExecutor())
                .add(mySecondFlow)
                .next(myLastFlow)
                .end()
                .build();
    }


}
