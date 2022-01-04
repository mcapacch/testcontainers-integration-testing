package com.bmuschko.testcontainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class NginxServiceIntegrationTest {
    private NginxService nginxService;

    @Container
    private final GenericContainer nginxContainer = new GenericContainer(DockerImageName.parse("nginx:1.21.3")).withExposedPorts(80);

    @BeforeEach
    public void setUp() {
        String host = nginxContainer.getHost();
        Integer port = nginxContainer.getFirstMappedPort();
        String endpointUrl = "http://" + host + ":" + port;
        nginxService = new NginxServiceImpl(endpointUrl);
    }

    @Test
    public void testPing() {
        nginxService.ping();
    }
}
