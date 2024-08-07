
![Backgroundable Logo](app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp)

# Backgroundable - Android Wallpaper App

Backgroundable is an Android app that allows you to set beautiful wallpapers for your phone. With millions of images and thousands of categories to choose from, you can easily find the perfect wallpaper to personalize your device. The app also offers various features, including downloading images in six different resolutions, M3 dynamic themes, and much more.

## Features

- **Search in Millions of Images:** Explore a vast collection of images from various sources to find the perfect wallpaper for your device.
- **Thousands of Categories:** Browse through thousands of categories to discover wallpapers that match your preferences and style.
- **Download in Any 6 Resolution:** Choose from six different resolutions to download wallpapers that perfectly fit your screen.
- **M3 Dynamic Theme:** Enjoy dynamic themes that change automatically based on your preferences and time of day.
- **And Much More:** Backgroundable offers a range of additional features to enhance your wallpaper experience.

## Screenshots

| Home                                                                            | Media List                                                                           | Media Detail                                                                             | Search                                                                              | Settings                                                                                |
|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| <img src="resource/screenshots/homeScreen.png" alt="Home" style="width:200px"/> | <img src="resource/screenshots/MediaList.png" alt="Media List" style="width:200px"/> | <img src="resource/screenshots/MediaDetail.png" alt="Media Detail" style="width:200px"/> | <img src="resource/screenshots/SearchScreen.png" alt="Search" style="width:200px"/> | <img src="resource/screenshots/SettingsScreen.png" alt="Settings" style="width:200px"/> |


## Figma Link

Explore the app's design and mockups on Figma by following this link: [Figma Design](https://www.figma.com/file/38WKj9umF8Wz84n21Uxn2O/Backgroundable?type=design&node-id=0%3A1&mode=design&t=g4D2qNkdP51Gopms-1)

## Powered by [Pexels API](https://www.pexels.com)

Backgroundable is powered by the Pexels API, which provides a vast collection of high-quality images for use in the app. According to the Pexels API, thousands of new images are added every day, ensuring a constantly growing selection for our users.

![Pexels Logo](resource/pexels.svg)

## Download

[Download the latest version of Backgroundable](https://github.com/javadjafari1/Backgroundable/releases)

## Building the Project

Follow these steps to build the Backgroundable project:

1. **Clone the Project:**
   ```bash
   git clone https://github.com/javadjafari1/Backgroundable.git
   ```
2. **Setup Authorization:**
   - For better security, sensitive information such as URLs and tokens are stored in an `authorization.properties` file, which is not pushed to the git repository.
   - Create a file named authorization.properties in the properties folder.
   - Add your token with the name authorization in the authorization.properties file.
     Example authorization.properties:
     `authorization=your_token_here`
3. **Optional: Configure Nexus Properties:**
   - If you have a personal Nexus repository, you can configure your personal Nexus properties in the `nexus.properties` file.
   - Create a file named `nexus.properties` in the properties folder.
   - Add your Nexus properties in the `nexus.properties` file.
   - Example nexus.properties:
     ```
     username=your-nexus-username
     password=your-nexus-password
     url=your-nexus-url
     ```

## Usage

1. Open Backgroundable on your Android device.
2. Browse or search for wallpapers using the intuitive interface.
3. Select a wallpaper you like.
4. Choose the resolution that matches your device's screen size.
5. Download or set the wallpaper as your background.

## Libraries

This app is built with the help of the following libraries and dependencies:

- Android Gradle Plugin (Version 8.5.0).
- [Accompanist]([link_to_library2](https://github.com/google/accompanist)) (Version 0.34.0).
- [Coil](https://github.com/coil-kt/coil) (Version 2.6.0).
- Compose (Version 1.6.8).
- [Detekt](https://github.com/detekt/detekt) (Version 1.23.6).
- [Dagger](https://dagger.dev/) (Version 2.51.1).
- [Chucker](https://github.com/ChuckerTeam/chucker/) (Version 4.0.0).
- Datastore (Version 1.1.1).
- [Telephoto](https://github.com/saket/telephoto) (Version 0.11.2).
- KotlinxSerialization (Version 1.7.1).
- Kotlin (Version 2.0.0).
- Navigation Compose (Version 2.7.7).
- Paging3 (Version 3.3.0).
- [Retrofit2](https://github.com/square/retrofit) (Version 2.11.0).
- [Okhttp3](https://github.com/square/okhttp) (Version 4.12.0).
- Room (Version 2.6.1).
- KSP (Version 2.0.0-1.0.21).

Test Libraries

- [Junit5](https://github.com/junit-team/junit5) (Version 5.10.1)
- [KotestAssertion](https://github.com/kotest/kotest) (Version 5.9.1)
- [Kover](https://github.com/Kotlin/kotlinx-kover) (Version 0.7.6)
- [Jacoco](https://github.com/jacoco/jacoco) (Version 0.8.11)
- [MockK](https://github.com/mockk/mockk) (Version 1.13.10)
- [Turbine](https://github.com/cashapp/turbine) (Version 1.1.0)

## Regular Updates

We are committed to providing the best experience for our users, which is why Backgroundable is regularly updated with new features, improvements, and bug fixes. The continuous addition of fresh images from the Pexels API ensures that you have access to the latest and trending wallpapers.

## Contributing

We welcome contributions from the open-source community. If you would like to contribute to Backgroundable, please follow these guidelines:

1. Fork the repository and create your branch from `develop`.
2. Make changes and test thoroughly.
3. Create a pull request with a detailed description of the changes.

## Issues

If you encounter any bugs, glitches, or have suggestions for improvements, please create an issue in the GitHub repository.

---

## Contact Us

If you have any questions, encounter issues, or want to provide feedback, please feel free to email me at [javad2147@yahoo.com](mailto:javad2147@yahoo.com).

## Support the Project

If you find Backgroundable useful and would like to support its development, consider buying me a coffee! Your contribution helps keep this project alive and growing.

[![Buy Me a Coffee](https://img.shields.io/badge/Buy%20Me%20a%20Coffee-Donate-blue)](https://www.buymeacoffee.com/javad21476q)

---
We hope you enjoy using Backgroundable! Feel free to leave feedback, report issues, or contribute to the project. Happy wallpapering! 🎉
