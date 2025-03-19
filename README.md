# Multi-Page App

# Multi-Page App - "Seamless Navigation & Android Features"

This project is a multi-functional Android application that demonstrates various Android features, including content providers, deep linking, foreground services, and system broadcast handling.  
Users can navigate through multiple fragments, access the device's calendar events, interact with Instagram for story sharing, and monitor Airplane Mode changes dynamically.

****

### Code source available on GitHub: [MultiPageApp](https://github.com/Sunbekova/Multi-Page_App)

## About the App  
The Multi-Page App integrates multiple Android functionalities using **Fragments** and **Navigation Components**.  
It allows users to:
- View calendar events using **Content Providers**.
- Share images to Instagram stories via **Deep Linking**.
- Monitor **Airplane Mode changes** with **Broadcast Receivers**.
- Utilize **Foreground Services** for background tasks.

The app provides a modular, interactive experience showcasing key Android development concepts.

****

# Table of Contents
1. [Technical Topics](#technical-topics)
2. [App Source](#app-source)
3. [Features Overview](#features-overview)
4. [Permissions and APIs](#permissions-and-apis)
5. [Project Preview](#project-preview)
6. [Project Creator](#project-creator)
7. [Topics Covered](#topics-covered)


# Technical Topics
| Android Concepts |
|-----------------|
| Fragments & Navigation Component |
| ViewBinding |
| Content Providers |
| Broadcast Receivers |
| Foreground Services |
| Deep Linking |
| Permissions Handling |

# App Source
The Multi-Page App provides seamless navigation and interaction with various Android features.  

| Navigation | Description |
|------------|-------------|
| **Home Fragment** | Provides navigation options to different features. |
| **Content Provider Fragment** | Fetches and displays upcoming calendar events. |
| **Deep Linking Fragment** | Allows users to select and share images on Instagram stories. |
| **Foreground Service Fragment** | Demonstrates a running foreground service. |
| **Airplane Mode Monitoring** | Listens for system-wide airplane mode changes. |

# Features Overview

## Content Provider - Fetch Calendar Events
The app retrieves **upcoming calendar events** from the device using **Android's Content Provider API**.

- Displays event titles & start times.  
- Uses **runtime permissions** to access the calendar.  
- Updates UI dynamically upon fetching events.

## Deep Linking - Share to Instagram Story
Users can select an image and share it directly to their **Instagram Story**.

- Uses **ActivityResultContracts.GetContent()** for image selection.  
- Checks if **Instagram is installed** before sharing.  
- Redirects users to Play Store if the app is missing.  
- Grants required **URI permissions** for Instagram sharing.

## Broadcast Receiver - Airplane Mode Monitoring
The app listens for **Airplane Mode changes** and notifies users.

- Uses **BroadcastReceiver** to detect mode changes.  
- Displays a **Toast notification** when Airplane Mode toggles.  
- Allows users to **open Airplane Mode settings** from the app.

## Foreground Service - Persistent Background Task
The app demonstrates how to use **Foreground Services**.

- Runs a **long-running background task**.  
- Shows a **persistent notification** while running.  
- Ensures **efficient resource management**.

# Permissions and APIs

The app requires the following **permissions** to function:

| Permission | Purpose |
|------------|---------|
| `READ_CALENDAR` | Required to fetch calendar events. |
| `INTERNET` | Needed for external links (e.g., Instagram deep linking). |
| `FOREGROUND_SERVICE` | Required to run foreground services. |

# Project Preview

| **Home Screen** | **Calendar Events** |  **Deep Linking** | **Foreground Service** | 
|----------------|----------------------|-------------------|--------------------------| 
| ![home](https://github.com/user-attachments/assets/afce96d1-c060-42b2-a310-a09333d1778c) | ![calendar](https://github.com/user-attachments/assets/4379f75b-1589-4fdf-a09d-09cd4e0359ec) | ![deepLink](https://github.com/user-attachments/assets/08f2de52-2c0a-4da6-8d60-ef8f2c23ef16) | ![music](https://github.com/user-attachments/assets/56d0dfca-4731-447d-80d0-f77d60003626) |



https://github.com/user-attachments/assets/4b0424e7-6155-4ffa-ade9-ef5511aaadbe



https://github.com/user-attachments/assets/db9abb8a-cc0c-4047-8b1d-c727434f72cf



https://github.com/user-attachments/assets/d2cbc038-432e-4db0-b757-a3cb15654f01



https://github.com/user-attachments/assets/e3b6591d-3bbb-4cae-bede-71a27f9c6a0c



[Go to top](#multi-page-app)

# Project Creator
| Full Name | ID | GitHub |
|-----------|----|--------|
| Suanbekova Aisha | 22B030589 | [Suanbekova](https://github.com/Sunbekova/) |

[Go to top](#multi-page-app)

# Topics Covered

| Feature                    | Implemented |
|----------------------------|-------------|
| **Navigation Component**    | ✓ |
| **ViewBinding**             | ✓ |
| **Content Providers**       | ✓ |
| **Broadcast Receivers**     | ✓ |
| **Foreground Services**     | ✓ |
| **Deep Linking**            | ✓ |
| **Permissions Handling**    | ✓ |

[Go to top](#multi-page-app)
