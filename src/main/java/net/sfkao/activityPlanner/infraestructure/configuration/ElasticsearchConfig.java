package net.sfkao.activityPlanner.infraestructure.configuration;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

@Log4j2
@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {


    @Value("${spring.elasticsearch.rest.username}")
    private String username;

    @Value("${spring.elasticsearch.rest.password}")
    private String password;

    @Value("${spring.elasticsearch.rest.uris}")
    private String uris;

    @Value("${spring.elasticsearch.rest.cert.location}")
    private String certLocation;

    @Override
    public @NonNull ClientConfiguration clientConfiguration() {
        SSLContext context = null;
        try {
            context = SSLContext.getInstance("TLS");
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            Certificate ca;
            try (InputStream certificateInputStream = new FileInputStream(certLocation)) {
                ca = cf.generateCertificate(certificateInputStream);
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager

            context.init(null, tmf.getTrustManagers(), null);
        } catch (Exception e) {
            log.error("No se ha podido obtener el certificado", e);
        }

        return ClientConfiguration.builder()
                .connectedTo(uris)
                .usingSsl(context)
                .withBasicAuth(username, password)
                .build();

    }
}
