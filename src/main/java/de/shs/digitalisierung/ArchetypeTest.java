package de.shs.digitalisierung;

import io.jexxa.core.JexxaMain;
import io.jexxa.drivingadapter.rest.RESTfulRPCAdapter;
import de.shs.digitalisierung.applicationservice.BookStoreService;
import de.shs.digitalisierung.domainservice.DomainEventService;
import de.shs.digitalisierung.domainservice.ReferenceLibrary;

public final class ArchetypeTest
{
    public static void main(String[] args)
    {
        var jexxaMain = new JexxaMain(ArchetypeTest.class);

        jexxaMain
                .bootstrap(ReferenceLibrary.class).and()       // Bootstrap latest book via ReferenceLibrary
                .bootstrap(DomainEventService.class).and()     // DomainEventService to forward DomainEvents to a message bus

                .bind(RESTfulRPCAdapter.class).to(BookStoreService.class)        // Provide REST access to BookStoreService
                .bind(RESTfulRPCAdapter.class).to(jexxaMain.getBoundedContext()) // Provide REST access to BoundedContext

                .run(); // Finally run the application
    }
}
