package fi.haagahelia.course.web;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository <Book, Long>
{
	List<Book> findByIsbn(String isbn);

}