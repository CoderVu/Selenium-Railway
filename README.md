# Selenium Railway Project

## Giới thiệu
Dự án này sử dụng Selenium WebDriver để tự động hóa các testcase cho ứng dụng Railway. Các testcase được viết bằng Java và sử dụng TestNG framework.

## Yêu cầu hệ thống
- Java Development Kit (JDK) 11 hoặc mới hơn
- Maven 3.8.8 hoặc mới hơn
- Trình duyệt Google Chrome
- ChromeDriver tương thích với phiên bản Chrome đang sử dụng

## Cài đặt

### 1. Clone repository
Clone repository từ GitHub về máy của bạn:
```sh
git clone https://github.com/VuCoder/Selenium-Railway.git
cd Selenium-Railway
```
### 2. Cài đặt các dependencies
- mvn clean install
### 3. Chạy test case
 - chạy test case bằng cách chạy file testng.xml (tất cả test case) bằng lệnh: 
 - mvn test
 - chạy test case cụ thể bằng cách chạy file testng.xml (chỉ chạy test case cụ thể) bằng lệnh: mvn -Dtest=ClassName#methodName test
 - Cấu trúc dự án
   src/main/java/org/example/Common: Chứa các lớp tiện ích và constants.
   src/main/java/org/example/PageObjects: Chứa các lớp đại diện cho các trang web.
   src/main/java/org/example/TestCases: Chứa các lớp testcase.
 - Cấu trúc file testng.xml
   src/main/java/org/example/TestSuites: Chứa các file testng.xml.
