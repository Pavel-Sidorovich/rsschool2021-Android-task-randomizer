import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    // Stdlib
    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    private const val kotlinStdLibJdk =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"

    // Android UI
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    private const val material = "com.google.android.material:material:${Versions.material_version}"

    // Coroutine Lifecycle Scopes
    private const val livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    private const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    private const val runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"

    private const val common =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"

    // Fragment KTX
    private const val fragmentKtx =
        "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"

    val appLibraries = arrayListOf(
        kotlinStdLib,
        kotlinStdLibJdk,
        coreKtx,
        appcompat,
        constraintLayout,
        material,
        livedata,
        viewmodel,
        runtime,
        common,
        fragmentKtx
    )

}

// Util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

