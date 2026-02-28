package config;

import com.google.inject.AbstractModule;
import repository.custom.BookRepository;
import repository.custom.CustomerRepository;
import repository.custom.RentalRepository;
import repository.custom.impl.BookRepositoryImpl;
import repository.custom.impl.CustomerRepositoryImpl;
import repository.custom.impl.RentalRepositoryImpl;
import service.custom.BookService;
import service.custom.CustomerService;
import service.custom.RentalService;
import service.custom.impl.BookServiceImpl;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.RentalServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){

        bind(CustomerRepository.class).to(CustomerRepositoryImpl.class);
        bind(BookRepository.class).to(BookRepositoryImpl.class);
        bind(RentalRepository.class).to(RentalRepositoryImpl.class);

        bind(CustomerService.class).to(CustomerServiceImpl.class);
        bind(BookService.class).to(BookServiceImpl.class);
        bind(RentalService.class).to(RentalServiceImpl.class);
    }
}
