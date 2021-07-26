package ro.esolutions.testing.IT.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import ro.esolutions.testing.repositories.ClientRepository
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import static ro.esolutions.testing.testData.ClientGenerator.aClientForIt

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ClientRepositorySpecIT extends Specification{

    @Autowired
    ClientRepository clientRepository

    @SqlGroup(
            @Sql(value = '/sql/client.sql', executionPhase = BEFORE_TEST_METHOD)
    )
    def 'find all clients'() {
        given:
        def client = aClientForIt()

        when:
        def result = clientRepository.findAll()

        then:
        result == [client]
    }

}
