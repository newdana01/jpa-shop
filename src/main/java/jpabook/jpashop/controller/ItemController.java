package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(BookForm bookForm) {
        Book book = new Book();
        // createBook() 을 만들어서 setter를 다 없애는 게 더 좋ㅇ느 설계
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());

        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list (Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);
        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setName(item.getName());
        bookForm.setPrice(item.getPrice());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setIsbn(item.getIsbn());

        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form) {
        UpdateItemDto updateItemDto = new UpdateItemDto();
        updateItemDto.setName(form.getName());
        updateItemDto.setPrice(form.getPrice());
        updateItemDto.setStockQuantity(form.getStockQuantity());

        itemService.updateItem(itemId, updateItemDto);

        return "redirect:/items";
    }
}
