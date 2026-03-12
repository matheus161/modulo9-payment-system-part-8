package com.eliasnogueira.paymentservice.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HygieneRulesTest {

    private JavaClasses javaClasses =
            new ClassFileImporter().importPackages("com.eliasnogueira.paymentservice");

    @Test
    @DisplayName("Classes should not throw generic exceptions")
    void noGenericExceptionsShouldBeThrown() {
        ArchRule rule = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Classes should not use deprecated api")
    void shouldNotUseDeprecatedApi() {
        ArchRule rule = GeneralCodingRules.DEPRECATED_API_SHOULD_NOT_BE_USED;
        rule.check(javaClasses);
    }
}
