package com.dxc.libraryapi.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.libraryapi.entity.Library;
import com.dxc.libraryapi.exception.LibraryException;
import com.dxc.libraryapi.service.LibraryService;

@RestController
@CrossOrigin
@RequestMapping("/books")
public class LibraryApi {

	@Autowired
	private LibraryService libraryService;

	@GetMapping
	public ResponseEntity<List<Library>> getAllBooks() {
		return new ResponseEntity<List<Library>>(libraryService.getAllItems(), HttpStatus.OK);
	}

	@GetMapping("/{bookId:[0-9]{1,5}}")
	public ResponseEntity<Library> getBookById(@PathVariable("bookId") int bcode) {

		ResponseEntity<Library> response = null;
		Library library = libraryService.getById(bcode);

		if (library == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(library, HttpStatus.OK);

		}

		return response;
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<Library> getItemByTitle(@PathVariable("title") String title) {
		ResponseEntity<Library> response = null;

		Library library = libraryService.findByTitle(title);

		if (library == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(library, HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/pricerange/{lbound}/and/{ubound}")
	public ResponseEntity<List<Library>> getLibraryByPriceRange(@PathVariable("lbound") double lBound,
												@PathVariable("ubound") double uBound) {
		ResponseEntity<List<Library>> response = null;

		List<Library> library = libraryService.findByPriceRange(lBound, uBound);
		if (library == null || library.size() == 0) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(library, HttpStatus.OK);
		}
		return response;

	}

	@GetMapping("/date/{pDate}")
	public ResponseEntity<List<Library>> getLibraryBypublishedDate(
			@PathVariable("pDate") @DateTimeFormat(iso = ISO.DATE) LocalDate publishedDate) {
		ResponseEntity<List<Library>> response = null;

		List<Library> library = libraryService.findByPublishedDate(publishedDate);
		if (library == null || library.size() == 0) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(library, HttpStatus.OK);
		}
		return response;

	}

	@PostMapping
	//@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Library> createLibrary(@Valid @RequestBody Library library, BindingResult result)
			throws LibraryException {
		ResponseEntity<Library> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}

			throw new LibraryException(errMsg.toString());

		} else {
			libraryService.add(library);
			response = new ResponseEntity<>(library, HttpStatus.OK);
		}
		return response;
	}

	@PutMapping
	public ResponseEntity<Library> updateLibrary(@Valid @RequestBody Library library, BindingResult result)
			throws LibraryException {
		ResponseEntity<Library> response = null;

		if (result.hasErrors()) {
			StringBuilder errMsg = new StringBuilder();
			for (FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage() + ",");
			}
			throw new LibraryException(errMsg.toString());
		} else {
			libraryService.update(library);
			response = new ResponseEntity<>(library, HttpStatus.OK);
		}
		return response;
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteLibraryById(@PathVariable("bookId") int bcode) throws LibraryException {
		libraryService.deleteById(bcode);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
