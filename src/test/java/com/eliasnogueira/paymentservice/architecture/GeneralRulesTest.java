package com.eliasnogueira.paymentservice.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneralRulesTest {

    private JavaClasses javaClasses =
            new ClassFileImporter().importPackages("com.eliasnogueira.paymentservice");

    @Test
    @DisplayName("Controller should not access repository")
    void controllerTest() {
        ArchRule rule = ArchRuleDefinition.noClasses()
                .that()
                .resideInAnyPackage("..controller..")
                .should()
                .accessClassesThat()
                .resideInAnyPackage("..repository..");

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Repository should have only interfaces")
    void repositoryTest() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .resideInAnyPackage("..repository..")
                .should()
                .beInterfaces();

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Model should only having entity classes")
    void entityTest() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .areAnnotatedWith(Entity.class)
                .should()
                .resideInAnyPackage("..model..");

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Classes should not throw generic exceptions")
    void shouldNotUseGenericExceptions() {
        ArchRule rule = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Classes should not use deprecated api")
    void shouldNotUseDeprecatedClasses() {
        ArchRule rule = GeneralCodingRules.DEPRECATED_API_SHOULD_NOT_BE_USED;
        rule.check(javaClasses);
    }
}
