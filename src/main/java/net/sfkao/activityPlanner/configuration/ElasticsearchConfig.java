package net.sfkao.activityPlanner.configuration;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {


    @Override
    public @NonNull ClientConfiguration clientConfiguration() {

        return ClientConfiguration.builder()
                .connectedTo("es01:9200")
                .withBasicAuth("elastic", "rootroot")
                .build();

    }
}
