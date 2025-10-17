# Employee Management System

### Chức năng chính:

- Quản lý nhân viên (Thêm, Sửa, Xóa, Tìm kiếm, Xem danh sách)
- Đăng ký / Đăng nhập
- Phân quyền (Admin/User)
- Thống kê dữ liệu nhân viên (theo phòng ban, số lượng, xu hướng)

### Công nghệ:

- Spring Boot + Spring Data JPA + REST API + Thymeleaf + MySQL/Postgres

## Module 1: Getting Started with Spring Boot

- Tạo project employee-management
- Viết API /hello để kiểm tra project chạy thành công

## Module 2: Custom Bean & IoC

- Tạo một UtilityService (ví dụ: format chuỗi, tạo mã nhân viên tự động) → đánh dấu @Service
- Định nghĩa 1 custom bean @Bean trong @Configuration (ví dụ: PasswordEncoder hoặc ModelMapper)

## Module 3: REST API cơ bản

- Tạo API lấy danh sách nhân viên (dữ liệu in-memory)
- Tạo API thêm nhân viên mới

## Module 4: Spring Boot + Database (Spring Data JPA)

- Tạo bảng employee (id, name, email, department_Id)
- Tạo bảng department
- Tạo relation giữa employee và department
- Tích hợp CRUD Employee với DB
- Bổ sung chức năng tìm kiếm nhân viên theo tên hoặc phòng ban

## Module 5: Validation & Exception Handling

- Thêm validation cho Employee (email hợp lệ, name không rỗng)
- Xử lý lỗi khi không tìm thấy Employee (404 Not Found)
- Thông báo lỗi rõ ràng khi request sai định dạng dữ liệu

## Module 6: Spring Boot Web (MVC + Thymeleaf)

- Trang /employees/list hiển thị danh sách nhân viên từ DB
- Form /employees/add để thêm nhân viên mới
- Trang tìm kiếm nhân viên theo tên/phòng ban, hiển thị kết quả​

## Module 7: Logging & Profiles

- Logging với SLF4J + Logback
- Profiles (application-dev.yml, application-prod.yml)​

## Module 8: Advanced Spring Boot

- Thêm API báo cáo tổng số nhân viên (có caching 1 phút)
- Scheduled task log ra console mỗi 30s với nội dung “System running”

## Module 9: Spring Security Basics

- Tạo User entity (username, password, role)
- Đăng ký + đăng nhập
- USER chỉ được xem danh sách, ADMIN được CRUD nhân viên

## Module 10: Reporting & Analytics (Thống kê)

- Thống kê số lượng nhân viên theo phòng ban
- Thống kê tổng số nhân viên trong hệ thống
- Hiển thị kết quả thống kê trên trang web /employees/statistics
