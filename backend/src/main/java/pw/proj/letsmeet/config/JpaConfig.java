package pw.proj.letsmeet.config;

import pw.proj.letsmeet.generic.ModelBaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = {"pw.proj.letsmeet.*"}, repositoryBaseClass = ModelBaseRepositoryImpl.class)
class JpaConfig {}
