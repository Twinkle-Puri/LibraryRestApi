package com.dxc.libraryapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.libraryapi.entity.Library;
import com.dxc.libraryapi.exception.LibraryException;


@Service
public interface LibraryService {
	

	Library add(Library library) throws LibraryException;
 	Library update(Library library) throws LibraryException;
	boolean deleteById(int bcode) throws LibraryException;
	Library getById(int bcode);
	List<Library> getAllItems();
	
	
	Library findByTitle(String title);
	List<Library> findByPublishedDate(LocalDate publishedDate);
	List<Library> findByPriceRange(double lowerBound, double upperBound);

}
