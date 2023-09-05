package de.shs.digitalisierung.domainservice;


import io.jexxa.addend.applicationcore.InfrastructureService;
import de.shs.digitalisierung.domain.book.BookSoldOut;

@InfrastructureService
public interface DomainEventSender
{
    void publish(BookSoldOut domainEvent);
}
