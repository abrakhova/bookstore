package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.web.Category;
import fi.haagahelia.course.web.Book;
import fi.haagahelia.course.web.BookRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	
	@Autowired
    private BookRepository repository; 
	
	@Test
    public void findByIsbnShouldReturnBook() {
        List<Book> books = repository.findByIsbn("Isbn1");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Adventures of Tim");
    }
	
	
	@Test
    public void createNewBook() {
    	Book book = new Book("Prince and Princess", "Jack Mason", 1991, "Isbn1", 250, new Category("Fairy tale"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
}
