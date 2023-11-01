package br.anhembi.locadora.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.anhembi.locadora.dtos.PatchCustomerDTO;
import br.anhembi.locadora.dtos.PostCustomerDTO;
import br.anhembi.locadora.errors.custom.NotFoundError;
import br.anhembi.locadora.mappers.CustomerMapper;
import br.anhembi.locadora.models.Customer;
import br.anhembi.locadora.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private final CustomerRepo customerRepo;
	private final CustomerMapper customerMapper;

	public Customer save(PostCustomerDTO customerDTO) {
		var movie = customerMapper.post(customerDTO);
		return customerRepo.save(movie);
	}

	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	public Customer update(long id, PatchCustomerDTO customerDTO) {
		var customer = customerRepo.findById(id).orElseThrow(NotFoundError::new);

		customerMapper.patch(customerDTO, customer);

		customerRepo.save(customer);

		return customer;
	}

	public Customer delete(long id) {
		var customer = customerRepo.findById(id).orElseThrow(NotFoundError::new);

		customerRepo.delete(customer);

		return customer;
	}
}
