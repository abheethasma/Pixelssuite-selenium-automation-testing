<div align="center">

<img src="https://img.shields.io/badge/PixelsSuite-Automation-6366f1?style=for-the-badge&logo=selenium&logoColor=white" alt="PixelsSuite Automation"/>

# 🧪 PixelsSuite Selenium Automation Testing

### End-to-end automated test suite for the **PixelsSuite** web application

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![TestNG](https://img.shields.io/badge/TestNG-FF6C37?style=for-the-badge&logo=testng&logoColor=white)](https://testng.org/)
[![Chrome](https://img.shields.io/badge/Chrome-4285F4?style=for-the-badge&logo=googlechrome&logoColor=white)](https://www.google.com/chrome/)

</div>

---

## 📌 Overview

This project automates core feature testing of the **PixelsSuite** web application using **Selenium WebDriver**, **Java**, **Maven**, and **TestNG**. It is organized **feature by feature** for clean, maintainable, and independently executable test suites.

> 🎓 *Created for* **IT4100 – Software Quality Assurance** *as an academic learning project.*

---

## 🎯 Testing Scope

| Property | Details |
|---|---|
| **Application** | PixelsSuite |
| **Type** | Web Application |
| **Scope** | Whole system *(Transliteration excluded)* |
| **Approach** | Feature-by-feature execution |

---

## 🛠️ Automation Stack

| Tool | Purpose |
|---|---|
| ☕ **Java** | Core programming language |
| 🌐 **Selenium WebDriver** | Browser automation |
| 📦 **Maven** | Dependency management & build |
| 🧪 **TestNG** | Test execution framework |
| 🟡 **Google Chrome** | Browser under test |

---

## ✅ Features Covered

<details>
<summary>📄 <strong>Document Converter</strong></summary>

- Image → PDF
- PDF → Word
- Word → PDF
- Empty submission validation

</details>

<details>
<summary>📐 <strong>Resize</strong></summary>

- Resize
- Batch Resize
- Image Enlarger
- Empty submission validation

</details>

<details>
<summary>✂️ <strong>Crop</strong></summary>

- To JPG
- To PNG
- To WebP
- Empty submission validation

</details>

<details>
<summary>🗜️ <strong>Compress</strong></summary>

- Compress Image
- To GIF
- To PNG
- Empty submission validation

</details>

<details>
<summary>🖼️ <strong>Image Converter</strong></summary>

- To JPG
- To PNG
- To WebP
- Empty submission validation

</details>

<details>
<summary>📝 <strong>PDF Editor</strong></summary>

- Open editor with valid PDF
- Add text and download edited PDF
- Initial state validation
- Empty submission validation

</details>

<details>
<summary>🔧 <strong>More Tools</strong></summary>

- Rotate
- Flip
- Meme
- Color Picker
- Image → Text OCR
- Empty submission validation

</details>

---

## 🗂️ Project Structure

```text
pixelssuite-selenium-automation-testing/
│
├── 📄 pom.xml
├── 📄 testng.xml
├── 📄 .gitignore
├── 📄 README.md
│
├── 📁 downloads/          ← Downloaded output files from test runs
├── 📁 screenshots/        ← Captured screenshots during automation
├── 📁 test-data/          ← Sample input files (images, PDFs, Word docs)
│
└── 📁 src/
    └── 📁 test/
        └── 📁 java/
            ├── 📁 base/
            │   └── ☕ BaseTest.java
            └── 📁 tests/
                ├── ☕ DocumentConverterTest.java
                ├── ☕ ResizeTest.java
                ├── ☕ CropTest.java
                ├── ☕ CompressTest.java
                ├── ☕ ImageConverterTest.java
                ├── ☕ PdfEditorTest.java
                └── ☕ MoreToolsTest.java
```

---

## ⚙️ Prerequisites

Make sure the following are installed on your machine:

| Requirement | Check Command |
|---|---|
| ☕ Java | `java -version` |
| 📦 Maven | `mvn -version` |
| 🟡 Google Chrome | — |
| 🔧 Git | `git --version` |
| 💻 IDE | VS Code or IntelliJ IDEA |

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd pixelssuite-selenium-automation-testing
```

### 2. Install Dependencies & Compile

```bash
mvn clean compile
```

---

## ▶️ Running the Tests

### 🔹 Run a Single Feature Class

```bash
mvn -Dtest=DocumentConverterTest test
mvn -Dtest=ResizeTest test
mvn -Dtest=CropTest test
mvn -Dtest=CompressTest test
mvn -Dtest=ImageConverterTest test
mvn -Dtest=PdfEditorTest test
mvn -Dtest=MoreToolsTest test
```

### 🔹 Run a Single Test Method

```bash
mvn -Dtest=DocumentConverterTest#AT_DC_01_imageToPdfValidConversion test
```

### 🔹 Run All Tests

```bash
mvn test
```

### 🔹 Build Without Running Tests

```bash
mvn clean install -DskipTests
```

---

## 📁 Test Data

The `test-data/` folder contains all sample input files used during automation:

| Type | Used For |
|---|---|
| 🖼️ Images | Converter, Resize, Crop, Compress, More Tools |
| 📄 PDF files | Document Converter, PDF Editor |
| 📝 Word files | Document Converter (Word → PDF) |
| 🔤 OCR input files | Image → Text OCR |
| 😂 Meme images | Meme generator tool |

---

## 📸 Generated Output

During test execution, the following directories are populated:

```
📁 screenshots/   →  UI screenshots captured during test flows
📁 downloads/     →  Output files downloaded from the application
```

---

## 🔄 Recommended Workflow

```
1️⃣  Open the project in your IDE
       ↓
2️⃣  Compile with Maven
       ↓
3️⃣  Run one feature class at a time
       ↓
4️⃣  Review output files and screenshots
       ↓
5️⃣  Fix locators or assertions if needed
       ↓
6️⃣  Move on to the next feature
```

---

## 📋 Test Coverage Summary

| # | Feature | Positive Tests | Validation Tests |
|---|---|:---:|:---:|
| 1 | Document Converter | ✅ | ✅ |
| 2 | Resize | ✅ | ✅ |
| 3 | Crop | ✅ | ✅ |
| 4 | Compress | ✅ | ✅ |
| 5 | Image Converter | ✅ | ✅ |
| 6 | PDF Editor | ✅ | ✅ |
| 7 | More Tools | ✅ | ✅ |

---

## ⚠️ Notes

- 🎓 This project is intended for **academic and learning purposes only**
- 🔁 Some dynamic UI elements may require **locator adjustments** if the live website changes
- 🧩 **Feature-wise execution** is strongly recommended for easier debugging and isolation

---

## 👨‍💻 Author

<div align="center">

**Abheetha Dhananjaya**
* Software Quality Assurance *
**Selenium Automation Project — PixelsSuite Whole System Testing**


</div>

---

## 📜 License

<div align="center">

This project is created for **educational purposes only**.

</div>
