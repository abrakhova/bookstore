package fi.haagahelia.course.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.web.CategoryRepository;
import fi.haagahelia.course.web.Book;
import fi.haagahelia.course.web.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository; 
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	
		public String greeting(Model model)
		{
			return "hello";
		}
	
	// Show all books
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	@RequestMapping(value="/booklist", method=RequestMethod.GET)
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
	
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }   
    
 // RESTful service to get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findOne(bookId);
    }    
	
	@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }     
	
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Book book){
	        repository.save(book);
	        return "redirect:booklist";
	    }    
	 
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    	repository.delete(bookId);
	        return "redirect:../booklist";
	    }     
}
