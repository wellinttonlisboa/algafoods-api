package br.com.algaworks.algafoods.integration;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.repository.KitchenRepository;
import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;
import br.com.algaworks.algafoods.util.KitchenCreator;
import br.com.algaworks.algafoods.util.KitchenPostRequestBodyCreator;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class KitchenControllerIntegrationTest {
    @Autowired
    @Qualifier(value = "testRestTemplateRoleUser")
    private TestRestTemplate testRestTemplateRoleUser;
    @Autowired
    @Qualifier(value = "testRestTemplateRoleAdmin")
    private TestRestTemplate testRestTemplateRoleAdmin;
    @Autowired
    private KitchenRepository kitchenRepository;
//    @Autowired
//    private DevDojoUserRepository devDojoUserRepository;
    
//    private static final DevDojoUser USER = DevDojoUser.builder()
//            .name("DevDojo Academy")
//            .password("{bcrypt}$2a$10$hSTIR1LEGbkA6US1B0IJVeoTsHrFKzPwXSeE40SvIFckopmMHoUTm")
//            .username("devdojo")
//            .authorities("ROLE_USER")
//            .build();

//    private static final DevDojoUser ADMIN = DevDojoUser.builder()
//            .name("William Suane")
//            .password("{bcrypt}$2a$10$hSTIR1LEGbkA6US1B0IJVeoTsHrFKzPwXSeE40SvIFckopmMHoUTm")
//            .username("william")
//            .authorities("ROLE_USER,ROLE_ADMIN")
//            .build();

    @TestConfiguration
    @Lazy
    static class Config {
        @Bean(name = "testRestTemplateRoleUser")
        public TestRestTemplate testRestTemplateRoleUserCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:"+port)
                    .basicAuthentication("user", "password");
            return new TestRestTemplate(restTemplateBuilder);
        }
        @Bean(name = "testRestTemplateRoleAdmin")
        public TestRestTemplate testRestTemplateRoleAdminCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:"+port)
                    .basicAuthentication("wlisboa", "password");
            return new TestRestTemplate(restTemplateBuilder);
        }
    }

    @Test
    @DisplayName("list returns list of kitchen inside page object when successful")
    void list_ReturnsListOfKitchensInsidePageObject_WhenSuccessful() {
//        Kitchen savedKitchen = kitchenRepository.save(KitchenCreator.createKitchenToBeSaved());
//        devDojoUserRepository.save(USER);
//
//        String expectedName = savedKitchen.getName();
//
//        PageableResponse<Kitchen> kitchenPage = testRestTemplateRoleUser.exchange("/kitchens", HttpMethod.GET, null,
//                new ParameterizedTypeReference<PageableResponse<Kitchen>>() {
//                }).getBody();
//
//        Assertions.assertThat(kitchenPage).isNotNull();
//
//        Assertions.assertThat(kitchenPage.toList())
//                .isNotEmpty()
//                .hasSize(1);
//
//        Assertions.assertThat(kitchenPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns list of kitchen when successful")
    void listAll_ReturnsListOfKitchens_WhenSuccessful() {
//        Kitchen savedKitchen = kitchenRepository.save(KitchenCreator.createKitchenToBeSaved());
//        devDojoUserRepository.save(USER);
//
//        String expectedName = savedKitchen.getName();
//
//        List<Kitchen> kitchens = testRestTemplateRoleUser.exchange("/kitchens/all", HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Kitchen>>() {
//                }).getBody();
//
//        Assertions.assertThat(kitchens)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1);
//
//        Assertions.assertThat(kitchens.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns Kitchen when successful")
    void findById_ReturnsKitchen_WhenSuccessful() {
    	Kitchen savedKitchen = kitchenRepository.save(KitchenCreator.createKitchenToBeSaved());
        //devDojoUserRepository.save(USER);

        Long expectedId = savedKitchen.getId();

        Kitchen kitchen = testRestTemplateRoleUser.getForObject("/kitchens/{id}", Kitchen.class, expectedId);

        Assertions.assertThat(kitchen).isNotNull();

        Assertions.assertThat(kitchen.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of kitchen when successful")
    void findByName_ReturnsListOfKitchen_WhenSuccessful() {
        Kitchen savedKitchen = kitchenRepository.save(KitchenCreator.createKitchenToBeSaved());
//        devDojoUserRepository.save(USER);

        String expectedName = savedKitchen.getName();

        String url = String.format("/kitchens/find?name=%s", expectedName);

        List<Kitchen> kitchens = testRestTemplateRoleUser.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Kitchen>>() {
                }).getBody();

        Assertions.assertThat(kitchens)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(kitchens.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of kitchen when kitchen is not found")
    void findByName_ReturnsEmptyListOfKitchen_WhenKitchenIsNotFound() {
//        devDojoUserRepository.save(USER);

        List<Kitchen> kitchens = testRestTemplateRoleUser.exchange("/kitchens/find?name=dbz", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Kitchen>>() {
                }).getBody();

        Assertions.assertThat(kitchens)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("save returns kitchen when successful")
    void save_ReturnsKitchen_WhenSuccessful() {
//        devDojoUserRepository.save(USER);

        KitchenPostRequestBody kitchenPostRequestBody = KitchenPostRequestBodyCreator.createKitchenPostRequestBody();

        ResponseEntity<Kitchen> kitchenResponseEntity = testRestTemplateRoleUser.postForEntity("/kitchens", kitchenPostRequestBody, Kitchen.class);

        Assertions.assertThat(kitchenResponseEntity).isNotNull();
        Assertions.assertThat(kitchenResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(kitchenResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(kitchenResponseEntity.getBody().getId()).isNotNull();

    }

    @Test
    @DisplayName("replace updates kitchen when successful")
    void replace_UpdatesKitchen_WhenSuccessful() {
        Kitchen savedKitchen = kitchenRepository.save(KitchenCreator.createKitchenToBeSaved());
//        devDojoUserRepository.save(USER);

        savedKitchen.setName("new name");

        ResponseEntity<Void> kitchenResponseEntity = testRestTemplateRoleUser.exchange("/kitchens",
                HttpMethod.PUT, new HttpEntity<>(savedKitchen), Void.class);

        Assertions.assertThat(kitchenResponseEntity).isNotNull();

        Assertions.assertThat(kitchenResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes kitchen when successful")
    void delete_RemovesKitchen_WhenSuccessful() {
        Kitchen savedKitchen = kitchenRepository.save(KitchenCreator.createKitchenToBeSaved());
//        devDojoUserRepository.save(ADMIN);

        ResponseEntity<Void> kitchenResponseEntity = testRestTemplateRoleAdmin.exchange("/kitchens/admin/{id}",
                HttpMethod.DELETE, null, Void.class, savedKitchen.getId());

        Assertions.assertThat(kitchenResponseEntity).isNotNull();

        Assertions.assertThat(kitchenResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    @DisplayName("delete returns 403 when user is not admin")
    void delete_Returns403_WhenUserIsNotAdmin() {
        Kitchen savedKitchen = kitchenRepository.save(KitchenCreator.createKitchenToBeSaved());
//        devDojoUserRepository.save(USER);

        ResponseEntity<Void> kitchenResponseEntity = testRestTemplateRoleUser.exchange("/kitchens/admin/{id}",
                HttpMethod.DELETE, null, Void.class, savedKitchen.getId());

        Assertions.assertThat(kitchenResponseEntity).isNotNull();

        Assertions.assertThat(kitchenResponseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

}
