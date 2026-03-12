package com.eliasnogueira.paymentservice.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersistenceBoundariesRulesTest {

    private JavaClasses javaClasses =
            new ClassFileImporter().importPackages("com.eliasnogueira.paymentservice");


    @Test
    @DisplayName("Model should only have entity classes")
    void entitiesTest() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .areAnnotatedWith(Entity.class)
                .should()
                .resideInAnyPackage("..model..");

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Dto and Controller classes should not have Entity classes")
    void dtoTest() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that()
                .resideInAnyPackage("..dto..", "..controller..")
                .should()
                .notBeAnnotatedWith(Entity.class);
    }
}
