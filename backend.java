import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FoodDeliveryBackend {

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryBackend.class, args);
    }
}

@RestController
@RequestMapping("/api/menu")
class MenuController {
    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuController() {
        // Initialize sample menu items
        menuItems.add(new MenuItem(1, "Dal Makhani", "Delicious lentil curry", 349));
        menuItems.add(new MenuItem(2, "Paneer Tikka", "Grilled cottage cheese", 399));
        // Add more menu items here
    }

    @GetMapping("/items")
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}

class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;

    public MenuItem(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters and setters
}

@RestController
@RequestMapping("/api/cart")
class CartController {
    private final List<CartItem> cartItems = new ArrayList<>();

    @GetMapping("/items")
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody CartItemRequest cartItemRequest) {
        // You can implement the logic to add items to the cart here
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeFromCart(@PathVariable int itemId) {
        // You can implement the logic to remove items from the cart here
    }
}

class CartItem {
    private int itemId;
    private String itemName;
    private int quantity;
    private double totalPrice;

    // Getters and setters
}

class CartItemRequest {
    private int itemId;
    private int quantity;

    // Getters and setters
}

// You can add more controllers and implement user authentication, order management, etc.
