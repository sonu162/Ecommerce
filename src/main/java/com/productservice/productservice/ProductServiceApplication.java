package com.productservice.productservice;

import com.productservice.productservice.models.*;
import com.productservice.productservice.repositories.CategoryRepository;
import com.productservice.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;
    private final OrderRepository orderRepository;

    public ProductServiceApplication(CategoryRepository categoryRepository, ProductRepository productRepository,
                                     PriceRepository priceRepository, OrderRepository orderRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Price price = new Price();
        price.setCurrency("INR");
        price.setValue(12.0);
        Price savedPrice = priceRepository.save(price);
        System.out.println("savedPrice :" + savedPrice);

        Category category = new Category();
        category.setName("Apple Phone");
        Category savedCategory = categoryRepository.save(category);
        System.out.println("savedCategory :" + savedCategory);

        Product product = new Product();
        product.setTitle("I nphone 13");
        product.setDescription("cheap Iphone");
        product.setCategory(savedCategory);
        product.setPrice(savedPrice);
        Product savedProduct = productRepository.save(product);
        System.out.println("saved product " + savedProduct);

        Order order = new Order();
        orderRepository.save(order);
    }
}


// implements CommandLineRunner

/*
 * // private MentorRepository mentorRepository; // // private Tpc_MentorRepository mentorRepository1; // // private
 * StudentRepository studentRepository; // // private UserRepository userRepository; // // private St_UserRepository
 * st_userRepository; // // private St_StudentRepository st_studentRepository; // // private St_MentorRepository
 * st_mentorRepository;
 *
 * // ProductServiceApplication(MentorRepository mentorRepository, // Tpc_MentorRepository mentorRepository1, //
 * StudentRepository studentRepository, // UserRepository userRepository, // St_UserRepository stUserRepository, //
 * St_StudentRepository stStudentRepository, // St_MentorRepository stMentorRepository){ // this.mentorRepository =
 * mentorRepository; // this.mentorRepository1 = mentorRepository1; // this.studentRepository = studentRepository;
 * // this.userRepository = userRepository; // st_userRepository = stUserRepository; // st_studentRepository =
 * stStudentRepository; // st_mentorRepository = stMentorRepository; // }
 *
 */


// Mentor mentor = new Mentor();
// mentor.setName("Sonu");
// mentor.setEmailId("Sonu@gmail.com");
// mentor.setAvgRating(4.8);
// mentorRepository.save(mentor);
//
//
// Tpc_Mentor mentor1 = new Tpc_Mentor();
// mentor1.setName("Prince");
// mentor1.setId(1000L);
// mentor1.setEmailId("prince@gmail.com");
// mentor1.setAvgRating(6.9);
// mentorRepository1.save(mentor1);
//
//
// Student student = new Student();
// student.setName("Ankit");
// student.setEmailId("ankit@gmail.com");
// student.setPsp(80);
// studentRepository.save(student);
//
//
// User user = new User();
// user.setName("Rajni");
// user.setEmailId("rajni@gmail.com");
// userRepository.save(user);
//
// // to get all the user you should get all user of all users
// List<User> user1 = userRepository.findAll();
//
// System.out.println(user1);

// com.productservice.productservice.inheritancerelations.singletable.User user = new
// com.productservice.productservice.inheritancerelations.singletable.User();
// user.setName("Amit");
// user.setEmailId("Amit@gmail.com");
// st_userRepository.save(user);
//
//
// St_Student student = new St_Student();
// student.setPsp(40);
// student.setName("Harsh");
// student.setEmailId("harsh@gmail.com");
// st_studentRepository.save(student);
//
// St_Mentor mentor = new St_Mentor();
// mentor.setName("Deepak");
// mentor.setEmailId("deepak@gmail.com");
// mentor.setAvgRating(5.0);
// st_mentorRepository.save(mentor);

// Category category = new Category();
// category.setName("Apple Phone");
// Category savedCategory = categoryRepository.save(category);

// Optionalal<Category> category = categoryRepository.findByName("Apple Phone");

// Product product = new Product();
// product.setTitle("I nphone 13");
// product.setDescription("cheap Iphone");
// product.setCategory(category.get());
// Product savedProduct = productRepository.save(product);

// find all the products with category = "Apple Phone"
// Optional<Category> category1 = categoryRepository.findByName("Apple Phone");
// if (category1.isEmpty()){
// throw new Exception("Category Not Present");
// }
// List<Product> productList = category1.get().getProducts();
// productList.forEach(System.out::println);