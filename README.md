

**GraphQL with Kaspresso Ui Testing Example**
---

- ##### Get your [Github Personal Access Token](https://github.com/settings/tokens) 

- ##### `ADD IT` in `keys.properties` file that is located in `ROOT` project folder 

``` json
token_header_name="Authorization"
base_url="https://api.github.com/graphql"
github_token="bearer Github_Personal_Access_Token"
```

---

| Feature             |  Details                         |
:---------------------|:----------------------------------
| [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) | [BaseViewModel](https://github.com/EsmaeelNabilM/graphql-kaspresso/blob/master/app/src/main/java/com/example/graphspresso/ui/base/BaseViewModel.kt#L14) <br> [BaseActivity](https://github.com/EsmaeelNabilM/graphql-kaspresso/blob/master/app/src/main/java/com/example/graphspresso/ui/base/BaseActivity.kt#L58) |
|[Koin](https://insert-koin.io/docs/quickstart/android)|[modules](https://github.com/EsmaeelNabilM/graphql-kaspresso/tree/master/app/src/main/java/com/example/graphspresso/di)|
|[GraphQl](https://www.apollographql.com/docs/android/)|[Apollo-Client](https://github.com/EsmaeelNabilM/graphql-kaspresso/blob/master/app/src/main/java/com/example/graphspresso/di/KoinModule.kt#L29)|
|[Kaspresso](https://github.com/KasperskyLab/Kaspresso)|[Scenarios](https://github.com/EsmaeelNabilM/graphql-kaspresso/tree/master/app/src/androidTest/java/com/example/graphspresso/ui/scenarios)|
|[Hiding secretKeys in properties Files](https://github.com/EsmaeelNabilM/graphql-kaspresso/blob/master/app/build.gradle#L42)|
|[Github Actions](https://github.com/EsmaeelNabilM/graphql-kaspresso/tree/master/.github/workflows)|
|[Dependencies Versions & update plugin](https://github.com/jmfayard/refreshVersions)|[buildSrc](https://github.com/EsmaeelNabilM/graphql-kaspresso/tree/master/buildSrc/src/main/kotlin)|

---
###### to get latest dependencies versions run this gradle command :
```
./gradlew refreshVersions
```
##### latest versions will be [here](https://github.com/EsmaeelNabilM/graphql-kaspresso/blob/master/gradle.properties) and [here](https://github.com/EsmaeelNabilM/graphql-kaspresso/blob/master/buildSrc/src/main/kotlin/Versions.kt) to select from.
