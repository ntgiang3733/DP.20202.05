
# Report of group DP.20202.05

- Link Report: [link](https://husteduvn-my.sharepoint.com/:w:/g/personal/giang_nt173083_sis_hust_edu_vn/EZCTgyzVOR1GnV4q1WWcTqUBdS13J_oJVlJmrKVddwfpag?e=JhGg0l)

- Link Presentation: [link](https://husteduvn-my.sharepoint.com/:p:/g/personal/giang_nt173083_sis_hust_edu_vn/EV-ktbh28pZCktDuTHMZI8EBr0hTKcwG5tBTbsh114HZbQ?e=cf4QOi)

- Link Video [link1](https://husteduvn-my.sharepoint.com/:v:/r/personal/hieu_nvt173107_sis_hust_edu_vn/Documents/DP.20202.05-video-v2.mp4?csf=1&web=1&e=sc5ane)  [link2](https://drive.google.com/file/d/14FJCCo9Iqp8g8cbdt9TEjr2NMTRBC1Zf/view?usp=sharing)

- Link github [link](https://github.com/ntgiang3733/DP.20202.05)


### Contribution

 - Subteam1: Nguyễn Trường Giang, Lê Đức Hải
 - Subteam2: Đỗ Quang Hiếu, Nguyễn Văn Trung Hiếu


### Assignment

| Student name        | Student Id           | Assignment  |
| ------------------- |:--------------------:| ----------- |
| Lê Đức Hải  | 20173094 | Các vấn đề liên quan đến State pattern và Adapter pattern|
| Đỗ Quang Hiếu | 20173108 | Các vấn đề liên quan đến Clean function/method, Template method và Factory method |
| Nguyễn Văn Trung Hiếu | 20173107 | Các vấn đề liên quan đến Cleanclass, cleanName và Observable pettern |
| Nguyễn Trường Giang | 20173083 | Các vấn đề liên quan đến Singleton pattern và Strategy pattern |
| | |

---
---
---


#  An Internet Media Store
<p align="center">
  <img src="assets/images/aims_cover_image.png" />
</p>

## Getting Started

Welcome to the AIMS project. Here is a guideline to help you get started.

## Folder Structure

The workspace contains the following folders, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `test`: the folder for testing purpose

## Dependency Management
### Working with Eclipse
Import the root directory of this repository after cloning under `Eclipse` -> `Open Projects from File System...` or by using EGit.

### SQLite
Import `sqlite-jdbc-3.7.2.jar` in `lib` under `Eclipse` -> `Project` -> `Properties` -> `Java Build Path` -> `Classpath` -> `Add JARs...`.


### JUnit
Import `JUnit5` library under `Eclipse` -> `Project` -> `Properties` -> `Java Build Path` -> `Modulepath` -> `Add Library...` -> `JUnit` -> `Next`.

### JavaFX
**Note:** At first, please try to run the project once, and then follow these steps.
1. Create a new `User Library` under `Eclipse` -> `Window` -> `Preferences` -> `Java` -> `Build Path` -> `User Libraries` -> `New`
2. Name it anything you want, e.g., `JavaFX15`, and include the ***jars*** under either the `lib/linux/javafx-sdk-15` directory for Linux distro or the `lib/win/javafx-sdk-15` directory for Windows in the project.
3. Include the library, e.g., `JavaFX15`, into the classpath.

### Add VM arguments
Click on `Run` -> `Run Configurations...`  -> `Java Application`, create a new launch configuration for your project and add these VM arguments:
- For Linux distro:
> `--module-path lib/linux/javafx-sdk-15 --add-modules javafx.controls,javafx.fxml`
- For Windows:
> `--module-path lib/win/javafx-sdk-15 --add-modules javafx.controls,javafx.fxml`

## Author
- nguyenlm - Software Engeneering Student - k61
- manhvd   - Software Engeneering Student - k61
- hieudm   - ICT - k61
