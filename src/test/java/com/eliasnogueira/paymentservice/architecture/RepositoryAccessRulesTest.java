package com.eliasnogueira.paymentservice.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RepositoryAccessRulesTest {

    private JavaClasses javaClasses =
            new ClassFileImporter().importPackages("com.eliasnogueira.paymentservice");

    @Test
    @DisplayName("Controllers classes should not have access to repository classes")
    void controllersTest() {
        ArchRule rule = ArchRuleDefinition.noClasses()
                .that()
                .resideInAnyPackage("..controller..")
                .should()
                .accessClassesThat()
                .resideInAnyPackage("..repository..");

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Repository should only gave interfaces")
    void repositoriesTest() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .resideInAnyPackage("..repository..")
                .should()
                .beInterfaces();

        rule.check(javaClasses);
    }
}
