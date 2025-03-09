package pl.edu.wszib.GitHubApi;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.edu.wszib.GitHubApi.Controller.GitHubController;
import pl.edu.wszib.GitHubApi.Service.GitHubService;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTestGit {

    @Autowired
    private WebTestClient webTestClient;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(8081); // ireMock - port 8081
        wireMockServer.start();
        configureFor("localhost", 8081);
    }

    @AfterAll
    static void stop() {
        wireMockServer.stop();
    }

    @Test
    void testGetRepositories() {

        stubFor(get(urlEqualTo("/users/mo/repos"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"repositoryName\": \"\", \"ownerName\": {\"login\": \"\"} }]")
                        .withStatus(200)));


        stubFor(get(urlEqualTo("/repos/mo/repo1/branches"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"name\": \"\"}]")
                        .withStatus(200)));


        webTestClient.get().uri("/mo")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[*].repositoryName").exists() // czy istnieje repoName
                .jsonPath("$[*].ownerName").value(ownerNames -> {
                    List<String> ownersList = (List<String>) ownerNames; // rzutowanie na listę
                    org.assertj.core.api.Assertions.assertThat(ownersList)
                            .allMatch(owner -> owner.equals("mo"), "ownerName != 'mo'");
                })
                .jsonPath("$[*].branchResponses").exists() ;// czy branchResponses istnieje


    }
}