package yqx1110.LibraryManagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import yqx1110.LibraryManagement.entity.Book;
import yqx1110.LibraryManagement.entity.User;
import yqx1110.LibraryManagement.service.ReaderService;
import yqx1110.LibraryManagement.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class ReaderController {
    private ReaderService readerService;
    private static final String KEY_USER = "__user__";

    @Autowired
    public void setReaderService(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/books")
    public ModelAndView listBooks(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user == null) {
            return new ModelAndView("redirect:/signin");
        }
        HashMap<String, Object> model = new HashMap<>();
        List<Book> books = readerService.getBooks(1, 10);
        model.put("user", user);
        model.put("books", books);
        return new ModelAndView("reader_books_query.html", model);
    }

    @GetMapping("/books/search")
    public ModelAndView searchBooks(@RequestParam("s") String searchWord, HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user == null) {
            return new ModelAndView("redirect:/signin");
        }
        HashMap<String, Object> model = new HashMap<>();
        model.put("user", user);
        if (searchWord.equals("")) {
            return new ModelAndView("redirect:/books", model);
        } else {
            List<Book> books = readerService.searchBooks(searchWord, 1, 10);
            model.put("searchWord", searchWord);
            model.put("books", books);
            return new ModelAndView("reader_books_query.html", model);
        }
    }

    @GetMapping("books/detail/{id}")
    public String bookDetail(@PathVariable int id, Model model) {
        try {
            Book book = readerService.getBookById(id);
            model.addAttribute("book", book);
        } catch (DataAccessException ignored) {
        }
        return "book_detail.html";
    }

    @GetMapping("/account")
    public String account(HttpSession session, Model model) {
        return "";
    }

}
