# Todo Application with AI Integration

Ứng dụng quản lý công việc cá nhân tích hợp với Google Gemini AI để tạo công việc thông minh.

## Công nghệ sử dụng

- Spring Boot 3.2.3
- Spring Data JPA
- MySQL
- Thymeleaf
- Swagger UI
- Google Gemini AI
- Lombok
- Bootstrap 5

## Yêu cầu hệ thống

- Java 17 trở lên
- MySQL 8.0 trở lên
- Maven
- Gemini API Key

## Cài đặt và Chạy

1. Clone repository:
```bash
git clone <repository-url>
cd todo-chatbot
```

2. Cấu hình database trong `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Thêm Gemini API Key vào `application.properties`:
```properties
gemini.api.key=your_api_key
```

4. Build và chạy ứng dụng:
```bash
mvn clean install
mvn spring-boot:run
```

5. Truy cập ứng dụng:
- Web UI: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

## Tính năng

### Quản lý công việc cơ bản
- Xem danh sách công việc
- Thêm công việc mới
- Chỉnh sửa công việc
- Xóa công việc
- Đánh dấu hoàn thành/chưa hoàn thành

### Lọc và Sắp xếp
- Lọc theo trạng thái (tất cả/đã hoàn thành/chưa hoàn thành)
- Sắp xếp theo thời gian tạo

### Tích hợp AI
- Tạo công việc tự động với Google Gemini AI
- Phân tích yêu cầu bằng ngôn ngữ tự nhiên
- Gợi ý tiêu đề và mô tả công việc

### Giao diện người dùng
- Thiết kế responsive với Bootstrap 5
- Form nhập liệu thân thiện
- Hiển thị thời gian theo định dạng 24 giờ
- Xác nhận trước khi xóa
- Thông báo lỗi và thành công

## API Documentation

### REST Endpoints

#### Todo API
- `GET /api/todos`: Lấy danh sách tất cả công việc
- `GET /api/todos/{id}`: Lấy thông tin một công việc
- `POST /api/todos`: Tạo công việc mới
- `PUT /api/todos/{id}`: Cập nhật công việc
- `DELETE /api/todos/{id}`: Xóa công việc
- `GET /api/todos/completed`: Lấy danh sách công việc đã hoàn thành
- `GET /api/todos/uncompleted`: Lấy danh sách công việc chưa hoàn thành
- `POST /api/todos/generate`: Tạo công việc với AI

Chi tiết API có thể xem tại Swagger UI: http://localhost:8080/swagger-ui.html

## Cấu trúc project

```
todo-chatbot/
├── src/main/java/
│   └── com/example/todochatbot/
│       ├── config/
│       ├── controller/
│       ├── dto/
│       ├── entity/
│       ├── exception/
│       ├── repository/
│       ├── service/
│       └── TodoChatbotApplication.java
├── src/main/resources/
│   ├── templates/
│   │   ├── index.html
│   │   └── edit.html
│   └── application.properties
└── pom.xml
```

## Screenshots

*(Có thể thêm screenshots của ứng dụng vào đây)*

## License

MIT License

## Author

*(Thêm thông tin tác giả nếu muốn)*