package com.dxc.libraryapi.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.libraryapi.entity.Library;
import com.dxc.libraryapi.exception.LibraryException;
import com.dxc.libraryapi.repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService {
	
	@Autowired
	private LibraryRepository libRepo;
	
	@Transactional
	@Override
	public Library add(Library library) throws LibraryException {
		if(library != null) {
			if(libRepo.existsById(library.getBcode())) {
				throw new LibraryException("An book with the code " + library.getBcode() + " already exists!");
			}
			libRepo.save(library);
		}
		return library;
	}

	@Override
	public Library update(Library library) throws LibraryException {
		if(library != null) {
			if(!libRepo.existsById(library.getBcode())) {
				throw new LibraryException("No such book found to update");
			}
			libRepo.save(library);
		}
		return library;
	}

	@Override
	public boolean deleteById(int bcode) throws LibraryException {
		boolean deleted = false;
		if(!libRepo.existsById(bcode)) {
			throw new LibraryException("No such book found!");
		}
		libRepo.deleteById(bcode);
		deleted = true;
		return deleted;
	}

	@Override
	public Library getById(int bcode) {
	
		return libRepo.findById(bcode).orElse(null);
	}

	@Override
	public List<Library> getAllItems() {
		
		return libRepo.findAll();
	}

	@Override
	public Library findByTitle(String title) {
		
		return libRepo.findByTitle(title);
	}

	@Override
	public List<Library> findByPublishedDate(LocalDate publishedDate) {
	
		return libRepo.findAllByPublishedDate(publishedDate);
	}

	@Override
	public List<Library> findByPriceRange(double lowerBound, double upperBound) {

		return libRepo.getAllInPriceRange(lowerBound, upperBound);
	}

}
