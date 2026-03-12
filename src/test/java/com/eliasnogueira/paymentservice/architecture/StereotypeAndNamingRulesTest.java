package com.eliasnogueira.paymentservice.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

public class StereotypeAndNamingRulesTest {

    private JavaClasses javaClasses =
            new ClassFileImporter().importPackages("com.eliasnogueira.paymentservice");

    @Test
    @DisplayName("Controller classes should be annotated with RestController and have simple name ending with Controller")
    void controllersTest() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .areAnnotatedWith(RestController.class)
                .should()
                .resideInAnyPackage("..controller..")
                .andShould()
                .haveSimpleNameEndingWith("Controller");

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Service classes should be annotated with Service and have simple name ending with Service")
    void servicesTest() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .areAnnotatedWith(Service.class)
                .should()
                .resideInAnyPackage("..service..")
                .andShould()
                .haveSimpleNameEndingWith("Service");

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Repository should be annotated with Repository and have simple name ending with Repository")
    void repositoriesTest() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .areAnnotatedWith(Repository.class)
                .should()
                .resideInAnyPackage("..repository..")
                .andShould()
                .haveSimpleNameEndingWith("Repository");

        rule.check(javaClasses);
    }
}
