package edu.utdallas.cs6303.finalproject.main;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.ICredentialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatEmbeddedWebappClassLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.utdallas.cs6303.finalproject.services.dualauth.CredentialRepository;
import edu.utdallas.cs6303.finalproject.services.storage.StorageProperties;
import edu.utdallas.cs6303.finalproject.services.storage.StorageService;

@SpringBootApplication
@ComponentScan(basePackages = { "edu.utdallas.cs6303.finalproject.model.validation", "edu.utdallas.cs6303.finalproject.main", "edu.utdallas.cs6303.finalproject.model", "edu.utdallas.cs6303.finalproject.controllers", "edu.utdallas.cs6303.finalproject.services",
                                "edu.utdallas.cs6303.finalproject.advices", "edu.utdallas.cs6303.finalproject.listeners", "edu.utdallas.cs6303.finalproject.interceptors"  })
@EnableConfigurationProperties(StorageProperties.class)
@EnableJpaRepositories(basePackages = "edu.utdallas.cs6303.finalproject.model.database.repositories")
@EntityScan("edu.utdallas.cs6303.finalproject.model.database")
@EnableEncryptableProperties
@EnableAutoConfiguration
public class MainApplication {

    @Autowired 
    private ICredentialRepository credentialRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return args -> storageService.init();
    }

    @Bean
    public GoogleAuthenticator googleAuthenticator() {
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        googleAuthenticator.setCredentialRepository(credentialRepository);
        return googleAuthenticator;
    }


}