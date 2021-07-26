package ro.esolutions.testing

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class TestingApplicationSpec extends Specification {
    def "check that application context actually starts"() {
        expect: 'application context is created'
    }
}
