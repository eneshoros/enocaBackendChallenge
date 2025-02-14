# enocaBackendChallenge API Documentation

## Add Customer

**POST** `localhost:8080/customers/add`

Müşteri eklemek için kullanılacak olan istek

### Request Body
```json
{
    "name": "Enes Horos",
    "email": "eneshoros@gmail.com"
}
```

---

## Get Product by ID

**GET** `localhost:8080/products/{productId}`

Tek bir ürüne erişim için kullanılacak olan istek

---

## Get All Products

**GET** `localhost:8080/products/all`

Tüm ürünlere erişim için kullanılacak olan istek

---

## Create Product 

**POST** `localhost:8080/products/create`

Ürün eklemek için kullanılacak olan istek

### Request Body
```json
{
    "name": "productName",
    "price": 999,
    "stockQuantity": 8
}
```

---

## Update Product by ID 

**PUT** `localhost:8080/products/{productId}/update`

Ürün güncellemek için kullanılacak olan istek

### Request Body
```json
{
    "name": "Soru Kitabı",
    "price": 155.99,
    "stockQuantity": 61
}
```

---

## Delete Product by ID 

**DELETE** `localhost:8080/products/delete/{productId}`

Ürün silmek için kullanılacak olan istek

---

## Get Cart by ID

**GET** `localhost:8080/api/carts/{cartId}`

Tek bir sepete erişim için kullanılacak olan istek

---

## Update Cart by ID

**PUT** `localhost:8080/api/carts/{cartId}/update-product/{productId}?newQuantity={newQuantity}`

Sepetteki ürünün miktarını güncellemek için kullanılacak olan istek

### Query Params

newQuantity:{newQuantity}

---

## Empty Cart by ID

**PUT** `localhost:8080/api/carts/{cartId}/empty`

Sepeti boşaltmak için kullanılacak olan istek

---

## Place Order

**POST** `localhost:8080/api/orders/placeOrder/{customerId}`

Sipariş vermek için kullanılacak olan istek

### Request Body
```json
[
    {
        "productId": 1,
        "quantity": 3
    },
    {
        "productId": 2,
        "quantity": 2
    },
    {
        "productId": 3,
        "quantity": 1
    }
]

```

---

## Get Order For Code

**GET** `localhost:8080/api/orders/{orderCode}`

Sipariş koduna göre siparişi getirmek için kullanılacak olan istek

---

## Get All Orders For Customer

**GET** `localhost:8080/api/orders/customer/{customerId}`

Müşterinin bütün siparişlerini getirmek için kullanılacak olan istek

---

## Add Prodcut To Cart 1

**POST** `localhost:8080/api/carts/add-product`

Sepete ürün eklemek için kullanılacak olan istek

### Request Body
```json
{
    "cartId": 6,
    "productId": 5,
    "quantity": 1
}
```

---

## Add Prodcut To Cart 2

**POST** `localhost:8080/api/carts/add-product`

Sepete ürün eklemek için kullanılacak olan istek

### Request Body
```json
{
    "cartId": 5,
    "productId": 2,
    "quantity": 3
}
```

---

## Add Prodcut To Cart 3

**POST** `localhost:8080/api/carts/add-product`

Sepete ürün eklemek için kullanılacak olan istek

### Request Body
```json
{
    "cartId": 5,
    "productId": 1,
    "quantity": 1
}
```

---

## Remove Product from Cart by CartID and Product ID

**DELETE ** `localhost:8080/api/carts/{cartId}/remove-product/{productId}`

Sepetteki belirli bir ürünü sepetten kaldırmak için kullanılacak olan istek
