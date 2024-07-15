# enocaBackendChallenge API Documentation

## Add Customer

**POST** `localhost:8080/customers/add`

Müşteri eklemek için kullanılacak olan request

### Request Body
```json
{
    "name": "Enes Horos",
    "email": "eneshoros@gmail.com"
}
```

---

## Get Product by ID

**GET** `localhost:8080/products/2`

Tek bir ürüne erişim için kullanılacak olan request

---

## Get All Products

**GET** `localhost:8080/products/all`

Tüm ürünlere erişim için kullanılacak olan request

---

## Create Product 

**POST** `localhost:8080/products/create`

Ürün eklemek için kullanılacak olan request

### Request Body
```json
{
    "name": "Not Defteri",
    "price": 34.99,
    "stockQuantity": 23
}
```

---

## Update Product by ID 

**PUT** `localhost:8080/products/1/update`

Ürün güncellemek için kullanılacak olan request

### Request Body
```json
{
    "name": "Soru Kitabı",
    "price": 155.99,
    "stockQuantity": 41
}
```

---

## Delete Product by ID 

**DELETE** `localhost:8080/products/delete/1`

Ürün silmek için kullanılacak olan request

---

## Get Cart by ID

**GET** `localhost:8080/api/carts/1`

Tek bir sepete erişim için kullanılacak olan request

---

## Create Cart for Customer by ID

**POST** `localhost:8080/api/carts/1/create-cart`

Bir müşteriye bir sepet oluşturmak için kullanılacak olan request

---

## Empty Cart by ID

**PUT** `localhost:8080/api/carts/1/empty`

Sepeti boşaltmak için kullanılacak olan request

### Request Body
```json
{
    "customerId": 1,
    "productId": 1,
    "quantity": 2
}
```

---

## Get Cart Item by ID

**GET** `localhost:8080/api/cartItems/1`

Tek bir sepet ürününe erişim için kullanılacak olan request

---

## Get All Cart Items

**GET** `localhost:8080/api/cartItems/all`

Tüm sepete ürünlerine erişim için kullanılacak olan request

---

## Add Product to Cart Item

**POST** `localhost:8080/api/cartItems/add`

Sepete ürün eklemek için kullanılacak olan request

### Request Body
```json
{
    "cartId": 2,
    "productId": 1,
    "quantity": 2
}
```

---

## Update Cart Item by ID

**PUT** `localhost:8080/api/cartItems/update/1`

Sepetteki ürünü güncellemek için kullanılacak olan request

### Request Body
```json
{
    "quantity": 1
}
```

---

## Empty Cart Item by ID

**PUT** `localhost:8080/api/cartItems/1/empty`

Sepetteki ürünü güncellemek için kullanılacak olan request

---

## Remove Product from Cart by Cart and Cart Item ID

**DELETE ** `localhost:8080/api/cartItems/1/remove-product/1`

Sepetteki belirli bir ürünü sepetten kaldırmak için kullanılacak olan request

---

## Delete Cart Item by ID

**DELETE ** `localhost:8080/api/cartItems/1`

Sepetteki belirli bir ürünü silmek için kullanılacak olan request
