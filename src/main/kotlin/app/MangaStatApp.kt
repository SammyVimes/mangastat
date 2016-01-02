package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources

/**
 * Created by Semyon on 02.01.2016.
 */
@SpringBootApplication
@ComponentScan(basePackages = arrayOf("app", "controller", "model", "repository", "service"))
@PropertySources(PropertySource("classpath:application-dev.properties"))
open class MangaStatApp {

    companion object {

        @JvmStatic
        public fun main(args: Array<String>) {
            SpringApplication.run(MangaStatApp::class.java, *args);
        }

    }

}