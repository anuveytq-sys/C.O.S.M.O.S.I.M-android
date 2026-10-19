<div align="center">

<img src="docs/icon.png" width="140" alt="C.O.S.M.O.S.I.M icon"/>

# C.O.S.M.O.S.I.M · Android

### A 3D realistic space simulator — now packaged as an Android APK

**C.O.S.M.O.S.I.M** (COSMOSIM) is the Android APK version of the C.O.S.M.O.S.I.M space-simulation website. It brings the project's interactive 3D space environment, realistic simulation systems, Destroyer Mode and Interstellar Flight Simulator Mode to Android.

![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Min SDK](https://img.shields.io/badge/minSdk-23-00EAFF?style=for-the-badge)
![Target SDK](https://img.shields.io/badge/targetSdk-35-0A84FF?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-00C853?style=for-the-badge)

</div>

---

## 🌌 About C.O.S.M.O.S.I.M

C.O.S.M.O.S.I.M is a **3D space simulator and interactive space experience** designed to bring a large-scale cosmic environment to the screen.

This repository is the **Android APK version of the C.O.S.M.O.S.I.M website**. The Android project is intended to provide the same core COSMOSIM experience in an installable Android application rather than being a separate JARVIS assistant project.

The simulator focuses on exploring space, interacting with large-scale 3D environments and experiencing different simulation modes from the COSMOSIM web version.

> **Important:** C.O.S.M.O.S.I.M-android is the Android version of COSMOSIM. It should not be described as a JARVIS WebView application or as a generic voice-assistant wrapper.

---

## 🚀 Main Simulation Experiences

### 🌍 3D Realistic Space Simulation

Experience a large interactive 3D space environment with a focus on realistic-looking celestial objects, deep-space surroundings and immersive movement through the simulation.

### 🛰️ Space Exploration

Explore the simulated universe and interact with its different celestial and space-simulation elements.

### 💥 Destroyer Mode

A dedicated simulation/gameplay mode focused on high-intensity space scenarios and large-scale destruction effects inside the simulated environment.

### 🚀 Interstellar Flight Simulator Mode

Fly through the simulated interstellar environment and experience long-distance space travel in a dedicated flight-simulation mode.

### 🌌 Multiple Space Environments

COSMOSIM is designed to provide different environments and scenarios rather than being limited to a single static 3D scene.

### 📱 Android APK Experience

The goal of this repository is to package the COSMOSIM website experience for Android so it can be launched and used as an installable APK.

---

## 🧭 Core Modes

| Mode | Description |
|---|---|
| 🌌 **3D Space Simulator** | Explore an interactive 3D cosmic environment |
| 💥 **Destroyer Mode** | Experience a dedicated large-scale space destruction simulation mode |
| 🚀 **Interstellar Flight Simulator** | Pilot through the simulated interstellar environment |
| 🛰️ **Space Exploration** | Explore and interact with the available simulated space elements |

---

## 🌐 Website → Android APK

The relationship between the two projects is simple:

```text
┌──────────────────────────────────────┐
│          C.O.S.M.O.S.I.M             │
│       3D Space Simulator Website     │
└──────────────────┬───────────────────┘
                   │
                   │ Android version
                   ▼
┌──────────────────────────────────────┐
│       C.O.S.M.O.S.I.M-android        │
│            Installable APK           │
└──────────────────────────────────────┘
```

The Android repository exists to bring the COSMOSIM website/project to Android as an APK while preserving the project's space-simulation identity and core experience.

---

## ✨ Project Highlights

- 🌌 3D space simulation
- 🌍 Realistic-looking cosmic environments
- 🛰️ Interactive space and celestial simulation
- 🚀 Interstellar flight simulator mode
- 💥 Destroyer mode
- 🌠 Large-scale space environments
- 📱 Android APK packaging
- 🎮 Simulation-oriented controls and interaction
- 🔭 Immersive deep-space experience

---

## 📁 Project Structure

The Android project contains the native Android packaging required to build COSMOSIM as an APK.

```text
C.O.S.M.O.S.I.M-android/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── assets/
│       ├── java/
│       └── res/
├── docs/
├── build.gradle
├── settings.gradle
├── gradle.properties
├── LICENSE
└── README.md
```

The exact simulation implementation and assets may evolve as the Android version is brought into closer alignment with the COSMOSIM website.

---

## 🛠️ Getting Started

### Requirements

- **Android Studio** (recent stable version)
- **JDK 17**
- Android device or emulator running **Android 6.0 (API 23)** or newer
- A working Android SDK installation

### Clone the repository

```bash
git clone https://github.com/anuveytq-sys/C.O.S.M.O.S.I.M-android.git
cd C.O.S.M.O.S.I.M-android
```

### Open in Android Studio

1. Open the cloned project in Android Studio.
2. Allow Gradle synchronization to complete.
3. Connect an Android device or start an emulator.
4. Build and run the application.

---

## 📦 Build the APK

From Android Studio:

1. Open **Build**.
2. Select **Generate Signed Bundle / APK**.
3. Select **APK**.
4. Choose or create a signing keystore.
5. Complete the build process.

The generated APK can then be installed on a compatible Android device.

For development builds, Android Studio can also build and install the application directly on a connected device or emulator.

---

## ⚙️ Android Configuration

The current Android project is configured with:

| Setting | Value |
|---|---|
| **Application ID** | `com.anuveytq.jarvisweb` |
| **Minimum SDK** | Android API 23 |
| **Target SDK** | Android API 35 |
| **Compile SDK** | Android API 35 |
| **Build system** | Gradle / Android Gradle Plugin |
| **Primary platform** | Android |

> The application ID is an Android project identifier and does not change the identity of the COSMOSIM project itself.

---

## 🎮 Simulation Direction

C.O.S.M.O.S.I.M-android is intended to evolve alongside the main COSMOSIM website.

The Android version should preserve the project's core identity:

- A **3D space simulator**, not a generic utility application.
- A **realistic and immersive space environment**.
- Multiple simulation experiences and modes.
- A dedicated **Destroyer Mode**.
- A dedicated **Interstellar Flight Simulator Mode**.
- A mobile-friendly way to experience COSMOSIM as an APK.

---

## 🗺️ Roadmap

The Android version can continue expanding toward feature parity with the COSMOSIM website, including:

- [ ] More realistic 3D space environments
- [ ] Expanded celestial-object simulation
- [ ] More detailed interstellar flight systems
- [ ] Expanded Destroyer Mode
- [ ] More space scenarios and missions
- [ ] Improved Android controls
- [ ] Performance optimization for mobile GPUs
- [ ] Improved graphics and visual effects
- [ ] Better support for different Android screen sizes
- [ ] Closer feature parity with the COSMOSIM website

---

## 🔧 Troubleshooting

### APK does not build

- Make sure Android Studio has completed Gradle synchronization.
- Confirm that the required Android SDK is installed.
- Confirm that JDK 17 is configured for the project.
- Try **Build → Clean Project**, followed by **Build → Rebuild Project**.

### APK installs but the simulation does not load correctly

Check that the Android implementation and the current COSMOSIM website version are compatible and that any required online resources are reachable.

### Performance is low

3D space simulation can be demanding on mobile hardware. Performance depends on the device GPU, CPU, available memory, display resolution and the complexity of the current COSMOSIM scene.

---

## 🌠 Project Vision

The long-term goal of C.O.S.M.O.S.I.M is to create an increasingly immersive **3D space simulation platform** that can be experienced across the web and Android.

The Android APK is an important part of that goal: it brings the COSMOSIM experience from the website to mobile devices while continuing to expand the simulator with more realistic environments, flight simulation, interactive space systems and additional gameplay/simulation modes.

---

## 🤝 Contributing

Contributions and improvements to the Android version are welcome.

1. Fork the repository.
2. Create a feature branch.
3. Make your changes.
4. Test the Android build.
5. Commit your changes.
6. Open a Pull Request.

When contributing, keep the Android version aligned with the overall C.O.S.M.O.S.I.M vision and avoid unrelated changes.

---

## 📄 License

Released under the **MIT License**. See [`LICENSE`](LICENSE).

<div align="center">

**C.O.S.M.O.S.I.M · Android**

*3D Space Simulation · Destroyer Mode · Interstellar Flight Simulator*

**Built by [anuveytq-sys](https://github.com/anuveytq-sys)**

</div>
