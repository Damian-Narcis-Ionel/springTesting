package ro.esolutions.testing.UT.resources

import org.springframework.http.ResponseEntity
import ro.esolutions.testing.resources.ClientResource
import ro.esolutions.testing.services.ClientService
import spock.lang.Specification
import spock.lang.Subject

import static ro.esolutions.testing.testData.ClientGenerator.aClient
import static ro.esolutions.testing.testData.ClientGenerator.aClientModel

class  ClientResourceSpec extends Specification{

    def clientService = Mock(ClientService)

    @Subject
    def clientResource = new ClientResource(clientService)

    def 'find all clients'(){
        given:
        def aClientList = [aClient()]

        when:
        def result = clientResource.findAllClients()

        then:
        1 * clientService.findAllClients() >> aClientList
        0 * _

        and:
        result == ResponseEntity.ok(aClientList)
    }

    def 'save client test' (){
        given:
        def clientModel = aClientModel();

        when:
        def  result = clientResource.saveOrUpdateClient(clientModel);

        then:
        1 * clientService.saveOrUpdateClient(clientModel);
        0 * _;

        and:
        result == null;


    }

}
