package net.sfkao.activityPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class ActivityPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityPlannerApplication.class, args);
	}

}
