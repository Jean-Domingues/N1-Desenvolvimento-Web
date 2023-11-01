package br.anhembi.locadora.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.anhembi.locadora.models.Category;
import br.anhembi.locadora.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepo categoryRepo;

	public Category save(Category category) {
		return categoryRepo.save(category);
	}

	public List<Category> findAll() {
		return categoryRepo.findAll();
	}
}
