package ro.esolutions.testing.testData

import ro.esolutions.testing.entities.Client
import ro.esolutions.testing.models.ClientModel

class ClientGenerator {

    static Client aClient(Map overrides = [:]) {
        Map defaultValues = [
                id      : 1,
                name    : 'Alex',
                isActive: true,
                type    : Client.Type.LOYAL
        ]
        defaultValues << overrides
        new Client(defaultValues)
    }

    static Client aClientForIt(Map overrides = [:]) {
        Map defaultValues = [
                id      : 1,
                name    : 'clientName',
                isActive: true,
                type    : Client.Type.LOYAL
        ]
        defaultValues << overrides
        new Client(defaultValues)
    }

    static ClientModel aClientModel(Map overrides = [:]) {
        Map defaultValues = [
                id      : 1,
                name    : 'Alex',
                type    : Client.Type.LOYAL
        ]
        defaultValues << overrides
        new ClientModel(defaultValues)
    }
}
