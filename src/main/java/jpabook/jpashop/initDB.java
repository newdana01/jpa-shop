package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 총 주문 2개 데이터 생성
 */

@Component
@RequiredArgsConstructor
public class initDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("user1", "서울", "1", "1111");
            em.persist(member);

            Book book1 = createBook("jpa1 book", 10000, 100);
            Book book2 = createBook("jpa2 book", 20000, 100);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery1 = getDelivery(member);

            Order order = Order.createOrder(member, delivery1, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Delivery getDelivery(Member member) {
            Delivery delivery1 = new Delivery();
            delivery1.setAddress(member.getAddress());
            return delivery1;
        }

        private static Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private static Member createMember(String username, String city, String street, String zipcode) {
            Member member = new Member();
            member.setUsername(username);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        public void dbInit2() {
            Member member = createMember("user2", "부", "1", "1111");
            em.persist(member);

            Book book1 = createBook("spring1 book", 20000, 200);
            Book book2 = createBook("spring2 book", 40000, 200);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery1 = getDelivery(member);

            Order order = Order.createOrder(member, delivery1, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
