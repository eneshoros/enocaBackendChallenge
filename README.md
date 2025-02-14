# Enoca Backend Challenge

Bu proje, Enoca tarafından sunulan backend programlama challenge için geliştirilmiştir. Proje, Java programlama dilinde Spring Boot ve Maven kullanılarak geliştirilmiş olup, PostgreSQL veritabanıyla entegre edilmiştir.

## Başlangıç

Projeyi yerel makinenizde çalıştırmak için aşağıdaki adımları izleyebilirsiniz.

### Gereksinimler

Projeyi çalıştırmak için aşağıdaki yazılımların yüklü olması gerekmektedir:

- Java 8 veya üzeri
- Maven
- PostgreSQL

### Kurulum

1. **Repoyu Clone Edin**

   ```sh
   git clone https://github.com/eneshoros/enocaBackendChallenge.git

   ```

2. **Veritabanı Yapılandırması**

   PostgreSQL veritabanınızı kurduktan sonra, application.properties dosyasını açarak aşağıdaki ayarları yapın:

   ```properties

   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password

   ```

3. **Uygulamayı Başlatın**

   Proje dizinine gidin ve aşağıdaki komutu çalıştırın:

   ```sh
   mvn spring-boot:run

   ```

4. **API'yi Kullanma**

   Uygulama başarıyla başlatıldıktan sonra, API'ye aşağıdaki URL üzerinden erişebilirsiniz:

   ```bash
   http://localhost:8080/apiName
   ```

   API dokümantasyonu ve kullanımı için [buraya](https://github.com/eneshoros/enocaBackendChallenge/blob/master/apiDocumentation.md) bakabilirsiniz.

---

Bu README dosyası, projeyle ilgili temel bilgileri sunarak projeyi yerel makinenizde başlatma adımlarını açıklar.
