package com.dxc.libraryapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.libraryapi.entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Integer> {
	
	
	Library findByTitle(String title);
	
	List<Library> findAllByPublishedDate(LocalDate publishedDate);
	
	@Query("SELECT i from Library i WHERE i.price between :lowerBound and :upperBound")
	List<Library> getAllInPriceRange(double lowerBound,double upperBound);
}
