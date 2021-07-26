package ro.esolutions.testing.UT.services

import ro.esolutions.testing.entities.Client
import ro.esolutions.testing.models.ClientModel
import ro.esolutions.testing.repositories.ClientRepository
import ro.esolutions.testing.resources.ClientResource
import ro.esolutions.testing.services.ClientService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import javax.swing.text.html.Option

import static ro.esolutions.testing.testData.ClientGenerator.aClient
import static ro.esolutions.testing.testData.ClientGenerator.aClientModel

class ClientServiceSpec extends Specification {

    def clientRepository = Mock(ClientRepository)

    @Subject
    def clientService = new ClientService(clientRepository)

    def 'find all clients'() {
        given:
        def aClientList = [aClient()]

        when: 'we call the service to find all existing clients'
        def result = clientService.findAllClients()

        then: ' we return all the clients found'
        1 * clientRepository.findAll() >> aClientList
        0 * _

        and:
        result == aClientList
    }

    def 'save new client'() {
        given:
        def aClientModel = aClientModel();
        def newClientToBeSaved = aClient();

        when:
        def result = clientService.saveOrUpdateClient2(aClientModel);

        then:
        1 * clientRepository.findById(1) >> Optional.empty();
        1 * clientRepository.save(newClientToBeSaved) >> aClient();
        0 * _;

        and:
        result == null;

    }

    def 'update the client'() {
        given:
        def model = aClientModel(name: 'Damian', type: Client.Type.DISLOYAL)


        when:
        def result = clientService.saveOrUpdateClient2(model)

        then:
        1 * clientRepository.findById(1) >> Optional.of(aClient())
        1 * clientRepository.save(aClient(name: 'Damian', type: Client.Type.DISLOYAL))
        0 * _


        and:
        result == null;
    }

    /*test in care verificam ce se intampla daca
     findall intoarce o lista goala/
      5 clienti si doar 2 sunt noi/
      2 clienti doar 1 new/
      2 clienti amandoi new
      1 client si nu se potriveste
    nu intoarce un client, intoarce client model (lista client model)
    */

    @Unroll
    def 'test get new clients'() {

        when:
        def result = clientService.getNewClients()


        then:
        1 * clientRepository.findAll() >> foundClients
        0 * _

        and:
        result == expectedResult

        where:
        foundClients | expectedResult
        []           | []
        [aClient(type: Client.Type.VETERAN)] | []
        [aClient(type: Client.Type.NEW),aClient(type: Client.Type.NEW), aClient(type: Client.Type.VETERAN),aClient(type: Client.Type.VETERAN), aClient(type: Client.Type.VETERAN)] | [aClientModel(type: Client.Type.NEW),aClientModel(type: Client.Type.NEW)]
        [aClient(type: Client.Type.VETERAN),aClient(type: Client.Type.NEW)] | [aClientModel(type: Client.Type.NEW)]
        [aClient(type: Client.Type.NEW),aClient(type: Client.Type.NEW)] | [aClientModel(type: Client.Type.NEW),aClientModel(type: Client.Type.NEW)]

    }


}
