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
 - chạy test case bằng cách chạy file testng.xml (tất cả test case) bằng lệnh: mvn clean test
 - chạy test case cụ thể bằng cách chạy file testng.xml (chỉ chạy test case cụ thể) bằng lệnh: mvn clean test -Dtest=LoginTest#TC01
 - Cấu trúc dự án
   src/main/java/org/example/Common: Chứa các lớp tiện ích và constants.
   src/main/java/org/example/PageObjects: Chứa các lớp đại diện cho các trang web.
   src/test/java/org/example/TestCases: Chứa các lớp testcase.
 - Cấu trúc file testng.xml
   src/test/resources/testng.xml: Chứa thông tin cấu hình cho TestNG.
 - Report
   target/surefire-reports: Chứa kết quả chạy test case.
   target/surefire-reports/emailable-report.html: Báo cáo kết quả chạy test case.
   hoặc file extent-report_datetime.html: Báo cáo kết quả chạy test case.
 