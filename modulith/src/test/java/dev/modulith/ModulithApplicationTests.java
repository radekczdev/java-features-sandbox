package dev.modulith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class ModulithApplicationTests {

  @Test
  void contextLoads() {
    var modules = ApplicationModules.of(ModulithApplication.class);
    modules.forEach(System.out::println);
  }

  @Test
  void verificationOfModules() {
    ApplicationModules.of(ModulithApplication.class).verify();
  }

  @Test
  void createModuleDocumentation() {
    ApplicationModules modules = ApplicationModules.of(ModulithApplication.class);
    new Documenter(modules)
        .writeModulesAsPlantUml()
        .writeIndividualModulesAsPlantUml();
  }

  @Test
  void moduleOrderInternalInternal_shouldNotAccessOrderNestedInternalModule() {
    JavaClasses jc = new ClassFileImporter().importPackages("dev.modulith.order");
    final ArchRule classesShouldConjunction = noClasses().that().resideInAPackage("..order.internal.internal..")
        .should().accessClassesThat().resideInAPackage("..order.nested.internal..");
    classesShouldConjunction.check(jc);
  }

}
