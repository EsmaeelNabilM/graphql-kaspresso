package com.example.graphspresso.testUtils

import android.app.Activity
import androidx.test.core.app.ActivityScenario

fun <T : Activity> goto(to: Class<T>): ActivityScenario<T> {
    return ActivityScenario.launch(to)
}